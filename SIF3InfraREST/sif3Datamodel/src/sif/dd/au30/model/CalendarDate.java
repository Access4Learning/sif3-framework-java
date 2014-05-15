
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This object defines information related to a school calendar day in a given school calendar year. If both CalendarDate and CalendarSummary objects are supported, there must be an instance of this object for each date between CalendarSummary StartDate and EndDate, inclusive.
 * 
 * <p>Java class for CalendarDateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalendarDateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CalendarDateRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}SchoolYear" minOccurs="0"/>
 *         &lt;element name="CalendarDateType" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsCalendarEventType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.sifassociation.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CalendarDateNumber" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="StudentAttendance" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CountsTowardAttendance" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="Yes"/>
 *                         &lt;enumeration value="No"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="AttendanceValue" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;minInclusive value="0"/>
 *                         &lt;maxInclusive value="1"/>
 *                         &lt;fractionDigits value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TeacherAttendance" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CountsTowardAttendance" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="Yes"/>
 *                         &lt;enumeration value="No"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="AttendanceValue" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;minInclusive value="0"/>
 *                         &lt;maxInclusive value="1"/>
 *                         &lt;fractionDigits value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AdministratorAttendance" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CountsTowardAttendance" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                         &lt;enumeration value="Yes"/>
 *                         &lt;enumeration value="No"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="AttendanceValue" minOccurs="0">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;minInclusive value="0"/>
 *                         &lt;maxInclusive value="1"/>
 *                         &lt;fractionDigits value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SIF_Metadata" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="CalendarSummaryRefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalendarDateType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "calendarDateRefId",
    "schoolInfoRefId",
    "schoolYear",
    "calendarDateType",
    "calendarDateNumber",
    "studentAttendance",
    "teacherAttendance",
    "administratorAttendance",
    "sifMetadata",
    "sifExtendedElements"
})
public class CalendarDate {

    @XmlElementRef(name = "CalendarDateRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> calendarDateRefId;
    @XmlElement(name = "SchoolInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlElement(name = "SchoolYear", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected XMLGregorianCalendar schoolYear;
    @XmlElement(name = "CalendarDateType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected CalendarDate.CalendarDateType calendarDateType;
    @XmlElementRef(name = "CalendarDateNumber", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> calendarDateNumber;
    @XmlElementRef(name = "StudentAttendance", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<CalendarDate.StudentAttendance> studentAttendance;
    @XmlElementRef(name = "TeacherAttendance", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<CalendarDate.TeacherAttendance> teacherAttendance;
    @XmlElementRef(name = "AdministratorAttendance", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<CalendarDate.AdministratorAttendance> administratorAttendance;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "CalendarSummaryRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String calendarSummaryRefId;

    /**
     * Gets the value of the calendarDateRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCalendarDateRefId() {
        return calendarDateRefId;
    }

    /**
     * Sets the value of the calendarDateRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCalendarDateRefId(JAXBElement<String> value) {
        this.calendarDateRefId = value;
    }

    /**
     * Gets the value of the schoolInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolInfoRefId() {
        return schoolInfoRefId;
    }

    /**
     * Sets the value of the schoolInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolInfoRefId(String value) {
        this.schoolInfoRefId = value;
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
     * Gets the value of the calendarDateType property.
     * 
     * @return
     *     possible object is
     *     {@link CalendarDate.CalendarDateType }
     *     
     */
    public CalendarDate.CalendarDateType getCalendarDateType() {
        return calendarDateType;
    }

    /**
     * Sets the value of the calendarDateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarDate.CalendarDateType }
     *     
     */
    public void setCalendarDateType(CalendarDate.CalendarDateType value) {
        this.calendarDateType = value;
    }

    /**
     * Gets the value of the calendarDateNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getCalendarDateNumber() {
        return calendarDateNumber;
    }

    /**
     * Sets the value of the calendarDateNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setCalendarDateNumber(JAXBElement<Long> value) {
        this.calendarDateNumber = value;
    }

    /**
     * Gets the value of the studentAttendance property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CalendarDate.StudentAttendance }{@code >}
     *     
     */
    public JAXBElement<CalendarDate.StudentAttendance> getStudentAttendance() {
        return studentAttendance;
    }

    /**
     * Sets the value of the studentAttendance property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CalendarDate.StudentAttendance }{@code >}
     *     
     */
    public void setStudentAttendance(JAXBElement<CalendarDate.StudentAttendance> value) {
        this.studentAttendance = value;
    }

    /**
     * Gets the value of the teacherAttendance property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CalendarDate.TeacherAttendance }{@code >}
     *     
     */
    public JAXBElement<CalendarDate.TeacherAttendance> getTeacherAttendance() {
        return teacherAttendance;
    }

    /**
     * Sets the value of the teacherAttendance property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CalendarDate.TeacherAttendance }{@code >}
     *     
     */
    public void setTeacherAttendance(JAXBElement<CalendarDate.TeacherAttendance> value) {
        this.teacherAttendance = value;
    }

    /**
     * Gets the value of the administratorAttendance property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CalendarDate.AdministratorAttendance }{@code >}
     *     
     */
    public JAXBElement<CalendarDate.AdministratorAttendance> getAdministratorAttendance() {
        return administratorAttendance;
    }

    /**
     * Sets the value of the administratorAttendance property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CalendarDate.AdministratorAttendance }{@code >}
     *     
     */
    public void setAdministratorAttendance(JAXBElement<CalendarDate.AdministratorAttendance> value) {
        this.administratorAttendance = value;
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
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the calendarSummaryRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarSummaryRefId() {
        return calendarSummaryRefId;
    }

    /**
     * Sets the value of the calendarSummaryRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarSummaryRefId(String value) {
        this.calendarSummaryRefId = value;
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
     *         &lt;element name="CountsTowardAttendance" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="Yes"/>
     *               &lt;enumeration value="No"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="AttendanceValue" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;minInclusive value="0"/>
     *               &lt;maxInclusive value="1"/>
     *               &lt;fractionDigits value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
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
        "countsTowardAttendance",
        "attendanceValue"
    })
    public static class AdministratorAttendance {

        @XmlElement(name = "CountsTowardAttendance", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String countsTowardAttendance;
        @XmlElement(name = "AttendanceValue", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected BigDecimal attendanceValue;

        /**
         * Gets the value of the countsTowardAttendance property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountsTowardAttendance() {
            return countsTowardAttendance;
        }

        /**
         * Sets the value of the countsTowardAttendance property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountsTowardAttendance(String value) {
            this.countsTowardAttendance = value;
        }

        /**
         * Gets the value of the attendanceValue property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getAttendanceValue() {
            return attendanceValue;
        }

        /**
         * Sets the value of the attendanceValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setAttendanceValue(BigDecimal value) {
            this.attendanceValue = value;
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
     *         &lt;element name="Code" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsCalendarEventType" minOccurs="0"/>
     *         &lt;element name="OtherCodeList" type="{http://www.sifassociation.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
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
        "code",
        "otherCodeList"
    })
    public static class CalendarDateType {

        @XmlElement(name = "Code", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String code;
        @XmlElementRef(name = "OtherCodeList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<OtherCodeListType> otherCodeList;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
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
     *         &lt;element name="CountsTowardAttendance" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="Yes"/>
     *               &lt;enumeration value="No"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="AttendanceValue" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;minInclusive value="0"/>
     *               &lt;maxInclusive value="1"/>
     *               &lt;fractionDigits value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
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
        "countsTowardAttendance",
        "attendanceValue"
    })
    public static class StudentAttendance {

        @XmlElement(name = "CountsTowardAttendance", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String countsTowardAttendance;
        @XmlElement(name = "AttendanceValue", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected BigDecimal attendanceValue;

        /**
         * Gets the value of the countsTowardAttendance property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountsTowardAttendance() {
            return countsTowardAttendance;
        }

        /**
         * Sets the value of the countsTowardAttendance property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountsTowardAttendance(String value) {
            this.countsTowardAttendance = value;
        }

        /**
         * Gets the value of the attendanceValue property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getAttendanceValue() {
            return attendanceValue;
        }

        /**
         * Sets the value of the attendanceValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setAttendanceValue(BigDecimal value) {
            this.attendanceValue = value;
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
     *         &lt;element name="CountsTowardAttendance" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *               &lt;enumeration value="Yes"/>
     *               &lt;enumeration value="No"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="AttendanceValue" minOccurs="0">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;minInclusive value="0"/>
     *               &lt;maxInclusive value="1"/>
     *               &lt;fractionDigits value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
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
        "countsTowardAttendance",
        "attendanceValue"
    })
    public static class TeacherAttendance {

        @XmlElement(name = "CountsTowardAttendance", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String countsTowardAttendance;
        @XmlElement(name = "AttendanceValue", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected BigDecimal attendanceValue;

        /**
         * Gets the value of the countsTowardAttendance property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCountsTowardAttendance() {
            return countsTowardAttendance;
        }

        /**
         * Sets the value of the countsTowardAttendance property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCountsTowardAttendance(String value) {
            this.countsTowardAttendance = value;
        }

        /**
         * Gets the value of the attendanceValue property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getAttendanceValue() {
            return attendanceValue;
        }

        /**
         * Sets the value of the attendanceValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setAttendanceValue(BigDecimal value) {
            this.attendanceValue = value;
        }

    }

}
