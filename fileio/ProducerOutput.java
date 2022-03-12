package fileio;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({
  "id",
  "maxDistributors",
  "priceKW",
  "energyType",
  "energyPerDistributor",
  "monthlyStats"
})
public final class ProducerOutput {
  private int id;
  private String energyType;
  private int maxDistributors;
  private double priceKW;
  private int energyPerDistributor;
  private List<MonthlyStats> monthlyStats;

  public ProducerOutput(
      int id,
      String energyType,
      int maxDistributors,
      double priceKW,
      int energyPerDistributor,
      List<MonthlyStats> monthlyStats) {
    this.id = id;
    this.energyType = energyType;
    this.maxDistributors = maxDistributors;
    this.priceKW = priceKW;
    this.energyPerDistributor = energyPerDistributor;
    this.monthlyStats = monthlyStats;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEnergyType() {
    return energyType;
  }

  public void setEnergyType(String energyType) {
    this.energyType = energyType;
  }

  public int getMaxDistributors() {
    return maxDistributors;
  }

  public void setMaxDistributors(int maxDistributors) {
    this.maxDistributors = maxDistributors;
  }

  public double getPriceKW() {
    return priceKW;
  }

  public void setPriceKW(double priceKW) {
    this.priceKW = priceKW;
  }

  public int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public void setEnergyPerDistributor(int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }

  public List<MonthlyStats> getMonthlyStats() {
    return monthlyStats;
  }

  public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }

  @Override
  public String toString() {
    return "\nProducerOutput{"
        + "id="
        + id
        + ", energyType="
        + energyType
        + ", maxDistributors="
        + maxDistributors
        + ", priceKW="
        + priceKW
        + ", energyPerDistributor="
        + energyPerDistributor
        + ", \nmonthlyStats="
        + monthlyStats
        + '}';
  }
}
