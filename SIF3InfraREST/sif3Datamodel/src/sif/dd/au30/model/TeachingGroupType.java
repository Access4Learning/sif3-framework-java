
package sif.dd.au30.model;

import java.math.BigInteger;
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
 * 
 *         This object identifies a particular Teaching Group or class in a particular a time table.
 *       
 * 
 * <p>Java class for TeachingGroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TeachingGroupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
 *         &lt;element name="ShortName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="LongName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Set" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Block" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="CurriculumLevel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *         &lt;element name="SchoolLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SchoolCourseInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *         &lt;element name="SchoolCourseLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *         &lt;element name="TimeTableSubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="Semester" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="StudentList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TeachingGroupStudent" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="StudentPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                             &lt;element name="StudentLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="Name" type="{http://www.sifassociation.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
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
 *         &lt;element name="TeacherList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TeachingGroupTeacher" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="StaffPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                             &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="Name" type="{http://www.sifassociation.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
 *                             &lt;element name="Association" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
 *         &lt;element name="MinClassSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="MaxClassSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="TeachingGroupPeriodList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TeachingGroupPeriod" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="TimeTableCellRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *                             &lt;element name="RoomNumber" type="{http://www.sifassociation.org/au/datamodel/1.3}HomeroomNumberType" minOccurs="0"/>
 *                             &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="DayId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="PeriodId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *                             &lt;element name="CellType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
@XmlType(name = "TeachingGroupType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "schoolYear",
    "localId",
    "shortName",
    "longName",
    "set",
    "block",
    "curriculumLevel",
    "schoolInfoRefId",
    "schoolLocalId",
    "schoolCourseInfoRefId",
    "schoolCourseLocalId",
    "timeTableSubjectRefId",
    "timeTableSubjectLocalId",
    "semester",
    "studentList",
    "teacherList",
    "minClassSize",
    "maxClassSize",
    "teachingGroupPeriodList",
    "sifMetadata",
    "sifExtendedElements"
})
public class TeachingGroupType {

    @XmlElement(name = "SchoolYear", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected XMLGregorianCalendar schoolYear;
    @XmlElement(name = "LocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String localId;
    @XmlElement(name = "ShortName", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String shortName;
    @XmlElementRef(name = "LongName", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> longName;
    @XmlElementRef(name = "Set", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> set;
    @XmlElementRef(name = "Block", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> block;
    @XmlElementRef(name = "CurriculumLevel", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> curriculumLevel;
    @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolInfoRefId;
    @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolLocalId;
    @XmlElementRef(name = "SchoolCourseInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolCourseInfoRefId;
    @XmlElementRef(name = "SchoolCourseLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolCourseLocalId;
    @XmlElementRef(name = "TimeTableSubjectRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timeTableSubjectRefId;
    @XmlElementRef(name = "TimeTableSubjectLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timeTableSubjectLocalId;
    @XmlElementRef(name = "Semester", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> semester;
    @XmlElementRef(name = "StudentList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<TeachingGroupType.StudentList> studentList;
    @XmlElementRef(name = "TeacherList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<TeachingGroupType.TeacherList> teacherList;
    @XmlElementRef(name = "MinClassSize", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> minClassSize;
    @XmlElementRef(name = "MaxClassSize", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigInteger> maxClassSize;
    @XmlElementRef(name = "TeachingGroupPeriodList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<TeachingGroupType.TeachingGroupPeriodList> teachingGroupPeriodList;
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
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the longName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLongName() {
        return longName;
    }

    /**
     * Sets the value of the longName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLongName(JAXBElement<String> value) {
        this.longName = value;
    }

    /**
     * Gets the value of the set property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSet() {
        return set;
    }

    /**
     * Sets the value of the set property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSet(JAXBElement<String> value) {
        this.set = value;
    }

    /**
     * Gets the value of the block property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBlock() {
        return block;
    }

    /**
     * Sets the value of the block property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBlock(JAXBElement<String> value) {
        this.block = value;
    }

    /**
     * Gets the value of the curriculumLevel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCurriculumLevel() {
        return curriculumLevel;
    }

    /**
     * Sets the value of the curriculumLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCurriculumLevel(JAXBElement<String> value) {
        this.curriculumLevel = value;
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
     * Gets the value of the schoolCourseLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolCourseLocalId() {
        return schoolCourseLocalId;
    }

    /**
     * Sets the value of the schoolCourseLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolCourseLocalId(JAXBElement<String> value) {
        this.schoolCourseLocalId = value;
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
     * Gets the value of the timeTableSubjectLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimeTableSubjectLocalId() {
        return timeTableSubjectLocalId;
    }

    /**
     * Sets the value of the timeTableSubjectLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimeTableSubjectLocalId(JAXBElement<String> value) {
        this.timeTableSubjectLocalId = value;
    }

    /**
     * Gets the value of the semester property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getSemester() {
        return semester;
    }

    /**
     * Sets the value of the semester property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setSemester(JAXBElement<Long> value) {
        this.semester = value;
    }

    /**
     * Gets the value of the studentList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TeachingGroupType.StudentList }{@code >}
     *     
     */
    public JAXBElement<TeachingGroupType.StudentList> getStudentList() {
        return studentList;
    }

    /**
     * Sets the value of the studentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TeachingGroupType.StudentList }{@code >}
     *     
     */
    public void setStudentList(JAXBElement<TeachingGroupType.StudentList> value) {
        this.studentList = value;
    }

    /**
     * Gets the value of the teacherList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TeachingGroupType.TeacherList }{@code >}
     *     
     */
    public JAXBElement<TeachingGroupType.TeacherList> getTeacherList() {
        return teacherList;
    }

    /**
     * Sets the value of the teacherList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TeachingGroupType.TeacherList }{@code >}
     *     
     */
    public void setTeacherList(JAXBElement<TeachingGroupType.TeacherList> value) {
        this.teacherList = value;
    }

    /**
     * Gets the value of the minClassSize property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getMinClassSize() {
        return minClassSize;
    }

    /**
     * Sets the value of the minClassSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setMinClassSize(JAXBElement<BigInteger> value) {
        this.minClassSize = value;
    }

    /**
     * Gets the value of the maxClassSize property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public JAXBElement<BigInteger> getMaxClassSize() {
        return maxClassSize;
    }

    /**
     * Sets the value of the maxClassSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     *     
     */
    public void setMaxClassSize(JAXBElement<BigInteger> value) {
        this.maxClassSize = value;
    }

    /**
     * Gets the value of the teachingGroupPeriodList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TeachingGroupType.TeachingGroupPeriodList }{@code >}
     *     
     */
    public JAXBElement<TeachingGroupType.TeachingGroupPeriodList> getTeachingGroupPeriodList() {
        return teachingGroupPeriodList;
    }

    /**
     * Sets the value of the teachingGroupPeriodList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TeachingGroupType.TeachingGroupPeriodList }{@code >}
     *     
     */
    public void setTeachingGroupPeriodList(JAXBElement<TeachingGroupType.TeachingGroupPeriodList> value) {
        this.teachingGroupPeriodList = value;
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
     *         &lt;element name="TeachingGroupStudent" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="StudentPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                   &lt;element name="StudentLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="Name" type="{http://www.sifassociation.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
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
        "teachingGroupStudent"
    })
    public static class StudentList {

        @XmlElement(name = "TeachingGroupStudent", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<TeachingGroupType.StudentList.TeachingGroupStudent> teachingGroupStudent;

        /**
         * Gets the value of the teachingGroupStudent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the teachingGroupStudent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTeachingGroupStudent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TeachingGroupType.StudentList.TeachingGroupStudent }
         * 
         * 
         */
        public List<TeachingGroupType.StudentList.TeachingGroupStudent> getTeachingGroupStudent() {
            if (teachingGroupStudent == null) {
                teachingGroupStudent = new ArrayList<TeachingGroupType.StudentList.TeachingGroupStudent>();
            }
            return this.teachingGroupStudent;
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
         *         &lt;element name="StudentPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *         &lt;element name="StudentLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="Name" type="{http://www.sifassociation.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
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
            "studentPersonalRefId",
            "studentLocalId",
            "name"
        })
        public static class TeachingGroupStudent {

            @XmlElementRef(name = "StudentPersonalRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> studentPersonalRefId;
            @XmlElement(name = "StudentLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String studentLocalId;
            @XmlElement(name = "Name", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            protected NameOfRecordType name;

            /**
             * Gets the value of the studentPersonalRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getStudentPersonalRefId() {
                return studentPersonalRefId;
            }

            /**
             * Sets the value of the studentPersonalRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setStudentPersonalRefId(JAXBElement<String> value) {
                this.studentPersonalRefId = value;
            }

            /**
             * Gets the value of the studentLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStudentLocalId() {
                return studentLocalId;
            }

            /**
             * Sets the value of the studentLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStudentLocalId(String value) {
                this.studentLocalId = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link NameOfRecordType }
             *     
             */
            public NameOfRecordType getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link NameOfRecordType }
             *     
             */
            public void setName(NameOfRecordType value) {
                this.name = value;
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
     *         &lt;element name="TeachingGroupTeacher" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="StaffPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                   &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="Name" type="{http://www.sifassociation.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
     *                   &lt;element name="Association" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "teachingGroupTeacher"
    })
    public static class TeacherList {

        @XmlElement(name = "TeachingGroupTeacher", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<TeachingGroupType.TeacherList.TeachingGroupTeacher> teachingGroupTeacher;

        /**
         * Gets the value of the teachingGroupTeacher property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the teachingGroupTeacher property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTeachingGroupTeacher().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TeachingGroupType.TeacherList.TeachingGroupTeacher }
         * 
         * 
         */
        public List<TeachingGroupType.TeacherList.TeachingGroupTeacher> getTeachingGroupTeacher() {
            if (teachingGroupTeacher == null) {
                teachingGroupTeacher = new ArrayList<TeachingGroupType.TeacherList.TeachingGroupTeacher>();
            }
            return this.teachingGroupTeacher;
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
         *         &lt;element name="StaffPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *         &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="Name" type="{http://www.sifassociation.org/au/datamodel/1.3}NameOfRecordType" minOccurs="0"/>
         *         &lt;element name="Association" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "staffPersonalRefId",
            "staffLocalId",
            "name",
            "association"
        })
        public static class TeachingGroupTeacher {

            @XmlElementRef(name = "StaffPersonalRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> staffPersonalRefId;
            @XmlElement(name = "StaffLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String staffLocalId;
            @XmlElement(name = "Name", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            protected NameOfRecordType name;
            @XmlElement(name = "Association", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String association;

            /**
             * Gets the value of the staffPersonalRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getStaffPersonalRefId() {
                return staffPersonalRefId;
            }

            /**
             * Sets the value of the staffPersonalRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setStaffPersonalRefId(JAXBElement<String> value) {
                this.staffPersonalRefId = value;
            }

            /**
             * Gets the value of the staffLocalId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStaffLocalId() {
                return staffLocalId;
            }

            /**
             * Sets the value of the staffLocalId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStaffLocalId(String value) {
                this.staffLocalId = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link NameOfRecordType }
             *     
             */
            public NameOfRecordType getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link NameOfRecordType }
             *     
             */
            public void setName(NameOfRecordType value) {
                this.name = value;
            }

            /**
             * Gets the value of the association property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAssociation() {
                return association;
            }

            /**
             * Sets the value of the association property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAssociation(String value) {
                this.association = value;
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
     *         &lt;element name="TeachingGroupPeriod" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="TimeTableCellRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
     *                   &lt;element name="RoomNumber" type="{http://www.sifassociation.org/au/datamodel/1.3}HomeroomNumberType" minOccurs="0"/>
     *                   &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="DayId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="PeriodId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
     *                   &lt;element name="CellType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "teachingGroupPeriod"
    })
    public static class TeachingGroupPeriodList {

        @XmlElement(name = "TeachingGroupPeriod", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod> teachingGroupPeriod;

        /**
         * Gets the value of the teachingGroupPeriod property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the teachingGroupPeriod property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTeachingGroupPeriod().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod }
         * 
         * 
         */
        public List<TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod> getTeachingGroupPeriod() {
            if (teachingGroupPeriod == null) {
                teachingGroupPeriod = new ArrayList<TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod>();
            }
            return this.teachingGroupPeriod;
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
         *         &lt;element name="TimeTableCellRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
         *         &lt;element name="RoomNumber" type="{http://www.sifassociation.org/au/datamodel/1.3}HomeroomNumberType" minOccurs="0"/>
         *         &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="DayId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="PeriodId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
         *         &lt;element name="CellType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "timeTableCellRefId",
            "roomNumber",
            "staffLocalId",
            "dayId",
            "periodId",
            "startTime",
            "cellType"
        })
        public static class TeachingGroupPeriod {

            @XmlElementRef(name = "TimeTableCellRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> timeTableCellRefId;
            @XmlElementRef(name = "RoomNumber", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> roomNumber;
            @XmlElementRef(name = "StaffLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> staffLocalId;
            @XmlElement(name = "DayId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String dayId;
            @XmlElementRef(name = "PeriodId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> periodId;
            @XmlElementRef(name = "StartTime", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> startTime;
            @XmlElementRef(name = "CellType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> cellType;

            /**
             * Gets the value of the timeTableCellRefId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getTimeTableCellRefId() {
                return timeTableCellRefId;
            }

            /**
             * Sets the value of the timeTableCellRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setTimeTableCellRefId(JAXBElement<String> value) {
                this.timeTableCellRefId = value;
            }

            /**
             * Gets the value of the roomNumber property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getRoomNumber() {
                return roomNumber;
            }

            /**
             * Sets the value of the roomNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setRoomNumber(JAXBElement<String> value) {
                this.roomNumber = value;
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

            /**
             * Gets the value of the dayId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDayId() {
                return dayId;
            }

            /**
             * Sets the value of the dayId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDayId(String value) {
                this.dayId = value;
            }

            /**
             * Gets the value of the periodId property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getPeriodId() {
                return periodId;
            }

            /**
             * Sets the value of the periodId property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setPeriodId(JAXBElement<String> value) {
                this.periodId = value;
            }

            /**
             * Gets the value of the startTime property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *     
             */
            public JAXBElement<XMLGregorianCalendar> getStartTime() {
                return startTime;
            }

            /**
             * Sets the value of the startTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *     
             */
            public void setStartTime(JAXBElement<XMLGregorianCalendar> value) {
                this.startTime = value;
            }

            /**
             * Gets the value of the cellType property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getCellType() {
                return cellType;
            }

            /**
             * Sets the value of the cellType property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setCellType(JAXBElement<String> value) {
                this.cellType = value;
            }

        }

    }

}
