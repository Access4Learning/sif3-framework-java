
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.Calendar;
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


/**
 * 
 *         This object is designed to allow software systems to provide item detail information such as the stem of the item, the distractors, the stimuli, etc.
 *         This initial version of the object does not deal with presentation aspects of the item.
 *         Instead it focuses on the item content and characteristics needed to enable interoperability and the usage of item-level information in the improvement of learning and instruction.
 *       
 * 
 * <p>Java class for Sif3AssessmentItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemVersion" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ItemPublishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AssessmentIdentifiers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentIdentifier" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
 *                           &lt;attribute name="AssessmentIdType" use="required">
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
 *         &lt;element name="AssessmentSubjects" type="{http://www.sifassociation.org/au/datamodel/1.3}SubjectAreaListType" minOccurs="0"/>
 *         &lt;element name="AssessmentGradeLevels" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="AssessmentLanguages" type="{http://www.sifassociation.org/au/datamodel/1.3}LanguageListType" minOccurs="0"/>
 *         &lt;element name="LearningStandardItems" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LearningStandardItemRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AssessmentItemAssetRefIds" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentItemAssetRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Stem" type="{http://www.sifassociation.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
 *         &lt;element name="ResponseChoices" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Choice" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ChoiceLabel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="ChoiceContent" type="{http://www.sifassociation.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
 *                             &lt;element name="CreditValue" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *                             &lt;element name="ResponseFeedback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="ItemFeedbackCorrect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ItemFeedbackIncorrect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ItemFeedbackHint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ItemScoreMaximum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="ItemScoreMinimum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="ItemRubricIds" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ItemRubricId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AssessmentItemBanks" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentItemBank" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AssessmentItemBankId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="AssessmentItemBankName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="AssessmentItemPlatforms" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentItemPlatform" maxOccurs="unbounded" minOccurs="0">
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
 *         &lt;element name="SIF_Metadata" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" />
 *       &lt;attribute name="ResponseType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="multiple-choice"/>
 *             &lt;enumeration value="multiple-multiple choice"/>
 *             &lt;enumeration value="true-false"/>
 *             &lt;enumeration value="fill-in-the-blank"/>
 *             &lt;enumeration value="short-answer"/>
 *             &lt;enumeration value="essay"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentItemType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "itemVersion",
    "itemPublishDate",
    "itemName",
    "assessmentIdentifiers",
    "assessmentSubjects",
    "assessmentGradeLevels",
    "assessmentLanguages",
    "learningStandardItems",
    "assessmentItemAssetRefIds",
    "stem",
    "responseChoices",
    "itemFeedbackCorrect",
    "itemFeedbackIncorrect",
    "itemFeedbackHint",
    "itemScoreMaximum",
    "itemScoreMinimum",
    "itemRubricIds",
    "assessmentItemBanks",
    "assessmentItemPlatforms",
    "sifMetadata",
    "sifExtendedElements"
})
public class Sif3AssessmentItemType {

    @XmlElementRef(name = "ItemVersion", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemVersion;
    @XmlElementRef(name = "ItemPublishDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Calendar> itemPublishDate;
    @XmlElementRef(name = "ItemName", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemName;
    @XmlElementRef(name = "AssessmentIdentifiers", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentItemType.AssessmentIdentifiers> assessmentIdentifiers;
    @XmlElementRef(name = "AssessmentSubjects", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SubjectAreaListType> assessmentSubjects;
    @XmlElementRef(name = "AssessmentGradeLevels", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> assessmentGradeLevels;
    @XmlElementRef(name = "AssessmentLanguages", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LanguageListType> assessmentLanguages;
    @XmlElementRef(name = "LearningStandardItems", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentItemType.LearningStandardItems> learningStandardItems;
    @XmlElementRef(name = "AssessmentItemAssetRefIds", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentItemType.AssessmentItemAssetRefIds> assessmentItemAssetRefIds;
    @XmlElementRef(name = "Stem", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AbstractContentElementType> stem;
    @XmlElementRef(name = "ResponseChoices", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentItemType.ResponseChoices> responseChoices;
    @XmlElementRef(name = "ItemFeedbackCorrect", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemFeedbackCorrect;
    @XmlElementRef(name = "ItemFeedbackIncorrect", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemFeedbackIncorrect;
    @XmlElementRef(name = "ItemFeedbackHint", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemFeedbackHint;
    @XmlElementRef(name = "ItemScoreMaximum", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemScoreMaximum;
    @XmlElementRef(name = "ItemScoreMinimum", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemScoreMinimum;
    @XmlElementRef(name = "ItemRubricIds", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentItemType.ItemRubricIds> itemRubricIds;
    @XmlElementRef(name = "AssessmentItemBanks", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentItemType.AssessmentItemBanks> assessmentItemBanks;
    @XmlElementRef(name = "AssessmentItemPlatforms", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentItemType.AssessmentItemPlatforms> assessmentItemPlatforms;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;
    @XmlAttribute(name = "ResponseType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String responseType;

    /**
     * Gets the value of the itemVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getItemVersion() {
        return itemVersion;
    }

    /**
     * Sets the value of the itemVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setItemVersion(JAXBElement<String> value) {
        this.itemVersion = value;
    }

    /**
     * Gets the value of the itemPublishDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Calendar }{@code >}
     *     
     */
    public JAXBElement<Calendar> getItemPublishDate() {
        return itemPublishDate;
    }

    /**
     * Sets the value of the itemPublishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Calendar }{@code >}
     *     
     */
    public void setItemPublishDate(JAXBElement<Calendar> value) {
        this.itemPublishDate = value;
    }

    /**
     * Gets the value of the itemName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the itemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setItemName(JAXBElement<String> value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the assessmentIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentIdentifiers }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentItemType.AssessmentIdentifiers> getAssessmentIdentifiers() {
        return assessmentIdentifiers;
    }

    /**
     * Sets the value of the assessmentIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentIdentifiers }{@code >}
     *     
     */
    public void setAssessmentIdentifiers(JAXBElement<Sif3AssessmentItemType.AssessmentIdentifiers> value) {
        this.assessmentIdentifiers = value;
    }

    /**
     * Gets the value of the assessmentSubjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public JAXBElement<SubjectAreaListType> getAssessmentSubjects() {
        return assessmentSubjects;
    }

    /**
     * Sets the value of the assessmentSubjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public void setAssessmentSubjects(JAXBElement<SubjectAreaListType> value) {
        this.assessmentSubjects = value;
    }

    /**
     * Gets the value of the assessmentGradeLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public JAXBElement<YearLevelsType> getAssessmentGradeLevels() {
        return assessmentGradeLevels;
    }

    /**
     * Sets the value of the assessmentGradeLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public void setAssessmentGradeLevels(JAXBElement<YearLevelsType> value) {
        this.assessmentGradeLevels = value;
    }

    /**
     * Gets the value of the assessmentLanguages property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}
     *     
     */
    public JAXBElement<LanguageListType> getAssessmentLanguages() {
        return assessmentLanguages;
    }

    /**
     * Sets the value of the assessmentLanguages property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}
     *     
     */
    public void setAssessmentLanguages(JAXBElement<LanguageListType> value) {
        this.assessmentLanguages = value;
    }

    /**
     * Gets the value of the learningStandardItems property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.LearningStandardItems }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentItemType.LearningStandardItems> getLearningStandardItems() {
        return learningStandardItems;
    }

    /**
     * Sets the value of the learningStandardItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.LearningStandardItems }{@code >}
     *     
     */
    public void setLearningStandardItems(JAXBElement<Sif3AssessmentItemType.LearningStandardItems> value) {
        this.learningStandardItems = value;
    }

    /**
     * Gets the value of the assessmentItemAssetRefIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemAssetRefIds }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentItemType.AssessmentItemAssetRefIds> getAssessmentItemAssetRefIds() {
        return assessmentItemAssetRefIds;
    }

    /**
     * Sets the value of the assessmentItemAssetRefIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemAssetRefIds }{@code >}
     *     
     */
    public void setAssessmentItemAssetRefIds(JAXBElement<Sif3AssessmentItemType.AssessmentItemAssetRefIds> value) {
        this.assessmentItemAssetRefIds = value;
    }

    /**
     * Gets the value of the stem property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
     *     
     */
    public JAXBElement<AbstractContentElementType> getStem() {
        return stem;
    }

    /**
     * Sets the value of the stem property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
     *     
     */
    public void setStem(JAXBElement<AbstractContentElementType> value) {
        this.stem = value;
    }

    /**
     * Gets the value of the responseChoices property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.ResponseChoices }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentItemType.ResponseChoices> getResponseChoices() {
        return responseChoices;
    }

    /**
     * Sets the value of the responseChoices property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.ResponseChoices }{@code >}
     *     
     */
    public void setResponseChoices(JAXBElement<Sif3AssessmentItemType.ResponseChoices> value) {
        this.responseChoices = value;
    }

    /**
     * Gets the value of the itemFeedbackCorrect property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getItemFeedbackCorrect() {
        return itemFeedbackCorrect;
    }

    /**
     * Sets the value of the itemFeedbackCorrect property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setItemFeedbackCorrect(JAXBElement<String> value) {
        this.itemFeedbackCorrect = value;
    }

    /**
     * Gets the value of the itemFeedbackIncorrect property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getItemFeedbackIncorrect() {
        return itemFeedbackIncorrect;
    }

    /**
     * Sets the value of the itemFeedbackIncorrect property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setItemFeedbackIncorrect(JAXBElement<String> value) {
        this.itemFeedbackIncorrect = value;
    }

    /**
     * Gets the value of the itemFeedbackHint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getItemFeedbackHint() {
        return itemFeedbackHint;
    }

    /**
     * Sets the value of the itemFeedbackHint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setItemFeedbackHint(JAXBElement<String> value) {
        this.itemFeedbackHint = value;
    }

    /**
     * Gets the value of the itemScoreMaximum property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getItemScoreMaximum() {
        return itemScoreMaximum;
    }

    /**
     * Sets the value of the itemScoreMaximum property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setItemScoreMaximum(JAXBElement<String> value) {
        this.itemScoreMaximum = value;
    }

    /**
     * Gets the value of the itemScoreMinimum property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getItemScoreMinimum() {
        return itemScoreMinimum;
    }

    /**
     * Sets the value of the itemScoreMinimum property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setItemScoreMinimum(JAXBElement<String> value) {
        this.itemScoreMinimum = value;
    }

    /**
     * Gets the value of the itemRubricIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.ItemRubricIds }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentItemType.ItemRubricIds> getItemRubricIds() {
        return itemRubricIds;
    }

    /**
     * Sets the value of the itemRubricIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.ItemRubricIds }{@code >}
     *     
     */
    public void setItemRubricIds(JAXBElement<Sif3AssessmentItemType.ItemRubricIds> value) {
        this.itemRubricIds = value;
    }

    /**
     * Gets the value of the assessmentItemBanks property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemBanks }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentItemType.AssessmentItemBanks> getAssessmentItemBanks() {
        return assessmentItemBanks;
    }

    /**
     * Sets the value of the assessmentItemBanks property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemBanks }{@code >}
     *     
     */
    public void setAssessmentItemBanks(JAXBElement<Sif3AssessmentItemType.AssessmentItemBanks> value) {
        this.assessmentItemBanks = value;
    }

    /**
     * Gets the value of the assessmentItemPlatforms property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemPlatforms }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentItemType.AssessmentItemPlatforms> getAssessmentItemPlatforms() {
        return assessmentItemPlatforms;
    }

    /**
     * Sets the value of the assessmentItemPlatforms property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemPlatforms }{@code >}
     *     
     */
    public void setAssessmentItemPlatforms(JAXBElement<Sif3AssessmentItemType.AssessmentItemPlatforms> value) {
        this.assessmentItemPlatforms = value;
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
     * Gets the value of the responseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseType() {
        return responseType;
    }

    /**
     * Sets the value of the responseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseType(String value) {
        this.responseType = value;
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
     *         &lt;element name="AssessmentIdentifier" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
     *                 &lt;attribute name="AssessmentIdType" use="required">
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
        "assessmentIdentifier"
    })
    public static class AssessmentIdentifiers {

        @XmlElement(name = "AssessmentIdentifier", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentItemType.AssessmentIdentifiers.AssessmentIdentifier> assessmentIdentifier;

        /**
         * Gets the value of the assessmentIdentifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentIdentifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentIdentifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentItemType.AssessmentIdentifiers.AssessmentIdentifier }
         * 
         * 
         */
        public List<Sif3AssessmentItemType.AssessmentIdentifiers.AssessmentIdentifier> getAssessmentIdentifier() {
            if (assessmentIdentifier == null) {
                assessmentIdentifier = new ArrayList<Sif3AssessmentItemType.AssessmentIdentifiers.AssessmentIdentifier>();
            }
            return this.assessmentIdentifier;
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
         *       &lt;attribute name="AssessmentIdType" use="required">
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
        public static class AssessmentIdentifier {

            @XmlValue
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String value;
            @XmlAttribute(name = "AssessmentIdType", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String assessmentIdType;

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
             * Gets the value of the assessmentIdType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssessmentIdType() {
                return assessmentIdType;
            }

            /**
             * Sets the value of the assessmentIdType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssessmentIdType(String value) {
                this.assessmentIdType = value;
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
     *         &lt;element name="AssessmentItemAssetRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "assessmentItemAssetRefId"
    })
    public static class AssessmentItemAssetRefIds {

        @XmlElement(name = "AssessmentItemAssetRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> assessmentItemAssetRefId;

        /**
         * Gets the value of the assessmentItemAssetRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentItemAssetRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentItemAssetRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAssessmentItemAssetRefId() {
            if (assessmentItemAssetRefId == null) {
                assessmentItemAssetRefId = new ArrayList<String>();
            }
            return this.assessmentItemAssetRefId;
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
     *         &lt;element name="AssessmentItemBank" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AssessmentItemBankId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="AssessmentItemBankName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "assessmentItemBank"
    })
    public static class AssessmentItemBanks {

        @XmlElement(name = "AssessmentItemBank", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank> assessmentItemBank;

        /**
         * Gets the value of the assessmentItemBank property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentItemBank property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentItemBank().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank }
         * 
         * 
         */
        public List<Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank> getAssessmentItemBank() {
            if (assessmentItemBank == null) {
                assessmentItemBank = new ArrayList<Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank>();
            }
            return this.assessmentItemBank;
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
         *         &lt;element name="AssessmentItemBankId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="AssessmentItemBankName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "assessmentItemBankId",
            "assessmentItemBankName"
        })
        public static class AssessmentItemBank {

            @XmlElement(name = "AssessmentItemBankId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String assessmentItemBankId;
            @XmlElementRef(name = "AssessmentItemBankName", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> assessmentItemBankName;

            /**
             * Gets the value of the assessmentItemBankId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssessmentItemBankId() {
                return assessmentItemBankId;
            }

            /**
             * Sets the value of the assessmentItemBankId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssessmentItemBankId(String value) {
                this.assessmentItemBankId = value;
            }

            /**
             * Gets the value of the assessmentItemBankName property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getAssessmentItemBankName() {
                return assessmentItemBankName;
            }

            /**
             * Sets the value of the assessmentItemBankName property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setAssessmentItemBankName(JAXBElement<String> value) {
                this.assessmentItemBankName = value;
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
     *         &lt;element name="AssessmentItemPlatform" maxOccurs="unbounded" minOccurs="0">
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
        "assessmentItemPlatform"
    })
    public static class AssessmentItemPlatforms {

        @XmlElement(name = "AssessmentItemPlatform", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> assessmentItemPlatform;

        /**
         * Gets the value of the assessmentItemPlatform property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentItemPlatform property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentItemPlatform().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAssessmentItemPlatform() {
            if (assessmentItemPlatform == null) {
                assessmentItemPlatform = new ArrayList<String>();
            }
            return this.assessmentItemPlatform;
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
     *         &lt;element name="ItemRubricId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "itemRubricId"
    })
    public static class ItemRubricIds {

        @XmlElement(name = "ItemRubricId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> itemRubricId;

        /**
         * Gets the value of the itemRubricId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the itemRubricId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItemRubricId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getItemRubricId() {
            if (itemRubricId == null) {
                itemRubricId = new ArrayList<String>();
            }
            return this.itemRubricId;
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
     *         &lt;element name="LearningStandardItemRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "learningStandardItemRefId"
    })
    public static class LearningStandardItems {

        @XmlElement(name = "LearningStandardItemRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> learningStandardItemRefId;

        /**
         * Gets the value of the learningStandardItemRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the learningStandardItemRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLearningStandardItemRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getLearningStandardItemRefId() {
            if (learningStandardItemRefId == null) {
                learningStandardItemRefId = new ArrayList<String>();
            }
            return this.learningStandardItemRefId;
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
     *         &lt;element name="Choice" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ChoiceLabel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="ChoiceContent" type="{http://www.sifassociation.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
     *                   &lt;element name="CreditValue" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
     *                   &lt;element name="ResponseFeedback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "choice"
    })
    public static class ResponseChoices {

        @XmlElement(name = "Choice", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentItemType.ResponseChoices.Choice> choice;

        /**
         * Gets the value of the choice property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the choice property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getChoice().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentItemType.ResponseChoices.Choice }
         * 
         * 
         */
        public List<Sif3AssessmentItemType.ResponseChoices.Choice> getChoice() {
            if (choice == null) {
                choice = new ArrayList<Sif3AssessmentItemType.ResponseChoices.Choice>();
            }
            return this.choice;
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
         *         &lt;element name="ChoiceLabel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="ChoiceContent" type="{http://www.sifassociation.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
         *         &lt;element name="CreditValue" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
         *         &lt;element name="ResponseFeedback" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "choiceLabel",
            "choiceContent",
            "creditValue",
            "responseFeedback"
        })
        public static class Choice {

            @XmlElementRef(name = "ChoiceLabel", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> choiceLabel;
            @XmlElement(name = "ChoiceContent", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            protected AbstractContentElementType choiceContent;
            @XmlElementRef(name = "CreditValue", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Float> creditValue;
            @XmlElementRef(name = "ResponseFeedback", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> responseFeedback;

            /**
             * Gets the value of the choiceLabel property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getChoiceLabel() {
                return choiceLabel;
            }

            /**
             * Sets the value of the choiceLabel property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setChoiceLabel(JAXBElement<String> value) {
                this.choiceLabel = value;
            }

            /**
             * Gets the value of the choiceContent property.
             * 
             * @return
             *     possible object is
             *     {@link AbstractContentElementType }
             *     
             */
            public AbstractContentElementType getChoiceContent() {
                return choiceContent;
            }

            /**
             * Sets the value of the choiceContent property.
             * 
             * @param value
             *     allowed object is
             *     {@link AbstractContentElementType }
             *     
             */
            public void setChoiceContent(AbstractContentElementType value) {
                this.choiceContent = value;
            }

            /**
             * Gets the value of the creditValue property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Float }{@code >}
             *     
             */
            public JAXBElement<Float> getCreditValue() {
                return creditValue;
            }

            /**
             * Sets the value of the creditValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Float }{@code >}
             *     
             */
            public void setCreditValue(JAXBElement<Float> value) {
                this.creditValue = value;
            }

            /**
             * Gets the value of the responseFeedback property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getResponseFeedback() {
                return responseFeedback;
            }

            /**
             * Sets the value of the responseFeedback property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setResponseFeedback(JAXBElement<String> value) {
                this.responseFeedback = value;
            }

        }

    }

}
