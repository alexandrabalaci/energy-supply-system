package strategies;

import entities.Producer;

import java.util.List;

/**
 * Strategy interface
 *
 * <p>
 */
public interface EnergyChoiceStrategy {
  /** method to get a list of all producers needed in creating the strategies */
  List<Producer> getProducers();
}
