package entities;

/**
 * Consumers Factory class that creates an instance of consumers when called
 *
 * <p>
 */
public final class ConsumersFactory {
  private ConsumersFactory() { }

  private static ConsumersFactory instance = null;
  /**
   * creates Singleton instance of ConsumersFactory
   *
   * @return instance - the single instance of Factory
   */
  public static synchronized ConsumersFactory getInstance() {
    if (instance == null) {
      instance = new ConsumersFactory();
    }
    return instance;
  }

  /**
   * creates Consumer instance
   *
   * @param id - consumer's id
   * @param initialBudget - consumer's initialBuget
   * @param monthlyIncome - consumer's monthlyIncome
   */
  public Consumer createConsumer(final int id, final int initialBudget, final int monthlyIncome) {
    return new Consumer(id, initialBudget, monthlyIncome);
  }
}
