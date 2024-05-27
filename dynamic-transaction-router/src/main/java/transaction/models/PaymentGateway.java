package transaction.models;

public class PaymentGateway {
  private final String id;
  private final double fee;
  private String health;

  public PaymentGateway(String id, double fee) {
    this.id = id;
    this.fee = fee;
    this.health = "unknown";
  }

  public String getId() {
    return id;
  }

  public double getFee() {
    return fee;
  }

  public String getHealth() {
    return health;
  }

  public void setHealth(String health) {
    this.health = health;
  }
}
