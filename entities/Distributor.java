package entities;

import strategies.EnergyChoiceStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Information and methods for distributor
 *
 * <p>
 */
public final class Distributor implements Distributors {
  private static final double CONSTANT = 10;
  private static final double PROFIT_CONSTANT = 0.2;
  private int infrastructureCost;
  /** Distributor's id */
  private int id;
  /** Contract length */
  private int contractLength;
  /** Distributor's initialBudget */
  private int initialBudget;
  /** Cost of initial infrastructure */
  private int initialInfrastructureCost;
  /** Energy needed */
  private int energyNeededKW;
  /** strategy for producers */
  private String producerStrategy;
  /** Bankruptcy status */
  private boolean isBankrupt = false;
  /** List of clients */
  private List<Consumer> clients = new ArrayList<>();
  /** cost of production */
  private int productionCost = 0;
  /** List of producers */
  private List<Producer> energyProducers = new ArrayList<>();
  /** energy strategy*/
  private EnergyChoiceStrategy strategy;
  /** contract price */
  private int contractPrice;

  public Distributor() { }

  public Distributor(
      int id,
      int contractLength,
      int initialBudget,
      int initialInfrastructureCost,
      int energyNeededKW,
      String producerStrategy,
      List<Consumer> clients) {
    this.id = id;
    this.contractLength = contractLength;
    this.initialBudget = initialBudget;
    this.initialInfrastructureCost = initialInfrastructureCost;
    this.energyNeededKW = energyNeededKW;
    this.producerStrategy = producerStrategy;
    this.clients = clients;
  }

  @Override
  public void chooseProducer(final EnergyChoiceStrategy strategy) {
    int energy = 0;
    this.strategy = strategy;
    for (var producer : strategy.getProducers()) {
      if (energy <= this.energyNeededKW
          && producer.getMaxDistributors() > producer.getDistributors().size()) {
        this.energyProducers.add(producer);
        producer.addClient(this);
        energy += producer.getEnergyPerDistributor();
      }
    }
  }

  @Override
  public int getProductionCost() {
    double finalCost = 0;
    for (var producer : this.energyProducers) {
      double cost = producer.getEnergyPerDistributor() * producer.getPriceKW();
      finalCost += cost;
    }
    this.productionCost = (int) Math.round(Math.floor((finalCost) / CONSTANT));
    return productionCost;
  }

  @Override
  public int getContractPrice(final int infrastructureCost, final int productionCost) {
    int profit = this.getProfit();
    if (this.clients.isEmpty()) {
      setContractPrice(infrastructureCost + productionCost + profit);
      return contractPrice;
    } else {
      setContractPrice(
          (int)
              Math.round(
                  Math.floor(infrastructureCost / clients.size()) + productionCost + profit));
      return contractPrice;
    }
  }

  @Override
  public void cleanUpProducers() {
    energyProducers.clear();
  }

  @Override
  public void makeMonthlyPayments(final Distributor chosenDistributor, final int removedConsumers) {
    if (chosenDistributor.getId() == id) {
      this.initialBudget -=
          initialInfrastructureCost + productionCost * (this.clients.size() + removedConsumers);
    } else {
      this.initialBudget -= initialInfrastructureCost + productionCost * this.clients.size();
    }
    checkBankruptcyStatus();
  }

  @Override
  public void checkBankruptcyStatus() {
    if (initialBudget < 0) {
      isBankrupt = true;
    }
  }

  @Override
  public void addClient(final Consumer consumer) {
    this.clients.add(consumer);
  }

  @Override
  public void removeClient(final Consumer consumer) {
    this.clients.remove(consumer);
  }

  @Override
  public void cleanUp() {
    clients.removeIf(consumer -> consumer.getContractLength() == 0);
  }

  @Override
  public int getProfit() {
  return (int) Math.round(Math.floor(PROFIT_CONSTANT * productionCost));
  }

  @Override
  public void getMoney(int sum) {
    this.initialBudget += sum;
  }

  public int getInfrastructureCost() {
    return infrastructureCost;
  }

  public void setInfrastructureCost(int infrastructureCost) {
    this.infrastructureCost = infrastructureCost;
  }

  public List<Consumer> getClients() {
    return clients;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getContractLength() {
    return contractLength;
  }

  public void setContractLength(int contractLength) {
    this.contractLength = contractLength;
  }

  public int getInitialBudget() {
    return initialBudget;
  }

  public void setInitialBudget(final int initialBudget) {
    this.initialBudget = initialBudget;
  }

  public int getInitialInfrastructureCost() {
    return initialInfrastructureCost;
  }

  public void setInitialInfrastructureCost(int initialInfrastructureCost) {
    this.initialInfrastructureCost = initialInfrastructureCost;
  }

  public int getEnergyNeededKW() {
    return energyNeededKW;
  }

  public void setEnergyNeededKW(int energyNeededKW) {
    this.energyNeededKW = energyNeededKW;
  }

  public String getProducerStrategy() {
    return producerStrategy;
  }

  public void setProducerStrategy(String producerStrategy) {
    this.producerStrategy = producerStrategy;
  }

  public boolean isBankrupt() {
    return isBankrupt;
  }

  /** return bankruptcy status */
  public boolean getisBankrupt() {
    return isBankrupt;
  }

  public List<Producer> getEnergyProducers() {
    return energyProducers;
  }

  public void setEnergyProducers(List<Producer> energyProducers) {
    this.energyProducers = energyProducers;
  }

  public int getContract() {
    return contractPrice;
  }

  public void setContractPrice(int contractPrice) {
    this.contractPrice = contractPrice;
  }

  @Override
  public String toString() {
    return "\nDistributor{"
        + "id="
        + id
        + ", contractLength="
        + contractLength
        + ", initialBudget="
        + initialBudget
        + ", initialInfrastructureCost="
        + initialInfrastructureCost
        + ", energyNeededKW="
        + energyNeededKW
        + ", producerStrategy="
        + producerStrategy
        + ", isBankrupt="
        + isBankrupt
        + ", clients="
        + clients
        + ", productionCost="
        + productionCost
        + '}';
  }
}
