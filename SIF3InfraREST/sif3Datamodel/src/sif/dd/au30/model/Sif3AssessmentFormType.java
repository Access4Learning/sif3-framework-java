
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
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Represents the unique set of questions or stimuli that can be given to a set of test takers. A physical correlate of the assessment form is the test booklet given to students during an assessment.  For an adaptive test, the form would become a container for the structure and pool of items that are available to be selected by the adaptive algorithm.  However, this object does not contain the questions, it only contains elements that describe the form for use in scoring the questions.
 * 
 * <p>Java class for Sif3AssessmentFormType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentFormType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormVersion" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="FormPublishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AssessmentType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="standard"/>
 *               &lt;enumeration value="alternate"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="FormName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="FormIdentifiers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FormIdentifier" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *                           &lt;attribute name="FormIdentifierType" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="client"/>
 *                                 &lt;enumeration value="local"/>
 *                                 &lt;enumeration value="publisher"/>
 *                                 &lt;enumeration value="internal"/>
 *                                 &lt;enumeration value="other"/>
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
 *         &lt;element name="FormAccommodations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FormAccommodation" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="braille"/>
 *                         &lt;enumeration value="text-to-speech"/>
 *                         &lt;enumeration value="audio"/>
 *                         &lt;enumeration value="signLanguage"/>
 *                         &lt;enumeration value="alternateLanguage"/>
 *                         &lt;enumeration value="simplifiedLanguage"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Level" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="Period" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="GradeLevels" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="AssessmentFormSubjects" type="{http://www.sifassociation.org/au/datamodel/1.3}SubjectAreaListType" minOccurs="0"/>
 *         &lt;element name="AssessmentFormLanguages" type="{http://www.sifassociation.org/au/datamodel/1.3}LanguageListType" minOccurs="0"/>
 *         &lt;element name="AssessmentSubTestRefIds" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentSubTestRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AssessmentSections" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentSection" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AssessmentSectionRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                             &lt;element name="AssessmentSectionSequence" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
 *         &lt;element name="AssessmentPlatforms" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentPlatform" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="paper"/>
 *                         &lt;enumeration value="computer"/>
 *                         &lt;enumeration value="mobile"/>
 *                         &lt;enumeration value="clicker"/>
 *                         &lt;enumeration value="other"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AssessmentAssetRefIds" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentAssetRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SIF_Metadata" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" />
 *       &lt;attribute name="AssessmentRefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentFormType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "formVersion",
    "formPublishDate",
    "assessmentType",
    "formName",
    "formIdentifiers",
    "formAccommodations",
    "level",
    "period",
    "gradeLevels",
    "assessmentFormSubjects",
    "assessmentFormLanguages",
    "assessmentSubTestRefIds",
    "assessmentSections",
    "assessmentPlatforms",
    "assessmentAssetRefIds",
    "sifMetadata",
    "sifExtendedElements"
})
public class Sif3AssessmentFormType {

    @XmlElementRef(name = "FormVersion", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> formVersion;
    @XmlElementRef(name = "FormPublishDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> formPublishDate;
    @XmlElementRef(name = "AssessmentType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentType;
    @XmlElement(name = "FormName", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String formName;
    @XmlElementRef(name = "FormIdentifiers", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentFormType.FormIdentifiers> formIdentifiers;
    @XmlElementRef(name = "FormAccommodations", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentFormType.FormAccommodations> formAccommodations;
    @XmlElementRef(name = "Level", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> level;
    @XmlElementRef(name = "Period", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> period;
    @XmlElementRef(name = "GradeLevels", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> gradeLevels;
    @XmlElementRef(name = "AssessmentFormSubjects", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SubjectAreaListType> assessmentFormSubjects;
    @XmlElementRef(name = "AssessmentFormLanguages", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LanguageListType> assessmentFormLanguages;
    @XmlElementRef(name = "AssessmentSubTestRefIds", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentFormType.AssessmentSubTestRefIds> assessmentSubTestRefIds;
    @XmlElementRef(name = "AssessmentSections", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentFormType.AssessmentSections> assessmentSections;
    @XmlElementRef(name = "AssessmentPlatforms", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentFormType.AssessmentPlatforms> assessmentPlatforms;
    @XmlElementRef(name = "AssessmentAssetRefIds", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentFormType.AssessmentAssetRefIds> assessmentAssetRefIds;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;
    @XmlAttribute(name = "AssessmentRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String assessmentRefId;

    /**
     * Gets the value of the formVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFormVersion() {
        return formVersion;
    }

    /**
     * Sets the value of the formVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFormVersion(JAXBElement<String> value) {
        this.formVersion = value;
    }

    /**
     * Gets the value of the formPublishDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFormPublishDate() {
        return formPublishDate;
    }

    /**
     * Sets the value of the formPublishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFormPublishDate(JAXBElement<XMLGregorianCalendar> value) {
        this.formPublishDate = value;
    }

    /**
     * Gets the value of the assessmentType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentType() {
        return assessmentType;
    }

    /**
     * Sets the value of the assessmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentType(JAXBElement<String> value) {
        this.assessmentType = value;
    }

    /**
     * Gets the value of the formName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormName() {
        return formName;
    }

    /**
     * Sets the value of the formName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormName(String value) {
        this.formName = value;
    }

    /**
     * Gets the value of the formIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.FormIdentifiers }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentFormType.FormIdentifiers> getFormIdentifiers() {
        return formIdentifiers;
    }

    /**
     * Sets the value of the formIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.FormIdentifiers }{@code >}
     *     
     */
    public void setFormIdentifiers(JAXBElement<Sif3AssessmentFormType.FormIdentifiers> value) {
        this.formIdentifiers = value;
    }

    /**
     * Gets the value of the formAccommodations property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.FormAccommodations }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentFormType.FormAccommodations> getFormAccommodations() {
        return formAccommodations;
    }

    /**
     * Sets the value of the formAccommodations property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.FormAccommodations }{@code >}
     *     
     */
    public void setFormAccommodations(JAXBElement<Sif3AssessmentFormType.FormAccommodations> value) {
        this.formAccommodations = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLevel(JAXBElement<String> value) {
        this.level = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPeriod(JAXBElement<String> value) {
        this.period = value;
    }

    /**
     * Gets the value of the gradeLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public JAXBElement<YearLevelsType> getGradeLevels() {
        return gradeLevels;
    }

    /**
     * Sets the value of the gradeLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public void setGradeLevels(JAXBElement<YearLevelsType> value) {
        this.gradeLevels = value;
    }

    /**
     * Gets the value of the assessmentFormSubjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public JAXBElement<SubjectAreaListType> getAssessmentFormSubjects() {
        return assessmentFormSubjects;
    }

    /**
     * Sets the value of the assessmentFormSubjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public void setAssessmentFormSubjects(JAXBElement<SubjectAreaListType> value) {
        this.assessmentFormSubjects = value;
    }

    /**
     * Gets the value of the assessmentFormLanguages property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}
     *     
     */
    public JAXBElement<LanguageListType> getAssessmentFormLanguages() {
        return assessmentFormLanguages;
    }

    /**
     * Sets the value of the assessmentFormLanguages property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}
     *     
     */
    public void setAssessmentFormLanguages(JAXBElement<LanguageListType> value) {
        this.assessmentFormLanguages = value;
    }

    /**
     * Gets the value of the assessmentSubTestRefIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentSubTestRefIds }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentFormType.AssessmentSubTestRefIds> getAssessmentSubTestRefIds() {
        return assessmentSubTestRefIds;
    }

    /**
     * Sets the value of the assessmentSubTestRefIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentSubTestRefIds }{@code >}
     *     
     */
    public void setAssessmentSubTestRefIds(JAXBElement<Sif3AssessmentFormType.AssessmentSubTestRefIds> value) {
        this.assessmentSubTestRefIds = value;
    }

    /**
     * Gets the value of the assessmentSections property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentSections }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentFormType.AssessmentSections> getAssessmentSections() {
        return assessmentSections;
    }

    /**
     * Sets the value of the assessmentSections property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentSections }{@code >}
     *     
     */
    public void setAssessmentSections(JAXBElement<Sif3AssessmentFormType.AssessmentSections> value) {
        this.assessmentSections = value;
    }

    /**
     * Gets the value of the assessmentPlatforms property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentPlatforms }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentFormType.AssessmentPlatforms> getAssessmentPlatforms() {
        return assessmentPlatforms;
    }

    /**
     * Sets the value of the assessmentPlatforms property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentPlatforms }{@code >}
     *     
     */
    public void setAssessmentPlatforms(JAXBElement<Sif3AssessmentFormType.AssessmentPlatforms> value) {
        this.assessmentPlatforms = value;
    }

    /**
     * Gets the value of the assessmentAssetRefIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentAssetRefIds }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentFormType.AssessmentAssetRefIds> getAssessmentAssetRefIds() {
        return assessmentAssetRefIds;
    }

    /**
     * Sets the value of the assessmentAssetRefIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentAssetRefIds }{@code >}
     *     
     */
    public void setAssessmentAssetRefIds(JAXBElement<Sif3AssessmentFormType.AssessmentAssetRefIds> value) {
        this.assessmentAssetRefIds = value;
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
     * Gets the value of the assessmentRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentRefId() {
        return assessmentRefId;
    }

    /**
     * Sets the value of the assessmentRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentRefId(String value) {
        this.assessmentRefId = value;
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
     *         &lt;element name="AssessmentAssetRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "assessmentAssetRefId"
    })
    public static class AssessmentAssetRefIds {

        @XmlElement(name = "AssessmentAssetRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> assessmentAssetRefId;

        /**
         * Gets the value of the assessmentAssetRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentAssetRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentAssetRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAssessmentAssetRefId() {
            if (assessmentAssetRefId == null) {
                assessmentAssetRefId = new ArrayList<String>();
            }
            return this.assessmentAssetRefId;
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
     *         &lt;element name="AssessmentPlatform" maxOccurs="unbounded" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="paper"/>
     *               &lt;enumeration value="computer"/>
     *               &lt;enumeration value="mobile"/>
     *               &lt;enumeration value="clicker"/>
     *               &lt;enumeration value="other"/>
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
        "assessmentPlatform"
    })
    public static class AssessmentPlatforms {

        @XmlElement(name = "AssessmentPlatform", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> assessmentPlatform;

        /**
         * Gets the value of the assessmentPlatform property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentPlatform property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentPlatform().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAssessmentPlatform() {
            if (assessmentPlatform == null) {
                assessmentPlatform = new ArrayList<String>();
            }
            return this.assessmentPlatform;
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
     *         &lt;element name="AssessmentSection" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AssessmentSectionRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                   &lt;element name="AssessmentSectionSequence" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
        "assessmentSection"
    })
    public static class AssessmentSections {

        @XmlElement(name = "AssessmentSection", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentFormType.AssessmentSections.AssessmentSection> assessmentSection;

        /**
         * Gets the value of the assessmentSection property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentSection property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentSection().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentFormType.AssessmentSections.AssessmentSection }
         * 
         * 
         */
        public List<Sif3AssessmentFormType.AssessmentSections.AssessmentSection> getAssessmentSection() {
            if (assessmentSection == null) {
                assessmentSection = new ArrayList<Sif3AssessmentFormType.AssessmentSections.AssessmentSection>();
            }
            return this.assessmentSection;
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
         *         &lt;element name="AssessmentSectionRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *         &lt;element name="AssessmentSectionSequence" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
            "assessmentSectionRefId",
            "assessmentSectionSequence"
        })
        public static class AssessmentSection {

            @XmlElement(name = "AssessmentSectionRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String assessmentSectionRefId;
            @XmlElement(name = "AssessmentSectionSequence", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String assessmentSectionSequence;

            /**
             * Gets the value of the assessmentSectionRefId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssessmentSectionRefId() {
                return assessmentSectionRefId;
            }

            /**
             * Sets the value of the assessmentSectionRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssessmentSectionRefId(String value) {
                this.assessmentSectionRefId = value;
            }

            /**
             * Gets the value of the assessmentSectionSequence property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssessmentSectionSequence() {
                return assessmentSectionSequence;
            }

            /**
             * Sets the value of the assessmentSectionSequence property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssessmentSectionSequence(String value) {
                this.assessmentSectionSequence = value;
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
     *         &lt;element name="AssessmentSubTestRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "assessmentSubTestRefId"
    })
    public static class AssessmentSubTestRefIds {

        @XmlElement(name = "AssessmentSubTestRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> assessmentSubTestRefId;

        /**
         * Gets the value of the assessmentSubTestRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentSubTestRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentSubTestRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAssessmentSubTestRefId() {
            if (assessmentSubTestRefId == null) {
                assessmentSubTestRefId = new ArrayList<String>();
            }
            return this.assessmentSubTestRefId;
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
     *         &lt;element name="FormAccommodation" maxOccurs="unbounded" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="braille"/>
     *               &lt;enumeration value="text-to-speech"/>
     *               &lt;enumeration value="audio"/>
     *               &lt;enumeration value="signLanguage"/>
     *               &lt;enumeration value="alternateLanguage"/>
     *               &lt;enumeration value="simplifiedLanguage"/>
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
        "formAccommodation"
    })
    public static class FormAccommodations {

        @XmlElement(name = "FormAccommodation", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> formAccommodation;

        /**
         * Gets the value of the formAccommodation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the formAccommodation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFormAccommodation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getFormAccommodation() {
            if (formAccommodation == null) {
                formAccommodation = new ArrayList<String>();
            }
            return this.formAccommodation;
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
     *         &lt;element name="FormIdentifier" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
     *                 &lt;attribute name="FormIdentifierType" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="client"/>
     *                       &lt;enumeration value="local"/>
     *                       &lt;enumeration value="publisher"/>
     *                       &lt;enumeration value="internal"/>
     *                       &lt;enumeration value="other"/>
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
        "formIdentifier"
    })
    public static class FormIdentifiers {

        @XmlElement(name = "FormIdentifier", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentFormType.FormIdentifiers.FormIdentifier> formIdentifier;

        /**
         * Gets the value of the formIdentifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the formIdentifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFormIdentifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentFormType.FormIdentifiers.FormIdentifier }
         * 
         * 
         */
        public List<Sif3AssessmentFormType.FormIdentifiers.FormIdentifier> getFormIdentifier() {
            if (formIdentifier == null) {
                formIdentifier = new ArrayList<Sif3AssessmentFormType.FormIdentifiers.FormIdentifier>();
            }
            return this.formIdentifier;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
         *       &lt;attribute name="FormIdentifierType" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="client"/>
         *             &lt;enumeration value="local"/>
         *             &lt;enumeration value="publisher"/>
         *             &lt;enumeration value="internal"/>
         *             &lt;enumeration value="other"/>
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
        public static class FormIdentifier {

            @XmlValue
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String value;
            @XmlAttribute(name = "FormIdentifierType", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String formIdentifierType;

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
             * Gets the value of the formIdentifierType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFormIdentifierType() {
                return formIdentifierType;
            }

            /**
             * Sets the value of the formIdentifierType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFormIdentifierType(String value) {
                this.formIdentifierType = value;
            }

        }

    }

}
