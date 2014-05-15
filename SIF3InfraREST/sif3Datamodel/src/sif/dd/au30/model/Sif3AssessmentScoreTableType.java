
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
 * This object represents the score conversion tables to be used by the scoring system to derive alternate values. 
 * 
 * <p>Java class for Sif3AssessmentScoreTableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentScoreTableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ScoreTableVersion" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="ScoreTablePublishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ScoreTableIdentifiers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ScoreTableIdentifier" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *                           &lt;attribute name="ScoreTableIdentifierType">
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
 *         &lt;element name="ScoreTableName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ScoreValues" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ScoreValue" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="LowerCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="UpperCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="DerivedValue" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *                                     &lt;attribute name="ToScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="PassFailIndicator" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="FeedbackList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Feedback" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                                                 &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="FromScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
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
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentScoreTableType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "scoreTableVersion",
    "scoreTablePublishDate",
    "scoreTableIdentifiers",
    "scoreTableName",
    "scoreValues",
    "sifMetadata",
    "sifExtendedElements"
})
public class Sif3AssessmentScoreTableType {

    @XmlElementRef(name = "ScoreTableVersion", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> scoreTableVersion;
    @XmlElementRef(name = "ScoreTablePublishDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> scoreTablePublishDate;
    @XmlElementRef(name = "ScoreTableIdentifiers", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentScoreTableType.ScoreTableIdentifiers> scoreTableIdentifiers;
    @XmlElement(name = "ScoreTableName", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String scoreTableName;
    @XmlElementRef(name = "ScoreValues", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Sif3AssessmentScoreTableType.ScoreValues> scoreValues;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the scoreTableVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getScoreTableVersion() {
        return scoreTableVersion;
    }

    /**
     * Sets the value of the scoreTableVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setScoreTableVersion(JAXBElement<String> value) {
        this.scoreTableVersion = value;
    }

    /**
     * Gets the value of the scoreTablePublishDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getScoreTablePublishDate() {
        return scoreTablePublishDate;
    }

    /**
     * Sets the value of the scoreTablePublishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setScoreTablePublishDate(JAXBElement<XMLGregorianCalendar> value) {
        this.scoreTablePublishDate = value;
    }

    /**
     * Gets the value of the scoreTableIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreTableIdentifiers }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentScoreTableType.ScoreTableIdentifiers> getScoreTableIdentifiers() {
        return scoreTableIdentifiers;
    }

    /**
     * Sets the value of the scoreTableIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreTableIdentifiers }{@code >}
     *     
     */
    public void setScoreTableIdentifiers(JAXBElement<Sif3AssessmentScoreTableType.ScoreTableIdentifiers> value) {
        this.scoreTableIdentifiers = value;
    }

    /**
     * Gets the value of the scoreTableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScoreTableName() {
        return scoreTableName;
    }

    /**
     * Sets the value of the scoreTableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScoreTableName(String value) {
        this.scoreTableName = value;
    }

    /**
     * Gets the value of the scoreValues property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreValues }{@code >}
     *     
     */
    public JAXBElement<Sif3AssessmentScoreTableType.ScoreValues> getScoreValues() {
        return scoreValues;
    }

    /**
     * Sets the value of the scoreValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreValues }{@code >}
     *     
     */
    public void setScoreValues(JAXBElement<Sif3AssessmentScoreTableType.ScoreValues> value) {
        this.scoreValues = value;
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
     *         &lt;element name="ScoreTableIdentifier" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
     *                 &lt;attribute name="ScoreTableIdentifierType">
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
        "scoreTableIdentifier"
    })
    public static class ScoreTableIdentifiers {

        @XmlElement(name = "ScoreTableIdentifier", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentScoreTableType.ScoreTableIdentifiers.ScoreTableIdentifier> scoreTableIdentifier;

        /**
         * Gets the value of the scoreTableIdentifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the scoreTableIdentifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getScoreTableIdentifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentScoreTableType.ScoreTableIdentifiers.ScoreTableIdentifier }
         * 
         * 
         */
        public List<Sif3AssessmentScoreTableType.ScoreTableIdentifiers.ScoreTableIdentifier> getScoreTableIdentifier() {
            if (scoreTableIdentifier == null) {
                scoreTableIdentifier = new ArrayList<Sif3AssessmentScoreTableType.ScoreTableIdentifiers.ScoreTableIdentifier>();
            }
            return this.scoreTableIdentifier;
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
         *       &lt;attribute name="ScoreTableIdentifierType">
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
        public static class ScoreTableIdentifier {

            @XmlValue
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String value;
            @XmlAttribute(name = "ScoreTableIdentifierType")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String scoreTableIdentifierType;

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
             * Gets the value of the scoreTableIdentifierType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getScoreTableIdentifierType() {
                return scoreTableIdentifierType;
            }

            /**
             * Sets the value of the scoreTableIdentifierType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setScoreTableIdentifierType(String value) {
                this.scoreTableIdentifierType = value;
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
     *         &lt;element name="ScoreValue" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="LowerCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="UpperCut" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="DerivedValue" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
     *                           &lt;attribute name="ToScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="PassFailIndicator" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="FeedbackList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Feedback" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                                       &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="FromScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
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
        "scoreValue"
    })
    public static class ScoreValues {

        @XmlElement(name = "ScoreValue", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue> scoreValue;

        /**
         * Gets the value of the scoreValue property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the scoreValue property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getScoreValue().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue }
         * 
         * 
         */
        public List<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue> getScoreValue() {
            if (scoreValue == null) {
                scoreValue = new ArrayList<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue>();
            }
            return this.scoreValue;
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
         *         &lt;element name="DerivedValue" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
         *                 &lt;attribute name="ToScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="PassFailIndicator" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *         &lt;element name="FeedbackList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Feedback" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *                             &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
         *       &lt;/sequence>
         *       &lt;attribute name="FromScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
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
            "upperCut",
            "derivedValue",
            "passFailIndicator",
            "feedbackList"
        })
        public static class ScoreValue {

            @XmlElement(name = "LowerCut", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String lowerCut;
            @XmlElement(name = "UpperCut", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String upperCut;
            @XmlElement(name = "DerivedValue", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            protected Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue derivedValue;
            @XmlElementRef(name = "PassFailIndicator", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> passFailIndicator;
            @XmlElementRef(name = "FeedbackList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList> feedbackList;
            @XmlAttribute(name = "FromScoreMetric", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String fromScoreMetric;

            /**
             * Gets the value of the lowerCut property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLowerCut() {
                return lowerCut;
            }

            /**
             * Sets the value of the lowerCut property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLowerCut(String value) {
                this.lowerCut = value;
            }

            /**
             * Gets the value of the upperCut property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUpperCut() {
                return upperCut;
            }

            /**
             * Sets the value of the upperCut property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUpperCut(String value) {
                this.upperCut = value;
            }

            /**
             * Gets the value of the derivedValue property.
             * 
             * @return
             *     possible object is
             *     {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue }
             *     
             */
            public Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue getDerivedValue() {
                return derivedValue;
            }

            /**
             * Sets the value of the derivedValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue }
             *     
             */
            public void setDerivedValue(Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue value) {
                this.derivedValue = value;
            }

            /**
             * Gets the value of the passFailIndicator property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getPassFailIndicator() {
                return passFailIndicator;
            }

            /**
             * Sets the value of the passFailIndicator property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setPassFailIndicator(JAXBElement<String> value) {
                this.passFailIndicator = value;
            }

            /**
             * Gets the value of the feedbackList property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList }{@code >}
             *     
             */
            public JAXBElement<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList> getFeedbackList() {
                return feedbackList;
            }

            /**
             * Sets the value of the feedbackList property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList }{@code >}
             *     
             */
            public void setFeedbackList(JAXBElement<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList> value) {
                this.feedbackList = value;
            }

            /**
             * Gets the value of the fromScoreMetric property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFromScoreMetric() {
                return fromScoreMetric;
            }

            /**
             * Sets the value of the fromScoreMetric property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFromScoreMetric(String value) {
                this.fromScoreMetric = value;
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
             *       &lt;attribute name="ToScoreMetric" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" />
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
            public static class DerivedValue {

                @XmlValue
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                @XmlSchemaType(name = "token")
                protected String value;
                @XmlAttribute(name = "ToScoreMetric", required = true)
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String toScoreMetric;

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
                 * Gets the value of the toScoreMetric property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getToScoreMetric() {
                    return toScoreMetric;
                }

                /**
                 * Sets the value of the toScoreMetric property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setToScoreMetric(String value) {
                    this.toScoreMetric = value;
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
             *         &lt;element name="Feedback" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
             *                   &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
                "feedback"
            })
            public static class FeedbackList {

                @XmlElement(name = "Feedback", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
                protected List<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback> feedback;

                /**
                 * Gets the value of the feedback property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the feedback property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFeedback().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback }
                 * 
                 * 
                 */
                public List<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback> getFeedback() {
                    if (feedback == null) {
                        feedback = new ArrayList<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback>();
                    }
                    return this.feedback;
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
                 *         &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
                 *         &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
                    "diagnosticStatement",
                    "description",
                    "source"
                })
                public static class Feedback {

                    @XmlElement(name = "DiagnosticStatement", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
                    protected String diagnosticStatement;
                    @XmlElementRef(name = "Description", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> description;
                    @XmlElementRef(name = "Source", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> source;

                    /**
                     * Gets the value of the diagnosticStatement property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDiagnosticStatement() {
                        return diagnosticStatement;
                    }

                    /**
                     * Sets the value of the diagnosticStatement property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDiagnosticStatement(String value) {
                        this.diagnosticStatement = value;
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
                     * Gets the value of the source property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getSource() {
                        return source;
                    }

                    /**
                     * Sets the value of the source property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setSource(JAXBElement<String> value) {
                        this.source = value;
                    }

                }

            }

        }

    }

}
