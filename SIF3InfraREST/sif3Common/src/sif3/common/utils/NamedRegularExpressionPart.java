package sif3.common.utils;

public class NamedRegularExpressionPart {
  private String expression = null;
  private String[] names = null;

  public NamedRegularExpressionPart(String expression, String... names) {
    this.expression = expression;
    this.names = names;
  }

  public String getExpression() {
    return expression;
  }

  public String[] getNames() {
    return names;
  }
}
