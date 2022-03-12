package entities;

/**
 * Interface that offers minimal information on producers
 *
 * <p>
 */
public interface Producers {
  /**
   * adds distributor to producers clients list
   *
   * @param distributor - the distributor added as client
   */
  void addClient(Distributor distributor);
  /**
   * removes distributor from producers clients list
   *
   * @param distributor - the distributor added as client
   */
  void removeClient(Distributor distributor);
  /**
   * updates list of monthly stats concerning the clients the producer had each month
   *
   * @param turnNumber - the number of the month
   */
  void updateStats(int turnNumber);
}
