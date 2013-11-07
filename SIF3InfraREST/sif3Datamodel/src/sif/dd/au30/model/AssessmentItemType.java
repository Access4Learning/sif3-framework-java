
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


/**
 * 
 *         This object is designed to allow software systems to provide item detail information such as the stem of the item, the distractors, the stimuli, etc.
 *         This initial version of the object does not deal with presentation aspects of the item.
 *         Instead it focuses on the item content and characteristics needed to enable interoperability and the usage of item-level information in the improvement of learning and instruction.
 *       
 * 
 * <p>Java class for AssessmentItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssessmentItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssessmentFormRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="ResponseType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="multiple-choice"/>
 *               &lt;enumeration value="multiple-multiple choice"/>
 *               &lt;enumeration value="true-false"/>
 *               &lt;enumeration value="fill-in-the-blank"/>
 *               &lt;enumeration value="short-answer"/>
 *               &lt;enumeration value="essay"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ItemLabel" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LearningStandardItems" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LearningStandardItemRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Stimulus" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
 *         &lt;element name="Stem" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
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
 *                             &lt;element name="ChoiceContent" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
 *                             &lt;element name="CreditValue" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
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
 *         &lt;element name="ItemScoreMaximum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="ItemScoreMinimum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="PerformanceLevels" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="PerformanceLevel" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CutScores" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="LowerCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                       &lt;element name="UpperCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="ScoreMetric" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="LevelName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
@XmlType(name = "AssessmentItemType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "assessmentFormRefId",
    "responseType",
    "itemLabel",
    "itemName",
    "learningStandardItems",
    "stimulus",
    "stem",
    "responseChoices",
    "itemScoreMaximum",
    "itemScoreMinimum",
    "performanceLevels",
    "sifMetadata",
    "sifExtendedElements"
})
public class AssessmentItemType {

    @XmlElement(name = "AssessmentFormRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String assessmentFormRefId;
    @XmlElement(name = "ResponseType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String responseType;
    @XmlElement(name = "ItemLabel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String itemLabel;
    @XmlElementRef(name = "ItemName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemName;
    @XmlElementRef(name = "LearningStandardItems", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AssessmentItemType.LearningStandardItems> learningStandardItems;
    @XmlElementRef(name = "Stimulus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AbstractContentElementType> stimulus;
    @XmlElementRef(name = "Stem", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AbstractContentElementType> stem;
    @XmlElementRef(name = "ResponseChoices", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AssessmentItemType.ResponseChoices> responseChoices;
    @XmlElementRef(name = "ItemScoreMaximum", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemScoreMaximum;
    @XmlElementRef(name = "ItemScoreMinimum", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> itemScoreMinimum;
    @XmlElementRef(name = "PerformanceLevels", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AssessmentItemType.PerformanceLevels> performanceLevels;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the assessmentFormRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentFormRefId() {
        return assessmentFormRefId;
    }

    /**
     * Sets the value of the assessmentFormRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentFormRefId(String value) {
        this.assessmentFormRefId = value;
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
     * Gets the value of the itemLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemLabel() {
        return itemLabel;
    }

    /**
     * Sets the value of the itemLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemLabel(String value) {
        this.itemLabel = value;
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
     * Gets the value of the learningStandardItems property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AssessmentItemType.LearningStandardItems }{@code >}
     *     
     */
    public JAXBElement<AssessmentItemType.LearningStandardItems> getLearningStandardItems() {
        return learningStandardItems;
    }

    /**
     * Sets the value of the learningStandardItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssessmentItemType.LearningStandardItems }{@code >}
     *     
     */
    public void setLearningStandardItems(JAXBElement<AssessmentItemType.LearningStandardItems> value) {
        this.learningStandardItems = value;
    }

    /**
     * Gets the value of the stimulus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
     *     
     */
    public JAXBElement<AbstractContentElementType> getStimulus() {
        return stimulus;
    }

    /**
     * Sets the value of the stimulus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
     *     
     */
    public void setStimulus(JAXBElement<AbstractContentElementType> value) {
        this.stimulus = value;
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
     *     {@link JAXBElement }{@code <}{@link AssessmentItemType.ResponseChoices }{@code >}
     *     
     */
    public JAXBElement<AssessmentItemType.ResponseChoices> getResponseChoices() {
        return responseChoices;
    }

    /**
     * Sets the value of the responseChoices property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssessmentItemType.ResponseChoices }{@code >}
     *     
     */
    public void setResponseChoices(JAXBElement<AssessmentItemType.ResponseChoices> value) {
        this.responseChoices = value;
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
     * Gets the value of the performanceLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AssessmentItemType.PerformanceLevels }{@code >}
     *     
     */
    public JAXBElement<AssessmentItemType.PerformanceLevels> getPerformanceLevels() {
        return performanceLevels;
    }

    /**
     * Sets the value of the performanceLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssessmentItemType.PerformanceLevels }{@code >}
     *     
     */
    public void setPerformanceLevels(JAXBElement<AssessmentItemType.PerformanceLevels> value) {
        this.performanceLevels = value;
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
     *         &lt;element name="LearningStandardItemRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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

        @XmlElement(name = "LearningStandardItemRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
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
     *         &lt;element name="PerformanceLevel" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CutScores" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="LowerCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                             &lt;element name="UpperCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="ScoreMetric" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="LevelName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        "performanceLevel"
    })
    public static class PerformanceLevels {

        @XmlElement(name = "PerformanceLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<AssessmentItemType.PerformanceLevels.PerformanceLevel> performanceLevel;

        /**
         * Gets the value of the performanceLevel property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the performanceLevel property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPerformanceLevel().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AssessmentItemType.PerformanceLevels.PerformanceLevel }
         * 
         * 
         */
        public List<AssessmentItemType.PerformanceLevels.PerformanceLevel> getPerformanceLevel() {
            if (performanceLevel == null) {
                performanceLevel = new ArrayList<AssessmentItemType.PerformanceLevels.PerformanceLevel>();
            }
            return this.performanceLevel;
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
         *         &lt;element name="CutScores" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="LowerCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *                   &lt;element name="UpperCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="ScoreMetric" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="LevelName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "cutScores"
        })
        public static class PerformanceLevel {

            @XmlElementRef(name = "CutScores", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores> cutScores;
            @XmlAttribute(name = "LevelName", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String levelName;

            /**
             * Gets the value of the cutScores property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores }{@code >}
             *     
             */
            public JAXBElement<AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores> getCutScores() {
                return cutScores;
            }

            /**
             * Sets the value of the cutScores property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores }{@code >}
             *     
             */
            public void setCutScores(JAXBElement<AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores> value) {
                this.cutScores = value;
            }

            /**
             * Gets the value of the levelName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLevelName() {
                return levelName;
            }

            /**
             * Sets the value of the levelName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLevelName(String value) {
                this.levelName = value;
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
             *         &lt;element name="LowerCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
             *         &lt;element name="UpperCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
             *       &lt;/sequence>
             *       &lt;attribute name="ScoreMetric" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "lowerCut",
                "upperCut"
            })
            public static class CutScores {

                @XmlElementRef(name = "LowerCut", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                protected JAXBElement<String> lowerCut;
                @XmlElementRef(name = "UpperCut", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                protected JAXBElement<String> upperCut;
                @XmlAttribute(name = "ScoreMetric", required = true)
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String scoreMetric;

                /**
                 * Gets the value of the lowerCut property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public JAXBElement<String> getLowerCut() {
                    return lowerCut;
                }

                /**
                 * Sets the value of the lowerCut property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public void setLowerCut(JAXBElement<String> value) {
                    this.lowerCut = value;
                }

                /**
                 * Gets the value of the upperCut property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public JAXBElement<String> getUpperCut() {
                    return upperCut;
                }

                /**
                 * Sets the value of the upperCut property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public void setUpperCut(JAXBElement<String> value) {
                    this.upperCut = value;
                }

                /**
                 * Gets the value of the scoreMetric property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getScoreMetric() {
                    return scoreMetric;
                }

                /**
                 * Sets the value of the scoreMetric property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setScoreMetric(String value) {
                    this.scoreMetric = value;
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
     *         &lt;element name="Choice" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ChoiceLabel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="ChoiceContent" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
     *                   &lt;element name="CreditValue" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
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

        @XmlElement(name = "Choice", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<AssessmentItemType.ResponseChoices.Choice> choice;

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
         * {@link AssessmentItemType.ResponseChoices.Choice }
         * 
         * 
         */
        public List<AssessmentItemType.ResponseChoices.Choice> getChoice() {
            if (choice == null) {
                choice = new ArrayList<AssessmentItemType.ResponseChoices.Choice>();
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
         *         &lt;element name="ChoiceContent" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
         *         &lt;element name="CreditValue" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
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
            "creditValue"
        })
        public static class Choice {

            @XmlElementRef(name = "ChoiceLabel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> choiceLabel;
            @XmlElement(name = "ChoiceContent", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected AbstractContentElementType choiceContent;
            @XmlElementRef(name = "CreditValue", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Float> creditValue;

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

        }

    }

}
