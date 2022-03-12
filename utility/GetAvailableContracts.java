package utility;

import entities.Distributor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class that helps in parsing and indexing the received monthly updates
 *
 * <p>
 */
public final class GetAvailableContracts {

  /**
   * indexes and parses the updates
   *
   * @param distributors - a list of distributors
   * @return availableContracts - a map of all available contacts
   */
  public Map<Integer, Integer> getAvailableContracts(final List<Distributor> distributors) {
    Map<Integer, Integer> availableContracts = new HashMap<>();
    for (var distributor : distributors) {
      if (distributor.getisBankrupt()) {
        continue;
      }
      if (!availableContracts.containsKey(distributor.getId())) {
        availableContracts.put(
            distributor.getId(),
            distributor.getContractPrice(
                distributor.getInitialInfrastructureCost(),
                distributor.getProductionCost()));
      } else {
        availableContracts.replace(
            distributor.getId(),
            distributor.getContractPrice(
                distributor.getInitialInfrastructureCost(),
                distributor.getProductionCost()));
      }
    }
    return availableContracts;
  }
}
