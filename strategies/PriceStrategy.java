package strategies;

import entities.Producer;

import java.util.Comparator;
import java.util.List;

public final class PriceStrategy implements EnergyChoiceStrategy {
  private final List<Producer> producersList;

  public PriceStrategy(List<Producer> producersList) {
    this.producersList = producersList;
  }

  @Override
  public List<Producer> getProducers() {
    producersList.sort(
        Comparator.comparing(Producer::getPriceKW)
            .thenComparing(Producer::getEnergyPerDistributor, Comparator.reverseOrder()));
    return producersList;
  }

  @Override
  public String toString() {
    return "PriceStrategy{" + "producersList=" + producersList + '}';
  }
}
