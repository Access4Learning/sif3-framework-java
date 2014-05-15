
package sif.dd.au30.model;

import java.math.BigDecimal;
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
 *         This purpose of this object is to define and communicate the subject or distinct piece of curriculum that needs to be scheduled by the time table generator.  This is a new object proposed to meet SIF-AU needs.  Reviews of the existing specifications identified two relevant objects – SchoolCourseInfo, defined in SIF US 2.2 specification and SchoolGroup, defined within the SIF UK 1.1 specification.  These objects appear to have some similar context but seem overly complex considering that Australian requirements, which are limited to the Student Administration System or Curriculum Delivery System providing base information to the Time Tabling application about what curriculum offerings are being proposed.
 * 
 *         When this data is sent in a Request/Response only those subjects that are relevant to be scheduled (or active), should be sent to the TimeTabling application.
 *       
 * 
 * <p>Java class for TimeTableSubjectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeTableSubjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="AcademicYear" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *         &lt;element name="AcademicYearRange" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Start" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *                   &lt;element name="End" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CourseLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SchoolCourseInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *         &lt;element name="Faculty" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SubjectShortName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SubjectLongName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SubjectType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ProposedMaxClassSize" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ProposedMinClassSize" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="Semester" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="SchoolYear" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
 *         &lt;element name="OtherCodeList" type="{http://www.sifassociation.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
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
@XmlType(name = "TimeTableSubjectType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "subjectLocalId",
    "academicYear",
    "academicYearRange",
    "courseLocalId",
    "schoolCourseInfoRefId",
    "faculty",
    "subjectShortName",
    "subjectLongName",
    "subjectType",
    "proposedMaxClassSize",
    "proposedMinClassSize",
    "schoolInfoRefId",
    "schoolLocalId",
    "semester",
    "schoolYear",
    "otherCodeList",
    "sifMetadata",
    "sifExtendedElements"
})
public class TimeTableSubjectType {

    @XmlElement(name = "SubjectLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String subjectLocalId;
    @XmlElementRef(name = "AcademicYear", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelType> academicYear;
    @XmlElementRef(name = "AcademicYearRange", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<TimeTableSubjectType.AcademicYearRange> academicYearRange;
    @XmlElementRef(name = "CourseLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> courseLocalId;
    @XmlElementRef(name = "SchoolCourseInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolCourseInfoRefId;
    @XmlElementRef(name = "Faculty", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> faculty;
    @XmlElementRef(name = "SubjectShortName", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subjectShortName;
    @XmlElement(name = "SubjectLongName", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String subjectLongName;
    @XmlElementRef(name = "SubjectType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subjectType;
    @XmlElementRef(name = "ProposedMaxClassSize", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> proposedMaxClassSize;
    @XmlElementRef(name = "ProposedMinClassSize", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> proposedMinClassSize;
    @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolInfoRefId;
    @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolLocalId;
    @XmlElementRef(name = "Semester", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> semester;
    @XmlElementRef(name = "SchoolYear", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> schoolYear;
    @XmlElementRef(name = "OtherCodeList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<OtherCodeListType> otherCodeList;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

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
     * Gets the value of the academicYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public JAXBElement<YearLevelType> getAcademicYear() {
        return academicYear;
    }

    /**
     * Sets the value of the academicYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}
     *     
     */
    public void setAcademicYear(JAXBElement<YearLevelType> value) {
        this.academicYear = value;
    }

    /**
     * Gets the value of the academicYearRange property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TimeTableSubjectType.AcademicYearRange }{@code >}
     *     
     */
    public JAXBElement<TimeTableSubjectType.AcademicYearRange> getAcademicYearRange() {
        return academicYearRange;
    }

    /**
     * Sets the value of the academicYearRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TimeTableSubjectType.AcademicYearRange }{@code >}
     *     
     */
    public void setAcademicYearRange(JAXBElement<TimeTableSubjectType.AcademicYearRange> value) {
        this.academicYearRange = value;
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
     * Gets the value of the faculty property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFaculty() {
        return faculty;
    }

    /**
     * Sets the value of the faculty property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFaculty(JAXBElement<String> value) {
        this.faculty = value;
    }

    /**
     * Gets the value of the subjectShortName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubjectShortName() {
        return subjectShortName;
    }

    /**
     * Sets the value of the subjectShortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubjectShortName(JAXBElement<String> value) {
        this.subjectShortName = value;
    }

    /**
     * Gets the value of the subjectLongName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubjectLongName() {
        return subjectLongName;
    }

    /**
     * Sets the value of the subjectLongName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectLongName(String value) {
        this.subjectLongName = value;
    }

    /**
     * Gets the value of the subjectType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSubjectType() {
        return subjectType;
    }

    /**
     * Sets the value of the subjectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubjectType(JAXBElement<String> value) {
        this.subjectType = value;
    }

    /**
     * Gets the value of the proposedMaxClassSize property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getProposedMaxClassSize() {
        return proposedMaxClassSize;
    }

    /**
     * Sets the value of the proposedMaxClassSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setProposedMaxClassSize(JAXBElement<BigDecimal> value) {
        this.proposedMaxClassSize = value;
    }

    /**
     * Gets the value of the proposedMinClassSize property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getProposedMinClassSize() {
        return proposedMinClassSize;
    }

    /**
     * Sets the value of the proposedMinClassSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setProposedMinClassSize(JAXBElement<BigDecimal> value) {
        this.proposedMinClassSize = value;
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
     * Gets the value of the otherCodeList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
     *     
     */
    public JAXBElement<OtherCodeListType> getOtherCodeList() {
        return otherCodeList;
    }

    /**
     * Sets the value of the otherCodeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
     *     
     */
    public void setOtherCodeList(JAXBElement<OtherCodeListType> value) {
        this.otherCodeList = value;
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
     *         &lt;element name="Start" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
     *         &lt;element name="End" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelType" minOccurs="0"/>
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
        "start",
        "end"
    })
    public static class AcademicYearRange {

        @XmlElement(name = "Start", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected YearLevelType start;
        @XmlElement(name = "End", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected YearLevelType end;

        /**
         * Gets the value of the start property.
         * 
         * @return
         *     possible object is
         *     {@link YearLevelType }
         *     
         */
        public YearLevelType getStart() {
            return start;
        }

        /**
         * Sets the value of the start property.
         * 
         * @param value
         *     allowed object is
         *     {@link YearLevelType }
         *     
         */
        public void setStart(YearLevelType value) {
            this.start = value;
        }

        /**
         * Gets the value of the end property.
         * 
         * @return
         *     possible object is
         *     {@link YearLevelType }
         *     
         */
        public YearLevelType getEnd() {
            return end;
        }

        /**
         * Sets the value of the end property.
         * 
         * @param value
         *     allowed object is
         *     {@link YearLevelType }
         *     
         */
        public void setEnd(YearLevelType value) {
            this.end = value;
        }

    }

}
