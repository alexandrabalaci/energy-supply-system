package fileio;

import entities.Consumer;
import entities.Distributor;
import entities.Producer;

import java.util.List;

/**
 * Information about the monthly updates, retrieved from parsing the input test files
 *
 * <p>
 */
public final class MonthlyUpdates {
  private List<Consumer> newConsumers;
  private List<Distributor> distributorChanges;
  private List<Producer> producerChanges;

  public MonthlyUpdates() { }

  public List<Consumer> getNewConsumers() {
    return newConsumers;
  }

  public void setNewConsumers(List<Consumer> newConsumers) {
    this.newConsumers = newConsumers;
  }

  public List<Producer> getProducerChanges() {
    return producerChanges;
  }

  public void setProducerChanges(List<Producer> producerChanges) {
    this.producerChanges = producerChanges;
  }

  public List<Distributor> getDistributorChanges() {
    return distributorChanges;
  }

  public void setDistributorChanges(List<Distributor> distributorChanges) {
    this.distributorChanges = distributorChanges;
  }

  @Override
  public String toString() {
    return "\nMonthlyUpdates{"
        + "\nnewConsumers="
        + newConsumers
        + ", \ndistributorChanges="
        + distributorChanges
        + ", \nproducerChanges="
        + producerChanges
        + '}';
  }
}
