package strategies;

import entities.Producer;

import java.util.Comparator;
import java.util.List;

public final class GreenStrategy implements EnergyChoiceStrategy {
  private final List<Producer> producersList;

  public GreenStrategy(List<Producer> producersList) {
    this.producersList = producersList;
  }

  @Override
  public List<Producer> getProducers() {
    producersList.sort(
        Comparator.comparing(Producer::isRenewable, Comparator.reverseOrder())
            .thenComparing(Producer::getPriceKW)
            .thenComparing(Producer::getEnergyPerDistributor, Comparator.reverseOrder()));
    return producersList;
  }

  @Override
  public String toString() {
    return "GreenStrategy{" + "producersList=" + producersList + '}';
  }
}
