package fileio;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"month", "distributorsIds"})
public final class MonthlyStats {
  private int month;
  private List<Integer> distributorsIds;

  public MonthlyStats(int month, List<Integer> distributorsIds) {
    this.month = month;
    this.distributorsIds = distributorsIds;
  }

  public int getMonth() {
    return month;
  }

  public List<Integer> getDistributorsIds() {
    return distributorsIds;
  }

  @Override
  public String toString() {
    return "\nMonthlyStats{" + "month=" + month + ", distributorsIds=" + distributorsIds + '}';
  }
}
