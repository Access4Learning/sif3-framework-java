
package sif.dd.au30.model;

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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         This object has been developed to cater for the SDTN – Student Data Transfer Note.  http://www.mceecdya.edu.au/verve/_resources/ISDTN_Form_3_Transfer_Note_Oct_06.pdf
 *         The elements in this object have been developed to support the LIMF recommendations and allow two or more jurisdictions to exchange this data. http://www.aictec.edu.au/aictec/go/home/about/pid/289
 *       
 * 
 * <p>Java class for StudentSDTNType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentSDTNType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SnapDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="StudentPersonalRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}PersonInfo" minOccurs="0"/>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="AddressList" type="{http://www.SIFinfo.org/au/datamodel/1.3}AddressListType" minOccurs="0"/>
 *         &lt;element name="PrincipalInfo" type="{http://www.SIFinfo.org/au/datamodel/1.3}PrincipalInfoType" minOccurs="0"/>
 *         &lt;element name="FurtherInformation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ContactName" type="{http://www.SIFinfo.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
 *                   &lt;element name="PhoneNumberList" type="{http://www.SIFinfo.org/au/datamodel/1.3}PhoneNumberListType" minOccurs="0"/>
 *                   &lt;element name="EmailList" type="{http://www.SIFinfo.org/au/datamodel/1.3}EmailListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EnrollmentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="DepartureDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="YearLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *         &lt;element name="ReasonForLeaving" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="PreviousSchoolsList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PreviousSchool" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="ReasonLeft" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="AttendanceConcerns" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="HealthNeeds" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="AreasOfInterestList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ActivityInfo" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="StudentActivityInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                             &lt;element name="StudentActivityDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="NegotiatedCurriculumPlan" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="AdjustedEducationProgram" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="CareerGuidanceFileHeld" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="SchoolCounsellorFileHeld" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="OtherLearningSupport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcceleratedProgram" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="YoungCarersRole" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="Literacy" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgressLevelType" minOccurs="0"/>
 *         &lt;element name="Numeracy" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgressLevelType" minOccurs="0"/>
 *         &lt;element name="OtherLearningAreasList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OtherLearningArea" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="Result" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgressLevelType" minOccurs="0"/>
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
 *         &lt;element name="LatestStudentReportAvailable" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="PastoralCare" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DisciplinaryAbsences" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                   &lt;element name="IndividualBehaviourPlan" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
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
@XmlType(name = "StudentSDTNType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "snapDate",
    "studentPersonalRefId",
    "personInfo",
    "schoolInfoRefId",
    "schoolLocalId",
    "schoolName",
    "addressList",
    "principalInfo",
    "furtherInformation",
    "enrollmentDate",
    "departureDate",
    "yearLevel",
    "reasonForLeaving",
    "previousSchoolsList",
    "attendanceConcerns",
    "healthNeeds",
    "areasOfInterestList",
    "negotiatedCurriculumPlan",
    "adjustedEducationProgram",
    "careerGuidanceFileHeld",
    "schoolCounsellorFileHeld",
    "otherLearningSupport",
    "acceleratedProgram",
    "youngCarersRole",
    "literacy",
    "numeracy",
    "otherLearningAreasList",
    "latestStudentReportAvailable",
    "pastoralCare",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentSDTNType {

    @XmlElement(name = "SnapDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar snapDate;
    @XmlElementRef(name = "StudentPersonalRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> studentPersonalRefId;
    @XmlElement(name = "PersonInfo", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected PersonInfoType personInfo;
    @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolInfoRefId;
    @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolLocalId;
    @XmlElement(name = "SchoolName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String schoolName;
    @XmlElementRef(name = "AddressList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressListType> addressList;
    @XmlElementRef(name = "PrincipalInfo", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<PrincipalInfoType> principalInfo;
    @XmlElementRef(name = "FurtherInformation", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSDTNType.FurtherInformation> furtherInformation;
    @XmlElementRef(name = "EnrollmentDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> enrollmentDate;
    @XmlElementRef(name = "DepartureDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> departureDate;
    @XmlElementRef(name = "YearLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelType> yearLevel;
    @XmlElementRef(name = "ReasonForLeaving", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> reasonForLeaving;
    @XmlElementRef(name = "PreviousSchoolsList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSDTNType.PreviousSchoolsList> previousSchoolsList;
    @XmlElementRef(name = "AttendanceConcerns", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> attendanceConcerns;
    @XmlElementRef(name = "HealthNeeds", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> healthNeeds;
    @XmlElementRef(name = "AreasOfInterestList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSDTNType.AreasOfInterestList> areasOfInterestList;
    @XmlElementRef(name = "NegotiatedCurriculumPlan", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> negotiatedCurriculumPlan;
    @XmlElementRef(name = "AdjustedEducationProgram", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> adjustedEducationProgram;
    @XmlElementRef(name = "CareerGuidanceFileHeld", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> careerGuidanceFileHeld;
    @XmlElementRef(name = "SchoolCounsellorFileHeld", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> schoolCounsellorFileHeld;
    @XmlElementRef(name = "OtherLearningSupport", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> otherLearningSupport;
    @XmlElementRef(name = "AcceleratedProgram", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> acceleratedProgram;
    @XmlElementRef(name = "YoungCarersRole", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> youngCarersRole;
    @XmlElementRef(name = "Literacy", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsProgressLevelType> literacy;
    @XmlElementRef(name = "Numeracy", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsProgressLevelType> numeracy;
    @XmlElementRef(name = "OtherLearningAreasList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSDTNType.OtherLearningAreasList> otherLearningAreasList;
    @XmlElementRef(name = "LatestStudentReportAvailable", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> latestStudentReportAvailable;
    @XmlElementRef(name = "PastoralCare", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSDTNType.PastoralCare> pastoralCare;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the snapDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSnapDate() {
        return snapDate;
    }

    /**
     * Sets the value of the snapDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSnapDate(XMLGregorianCalendar value) {
        this.snapDate = value;
    }

    /**
     * Gets the value of the studentPersonalRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStudentPersonalRefId() {
        return studentPersonalRefId;
    }

    /**
     * Sets the value of the studentPersonalRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStudentPersonalRefId(JAXBElement<String> value) {
        this.studentPersonalRefId = value;
    }

    /**
     * Gets the value of the personInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PersonInfoType }
     *     
     */
    public PersonInfoType getPersonInfo() {
        return personInfo;
    }

    /**
     * Sets the value of the personInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonInfoType }
     *     
     */
    public void setPersonInfo(PersonInfoType value) {
        this.personInfo = value;
    }

    /**
     * Gets the value of the schoolInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolInfoRefId() {
        return schoolInfoRefId;
    }

    /**
     * Sets the value of the schoolInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolInfoRefId(JAXBElement<String> value) {
        this.schoolInfoRefId = value;
    }

    /**
     * Gets the value of the schoolLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolLocalId() {
        return schoolLocalId;
    }

    /**
     * Sets the value of the schoolLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolLocalId(JAXBElement<String> value) {
        this.schoolLocalId = value;
    }

    /**
     * Gets the value of the schoolName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Sets the value of the schoolName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolName(String value) {
        this.schoolName = value;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public JAXBElement<AddressListType> getAddressList() {
        return addressList;
    }

    /**
     * Sets the value of the addressList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public void setAddressList(JAXBElement<AddressListType> value) {
        this.addressList = value;
    }

    /**
     * Gets the value of the principalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PrincipalInfoType }{@code >}
     *     
     */
    public JAXBElement<PrincipalInfoType> getPrincipalInfo() {
        return principalInfo;
    }

    /**
     * Sets the value of the principalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PrincipalInfoType }{@code >}
     *     
     */
    public void setPrincipalInfo(JAXBElement<PrincipalInfoType> value) {
        this.principalInfo = value;
    }

    /**
     * Gets the value of the furtherInformation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.FurtherInformation }{@code >}
     *     
     */
    public JAXBElement<StudentSDTNType.FurtherInformation> getFurtherInformation() {
        return furtherInformation;
    }

    /**
     * Sets the value of the furtherInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.FurtherInformation }{@code >}
     *     
     */
    public void setFurtherInformation(JAXBElement<StudentSDTNType.FurtherInformation> value) {
        this.furtherInformation = value;
    }

    /**
     * Gets the value of the enrollmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * Sets the value of the enrollmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEnrollmentDate(JAXBElement<XMLGregorianCalendar> value) {
        this.enrollmentDate = value;
    }

    /**
     * Gets the value of the departureDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the value of the departureDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setDepartureDate(JAXBElement<XMLGregorianCalendar> value) {
        this.departureDate = value;
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
     * Gets the value of the reasonForLeaving property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReasonForLeaving() {
        return reasonForLeaving;
    }

    /**
     * Sets the value of the reasonForLeaving property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReasonForLeaving(JAXBElement<String> value) {
        this.reasonForLeaving = value;
    }

    /**
     * Gets the value of the previousSchoolsList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.PreviousSchoolsList }{@code >}
     *     
     */
    public JAXBElement<StudentSDTNType.PreviousSchoolsList> getPreviousSchoolsList() {
        return previousSchoolsList;
    }

    /**
     * Sets the value of the previousSchoolsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.PreviousSchoolsList }{@code >}
     *     
     */
    public void setPreviousSchoolsList(JAXBElement<StudentSDTNType.PreviousSchoolsList> value) {
        this.previousSchoolsList = value;
    }

    /**
     * Gets the value of the attendanceConcerns property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getAttendanceConcerns() {
        return attendanceConcerns;
    }

    /**
     * Sets the value of the attendanceConcerns property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setAttendanceConcerns(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.attendanceConcerns = value;
    }

    /**
     * Gets the value of the healthNeeds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getHealthNeeds() {
        return healthNeeds;
    }

    /**
     * Sets the value of the healthNeeds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setHealthNeeds(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.healthNeeds = value;
    }

    /**
     * Gets the value of the areasOfInterestList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.AreasOfInterestList }{@code >}
     *     
     */
    public JAXBElement<StudentSDTNType.AreasOfInterestList> getAreasOfInterestList() {
        return areasOfInterestList;
    }

    /**
     * Sets the value of the areasOfInterestList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.AreasOfInterestList }{@code >}
     *     
     */
    public void setAreasOfInterestList(JAXBElement<StudentSDTNType.AreasOfInterestList> value) {
        this.areasOfInterestList = value;
    }

    /**
     * Gets the value of the negotiatedCurriculumPlan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getNegotiatedCurriculumPlan() {
        return negotiatedCurriculumPlan;
    }

    /**
     * Sets the value of the negotiatedCurriculumPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setNegotiatedCurriculumPlan(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.negotiatedCurriculumPlan = value;
    }

    /**
     * Gets the value of the adjustedEducationProgram property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getAdjustedEducationProgram() {
        return adjustedEducationProgram;
    }

    /**
     * Sets the value of the adjustedEducationProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setAdjustedEducationProgram(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.adjustedEducationProgram = value;
    }

    /**
     * Gets the value of the careerGuidanceFileHeld property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getCareerGuidanceFileHeld() {
        return careerGuidanceFileHeld;
    }

    /**
     * Sets the value of the careerGuidanceFileHeld property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setCareerGuidanceFileHeld(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.careerGuidanceFileHeld = value;
    }

    /**
     * Gets the value of the schoolCounsellorFileHeld property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getSchoolCounsellorFileHeld() {
        return schoolCounsellorFileHeld;
    }

    /**
     * Sets the value of the schoolCounsellorFileHeld property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setSchoolCounsellorFileHeld(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.schoolCounsellorFileHeld = value;
    }

    /**
     * Gets the value of the otherLearningSupport property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOtherLearningSupport() {
        return otherLearningSupport;
    }

    /**
     * Sets the value of the otherLearningSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOtherLearningSupport(JAXBElement<String> value) {
        this.otherLearningSupport = value;
    }

    /**
     * Gets the value of the acceleratedProgram property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getAcceleratedProgram() {
        return acceleratedProgram;
    }

    /**
     * Sets the value of the acceleratedProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setAcceleratedProgram(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.acceleratedProgram = value;
    }

    /**
     * Gets the value of the youngCarersRole property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getYoungCarersRole() {
        return youngCarersRole;
    }

    /**
     * Sets the value of the youngCarersRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setYoungCarersRole(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.youngCarersRole = value;
    }

    /**
     * Gets the value of the literacy property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsProgressLevelType> getLiteracy() {
        return literacy;
    }

    /**
     * Sets the value of the literacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}
     *     
     */
    public void setLiteracy(JAXBElement<AUCodeSetsProgressLevelType> value) {
        this.literacy = value;
    }

    /**
     * Gets the value of the numeracy property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsProgressLevelType> getNumeracy() {
        return numeracy;
    }

    /**
     * Sets the value of the numeracy property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}
     *     
     */
    public void setNumeracy(JAXBElement<AUCodeSetsProgressLevelType> value) {
        this.numeracy = value;
    }

    /**
     * Gets the value of the otherLearningAreasList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.OtherLearningAreasList }{@code >}
     *     
     */
    public JAXBElement<StudentSDTNType.OtherLearningAreasList> getOtherLearningAreasList() {
        return otherLearningAreasList;
    }

    /**
     * Sets the value of the otherLearningAreasList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.OtherLearningAreasList }{@code >}
     *     
     */
    public void setOtherLearningAreasList(JAXBElement<StudentSDTNType.OtherLearningAreasList> value) {
        this.otherLearningAreasList = value;
    }

    /**
     * Gets the value of the latestStudentReportAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getLatestStudentReportAvailable() {
        return latestStudentReportAvailable;
    }

    /**
     * Sets the value of the latestStudentReportAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setLatestStudentReportAvailable(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.latestStudentReportAvailable = value;
    }

    /**
     * Gets the value of the pastoralCare property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.PastoralCare }{@code >}
     *     
     */
    public JAXBElement<StudentSDTNType.PastoralCare> getPastoralCare() {
        return pastoralCare;
    }

    /**
     * Sets the value of the pastoralCare property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSDTNType.PastoralCare }{@code >}
     *     
     */
    public void setPastoralCare(JAXBElement<StudentSDTNType.PastoralCare> value) {
        this.pastoralCare = value;
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
     *         &lt;element name="ActivityInfo" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="StudentActivityInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                   &lt;element name="StudentActivityDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "activityInfo"
    })
    public static class AreasOfInterestList {

        @XmlElement(name = "ActivityInfo", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<StudentSDTNType.AreasOfInterestList.ActivityInfo> activityInfo;

        /**
         * Gets the value of the activityInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the activityInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActivityInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentSDTNType.AreasOfInterestList.ActivityInfo }
         * 
         * 
         */
        public List<StudentSDTNType.AreasOfInterestList.ActivityInfo> getActivityInfo() {
            if (activityInfo == null) {
                activityInfo = new ArrayList<StudentSDTNType.AreasOfInterestList.ActivityInfo>();
            }
            return this.activityInfo;
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
         *         &lt;element name="StudentActivityInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *         &lt;element name="StudentActivityDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "studentActivityInfoRefId",
            "studentActivityDescription"
        })
        public static class ActivityInfo {

            @XmlElementRef(name = "StudentActivityInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> studentActivityInfoRefId;
            @XmlElement(name = "StudentActivityDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String studentActivityDescription;

            /**
             * Gets the value of the studentActivityInfoRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getStudentActivityInfoRefId() {
                return studentActivityInfoRefId;
            }

            /**
             * Sets the value of the studentActivityInfoRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setStudentActivityInfoRefId(JAXBElement<String> value) {
                this.studentActivityInfoRefId = value;
            }

            /**
             * Gets the value of the studentActivityDescription property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStudentActivityDescription() {
                return studentActivityDescription;
            }

            /**
             * Sets the value of the studentActivityDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStudentActivityDescription(String value) {
                this.studentActivityDescription = value;
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
     *         &lt;element name="ContactName" type="{http://www.SIFinfo.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
     *         &lt;element name="PhoneNumberList" type="{http://www.SIFinfo.org/au/datamodel/1.3}PhoneNumberListType" minOccurs="0"/>
     *         &lt;element name="EmailList" type="{http://www.SIFinfo.org/au/datamodel/1.3}EmailListType" minOccurs="0"/>
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
        "contactName",
        "phoneNumberList",
        "emailList"
    })
    public static class FurtherInformation {

        @XmlElementRef(name = "ContactName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<NameOfRecordType> contactName;
        @XmlElementRef(name = "PhoneNumberList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<PhoneNumberListType> phoneNumberList;
        @XmlElementRef(name = "EmailList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<EmailListType> emailList;

        /**
         * Gets the value of the contactName property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link NameOfRecordType }{@code >}
         *     
         */
        public JAXBElement<NameOfRecordType> getContactName() {
            return contactName;
        }

        /**
         * Sets the value of the contactName property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link NameOfRecordType }{@code >}
         *     
         */
        public void setContactName(JAXBElement<NameOfRecordType> value) {
            this.contactName = value;
        }

        /**
         * Gets the value of the phoneNumberList property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}
         *     
         */
        public JAXBElement<PhoneNumberListType> getPhoneNumberList() {
            return phoneNumberList;
        }

        /**
         * Sets the value of the phoneNumberList property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}
         *     
         */
        public void setPhoneNumberList(JAXBElement<PhoneNumberListType> value) {
            this.phoneNumberList = value;
        }

        /**
         * Gets the value of the emailList property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link EmailListType }{@code >}
         *     
         */
        public JAXBElement<EmailListType> getEmailList() {
            return emailList;
        }

        /**
         * Sets the value of the emailList property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link EmailListType }{@code >}
         *     
         */
        public void setEmailList(JAXBElement<EmailListType> value) {
            this.emailList = value;
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
     *         &lt;element name="OtherLearningArea" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="Result" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgressLevelType" minOccurs="0"/>
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
        "otherLearningArea"
    })
    public static class OtherLearningAreasList {

        @XmlElement(name = "OtherLearningArea", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<StudentSDTNType.OtherLearningAreasList.OtherLearningArea> otherLearningArea;

        /**
         * Gets the value of the otherLearningArea property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherLearningArea property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtherLearningArea().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentSDTNType.OtherLearningAreasList.OtherLearningArea }
         * 
         * 
         */
        public List<StudentSDTNType.OtherLearningAreasList.OtherLearningArea> getOtherLearningArea() {
            if (otherLearningArea == null) {
                otherLearningArea = new ArrayList<StudentSDTNType.OtherLearningAreasList.OtherLearningArea>();
            }
            return this.otherLearningArea;
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
         *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="Result" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgressLevelType" minOccurs="0"/>
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
            "description",
            "result"
        })
        public static class OtherLearningArea {

            @XmlElement(name = "Description", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String description;
            @XmlElementRef(name = "Result", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<AUCodeSetsProgressLevelType> result;

            /**
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the result property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}
             *     
             */
            public JAXBElement<AUCodeSetsProgressLevelType> getResult() {
                return result;
            }

            /**
             * Sets the value of the result property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}
             *     
             */
            public void setResult(JAXBElement<AUCodeSetsProgressLevelType> value) {
                this.result = value;
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
     *         &lt;element name="DisciplinaryAbsences" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
     *         &lt;element name="IndividualBehaviourPlan" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
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
        "disciplinaryAbsences",
        "individualBehaviourPlan"
    })
    public static class PastoralCare {

        @XmlElementRef(name = "DisciplinaryAbsences", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> disciplinaryAbsences;
        @XmlElementRef(name = "IndividualBehaviourPlan", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsYesOrNoCategoryType> individualBehaviourPlan;

        /**
         * Gets the value of the disciplinaryAbsences property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getDisciplinaryAbsences() {
            return disciplinaryAbsences;
        }

        /**
         * Sets the value of the disciplinaryAbsences property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setDisciplinaryAbsences(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.disciplinaryAbsences = value;
        }

        /**
         * Gets the value of the individualBehaviourPlan property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsYesOrNoCategoryType> getIndividualBehaviourPlan() {
            return individualBehaviourPlan;
        }

        /**
         * Sets the value of the individualBehaviourPlan property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
         *     
         */
        public void setIndividualBehaviourPlan(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
            this.individualBehaviourPlan = value;
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
     *         &lt;element name="PreviousSchool" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="ReasonLeft" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "previousSchool"
    })
    public static class PreviousSchoolsList {

        @XmlElement(name = "PreviousSchool", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<StudentSDTNType.PreviousSchoolsList.PreviousSchool> previousSchool;

        /**
         * Gets the value of the previousSchool property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the previousSchool property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPreviousSchool().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentSDTNType.PreviousSchoolsList.PreviousSchool }
         * 
         * 
         */
        public List<StudentSDTNType.PreviousSchoolsList.PreviousSchool> getPreviousSchool() {
            if (previousSchool == null) {
                previousSchool = new ArrayList<StudentSDTNType.PreviousSchoolsList.PreviousSchool>();
            }
            return this.previousSchool;
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
         *         &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="ReasonLeft" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "schoolName",
            "reasonLeft"
        })
        public static class PreviousSchool {

            @XmlElement(name = "SchoolName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String schoolName;
            @XmlElementRef(name = "ReasonLeft", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> reasonLeft;

            /**
             * Gets the value of the schoolName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSchoolName() {
                return schoolName;
            }

            /**
             * Sets the value of the schoolName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSchoolName(String value) {
                this.schoolName = value;
            }

            /**
             * Gets the value of the reasonLeft property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getReasonLeft() {
                return reasonLeft;
            }

            /**
             * Sets the value of the reasonLeft property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setReasonLeft(JAXBElement<String> value) {
                this.reasonLeft = value;
            }

        }

    }

}
