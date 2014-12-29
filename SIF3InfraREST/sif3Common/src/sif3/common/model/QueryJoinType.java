package sif3.common.model;

public enum QueryJoinType {
  AND("and"),
  OR("or");
  
  private String name;
  
  private QueryJoinType(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
}
