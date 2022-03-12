package strategies;

import entities.Producer;

import java.util.Comparator;
import java.util.List;

public final class QuantityStrategy implements EnergyChoiceStrategy {
  private final List<Producer> producersList;

  public QuantityStrategy(List<Producer> producersList) {
    this.producersList = producersList;
  }

  @Override
  public List<Producer> getProducers() {
    producersList.sort(
        Comparator.comparing(Producer::getEnergyPerDistributor, Comparator.reverseOrder()));
    return producersList;
  }

  @Override
  public String toString() {
    return "QuantityStrategy{" + "producersList=" + producersList + '}';
  }
}
