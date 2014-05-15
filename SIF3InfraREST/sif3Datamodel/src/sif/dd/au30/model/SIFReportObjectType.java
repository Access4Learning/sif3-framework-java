
package sif.dd.au30.model;

import java.math.BigInteger;
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
 *         This object is an envelope used to send other data objects, typically reports, to authorities such as state departments
 *         of education, district offices, other schools, etc. It is also used to signal a data collector agent that a report is
 *         compiled and ready to be requested. SIF_ReportObject contains a data object, which is either complete or partial (a
 *         partial data object is one that contains a subset of the elements for the data object). One or a series of
 *         SIF_ReportObjects contained within one or a series of related SIF_Response messages makes up a complete report.
 *       
 * 
 * <p>Java class for SIF_ReportObjectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_ReportObjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="ReportInfo" minOccurs="0">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="CalculationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                     &lt;element name="SubmissionNumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *                     &lt;element name="SubmissionReason" minOccurs="0">
 *                       &lt;simpleType>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                           &lt;enumeration value="Initial"/>
 *                           &lt;enumeration value="Correction"/>
 *                           &lt;enumeration value="Revision"/>
 *                           &lt;enumeration value="Addition"/>
 *                         &lt;/restriction>
 *                       &lt;/simpleType>
 *                     &lt;/element>
 *                     &lt;element name="ReportSubmitterInfo" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="SIF_RefId" minOccurs="0">
 *                                 &lt;complexType>
 *                                   &lt;simpleContent>
 *                                     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                                       &lt;attribute name="SIF_RefObject" use="required">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                             &lt;enumeration value="LEAInfo"/>
 *                                             &lt;enumeration value="SchoolInfo"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/attribute>
 *                                     &lt;/extension>
 *                                   &lt;/simpleContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                               &lt;element name="SubmitterName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                               &lt;element name="SubmitterDepartment" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                               &lt;element name="ContactInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}ContactInfoType" minOccurs="0"/>
 *                               &lt;element name="Address" type="{http://www.sifassociation.org/au/datamodel/1.3}AddressType" minOccurs="0"/>
 *                               &lt;element name="PhoneNumber" type="{http://www.sifassociation.org/au/datamodel/1.3}PhoneNumberType" minOccurs="0"/>
 *                               &lt;element name="SubmitterNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;/sequence>
 *                   &lt;attribute name="ReportManifestRefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" />
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="ReportData" type="{http://www.sifassociation.org/au/datamodel/1.3}ReportDataObjectType" minOccurs="0"/>
 *           &lt;element name="SIF_ExtendedQueryResults" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedQueryResultsDataModelType" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="SIF_Metadata" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF_ReportObjectType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "reportInfo",
    "reportData",
    "sifExtendedQueryResults",
    "sifMetadata",
    "sifExtendedElements"
})
public class SIFReportObjectType {

    @XmlElementRef(name = "ReportInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFReportObjectType.ReportInfo> reportInfo;
    @XmlElementRef(name = "ReportData", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ReportDataObjectType> reportData;
    @XmlElementRef(name = "SIF_ExtendedQueryResults", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedQueryResultsDataModelType> sifExtendedQueryResults;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the reportInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo }{@code >}
     *     
     */
    public JAXBElement<SIFReportObjectType.ReportInfo> getReportInfo() {
        return reportInfo;
    }

    /**
     * Sets the value of the reportInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo }{@code >}
     *     
     */
    public void setReportInfo(JAXBElement<SIFReportObjectType.ReportInfo> value) {
        this.reportInfo = value;
    }

    /**
     * Gets the value of the reportData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ReportDataObjectType }{@code >}
     *     
     */
    public JAXBElement<ReportDataObjectType> getReportData() {
        return reportData;
    }

    /**
     * Sets the value of the reportData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ReportDataObjectType }{@code >}
     *     
     */
    public void setReportData(JAXBElement<ReportDataObjectType> value) {
        this.reportData = value;
    }

    /**
     * Gets the value of the sifExtendedQueryResults property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedQueryResultsDataModelType }{@code >}
     *     
     */
    public JAXBElement<SIFExtendedQueryResultsDataModelType> getSIFExtendedQueryResults() {
        return sifExtendedQueryResults;
    }

    /**
     * Sets the value of the sifExtendedQueryResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedQueryResultsDataModelType }{@code >}
     *     
     */
    public void setSIFExtendedQueryResults(JAXBElement<SIFExtendedQueryResultsDataModelType> value) {
        this.sifExtendedQueryResults = value;
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
     *         &lt;element name="CalculationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="SubmissionNumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
     *         &lt;element name="SubmissionReason" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="Initial"/>
     *               &lt;enumeration value="Correction"/>
     *               &lt;enumeration value="Revision"/>
     *               &lt;enumeration value="Addition"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ReportSubmitterInfo" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SIF_RefId" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *                           &lt;attribute name="SIF_RefObject" use="required">
     *                             &lt;simpleType>
     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                                 &lt;enumeration value="LEAInfo"/>
     *                                 &lt;enumeration value="SchoolInfo"/>
     *                               &lt;/restriction>
     *                             &lt;/simpleType>
     *                           &lt;/attribute>
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="SubmitterName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="SubmitterDepartment" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="ContactInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}ContactInfoType" minOccurs="0"/>
     *                   &lt;element name="Address" type="{http://www.sifassociation.org/au/datamodel/1.3}AddressType" minOccurs="0"/>
     *                   &lt;element name="PhoneNumber" type="{http://www.sifassociation.org/au/datamodel/1.3}PhoneNumberType" minOccurs="0"/>
     *                   &lt;element name="SubmitterNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="ReportManifestRefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "calculationDate",
        "submissionNumber",
        "submissionReason",
        "reportSubmitterInfo",
        "description"
    })
    public static class ReportInfo {

        @XmlElement(name = "CalculationDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar calculationDate;
        @XmlElement(name = "SubmissionNumber", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger submissionNumber;
        @XmlElement(name = "SubmissionReason", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String submissionReason;
        @XmlElementRef(name = "ReportSubmitterInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo> reportSubmitterInfo;
        @XmlElementRef(name = "Description", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> description;
        @XmlAttribute(name = "ReportManifestRefId", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String reportManifestRefId;

        /**
         * Gets the value of the calculationDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCalculationDate() {
            return calculationDate;
        }

        /**
         * Sets the value of the calculationDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCalculationDate(XMLGregorianCalendar value) {
            this.calculationDate = value;
        }

        /**
         * Gets the value of the submissionNumber property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getSubmissionNumber() {
            return submissionNumber;
        }

        /**
         * Sets the value of the submissionNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setSubmissionNumber(BigInteger value) {
            this.submissionNumber = value;
        }

        /**
         * Gets the value of the submissionReason property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubmissionReason() {
            return submissionReason;
        }

        /**
         * Sets the value of the submissionReason property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubmissionReason(String value) {
            this.submissionReason = value;
        }

        /**
         * Gets the value of the reportSubmitterInfo property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo }{@code >}
         *     
         */
        public JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo> getReportSubmitterInfo() {
            return reportSubmitterInfo;
        }

        /**
         * Sets the value of the reportSubmitterInfo property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo }{@code >}
         *     
         */
        public void setReportSubmitterInfo(JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo> value) {
            this.reportSubmitterInfo = value;
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
         * Gets the value of the reportManifestRefId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReportManifestRefId() {
            return reportManifestRefId;
        }

        /**
         * Sets the value of the reportManifestRefId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReportManifestRefId(String value) {
            this.reportManifestRefId = value;
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
         *               &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>IdRefTypeOrEmpty">
         *                 &lt;attribute name="SIF_RefObject" use="required">
         *                   &lt;simpleType>
         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *                       &lt;enumeration value="LEAInfo"/>
         *                       &lt;enumeration value="SchoolInfo"/>
         *                     &lt;/restriction>
         *                   &lt;/simpleType>
         *                 &lt;/attribute>
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="SubmitterName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="SubmitterDepartment" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="ContactInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}ContactInfoType" minOccurs="0"/>
         *         &lt;element name="Address" type="{http://www.sifassociation.org/au/datamodel/1.3}AddressType" minOccurs="0"/>
         *         &lt;element name="PhoneNumber" type="{http://www.sifassociation.org/au/datamodel/1.3}PhoneNumberType" minOccurs="0"/>
         *         &lt;element name="SubmitterNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "submitterName",
            "submitterDepartment",
            "contactInfo",
            "address",
            "phoneNumber",
            "submitterNotes"
        })
        public static class ReportSubmitterInfo {

            @XmlElementRef(name = "SIF_RefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId> sifRefId;
            @XmlElement(name = "SubmitterName", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String submitterName;
            @XmlElementRef(name = "SubmitterDepartment", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> submitterDepartment;
            @XmlElementRef(name = "ContactInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<ContactInfoType> contactInfo;
            @XmlElementRef(name = "Address", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<AddressType> address;
            @XmlElementRef(name = "PhoneNumber", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<PhoneNumberType> phoneNumber;
            @XmlElementRef(name = "SubmitterNotes", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> submitterNotes;

            /**
             * Gets the value of the sifRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId }{@code >}
             *     
             */
            public JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId> getSIFRefId() {
                return sifRefId;
            }

            /**
             * Sets the value of the sifRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId }{@code >}
             *     
             */
            public void setSIFRefId(JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId> value) {
                this.sifRefId = value;
            }

            /**
             * Gets the value of the submitterName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubmitterName() {
                return submitterName;
            }

            /**
             * Sets the value of the submitterName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubmitterName(String value) {
                this.submitterName = value;
            }

            /**
             * Gets the value of the submitterDepartment property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSubmitterDepartment() {
                return submitterDepartment;
            }

            /**
             * Sets the value of the submitterDepartment property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSubmitterDepartment(JAXBElement<String> value) {
                this.submitterDepartment = value;
            }

            /**
             * Gets the value of the contactInfo property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link ContactInfoType }{@code >}
             *     
             */
            public JAXBElement<ContactInfoType> getContactInfo() {
                return contactInfo;
            }

            /**
             * Sets the value of the contactInfo property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link ContactInfoType }{@code >}
             *     
             */
            public void setContactInfo(JAXBElement<ContactInfoType> value) {
                this.contactInfo = value;
            }

            /**
             * Gets the value of the address property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link AddressType }{@code >}
             *     
             */
            public JAXBElement<AddressType> getAddress() {
                return address;
            }

            /**
             * Sets the value of the address property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link AddressType }{@code >}
             *     
             */
            public void setAddress(JAXBElement<AddressType> value) {
                this.address = value;
            }

            /**
             * Gets the value of the phoneNumber property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link PhoneNumberType }{@code >}
             *     
             */
            public JAXBElement<PhoneNumberType> getPhoneNumber() {
                return phoneNumber;
            }

            /**
             * Sets the value of the phoneNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link PhoneNumberType }{@code >}
             *     
             */
            public void setPhoneNumber(JAXBElement<PhoneNumberType> value) {
                this.phoneNumber = value;
            }

            /**
             * Gets the value of the submitterNotes property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSubmitterNotes() {
                return submitterNotes;
            }

            /**
             * Sets the value of the submitterNotes property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSubmitterNotes(JAXBElement<String> value) {
                this.submitterNotes = value;
            }


            /**
             * If the submitter is described by another SIF data object such as a SchoolInfo or LEAInfo instance, this element references that object by RefId.
             * 
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>IdRefTypeOrEmpty">
             *       &lt;attribute name="SIF_RefObject" use="required">
             *         &lt;simpleType>
             *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
             *             &lt;enumeration value="LEAInfo"/>
             *             &lt;enumeration value="SchoolInfo"/>
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

}
