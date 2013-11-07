
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This object contains information pertaining to student eligibility for and participation in an individualized special program such as special education, ESL, etc.
 * 
 * <p>Java class for StudentParticipationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentParticipationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentPersonalRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="StudentParticipationAsOfDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ProgramType" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsStudentFamilyProgramTypeType" minOccurs="0"/>
 *         &lt;element name="ProgramFundingSources" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ProgramFundingSource" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgramFundingSourceCodeType" minOccurs="0"/>
 *                             &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
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
 *         &lt;element name="ManagingSchool" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="SchoolInfo"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ReferralDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ReferralSource" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSets0792IdentificationProcedureType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ProgramStatus" type="{http://www.SIFinfo.org/au/datamodel/1.3}ProgramStatusType" minOccurs="0"/>
 *         &lt;element name="GiftedEligibilityCriteria" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="EvaluationParentalConsentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EvaluationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EvaluationExtensionDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ExtensionComments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReevaluationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ProgramEligibilityDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ProgramPlanDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ProgramPlanEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="NOREPDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="PlacementParentalConsentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ProgramPlacementDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ExtendedSchoolYear" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ExtendedDay" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ProgramAvailability" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSets0211ProgramAvailabilityType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EntryPerson" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="StudentSpecialEducationFTE" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="1"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ParticipationContact" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
@XmlType(name = "StudentParticipationType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "studentPersonalRefId",
    "studentParticipationAsOfDate",
    "programType",
    "programFundingSources",
    "managingSchool",
    "referralDate",
    "referralSource",
    "programStatus",
    "giftedEligibilityCriteria",
    "evaluationParentalConsentDate",
    "evaluationDate",
    "evaluationExtensionDate",
    "extensionComments",
    "reevaluationDate",
    "programEligibilityDate",
    "programPlanDate",
    "programPlanEffectiveDate",
    "norepDate",
    "placementParentalConsentDate",
    "programPlacementDate",
    "extendedSchoolYear",
    "extendedDay",
    "programAvailability",
    "entryPerson",
    "studentSpecialEducationFTE",
    "participationContact",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentParticipationType {

    @XmlElement(name = "StudentPersonalRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;
    @XmlElement(name = "StudentParticipationAsOfDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar studentParticipationAsOfDate;
    @XmlElementRef(name = "ProgramType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> programType;
    @XmlElementRef(name = "ProgramFundingSources", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentParticipationType.ProgramFundingSources> programFundingSources;
    @XmlElement(name = "ManagingSchool", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected StudentParticipationType.ManagingSchool managingSchool;
    @XmlElementRef(name = "ReferralDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> referralDate;
    @XmlElementRef(name = "ReferralSource", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentParticipationType.ReferralSource> referralSource;
    @XmlElementRef(name = "ProgramStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ProgramStatusType> programStatus;
    @XmlElementRef(name = "GiftedEligibilityCriteria", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> giftedEligibilityCriteria;
    @XmlElementRef(name = "EvaluationParentalConsentDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> evaluationParentalConsentDate;
    @XmlElementRef(name = "EvaluationDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> evaluationDate;
    @XmlElementRef(name = "EvaluationExtensionDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> evaluationExtensionDate;
    @XmlElementRef(name = "ExtensionComments", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extensionComments;
    @XmlElementRef(name = "ReevaluationDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> reevaluationDate;
    @XmlElementRef(name = "ProgramEligibilityDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> programEligibilityDate;
    @XmlElementRef(name = "ProgramPlanDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> programPlanDate;
    @XmlElementRef(name = "ProgramPlanEffectiveDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> programPlanEffectiveDate;
    @XmlElementRef(name = "NOREPDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> norepDate;
    @XmlElementRef(name = "PlacementParentalConsentDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> placementParentalConsentDate;
    @XmlElementRef(name = "ProgramPlacementDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> programPlacementDate;
    @XmlElementRef(name = "ExtendedSchoolYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> extendedSchoolYear;
    @XmlElementRef(name = "ExtendedDay", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> extendedDay;
    @XmlElementRef(name = "ProgramAvailability", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentParticipationType.ProgramAvailability> programAvailability;
    @XmlElementRef(name = "EntryPerson", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> entryPerson;
    @XmlElementRef(name = "StudentSpecialEducationFTE", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> studentSpecialEducationFTE;
    @XmlElementRef(name = "ParticipationContact", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> participationContact;
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
     * Gets the value of the studentParticipationAsOfDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStudentParticipationAsOfDate() {
        return studentParticipationAsOfDate;
    }

    /**
     * Sets the value of the studentParticipationAsOfDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStudentParticipationAsOfDate(XMLGregorianCalendar value) {
        this.studentParticipationAsOfDate = value;
    }

    /**
     * Gets the value of the programType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProgramType() {
        return programType;
    }

    /**
     * Sets the value of the programType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProgramType(JAXBElement<String> value) {
        this.programType = value;
    }

    /**
     * Gets the value of the programFundingSources property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentParticipationType.ProgramFundingSources }{@code >}
     *     
     */
    public JAXBElement<StudentParticipationType.ProgramFundingSources> getProgramFundingSources() {
        return programFundingSources;
    }

    /**
     * Sets the value of the programFundingSources property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentParticipationType.ProgramFundingSources }{@code >}
     *     
     */
    public void setProgramFundingSources(JAXBElement<StudentParticipationType.ProgramFundingSources> value) {
        this.programFundingSources = value;
    }

    /**
     * Gets the value of the managingSchool property.
     * 
     * @return
     *     possible object is
     *     {@link StudentParticipationType.ManagingSchool }
     *     
     */
    public StudentParticipationType.ManagingSchool getManagingSchool() {
        return managingSchool;
    }

    /**
     * Sets the value of the managingSchool property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentParticipationType.ManagingSchool }
     *     
     */
    public void setManagingSchool(StudentParticipationType.ManagingSchool value) {
        this.managingSchool = value;
    }

    /**
     * Gets the value of the referralDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getReferralDate() {
        return referralDate;
    }

    /**
     * Sets the value of the referralDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setReferralDate(JAXBElement<XMLGregorianCalendar> value) {
        this.referralDate = value;
    }

    /**
     * Gets the value of the referralSource property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentParticipationType.ReferralSource }{@code >}
     *     
     */
    public JAXBElement<StudentParticipationType.ReferralSource> getReferralSource() {
        return referralSource;
    }

    /**
     * Sets the value of the referralSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentParticipationType.ReferralSource }{@code >}
     *     
     */
    public void setReferralSource(JAXBElement<StudentParticipationType.ReferralSource> value) {
        this.referralSource = value;
    }

    /**
     * Gets the value of the programStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ProgramStatusType }{@code >}
     *     
     */
    public JAXBElement<ProgramStatusType> getProgramStatus() {
        return programStatus;
    }

    /**
     * Sets the value of the programStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ProgramStatusType }{@code >}
     *     
     */
    public void setProgramStatus(JAXBElement<ProgramStatusType> value) {
        this.programStatus = value;
    }

    /**
     * Gets the value of the giftedEligibilityCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getGiftedEligibilityCriteria() {
        return giftedEligibilityCriteria;
    }

    /**
     * Sets the value of the giftedEligibilityCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setGiftedEligibilityCriteria(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.giftedEligibilityCriteria = value;
    }

    /**
     * Gets the value of the evaluationParentalConsentDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEvaluationParentalConsentDate() {
        return evaluationParentalConsentDate;
    }

    /**
     * Sets the value of the evaluationParentalConsentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEvaluationParentalConsentDate(JAXBElement<XMLGregorianCalendar> value) {
        this.evaluationParentalConsentDate = value;
    }

    /**
     * Gets the value of the evaluationDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEvaluationDate() {
        return evaluationDate;
    }

    /**
     * Sets the value of the evaluationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEvaluationDate(JAXBElement<XMLGregorianCalendar> value) {
        this.evaluationDate = value;
    }

    /**
     * Gets the value of the evaluationExtensionDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEvaluationExtensionDate() {
        return evaluationExtensionDate;
    }

    /**
     * Sets the value of the evaluationExtensionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEvaluationExtensionDate(JAXBElement<XMLGregorianCalendar> value) {
        this.evaluationExtensionDate = value;
    }

    /**
     * Gets the value of the extensionComments property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtensionComments() {
        return extensionComments;
    }

    /**
     * Sets the value of the extensionComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtensionComments(JAXBElement<String> value) {
        this.extensionComments = value;
    }

    /**
     * Gets the value of the reevaluationDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getReevaluationDate() {
        return reevaluationDate;
    }

    /**
     * Sets the value of the reevaluationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setReevaluationDate(JAXBElement<XMLGregorianCalendar> value) {
        this.reevaluationDate = value;
    }

    /**
     * Gets the value of the programEligibilityDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getProgramEligibilityDate() {
        return programEligibilityDate;
    }

    /**
     * Sets the value of the programEligibilityDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setProgramEligibilityDate(JAXBElement<XMLGregorianCalendar> value) {
        this.programEligibilityDate = value;
    }

    /**
     * Gets the value of the programPlanDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getProgramPlanDate() {
        return programPlanDate;
    }

    /**
     * Sets the value of the programPlanDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setProgramPlanDate(JAXBElement<XMLGregorianCalendar> value) {
        this.programPlanDate = value;
    }

    /**
     * Gets the value of the programPlanEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getProgramPlanEffectiveDate() {
        return programPlanEffectiveDate;
    }

    /**
     * Sets the value of the programPlanEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setProgramPlanEffectiveDate(JAXBElement<XMLGregorianCalendar> value) {
        this.programPlanEffectiveDate = value;
    }

    /**
     * Gets the value of the norepDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getNOREPDate() {
        return norepDate;
    }

    /**
     * Sets the value of the norepDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setNOREPDate(JAXBElement<XMLGregorianCalendar> value) {
        this.norepDate = value;
    }

    /**
     * Gets the value of the placementParentalConsentDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getPlacementParentalConsentDate() {
        return placementParentalConsentDate;
    }

    /**
     * Sets the value of the placementParentalConsentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setPlacementParentalConsentDate(JAXBElement<XMLGregorianCalendar> value) {
        this.placementParentalConsentDate = value;
    }

    /**
     * Gets the value of the programPlacementDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getProgramPlacementDate() {
        return programPlacementDate;
    }

    /**
     * Sets the value of the programPlacementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setProgramPlacementDate(JAXBElement<XMLGregorianCalendar> value) {
        this.programPlacementDate = value;
    }

    /**
     * Gets the value of the extendedSchoolYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getExtendedSchoolYear() {
        return extendedSchoolYear;
    }

    /**
     * Sets the value of the extendedSchoolYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setExtendedSchoolYear(JAXBElement<Boolean> value) {
        this.extendedSchoolYear = value;
    }

    /**
     * Gets the value of the extendedDay property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getExtendedDay() {
        return extendedDay;
    }

    /**
     * Sets the value of the extendedDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setExtendedDay(JAXBElement<Boolean> value) {
        this.extendedDay = value;
    }

    /**
     * Gets the value of the programAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentParticipationType.ProgramAvailability }{@code >}
     *     
     */
    public JAXBElement<StudentParticipationType.ProgramAvailability> getProgramAvailability() {
        return programAvailability;
    }

    /**
     * Sets the value of the programAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentParticipationType.ProgramAvailability }{@code >}
     *     
     */
    public void setProgramAvailability(JAXBElement<StudentParticipationType.ProgramAvailability> value) {
        this.programAvailability = value;
    }

    /**
     * Gets the value of the entryPerson property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntryPerson() {
        return entryPerson;
    }

    /**
     * Sets the value of the entryPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntryPerson(JAXBElement<String> value) {
        this.entryPerson = value;
    }

    /**
     * Gets the value of the studentSpecialEducationFTE property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getStudentSpecialEducationFTE() {
        return studentSpecialEducationFTE;
    }

    /**
     * Sets the value of the studentSpecialEducationFTE property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setStudentSpecialEducationFTE(JAXBElement<BigDecimal> value) {
        this.studentSpecialEducationFTE = value;
    }

    /**
     * Gets the value of the participationContact property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParticipationContact() {
        return participationContact;
    }

    /**
     * Sets the value of the participationContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParticipationContact(JAXBElement<String> value) {
        this.participationContact = value;
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
     * The GUID of the school responsible for coordinating the student's program participation.
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
    public static class ManagingSchool {

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
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSets0211ProgramAvailabilityType" minOccurs="0"/>
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
    public static class ProgramAvailability {

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
     *         &lt;element name="ProgramFundingSource" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgramFundingSourceCodeType" minOccurs="0"/>
     *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
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
        "programFundingSource"
    })
    public static class ProgramFundingSources {

        @XmlElement(name = "ProgramFundingSource", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<StudentParticipationType.ProgramFundingSources.ProgramFundingSource> programFundingSource;

        /**
         * Gets the value of the programFundingSource property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the programFundingSource property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProgramFundingSource().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentParticipationType.ProgramFundingSources.ProgramFundingSource }
         * 
         * 
         */
        public List<StudentParticipationType.ProgramFundingSources.ProgramFundingSource> getProgramFundingSource() {
            if (programFundingSource == null) {
                programFundingSource = new ArrayList<StudentParticipationType.ProgramFundingSources.ProgramFundingSource>();
            }
            return this.programFundingSource;
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
         *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsProgramFundingSourceCodeType" minOccurs="0"/>
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
        public static class ProgramFundingSource {

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
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSets0792IdentificationProcedureType" minOccurs="0"/>
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
    public static class ReferralSource {

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

}
