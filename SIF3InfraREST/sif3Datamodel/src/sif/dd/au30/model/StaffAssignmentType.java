
package sif.dd.au30.model;

import java.math.BigDecimal;
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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         This object defines information related to a staff member's assignment(s); commonly, this will be a school
 *         assignment.
 *       
 * 
 * <p>Java class for StaffAssignmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StaffAssignmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
 *         &lt;element name="StaffPersonalRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="PrimaryAssignment" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="JobStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="JobEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="JobFTE" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="1"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="JobFunction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StaffSubjectList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StaffSubject" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *                             &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="TimeTableSubjectRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
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
 *         &lt;element name="StaffActivity" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsStaffActivityType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="YearLevels" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="CasualReliefTeacher" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="Homegroup" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="House" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
@XmlType(name = "StaffAssignmentType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "schoolInfoRefId",
    "schoolYear",
    "staffPersonalRefId",
    "description",
    "primaryAssignment",
    "jobStartDate",
    "jobEndDate",
    "jobFTE",
    "jobFunction",
    "staffSubjectList",
    "staffActivity",
    "yearLevels",
    "casualReliefTeacher",
    "homegroup",
    "house",
    "sifMetadata",
    "sifExtendedElements"
})
public class StaffAssignmentType {

    @XmlElement(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlElementRef(name = "SchoolYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> schoolYear;
    @XmlElement(name = "StaffPersonalRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String staffPersonalRefId;
    @XmlElementRef(name = "Description", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElement(name = "PrimaryAssignment", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected AUCodeSetsYesOrNoCategoryType primaryAssignment;
    @XmlElementRef(name = "JobStartDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> jobStartDate;
    @XmlElementRef(name = "JobEndDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> jobEndDate;
    @XmlElementRef(name = "JobFTE", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> jobFTE;
    @XmlElementRef(name = "JobFunction", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jobFunction;
    @XmlElementRef(name = "StaffSubjectList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StaffAssignmentType.StaffSubjectList> staffSubjectList;
    @XmlElementRef(name = "StaffActivity", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StaffAssignmentType.StaffActivity> staffActivity;
    @XmlElementRef(name = "YearLevels", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> yearLevels;
    @XmlElementRef(name = "CasualReliefTeacher", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> casualReliefTeacher;
    @XmlElementRef(name = "Homegroup", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> homegroup;
    @XmlElementRef(name = "House", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> house;
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
     * Gets the value of the schoolYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the value of the schoolYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setSchoolYear(JAXBElement<XMLGregorianCalendar> value) {
        this.schoolYear = value;
    }

    /**
     * Gets the value of the staffPersonalRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStaffPersonalRefId() {
        return staffPersonalRefId;
    }

    /**
     * Sets the value of the staffPersonalRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStaffPersonalRefId(String value) {
        this.staffPersonalRefId = value;
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
     * Gets the value of the primaryAssignment property.
     * 
     * @return
     *     possible object is
     *     {@link AUCodeSetsYesOrNoCategoryType }
     *     
     */
    public AUCodeSetsYesOrNoCategoryType getPrimaryAssignment() {
        return primaryAssignment;
    }

    /**
     * Sets the value of the primaryAssignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link AUCodeSetsYesOrNoCategoryType }
     *     
     */
    public void setPrimaryAssignment(AUCodeSetsYesOrNoCategoryType value) {
        this.primaryAssignment = value;
    }

    /**
     * Gets the value of the jobStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getJobStartDate() {
        return jobStartDate;
    }

    /**
     * Sets the value of the jobStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setJobStartDate(JAXBElement<XMLGregorianCalendar> value) {
        this.jobStartDate = value;
    }

    /**
     * Gets the value of the jobEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getJobEndDate() {
        return jobEndDate;
    }

    /**
     * Sets the value of the jobEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setJobEndDate(JAXBElement<XMLGregorianCalendar> value) {
        this.jobEndDate = value;
    }

    /**
     * Gets the value of the jobFTE property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getJobFTE() {
        return jobFTE;
    }

    /**
     * Sets the value of the jobFTE property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setJobFTE(JAXBElement<BigDecimal> value) {
        this.jobFTE = value;
    }

    /**
     * Gets the value of the jobFunction property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJobFunction() {
        return jobFunction;
    }

    /**
     * Sets the value of the jobFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJobFunction(JAXBElement<String> value) {
        this.jobFunction = value;
    }

    /**
     * Gets the value of the staffSubjectList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StaffAssignmentType.StaffSubjectList }{@code >}
     *     
     */
    public JAXBElement<StaffAssignmentType.StaffSubjectList> getStaffSubjectList() {
        return staffSubjectList;
    }

    /**
     * Sets the value of the staffSubjectList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StaffAssignmentType.StaffSubjectList }{@code >}
     *     
     */
    public void setStaffSubjectList(JAXBElement<StaffAssignmentType.StaffSubjectList> value) {
        this.staffSubjectList = value;
    }

    /**
     * Gets the value of the staffActivity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StaffAssignmentType.StaffActivity }{@code >}
     *     
     */
    public JAXBElement<StaffAssignmentType.StaffActivity> getStaffActivity() {
        return staffActivity;
    }

    /**
     * Sets the value of the staffActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StaffAssignmentType.StaffActivity }{@code >}
     *     
     */
    public void setStaffActivity(JAXBElement<StaffAssignmentType.StaffActivity> value) {
        this.staffActivity = value;
    }

    /**
     * Gets the value of the yearLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public JAXBElement<YearLevelsType> getYearLevels() {
        return yearLevels;
    }

    /**
     * Sets the value of the yearLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public void setYearLevels(JAXBElement<YearLevelsType> value) {
        this.yearLevels = value;
    }

    /**
     * Gets the value of the casualReliefTeacher property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getCasualReliefTeacher() {
        return casualReliefTeacher;
    }

    /**
     * Sets the value of the casualReliefTeacher property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setCasualReliefTeacher(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.casualReliefTeacher = value;
    }

    /**
     * Gets the value of the homegroup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHomegroup() {
        return homegroup;
    }

    /**
     * Sets the value of the homegroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHomegroup(JAXBElement<String> value) {
        this.homegroup = value;
    }

    /**
     * Gets the value of the house property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHouse() {
        return house;
    }

    /**
     * Sets the value of the house property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHouse(JAXBElement<String> value) {
        this.house = value;
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
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsStaffActivityType" minOccurs="0"/>
     *         &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
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
        "otherCodeList"
    })
    public static class StaffActivity {

        @XmlElement(name = "Code", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String code;
        @XmlElementRef(name = "OtherCodeList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<OtherCodeListType> otherCodeList;

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
         * Gets the value of the otherCodeList property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
         *     
         */
        public JAXBElement<OtherCodeListType> getOtherCodeList() {
            return otherCodeList;
        }

        /**
         * Sets the value of the otherCodeList property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
         *     
         */
        public void setOtherCodeList(JAXBElement<OtherCodeListType> value) {
            this.otherCodeList = value;
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
     *         &lt;element name="StaffSubject" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
     *                   &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="TimeTableSubjectRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
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
        "staffSubject"
    })
    public static class StaffSubjectList {

        @XmlElement(name = "StaffSubject", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<StaffAssignmentType.StaffSubjectList.StaffSubject> staffSubject;

        /**
         * Gets the value of the staffSubject property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the staffSubject property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStaffSubject().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StaffAssignmentType.StaffSubjectList.StaffSubject }
         * 
         * 
         */
        public List<StaffAssignmentType.StaffSubjectList.StaffSubject> getStaffSubject() {
            if (staffSubject == null) {
                staffSubject = new ArrayList<StaffAssignmentType.StaffSubjectList.StaffSubject>();
            }
            return this.staffSubject;
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
         *         &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
         *         &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="TimeTableSubjectRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
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
            "preferenceNumber",
            "subjectLocalId",
            "timeTableSubjectRefId"
        })
        public static class StaffSubject {

            @XmlElement(name = "PreferenceNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlSchemaType(name = "unsignedInt")
            protected Long preferenceNumber;
            @XmlElementRef(name = "SubjectLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> subjectLocalId;
            @XmlElementRef(name = "TimeTableSubjectRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> timeTableSubjectRefId;

            /**
             * Gets the value of the preferenceNumber property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getPreferenceNumber() {
                return preferenceNumber;
            }

            /**
             * Sets the value of the preferenceNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setPreferenceNumber(Long value) {
                this.preferenceNumber = value;
            }

            /**
             * Gets the value of the subjectLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSubjectLocalId() {
                return subjectLocalId;
            }

            /**
             * Sets the value of the subjectLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSubjectLocalId(JAXBElement<String> value) {
                this.subjectLocalId = value;
            }

            /**
             * Gets the value of the timeTableSubjectRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getTimeTableSubjectRefId() {
                return timeTableSubjectRefId;
            }

            /**
             * Sets the value of the timeTableSubjectRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setTimeTableSubjectRefId(JAXBElement<String> value) {
                this.timeTableSubjectRefId = value;
            }

        }

    }

}
