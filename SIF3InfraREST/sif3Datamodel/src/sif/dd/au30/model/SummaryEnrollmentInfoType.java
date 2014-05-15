
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
 * 
 *         A key Australian business requirement is the ability to publish a complete student status update on demand.  This status may include multiple school enrolments.
 *       
 * 
 * <p>Java class for SummaryEnrollmentInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SummaryEnrollmentInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYear" minOccurs="0"/>
 *         &lt;element name="ParentObjectRefId" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>RefIdTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="StudentPersonal"/>
 *                       &lt;enumeration value="StaffPersonal"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
 *         &lt;element name="StateProvinceId" type="{http://www.sifassociation.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}PersonInfo" minOccurs="0"/>
 *         &lt;element name="SchoolEnrollmentList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SchoolEnrollment" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *                             &lt;element name="SchoolLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="SchoolStateProvinceId" type="{http://www.sifassociation.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
 *                             &lt;element name="Student" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *                                       &lt;element name="StudentLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Staff" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="StaffAssignmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *                                       &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="EnrollmentList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Enrollment" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                                                 &lt;element name="SchoolCourseInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                                                 &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                                                 &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
 *                                                 &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                                                 &lt;element name="SchoolYear" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
 *                                                 &lt;element name="SubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                                                 &lt;element name="CourseLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
@XmlType(name = "SummaryEnrollmentInfoType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "schoolYear",
    "parentObjectRefId",
    "localId",
    "stateProvinceId",
    "personInfo",
    "schoolEnrollmentList",
    "sifMetadata",
    "sifExtendedElements"
})
public class SummaryEnrollmentInfoType {

    @XmlElement(name = "SchoolYear", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected XMLGregorianCalendar schoolYear;
    @XmlElement(name = "ParentObjectRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected SummaryEnrollmentInfoType.ParentObjectRefId parentObjectRefId;
    @XmlElement(name = "LocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String localId;
    @XmlElementRef(name = "StateProvinceId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stateProvinceId;
    @XmlElement(name = "PersonInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected PersonInfoType personInfo;
    @XmlElement(name = "SchoolEnrollmentList", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected SummaryEnrollmentInfoType.SchoolEnrollmentList schoolEnrollmentList;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the schoolYear property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the value of the schoolYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSchoolYear(XMLGregorianCalendar value) {
        this.schoolYear = value;
    }

    /**
     * Gets the value of the parentObjectRefId property.
     * 
     * @return
     *     possible object is
     *     {@link SummaryEnrollmentInfoType.ParentObjectRefId }
     *     
     */
    public SummaryEnrollmentInfoType.ParentObjectRefId getParentObjectRefId() {
        return parentObjectRefId;
    }

    /**
     * Sets the value of the parentObjectRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link SummaryEnrollmentInfoType.ParentObjectRefId }
     *     
     */
    public void setParentObjectRefId(SummaryEnrollmentInfoType.ParentObjectRefId value) {
        this.parentObjectRefId = value;
    }

    /**
     * Gets the value of the localId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalId() {
        return localId;
    }

    /**
     * Sets the value of the localId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalId(String value) {
        this.localId = value;
    }

    /**
     * Gets the value of the stateProvinceId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStateProvinceId() {
        return stateProvinceId;
    }

    /**
     * Sets the value of the stateProvinceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStateProvinceId(JAXBElement<String> value) {
        this.stateProvinceId = value;
    }

    /**
     * Gets the value of the personInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PersonInfoType }
     *     
     */
    public PersonInfoType getPersonInfo() {
        return personInfo;
    }

    /**
     * Sets the value of the personInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonInfoType }
     *     
     */
    public void setPersonInfo(PersonInfoType value) {
        this.personInfo = value;
    }

    /**
     * Gets the value of the schoolEnrollmentList property.
     * 
     * @return
     *     possible object is
     *     {@link SummaryEnrollmentInfoType.SchoolEnrollmentList }
     *     
     */
    public SummaryEnrollmentInfoType.SchoolEnrollmentList getSchoolEnrollmentList() {
        return schoolEnrollmentList;
    }

    /**
     * Sets the value of the schoolEnrollmentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SummaryEnrollmentInfoType.SchoolEnrollmentList }
     *     
     */
    public void setSchoolEnrollmentList(SummaryEnrollmentInfoType.SchoolEnrollmentList value) {
        this.schoolEnrollmentList = value;
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
     * The GUID of the related parent object
     * 
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>RefIdTypeOrEmpty">
     *       &lt;attribute name="SIF_RefObject" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="StudentPersonal"/>
     *             &lt;enumeration value="StaffPersonal"/>
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
    public static class ParentObjectRefId {

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
     *         &lt;element name="SchoolEnrollment" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
     *                   &lt;element name="SchoolLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="SchoolStateProvinceId" type="{http://www.sifassociation.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
     *                   &lt;element name="Student" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
     *                             &lt;element name="StudentLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Staff" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="StaffAssignmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
     *                             &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="EnrollmentList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Enrollment" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                                       &lt;element name="SchoolCourseInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                                       &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                                       &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
     *                                       &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                                       &lt;element name="SchoolYear" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
     *                                       &lt;element name="SubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                                       &lt;element name="CourseLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
        "schoolEnrollment"
    })
    public static class SchoolEnrollmentList {

        @XmlElement(name = "SchoolEnrollment", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment> schoolEnrollment;

        /**
         * Gets the value of the schoolEnrollment property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the schoolEnrollment property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSchoolEnrollment().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment }
         * 
         * 
         */
        public List<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment> getSchoolEnrollment() {
            if (schoolEnrollment == null) {
                schoolEnrollment = new ArrayList<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment>();
            }
            return this.schoolEnrollment;
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
         *         &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
         *         &lt;element name="SchoolLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="SchoolStateProvinceId" type="{http://www.sifassociation.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
         *         &lt;element name="Student" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
         *                   &lt;element name="StudentLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Staff" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="StaffAssignmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
         *                   &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="EnrollmentList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Enrollment" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *                             &lt;element name="SchoolCourseInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *                             &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *                             &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
         *                             &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *                             &lt;element name="SchoolYear" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
         *                             &lt;element name="SubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *                             &lt;element name="CourseLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
            "schoolName",
            "schoolInfoRefId",
            "schoolLocalId",
            "schoolStateProvinceId",
            "student",
            "staff",
            "enrollmentList"
        })
        public static class SchoolEnrollment {

            @XmlElement(name = "SchoolName", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String schoolName;
            @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> schoolInfoRefId;
            @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> schoolLocalId;
            @XmlElementRef(name = "SchoolStateProvinceId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> schoolStateProvinceId;
            @XmlElementRef(name = "Student", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student> student;
            @XmlElementRef(name = "Staff", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff> staff;
            @XmlElementRef(name = "EnrollmentList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList> enrollmentList;

            /**
             * Gets the value of the schoolName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSchoolName() {
                return schoolName;
            }

            /**
             * Sets the value of the schoolName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSchoolName(String value) {
                this.schoolName = value;
            }

            /**
             * Gets the value of the schoolInfoRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSchoolInfoRefId() {
                return schoolInfoRefId;
            }

            /**
             * Sets the value of the schoolInfoRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSchoolInfoRefId(JAXBElement<String> value) {
                this.schoolInfoRefId = value;
            }

            /**
             * Gets the value of the schoolLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSchoolLocalId() {
                return schoolLocalId;
            }

            /**
             * Sets the value of the schoolLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSchoolLocalId(JAXBElement<String> value) {
                this.schoolLocalId = value;
            }

            /**
             * Gets the value of the schoolStateProvinceId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSchoolStateProvinceId() {
                return schoolStateProvinceId;
            }

            /**
             * Sets the value of the schoolStateProvinceId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSchoolStateProvinceId(JAXBElement<String> value) {
                this.schoolStateProvinceId = value;
            }

            /**
             * Gets the value of the student property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student }{@code >}
             *     
             */
            public JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student> getStudent() {
                return student;
            }

            /**
             * Sets the value of the student property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student }{@code >}
             *     
             */
            public void setStudent(JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student> value) {
                this.student = value;
            }

            /**
             * Gets the value of the staff property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff }{@code >}
             *     
             */
            public JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff> getStaff() {
                return staff;
            }

            /**
             * Sets the value of the staff property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff }{@code >}
             *     
             */
            public void setStaff(JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff> value) {
                this.staff = value;
            }

            /**
             * Gets the value of the enrollmentList property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList }{@code >}
             *     
             */
            public JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList> getEnrollmentList() {
                return enrollmentList;
            }

            /**
             * Sets the value of the enrollmentList property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList }{@code >}
             *     
             */
            public void setEnrollmentList(JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList> value) {
                this.enrollmentList = value;
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
             *         &lt;element name="Enrollment" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
             *                   &lt;element name="SchoolCourseInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
             *                   &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
             *                   &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
             *                   &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
             *                   &lt;element name="SchoolYear" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
             *                   &lt;element name="SubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
             *                   &lt;element name="CourseLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
                "enrollment"
            })
            public static class EnrollmentList {

                @XmlElement(name = "Enrollment", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
                protected List<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment> enrollment;

                /**
                 * Gets the value of the enrollment property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the enrollment property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getEnrollment().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment }
                 * 
                 * 
                 */
                public List<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment> getEnrollment() {
                    if (enrollment == null) {
                        enrollment = new ArrayList<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment>();
                    }
                    return this.enrollment;
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
                 *         &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
                 *         &lt;element name="SchoolCourseInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
                 *         &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
                 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
                 *         &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
                 *         &lt;element name="SchoolYear" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
                 *         &lt;element name="SubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
                 *         &lt;element name="CourseLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
                    "teachingGroupRefId",
                    "schoolCourseInfoRefId",
                    "timeTableSubjectRefId",
                    "localId",
                    "shortName",
                    "schoolYear",
                    "subjectLocalId",
                    "courseLocalId"
                })
                public static class Enrollment {

                    @XmlElementRef(name = "TeachingGroupRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> teachingGroupRefId;
                    @XmlElementRef(name = "SchoolCourseInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> schoolCourseInfoRefId;
                    @XmlElementRef(name = "TimeTableSubjectRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> timeTableSubjectRefId;
                    @XmlElement(name = "LocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
                    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
                    protected String localId;
                    @XmlElementRef(name = "ShortName", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> shortName;
                    @XmlElementRef(name = "SchoolYear", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<XMLGregorianCalendar> schoolYear;
                    @XmlElementRef(name = "SubjectLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> subjectLocalId;
                    @XmlElementRef(name = "CourseLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<String> courseLocalId;

                    /**
                     * Gets the value of the teachingGroupRefId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getTeachingGroupRefId() {
                        return teachingGroupRefId;
                    }

                    /**
                     * Sets the value of the teachingGroupRefId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setTeachingGroupRefId(JAXBElement<String> value) {
                        this.teachingGroupRefId = value;
                    }

                    /**
                     * Gets the value of the schoolCourseInfoRefId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getSchoolCourseInfoRefId() {
                        return schoolCourseInfoRefId;
                    }

                    /**
                     * Sets the value of the schoolCourseInfoRefId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setSchoolCourseInfoRefId(JAXBElement<String> value) {
                        this.schoolCourseInfoRefId = value;
                    }

                    /**
                     * Gets the value of the timeTableSubjectRefId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getTimeTableSubjectRefId() {
                        return timeTableSubjectRefId;
                    }

                    /**
                     * Sets the value of the timeTableSubjectRefId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setTimeTableSubjectRefId(JAXBElement<String> value) {
                        this.timeTableSubjectRefId = value;
                    }

                    /**
                     * Gets the value of the localId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLocalId() {
                        return localId;
                    }

                    /**
                     * Sets the value of the localId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLocalId(String value) {
                        this.localId = value;
                    }

                    /**
                     * Gets the value of the shortName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getShortName() {
                        return shortName;
                    }

                    /**
                     * Sets the value of the shortName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setShortName(JAXBElement<String> value) {
                        this.shortName = value;
                    }

                    /**
                     * Gets the value of the schoolYear property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
                     *     
                     */
                    public JAXBElement<XMLGregorianCalendar> getSchoolYear() {
                        return schoolYear;
                    }

                    /**
                     * Sets the value of the schoolYear property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
                     *     
                     */
                    public void setSchoolYear(JAXBElement<XMLGregorianCalendar> value) {
                        this.schoolYear = value;
                    }

                    /**
                     * Gets the value of the subjectLocalId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getSubjectLocalId() {
                        return subjectLocalId;
                    }

                    /**
                     * Sets the value of the subjectLocalId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setSubjectLocalId(JAXBElement<String> value) {
                        this.subjectLocalId = value;
                    }

                    /**
                     * Gets the value of the courseLocalId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public JAXBElement<String> getCourseLocalId() {
                        return courseLocalId;
                    }

                    /**
                     * Sets the value of the courseLocalId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link String }{@code >}
                     *     
                     */
                    public void setCourseLocalId(JAXBElement<String> value) {
                        this.courseLocalId = value;
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
             *         &lt;element name="StaffAssignmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
             *         &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
                "staffAssignmentRefId",
                "staffLocalId"
            })
            public static class Staff {

                @XmlElement(name = "StaffAssignmentRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String staffAssignmentRefId;
                @XmlElementRef(name = "StaffLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                protected JAXBElement<String> staffLocalId;

                /**
                 * Gets the value of the staffAssignmentRefId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getStaffAssignmentRefId() {
                    return staffAssignmentRefId;
                }

                /**
                 * Sets the value of the staffAssignmentRefId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setStaffAssignmentRefId(String value) {
                    this.staffAssignmentRefId = value;
                }

                /**
                 * Gets the value of the staffLocalId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public JAXBElement<String> getStaffLocalId() {
                    return staffLocalId;
                }

                /**
                 * Sets the value of the staffLocalId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public void setStaffLocalId(JAXBElement<String> value) {
                    this.staffLocalId = value;
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
             *         &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
             *         &lt;element name="StudentLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
                "studentSchoolEnrollmentRefId",
                "studentLocalId"
            })
            public static class Student {

                @XmlElement(name = "StudentSchoolEnrollmentRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String studentSchoolEnrollmentRefId;
                @XmlElementRef(name = "StudentLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                protected JAXBElement<String> studentLocalId;

                /**
                 * Gets the value of the studentSchoolEnrollmentRefId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getStudentSchoolEnrollmentRefId() {
                    return studentSchoolEnrollmentRefId;
                }

                /**
                 * Sets the value of the studentSchoolEnrollmentRefId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setStudentSchoolEnrollmentRefId(String value) {
                    this.studentSchoolEnrollmentRefId = value;
                }

                /**
                 * Gets the value of the studentLocalId property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public JAXBElement<String> getStudentLocalId() {
                    return studentLocalId;
                }

                /**
                 * Sets the value of the studentLocalId property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link JAXBElement }{@code <}{@link String }{@code >}
                 *     
                 */
                public void setStudentLocalId(JAXBElement<String> value) {
                    this.studentLocalId = value;
                }

            }

        }

    }

}
