
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
 * The scored results from an assessment.
 * 
 * <p>Java class for StudentScoreSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentScoreSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ScoreMetric" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAssessmentReportingMethodType" minOccurs="0"/>
 *         &lt;element name="AssessmentAdministrationRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="StudentPersonalRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="AssessmentRegistrationRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
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
 *                             &lt;element name="ScoreValue" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NumberOfResponses" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="AssessmentSubTestRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="StartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="FinishDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "StudentScoreSetType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "scoreMetric",
    "assessmentAdministrationRefId",
    "studentPersonalRefId",
    "assessmentRegistrationRefId",
    "scores",
    "startDateTime",
    "finishDateTime",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentScoreSetType {

    @XmlElement(name = "ScoreMetric", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String scoreMetric;
    @XmlElement(name = "AssessmentAdministrationRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String assessmentAdministrationRefId;
    @XmlElement(name = "StudentPersonalRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;
    @XmlElementRef(name = "AssessmentRegistrationRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentRegistrationRefId;
    @XmlElement(name = "Scores", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected StudentScoreSetType.Scores scores;
    @XmlElementRef(name = "StartDateTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> startDateTime;
    @XmlElementRef(name = "FinishDateTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> finishDateTime;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

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

    /**
     * Gets the value of the assessmentAdministrationRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentAdministrationRefId() {
        return assessmentAdministrationRefId;
    }

    /**
     * Sets the value of the assessmentAdministrationRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentAdministrationRefId(String value) {
        this.assessmentAdministrationRefId = value;
    }

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
     * Gets the value of the assessmentRegistrationRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentRegistrationRefId() {
        return assessmentRegistrationRefId;
    }

    /**
     * Sets the value of the assessmentRegistrationRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentRegistrationRefId(JAXBElement<String> value) {
        this.assessmentRegistrationRefId = value;
    }

    /**
     * Gets the value of the scores property.
     * 
     * @return
     *     possible object is
     *     {@link StudentScoreSetType.Scores }
     *     
     */
    public StudentScoreSetType.Scores getScores() {
        return scores;
    }

    /**
     * Sets the value of the scores property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentScoreSetType.Scores }
     *     
     */
    public void setScores(StudentScoreSetType.Scores value) {
        this.scores = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setStartDateTime(JAXBElement<XMLGregorianCalendar> value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the finishDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getFinishDateTime() {
        return finishDateTime;
    }

    /**
     * Sets the value of the finishDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setFinishDateTime(JAXBElement<XMLGregorianCalendar> value) {
        this.finishDateTime = value;
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
     *         &lt;element name="Score" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ScoreValue" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NumberOfResponses" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="AssessmentSubTestRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
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
        protected List<StudentScoreSetType.Scores.Score> score;

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
         * {@link StudentScoreSetType.Scores.Score }
         * 
         * 
         */
        public List<StudentScoreSetType.Scores.Score> getScore() {
            if (score == null) {
                score = new ArrayList<StudentScoreSetType.Scores.Score>();
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
         *         &lt;element name="ScoreValue" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NumberOfResponses" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
         *       &lt;/sequence>
         *       &lt;attribute name="AssessmentSubTestRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "scoreValue",
            "diagnosticStatement",
            "description",
            "numberOfResponses"
        })
        public static class Score {

            @XmlElement(name = "ScoreValue", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String scoreValue;
            @XmlElementRef(name = "DiagnosticStatement", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> diagnosticStatement;
            @XmlElementRef(name = "Description", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> description;
            @XmlElementRef(name = "NumberOfResponses", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Long> numberOfResponses;
            @XmlAttribute(name = "AssessmentSubTestRefId", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String assessmentSubTestRefId;

            /**
             * Gets the value of the scoreValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getScoreValue() {
                return scoreValue;
            }

            /**
             * Sets the value of the scoreValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setScoreValue(String value) {
                this.scoreValue = value;
            }

            /**
             * Gets the value of the diagnosticStatement property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getDiagnosticStatement() {
                return diagnosticStatement;
            }

            /**
             * Sets the value of the diagnosticStatement property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setDiagnosticStatement(JAXBElement<String> value) {
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
             * Gets the value of the numberOfResponses property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public JAXBElement<Long> getNumberOfResponses() {
                return numberOfResponses;
            }

            /**
             * Sets the value of the numberOfResponses property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public void setNumberOfResponses(JAXBElement<Long> value) {
                this.numberOfResponses = value;
            }

            /**
             * Gets the value of the assessmentSubTestRefId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssessmentSubTestRefId() {
                return assessmentSubTestRefId;
            }

            /**
             * Sets the value of the assessmentSubTestRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssessmentSubTestRefId(String value) {
                this.assessmentSubTestRefId = value;
            }

        }

    }

}
