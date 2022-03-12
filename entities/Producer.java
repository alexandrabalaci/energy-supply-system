package entities;

import java.util.*;
/**
 * Information and methods for producer
 *
 * <p>
 */
public final class Producer implements Observer, Producers {
  private int id;
  private String energyType;
  private int maxDistributors;
  private double priceKW;
  private int energyPerDistributor;
  private List<Distributor> distributors = new ArrayList<>();
  private Map<Integer, List<Integer>> stats = new HashMap<>();
  private boolean hasChanged = false;

  public Producer() { }

  public Producer(
      final int id,
      final String energyType,
      final int maxDistributors,
      final double priceKW,
      final int energyPerDistributor) {
    this.id = id;
    this.energyType = energyType;
    this.maxDistributors = maxDistributors;
    this.priceKW = priceKW;
    this.energyPerDistributor = energyPerDistributor;
  }

  @Override
  public void update(Observable o, Object producer) {
    int idUpdate = ((Producer) producer).getId();
    int updatedEnergy = ((Producer) producer).getEnergyPerDistributor();
    if (id == idUpdate) {
      this.setEnergyPerDistributor(updatedEnergy);
      hasChanged = true;
    }
  }

  @Override
  public void addClient(final Distributor distributor) {
    if (!this.distributors.contains(distributor) && distributors.size() <= maxDistributors) {
      this.distributors.add(distributor);
    }
  }

  @Override
  public void removeClient(final Distributor distributor) {
    this.distributors.remove(distributor);
  }

  @Override
  public void updateStats(final int turnNumber) {
    if (turnNumber != 0) {
      List<Integer> ids = new ArrayList<>();
      for (var distributor : distributors) {
        ids.add(distributor.getId());
      }
      Collections.sort(ids);
      stats.put(turnNumber, ids);
    }
  }

  public Map<Integer, List<Integer>> getStats() {
    return stats;
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
  /** Checks if the energy source is renewable */
  public boolean isRenewable() {
    if (this.getEnergyType().equals("WIND")
        || this.getEnergyType().equals("SOLAR")
        || this.getEnergyType().equals("HYDRO")) {
      return true;
    }
    return false;
  }

  public List<Distributor> getDistributors() {
    return distributors;
  }

  public void setDistributors(List<Distributor> distributors) {
    this.distributors = distributors;
  }

  public boolean isHasChanged() {
    return hasChanged;
  }

  public void setHasChanged(boolean hasChanged) {
    this.hasChanged = hasChanged;
  }

  @Override
  public String toString() {
    return "\nProducer{"
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
        + '}';
  }
}
