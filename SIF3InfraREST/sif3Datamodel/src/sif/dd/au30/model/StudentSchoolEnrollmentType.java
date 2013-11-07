
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
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         This object defines information related to a student's enrollment.  StudentSchoolEnrollment
 *         instances must not span multiple school years.
 *       
 * 
 * <p>Java class for StudentSchoolEnrollmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentSchoolEnrollmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentPersonalRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="MembershipType" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsSchoolEnrollmentTypeType" minOccurs="0"/>
 *         &lt;element name="TimeFrame" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsEnrollmentTimeFrameType" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolYear" minOccurs="0"/>
 *         &lt;element name="EntryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EntryType" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsEntryTypeType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="YearLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *         &lt;element name="Homeroom" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="RoomInfo"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Advisor" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="StaffPersonal"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Counselor" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="StaffPersonal"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Homegroup" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="House" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="IndividualLearningPlan" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="Calendar" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="CalendarSummary"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ExitDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ExitStatus" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsExitWithdrawalStatusType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ExitType" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsExitWithdrawalTypeType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="FTE" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="1"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FTPTStatus" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsFTPTStatusCodeType" minOccurs="0"/>
 *         &lt;element name="FFPOS" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsFFPOSStatusCodeType" minOccurs="0"/>
 *         &lt;element name="CatchmentStatus" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsPublicSchoolCatchmentStatusType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RecordClosureReason" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="SchoolExit"/>
 *               &lt;enumeration value="TimeDependentDataChange"/>
 *               &lt;enumeration value="EndOfYear"/>
 *               &lt;enumeration value="CampusExit"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PromotionInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PromotionStatus" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="Promoted"/>
 *                         &lt;enumeration value="Demoted"/>
 *                         &lt;enumeration value="Retained"/>
 *                         &lt;enumeration value="NA"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PreviousSchool" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="DestinationSchool" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="StudentSubjectChoiceList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StudentSubjectChoice" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *                             &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="StudyDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
 *                             &lt;element name="OtherSchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
@XmlType(name = "StudentSchoolEnrollmentType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "studentPersonalRefId",
    "schoolInfoRefId",
    "membershipType",
    "timeFrame",
    "schoolYear",
    "entryDate",
    "entryType",
    "yearLevel",
    "homeroom",
    "advisor",
    "counselor",
    "homegroup",
    "house",
    "individualLearningPlan",
    "calendar",
    "exitDate",
    "exitStatus",
    "exitType",
    "fte",
    "ftptStatus",
    "ffpos",
    "catchmentStatus",
    "recordClosureReason",
    "promotionInfo",
    "previousSchool",
    "destinationSchool",
    "studentSubjectChoiceList",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentSchoolEnrollmentType {

    @XmlElement(name = "StudentPersonalRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;
    @XmlElement(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlElement(name = "MembershipType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String membershipType;
    @XmlElement(name = "TimeFrame", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected AUCodeSetsEnrollmentTimeFrameType timeFrame;
    @XmlElement(name = "SchoolYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected XMLGregorianCalendar schoolYear;
    @XmlElement(name = "EntryDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar entryDate;
    @XmlElementRef(name = "EntryType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.EntryType> entryType;
    @XmlElementRef(name = "YearLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelType> yearLevel;
    @XmlElementRef(name = "Homeroom", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.Homeroom> homeroom;
    @XmlElementRef(name = "Advisor", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.Advisor> advisor;
    @XmlElementRef(name = "Counselor", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.Counselor> counselor;
    @XmlElementRef(name = "Homegroup", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> homegroup;
    @XmlElementRef(name = "House", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> house;
    @XmlElementRef(name = "IndividualLearningPlan", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> individualLearningPlan;
    @XmlElementRef(name = "Calendar", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.Calendar> calendar;
    @XmlElementRef(name = "ExitDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> exitDate;
    @XmlElementRef(name = "ExitStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.ExitStatus> exitStatus;
    @XmlElementRef(name = "ExitType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.ExitType> exitType;
    @XmlElementRef(name = "FTE", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> fte;
    @XmlElementRef(name = "FTPTStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ftptStatus;
    @XmlElementRef(name = "FFPOS", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> ffpos;
    @XmlElementRef(name = "CatchmentStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.CatchmentStatus> catchmentStatus;
    @XmlElementRef(name = "RecordClosureReason", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recordClosureReason;
    @XmlElementRef(name = "PromotionInfo", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.PromotionInfo> promotionInfo;
    @XmlElementRef(name = "PreviousSchool", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> previousSchool;
    @XmlElementRef(name = "DestinationSchool", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destinationSchool;
    @XmlElementRef(name = "StudentSubjectChoiceList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSchoolEnrollmentType.StudentSubjectChoiceList> studentSubjectChoiceList;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the studentPersonalRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentPersonalRefId() {
        return studentPersonalRefId;
    }

    /**
     * Sets the value of the studentPersonalRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentPersonalRefId(String value) {
        this.studentPersonalRefId = value;
    }

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
     * Gets the value of the membershipType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMembershipType() {
        return membershipType;
    }

    /**
     * Sets the value of the membershipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMembershipType(String value) {
        this.membershipType = value;
    }

    /**
     * Gets the value of the timeFrame property.
     * 
     * @return
     *     possible object is
     *     {@link AUCodeSetsEnrollmentTimeFrameType }
     *     
     */
    public AUCodeSetsEnrollmentTimeFrameType getTimeFrame() {
        return timeFrame;
    }

    /**
     * Sets the value of the timeFrame property.
     * 
     * @param value
     *     allowed object is
     *     {@link AUCodeSetsEnrollmentTimeFrameType }
     *     
     */
    public void setTimeFrame(AUCodeSetsEnrollmentTimeFrameType value) {
        this.timeFrame = value;
    }

    /**
     * Gets the value of the schoolYear property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the value of the schoolYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSchoolYear(XMLGregorianCalendar value) {
        this.schoolYear = value;
    }

    /**
     * Gets the value of the entryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntryDate() {
        return entryDate;
    }

    /**
     * Sets the value of the entryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntryDate(XMLGregorianCalendar value) {
        this.entryDate = value;
    }

    /**
     * Gets the value of the entryType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.EntryType }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.EntryType> getEntryType() {
        return entryType;
    }

    /**
     * Sets the value of the entryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.EntryType }{@code >}
     *     
     */
    public void setEntryType(JAXBElement<StudentSchoolEnrollmentType.EntryType> value) {
        this.entryType = value;
    }

    /**
     * Gets the value of the yearLevel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public JAXBElement<YearLevelType> getYearLevel() {
        return yearLevel;
    }

    /**
     * Sets the value of the yearLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public void setYearLevel(JAXBElement<YearLevelType> value) {
        this.yearLevel = value;
    }

    /**
     * Gets the value of the homeroom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Homeroom }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.Homeroom> getHomeroom() {
        return homeroom;
    }

    /**
     * Sets the value of the homeroom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Homeroom }{@code >}
     *     
     */
    public void setHomeroom(JAXBElement<StudentSchoolEnrollmentType.Homeroom> value) {
        this.homeroom = value;
    }

    /**
     * Gets the value of the advisor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Advisor }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.Advisor> getAdvisor() {
        return advisor;
    }

    /**
     * Sets the value of the advisor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Advisor }{@code >}
     *     
     */
    public void setAdvisor(JAXBElement<StudentSchoolEnrollmentType.Advisor> value) {
        this.advisor = value;
    }

    /**
     * Gets the value of the counselor property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Counselor }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.Counselor> getCounselor() {
        return counselor;
    }

    /**
     * Sets the value of the counselor property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Counselor }{@code >}
     *     
     */
    public void setCounselor(JAXBElement<StudentSchoolEnrollmentType.Counselor> value) {
        this.counselor = value;
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
     * Gets the value of the individualLearningPlan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getIndividualLearningPlan() {
        return individualLearningPlan;
    }

    /**
     * Sets the value of the individualLearningPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setIndividualLearningPlan(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.individualLearningPlan = value;
    }

    /**
     * Gets the value of the calendar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Calendar }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.Calendar> getCalendar() {
        return calendar;
    }

    /**
     * Sets the value of the calendar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Calendar }{@code >}
     *     
     */
    public void setCalendar(JAXBElement<StudentSchoolEnrollmentType.Calendar> value) {
        this.calendar = value;
    }

    /**
     * Gets the value of the exitDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getExitDate() {
        return exitDate;
    }

    /**
     * Sets the value of the exitDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setExitDate(JAXBElement<XMLGregorianCalendar> value) {
        this.exitDate = value;
    }

    /**
     * Gets the value of the exitStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.ExitStatus }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.ExitStatus> getExitStatus() {
        return exitStatus;
    }

    /**
     * Sets the value of the exitStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.ExitStatus }{@code >}
     *     
     */
    public void setExitStatus(JAXBElement<StudentSchoolEnrollmentType.ExitStatus> value) {
        this.exitStatus = value;
    }

    /**
     * Gets the value of the exitType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.ExitType }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.ExitType> getExitType() {
        return exitType;
    }

    /**
     * Sets the value of the exitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.ExitType }{@code >}
     *     
     */
    public void setExitType(JAXBElement<StudentSchoolEnrollmentType.ExitType> value) {
        this.exitType = value;
    }

    /**
     * Gets the value of the fte property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFTE() {
        return fte;
    }

    /**
     * Sets the value of the fte property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFTE(JAXBElement<BigDecimal> value) {
        this.fte = value;
    }

    /**
     * Gets the value of the ftptStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFTPTStatus() {
        return ftptStatus;
    }

    /**
     * Sets the value of the ftptStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFTPTStatus(JAXBElement<String> value) {
        this.ftptStatus = value;
    }

    /**
     * Gets the value of the ffpos property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFFPOS() {
        return ffpos;
    }

    /**
     * Sets the value of the ffpos property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFFPOS(JAXBElement<String> value) {
        this.ffpos = value;
    }

    /**
     * Gets the value of the catchmentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.CatchmentStatus }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.CatchmentStatus> getCatchmentStatus() {
        return catchmentStatus;
    }

    /**
     * Sets the value of the catchmentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.CatchmentStatus }{@code >}
     *     
     */
    public void setCatchmentStatus(JAXBElement<StudentSchoolEnrollmentType.CatchmentStatus> value) {
        this.catchmentStatus = value;
    }

    /**
     * Gets the value of the recordClosureReason property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecordClosureReason() {
        return recordClosureReason;
    }

    /**
     * Sets the value of the recordClosureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecordClosureReason(JAXBElement<String> value) {
        this.recordClosureReason = value;
    }

    /**
     * Gets the value of the promotionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.PromotionInfo }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.PromotionInfo> getPromotionInfo() {
        return promotionInfo;
    }

    /**
     * Sets the value of the promotionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.PromotionInfo }{@code >}
     *     
     */
    public void setPromotionInfo(JAXBElement<StudentSchoolEnrollmentType.PromotionInfo> value) {
        this.promotionInfo = value;
    }

    /**
     * Gets the value of the previousSchool property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreviousSchool() {
        return previousSchool;
    }

    /**
     * Sets the value of the previousSchool property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreviousSchool(JAXBElement<String> value) {
        this.previousSchool = value;
    }

    /**
     * Gets the value of the destinationSchool property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestinationSchool() {
        return destinationSchool;
    }

    /**
     * Sets the value of the destinationSchool property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestinationSchool(JAXBElement<String> value) {
        this.destinationSchool = value;
    }

    /**
     * Gets the value of the studentSubjectChoiceList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.StudentSubjectChoiceList }{@code >}
     *     
     */
    public JAXBElement<StudentSchoolEnrollmentType.StudentSubjectChoiceList> getStudentSubjectChoiceList() {
        return studentSubjectChoiceList;
    }

    /**
     * Sets the value of the studentSubjectChoiceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.StudentSubjectChoiceList }{@code >}
     *     
     */
    public void setStudentSubjectChoiceList(JAXBElement<StudentSchoolEnrollmentType.StudentSubjectChoiceList> value) {
        this.studentSubjectChoiceList = value;
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
     * Staff member assigned as an advisor.
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
     *             &lt;enumeration value="StaffPersonal"/>
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
    public static class Advisor {

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
     * The calendar assigned to this enrollment. 
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
     *             &lt;enumeration value="CalendarSummary"/>
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
    public static class Calendar {

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
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsPublicSchoolCatchmentStatusType" minOccurs="0"/>
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
    public static class CatchmentStatus {

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
     * Staff member assigned as an advisor.
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
     *             &lt;enumeration value="StaffPersonal"/>
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
    public static class Counselor {

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
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsEntryTypeType" minOccurs="0"/>
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
    public static class EntryType {

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
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsExitWithdrawalStatusType" minOccurs="0"/>
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
    public static class ExitStatus {

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
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsExitWithdrawalTypeType" minOccurs="0"/>
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
    public static class ExitType {

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
     * Homeroom for this enrollment.
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
     *             &lt;enumeration value="RoomInfo"/>
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
    public static class Homeroom {

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
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="PromotionStatus" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="Promoted"/>
     *               &lt;enumeration value="Demoted"/>
     *               &lt;enumeration value="Retained"/>
     *               &lt;enumeration value="NA"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
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
        "promotionStatus"
    })
    public static class PromotionInfo {

        @XmlElementRef(name = "PromotionStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> promotionStatus;

        /**
         * Gets the value of the promotionStatus property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getPromotionStatus() {
            return promotionStatus;
        }

        /**
         * Sets the value of the promotionStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setPromotionStatus(JAXBElement<String> value) {
            this.promotionStatus = value;
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
     *         &lt;element name="StudentSubjectChoice" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
     *                   &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="StudyDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
     *                   &lt;element name="OtherSchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
        "studentSubjectChoice"
    })
    public static class StudentSubjectChoiceList {

        @XmlElement(name = "StudentSubjectChoice", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice> studentSubjectChoice;

        /**
         * Gets the value of the studentSubjectChoice property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the studentSubjectChoice property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStudentSubjectChoice().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice }
         * 
         * 
         */
        public List<StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice> getStudentSubjectChoice() {
            if (studentSubjectChoice == null) {
                studentSubjectChoice = new ArrayList<StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice>();
            }
            return this.studentSubjectChoice;
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
         *         &lt;element name="StudyDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
         *         &lt;element name="OtherSchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
            "studyDescription",
            "otherSchoolLocalId"
        })
        public static class StudentSubjectChoice {

            @XmlElementRef(name = "PreferenceNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Long> preferenceNumber;
            @XmlElement(name = "SubjectLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String subjectLocalId;
            @XmlElementRef(name = "StudyDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SubjectAreaType> studyDescription;
            @XmlElementRef(name = "OtherSchoolLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> otherSchoolLocalId;

            /**
             * Gets the value of the preferenceNumber property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public JAXBElement<Long> getPreferenceNumber() {
                return preferenceNumber;
            }

            /**
             * Sets the value of the preferenceNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public void setPreferenceNumber(JAXBElement<Long> value) {
                this.preferenceNumber = value;
            }

            /**
             * Gets the value of the subjectLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubjectLocalId() {
                return subjectLocalId;
            }

            /**
             * Sets the value of the subjectLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubjectLocalId(String value) {
                this.subjectLocalId = value;
            }

            /**
             * Gets the value of the studyDescription property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
             *     
             */
            public JAXBElement<SubjectAreaType> getStudyDescription() {
                return studyDescription;
            }

            /**
             * Sets the value of the studyDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
             *     
             */
            public void setStudyDescription(JAXBElement<SubjectAreaType> value) {
                this.studyDescription = value;
            }

            /**
             * Gets the value of the otherSchoolLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getOtherSchoolLocalId() {
                return otherSchoolLocalId;
            }

            /**
             * Sets the value of the otherSchoolLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setOtherSchoolLocalId(JAXBElement<String> value) {
                this.otherSchoolLocalId = value;
            }

        }

    }

}
