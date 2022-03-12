package utility;

import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import fileio.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class that helps with output writing
 *
 * <p>
 */
public final class WriteOutput {
  /**
   * method that writes to output
   *
   * @param consumers - list of consumers
   * @param consumersOut - list for output for consumers
   * @param distributors - list of distributors
   * @param distributorsOut - list for output for distributors
   * @param producers - list of producers
   * @param producersOut - list for output for producers
   * @param output - output class to populate for object mapper
   */
  public void writeOutput(
      final List<Consumer> consumers,
      final List<ConsumerOutput> consumersOut,
      final List<Distributor> distributors,
      final List<DistributorOutput> distributorsOut,
      final List<Producer> producers,
      final List<ProducerOutput> producersOut,
      final Output output) {
    /* populate consumer output list*/
    for (var consumer : consumers) {
      consumersOut.add(
          new ConsumerOutput(
              consumer.getId(), consumer.getisBankrupt(), consumer.getInitialBudget()));
      output.setConsumers(consumersOut);
    }
    /* populate distributor output list*/
    for (var distributor : distributors) {
      var clients = distributor.getClients();
      clients =
          clients.stream()
              .sorted(
                  Comparator.comparingInt(Consumer::getContractLength)
                      .thenComparing(Consumer::getId))
              .collect(Collectors.toList());
      List<ContractsDetails> contracts = new ArrayList<>();
      for (var client : clients) {
        contracts.add(
            new ContractsDetails(
                client.getId(), client.getCurrentContract(), client.getContractLength()));
      }
      distributorsOut.add(
          new DistributorOutput(
              distributor.getId(),
              distributor.getEnergyNeededKW(),
              distributor.getContract(),
              distributor.getInitialBudget(),
              distributor.getProducerStrategy(),
              distributor.getisBankrupt(),
              contracts));
      output.setDistributors(distributorsOut);
    }
    producers.sort(Comparator.comparing(Producer::getId));
    for (var producer : producers) {
      List<MonthlyStats> monthlyStats = new ArrayList<>();
      Map<Integer, List<Integer>> stats = producer.getStats();
      for (Map.Entry<Integer, List<Integer>> entry : stats.entrySet()) {
        monthlyStats.add(new MonthlyStats(entry.getKey(), entry.getValue()));
      }
      producersOut.add(
          new ProducerOutput(
              producer.getId(),
              producer.getEnergyType(),
              producer.getMaxDistributors(),
              producer.getPriceKW(),
              producer.getEnergyPerDistributor(),
              monthlyStats));
      output.setEnergyProducers(producersOut);
    }
  }
}
