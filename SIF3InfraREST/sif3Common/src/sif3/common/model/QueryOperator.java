package sif3.common.model;

import java.util.HashMap;

public enum QueryOperator {
  EQUAL("=");
  
  private String sign = null;
  
  private QueryOperator(String sign) {
    this.sign = sign;
  }
  
  public String getSign() {
    return sign;
  }
  
  private static final HashMap<String, QueryOperator> SIGN_MAP = new HashMap<String, QueryOperator>();
  
  static {
    for (QueryOperator qo : QueryOperator.values()) {
      SIGN_MAP.put(qo.getSign(), qo);
    }
  }
  
  public static QueryOperator fromSign(String sign) throws IllegalArgumentException {
    QueryOperator result = SIGN_MAP.get(sign);
    if (result == null) throw new IllegalArgumentException("No QueryOperator with sign : " + sign);
    return result;
  }
}
