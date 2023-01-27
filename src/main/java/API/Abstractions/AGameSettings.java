package API.Abstractions;

public abstract class AGameSettings {
  private final int energy;

  public AGameSettings(int energy) {
    this.energy = energy;
  }

  public int getEnergy() {
    return energy;
  }

  @Override
  public String toString() {
    return
        "energy=" + energy;
  }
}
