
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         The work assigned to students, an educational event planned by a teacher to deliver a particular educational concept
 *         or skill.
 *       
 * 
 * <p>Java class for ActivityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActivityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Preamble" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TechnicalRequirements" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TechnicalRequirement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SoftwareRequirementList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SoftwareRequirement" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SoftwareTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="Vendor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="EssentialMaterials" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EssentialMaterial" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LearningObjectives" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LearningObjective" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LearningStandards" minOccurs="0">
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
 *         &lt;element name="SubjectArea" type="{http://www.sifassociation.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
 *         &lt;element name="Prerequisites" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Prerequisite" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Students" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StudentPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SourceObjects" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SourceObject" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                           &lt;attribute name="SIF_RefObject" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="Assessment"/>
 *                                 &lt;enumeration value="LearningResource"/>
 *                                 &lt;enumeration value="Activity"/>
 *                                 &lt;enumeration value="Lesson"/>
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
 *         &lt;element name="Points" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ActivityTime" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="Duration" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>XSUnsignedIntOrEmpty">
 *                           &lt;attribute name="Units" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="week"/>
 *                                 &lt;enumeration value="day"/>
 *                                 &lt;enumeration value="hour"/>
 *                                 &lt;enumeration value="minute"/>
 *                                 &lt;enumeration value="second"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="FinishDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AssessmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="MaxAttemptsAllowed" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ActivityWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Evaluation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="EvaluationType" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="Inline"/>
 *                       &lt;enumeration value="RefId"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LearningResources" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LearningResourceRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SIF_Metadata" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" />
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "title",
    "preamble",
    "technicalRequirements",
    "softwareRequirementList",
    "essentialMaterials",
    "learningObjectives",
    "learningStandards",
    "subjectArea",
    "prerequisites",
    "students",
    "sourceObjects",
    "points",
    "activityTime",
    "assessmentRefId",
    "maxAttemptsAllowed",
    "activityWeight",
    "evaluation",
    "learningResources",
    "sifMetadata",
    "sifExtendedElements"
})
public class ActivityType {

    @XmlElementRef(name = "Title", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> title;
    @XmlElementRef(name = "Preamble", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> preamble;
    @XmlElementRef(name = "TechnicalRequirements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.TechnicalRequirements> technicalRequirements;
    @XmlElementRef(name = "SoftwareRequirementList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.SoftwareRequirementList> softwareRequirementList;
    @XmlElementRef(name = "EssentialMaterials", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.EssentialMaterials> essentialMaterials;
    @XmlElementRef(name = "LearningObjectives", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.LearningObjectives> learningObjectives;
    @XmlElementRef(name = "LearningStandards", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.LearningStandards> learningStandards;
    @XmlElementRef(name = "SubjectArea", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SubjectAreaType> subjectArea;
    @XmlElementRef(name = "Prerequisites", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.Prerequisites> prerequisites;
    @XmlElementRef(name = "Students", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.Students> students;
    @XmlElementRef(name = "SourceObjects", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.SourceObjects> sourceObjects;
    @XmlElementRef(name = "Points", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> points;
    @XmlElement(name = "ActivityTime", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected ActivityType.ActivityTime activityTime;
    @XmlElementRef(name = "AssessmentRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> assessmentRefId;
    @XmlElementRef(name = "MaxAttemptsAllowed", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> maxAttemptsAllowed;
    @XmlElementRef(name = "ActivityWeight", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> activityWeight;
    @XmlElementRef(name = "Evaluation", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.Evaluation> evaluation;
    @XmlElementRef(name = "LearningResources", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ActivityType.LearningResources> learningResources;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace", required = true)
    protected String lang;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTitle(JAXBElement<String> value) {
        this.title = value;
    }

    /**
     * Gets the value of the preamble property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPreamble() {
        return preamble;
    }

    /**
     * Sets the value of the preamble property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreamble(JAXBElement<String> value) {
        this.preamble = value;
    }

    /**
     * Gets the value of the technicalRequirements property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.TechnicalRequirements }{@code >}
     *     
     */
    public JAXBElement<ActivityType.TechnicalRequirements> getTechnicalRequirements() {
        return technicalRequirements;
    }

    /**
     * Sets the value of the technicalRequirements property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.TechnicalRequirements }{@code >}
     *     
     */
    public void setTechnicalRequirements(JAXBElement<ActivityType.TechnicalRequirements> value) {
        this.technicalRequirements = value;
    }

    /**
     * Gets the value of the softwareRequirementList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.SoftwareRequirementList }{@code >}
     *     
     */
    public JAXBElement<ActivityType.SoftwareRequirementList> getSoftwareRequirementList() {
        return softwareRequirementList;
    }

    /**
     * Sets the value of the softwareRequirementList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.SoftwareRequirementList }{@code >}
     *     
     */
    public void setSoftwareRequirementList(JAXBElement<ActivityType.SoftwareRequirementList> value) {
        this.softwareRequirementList = value;
    }

    /**
     * Gets the value of the essentialMaterials property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.EssentialMaterials }{@code >}
     *     
     */
    public JAXBElement<ActivityType.EssentialMaterials> getEssentialMaterials() {
        return essentialMaterials;
    }

    /**
     * Sets the value of the essentialMaterials property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.EssentialMaterials }{@code >}
     *     
     */
    public void setEssentialMaterials(JAXBElement<ActivityType.EssentialMaterials> value) {
        this.essentialMaterials = value;
    }

    /**
     * Gets the value of the learningObjectives property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.LearningObjectives }{@code >}
     *     
     */
    public JAXBElement<ActivityType.LearningObjectives> getLearningObjectives() {
        return learningObjectives;
    }

    /**
     * Sets the value of the learningObjectives property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.LearningObjectives }{@code >}
     *     
     */
    public void setLearningObjectives(JAXBElement<ActivityType.LearningObjectives> value) {
        this.learningObjectives = value;
    }

    /**
     * Gets the value of the learningStandards property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.LearningStandards }{@code >}
     *     
     */
    public JAXBElement<ActivityType.LearningStandards> getLearningStandards() {
        return learningStandards;
    }

    /**
     * Sets the value of the learningStandards property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.LearningStandards }{@code >}
     *     
     */
    public void setLearningStandards(JAXBElement<ActivityType.LearningStandards> value) {
        this.learningStandards = value;
    }

    /**
     * Gets the value of the subjectArea property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
     *     
     */
    public JAXBElement<SubjectAreaType> getSubjectArea() {
        return subjectArea;
    }

    /**
     * Sets the value of the subjectArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
     *     
     */
    public void setSubjectArea(JAXBElement<SubjectAreaType> value) {
        this.subjectArea = value;
    }

    /**
     * Gets the value of the prerequisites property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.Prerequisites }{@code >}
     *     
     */
    public JAXBElement<ActivityType.Prerequisites> getPrerequisites() {
        return prerequisites;
    }

    /**
     * Sets the value of the prerequisites property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.Prerequisites }{@code >}
     *     
     */
    public void setPrerequisites(JAXBElement<ActivityType.Prerequisites> value) {
        this.prerequisites = value;
    }

    /**
     * Gets the value of the students property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.Students }{@code >}
     *     
     */
    public JAXBElement<ActivityType.Students> getStudents() {
        return students;
    }

    /**
     * Sets the value of the students property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.Students }{@code >}
     *     
     */
    public void setStudents(JAXBElement<ActivityType.Students> value) {
        this.students = value;
    }

    /**
     * Gets the value of the sourceObjects property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.SourceObjects }{@code >}
     *     
     */
    public JAXBElement<ActivityType.SourceObjects> getSourceObjects() {
        return sourceObjects;
    }

    /**
     * Sets the value of the sourceObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.SourceObjects }{@code >}
     *     
     */
    public void setSourceObjects(JAXBElement<ActivityType.SourceObjects> value) {
        this.sourceObjects = value;
    }

    /**
     * Gets the value of the points property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getPoints() {
        return points;
    }

    /**
     * Sets the value of the points property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setPoints(JAXBElement<Long> value) {
        this.points = value;
    }

    /**
     * Gets the value of the activityTime property.
     * 
     * @return
     *     possible object is
     *     {@link ActivityType.ActivityTime }
     *     
     */
    public ActivityType.ActivityTime getActivityTime() {
        return activityTime;
    }

    /**
     * Sets the value of the activityTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityType.ActivityTime }
     *     
     */
    public void setActivityTime(ActivityType.ActivityTime value) {
        this.activityTime = value;
    }

    /**
     * Gets the value of the assessmentRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAssessmentRefId() {
        return assessmentRefId;
    }

    /**
     * Sets the value of the assessmentRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAssessmentRefId(JAXBElement<String> value) {
        this.assessmentRefId = value;
    }

    /**
     * Gets the value of the maxAttemptsAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getMaxAttemptsAllowed() {
        return maxAttemptsAllowed;
    }

    /**
     * Sets the value of the maxAttemptsAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setMaxAttemptsAllowed(JAXBElement<Long> value) {
        this.maxAttemptsAllowed = value;
    }

    /**
     * Gets the value of the activityWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getActivityWeight() {
        return activityWeight;
    }

    /**
     * Sets the value of the activityWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setActivityWeight(JAXBElement<BigDecimal> value) {
        this.activityWeight = value;
    }

    /**
     * Gets the value of the evaluation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.Evaluation }{@code >}
     *     
     */
    public JAXBElement<ActivityType.Evaluation> getEvaluation() {
        return evaluation;
    }

    /**
     * Sets the value of the evaluation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.Evaluation }{@code >}
     *     
     */
    public void setEvaluation(JAXBElement<ActivityType.Evaluation> value) {
        this.evaluation = value;
    }

    /**
     * Gets the value of the learningResources property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.LearningResources }{@code >}
     *     
     */
    public JAXBElement<ActivityType.LearningResources> getLearningResources() {
        return learningResources;
    }

    /**
     * Sets the value of the learningResources property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ActivityType.LearningResources }{@code >}
     *     
     */
    public void setLearningResources(JAXBElement<ActivityType.LearningResources> value) {
        this.learningResources = value;
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
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
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
     *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="Duration" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>XSUnsignedIntOrEmpty">
     *                 &lt;attribute name="Units" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="week"/>
     *                       &lt;enumeration value="day"/>
     *                       &lt;enumeration value="hour"/>
     *                       &lt;enumeration value="minute"/>
     *                       &lt;enumeration value="second"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="FinishDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="DueDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
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
        "creationDate",
        "duration",
        "startDate",
        "finishDate",
        "dueDate"
    })
    public static class ActivityTime {

        @XmlElement(name = "CreationDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar creationDate;
        @XmlElementRef(name = "Duration", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<ActivityType.ActivityTime.Duration> duration;
        @XmlElementRef(name = "StartDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> startDate;
        @XmlElementRef(name = "FinishDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> finishDate;
        @XmlElementRef(name = "DueDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> dueDate;

        /**
         * Gets the value of the creationDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCreationDate() {
            return creationDate;
        }

        /**
         * Sets the value of the creationDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCreationDate(XMLGregorianCalendar value) {
            this.creationDate = value;
        }

        /**
         * Gets the value of the duration property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link ActivityType.ActivityTime.Duration }{@code >}
         *     
         */
        public JAXBElement<ActivityType.ActivityTime.Duration> getDuration() {
            return duration;
        }

        /**
         * Sets the value of the duration property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link ActivityType.ActivityTime.Duration }{@code >}
         *     
         */
        public void setDuration(JAXBElement<ActivityType.ActivityTime.Duration> value) {
            this.duration = value;
        }

        /**
         * Gets the value of the startDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getStartDate() {
            return startDate;
        }

        /**
         * Sets the value of the startDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setStartDate(JAXBElement<XMLGregorianCalendar> value) {
            this.startDate = value;
        }

        /**
         * Gets the value of the finishDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getFinishDate() {
            return finishDate;
        }

        /**
         * Sets the value of the finishDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setFinishDate(JAXBElement<XMLGregorianCalendar> value) {
            this.finishDate = value;
        }

        /**
         * Gets the value of the dueDate property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getDueDate() {
            return dueDate;
        }

        /**
         * Sets the value of the dueDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setDueDate(JAXBElement<XMLGregorianCalendar> value) {
            this.dueDate = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>XSUnsignedIntOrEmpty">
         *       &lt;attribute name="Units" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="week"/>
         *             &lt;enumeration value="day"/>
         *             &lt;enumeration value="hour"/>
         *             &lt;enumeration value="minute"/>
         *             &lt;enumeration value="second"/>
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
        public static class Duration {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "Units", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String units;

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
             * Gets the value of the units property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUnits() {
                return units;
            }

            /**
             * Sets the value of the units property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUnits(String value) {
                this.units = value;
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
     *         &lt;element name="EssentialMaterial" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "essentialMaterial"
    })
    public static class EssentialMaterials {

        @XmlElement(name = "EssentialMaterial", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<String> essentialMaterial;

        /**
         * Gets the value of the essentialMaterial property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the essentialMaterial property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEssentialMaterial().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getEssentialMaterial() {
            if (essentialMaterial == null) {
                essentialMaterial = new ArrayList<String>();
            }
            return this.essentialMaterial;
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
     *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="EvaluationType" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="Inline"/>
     *             &lt;enumeration value="RefId"/>
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
    @XmlType(name = "", propOrder = {
        "description"
    })
    public static class Evaluation {

        @XmlElementRef(name = "Description", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> description;
        @XmlAttribute(name = "EvaluationType", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String evaluationType;

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
         * Gets the value of the evaluationType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEvaluationType() {
            return evaluationType;
        }

        /**
         * Sets the value of the evaluationType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEvaluationType(String value) {
            this.evaluationType = value;
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
     *         &lt;element name="LearningObjective" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "learningObjective"
    })
    public static class LearningObjectives {

        @XmlElement(name = "LearningObjective", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<String> learningObjective;

        /**
         * Gets the value of the learningObjective property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the learningObjective property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLearningObjective().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getLearningObjective() {
            if (learningObjective == null) {
                learningObjective = new ArrayList<String>();
            }
            return this.learningObjective;
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
     *         &lt;element name="LearningResourceRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "learningResourceRefId"
    })
    public static class LearningResources {

        @XmlElement(name = "LearningResourceRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> learningResourceRefId;

        /**
         * Gets the value of the learningResourceRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the learningResourceRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLearningResourceRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getLearningResourceRefId() {
            if (learningResourceRefId == null) {
                learningResourceRefId = new ArrayList<String>();
            }
            return this.learningResourceRefId;
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
    public static class LearningStandards {

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
     *         &lt;element name="Prerequisite" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "prerequisite"
    })
    public static class Prerequisites {

        @XmlElement(name = "Prerequisite", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<String> prerequisite;

        /**
         * Gets the value of the prerequisite property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the prerequisite property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPrerequisite().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getPrerequisite() {
            if (prerequisite == null) {
                prerequisite = new ArrayList<String>();
            }
            return this.prerequisite;
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
     *         &lt;element name="SoftwareRequirement" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SoftwareTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="Vendor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "softwareRequirement"
    })
    public static class SoftwareRequirementList {

        @XmlElement(name = "SoftwareRequirement", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<ActivityType.SoftwareRequirementList.SoftwareRequirement> softwareRequirement;

        /**
         * Gets the value of the softwareRequirement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the softwareRequirement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSoftwareRequirement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActivityType.SoftwareRequirementList.SoftwareRequirement }
         * 
         * 
         */
        public List<ActivityType.SoftwareRequirementList.SoftwareRequirement> getSoftwareRequirement() {
            if (softwareRequirement == null) {
                softwareRequirement = new ArrayList<ActivityType.SoftwareRequirementList.SoftwareRequirement>();
            }
            return this.softwareRequirement;
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
         *         &lt;element name="SoftwareTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="Vendor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "softwareTitle",
            "version",
            "vendor",
            "os"
        })
        public static class SoftwareRequirement {

            @XmlElement(name = "SoftwareTitle", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            protected String softwareTitle;
            @XmlElementRef(name = "Version", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> version;
            @XmlElementRef(name = "Vendor", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> vendor;
            @XmlElementRef(name = "OS", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> os;

            /**
             * Gets the value of the softwareTitle property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSoftwareTitle() {
                return softwareTitle;
            }

            /**
             * Sets the value of the softwareTitle property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSoftwareTitle(String value) {
                this.softwareTitle = value;
            }

            /**
             * Gets the value of the version property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getVersion() {
                return version;
            }

            /**
             * Sets the value of the version property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setVersion(JAXBElement<String> value) {
                this.version = value;
            }

            /**
             * Gets the value of the vendor property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getVendor() {
                return vendor;
            }

            /**
             * Sets the value of the vendor property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setVendor(JAXBElement<String> value) {
                this.vendor = value;
            }

            /**
             * Gets the value of the os property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getOS() {
                return os;
            }

            /**
             * Sets the value of the os property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setOS(JAXBElement<String> value) {
                this.os = value;
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
     *         &lt;element name="SourceObject" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *                 &lt;attribute name="SIF_RefObject" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="Assessment"/>
     *                       &lt;enumeration value="LearningResource"/>
     *                       &lt;enumeration value="Activity"/>
     *                       &lt;enumeration value="Lesson"/>
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
        "sourceObject"
    })
    public static class SourceObjects {

        @XmlElement(name = "SourceObject", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<ActivityType.SourceObjects.SourceObject> sourceObject;

        /**
         * Gets the value of the sourceObject property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sourceObject property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSourceObject().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ActivityType.SourceObjects.SourceObject }
         * 
         * 
         */
        public List<ActivityType.SourceObjects.SourceObject> getSourceObject() {
            if (sourceObject == null) {
                sourceObject = new ArrayList<ActivityType.SourceObjects.SourceObject>();
            }
            return this.sourceObject;
        }


        /**
         * GUID assigned to the source object.
         * 
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>IdRefTypeOrEmpty">
         *       &lt;attribute name="SIF_RefObject" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="Assessment"/>
         *             &lt;enumeration value="LearningResource"/>
         *             &lt;enumeration value="Activity"/>
         *             &lt;enumeration value="Lesson"/>
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
        public static class SourceObject {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "SIF_RefObject", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String sifRefObject;

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
             * Gets the value of the sifRefObject property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSIFRefObject() {
                return sifRefObject;
            }

            /**
             * Sets the value of the sifRefObject property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSIFRefObject(String value) {
                this.sifRefObject = value;
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
     *         &lt;element name="StudentPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
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
        "studentPersonalRefId"
    })
    public static class Students {

        @XmlElement(name = "StudentPersonalRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> studentPersonalRefId;

        /**
         * Gets the value of the studentPersonalRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the studentPersonalRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStudentPersonalRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getStudentPersonalRefId() {
            if (studentPersonalRefId == null) {
                studentPersonalRefId = new ArrayList<String>();
            }
            return this.studentPersonalRefId;
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
     *         &lt;element name="TechnicalRequirement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "technicalRequirement"
    })
    public static class TechnicalRequirements {

        @XmlElementRef(name = "TechnicalRequirement", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> technicalRequirement;

        /**
         * Gets the value of the technicalRequirement property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getTechnicalRequirement() {
            return technicalRequirement;
        }

        /**
         * Sets the value of the technicalRequirement property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setTechnicalRequirement(JAXBElement<String> value) {
            this.technicalRequirement = value;
        }

    }

}
