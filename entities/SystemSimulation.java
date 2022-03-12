package entities;

import java.util.Observable;
/**
 * Class that monitors the updates concerning the producers
 *
 * <p>
 */
public final class SystemSimulation extends Observable {
  private Producer producer = new Producer();

  public SystemSimulation() { }

  public SystemSimulation(Producer producer) {
    this.producer = producer;
  }

  /**
   * updates the energy given by producers
   *
   * @param updatedProducer - the producer that gets the update
   */
  public void setEnergy(Producer updatedProducer) {
    producer.setEnergyPerDistributor(updatedProducer.getEnergyPerDistributor());
    setChanged();
    notifyObservers(updatedProducer);
  }

  @Override
  public String toString() {
    return "SystemSimulation{" + "producer=" + producer + '}';
  }
}
