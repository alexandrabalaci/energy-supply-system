package fileio;

import entities.Consumer;
import entities.Distributor;
import entities.Producer;

import java.util.List;

/**
 * Information about the initial data, retrieved from parsing the input test files
 *
 * <p>
 */
public final class InitialData {
  private List<Consumer> consumers;
  private List<Distributor> distributors;
  private List<Producer> producers;

  public InitialData() { }

  public List<Consumer> getConsumers() {
    return consumers;
  }

  public List<Distributor> getDistributors() {
    return distributors;
  }

  public List<Producer> getProducers() {
    return producers;
  }

  @Override
  public String toString() {
    return "InitialData{"
        + "\nconsumers="
        + consumers
        + ", \ndistributors="
        + distributors
        + ", \nproducers="
        + producers
        + '}';
  }
}
