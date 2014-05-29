
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
 * <p>Java class for Sif3AssessmentAssetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentAssetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetVersion" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="AssetPublishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AssetIdentifiers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssetIdentifier" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
 *                           &lt;attribute name="AssetIdType">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="client"/>
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
 *         &lt;element name="AssetName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="AssetType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="reading passage"/>
 *               &lt;enumeration value="graphic art"/>
 *               &lt;enumeration value="map"/>
 *               &lt;enumeration value="formula sheet"/>
 *               &lt;enumeration value="table"/>
 *               &lt;enumeration value="chart"/>
 *               &lt;enumeration value="audio"/>
 *               &lt;enumeration value="video"/>
 *               &lt;enumeration value="scenario"/>
 *               &lt;enumeration value="simulation"/>
 *               &lt;enumeration value="story board"/>
 *               &lt;enumeration value="lab set"/>
 *               &lt;enumeration value="periodic table"/>
 *               &lt;enumeration value="translation dictionary"/>
 *               &lt;enumeration value="basic calculator"/>
 *               &lt;enumeration value="standard calculator"/>
 *               &lt;enumeration value="scientific calculator"/>
 *               &lt;enumeration value="graphing calculator"/>
 *               &lt;enumeration value="protractor"/>
 *               &lt;enumeration value="metric ruler"/>
 *               &lt;enumeration value="english ruler"/>
 *               &lt;enumeration value="units ruler"/>
 *               &lt;enumeration value="reading line"/>
 *               &lt;enumeration value="line draw"/>
 *               &lt;enumeration value="highlighter"/>
 *               &lt;enumeration value="other interactive"/>
 *               &lt;enumeration value="other non-interactive"/>
 *               &lt;enumeration value="other"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AssetOwner" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="AssetSubjects" type="{http://www.sifassociation.org/au/datamodel/1.3}SubjectAreaListType" minOccurs="0"/>
 *         &lt;element name="AssetGradeLevels" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="AssetLanguage" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAustralianStandardClassificationOfLanguagesASCLType" minOccurs="0"/>
 *         &lt;element name="AssetLearningStandards" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="AssetLearningStandard" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AssetContent" type="{http://www.sifassociation.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
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
@XmlType(name = "Sif3AssessmentAssetType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "assetVersion",
    "assetPublishDate",
    "assetIdentifiers",
    "assetName",
    "assetType",
    "assetOwner",
    "assetSubjects",
    "assetGradeLevels",
    "assetLanguage",
    "assetLearningStandards",
    "assetContent",
    "sifMetadata",
    "sifExtendedElements"
})
public class Sif3AssessmentAssetType {

    @XmlElementRef(name = "AssetVersion", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assetVersion;
    @XmlElementRef(name = "AssetPublishDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Calendar> assetPublishDate;
    @XmlElementRef(name = "AssetIdentifiers", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentAssetType.AssetIdentifiers> assetIdentifiers;
    @XmlElementRef(name = "AssetName", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assetName;
    @XmlElement(name = "AssetType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String assetType;
    @XmlElementRef(name = "AssetOwner", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assetOwner;
    @XmlElementRef(name = "AssetSubjects", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SubjectAreaListType> assetSubjects;
    @XmlElementRef(name = "AssetGradeLevels", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> assetGradeLevels;
    @XmlElementRef(name = "AssetLanguage", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assetLanguage;
    @XmlElementRef(name = "AssetLearningStandards", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentAssetType.AssetLearningStandards> assetLearningStandards;
    @XmlElement(name = "AssetContent", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected AbstractContentElementType assetContent;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the assetVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssetVersion() {
        return assetVersion;
    }

    /**
     * Sets the value of the assetVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssetVersion(JAXBElement<String> value) {
        this.assetVersion = value;
    }

    /**
     * Gets the value of the assetPublishDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Calendar }{@code >}
     *     
     */
    public JAXBElement<Calendar> getAssetPublishDate() {
        return assetPublishDate;
    }

    /**
     * Sets the value of the assetPublishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Calendar }{@code >}
     *     
     */
    public void setAssetPublishDate(JAXBElement<Calendar> value) {
        this.assetPublishDate = value;
    }

    /**
     * Gets the value of the assetIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetType.AssetIdentifiers }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentAssetType.AssetIdentifiers> getAssetIdentifiers() {
        return assetIdentifiers;
    }

    /**
     * Sets the value of the assetIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetType.AssetIdentifiers }{@code >}
     *     
     */
    public void setAssetIdentifiers(JAXBElement<Sif3AssessmentAssetType.AssetIdentifiers> value) {
        this.assetIdentifiers = value;
    }

    /**
     * Gets the value of the assetName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssetName() {
        return assetName;
    }

    /**
     * Sets the value of the assetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssetName(JAXBElement<String> value) {
        this.assetName = value;
    }

    /**
     * Gets the value of the assetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * Sets the value of the assetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetType(String value) {
        this.assetType = value;
    }

    /**
     * Gets the value of the assetOwner property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssetOwner() {
        return assetOwner;
    }

    /**
     * Sets the value of the assetOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssetOwner(JAXBElement<String> value) {
        this.assetOwner = value;
    }

    /**
     * Gets the value of the assetSubjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public JAXBElement<SubjectAreaListType> getAssetSubjects() {
        return assetSubjects;
    }

    /**
     * Sets the value of the assetSubjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public void setAssetSubjects(JAXBElement<SubjectAreaListType> value) {
        this.assetSubjects = value;
    }

    /**
     * Gets the value of the assetGradeLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public JAXBElement<YearLevelsType> getAssetGradeLevels() {
        return assetGradeLevels;
    }

    /**
     * Sets the value of the assetGradeLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public void setAssetGradeLevels(JAXBElement<YearLevelsType> value) {
        this.assetGradeLevels = value;
    }

    /**
     * Gets the value of the assetLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssetLanguage() {
        return assetLanguage;
    }

    /**
     * Sets the value of the assetLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssetLanguage(JAXBElement<String> value) {
        this.assetLanguage = value;
    }

    /**
     * Gets the value of the assetLearningStandards property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetType.AssetLearningStandards }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentAssetType.AssetLearningStandards> getAssetLearningStandards() {
        return assetLearningStandards;
    }

    /**
     * Sets the value of the assetLearningStandards property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetType.AssetLearningStandards }{@code >}
     *     
     */
    public void setAssetLearningStandards(JAXBElement<Sif3AssessmentAssetType.AssetLearningStandards> value) {
        this.assetLearningStandards = value;
    }

    /**
     * Gets the value of the assetContent property.
     * 
     * @return
     *     possible object is
     *     {@link AbstractContentElementType }
     *     
     */
    public AbstractContentElementType getAssetContent() {
        return assetContent;
    }

    /**
     * Sets the value of the assetContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractContentElementType }
     *     
     */
    public void setAssetContent(AbstractContentElementType value) {
        this.assetContent = value;
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
     *         &lt;element name="AssetIdentifier" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
     *                 &lt;attribute name="AssetIdType">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="client"/>
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
        "assetIdentifier"
    })
    public static class AssetIdentifiers {

        @XmlElement(name = "AssetIdentifier", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentAssetType.AssetIdentifiers.AssetIdentifier> assetIdentifier;

        /**
         * Gets the value of the assetIdentifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assetIdentifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssetIdentifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentAssetType.AssetIdentifiers.AssetIdentifier }
         * 
         * 
         */
        public List<Sif3AssessmentAssetType.AssetIdentifiers.AssetIdentifier> getAssetIdentifier() {
            if (assetIdentifier == null) {
                assetIdentifier = new ArrayList<Sif3AssessmentAssetType.AssetIdentifiers.AssetIdentifier>();
            }
            return this.assetIdentifier;
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
         *       &lt;attribute name="AssetIdType">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="client"/>
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
        public static class AssetIdentifier {

            @XmlValue
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String value;
            @XmlAttribute(name = "AssetIdType")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String assetIdType;

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
             * Gets the value of the assetIdType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssetIdType() {
                return assetIdType;
            }

            /**
             * Sets the value of the assetIdType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssetIdType(String value) {
                this.assetIdType = value;
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
     *         &lt;element name="AssetLearningStandard" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "assetLearningStandard"
    })
    public static class AssetLearningStandards {

        @XmlElement(name = "AssetLearningStandard", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> assetLearningStandard;

        /**
         * Gets the value of the assetLearningStandard property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the assetLearningStandard property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAssetLearningStandard().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAssetLearningStandard() {
            if (assetLearningStandard == null) {
                assetLearningStandard = new ArrayList<String>();
            }
            return this.assetLearningStandard;
        }

    }

}
