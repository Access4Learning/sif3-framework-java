/*
 * XMLDecoder.java
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

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * @author Joerg Huber
 */
public class XMLUtils
{
  protected static org.w3c.dom.Document         tmpDocument    = null;
  protected static org.w3c.dom.DocumentFragment tmpFragment    = null;
  protected static int                          tmpNameCounter = 0;

  protected final static Logger logger = LoggerFactory.getLogger(XMLUtils.class);

  private static final XMLOutputter prettyXML  = new XMLOutputter(Format.getPrettyFormat());
  private static final XMLOutputter compactXML = new XMLOutputter(Format.getCompactFormat());

  public static String createXMLString(Element item)
  {
    return createXMLString(item, true);
  }

  public static String createXMLString(Element item, boolean pretty)
  {
    XMLOutputter out = pretty ? prettyXML : compactXML;
    return out.outputString(item);
  }

  /**
   * Loads the given XML string into a DOM Document. If the string isn't a valid XML document then null 
   * is returned.
   * 
   * @return A xml Document that can be parsed afterwards. Null is returned if the string contains 
   *         incorrect data (no XML).
   */
  public static Document loadXMLDocument(String xmlString)
  {
    Document document = null;
    StringReader in = new StringReader(xmlString);

    ByteArrayInputStream bais = new ByteArrayInputStream(xmlString.getBytes());

    try
    {
      document = new SAXBuilder().build(bais);
    }
    catch (JDOMException e)
    {
      document = null;
      logger.error("Cannot parse XML string " + xmlString, e);
    }
    catch (Exception e)
    {
      document = null;
      logger.error("Problem reading XML string " + xmlString, e);
    }

    // Ensure that all resources are closed at all time.
    finally
    {
      in.close();
    }
    return document;
  }

  /**
   * Load an XML Document
   * 
   * @param fileName Filename of the XML document to be loaded and returned.
   * 
   * @return Document or null on error (written to error log)
   */
  public static Document load(String fileName)
  {
    Document document = null;
    InputStream inputStream = null;

    try
    {
      inputStream = XMLUtils.class.getClassLoader().getResourceAsStream(
          fileName);
      document = new SAXBuilder().build(inputStream);
    }
    catch (FileNotFoundException ex)
    {
      logger.error("Caught IOException", ex);
    }
    catch (IOException ex)
    {
      logger.error("Caught IOException", ex);
    }
    catch (JDOMException ex)
    {
      logger.error("Caught JDOMException", ex);
    }

    try
    {
      if (inputStream != null)
      {
        inputStream.close();
      }
    }
    catch (IOException ex1)
    {}

    return document;
  }

  /**
   * Load an XML Document
   * 
   * @param resource Name of the resource (XML file name).
   * @param clazz Class to be used for retrieving a class loader to load the CML document.
   * @return Document or null on error (written to error log)
   */
  @SuppressWarnings("rawtypes")
  public static Document load(String resource, Class clazz)
  {
    Document document = null;
    InputSource inputSource = getInputSource(resource, clazz);
    try
    {
      document = new SAXBuilder().build(inputSource);
    }
    catch (IOException ex)
    {
      logger.error("Caught IOException", ex);
    }
    catch (JDOMException ex)
    {
      logger.error("Caught JDOMException", ex);
    }
    catch (Exception ex)
    {
      logger.error("Caught Exception", ex);
    }

    return document;
  }

  /**
   * Save an XML Document
   * 
   * @param document Document to save.
   * @param file Name of file where document is saved to.
   */
  public static void save(Document document, String file)
  {
    try
    {
      OutputStream out = new FileOutputStream(file);
      new XMLOutputter().output(document, out);
    }
    catch (FileNotFoundException ex)
    {
      logger.error("Caught FileNotFoundException", ex);
    }
    catch (IOException ex)
    {
      logger.error("Caught IOException", ex);
    }
  }

  /**
   * Convert a JDOM Document to a DOM Document
   * 
   * @param node The JDOM document for whic a DOM Document shall be created.
   * 
   * @return Document or null on error (written to error log)
   */
  public static org.w3c.dom.Document toDOM(Document node)
  {
    try
    {
      return new DOMOutputter().output(node);
    }
    catch (JDOMException ex)
    {
      logger.error("Caught JDOMException", ex);
      return null;
    }
  }

  /**
   * Convert a DOM Document to a JDOM Document
   * 
   * @param node The DOM document for whic a JDOM Document shall be created.
   * 
   * @return Document or null on error (written to error log)
   */
  public static Document toJDOM(org.w3c.dom.Document node)
  {
    return new DOMBuilder().build(node);
  }

  /**
   * Convert a DOM Element to a JDOM Element
   * 
   * @param node Document
   * @return Element or null on error (written to error log)
   */
  public static Element toJDOM(org.w3c.dom.Element node)
  {
    return new DOMBuilder().build(node);
  }

  /**
   * Build a JAVA Object from an XML definition refer to java.xml.XMLDecoder for somem ore details.
   * 
   * @param xml the Document for which the Java Class shall be built.
   * 
   * @return the object created or null on error (written to error log)
   */
  @SuppressWarnings("resource")
  public static Object readXMLObject(Document xml)
  {
    try
    {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      new XMLOutputter().output(xml, out);
      XMLDecoder fromXml = new XMLDecoder(new ByteArrayInputStream(out.toByteArray()));
      return fromXml.readObject();
    }
    catch (IOException ex)
    {
      logger.error("Caught IOException", ex);
      return null;
    }
  }

  @SuppressWarnings("rawtypes")
  private static InputSource getInputSource(String resource, Class clazz)
  {
    // load the file using class resource loader
    return new InputSource(clazz.getResourceAsStream(resource));
  }

  /**
   * Returns a w3c DOM builder.
   * 
   * @return A default DOM Bulder Object.
   * @throws Exception
   */
  public static synchronized DocumentBuilder getDomBuilder() throws Exception
  {
    DocumentBuilder db = null;
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      db = dbf.newDocumentBuilder();
    }
    catch (Exception e)
    {
      throw new Exception("Problem parsing the file.");
    }
    return db;

  }

  /**
   * Returns a temporary w3c Document.
   * 
   * @return A blank default DOM Documment.
   */
  public static synchronized org.w3c.dom.Document getTemporaryDocument()
  {
    try
    {
      if (tmpDocument == null)
      {
        tmpDocument = getDomBuilder().newDocument();
      }
    }
    catch (Exception e)
    {
      return null;
    }
    return tmpDocument;
  }

  /**
   * Returns a blank temporary DOM Node.
   * 
   * @return See Description.
   */
  public static synchronized org.w3c.dom.Node createTemporaryDomNode()
  {
    String tmpName = "_" + tmpNameCounter++;
    if (tmpFragment == null)
    {
      tmpFragment = getTemporaryDocument().createDocumentFragment();
      tmpDocument.appendChild(tmpFragment);
    }
    org.w3c.dom.Node node = getTemporaryDocument().createElement(tmpName);
    tmpFragment.appendChild(node);
    return node;
  }

  /**
   * Helper function to avoid "JDOM Can't Have a Null Value Exception"
   * 
   * @param el Element
   * @param attr String
   * @param value String
   */
  public static void setAttribute(Element el, String attr, String value)
  {
    if (el != null && attr != null && value != null && !value.equals(""))
    {
      el.setAttribute(attr, value);
    }

  }

  /**
   * get the full name of an element
   * 
   * @param el
   *          Element
   * @return String
   */
  public static String getFullName(Element el)
  {
    StringBuffer sb = new StringBuffer();
    buildFullName(el, sb);
    return sb.toString();
  }

  private static void buildFullName(Element el, StringBuffer sb)
  {
    if (!el.isRootElement())
    {
      buildFullName((Element) el.getParent(), sb);
      sb.append(".");
    }
    sb.append(el.getName());
  }

  /**
   * Find a jdom element by xpath e.g. "/OnixMessage/Product"
   * 
   * @param document
   * @param xpath
   * @return The Element in the dom tree that matches the xpath.
   * @throws JDOMException
   */
  public static Element findUsingXPath(Document document, String xpath)
      throws JDOMException
  {
    XPath xPath = XPath.newInstance(xpath);
    Element selectedElement = (Element) xPath.selectSingleNode(document);
    return selectedElement;
  }

}
