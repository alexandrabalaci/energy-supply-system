package entities;

/**
 * Interface that offers minimal information on consumers
 *
 * <p>
 */
public interface Consumers {
  /** indexes and parses the updates */
  void receiveSalary();

  /**
   * adds consumer to distributor's clients list and sets the contract parameters
   *
   * @param chosenDistributor - the distributor that offers the cheapest contract
   */
  void chooseDistributor(Distributor chosenDistributor);

  /**
   * pays the contract price
   *
   * @param contractPrice - updates the customer's budget after they pay the contract price
   */
  void payContract(int contractPrice, Distributor distributor);
  /** consumer's bankruptcy status */
  boolean checkBankruptcyStatus();
}
