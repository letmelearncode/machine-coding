package transaction.models.rule;

import com.google.gson.Gson;
import java.util.List;

public class RoutingRules {
  private List<RoutingRule> routingRules;

  public static RoutingRules fromJson(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, RoutingRules.class);
  }

  // Getter and setter for routingRules

  public List<RoutingRule> getRoutingRules() {
    return routingRules;
  }

  public void setRoutingRules(List<RoutingRule> routingRules) {
    this.routingRules = routingRules;
  }

  // Implement other methods as needed
}