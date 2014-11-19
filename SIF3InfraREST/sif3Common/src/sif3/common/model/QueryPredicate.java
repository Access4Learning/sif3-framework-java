package sif3.common.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryPredicate implements Serializable {
  
  private static final long serialVersionUID = 5515817219198796201L;

  private static final Pattern predicateParser = Pattern.compile("^([^\\s]*)\\s([^\\s]*)\\s(.*)$");
  
  private String subject;
  private QueryOperator operator;
  private String value;
  
  public QueryPredicate(String subject, QueryOperator operator, String value) {
    this.subject = subject;
    this.operator = operator;
    this.value = value;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public QueryOperator getOperator() {
    return operator;
  }

  public void setOperator(QueryOperator operator) {
    this.operator = operator;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
  
  public static QueryPredicate parse(String value) {
    Matcher matcher = predicateParser.matcher(value);
    if (matcher.matches()) {
      return new QueryPredicate(matcher.group(1), QueryOperator.fromSign(matcher.group(2)), matcher.group(3));
    } else {
      return null;
    }
  }

}
