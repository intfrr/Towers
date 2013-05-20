package lineup.model.implementations.trackers;

public class HighPowerThreatAnalyser extends NearestToBasePrioritiser {

  public HighPowerThreatAnalyser() {
    super(130, 8, 12);
  }

  public int getCost() {
    return 250;
  }

  public String getName() {
    return "Hi Power Threat Analyser";
  }

  public String getDescription() {
    return "Analyses targets and prioritises those nearest the base.";
  }

}
