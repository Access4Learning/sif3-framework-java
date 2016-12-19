/*
 * StringUtils.java
 *
 * Copyright 2003-2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package au.com.systemic.framework.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Joerg Huber
 */
public class StringUtils
{
  private static final String htmlExpr = "</?[a-zA-Z0-9]*>"; 


  public static boolean isEmpty(String value)
  {
    return ((value == null) || (value.trim().length()==0));
  }

  public static boolean notEmpty(String value)
  {
    return ((value != null) && (value.trim().length()>0));
  }

  /**
   * Joins a list using the specified delimiter
   * 
   * @param list
   *          List
   * @param delimiter
   *          String
   * @return String
   */
  public static String join(List<String> list, String delimiter)
  {
    StringBuffer buffer = new StringBuffer();
    for (Iterator<String> token = list.iterator(); token.hasNext();)
    {
      buffer.append(token.next());
      if (token.hasNext())
      {
        buffer.append(delimiter);
      }
    }
    return buffer.toString();
  }
  
  /**
   * Splits the specified string and saves it in a LIst rather than a String[] as the String.split() does.
   * @param string String
   * @param delimiter String
   * @return List
   */
  public static List<String> split(String string, String delimiter)
  {
     if (string == null || string.equals(""))
     {
        return new ArrayList<String>();
     }
     return new ArrayList<String>(Arrays.asList(string.split(delimiter)));
  }
  

  /**
   * Splits the specified string on whitespaces
   * @param string String
   * @return List
   */
  public static List<String> split(String string)
  {
     return new ArrayList<String>(Arrays.asList(string.split("\\s")));
  }

  /**
   * This method splits a string into its parts based on the delimiter. If strict is set
   * to true then each delimiter indicates another token until the end of the string is
   * reached. If strict is set to false than successive delimiters are treated as ONE and
   * trailing delimiters are ignored.
   * If the delimiter is null or empty then the original string is returned untouched and
   * the length of the String[] is 1. If the origin string is null then null is returned.
   * If the origin string is empty then the empty string is returned in the String[].
   *
   * Example: Original String:  "this::is:a:test::"
   *          Delimiter: ":"
   * Case A: strict=true  => Result = {"this","","is","a","test","",""}
   * Case B: strict=false => Result = {"this","is","a","test"}
   *
   * @param origin The string to be split.
   * @param delimiter The delimiter
   * @param strict  See description
   *
   * @return String[] of Strings. Depending in the parameter 'strict' empty strings can
   *         be part of the array.
   */
  public static String[] splitter(String origin, String delimiter,
      boolean strict)
  {
    if (origin == null)
    {
      return null;
    }

    if (origin.length() == 0)
    {
      String[] retArr = { "" };
      return retArr;
    }

    if ((delimiter == null) || (delimiter.length() == 0))
    {
      String[] retArr = { origin };
      return retArr;
    }

    if (strict)
    {
      return origin.split(delimiter, -1);
    }
    else
    {
      String[] retArr = origin.split(delimiter); // this may still have empty strings
      ArrayList<String> tmp = new ArrayList<String>();
      for (int i = 0; i < retArr.length; i++)
      {
        if (retArr[i].length() > 0) // only collect strings that are not empty
        {
          tmp.add(retArr[i]);
        }
      }
      // Now rebuild new string array
      retArr = new String[tmp.size()];

      return (String[]) (tmp.toArray(retArr));
    }
  }
  
  /**
   * Split the given string based on the node name. This is an XML split, meaning the nodeName must be a value of
   * an XML node without the '<' and '>' characters.
   * Example:
   *  ValueToSplit:
   *         <abc type="1">
   *           <yyu>14M<yyu>
   *         </abc>
   *         <abc type="7">
   *           <yyu>UY7<yyu>
   *         </abc>
   *  NodeName: abc
   *  Result:
   *  List[0] = <abc type="1">
   *              <yyu>14M<yyu>
   *            </abc>
   *  List[1] = <abc type="7">
   *              <yyu>UY7<yyu>
   *            </abc>
   * 
   * @param nodeName
   * @return See desc.
   */  
	public static List<String> splitXML(String valueToSplit, String nodeName)
	{
		String expr = "</"+nodeName+">";
		String xmlValueArray[] = valueToSplit.split(expr);
		List<String> xmlValueList = new ArrayList<String>();
		
		for (String xmlValue : xmlValueArray)
		{
			xmlValueList.add(xmlValue+expr);
		}

		return xmlValueList;
	}
	
  public static String rpad(String s, int len, char padChar)
  {
    if (s == null)
    {
      s = "";
    }
    // create a buffer that's already the right length (instead of dynamically extending the buffer.
    int nspaces = len - s.length();
    if (nspaces <= 0)
    {
      return s; // String already at max length, no room to pad
    }
    char ca[] = new char[nspaces];

    // operations on primitives are faster than StringBuffer.append(...). plus you lose the method call,
    // and StringBuffer.append(...) calls more methods internally.
    for (int ctr = 0; ctr < nspaces; ctr++)
    {
      ca[ctr] = padChar;

    }
    String ret = s + (new String(ca));

    return ret;
  }

  public static String lpad(String s, int len, char padChar)
  {
    if (s == null)
    {
      s = "";
    }
    // create a buffer that's already the right length (instead of dynamically extending the buffer.
    int nspaces = len - s.length();
    if (nspaces <= 0)
    {
      return s; // String already at max length, no room to pad
    }
    char ca[] = new char[nspaces];

    // operations on primitives are faster than StringBuffer.append(...). plus you lose the method call,
    // and StringBuffer.append(...) calls more methods internally.
    for (int ctr = 0; ctr < nspaces; ctr++)
    {
      ca[ctr] = padChar;

    }
    String ret = (new String(ca)) + s;

    return ret;
  }

  /**
   * Replace all occurences of 'key' in source with value
   * 
   * @return String
   */
  public static String replaceAll(String source, String key, String value)
  {
    StringBuffer result = new StringBuffer(source);
    for (int idx = result.indexOf(key); idx >= 0;)
    {
      result.replace(idx, idx + key.length(), value);
      idx = result.indexOf(key, idx - key.length() + value.length());
    }
    return result.toString();
  }

  /**
   * Replace all occurences of 'key' in source with value. Will not be case
   * sensitive if caseSensitive = false.
   * 
   * @return String
   */
  public static String replaceAllCaseSensitive(String source, String key,
      String value, boolean caseSensitive)
  {
    StringBuffer result = new StringBuffer(source);
    if (caseSensitive)
    {
      return replaceAll(source, key, value);
    }
    else
    {
      for (int idx = source.toLowerCase().indexOf(key.toLowerCase()); idx >= 0;)
      {
        result.replace(idx, idx + key.length(), value);
        idx = result.indexOf(key, idx - key.length() + value.length());
      }
    }
    return result.toString();
  }

  /**
   * Make the given string printable by removing all of the control characters that may be in it.
   * 
   * @param str The input string.
   * 
   * @return The same string but with any character, c, removed where Character.isISOControl(c) is true.
   */
  public static String makePrintable(String str)
  {
    StringBuffer result = new StringBuffer(str);
     for (int i = result.length() - 1; i >= 0; i--)
    {
      if (Character.isISOControl(result.charAt(i)))
      {
        result.deleteCharAt(i);
      }
    }

    return result.toString();
  }

  /**
   * This method escapes all characters outside the ASCII(32) - ASCII(127) range with
   * the '&#x<hexNo>;' sequence so that it can be used in XML documents. In addition the
   * '&' (ASCII(38)) character is replaced with '&amp'.
   *  
   * @param text The text to escape
   * 
   * @return The escaped string.
   */
  public static String escapeToUTF8(String text)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < text.length(); i++)
    {
      char c = text.charAt(i);
      if ((c < 32) || (c > 127))
      {
        sb.append("&#x" + Integer.toHexString(c) + ";");
      }
      else if (c == 38) // '&'
      {
        sb.append("&amp;");
      }
      else
      {
        sb.append(c);
      }
    }

    return sb.toString();
  }
  
  /**
   * Encoding a text.  All chars except (a..z,A..Z,0..9) becomes changed to &#nnn; example: # -> &#35;
   *
   *@param  text  To be encoded
   *@param  nbsp  blank to be encoded
   *@return        The encoded text
   */
  public static String encodeText(String text, boolean nbsp)
  {
    int i = 0;
    int j = 0;
    int l = text.length();
    StringBuffer sb = new StringBuffer();
    while (j < l)
    {
      char c = text.charAt(j);
      if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == ' ' && !nbsp))
      {
        j++;
        if (j == l)
        {
          sb.append(text.substring(i, j));
        }
        continue;
      }

      if (i < j)
      {
        sb.append(text.substring(i, j));
      }

      if (c == ' ' && nbsp)
      {
        sb.append("&nbsp;");
      }
      else if (c == '-')
      {
        sb.append("-");
      }
      else if (c == '\n')
      {
        sb.append("<br\\>");
      }
      else
      {
        sb.append("&#").append((int) c).append(";");
      }
      j++;
      i = j;
    }

    return sb.toString();
  }
  
  
  /**
   * Encoding a text.  All chars except (a..z,A..Z,0..9) becomes changed to &#nnn; example: # -> &#35;
   *
   *@param  text  To be encoded
   *@param  nbsp  blank to be encoded
   *@param  pageBreak  pagebreak to be encoded
   *@return        The encoded text
   */
  public static String encodeText(String text, boolean nbsp, boolean pageBreak)
  {
    int i = 0;
    int j = 0;
    int l = text.length();
    StringBuffer sb = new StringBuffer();
    while (j < l)
    {
      char c = text.charAt(j);
      if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
          || (c >= 'A' && c <= 'Z'))
      {
        j++;
        if (j == l)
        {
          sb.append(text.substring(i, j));
        }
        continue;
      }
      if (i < j)
      {
        sb.append(text.substring(i, j));
      }
      if (c == ' ' && nbsp)
      {
        sb.append("&nbsp;");
      }
      else if (c == ' ' && !nbsp)
      {
        sb.append(" ");
      }
      else if (c == '-')
      {
        sb.append("-");
      }
      else if (c == '\n' && pageBreak)
      {
        sb.append("<br\\>");
      }
      else if (c == '\n' && !pageBreak)
      {
        // no pagebreak, just blank
      }
      else
      {
        sb.append("&#").append((int) c).append(";");
      }
      j++;
      i = j;
    }

    return sb.toString();
  }

  /**
   * This method removes all HTML, XML or any other tags that have the <xxx> and
   * </xxx> structure from the given string. It is assumed that the input
   * parameter is not null.
   * 
   * @param original
   *          The string from which the tags shall be removed. It is assumed
   *          that this value is not null.
   * 
   * @return Original string without tags.
   */
  public static String removeHTMLTags(String original)
  {
    return original.replaceAll(htmlExpr, "");
  }
  
  /**
   * Converts a string representing a boolean to a boolean value. If the string
   * is empty or null then false is returned otherwise the following values will
   * return true: - Any combination of upper and lower case of the word 'true'
   * (ie. True, true, TRUE, TrUe etc.) - The letter 'T' or 't' - Any combination
   * of upper and lower case of the word 'yes' (ie. Yes, yes, yEs etc.) - The
   * letter 'Y' or 'y' - The digit 1 Everything else will return false.
   * 
   * @param booleanString The value as a String that represents the boolean.
   * @return See description.
   */
  public static boolean toBoolean(String booleanString)
  {
    if (isEmpty(booleanString))
    {
      return false;
    }
    booleanString = booleanString.toUpperCase();
    if (booleanString.equals("TRUE"))
    {
      return true;
    }
    if (booleanString.equals("T"))
    {
      return true;
    }
    if (booleanString.equals("YES"))
    {
      return true;
    }
    if (booleanString.equals("Y"))
    {
      return true;
    }
    if (booleanString.equals("1"))
    {
      return true;
    }
    
    return false;
  }
  
  public static String exceptionToString(Throwable ex)
  {
     StringWriter sw = new StringWriter();
     ex.printStackTrace(new PrintWriter(sw));
     
     return sw.toString();
  }  
}
