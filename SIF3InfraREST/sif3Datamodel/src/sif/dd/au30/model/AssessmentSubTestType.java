
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
 *         A psychological construct measured by the assessment. Operationally, a subtest is a class of scores on an
 *         assessment. Some assessments may have only one subtest or type of score but most assessments measure more than
 *         one psychological construct. The subtest can be based upon items in a section or items that are empirically related.
 *         Subtests can also be composites of other subtests that are combined using a particular algorithm. Examples of
 *         subtests of an assessment are math total, reading composite, total test, and English composition.
 *       
 * 
 * <p>Java class for AssessmentSubTestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssessmentSubTestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ScoreRange" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Minimum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                   &lt;element name="Maximum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
 *                                     &lt;attribute name="ScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="LevelName" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SubjectArea" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="YearLevels" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
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
 *         &lt;element name="SubTestTier" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="LearningStandardItemRefIds" minOccurs="0">
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
 *         &lt;element name="Abbreviation" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumberOfItems" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ContainerOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "AssessmentSubTestType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "name",
    "scoreRange",
    "performanceLevels",
    "subjectArea",
    "yearLevels",
    "assessmentSubTestRefIds",
    "subTestTier",
    "learningStandardItemRefIds",
    "abbreviation",
    "description",
    "numberOfItems",
    "containerOnly",
    "sifMetadata",
    "sifExtendedElements"
})
public class AssessmentSubTestType {

    @XmlElement(name = "Name", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String name;
    @XmlElementRef(name = "ScoreRange", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AssessmentSubTestType.ScoreRange> scoreRange;
    @XmlElementRef(name = "PerformanceLevels", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AssessmentSubTestType.PerformanceLevels> performanceLevels;
    @XmlElementRef(name = "SubjectArea", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subjectArea;
    @XmlElementRef(name = "YearLevels", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> yearLevels;
    @XmlElementRef(name = "AssessmentSubTestRefIds", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AssessmentSubTestType.AssessmentSubTestRefIds> assessmentSubTestRefIds;
    @XmlElementRef(name = "SubTestTier", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> subTestTier;
    @XmlElementRef(name = "LearningStandardItemRefIds", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AssessmentSubTestType.LearningStandardItemRefIds> learningStandardItemRefIds;
    @XmlElementRef(name = "Abbreviation", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> abbreviation;
    @XmlElementRef(name = "Description", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "NumberOfItems", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> numberOfItems;
    @XmlElementRef(name = "ContainerOnly", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> containerOnly;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the scoreRange property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.ScoreRange }{@code >}
     *     
     */
    public JAXBElement<AssessmentSubTestType.ScoreRange> getScoreRange() {
        return scoreRange;
    }

    /**
     * Sets the value of the scoreRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.ScoreRange }{@code >}
     *     
     */
    public void setScoreRange(JAXBElement<AssessmentSubTestType.ScoreRange> value) {
        this.scoreRange = value;
    }

    /**
     * Gets the value of the performanceLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.PerformanceLevels }{@code >}
     *     
     */
    public JAXBElement<AssessmentSubTestType.PerformanceLevels> getPerformanceLevels() {
        return performanceLevels;
    }

    /**
     * Sets the value of the performanceLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.PerformanceLevels }{@code >}
     *     
     */
    public void setPerformanceLevels(JAXBElement<AssessmentSubTestType.PerformanceLevels> value) {
        this.performanceLevels = value;
    }

    /**
     * Gets the value of the subjectArea property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubjectArea() {
        return subjectArea;
    }

    /**
     * Sets the value of the subjectArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubjectArea(JAXBElement<String> value) {
        this.subjectArea = value;
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
     * Gets the value of the assessmentSubTestRefIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.AssessmentSubTestRefIds }{@code >}
     *     
     */
    public JAXBElement<AssessmentSubTestType.AssessmentSubTestRefIds> getAssessmentSubTestRefIds() {
        return assessmentSubTestRefIds;
    }

    /**
     * Sets the value of the assessmentSubTestRefIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.AssessmentSubTestRefIds }{@code >}
     *     
     */
    public void setAssessmentSubTestRefIds(JAXBElement<AssessmentSubTestType.AssessmentSubTestRefIds> value) {
        this.assessmentSubTestRefIds = value;
    }

    /**
     * Gets the value of the subTestTier property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getSubTestTier() {
        return subTestTier;
    }

    /**
     * Sets the value of the subTestTier property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setSubTestTier(JAXBElement<Long> value) {
        this.subTestTier = value;
    }

    /**
     * Gets the value of the learningStandardItemRefIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.LearningStandardItemRefIds }{@code >}
     *     
     */
    public JAXBElement<AssessmentSubTestType.LearningStandardItemRefIds> getLearningStandardItemRefIds() {
        return learningStandardItemRefIds;
    }

    /**
     * Sets the value of the learningStandardItemRefIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.LearningStandardItemRefIds }{@code >}
     *     
     */
    public void setLearningStandardItemRefIds(JAXBElement<AssessmentSubTestType.LearningStandardItemRefIds> value) {
        this.learningStandardItemRefIds = value;
    }

    /**
     * Gets the value of the abbreviation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the value of the abbreviation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAbbreviation(JAXBElement<String> value) {
        this.abbreviation = value;
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
     * Gets the value of the numberOfItems property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Sets the value of the numberOfItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setNumberOfItems(JAXBElement<Long> value) {
        this.numberOfItems = value;
    }

    /**
     * Gets the value of the containerOnly property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getContainerOnly() {
        return containerOnly;
    }

    /**
     * Sets the value of the containerOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setContainerOnly(JAXBElement<Boolean> value) {
        this.containerOnly = value;
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
    public static class LearningStandardItemRefIds {

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
     *                           &lt;attribute name="ScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="LevelName" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
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

        @XmlElement(name = "PerformanceLevel", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<AssessmentSubTestType.PerformanceLevels.PerformanceLevel> performanceLevel;

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
         * {@link AssessmentSubTestType.PerformanceLevels.PerformanceLevel }
         * 
         * 
         */
        public List<AssessmentSubTestType.PerformanceLevels.PerformanceLevel> getPerformanceLevel() {
            if (performanceLevel == null) {
                performanceLevel = new ArrayList<AssessmentSubTestType.PerformanceLevels.PerformanceLevel>();
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
         *                 &lt;attribute name="ScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="LevelName" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
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

            @XmlElementRef(name = "CutScores", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores> cutScores;
            @XmlAttribute(name = "LevelName", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String levelName;

            /**
             * Gets the value of the cutScores property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores }{@code >}
             *     
             */
            public JAXBElement<AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores> getCutScores() {
                return cutScores;
            }

            /**
             * Sets the value of the cutScores property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores }{@code >}
             *     
             */
            public void setCutScores(JAXBElement<AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores> value) {
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
             *       &lt;attribute name="ScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
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

                @XmlElementRef(name = "LowerCut", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                protected JAXBElement<String> lowerCut;
                @XmlElementRef(name = "UpperCut", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
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
     *         &lt;element name="Minimum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *         &lt;element name="Maximum" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="ScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "minimum",
        "maximum"
    })
    public static class ScoreRange {

        @XmlElementRef(name = "Minimum", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> minimum;
        @XmlElementRef(name = "Maximum", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> maximum;
        @XmlAttribute(name = "ScoreMetric", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String scoreMetric;

        /**
         * Gets the value of the minimum property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getMinimum() {
            return minimum;
        }

        /**
         * Sets the value of the minimum property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setMinimum(JAXBElement<String> value) {
            this.minimum = value;
        }

        /**
         * Gets the value of the maximum property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getMaximum() {
            return maximum;
        }

        /**
         * Sets the value of the maximum property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setMaximum(JAXBElement<String> value) {
            this.maximum = value;
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
