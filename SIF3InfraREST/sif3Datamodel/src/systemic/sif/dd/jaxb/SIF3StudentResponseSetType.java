
package systemic.sif.dd.jaxb;

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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.Duration;


/**
 * This object transmits the student's responses to stimuli presented in an assessment. This object contains the raw responses as well as provides item-level scores and feedback to the student based on those responses.
 * 
 * <p>Java class for SIF3StudentResponseSetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF3StudentResponseSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Items" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Item" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Response" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ResponseLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *                             &lt;element name="ResponseCorrectness" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;enumeration value="correct"/>
 *                                   &lt;enumeration value="incorrect"/>
 *                                   &lt;enumeration value="partial"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="ViewStatus" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;enumeration value="yes"/>
 *                                   &lt;enumeration value="no"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="AttemptStatus" minOccurs="0">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                   &lt;enumeration value="yes"/>
 *                                   &lt;enumeration value="no"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                             &lt;element name="NumberOfAttempts" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *                             &lt;element name="TimeOnItem" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/>
 *                             &lt;element name="ItemNumber" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="AssessmentRubricRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *                             &lt;element name="ItemScore" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="ItemScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                             &lt;element name="Comments" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Comment" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                                 &lt;element name="CommentDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *                             &lt;element name="TraitScores" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="TraitScore" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="TraitScoreType" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                                 &lt;element name="TraitScoreValue" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                                 &lt;element name="TraitScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
 *                             &lt;element name="FeedbackItems" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="FeedbackItem" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="FeedbackCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *                                                 &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="FeedbackDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                                 &lt;element name="FeedbackSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                             &lt;element name="ItemAids" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="ItemAid" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="AssessmentItemRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
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
 *       &lt;attribute name="AssessmentAdministrationRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *       &lt;attribute name="StudentPersonalRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *       &lt;attribute name="AssessmentRegistrationRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF3StudentResponseSetType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "items",
    "sifMetadata",
    "sifExtendedElements"
})
public class SIF3StudentResponseSetType {

    @XmlElement(name = "Items", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected SIF3StudentResponseSetType.Items items;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;
    @XmlAttribute(name = "AssessmentAdministrationRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String assessmentAdministrationRefId;
    @XmlAttribute(name = "StudentPersonalRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;
    @XmlAttribute(name = "AssessmentRegistrationRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String assessmentRegistrationRefId;

    /**
     * Gets the value of the items property.
     * 
     * @return
     *     possible object is
     *     {@link SIF3StudentResponseSetType.Items }
     *     
     */
    public SIF3StudentResponseSetType.Items getItems() {
        return items;
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *     allowed object is
     *     {@link SIF3StudentResponseSetType.Items }
     *     
     */
    public void setItems(SIF3StudentResponseSetType.Items value) {
        this.items = value;
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
     *     {@link String }
     *     
     */
    public String getAssessmentRegistrationRefId() {
        return assessmentRegistrationRefId;
    }

    /**
     * Sets the value of the assessmentRegistrationRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentRegistrationRefId(String value) {
        this.assessmentRegistrationRefId = value;
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
     *         &lt;element name="Item" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Response" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ResponseLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
     *                   &lt;element name="ResponseCorrectness" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;enumeration value="correct"/>
     *                         &lt;enumeration value="incorrect"/>
     *                         &lt;enumeration value="partial"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="ViewStatus" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;enumeration value="yes"/>
     *                         &lt;enumeration value="no"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="AttemptStatus" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;enumeration value="yes"/>
     *                         &lt;enumeration value="no"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="NumberOfAttempts" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
     *                   &lt;element name="TimeOnItem" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/>
     *                   &lt;element name="ItemNumber" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="AssessmentRubricRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
     *                   &lt;element name="ItemScore" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="ItemScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                   &lt;element name="Comments" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Comment" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                                       &lt;element name="CommentDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
     *                   &lt;element name="TraitScores" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="TraitScore" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="TraitScoreType" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                                       &lt;element name="TraitScoreValue" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                                       &lt;element name="TraitScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
     *                   &lt;element name="FeedbackItems" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="FeedbackItem" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="FeedbackCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
     *                                       &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="FeedbackDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                                       &lt;element name="FeedbackSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
     *                   &lt;element name="ItemAids" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="ItemAid" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="AssessmentItemRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
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
        "item"
    })
    public static class Items {

        @XmlElement(name = "Item", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SIF3StudentResponseSetType.Items.Item> item;

        /**
         * Gets the value of the item property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the item property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIF3StudentResponseSetType.Items.Item }
         * 
         * 
         */
        public List<SIF3StudentResponseSetType.Items.Item> getItem() {
            if (item == null) {
                item = new ArrayList<SIF3StudentResponseSetType.Items.Item>();
            }
            return this.item;
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
         *         &lt;element name="Response" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ResponseLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
         *         &lt;element name="ResponseCorrectness" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;enumeration value="correct"/>
         *               &lt;enumeration value="incorrect"/>
         *               &lt;enumeration value="partial"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="ViewStatus" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;enumeration value="yes"/>
         *               &lt;enumeration value="no"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="AttemptStatus" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;enumeration value="yes"/>
         *               &lt;enumeration value="no"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="NumberOfAttempts" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
         *         &lt;element name="TimeOnItem" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/>
         *         &lt;element name="ItemNumber" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *         &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *         &lt;element name="AssessmentRubricRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
         *         &lt;element name="ItemScore" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *         &lt;element name="ItemScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *         &lt;element name="Comments" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Comment" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *                             &lt;element name="CommentDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
         *         &lt;element name="TraitScores" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="TraitScore" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="TraitScoreType" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *                             &lt;element name="TraitScoreValue" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *                             &lt;element name="TraitScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
         *         &lt;element name="FeedbackItems" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="FeedbackItem" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="FeedbackCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
         *                             &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="FeedbackDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                             &lt;element name="FeedbackSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
         *         &lt;element name="ItemAids" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="ItemAid" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="AssessmentItemRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "response",
            "responseLocation",
            "responseCorrectness",
            "viewStatus",
            "attemptStatus",
            "numberOfAttempts",
            "timeOnItem",
            "itemNumber",
            "itemName",
            "assessmentRubricRefId",
            "itemScore",
            "itemScoreCode",
            "comments",
            "traitScores",
            "feedbackItems",
            "itemAids"
        })
        public static class Item {

            @XmlElement(name = "Response", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected String response;
            @XmlElementRef(name = "ResponseLocation", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> responseLocation;
            @XmlElementRef(name = "ResponseCorrectness", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> responseCorrectness;
            @XmlElementRef(name = "ViewStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> viewStatus;
            @XmlElementRef(name = "AttemptStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> attemptStatus;
            @XmlElementRef(name = "NumberOfAttempts", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Long> numberOfAttempts;
            @XmlElementRef(name = "TimeOnItem", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Duration> timeOnItem;
            @XmlElementRef(name = "ItemNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> itemNumber;
            @XmlElementRef(name = "ItemName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> itemName;
            @XmlElementRef(name = "AssessmentRubricRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> assessmentRubricRefId;
            @XmlElementRef(name = "ItemScore", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> itemScore;
            @XmlElementRef(name = "ItemScoreCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> itemScoreCode;
            @XmlElementRef(name = "Comments", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIF3StudentResponseSetType.Items.Item.Comments> comments;
            @XmlElementRef(name = "TraitScores", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIF3StudentResponseSetType.Items.Item.TraitScores> traitScores;
            @XmlElementRef(name = "FeedbackItems", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIF3StudentResponseSetType.Items.Item.FeedbackItems> feedbackItems;
            @XmlElementRef(name = "ItemAids", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SIF3StudentResponseSetType.Items.Item.ItemAids> itemAids;
            @XmlAttribute(name = "AssessmentItemRefId")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String assessmentItemRefId;

            /**
             * Gets the value of the response property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getResponse() {
                return response;
            }

            /**
             * Sets the value of the response property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setResponse(String value) {
                this.response = value;
            }

            /**
             * Gets the value of the responseLocation property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getResponseLocation() {
                return responseLocation;
            }

            /**
             * Sets the value of the responseLocation property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setResponseLocation(JAXBElement<String> value) {
                this.responseLocation = value;
            }

            /**
             * Gets the value of the responseCorrectness property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getResponseCorrectness() {
                return responseCorrectness;
            }

            /**
             * Sets the value of the responseCorrectness property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setResponseCorrectness(JAXBElement<String> value) {
                this.responseCorrectness = value;
            }

            /**
             * Gets the value of the viewStatus property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getViewStatus() {
                return viewStatus;
            }

            /**
             * Sets the value of the viewStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setViewStatus(JAXBElement<String> value) {
                this.viewStatus = value;
            }

            /**
             * Gets the value of the attemptStatus property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getAttemptStatus() {
                return attemptStatus;
            }

            /**
             * Sets the value of the attemptStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setAttemptStatus(JAXBElement<String> value) {
                this.attemptStatus = value;
            }

            /**
             * Gets the value of the numberOfAttempts property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public JAXBElement<Long> getNumberOfAttempts() {
                return numberOfAttempts;
            }

            /**
             * Sets the value of the numberOfAttempts property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public void setNumberOfAttempts(JAXBElement<Long> value) {
                this.numberOfAttempts = value;
            }

            /**
             * Gets the value of the timeOnItem property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Duration }{@code >}
             *     
             */
            public JAXBElement<Duration> getTimeOnItem() {
                return timeOnItem;
            }

            /**
             * Sets the value of the timeOnItem property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Duration }{@code >}
             *     
             */
            public void setTimeOnItem(JAXBElement<Duration> value) {
                this.timeOnItem = value;
            }

            /**
             * Gets the value of the itemNumber property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getItemNumber() {
                return itemNumber;
            }

            /**
             * Sets the value of the itemNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setItemNumber(JAXBElement<String> value) {
                this.itemNumber = value;
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
             * Gets the value of the assessmentRubricRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getAssessmentRubricRefId() {
                return assessmentRubricRefId;
            }

            /**
             * Sets the value of the assessmentRubricRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setAssessmentRubricRefId(JAXBElement<String> value) {
                this.assessmentRubricRefId = value;
            }

            /**
             * Gets the value of the itemScore property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getItemScore() {
                return itemScore;
            }

            /**
             * Sets the value of the itemScore property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setItemScore(JAXBElement<String> value) {
                this.itemScore = value;
            }

            /**
             * Gets the value of the itemScoreCode property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getItemScoreCode() {
                return itemScoreCode;
            }

            /**
             * Sets the value of the itemScoreCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setItemScoreCode(JAXBElement<String> value) {
                this.itemScoreCode = value;
            }

            /**
             * Gets the value of the comments property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.Comments }{@code >}
             *     
             */
            public JAXBElement<SIF3StudentResponseSetType.Items.Item.Comments> getComments() {
                return comments;
            }

            /**
             * Sets the value of the comments property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.Comments }{@code >}
             *     
             */
            public void setComments(JAXBElement<SIF3StudentResponseSetType.Items.Item.Comments> value) {
                this.comments = value;
            }

            /**
             * Gets the value of the traitScores property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.TraitScores }{@code >}
             *     
             */
            public JAXBElement<SIF3StudentResponseSetType.Items.Item.TraitScores> getTraitScores() {
                return traitScores;
            }

            /**
             * Sets the value of the traitScores property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.TraitScores }{@code >}
             *     
             */
            public void setTraitScores(JAXBElement<SIF3StudentResponseSetType.Items.Item.TraitScores> value) {
                this.traitScores = value;
            }

            /**
             * Gets the value of the feedbackItems property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.FeedbackItems }{@code >}
             *     
             */
            public JAXBElement<SIF3StudentResponseSetType.Items.Item.FeedbackItems> getFeedbackItems() {
                return feedbackItems;
            }

            /**
             * Sets the value of the feedbackItems property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.FeedbackItems }{@code >}
             *     
             */
            public void setFeedbackItems(JAXBElement<SIF3StudentResponseSetType.Items.Item.FeedbackItems> value) {
                this.feedbackItems = value;
            }

            /**
             * Gets the value of the itemAids property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.ItemAids }{@code >}
             *     
             */
            public JAXBElement<SIF3StudentResponseSetType.Items.Item.ItemAids> getItemAids() {
                return itemAids;
            }

            /**
             * Sets the value of the itemAids property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SIF3StudentResponseSetType.Items.Item.ItemAids }{@code >}
             *     
             */
            public void setItemAids(JAXBElement<SIF3StudentResponseSetType.Items.Item.ItemAids> value) {
                this.itemAids = value;
            }

            /**
             * Gets the value of the assessmentItemRefId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssessmentItemRefId() {
                return assessmentItemRefId;
            }

            /**
             * Sets the value of the assessmentItemRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssessmentItemRefId(String value) {
                this.assessmentItemRefId = value;
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
             *         &lt;element name="Comment" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="CommentCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
             *                   &lt;element name="CommentDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
                "comment"
            })
            public static class Comments {

                @XmlElement(name = "Comment", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                protected List<SIF3StudentResponseSetType.Items.Item.Comments.Comment> comment;

                /**
                 * Gets the value of the comment property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the comment property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getComment().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SIF3StudentResponseSetType.Items.Item.Comments.Comment }
                 * 
                 * 
                 */
                public List<SIF3StudentResponseSetType.Items.Item.Comments.Comment> getComment() {
                    if (comment == null) {
                        comment = new ArrayList<SIF3StudentResponseSetType.Items.Item.Comments.Comment>();
                    }
                    return this.comment;
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
                 *         &lt;element name="CommentDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
                    "commentDescription"
                })
                public static class Comment {

                    @XmlElement(name = "CommentCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    @XmlSchemaType(name = "token")
                    protected String commentCode;
                    @XmlElementRef(name = "CommentDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> commentDescription;

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
                     * Gets the value of the commentDescription property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getCommentDescription() {
                        return commentDescription;
                    }

                    /**
                     * Sets the value of the commentDescription property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setCommentDescription(JAXBElement<String> value) {
                        this.commentDescription = value;
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
             *         &lt;element name="FeedbackItem" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="FeedbackCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
             *                   &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="FeedbackDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *                   &lt;element name="FeedbackSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                "feedbackItem"
            })
            public static class FeedbackItems {

                @XmlElement(name = "FeedbackItem", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                protected List<SIF3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem> feedbackItem;

                /**
                 * Gets the value of the feedbackItem property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the feedbackItem property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFeedbackItem().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SIF3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem }
                 * 
                 * 
                 */
                public List<SIF3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem> getFeedbackItem() {
                    if (feedbackItem == null) {
                        feedbackItem = new ArrayList<SIF3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem>();
                    }
                    return this.feedbackItem;
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
                 *         &lt;element name="FeedbackCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
                 *         &lt;element name="DiagnosticStatement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="FeedbackDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
                 *         &lt;element name="FeedbackSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
                    "feedbackCode",
                    "diagnosticStatement",
                    "feedbackDescription",
                    "feedbackSource"
                })
                public static class FeedbackItem {

                    @XmlElementRef(name = "FeedbackCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> feedbackCode;
                    @XmlElementRef(name = "DiagnosticStatement", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> diagnosticStatement;
                    @XmlElementRef(name = "FeedbackDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> feedbackDescription;
                    @XmlElementRef(name = "FeedbackSource", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> feedbackSource;

                    /**
                     * Gets the value of the feedbackCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getFeedbackCode() {
                        return feedbackCode;
                    }

                    /**
                     * Sets the value of the feedbackCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setFeedbackCode(JAXBElement<String> value) {
                        this.feedbackCode = value;
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
                     * Gets the value of the feedbackDescription property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getFeedbackDescription() {
                        return feedbackDescription;
                    }

                    /**
                     * Sets the value of the feedbackDescription property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setFeedbackDescription(JAXBElement<String> value) {
                        this.feedbackDescription = value;
                    }

                    /**
                     * Gets the value of the feedbackSource property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getFeedbackSource() {
                        return feedbackSource;
                    }

                    /**
                     * Sets the value of the feedbackSource property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setFeedbackSource(JAXBElement<String> value) {
                        this.feedbackSource = value;
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
             *         &lt;element name="ItemAid" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
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
                "itemAid"
            })
            public static class ItemAids {

                @XmlElement(name = "ItemAid", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                @XmlSchemaType(name = "token")
                protected List<String> itemAid;

                /**
                 * Gets the value of the itemAid property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the itemAid property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getItemAid().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getItemAid() {
                    if (itemAid == null) {
                        itemAid = new ArrayList<String>();
                    }
                    return this.itemAid;
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
             *         &lt;element name="TraitScore" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="TraitScoreType" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
             *                   &lt;element name="TraitScoreValue" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
             *                   &lt;element name="TraitScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
                "traitScore"
            })
            public static class TraitScores {

                @XmlElement(name = "TraitScore", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                protected List<SIF3StudentResponseSetType.Items.Item.TraitScores.TraitScore> traitScore;

                /**
                 * Gets the value of the traitScore property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the traitScore property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getTraitScore().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SIF3StudentResponseSetType.Items.Item.TraitScores.TraitScore }
                 * 
                 * 
                 */
                public List<SIF3StudentResponseSetType.Items.Item.TraitScores.TraitScore> getTraitScore() {
                    if (traitScore == null) {
                        traitScore = new ArrayList<SIF3StudentResponseSetType.Items.Item.TraitScores.TraitScore>();
                    }
                    return this.traitScore;
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
                 *         &lt;element name="TraitScoreType" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
                 *         &lt;element name="TraitScoreValue" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
                 *         &lt;element name="TraitScoreCode" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
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
                    "traitScoreType",
                    "traitScoreValue",
                    "traitScoreCode"
                })
                public static class TraitScore {

                    @XmlElementRef(name = "TraitScoreType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> traitScoreType;
                    @XmlElementRef(name = "TraitScoreValue", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> traitScoreValue;
                    @XmlElementRef(name = "TraitScoreCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> traitScoreCode;

                    /**
                     * Gets the value of the traitScoreType property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getTraitScoreType() {
                        return traitScoreType;
                    }

                    /**
                     * Sets the value of the traitScoreType property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setTraitScoreType(JAXBElement<String> value) {
                        this.traitScoreType = value;
                    }

                    /**
                     * Gets the value of the traitScoreValue property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getTraitScoreValue() {
                        return traitScoreValue;
                    }

                    /**
                     * Sets the value of the traitScoreValue property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setTraitScoreValue(JAXBElement<String> value) {
                        this.traitScoreValue = value;
                    }

                    /**
                     * Gets the value of the traitScoreCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getTraitScoreCode() {
                        return traitScoreCode;
                    }

                    /**
                     * Sets the value of the traitScoreCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setTraitScoreCode(JAXBElement<String> value) {
                        this.traitScoreCode = value;
                    }

                }

            }

        }

    }

}
