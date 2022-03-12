import entities.*;
import fileio.*;
import strategies.EnergyChoiceStrategy;
import strategies.EnergyChoiceStrategyFactory;
import utility.GetAvailableContracts;
import utility.RegisterUpdates;
import utility.WriteOutput;

import java.util.*;

/** Entry point to the simulation */
public final class Main {

  private Main() { }

  /**
   * Main function which reads the input file and starts simulation
   *
   * @param args input and output files
   * @throws Exception might error when reading/writing/opening files, parsing JSON
   */
  public static void main(final String[] args) throws Exception {
    /* reads the input from file*/
    InputLoader inputLoader = new InputLoader(args[0]);
    Input input = inputLoader.readData();
    ConsumersFactory consumersFactory = ConsumersFactory.getInstance();
    EnergyChoiceStrategyFactory strategyFactory = new EnergyChoiceStrategyFactory();
    /* get the initial information and populate the lists for consumers,
    distributors, producers and updates*/
    int numberOfTurns = input.getNumberOfTurns();
    List<Distributor> distributors = input.getInitialData().getDistributors();
    List<Consumer> consumers = input.getInitialData().getConsumers();
    List<MonthlyUpdates> monthlyUpdates = input.getMonthlyUpdates();
    List<Producer> producers = input.getInitialData().getProducers();
    producers.sort(Comparator.comparing(Producer::getId));
    Map<Integer, Integer> availableContracts;
    RegisterUpdates registerUpdates = new RegisterUpdates();
    SystemSimulation systemSimulation = new SystemSimulation();
    /* add all producers as observers to the simulation class*/
    for (var producer : producers) {
      systemSimulation.addObserver(producer);
    }
    for (int turnNumber = 0; turnNumber <= numberOfTurns; turnNumber++) {
      int removedConsumers = 0;

      /* apply changes, if they exist, but skip producers*/
      registerUpdates.registerUpdates(
          systemSimulation,
          turnNumber,
          monthlyUpdates,
          consumersFactory,
          consumers,
          distributors,
          producers,
          false);
      /* create initial strategy for distributors and choose producers*/
      if (turnNumber == 0) {
        for (Distributor distributor : distributors) {
          EnergyChoiceStrategy strategy =
              strategyFactory.createStrategy(distributor.getProducerStrategy(), producers);
          distributor.chooseProducer(strategy);
        }
      }
      /* update the map of available contracts*/
      GetAvailableContracts getAvailableContracts = new GetAvailableContracts();
      availableContracts = getAvailableContracts.getAvailableContracts(distributors);
      Map.Entry<Integer, Integer> min =
          Collections.min(availableContracts.entrySet(), Map.Entry.comparingByValue());
      /* now the consumers get to select the contract from the distributor with
      the smallest contract fee*/
      Distributor chosenDistributor = new Distributor();
      for (var distributor : distributors) {
        if (distributor.getisBankrupt()) {
          continue;
        }
        distributor.cleanUp();
        if (distributor.getId() == min.getKey()) {
          chosenDistributor = distributor;
        }
      }
      for (var consumer : consumers) {
        if (consumer.getisBankrupt()) {
          continue;
        }
        consumer.receiveSalary();
        for (var distributor : distributors) {
          if (distributor.getisBankrupt()) {
            continue;
          }
          if (consumer.getContractLength() == 0) {
            if (distributor.getId() == chosenDistributor.getId()) {
              /*if the consumer doesn't have a contract, get one and then make payments*/
              consumer.chooseDistributor(chosenDistributor);
              consumer.payContract(chosenDistributor.getContract(), distributor);
            }
            /* if consumer is bankrupt, remove him from distributor's clients list*/
            if (consumer.checkBankruptcyStatus()) {
              distributor.removeClient(consumer);
              removedConsumers++;
            }
          } else {
            if (distributor.getClients().contains(consumer)) {
              /* if consumer already has a contract, pay*/
              consumer.payContract(consumer.getCurrentContract(), distributor);
              consumer.setContractLength(consumer.getContractLength() - 1);
              if (consumer.getisBankrupt()) {
                distributor.removeClient(consumer);
                removedConsumers++;
              }
              break;
            }
          }
        }
      }
      /* distributor makes monthly payments*/
      for (var distributor : distributors) {
        if (distributor.getisBankrupt()) {
          continue;
        }
        /* if the distributor goes bankrupt, free clients from contract */
        distributor.makeMonthlyPayments(chosenDistributor, removedConsumers);
        if (distributor.getisBankrupt()) {
          for (var consumer : consumers) {
            if (distributor.getClients().contains(consumer)) {
              consumer.setCurrentContract(0);
              consumer.setContractLength(0);
            }
          }
        }
      }
      /* apply producers updates*/
      registerUpdates.registerUpdates(
          systemSimulation,
          turnNumber,
          monthlyUpdates,
          consumersFactory,
          consumers,
          distributors,
          producers,
          true);
      producers.sort(Comparator.comparing(Producer::getId));
      distributors.sort(Comparator.comparing(Distributor::getId));
      /* if one or more of the distributor's producers has changed, remove distributor from
      producers clients list and empty distributor's list of producers*/
      for (var distributor : distributors) {
        for (var prod : producers) {
          if (distributor.getEnergyProducers().contains(prod) && prod.isHasChanged()) {
            distributor.cleanUpProducers();
            prod.removeClient(distributor);
          }
        }
        /* if distributor has no producers, reapply strategy and choose */
        if (distributor.getEnergyProducers().isEmpty()) {
          EnergyChoiceStrategy newStrategy =
              strategyFactory.createStrategy(distributor.getProducerStrategy(), producers);
          distributor.chooseProducer(newStrategy);
        }
      }
      /* refresh hasChanged status and update monthly stats with current month */
      for (var producer : producers) {
        producer.setHasChanged(false);
        producer.updateStats(turnNumber);
      }
    }
    /* writes to output*/
    /* create objects lists and populate them, then set instance of Output class to be
    printed at output */
    Writer writer = new Writer(args[1]);
    Output output = new Output();
    List<ConsumerOutput> consumersOut = new ArrayList<>();
    List<DistributorOutput> distributorsOut = new ArrayList<>();
    List<ProducerOutput> producersOut = new ArrayList<>();
    WriteOutput writeOutput = new WriteOutput();
    writeOutput.writeOutput(
        consumers, consumersOut, distributors, distributorsOut, producers, producersOut, output);
    writer.writeData(output);
  }
}
