package lineup.model.implementations.trackers;

public class ThreatAnalyser extends NearestToBasePrioritiser {

  public ThreatAnalyser() {
    super(90, 6, 8);
  }

  public int getCost() {
    return 90;
  }

  public String getName() {
    return "Threat Analyser";
  }

  public String getDescription() {
    return "Analyses targets and prioritises those nearest the base.";
  }

}
