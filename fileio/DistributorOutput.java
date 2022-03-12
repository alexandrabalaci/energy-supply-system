package fileio;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({
  "id",
  "energyNeededKW",
  "contractCost",
  "budget",
  "producerStrategy",
  "isBankrupt",
  "contacts"
})
public final class DistributorOutput {
  private int id;
  private int energyNeededKW;
  private int contractCost;
  private int budget;
  private String producerStrategy;
  private boolean isBankrupt;
  private List<ContractsDetails> contracts;

  public DistributorOutput(
      int id,
      int energyNeededKW,
      int contractCost,
      int budget,
      String producerStrategy,
      boolean isBankrupt,
      List<ContractsDetails> contracts) {
    this.id = id;
    this.energyNeededKW = energyNeededKW;
    this.contractCost = contractCost;
    this.budget = budget;
    this.producerStrategy = producerStrategy;
    this.isBankrupt = isBankrupt;
    this.contracts = contracts;
  }

  public int getId() {
    return id;
  }

  public int getBudget() {
    return budget;
  }

  public List<ContractsDetails> getContracts() {
    return contracts;
  }

  public boolean getIsBankrupt() {
    return isBankrupt;
  }

  public int getEnergyNeededKW() {
    return energyNeededKW;
  }

  public int getContractCost() {
    return contractCost;
  }

  public String getProducerStrategy() {
    return producerStrategy;
  }

  @Override
  public String toString() {
    return "\nDistributorOutput{"
        + "id="
        + id
        + ", energyNeededKW="
        + energyNeededKW
        + ", contractCost="
        + contractCost
        + ", budget="
        + budget
        + ", producerStrategy='"
        + producerStrategy
        + '\''
        + ", isBankrupt="
        + isBankrupt
        + ", \ncontracts="
        + contracts
        + '}';
  }
}
