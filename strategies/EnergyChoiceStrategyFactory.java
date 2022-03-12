package strategies;

import entities.Producer;

import java.util.List;

/**
 * Factory class for creating the strategies
 *
 * <p>
 */
public final class EnergyChoiceStrategyFactory {
  /**
   * method to create the correct strategy
   *
   * @param strategyType - the name of the strategy
   * @param producersList - list of producers
   */
  public EnergyChoiceStrategy createStrategy(String strategyType, List<Producer> producersList) {
    switch (strategyType) {
      case "GREEN":
        return new GreenStrategy(producersList);
      case "PRICE":
        return new PriceStrategy(producersList);
      case "QUANTITY":
        return new QuantityStrategy(producersList);
      default:
        return null;
    }
  }
}
