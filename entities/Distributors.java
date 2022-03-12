package entities;

import strategies.EnergyChoiceStrategy;

/**
 * Interface that offers minimal information on distributors
 *
 * <p>
 */
public interface Distributors {
  /**
   * chooses the energy producers, adds them to the list of providers and adds distributor to
   * producers clients list
   *
   * @param strategy - the distributor's energy strategy
   */
  void chooseProducer(EnergyChoiceStrategy strategy);

  /**
   * computes the price of production
   *
   * @return the cost of production
   */
  int getProductionCost();
  /**
   * computes the price of the contract
   *
   * @param infrastructureCost - the cost of infrastructure
   * @param productionCost - the cost of production
   * @return contractPrice - the price of contract
   */
  int getContractPrice(int infrastructureCost, int productionCost);

  /** removes all associated producers */
  void cleanUpProducers();
  /**
   * updates the distributor's budget after they pay the monthly fees
   *
   * @param chosenDistributor
   * @param removedConsumers
   */
  void makeMonthlyPayments(Distributor chosenDistributor, int removedConsumers);
  /** checks bankruptcy status */
  void checkBankruptcyStatus();

  /**
   * adds client to distributor's list
   *
   * @param consumer - consumer to be added
   */
  void addClient(Consumer consumer);

  /**
   * remove client to distributor's list
   *
   * @param consumer - consumer to be removed
   */
  void removeClient(Consumer consumer);

  /** remove clients that have reached their contract's period end from the distributor's list */
  void cleanUp();

  /**
   * receives payment for contract
   *
   * @param sum
   */
  void getMoney(int sum);

  /**
   * returns the profit made
   *
   * @return - profit
   */
  int getProfit();
}
