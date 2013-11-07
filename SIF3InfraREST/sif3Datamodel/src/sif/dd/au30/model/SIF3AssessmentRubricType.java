
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
 * This object represents the scoring rubric used to evaluate responses to open-ended items. Rubrics will define how a student response is to be evaluated and what score values should be applied based on that evaluation. Rubrics can be used by human scorers (teachers or professional scorers) or are used to train artificial intelligence engines. An individual item may have multiple rubrics that are used to evaluate different 'traits' of the response.      
 *       
 * 
 * <p>Java class for SIF3AssessmentRubricType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF3AssessmentRubricType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RubricVersion" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="RubricPublishDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RubricIdentifiers" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RubricIdentifier" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
 *                           &lt;attribute name="RubricIdentifierType">
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
 *         &lt;element name="RubricName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ScoringGuideReference" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="Scores" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Score" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="ScoreCodeDefinition" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
 *                             &lt;element name="ScoreValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="ScoreDescriptions" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ScoreDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="ScoreComments" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ScoreComment" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                                 &lt;element name="Comment" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
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
@XmlType(name = "SIF3AssessmentRubricType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "rubricVersion",
    "rubricPublishDate",
    "rubricIdentifiers",
    "rubricName",
    "scoringGuideReference",
    "scores",
    "sifMetadata",
    "sifExtendedElements"
})
public class SIF3AssessmentRubricType {

    @XmlElementRef(name = "RubricVersion", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> rubricVersion;
    @XmlElementRef(name = "RubricPublishDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> rubricPublishDate;
    @XmlElementRef(name = "RubricIdentifiers", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentRubricType.RubricIdentifiers> rubricIdentifiers;
    @XmlElement(name = "RubricName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String rubricName;
    @XmlElementRef(name = "ScoringGuideReference", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> scoringGuideReference;
    @XmlElementRef(name = "Scores", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIF3AssessmentRubricType.Scores> scores;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the rubricVersion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRubricVersion() {
        return rubricVersion;
    }

    /**
     * Sets the value of the rubricVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRubricVersion(JAXBElement<String> value) {
        this.rubricVersion = value;
    }

    /**
     * Gets the value of the rubricPublishDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getRubricPublishDate() {
        return rubricPublishDate;
    }

    /**
     * Sets the value of the rubricPublishDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setRubricPublishDate(JAXBElement<XMLGregorianCalendar> value) {
        this.rubricPublishDate = value;
    }

    /**
     * Gets the value of the rubricIdentifiers property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.RubricIdentifiers }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentRubricType.RubricIdentifiers> getRubricIdentifiers() {
        return rubricIdentifiers;
    }

    /**
     * Sets the value of the rubricIdentifiers property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.RubricIdentifiers }{@code >}
     *     
     */
    public void setRubricIdentifiers(JAXBElement<SIF3AssessmentRubricType.RubricIdentifiers> value) {
        this.rubricIdentifiers = value;
    }

    /**
     * Gets the value of the rubricName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRubricName() {
        return rubricName;
    }

    /**
     * Sets the value of the rubricName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRubricName(String value) {
        this.rubricName = value;
    }

    /**
     * Gets the value of the scoringGuideReference property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getScoringGuideReference() {
        return scoringGuideReference;
    }

    /**
     * Sets the value of the scoringGuideReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setScoringGuideReference(JAXBElement<String> value) {
        this.scoringGuideReference = value;
    }

    /**
     * Gets the value of the scores property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.Scores }{@code >}
     *     
     */
    public JAXBElement<SIF3AssessmentRubricType.Scores> getScores() {
        return scores;
    }

    /**
     * Sets the value of the scores property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.Scores }{@code >}
     *     
     */
    public void setScores(JAXBElement<SIF3AssessmentRubricType.Scores> value) {
        this.scores = value;
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
     *         &lt;element name="RubricIdentifier" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>token">
     *                 &lt;attribute name="RubricIdentifierType">
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
        "rubricIdentifier"
    })
    public static class RubricIdentifiers {

        @XmlElement(name = "RubricIdentifier", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIF3AssessmentRubricType.RubricIdentifiers.RubricIdentifier> rubricIdentifier;

        /**
         * Gets the value of the rubricIdentifier property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rubricIdentifier property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRubricIdentifier().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIF3AssessmentRubricType.RubricIdentifiers.RubricIdentifier }
         * 
         * 
         */
        public List<SIF3AssessmentRubricType.RubricIdentifiers.RubricIdentifier> getRubricIdentifier() {
            if (rubricIdentifier == null) {
                rubricIdentifier = new ArrayList<SIF3AssessmentRubricType.RubricIdentifiers.RubricIdentifier>();
            }
            return this.rubricIdentifier;
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
         *       &lt;attribute name="RubricIdentifierType">
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
        public static class RubricIdentifier {

            @XmlValue
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlSchemaType(name = "token")
            protected String value;
            @XmlAttribute(name = "RubricIdentifierType")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String rubricIdentifierType;

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
             * Gets the value of the rubricIdentifierType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRubricIdentifierType() {
                return rubricIdentifierType;
            }

            /**
             * Sets the value of the rubricIdentifierType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRubricIdentifierType(String value) {
                this.rubricIdentifierType = value;
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
     *         &lt;element name="Score" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="ScoreCodeDefinition" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
     *                   &lt;element name="ScoreValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="ScoreDescriptions" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ScoreDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" maxOccurs="unbounded" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="ScoreComments" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ScoreComment" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                                       &lt;element name="Comment" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
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
        "score"
    })
    public static class Scores {

        @XmlElement(name = "Score", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIF3AssessmentRubricType.Scores.Score> score;

        /**
         * Gets the value of the score property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the score property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getScore().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIF3AssessmentRubricType.Scores.Score }
         * 
         * 
         */
        public List<SIF3AssessmentRubricType.Scores.Score> getScore() {
            if (score == null) {
                score = new ArrayList<SIF3AssessmentRubricType.Scores.Score>();
            }
            return this.score;
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
         *         &lt;element name="ScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *         &lt;element name="ScoreCodeDefinition" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
         *         &lt;element name="ScoreValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="ScoreDescriptions" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ScoreDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" maxOccurs="unbounded" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="ScoreComments" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ScoreComment" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *                             &lt;element name="Comment" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
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
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "scoreCode",
            "scoreCodeDefinition",
            "scoreValue",
            "scoreDescriptions",
            "scoreComments"
        })
        public static class Score {

            @XmlElementRef(name = "ScoreCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> scoreCode;
            @XmlElementRef(name = "ScoreCodeDefinition", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<AbstractContentElementType> scoreCodeDefinition;
            @XmlElement(name = "ScoreValue", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected BigDecimal scoreValue;
            @XmlElementRef(name = "ScoreDescriptions", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIF3AssessmentRubricType.Scores.Score.ScoreDescriptions> scoreDescriptions;
            @XmlElementRef(name = "ScoreComments", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIF3AssessmentRubricType.Scores.Score.ScoreComments> scoreComments;

            /**
             * Gets the value of the scoreCode property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getScoreCode() {
                return scoreCode;
            }

            /**
             * Sets the value of the scoreCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setScoreCode(JAXBElement<String> value) {
                this.scoreCode = value;
            }

            /**
             * Gets the value of the scoreCodeDefinition property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
             *     
             */
            public JAXBElement<AbstractContentElementType> getScoreCodeDefinition() {
                return scoreCodeDefinition;
            }

            /**
             * Sets the value of the scoreCodeDefinition property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
             *     
             */
            public void setScoreCodeDefinition(JAXBElement<AbstractContentElementType> value) {
                this.scoreCodeDefinition = value;
            }

            /**
             * Gets the value of the scoreValue property.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getScoreValue() {
                return scoreValue;
            }

            /**
             * Sets the value of the scoreValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setScoreValue(BigDecimal value) {
                this.scoreValue = value;
            }

            /**
             * Gets the value of the scoreDescriptions property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.Scores.Score.ScoreDescriptions }{@code >}
             *     
             */
            public JAXBElement<SIF3AssessmentRubricType.Scores.Score.ScoreDescriptions> getScoreDescriptions() {
                return scoreDescriptions;
            }

            /**
             * Sets the value of the scoreDescriptions property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.Scores.Score.ScoreDescriptions }{@code >}
             *     
             */
            public void setScoreDescriptions(JAXBElement<SIF3AssessmentRubricType.Scores.Score.ScoreDescriptions> value) {
                this.scoreDescriptions = value;
            }

            /**
             * Gets the value of the scoreComments property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.Scores.Score.ScoreComments }{@code >}
             *     
             */
            public JAXBElement<SIF3AssessmentRubricType.Scores.Score.ScoreComments> getScoreComments() {
                return scoreComments;
            }

            /**
             * Sets the value of the scoreComments property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIF3AssessmentRubricType.Scores.Score.ScoreComments }{@code >}
             *     
             */
            public void setScoreComments(JAXBElement<SIF3AssessmentRubricType.Scores.Score.ScoreComments> value) {
                this.scoreComments = value;
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
             *         &lt;element name="ScoreComment" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
             *                   &lt;element name="Comment" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
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
                "scoreComment"
            })
            public static class ScoreComments {

                @XmlElement(name = "ScoreComment", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                protected List<SIF3AssessmentRubricType.Scores.Score.ScoreComments.ScoreComment> scoreComment;

                /**
                 * Gets the value of the scoreComment property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the scoreComment property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getScoreComment().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SIF3AssessmentRubricType.Scores.Score.ScoreComments.ScoreComment }
                 * 
                 * 
                 */
                public List<SIF3AssessmentRubricType.Scores.Score.ScoreComments.ScoreComment> getScoreComment() {
                    if (scoreComment == null) {
                        scoreComment = new ArrayList<SIF3AssessmentRubricType.Scores.Score.ScoreComments.ScoreComment>();
                    }
                    return this.scoreComment;
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
                 *         &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
                 *         &lt;element name="Comment" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
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
                    "commentCode",
                    "comment"
                })
                public static class ScoreComment {

                    @XmlElement(name = "CommentCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    @XmlSchemaType(name = "token")
                    protected String commentCode;
                    @XmlElement(name = "Comment", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                    protected AbstractContentElementType comment;

                    /**
                     * Gets the value of the commentCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCommentCode() {
                        return commentCode;
                    }

                    /**
                     * Sets the value of the commentCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCommentCode(String value) {
                        this.commentCode = value;
                    }

                    /**
                     * Gets the value of the comment property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link AbstractContentElementType }
                     *     
                     */
                    public AbstractContentElementType getComment() {
                        return comment;
                    }

                    /**
                     * Sets the value of the comment property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link AbstractContentElementType }
                     *     
                     */
                    public void setComment(AbstractContentElementType value) {
                        this.comment = value;
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
             *         &lt;element name="ScoreDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" maxOccurs="unbounded" minOccurs="0"/>
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
                "scoreDescription"
            })
            public static class ScoreDescriptions {

                @XmlElement(name = "ScoreDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                protected List<AbstractContentElementType> scoreDescription;

                /**
                 * Gets the value of the scoreDescription property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the scoreDescription property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getScoreDescription().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link AbstractContentElementType }
                 * 
                 * 
                 */
                public List<AbstractContentElementType> getScoreDescription() {
                    if (scoreDescription == null) {
                        scoreDescription = new ArrayList<AbstractContentElementType>();
                    }
                    return this.scoreDescription;
                }

            }

        }

    }

}
