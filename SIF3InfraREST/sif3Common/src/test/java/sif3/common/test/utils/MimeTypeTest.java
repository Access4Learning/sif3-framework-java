package sif3.common.test.utils;

import javax.ws.rs.core.MediaType;

import au.com.systemic.framework.utils.StringUtils;

public class MimeTypeTest
{
  private enum PostFixMimeType {XML, JSON};
  
  public static MediaType getMediaTypeFromURLPostfix(String urlPostfixMimeType)
  {
    if (StringUtils.isEmpty(urlPostfixMimeType))
    {
      return MediaType.APPLICATION_XML_TYPE;
    }
    
    // get the position of the last '.'.
    int pos = urlPostfixMimeType.lastIndexOf(".");
    if ((pos > -1) && (pos + 1 < urlPostfixMimeType.length())) // "." found. Get everything after the "."
    {
      urlPostfixMimeType = urlPostfixMimeType.substring(pos+1);
    }
    
    // Start comparing for valid types
    PostFixMimeType mimeType = PostFixMimeType.XML;
    try
    {
      mimeType = PostFixMimeType.valueOf(urlPostfixMimeType.trim().toUpperCase());
    }
    catch (Exception ex)
    {
      System.out.println("Failed to convert URL Postfix Mime Type '"+urlPostfixMimeType+"' to XML or JSON. Default to Media Type will be APPLICATION_XML");
      mimeType = PostFixMimeType.XML;
    }
    
    switch (mimeType)
    {
      case XML: return MediaType.APPLICATION_XML_TYPE;
      case JSON: return MediaType.APPLICATION_JSON_TYPE;
    }
    
    return MediaType.APPLICATION_XML_TYPE;
  }
  
  public static void main(String[] args)
  {
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix(null));
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix(""));
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix("."));
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix(". "));
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix(" . . "));
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix(".test"));
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix(".Xml"));
    System.out.println("Media Type: "+getMediaTypeFromURLPostfix(".json"));
  }
}
