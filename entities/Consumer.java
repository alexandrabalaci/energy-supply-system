package entities;

import fileio.ContractsDetails;

/**
 * Information and methods for consumer
 *
 * <p>
 */
public final class Consumer implements Consumers {
  private static final double CONTRACT_PENALTY = 1.2;
  /** Consumer's id */
  private int id;
  /** Consumer's initialBudget */
  private int initialBudget;
  /** Consumer's monthlyIncome */
  private int monthlyIncome;
  /** Consumer's contractLength */
  private int contractLength = 0;
  /** Consumer's currentContract price */
  private int currentContract = 0;
  /** Consumer's oldContract price */
  private int oldContract = 0;
  /** Consumer's old Distributor id */
  private int oldDistributorId;
  /** variable to check if payment has been delayed */
  private int monthsDelayed = 0;
  /** Consumer's bankruptcy status */
  private boolean isBankrupt = false;
  /** Consumer's contract details */
  private ContractsDetails contract;

  public Consumer() { }

  public Consumer(final int id, final int initialBudget, final int monthlyIncome) {
    this.id = id;
    this.initialBudget = initialBudget;
    this.monthlyIncome = monthlyIncome;
  }
  /** indexes and parses the updates */
  @Override
  public void receiveSalary() {
    initialBudget += monthlyIncome;
  }
  /**
   * adds consumer to distributor's clients list and sets the contract parameters pays the
   * distributor
   *
   * @param chosenDistributor - the distributor that offers the cheapest contract
   */
  @Override
  public void chooseDistributor(
      final Distributor chosenDistributor) {
    if (!chosenDistributor.getClients().contains(this)) {
      chosenDistributor.addClient(this);
      this.setContractLength(chosenDistributor.getContractLength() - 1);
    } else {
      this.setContractLength(contractLength - 1);
    }
    this.setCurrentContract(chosenDistributor.getContract());
    // consumer.payContract(contractPrice);
  }

  /**
   * pays the contract price
   *
   * @param contractPrice - updates the customer's budget after they pay the contract price
   * @param distributor - the distributor that gets paid
   */
  @Override
  public void payContract(final int contractPrice, final Distributor distributor) {
    if (monthsDelayed == 0) {
      if (initialBudget - contractPrice < 0) {
        monthsDelayed++;
        setOldContract(contractPrice);
        setOldDistributorId(distributor.getId());
      } else {
        initialBudget -= contractPrice;
        distributor.getMoney(contractPrice);
      }
    } else {
      if (monthsDelayed == 1) {
        /* if consumer has debt at the same distributor*/
        if (distributor.getId() == oldDistributorId) {
          if (initialBudget - Math.round(Math.floor(CONTRACT_PENALTY * oldContract) + contractPrice)
              < 0) {
            setBankrupt(true);
          } else {
            initialBudget -= Math.round(Math.floor(CONTRACT_PENALTY * oldContract) + contractPrice);
            distributor.getMoney(
                (int) (Math.round(Math.floor(CONTRACT_PENALTY * oldContract) + contractPrice)));
          }
        } else {
          /*has debt to another distributor from the current one*/
          if (initialBudget - Math.round(Math.floor(CONTRACT_PENALTY * oldContract)) < 0) {
            setBankrupt(true);
          } else {
            initialBudget -= Math.round(Math.floor(CONTRACT_PENALTY * oldContract));
            distributor.getMoney((int) (Math.round(Math.floor(CONTRACT_PENALTY * oldContract))));
            setOldContract(contractPrice);
            setOldDistributorId(distributor.getId());
          }
        }
      }
    }
  }

  @Override
  public boolean checkBankruptcyStatus() {
    if (initialBudget < 0) {
      isBankrupt = true;
    }
    return isBankrupt;
  }

  public int getCurrentContract() {
    return currentContract;
  }

  public void setCurrentContract(final int currentContract) {
    this.currentContract = currentContract;
  }

  public int getContractLength() {
    return contractLength;
  }

  public void setContractLength(final int contractLength) {
    this.contractLength = contractLength;
  }

  /** return bankruptcy status */
  public boolean getisBankrupt() {
    return isBankrupt;
  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public int getInitialBudget() {
    return initialBudget;
  }

  public void setInitialBudget(final int initialBudget) {
    this.initialBudget = initialBudget;
  }

  public int getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(final int monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public void setBankrupt(final boolean bankrupt) {
    isBankrupt = bankrupt;
  }

  public void setOldDistributorId(int oldDistributorId) {
    this.oldDistributorId = oldDistributorId;
  }

  public void setOldContract(int oldContract) {
    this.oldContract = oldContract;
  }

  /** toString */
  @Override
  public String toString() {
    return "Consumer{"
        + "id="
        + id
        + ", initialBudget="
        + initialBudget
        + ", monthlyIncome="
        + monthlyIncome
        + ", contractLength="
        + contractLength
        + ", currentContract="
        + currentContract
        + ", isBankrupt="
        + isBankrupt
        + '}';
  }
}
