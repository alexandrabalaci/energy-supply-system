package fileio;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * The class contains information about output
 *
 * <p>
 */
@JsonPropertyOrder({"consumers", "distributors", "energyProducers"})
public final class Output {
  private List<ConsumerOutput> consumers;
  private List<DistributorOutput> distributors;
  private List<ProducerOutput> energyProducers;

  public Output() { }

  public List<ConsumerOutput> getConsumers() {
    return consumers;
  }

  public void setConsumers(final List<ConsumerOutput> consumers) {
    this.consumers = consumers;
  }

  public List<DistributorOutput> getDistributors() {
    return distributors;
  }

  public void setDistributors(final List<DistributorOutput> distributors) {
    this.distributors = distributors;
  }

  public List<ProducerOutput> getEnergyProducers() {
    return energyProducers;
  }

  public void setEnergyProducers(List<ProducerOutput> energyProducers) {
    this.energyProducers = energyProducers;
  }

  @Override
  public String toString() {
    return "Output{"
        + "consumers="
        + consumers
        + ", distributors="
        + distributors
        + ", energyProducers="
        + energyProducers
        + '}';
  }
}
