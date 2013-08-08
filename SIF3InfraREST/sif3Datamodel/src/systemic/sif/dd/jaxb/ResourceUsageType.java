
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
 * 			This object tracks resource usage by a student or staff member at a school over a defined time period.
 * 		  
 * 
 * <p>Java class for ResourceUsageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResourceUsageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="ResourceUsageContentType" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsResourceUsageContentTypeType" minOccurs="0"/>
 *                   &lt;element name="LocalDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ResourceReportColumnList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ResourceReportColumn" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ColumnName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="ColumnDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="ColumnDelimiter" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="ResourceReportLineList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ResourceReportLine" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SIF_RefId" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                                     &lt;attribute name="SIF_RefObject" use="required">
 *                                       &lt;simpleType>
 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                           &lt;enumeration value="StudentPersonal"/>
 *                                           &lt;enumeration value="StaffPersonal"/>
 *                                           &lt;enumeration value="TeachingGroup"/>
 *                                         &lt;/restriction>
 *                                       &lt;/simpleType>
 *                                     &lt;/attribute>
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                             &lt;element name="CurrentCost" type="{http://www.SIFinfo.org/au/datamodel/1.3}MonetaryAmountType" minOccurs="0"/>
 *                             &lt;element name="ReportRow" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResourceUsageType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "schoolInfoRefId",
    "resourceUsageContentType",
    "resourceReportColumnList",
    "resourceReportLineList",
    "sifMetadata",
    "sifExtendedElements"
})
public class ResourceUsageType {

    @XmlElement(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlElement(name = "ResourceUsageContentType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected ResourceUsageType.ResourceUsageContentType resourceUsageContentType;
    @XmlElement(name = "ResourceReportColumnList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected ResourceUsageType.ResourceReportColumnList resourceReportColumnList;
    @XmlElement(name = "ResourceReportLineList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected ResourceUsageType.ResourceReportLineList resourceReportLineList;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the schoolInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolInfoRefId() {
        return schoolInfoRefId;
    }

    /**
     * Sets the value of the schoolInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolInfoRefId(String value) {
        this.schoolInfoRefId = value;
    }

    /**
     * Gets the value of the resourceUsageContentType property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceUsageType.ResourceUsageContentType }
     *     
     */
    public ResourceUsageType.ResourceUsageContentType getResourceUsageContentType() {
        return resourceUsageContentType;
    }

    /**
     * Sets the value of the resourceUsageContentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceUsageType.ResourceUsageContentType }
     *     
     */
    public void setResourceUsageContentType(ResourceUsageType.ResourceUsageContentType value) {
        this.resourceUsageContentType = value;
    }

    /**
     * Gets the value of the resourceReportColumnList property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceUsageType.ResourceReportColumnList }
     *     
     */
    public ResourceUsageType.ResourceReportColumnList getResourceReportColumnList() {
        return resourceReportColumnList;
    }

    /**
     * Sets the value of the resourceReportColumnList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceUsageType.ResourceReportColumnList }
     *     
     */
    public void setResourceReportColumnList(ResourceUsageType.ResourceReportColumnList value) {
        this.resourceReportColumnList = value;
    }

    /**
     * Gets the value of the resourceReportLineList property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceUsageType.ResourceReportLineList }
     *     
     */
    public ResourceUsageType.ResourceReportLineList getResourceReportLineList() {
        return resourceReportLineList;
    }

    /**
     * Sets the value of the resourceReportLineList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceUsageType.ResourceReportLineList }
     *     
     */
    public void setResourceReportLineList(ResourceUsageType.ResourceReportLineList value) {
        this.resourceReportLineList = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ResourceReportColumn" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ColumnName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="ColumnDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="ColumnDelimiter" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "resourceReportColumn"
    })
    public static class ResourceReportColumnList {

        @XmlElement(name = "ResourceReportColumn", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<ResourceUsageType.ResourceReportColumnList.ResourceReportColumn> resourceReportColumn;

        /**
         * Gets the value of the resourceReportColumn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resourceReportColumn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResourceReportColumn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResourceUsageType.ResourceReportColumnList.ResourceReportColumn }
         * 
         * 
         */
        public List<ResourceUsageType.ResourceReportColumnList.ResourceReportColumn> getResourceReportColumn() {
            if (resourceReportColumn == null) {
                resourceReportColumn = new ArrayList<ResourceUsageType.ResourceReportColumnList.ResourceReportColumn>();
            }
            return this.resourceReportColumn;
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
         *         &lt;element name="ColumnName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="ColumnDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="ColumnDelimiter" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "columnName",
            "columnDescription",
            "columnDelimiter"
        })
        public static class ResourceReportColumn {

            @XmlElement(name = "ColumnName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String columnName;
            @XmlElementRef(name = "ColumnDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> columnDescription;
            @XmlElementRef(name = "ColumnDelimiter", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> columnDelimiter;

            /**
             * Gets the value of the columnName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getColumnName() {
                return columnName;
            }

            /**
             * Sets the value of the columnName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setColumnName(String value) {
                this.columnName = value;
            }

            /**
             * Gets the value of the columnDescription property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getColumnDescription() {
                return columnDescription;
            }

            /**
             * Sets the value of the columnDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setColumnDescription(JAXBElement<String> value) {
                this.columnDescription = value;
            }

            /**
             * Gets the value of the columnDelimiter property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getColumnDelimiter() {
                return columnDelimiter;
            }

            /**
             * Sets the value of the columnDelimiter property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setColumnDelimiter(JAXBElement<String> value) {
                this.columnDelimiter = value;
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
     *         &lt;element name="ResourceReportLine" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SIF_RefId" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *                           &lt;attribute name="SIF_RefObject" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                                 &lt;enumeration value="StudentPersonal"/>
     *                                 &lt;enumeration value="StaffPersonal"/>
     *                                 &lt;enumeration value="TeachingGroup"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                   &lt;element name="CurrentCost" type="{http://www.SIFinfo.org/au/datamodel/1.3}MonetaryAmountType" minOccurs="0"/>
     *                   &lt;element name="ReportRow" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "resourceReportLine"
    })
    public static class ResourceReportLineList {

        @XmlElement(name = "ResourceReportLine", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<ResourceUsageType.ResourceReportLineList.ResourceReportLine> resourceReportLine;

        /**
         * Gets the value of the resourceReportLine property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resourceReportLine property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResourceReportLine().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ResourceUsageType.ResourceReportLineList.ResourceReportLine }
         * 
         * 
         */
        public List<ResourceUsageType.ResourceReportLineList.ResourceReportLine> getResourceReportLine() {
            if (resourceReportLine == null) {
                resourceReportLine = new ArrayList<ResourceUsageType.ResourceReportLineList.ResourceReportLine>();
            }
            return this.resourceReportLine;
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
         *         &lt;element name="SIF_RefId" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
         *                 &lt;attribute name="SIF_RefObject" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *                       &lt;enumeration value="StudentPersonal"/>
         *                       &lt;enumeration value="StaffPersonal"/>
         *                       &lt;enumeration value="TeachingGroup"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *         &lt;element name="CurrentCost" type="{http://www.SIFinfo.org/au/datamodel/1.3}MonetaryAmountType" minOccurs="0"/>
         *         &lt;element name="ReportRow" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "sifRefId",
            "startDate",
            "endDate",
            "currentCost",
            "reportRow"
        })
        public static class ResourceReportLine {

            @XmlElementRef(name = "SIF_RefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId> sifRefId;
            @XmlElement(name = "StartDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar startDate;
            @XmlElementRef(name = "EndDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> endDate;
            @XmlElement(name = "CurrentCost", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected MonetaryAmountType currentCost;
            @XmlElement(name = "ReportRow", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String reportRow;

            /**
             * Gets the value of the sifRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId }{@code >}
             *     
             */
            public JAXBElement<ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId> getSIFRefId() {
                return sifRefId;
            }

            /**
             * Sets the value of the sifRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId }{@code >}
             *     
             */
            public void setSIFRefId(JAXBElement<ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId> value) {
                this.sifRefId = value;
            }

            /**
             * Gets the value of the startDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getStartDate() {
                return startDate;
            }

            /**
             * Sets the value of the startDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setStartDate(XMLGregorianCalendar value) {
                this.startDate = value;
            }

            /**
             * Gets the value of the endDate property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *     
             */
            public JAXBElement<XMLGregorianCalendar> getEndDate() {
                return endDate;
            }

            /**
             * Sets the value of the endDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *     
             */
            public void setEndDate(JAXBElement<XMLGregorianCalendar> value) {
                this.endDate = value;
            }

            /**
             * Gets the value of the currentCost property.
             * 
             * @return
             *     possible object is
             *     {@link MonetaryAmountType }
             *     
             */
            public MonetaryAmountType getCurrentCost() {
                return currentCost;
            }

            /**
             * Sets the value of the currentCost property.
             * 
             * @param value
             *     allowed object is
             *     {@link MonetaryAmountType }
             *     
             */
            public void setCurrentCost(MonetaryAmountType value) {
                this.currentCost = value;
            }

            /**
             * Gets the value of the reportRow property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReportRow() {
                return reportRow;
            }

            /**
             * Sets the value of the reportRow property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReportRow(String value) {
                this.reportRow = value;
            }


            /**
             * SIF RefId that identifies the student, staff or group whose usage is being tracked.
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
             *             &lt;enumeration value="StudentPersonal"/>
             *             &lt;enumeration value="StaffPersonal"/>
             *             &lt;enumeration value="TeachingGroup"/>
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
            public static class SIFRefId {

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
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsResourceUsageContentTypeType" minOccurs="0"/>
     *         &lt;element name="LocalDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "code",
        "localDescription"
    })
    public static class ResourceUsageContentType {

        @XmlElement(name = "Code", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String code;
        @XmlElementRef(name = "LocalDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> localDescription;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the localDescription property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getLocalDescription() {
            return localDescription;
        }

        /**
         * Sets the value of the localDescription property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setLocalDescription(JAXBElement<String> value) {
            this.localDescription = value;
        }

    }

}
