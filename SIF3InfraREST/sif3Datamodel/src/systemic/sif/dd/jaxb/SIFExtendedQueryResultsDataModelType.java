
package systemic.sif.dd.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *             This element provides a wrapper for data returned in response to a SIF_ExtendedQuery.  Used in SIF_Response
 *             and SIF_ReportObject.
 *           
 * 
 * <p>Java class for SIF_ExtendedQueryResultsDataModelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_ExtendedQueryResultsDataModelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SIF_ColumnHeaders" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SIF_Element" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
 *                           &lt;attribute name="ObjectName" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}ObjectNameType" />
 *                           &lt;attribute name="Alias">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
 *                                 &lt;maxLength value="64"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SIF_Rows" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="R" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="C" type="{http://www.SIFinfo.org/au/datamodel/1.3}SelectedContentType" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF_ExtendedQueryResultsDataModelType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "sifColumnHeaders",
    "sifRows"
})
public class SIFExtendedQueryResultsDataModelType {

    @XmlElement(name = "SIF_ColumnHeaders", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected SIFExtendedQueryResultsDataModelType.SIFColumnHeaders sifColumnHeaders;
    @XmlElement(name = "SIF_Rows", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected SIFExtendedQueryResultsDataModelType.SIFRows sifRows;

    /**
     * Gets the value of the sifColumnHeaders property.
     * 
     * @return
     *     possible object is
     *     {@link SIFExtendedQueryResultsDataModelType.SIFColumnHeaders }
     *     
     */
    public SIFExtendedQueryResultsDataModelType.SIFColumnHeaders getSIFColumnHeaders() {
        return sifColumnHeaders;
    }

    /**
     * Sets the value of the sifColumnHeaders property.
     * 
     * @param value
     *     allowed object is
     *     {@link SIFExtendedQueryResultsDataModelType.SIFColumnHeaders }
     *     
     */
    public void setSIFColumnHeaders(SIFExtendedQueryResultsDataModelType.SIFColumnHeaders value) {
        this.sifColumnHeaders = value;
    }

    /**
     * Gets the value of the sifRows property.
     * 
     * @return
     *     possible object is
     *     {@link SIFExtendedQueryResultsDataModelType.SIFRows }
     *     
     */
    public SIFExtendedQueryResultsDataModelType.SIFRows getSIFRows() {
        return sifRows;
    }

    /**
     * Sets the value of the sifRows property.
     * 
     * @param value
     *     allowed object is
     *     {@link SIFExtendedQueryResultsDataModelType.SIFRows }
     *     
     */
    public void setSIFRows(SIFExtendedQueryResultsDataModelType.SIFRows value) {
        this.sifRows = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="SIF_Element" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
     *                 &lt;attribute name="ObjectName" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}ObjectNameType" />
     *                 &lt;attribute name="Alias">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
     *                       &lt;maxLength value="64"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "sifElement"
    })
    public static class SIFColumnHeaders {

        @XmlElement(name = "SIF_Element", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIFExtendedQueryResultsDataModelType.SIFColumnHeaders.SIFElement> sifElement;

        /**
         * Gets the value of the sifElement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sifElement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSIFElement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIFExtendedQueryResultsDataModelType.SIFColumnHeaders.SIFElement }
         * 
         * 
         */
        public List<SIFExtendedQueryResultsDataModelType.SIFColumnHeaders.SIFElement> getSIFElement() {
            if (sifElement == null) {
                sifElement = new ArrayList<SIFExtendedQueryResultsDataModelType.SIFColumnHeaders.SIFElement>();
            }
            return this.sifElement;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
         *       &lt;attribute name="ObjectName" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}ObjectNameType" />
         *       &lt;attribute name="Alias">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
         *             &lt;maxLength value="64"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class SIFElement {

            @XmlValue
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String value;
            @XmlAttribute(name = "ObjectName", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String objectName;
            @XmlAttribute(name = "Alias")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String alias;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the objectName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getObjectName() {
                return objectName;
            }

            /**
             * Sets the value of the objectName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setObjectName(String value) {
                this.objectName = value;
            }

            /**
             * Gets the value of the alias property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAlias() {
                return alias;
            }

            /**
             * Sets the value of the alias property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAlias(String value) {
                this.alias = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="R" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="C" type="{http://www.SIFinfo.org/au/datamodel/1.3}SelectedContentType" maxOccurs="unbounded" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "r"
    })
    public static class SIFRows {

        @XmlElement(name = "R", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIFExtendedQueryResultsDataModelType.SIFRows.R> r;

        /**
         * Gets the value of the r property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the r property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getR().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIFExtendedQueryResultsDataModelType.SIFRows.R }
         * 
         * 
         */
        public List<SIFExtendedQueryResultsDataModelType.SIFRows.R> getR() {
            if (r == null) {
                r = new ArrayList<SIFExtendedQueryResultsDataModelType.SIFRows.R>();
            }
            return this.r;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="C" type="{http://www.SIFinfo.org/au/datamodel/1.3}SelectedContentType" maxOccurs="unbounded" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "c"
        })
        public static class R {

            @XmlElement(name = "C", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected List<SelectedContentType> c;

            /**
             * Gets the value of the c property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the c property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getC().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link SelectedContentType }
             * 
             * 
             */
            public List<SelectedContentType> getC() {
                if (c == null) {
                    c = new ArrayList<SelectedContentType>();
                }
                return this.c;
            }

        }

    }

}
