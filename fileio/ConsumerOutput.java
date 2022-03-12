package fileio;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public final class ConsumerOutput {
  private int id;
  private boolean isBankrupt;
  private int budget;

  public ConsumerOutput(final int id, final boolean isBankrupt, final int budget) {
    this.id = id;
    this.isBankrupt = isBankrupt;
    this.budget = budget;
  }

  public int getId() {
    return id;
  }

  public boolean getIsBankrupt() {
    return isBankrupt;
  }

  public int getBudget() {
    return budget;
  }

  @Override
  public String toString() {
    return "\nConsumerOutput{"
        + "id="
        + id
        + ", isBankrupt="
        + isBankrupt
        + ", budget="
        + budget
        + '}';
  }
}
