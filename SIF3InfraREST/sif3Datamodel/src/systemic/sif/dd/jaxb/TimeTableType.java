
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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         The purpose of this object is to define a schedule or Time Table structure/skeleton for the school.  The Time Table is produced by the Time Tabling software.  Once the Time Table is produced, the time tabling software creates the schedule based on business rules and constraints to fit into the time table structure.
 *       
 * 
 * <p>Java class for TimeTableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeTableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolYear" minOccurs="0"/>
 *         &lt;element name="LocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="DaysPerCycle" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="PeriodsPerDay" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="TeachingPeriodsPerDay" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="SchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="TimeTableCreationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="TimeTableDayList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TimeTableDay" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DayId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="DayTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="TimeTablePeriodList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="TimeTablePeriod" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="PeriodId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                                                 &lt;element name="PeriodTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
@XmlType(name = "TimeTableType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "schoolInfoRefId",
    "schoolYear",
    "localId",
    "title",
    "daysPerCycle",
    "periodsPerDay",
    "teachingPeriodsPerDay",
    "schoolLocalId",
    "schoolName",
    "timeTableCreationDate",
    "startDate",
    "endDate",
    "timeTableDayList",
    "sifMetadata",
    "sifExtendedElements"
})
public class TimeTableType {

    @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolInfoRefId;
    @XmlElement(name = "SchoolYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected XMLGregorianCalendar schoolYear;
    @XmlElementRef(name = "LocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> localId;
    @XmlElement(name = "Title", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String title;
    @XmlElement(name = "DaysPerCycle", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "unsignedInt")
    protected Long daysPerCycle;
    @XmlElement(name = "PeriodsPerDay", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "unsignedInt")
    protected Long periodsPerDay;
    @XmlElementRef(name = "TeachingPeriodsPerDay", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> teachingPeriodsPerDay;
    @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolLocalId;
    @XmlElementRef(name = "SchoolName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolName;
    @XmlElementRef(name = "TimeTableCreationDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> timeTableCreationDate;
    @XmlElementRef(name = "StartDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> startDate;
    @XmlElementRef(name = "EndDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> endDate;
    @XmlElement(name = "TimeTableDayList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected TimeTableType.TimeTableDayList timeTableDayList;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

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
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the daysPerCycle property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDaysPerCycle() {
        return daysPerCycle;
    }

    /**
     * Sets the value of the daysPerCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDaysPerCycle(Long value) {
        this.daysPerCycle = value;
    }

    /**
     * Gets the value of the periodsPerDay property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPeriodsPerDay() {
        return periodsPerDay;
    }

    /**
     * Sets the value of the periodsPerDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPeriodsPerDay(Long value) {
        this.periodsPerDay = value;
    }

    /**
     * Gets the value of the teachingPeriodsPerDay property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getTeachingPeriodsPerDay() {
        return teachingPeriodsPerDay;
    }

    /**
     * Sets the value of the teachingPeriodsPerDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setTeachingPeriodsPerDay(JAXBElement<Long> value) {
        this.teachingPeriodsPerDay = value;
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
     * Gets the value of the timeTableCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getTimeTableCreationDate() {
        return timeTableCreationDate;
    }

    /**
     * Sets the value of the timeTableCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setTimeTableCreationDate(JAXBElement<XMLGregorianCalendar> value) {
        this.timeTableCreationDate = value;
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
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEndDate(JAXBElement<XMLGregorianCalendar> value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the timeTableDayList property.
     * 
     * @return
     *     possible object is
     *     {@link TimeTableType.TimeTableDayList }
     *     
     */
    public TimeTableType.TimeTableDayList getTimeTableDayList() {
        return timeTableDayList;
    }

    /**
     * Sets the value of the timeTableDayList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeTableType.TimeTableDayList }
     *     
     */
    public void setTimeTableDayList(TimeTableType.TimeTableDayList value) {
        this.timeTableDayList = value;
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
     *         &lt;element name="TimeTableDay" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="DayId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="DayTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="TimeTablePeriodList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="TimeTablePeriod" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="PeriodId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                                       &lt;element name="PeriodTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "timeTableDay"
    })
    public static class TimeTableDayList {

        @XmlElement(name = "TimeTableDay", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<TimeTableType.TimeTableDayList.TimeTableDay> timeTableDay;

        /**
         * Gets the value of the timeTableDay property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the timeTableDay property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTimeTableDay().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TimeTableType.TimeTableDayList.TimeTableDay }
         * 
         * 
         */
        public List<TimeTableType.TimeTableDayList.TimeTableDay> getTimeTableDay() {
            if (timeTableDay == null) {
                timeTableDay = new ArrayList<TimeTableType.TimeTableDayList.TimeTableDay>();
            }
            return this.timeTableDay;
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
         *         &lt;element name="DayId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="DayTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="TimeTablePeriodList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="TimeTablePeriod" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="PeriodId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *                             &lt;element name="PeriodTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
            "dayId",
            "dayTitle",
            "timeTablePeriodList"
        })
        public static class TimeTableDay {

            @XmlElement(name = "DayId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String dayId;
            @XmlElement(name = "DayTitle", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String dayTitle;
            @XmlElement(name = "TimeTablePeriodList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList timeTablePeriodList;

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
             * Gets the value of the dayTitle property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDayTitle() {
                return dayTitle;
            }

            /**
             * Sets the value of the dayTitle property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDayTitle(String value) {
                this.dayTitle = value;
            }

            /**
             * Gets the value of the timeTablePeriodList property.
             * 
             * @return
             *     possible object is
             *     {@link TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList }
             *     
             */
            public TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList getTimeTablePeriodList() {
                return timeTablePeriodList;
            }

            /**
             * Sets the value of the timeTablePeriodList property.
             * 
             * @param value
             *     allowed object is
             *     {@link TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList }
             *     
             */
            public void setTimeTablePeriodList(TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList value) {
                this.timeTablePeriodList = value;
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
             *         &lt;element name="TimeTablePeriod" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="PeriodId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
             *                   &lt;element name="PeriodTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
                "timeTablePeriod"
            })
            public static class TimeTablePeriodList {

                @XmlElement(name = "TimeTablePeriod", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                protected List<TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod> timeTablePeriod;

                /**
                 * Gets the value of the timeTablePeriod property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the timeTablePeriod property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getTimeTablePeriod().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod }
                 * 
                 * 
                 */
                public List<TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod> getTimeTablePeriod() {
                    if (timeTablePeriod == null) {
                        timeTablePeriod = new ArrayList<TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod>();
                    }
                    return this.timeTablePeriod;
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
                 *         &lt;element name="PeriodId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
                 *         &lt;element name="PeriodTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
                    "periodId",
                    "periodTitle"
                })
                public static class TimeTablePeriod {

                    @XmlElement(name = "PeriodId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
                    protected String periodId;
                    @XmlElement(name = "PeriodTitle", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
                    @XmlSchemaType(name = "normalizedString")
                    protected String periodTitle;

                    /**
                     * Gets the value of the periodId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPeriodId() {
                        return periodId;
                    }

                    /**
                     * Sets the value of the periodId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPeriodId(String value) {
                        this.periodId = value;
                    }

                    /**
                     * Gets the value of the periodTitle property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPeriodTitle() {
                        return periodTitle;
                    }

                    /**
                     * Sets the value of the periodTitle property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPeriodTitle(String value) {
                        this.periodTitle = value;
                    }

                }

            }

        }

    }

}
