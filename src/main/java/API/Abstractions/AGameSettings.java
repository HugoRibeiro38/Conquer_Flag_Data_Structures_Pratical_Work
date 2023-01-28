package API.Abstractions;

public abstract class AGameSettings {
  private int energy;

  public AGameSettings(int energy) {
    this.energy = energy;
  }

  public int getEnergy() {
    return energy;
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  @Override
  public String toString() {
    return
        "energy=" + energy;
  }
}
