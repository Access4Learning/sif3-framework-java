
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
 *         This object provides a snapshot of a student's record on a given day. All information reported in the object is
 *         reported as it appeared in the responding system on the date specified in SnapDate. It can be used for synching data
 *         across applications, for periodically loading a data warehouse, or for vertical reporting of data to a requesting
 *         authority, such as a state department of education.
 *       
 * 
 * <p>Java class for StudentSnapshotType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentSnapshotType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentSnapshotRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}Name" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
 *         &lt;element name="StateProvinceId" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
 *         &lt;element name="Sex" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsSexCodeType" minOccurs="0"/>
 *         &lt;element name="BirthDate" type="{http://www.SIFinfo.org/au/datamodel/1.3}BirthDateType" minOccurs="0"/>
 *         &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="ProjectedGraduationYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}ProjectedGraduationYearType" minOccurs="0"/>
 *         &lt;element name="OnTimeGraduationYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}OnTimeGraduationYearType" minOccurs="0"/>
 *         &lt;element name="StudentSubjectChoiceList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StudentSubjectChoice" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *                             &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="StudyDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
 *                             &lt;element name="OtherSchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
 *         &lt;element name="HomeEnrollment" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                   &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                   &lt;element name="LocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                   &lt;element name="SchoolNo" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                   &lt;element name="YearLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *                   &lt;element name="Homeroom" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                           &lt;attribute name="SIF_RefObject" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="RoomInfo"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="HomeroomNumber" type="{http://www.SIFinfo.org/au/datamodel/1.3}HomeroomNumberType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SnapDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="StudentPersonalRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentSnapshotType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "studentSnapshotRefId",
    "schoolYear",
    "name",
    "localId",
    "stateProvinceId",
    "sex",
    "birthDate",
    "age",
    "projectedGraduationYear",
    "onTimeGraduationYear",
    "studentSubjectChoiceList",
    "homeEnrollment",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentSnapshotType {

    @XmlElementRef(name = "StudentSnapshotRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> studentSnapshotRefId;
    @XmlElement(name = "SchoolYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected XMLGregorianCalendar schoolYear;
    @XmlElement(name = "Name", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected NameType name;
    @XmlElement(name = "LocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String localId;
    @XmlElementRef(name = "StateProvinceId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stateProvinceId;
    @XmlElementRef(name = "Sex", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sex;
    @XmlElementRef(name = "BirthDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> birthDate;
    @XmlElementRef(name = "Age", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> age;
    @XmlElementRef(name = "ProjectedGraduationYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> projectedGraduationYear;
    @XmlElementRef(name = "OnTimeGraduationYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> onTimeGraduationYear;
    @XmlElementRef(name = "StudentSubjectChoiceList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<StudentSnapshotType.StudentSubjectChoiceList> studentSubjectChoiceList;
    @XmlElement(name = "HomeEnrollment", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected StudentSnapshotType.HomeEnrollment homeEnrollment;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "SnapDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar snapDate;
    @XmlAttribute(name = "StudentPersonalRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;

    /**
     * Gets the value of the studentSnapshotRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStudentSnapshotRefId() {
        return studentSnapshotRefId;
    }

    /**
     * Sets the value of the studentSnapshotRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStudentSnapshotRefId(JAXBElement<String> value) {
        this.studentSnapshotRefId = value;
    }

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setName(NameType value) {
        this.name = value;
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
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSex(JAXBElement<String> value) {
        this.sex = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setBirthDate(JAXBElement<XMLGregorianCalendar> value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setAge(JAXBElement<Long> value) {
        this.age = value;
    }

    /**
     * Gets the value of the projectedGraduationYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getProjectedGraduationYear() {
        return projectedGraduationYear;
    }

    /**
     * Sets the value of the projectedGraduationYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setProjectedGraduationYear(JAXBElement<XMLGregorianCalendar> value) {
        this.projectedGraduationYear = value;
    }

    /**
     * Gets the value of the onTimeGraduationYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getOnTimeGraduationYear() {
        return onTimeGraduationYear;
    }

    /**
     * Sets the value of the onTimeGraduationYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setOnTimeGraduationYear(JAXBElement<XMLGregorianCalendar> value) {
        this.onTimeGraduationYear = value;
    }

    /**
     * Gets the value of the studentSubjectChoiceList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link StudentSnapshotType.StudentSubjectChoiceList }{@code >}
     *     
     */
    public JAXBElement<StudentSnapshotType.StudentSubjectChoiceList> getStudentSubjectChoiceList() {
        return studentSubjectChoiceList;
    }

    /**
     * Sets the value of the studentSubjectChoiceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link StudentSnapshotType.StudentSubjectChoiceList }{@code >}
     *     
     */
    public void setStudentSubjectChoiceList(JAXBElement<StudentSnapshotType.StudentSubjectChoiceList> value) {
        this.studentSubjectChoiceList = value;
    }

    /**
     * Gets the value of the homeEnrollment property.
     * 
     * @return
     *     possible object is
     *     {@link StudentSnapshotType.HomeEnrollment }
     *     
     */
    public StudentSnapshotType.HomeEnrollment getHomeEnrollment() {
        return homeEnrollment;
    }

    /**
     * Sets the value of the homeEnrollment property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentSnapshotType.HomeEnrollment }
     *     
     */
    public void setHomeEnrollment(StudentSnapshotType.HomeEnrollment value) {
        this.homeEnrollment = value;
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
     * Gets the value of the snapDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSnapDate() {
        return snapDate;
    }

    /**
     * Sets the value of the snapDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSnapDate(XMLGregorianCalendar value) {
        this.snapDate = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="StudentSchoolEnrollmentRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *         &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *         &lt;element name="LocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *         &lt;element name="SchoolNo" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *         &lt;element name="YearLevel" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
     *         &lt;element name="Homeroom" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *                 &lt;attribute name="SIF_RefObject" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="RoomInfo"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="HomeroomNumber" type="{http://www.SIFinfo.org/au/datamodel/1.3}HomeroomNumberType" minOccurs="0"/>
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
        "schoolName",
        "schoolInfoRefId",
        "localId",
        "schoolNo",
        "yearLevel",
        "homeroom",
        "homeroomNumber"
    })
    public static class HomeEnrollment {

        @XmlElementRef(name = "StudentSchoolEnrollmentRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> studentSchoolEnrollmentRefId;
        @XmlElementRef(name = "SchoolName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> schoolName;
        @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> schoolInfoRefId;
        @XmlElementRef(name = "LocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> localId;
        @XmlElementRef(name = "SchoolNo", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> schoolNo;
        @XmlElementRef(name = "YearLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<YearLevelType> yearLevel;
        @XmlElementRef(name = "Homeroom", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<StudentSnapshotType.HomeEnrollment.Homeroom> homeroom;
        @XmlElementRef(name = "HomeroomNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> homeroomNumber;

        /**
         * Gets the value of the studentSchoolEnrollmentRefId property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getStudentSchoolEnrollmentRefId() {
            return studentSchoolEnrollmentRefId;
        }

        /**
         * Sets the value of the studentSchoolEnrollmentRefId property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setStudentSchoolEnrollmentRefId(JAXBElement<String> value) {
            this.studentSchoolEnrollmentRefId = value;
        }

        /**
         * Gets the value of the schoolName property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getSchoolName() {
            return schoolName;
        }

        /**
         * Sets the value of the schoolName property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setSchoolName(JAXBElement<String> value) {
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
         * Gets the value of the localId property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getLocalId() {
            return localId;
        }

        /**
         * Sets the value of the localId property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setLocalId(JAXBElement<String> value) {
            this.localId = value;
        }

        /**
         * Gets the value of the schoolNo property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getSchoolNo() {
            return schoolNo;
        }

        /**
         * Sets the value of the schoolNo property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setSchoolNo(JAXBElement<String> value) {
            this.schoolNo = value;
        }

        /**
         * Gets the value of the yearLevel property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
         *     
         */
        public JAXBElement<YearLevelType> getYearLevel() {
            return yearLevel;
        }

        /**
         * Sets the value of the yearLevel property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
         *     
         */
        public void setYearLevel(JAXBElement<YearLevelType> value) {
            this.yearLevel = value;
        }

        /**
         * Gets the value of the homeroom property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link StudentSnapshotType.HomeEnrollment.Homeroom }{@code >}
         *     
         */
        public JAXBElement<StudentSnapshotType.HomeEnrollment.Homeroom> getHomeroom() {
            return homeroom;
        }

        /**
         * Sets the value of the homeroom property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link StudentSnapshotType.HomeEnrollment.Homeroom }{@code >}
         *     
         */
        public void setHomeroom(JAXBElement<StudentSnapshotType.HomeEnrollment.Homeroom> value) {
            this.homeroom = value;
        }

        /**
         * Gets the value of the homeroomNumber property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getHomeroomNumber() {
            return homeroomNumber;
        }

        /**
         * Sets the value of the homeroomNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setHomeroomNumber(JAXBElement<String> value) {
            this.homeroomNumber = value;
        }


        /**
         * HomeGroup this student belongs to
         * 
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
         *       &lt;attribute name="SIF_RefObject" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="RoomInfo"/>
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
        public static class Homeroom {

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
     *         &lt;element name="StudentSubjectChoice" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
     *                   &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="StudyDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
     *                   &lt;element name="OtherSchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
        "studentSubjectChoice"
    })
    public static class StudentSubjectChoiceList {

        @XmlElement(name = "StudentSubjectChoice", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice> studentSubjectChoice;

        /**
         * Gets the value of the studentSubjectChoice property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the studentSubjectChoice property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStudentSubjectChoice().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice }
         * 
         * 
         */
        public List<StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice> getStudentSubjectChoice() {
            if (studentSubjectChoice == null) {
                studentSubjectChoice = new ArrayList<StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice>();
            }
            return this.studentSubjectChoice;
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
         *         &lt;element name="PreferenceNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
         *         &lt;element name="SubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="StudyDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaType" minOccurs="0"/>
         *         &lt;element name="OtherSchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
            "preferenceNumber",
            "subjectLocalId",
            "studyDescription",
            "otherSchoolLocalId"
        })
        public static class StudentSubjectChoice {

            @XmlElementRef(name = "PreferenceNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<Long> preferenceNumber;
            @XmlElement(name = "SubjectLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String subjectLocalId;
            @XmlElementRef(name = "StudyDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SubjectAreaType> studyDescription;
            @XmlElementRef(name = "OtherSchoolLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> otherSchoolLocalId;

            /**
             * Gets the value of the preferenceNumber property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public JAXBElement<Long> getPreferenceNumber() {
                return preferenceNumber;
            }

            /**
             * Sets the value of the preferenceNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link Long }{@code >}
             *     
             */
            public void setPreferenceNumber(JAXBElement<Long> value) {
                this.preferenceNumber = value;
            }

            /**
             * Gets the value of the subjectLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubjectLocalId() {
                return subjectLocalId;
            }

            /**
             * Sets the value of the subjectLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubjectLocalId(String value) {
                this.subjectLocalId = value;
            }

            /**
             * Gets the value of the studyDescription property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
             *     
             */
            public JAXBElement<SubjectAreaType> getStudyDescription() {
                return studyDescription;
            }

            /**
             * Sets the value of the studyDescription property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}
             *     
             */
            public void setStudyDescription(JAXBElement<SubjectAreaType> value) {
                this.studyDescription = value;
            }

            /**
             * Gets the value of the otherSchoolLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getOtherSchoolLocalId() {
                return otherSchoolLocalId;
            }

            /**
             * Sets the value of the otherSchoolLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setOtherSchoolLocalId(JAXBElement<String> value) {
                this.otherSchoolLocalId = value;
            }

        }

    }

}
