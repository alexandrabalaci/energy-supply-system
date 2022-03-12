package entities;

import java.util.List;

/**
 * Distributors Factory class that creates an instance of distributor when called
 *
 * <p>
 */
public final class DistributorsFactory {
  private static DistributorsFactory instance = null;

  private DistributorsFactory() { }

  /**
   * creates Singleton instance of DistributorsFactory
   *
   * @return instance - the single instance of Factory
   */
  public static synchronized DistributorsFactory getInstance() {
    if (instance == null) {
      instance = new DistributorsFactory();
    }
    return instance;
  }

  /**
   * creates Distributor instance
   *
   * @param id - distributor's id
   * @param contractLength - distributor's contract length
   * @param initialBudget - distributor's initialBuget
   * @param initialInfrastructureCost - distributor's initialInfrastructureCost
   * @param clients - distributor's list of clients
   */
  public Distributor createDistributor(
      final int id,
      final int contractLength,
      final int initialBudget,
      final int initialInfrastructureCost,
      final int energyNeededKW,
      final String producerStrategy,
      final List<Consumer> clients) {
    return new Distributor(
        id,
        contractLength,
        initialBudget,
        initialInfrastructureCost,
        energyNeededKW,
        producerStrategy,
        clients);
  }
}
