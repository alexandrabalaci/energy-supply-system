package fileio;

import java.util.List;

/**
 * The class contains information about input
 *
 * <p>
 */
public final class Input {
  /** number of rounds to play */
  private int numberOfTurns;
  /** List of initial data consumers and distributors */
  private InitialData initialData;
  /** List of cost changes for each round */
  private List<MonthlyUpdates> monthlyUpdates;

  public Input() { }

  public int getNumberOfTurns() {
    return numberOfTurns;
  }

  public InitialData getInitialData() {
    return initialData;
  }

  public List<MonthlyUpdates> getMonthlyUpdates() {
    return monthlyUpdates;
  }
}
