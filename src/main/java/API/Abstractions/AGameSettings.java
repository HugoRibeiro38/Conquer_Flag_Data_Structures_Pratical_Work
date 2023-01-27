package API.Abstractions;

public abstract class AGameSettings {
  private final int energy;

  public AGameSettings(int energy) {
    this.energy = energy;
  }

  public int getEnergy() {
    return energy;
  }

  public int getCooldownTimer() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public String toString() {
    return
        "energy=" + energy;
  }
}
