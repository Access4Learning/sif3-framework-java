
package systemic.sif.dd.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         This object provides a way to define a report within a SIF zone. It contains metadata about the report. This object
 *         represents a mutual agreement between senders and receivers of the report.
 *       
 * 
 * <p>Java class for ReportManifestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReportManifestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SIF_Version" type="{http://www.SIFinfo.org/au/datamodel/1.3}VersionWithWildcardsType" minOccurs="0"/>
 *         &lt;element name="SIF_MaxBufferSize" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ReceivingAuthority" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="ReportAuthorityInfo"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ReportName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportingPeriod" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BeginReportDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="EndReportDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="BeginSubmitDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="EndSubmitDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ReportDefinitionSource" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="Type" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="URL"/>
 *                       &lt;enumeration value="Embedded"/>
 *                       &lt;enumeration value="Base64Binary"/>
 *                       &lt;enumeration value="SIF_Query"/>
 *                       &lt;enumeration value="SIF_ExtendedQuery"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="QueryLanguage" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ReportFormatList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ReportFormat" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="Type" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="URL"/>
 *                                 &lt;enumeration value="Embedded"/>
 *                                 &lt;enumeration value="Base64Binary"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="ContentType" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;choice>
 *           &lt;element name="SIF_QueryGroup" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_Query" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="SIF_ExtendedQuery" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedQueryDataModelType" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" />
 *       &lt;attribute name="ReportAuthorityInfoRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportManifestType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "sifVersion",
    "sifMaxBufferSize",
    "receivingAuthority",
    "reportName",
    "description",
    "reportingPeriod",
    "reportDefinitionSource",
    "reportFormatList",
    "sifQueryGroup",
    "sifExtendedQuery",
    "sifMetadata",
    "sifExtendedElements"
})
public class ReportManifestType {

    @XmlElement(name = "SIF_Version", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sifVersion;
    @XmlElement(name = "SIF_MaxBufferSize", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "unsignedInt")
    protected Long sifMaxBufferSize;
    @XmlElementRef(name = "ReceivingAuthority", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ReportManifestType.ReceivingAuthority> receivingAuthority;
    @XmlElement(name = "ReportName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String reportName;
    @XmlElementRef(name = "Description", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "ReportingPeriod", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ReportManifestType.ReportingPeriod> reportingPeriod;
    @XmlElement(name = "ReportDefinitionSource", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected ReportManifestType.ReportDefinitionSource reportDefinitionSource;
    @XmlElementRef(name = "ReportFormatList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ReportManifestType.ReportFormatList> reportFormatList;
    @XmlElementRef(name = "SIF_QueryGroup", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ReportManifestType.SIFQueryGroup> sifQueryGroup;
    @XmlElementRef(name = "SIF_ExtendedQuery", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedQueryDataModelType> sifExtendedQuery;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;
    @XmlAttribute(name = "ReportAuthorityInfoRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String reportAuthorityInfoRefId;

    /**
     * Gets the value of the sifVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIFVersion() {
        return sifVersion;
    }

    /**
     * Sets the value of the sifVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIFVersion(String value) {
        this.sifVersion = value;
    }

    /**
     * Gets the value of the sifMaxBufferSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSIFMaxBufferSize() {
        return sifMaxBufferSize;
    }

    /**
     * Sets the value of the sifMaxBufferSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSIFMaxBufferSize(Long value) {
        this.sifMaxBufferSize = value;
    }

    /**
     * Gets the value of the receivingAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.ReceivingAuthority }{@code >}
     *     
     */
    public JAXBElement<ReportManifestType.ReceivingAuthority> getReceivingAuthority() {
        return receivingAuthority;
    }

    /**
     * Sets the value of the receivingAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.ReceivingAuthority }{@code >}
     *     
     */
    public void setReceivingAuthority(JAXBElement<ReportManifestType.ReceivingAuthority> value) {
        this.receivingAuthority = value;
    }

    /**
     * Gets the value of the reportName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the value of the reportName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportName(String value) {
        this.reportName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = value;
    }

    /**
     * Gets the value of the reportingPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.ReportingPeriod }{@code >}
     *     
     */
    public JAXBElement<ReportManifestType.ReportingPeriod> getReportingPeriod() {
        return reportingPeriod;
    }

    /**
     * Sets the value of the reportingPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.ReportingPeriod }{@code >}
     *     
     */
    public void setReportingPeriod(JAXBElement<ReportManifestType.ReportingPeriod> value) {
        this.reportingPeriod = value;
    }

    /**
     * Gets the value of the reportDefinitionSource property.
     * 
     * @return
     *     possible object is
     *     {@link ReportManifestType.ReportDefinitionSource }
     *     
     */
    public ReportManifestType.ReportDefinitionSource getReportDefinitionSource() {
        return reportDefinitionSource;
    }

    /**
     * Sets the value of the reportDefinitionSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportManifestType.ReportDefinitionSource }
     *     
     */
    public void setReportDefinitionSource(ReportManifestType.ReportDefinitionSource value) {
        this.reportDefinitionSource = value;
    }

    /**
     * Gets the value of the reportFormatList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.ReportFormatList }{@code >}
     *     
     */
    public JAXBElement<ReportManifestType.ReportFormatList> getReportFormatList() {
        return reportFormatList;
    }

    /**
     * Sets the value of the reportFormatList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.ReportFormatList }{@code >}
     *     
     */
    public void setReportFormatList(JAXBElement<ReportManifestType.ReportFormatList> value) {
        this.reportFormatList = value;
    }

    /**
     * Gets the value of the sifQueryGroup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.SIFQueryGroup }{@code >}
     *     
     */
    public JAXBElement<ReportManifestType.SIFQueryGroup> getSIFQueryGroup() {
        return sifQueryGroup;
    }

    /**
     * Sets the value of the sifQueryGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReportManifestType.SIFQueryGroup }{@code >}
     *     
     */
    public void setSIFQueryGroup(JAXBElement<ReportManifestType.SIFQueryGroup> value) {
        this.sifQueryGroup = value;
    }

    /**
     * Gets the value of the sifExtendedQuery property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedQueryDataModelType }{@code >}
     *     
     */
    public JAXBElement<SIFExtendedQueryDataModelType> getSIFExtendedQuery() {
        return sifExtendedQuery;
    }

    /**
     * Sets the value of the sifExtendedQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedQueryDataModelType }{@code >}
     *     
     */
    public void setSIFExtendedQuery(JAXBElement<SIFExtendedQueryDataModelType> value) {
        this.sifExtendedQuery = value;
    }

    /**
     * Gets the value of the sifMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public JAXBElement<SIFMetadataType> getSIFMetadata() {
        return sifMetadata;
    }

    /**
     * Sets the value of the sifMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public void setSIFMetadata(JAXBElement<SIFMetadataType> value) {
        this.sifMetadata = value;
    }

    /**
     * Gets the value of the sifExtendedElements property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public JAXBElement<SIFExtendedElementsType> getSIFExtendedElements() {
        return sifExtendedElements;
    }

    /**
     * Sets the value of the sifExtendedElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public void setSIFExtendedElements(JAXBElement<SIFExtendedElementsType> value) {
        this.sifExtendedElements = value;
    }

    /**
     * Gets the value of the refId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefId() {
        return refId;
    }

    /**
     * Sets the value of the refId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefId(String value) {
        this.refId = value;
    }

    /**
     * Gets the value of the reportAuthorityInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportAuthorityInfoRefId() {
        return reportAuthorityInfoRefId;
    }

    /**
     * Sets the value of the reportAuthorityInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportAuthorityInfoRefId(String value) {
        this.reportAuthorityInfoRefId = value;
    }


    /**
     * The GUID of the authority for whom the report is intended, usually the same authority that defines this manifest.
     * 
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *       &lt;attribute name="SIF_RefObject" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="ReportAuthorityInfo"/>
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
    public static class ReceivingAuthority {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "SIF_RefObject", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sifRefObject;

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
         * Gets the value of the sifRefObject property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSIFRefObject() {
            return sifRefObject;
        }

        /**
         * Sets the value of the sifRefObject property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSIFRefObject(String value) {
            this.sifRefObject = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="Type" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="URL"/>
     *             &lt;enumeration value="Embedded"/>
     *             &lt;enumeration value="Base64Binary"/>
     *             &lt;enumeration value="SIF_Query"/>
     *             &lt;enumeration value="SIF_ExtendedQuery"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="QueryLanguage" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
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
    public static class ReportDefinitionSource {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "Type", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String type;
        @XmlAttribute(name = "QueryLanguage", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "token")
        protected String queryLanguage;

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
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the queryLanguage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQueryLanguage() {
            return queryLanguage;
        }

        /**
         * Sets the value of the queryLanguage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQueryLanguage(String value) {
            this.queryLanguage = value;
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
     *         &lt;element name="ReportFormat" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="Type" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="URL"/>
     *                       &lt;enumeration value="Embedded"/>
     *                       &lt;enumeration value="Base64Binary"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="ContentType" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        "reportFormat"
    })
    public static class ReportFormatList {

        @XmlElement(name = "ReportFormat", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<ReportManifestType.ReportFormatList.ReportFormat> reportFormat;

        /**
         * Gets the value of the reportFormat property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reportFormat property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReportFormat().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReportManifestType.ReportFormatList.ReportFormat }
         * 
         * 
         */
        public List<ReportManifestType.ReportFormatList.ReportFormat> getReportFormat() {
            if (reportFormat == null) {
                reportFormat = new ArrayList<ReportManifestType.ReportFormatList.ReportFormat>();
            }
            return this.reportFormat;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="Type" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="URL"/>
         *             &lt;enumeration value="Embedded"/>
         *             &lt;enumeration value="Base64Binary"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="ContentType" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        public static class ReportFormat {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "Type", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String type;
            @XmlAttribute(name = "ContentType", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String contentType;

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
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the contentType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getContentType() {
                return contentType;
            }

            /**
             * Sets the value of the contentType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setContentType(String value) {
                this.contentType = value;
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
     *         &lt;element name="BeginReportDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="EndReportDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="BeginSubmitDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="EndSubmitDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
        "beginReportDate",
        "endReportDate",
        "beginSubmitDate",
        "endSubmitDate",
        "dueDate"
    })
    public static class ReportingPeriod {

        @XmlElementRef(name = "BeginReportDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> beginReportDate;
        @XmlElementRef(name = "EndReportDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> endReportDate;
        @XmlElementRef(name = "BeginSubmitDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> beginSubmitDate;
        @XmlElementRef(name = "EndSubmitDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> endSubmitDate;
        @XmlElementRef(name = "DueDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dueDate;

        /**
         * Gets the value of the beginReportDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getBeginReportDate() {
            return beginReportDate;
        }

        /**
         * Sets the value of the beginReportDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setBeginReportDate(JAXBElement<XMLGregorianCalendar> value) {
            this.beginReportDate = value;
        }

        /**
         * Gets the value of the endReportDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getEndReportDate() {
            return endReportDate;
        }

        /**
         * Sets the value of the endReportDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setEndReportDate(JAXBElement<XMLGregorianCalendar> value) {
            this.endReportDate = value;
        }

        /**
         * Gets the value of the beginSubmitDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getBeginSubmitDate() {
            return beginSubmitDate;
        }

        /**
         * Sets the value of the beginSubmitDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setBeginSubmitDate(JAXBElement<XMLGregorianCalendar> value) {
            this.beginSubmitDate = value;
        }

        /**
         * Gets the value of the endSubmitDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getEndSubmitDate() {
            return endSubmitDate;
        }

        /**
         * Sets the value of the endSubmitDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setEndSubmitDate(JAXBElement<XMLGregorianCalendar> value) {
            this.endSubmitDate = value;
        }

        /**
         * Gets the value of the dueDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getDueDate() {
            return dueDate;
        }

        /**
         * Sets the value of the dueDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setDueDate(JAXBElement<XMLGregorianCalendar> value) {
            this.dueDate = value;
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
     *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_Query" maxOccurs="unbounded" minOccurs="0"/>
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
        "sifQuery"
    })
    public static class SIFQueryGroup {

        @XmlElement(name = "SIF_Query", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIFQueryType> sifQuery;

        /**
         * Gets the value of the sifQuery property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sifQuery property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSIFQuery().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIFQueryType }
         * 
         * 
         */
        public List<SIFQueryType> getSIFQuery() {
            if (sifQuery == null) {
                sifQuery = new ArrayList<SIFQueryType>();
            }
            return this.sifQuery;
        }

    }

}
