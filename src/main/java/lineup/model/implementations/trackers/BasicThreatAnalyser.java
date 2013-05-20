package lineup.model.implementations.trackers;

public class BasicThreatAnalyser extends NearestToBasePrioritiser {

  public BasicThreatAnalyser() {
    super(50, 4, 4);
  }

  public int getCost() {
    return 50;
  }

  public String getName() {
    return "Basic Threat Analyser";
  }

  public String getDescription() {
    return "Analyses targets and prioritises those nearest the base.";
  }

}
