package sif3.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionHelper {
  
  private String expression = "";
  private List<String> names = new ArrayList<String>();
  private Pattern pattern = null;
  
  public RegularExpressionHelper(NamedRegularExpressionPart... parts) {
    if (parts != null) {
      String prefix = "";
      for (NamedRegularExpressionPart part : parts) {
        expression += prefix + part;
        prefix = "|";
        if (part.getNames() != null) {
          for (String name : part.getNames()) {
            this.names.add(name);
          }
        }
      }
      pattern = Pattern.compile(expression);
    }
  }
  
  public HashMap<String, String> matches(String string) {
    HashMap<String, String> result = null;
    if (pattern != null && string != null) {
      Matcher matcher = pattern.matcher(string);
      result = new HashMap<String, String>();
      if (matcher.matches()) {
        for (int i = 1; i <= matcher.groupCount(); i++) {
          String group = matcher.group(i);
          if (group != null) {
            result.put(names.get(i), group);
          }
        }
      }
    }
    return result;
  }
}
