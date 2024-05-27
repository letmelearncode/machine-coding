package transaction.configuration;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import transaction.models.rule.RoutingRule;

public class ConfigurationManagementService {
  private static final String CONFIG_FILE_PATH = "routing_rules.json"; // Path to the configuration file
  private List<RoutingRule> routingRules;

  public ConfigurationManagementService() {
    loadConfiguration(); // Load initial configuration
    startConfigFileWatcher(); // Start watching for configuration file changes
  }

  private void loadConfiguration() {
    try {
      // Read routing rules from the configuration file
      String configJson = new String(Files.readAllBytes(Paths.get(CONFIG_FILE_PATH)));
      routingRules = parseRoutingRules(configJson);
      System.out.println("Loaded initial configuration: " + routingRules);
    } catch (IOException e) {
      System.err.println("Error loading configuration: " + e.getMessage());
    }
  }

  private void startConfigFileWatcher() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(this::checkForConfigChanges, 0, 5, TimeUnit.SECONDS); // Check for changes every 5 seconds
  }

  private void checkForConfigChanges() {
    try {
      String currentConfigJson = new String(Files.readAllBytes(Paths.get(CONFIG_FILE_PATH)));
      if (!currentConfigJson.equals(routingRules.toString())) {
        System.out.println("Detected configuration change. Reloading...");
        routingRules = parseRoutingRules(currentConfigJson);
        System.out.println("Updated configuration: " + routingRules);
      }
    } catch (IOException e) {
      System.err.println("Error checking for configuration changes: " + e.getMessage());
    }
  }

  private List<RoutingRule> parseRoutingRules(String configJson) {
    // Parse routing rules from JSON
    // Implement your parsing logic here
    return new ArrayList<>(); // Dummy return for demonstration
  }

  public List<RoutingRule> getRoutingRules() {
    return routingRules;
  }

  // Example method to demonstrate usage
  public static void main(String[] args) {
    ConfigurationManagementService configService = new ConfigurationManagementService();
    // Example usage: Access routing rules
    List<RoutingRule> routingRules = configService.getRoutingRules();
    // Use routing rules for routing transactions...
  }
}

