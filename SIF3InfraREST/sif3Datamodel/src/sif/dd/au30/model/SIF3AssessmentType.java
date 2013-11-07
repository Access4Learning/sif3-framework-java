
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


/**
 * <p>Java class for SIF3AssessmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF3AssessmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="AssessmentPackageRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="AssessmentDescriptors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssessmentDescriptor" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;union memberTypes=" {http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAssessmentTypeType">
 *                         &lt;simpleType>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                             &lt;enumeration value="statewide"/>
 *                             &lt;enumeration value="formative"/>
 *                             &lt;enumeration value="localSummative"/>
 *                             &lt;enumeration value="diagnostic"/>
 *                             &lt;enumeration value="benchmark"/>
 *                             &lt;enumeration value="commonCore"/>
 *                             &lt;enumeration value="national"/>
 *                           &lt;/restriction>
 *                         &lt;/simpleType>
 *                       &lt;/union>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AssessmentProvider" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="AssessmentSubjects" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaListType" minOccurs="0"/>
 *         &lt;element name="AssessmentGradeLevels" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="AssessmentLanguages" type="{http://www.SIFinfo.org/au/datamodel/1.3}LanguageListType" minOccurs="0"/>
 *         &lt;element name="LearningStandardItemRefIds" minOccurs="0">
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
@XmlType(name = "SIF3AssessmentType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "name",
    "assessmentIdentifiers",
    "assessmentPackageRefId",
    "assessmentDescriptors",
    "assessmentProvider",
    "assessmentItemBanks",
    "assessmentSubjects",
    "assessmentGradeLevels",
    "assessmentLanguages",
    "learningStandardItemRefIds",
    "sifMetadata",
    "sifExtendedElements"
})
public class SIF3AssessmentType {

    @XmlElement(name = "Name", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String name;
    @XmlElementRef(name = "AssessmentIdentifiers", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentType.AssessmentIdentifiers> assessmentIdentifiers;
    @XmlElementRef(name = "AssessmentPackageRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentPackageRefId;
    @XmlElementRef(name = "AssessmentDescriptors", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentType.AssessmentDescriptors> assessmentDescriptors;
    @XmlElementRef(name = "AssessmentProvider", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentProvider;
    @XmlElementRef(name = "AssessmentItemBanks", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentType.AssessmentItemBanks> assessmentItemBanks;
    @XmlElementRef(name = "AssessmentSubjects", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SubjectAreaListType> assessmentSubjects;
    @XmlElementRef(name = "AssessmentGradeLevels", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> assessmentGradeLevels;
    @XmlElementRef(name = "AssessmentLanguages", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LanguageListType> assessmentLanguages;
    @XmlElementRef(name = "LearningStandardItemRefIds", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentType.LearningStandardItemRefIds> learningStandardItemRefIds;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
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
     * Gets the value of the assessmentIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.AssessmentIdentifiers }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentType.AssessmentIdentifiers> getAssessmentIdentifiers() {
        return assessmentIdentifiers;
    }

    /**
     * Sets the value of the assessmentIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.AssessmentIdentifiers }{@code >}
     *     
     */
    public void setAssessmentIdentifiers(JAXBElement<SIF3AssessmentType.AssessmentIdentifiers> value) {
        this.assessmentIdentifiers = value;
    }

    /**
     * Gets the value of the assessmentPackageRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentPackageRefId() {
        return assessmentPackageRefId;
    }

    /**
     * Sets the value of the assessmentPackageRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentPackageRefId(JAXBElement<String> value) {
        this.assessmentPackageRefId = value;
    }

    /**
     * Gets the value of the assessmentDescriptors property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.AssessmentDescriptors }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentType.AssessmentDescriptors> getAssessmentDescriptors() {
        return assessmentDescriptors;
    }

    /**
     * Sets the value of the assessmentDescriptors property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.AssessmentDescriptors }{@code >}
     *     
     */
    public void setAssessmentDescriptors(JAXBElement<SIF3AssessmentType.AssessmentDescriptors> value) {
        this.assessmentDescriptors = value;
    }

    /**
     * Gets the value of the assessmentProvider property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentProvider() {
        return assessmentProvider;
    }

    /**
     * Sets the value of the assessmentProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentProvider(JAXBElement<String> value) {
        this.assessmentProvider = value;
    }

    /**
     * Gets the value of the assessmentItemBanks property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.AssessmentItemBanks }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentType.AssessmentItemBanks> getAssessmentItemBanks() {
        return assessmentItemBanks;
    }

    /**
     * Sets the value of the assessmentItemBanks property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.AssessmentItemBanks }{@code >}
     *     
     */
    public void setAssessmentItemBanks(JAXBElement<SIF3AssessmentType.AssessmentItemBanks> value) {
        this.assessmentItemBanks = value;
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
     * Gets the value of the learningStandardItemRefIds property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.LearningStandardItemRefIds }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentType.LearningStandardItemRefIds> getLearningStandardItemRefIds() {
        return learningStandardItemRefIds;
    }

    /**
     * Sets the value of the learningStandardItemRefIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentType.LearningStandardItemRefIds }{@code >}
     *     
     */
    public void setLearningStandardItemRefIds(JAXBElement<SIF3AssessmentType.LearningStandardItemRefIds> value) {
        this.learningStandardItemRefIds = value;
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
     *         &lt;element name="AssessmentDescriptor" maxOccurs="unbounded" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;union memberTypes=" {http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAssessmentTypeType">
     *               &lt;simpleType>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                   &lt;enumeration value="statewide"/>
     *                   &lt;enumeration value="formative"/>
     *                   &lt;enumeration value="localSummative"/>
     *                   &lt;enumeration value="diagnostic"/>
     *                   &lt;enumeration value="benchmark"/>
     *                   &lt;enumeration value="commonCore"/>
     *                   &lt;enumeration value="national"/>
     *                 &lt;/restriction>
     *               &lt;/simpleType>
     *             &lt;/union>
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
        "assessmentDescriptor"
    })
    public static class AssessmentDescriptors {

        @XmlElement(name = "AssessmentDescriptor", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<String> assessmentDescriptor;

        /**
         * Gets the value of the assessmentDescriptor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assessmentDescriptor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssessmentDescriptor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAssessmentDescriptor() {
            if (assessmentDescriptor == null) {
                assessmentDescriptor = new ArrayList<String>();
            }
            return this.assessmentDescriptor;
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

        @XmlElement(name = "AssessmentIdentifier", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIF3AssessmentType.AssessmentIdentifiers.AssessmentIdentifier> assessmentIdentifier;

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
         * {@link SIF3AssessmentType.AssessmentIdentifiers.AssessmentIdentifier }
         * 
         * 
         */
        public List<SIF3AssessmentType.AssessmentIdentifiers.AssessmentIdentifier> getAssessmentIdentifier() {
            if (assessmentIdentifier == null) {
                assessmentIdentifier = new ArrayList<SIF3AssessmentType.AssessmentIdentifiers.AssessmentIdentifier>();
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

        @XmlElement(name = "AssessmentItemBank", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIF3AssessmentType.AssessmentItemBanks.AssessmentItemBank> assessmentItemBank;

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
         * {@link SIF3AssessmentType.AssessmentItemBanks.AssessmentItemBank }
         * 
         * 
         */
        public List<SIF3AssessmentType.AssessmentItemBanks.AssessmentItemBank> getAssessmentItemBank() {
            if (assessmentItemBank == null) {
                assessmentItemBank = new ArrayList<SIF3AssessmentType.AssessmentItemBanks.AssessmentItemBank>();
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

            @XmlElement(name = "AssessmentItemBankId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String assessmentItemBankId;
            @XmlElementRef(name = "AssessmentItemBankName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
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
    public static class LearningStandardItemRefIds {

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

}
