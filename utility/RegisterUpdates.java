package utility;

import entities.*;
import fileio.MonthlyUpdates;

import java.util.List;

public final class RegisterUpdates {
  /**
   * method that parses and stores the updates
   *
   * @param systemSimulation - the simulation the producers are observing
   * @param consumers - list of consumers
   * @param monthlyUpdates - list for monthly updates parsed from input
   * @param distributors - list of distributors
   * @param consumersFactory - factory for creating new consumers
   * @param producers - list of producers
   * @param includeProducers - check to include the producers or not
   */
  public void registerUpdates(
      SystemSimulation systemSimulation,
      int turnNumber,
      List<MonthlyUpdates> monthlyUpdates,
      ConsumersFactory consumersFactory,
      List<Consumer> consumers,
      List<Distributor> distributors,
      List<Producer> producers,
      boolean includeProducers) {

    if (turnNumber != 0) {
      int count = 0;
      for (var update : monthlyUpdates) {
        count++;
        if (count == turnNumber) {
          if (!includeProducers) {
            if (!update.getNewConsumers().isEmpty()) {
              for (var newConsumer : update.getNewConsumers()) {
                Consumer consumer =
                    consumersFactory.createConsumer(
                        newConsumer.getId(),
                        newConsumer.getInitialBudget(),
                        newConsumer.getMonthlyIncome());
                consumers.add(consumer);
              }
            }
            if (!update.getDistributorChanges().isEmpty()) {
              for (var distributorChanges : update.getDistributorChanges()) {
                for (var distributor : distributors) {
                  if (distributor.getId() == distributorChanges.getId()) {
                    distributor.setInitialInfrastructureCost(
                        distributorChanges.getInfrastructureCost());
                  }
                }
              }
            }
          } else {
            if (!update.getProducerChanges().isEmpty()) {
              for (var producer : producers) {
                for (var producerChanges : update.getProducerChanges()) {
                  if (producerChanges.getId() == producer.getId()) {
                    systemSimulation.setEnergy(producerChanges);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
