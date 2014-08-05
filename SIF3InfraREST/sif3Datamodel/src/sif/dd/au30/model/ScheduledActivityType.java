
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
 * <p>Java class for ScheduledActivityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ScheduledActivityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType"/>
 *         &lt;element name="TimeTableCellRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="DayId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="PeriodId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="TimeTableRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="ActivityDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="FinishTime" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="CellType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="TeacherList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TeacherCover" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="StaffPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType"/>
 *                             &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *                             &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *                             &lt;element name="FinishTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *                             &lt;element name="Credit" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="Supervision" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="Weighting" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
 *         &lt;element name="RoomList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RoomInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AddressList" type="{http://www.sifassociation.org/au/datamodel/1.3}AddressListType" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ActivityType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ActivityName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="ActivityComment" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="StudentList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StudentPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TeachingGroupList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="YearLevels" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="Override" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>AUCodeSetsYesOrNoCategoryType">
 *                 &lt;attribute name="DateOfOverride" type="{http://www.w3.org/2001/XMLSchema}date" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
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
@XmlType(name = "ScheduledActivityType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "schoolInfoRefId",
    "timeTableCellRefId",
    "dayId",
    "periodId",
    "timeTableRefId",
    "activityDate",
    "startTime",
    "finishTime",
    "cellType",
    "timeTableSubjectRefId",
    "teacherList",
    "roomList",
    "addressList",
    "location",
    "activityType",
    "activityName",
    "activityComment",
    "studentList",
    "teachingGroupList",
    "yearLevels",
    "override",
    "sifMetadata",
    "sifExtendedElements"
})
public class ScheduledActivityType {

    @XmlElement(name = "SchoolInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlElementRef(name = "TimeTableCellRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timeTableCellRefId;
    @XmlElementRef(name = "DayId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> dayId;
    @XmlElementRef(name = "PeriodId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> periodId;
    @XmlElementRef(name = "TimeTableRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timeTableRefId;
    @XmlElement(name = "ActivityDate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar activityDate;
    @XmlElement(name = "StartTime", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar startTime;
    @XmlElement(name = "FinishTime", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar finishTime;
    @XmlElementRef(name = "CellType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cellType;
    @XmlElementRef(name = "TimeTableSubjectRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timeTableSubjectRefId;
    @XmlElementRef(name = "TeacherList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ScheduledActivityType.TeacherList> teacherList;
    @XmlElementRef(name = "RoomList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ScheduledActivityType.RoomList> roomList;
    @XmlElementRef(name = "AddressList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressListType> addressList;
    @XmlElementRef(name = "Location", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> location;
    @XmlElementRef(name = "ActivityType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> activityType;
    @XmlElementRef(name = "ActivityName", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> activityName;
    @XmlElementRef(name = "ActivityComment", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> activityComment;
    @XmlElementRef(name = "StudentList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ScheduledActivityType.StudentList> studentList;
    @XmlElementRef(name = "TeachingGroupList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ScheduledActivityType.TeachingGroupList> teachingGroupList;
    @XmlElementRef(name = "YearLevels", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> yearLevels;
    @XmlElementRef(name = "Override", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<ScheduledActivityType.Override> override;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

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
     * Gets the value of the dayId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDayId() {
        return dayId;
    }

    /**
     * Sets the value of the dayId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDayId(JAXBElement<String> value) {
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
     * Gets the value of the timeTableRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimeTableRefId() {
        return timeTableRefId;
    }

    /**
     * Sets the value of the timeTableRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimeTableRefId(JAXBElement<String> value) {
        this.timeTableRefId = value;
    }

    /**
     * Gets the value of the activityDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActivityDate() {
        return activityDate;
    }

    /**
     * Sets the value of the activityDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActivityDate(XMLGregorianCalendar value) {
        this.activityDate = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the finishTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFinishTime() {
        return finishTime;
    }

    /**
     * Sets the value of the finishTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinishTime(XMLGregorianCalendar value) {
        this.finishTime = value;
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
     * Gets the value of the teacherList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.TeacherList }{@code >}
     *     
     */
    public JAXBElement<ScheduledActivityType.TeacherList> getTeacherList() {
        return teacherList;
    }

    /**
     * Sets the value of the teacherList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.TeacherList }{@code >}
     *     
     */
    public void setTeacherList(JAXBElement<ScheduledActivityType.TeacherList> value) {
        this.teacherList = value;
    }

    /**
     * Gets the value of the roomList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.RoomList }{@code >}
     *     
     */
    public JAXBElement<ScheduledActivityType.RoomList> getRoomList() {
        return roomList;
    }

    /**
     * Sets the value of the roomList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.RoomList }{@code >}
     *     
     */
    public void setRoomList(JAXBElement<ScheduledActivityType.RoomList> value) {
        this.roomList = value;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public JAXBElement<AddressListType> getAddressList() {
        return addressList;
    }

    /**
     * Sets the value of the addressList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public void setAddressList(JAXBElement<AddressListType> value) {
        this.addressList = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocation(JAXBElement<String> value) {
        this.location = value;
    }

    /**
     * Gets the value of the activityType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActivityType() {
        return activityType;
    }

    /**
     * Sets the value of the activityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActivityType(JAXBElement<String> value) {
        this.activityType = value;
    }

    /**
     * Gets the value of the activityName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActivityName() {
        return activityName;
    }

    /**
     * Sets the value of the activityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActivityName(JAXBElement<String> value) {
        this.activityName = value;
    }

    /**
     * Gets the value of the activityComment property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActivityComment() {
        return activityComment;
    }

    /**
     * Sets the value of the activityComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActivityComment(JAXBElement<String> value) {
        this.activityComment = value;
    }

    /**
     * Gets the value of the studentList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.StudentList }{@code >}
     *     
     */
    public JAXBElement<ScheduledActivityType.StudentList> getStudentList() {
        return studentList;
    }

    /**
     * Sets the value of the studentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.StudentList }{@code >}
     *     
     */
    public void setStudentList(JAXBElement<ScheduledActivityType.StudentList> value) {
        this.studentList = value;
    }

    /**
     * Gets the value of the teachingGroupList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.TeachingGroupList }{@code >}
     *     
     */
    public JAXBElement<ScheduledActivityType.TeachingGroupList> getTeachingGroupList() {
        return teachingGroupList;
    }

    /**
     * Sets the value of the teachingGroupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.TeachingGroupList }{@code >}
     *     
     */
    public void setTeachingGroupList(JAXBElement<ScheduledActivityType.TeachingGroupList> value) {
        this.teachingGroupList = value;
    }

    /**
     * Gets the value of the yearLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public JAXBElement<YearLevelsType> getYearLevels() {
        return yearLevels;
    }

    /**
     * Sets the value of the yearLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public void setYearLevels(JAXBElement<YearLevelsType> value) {
        this.yearLevels = value;
    }

    /**
     * Gets the value of the override property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.Override }{@code >}
     *     
     */
    public JAXBElement<ScheduledActivityType.Override> getOverride() {
        return override;
    }

    /**
     * Sets the value of the override property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ScheduledActivityType.Override }{@code >}
     *     
     */
    public void setOverride(JAXBElement<ScheduledActivityType.Override> value) {
        this.override = value;
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
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>AUCodeSetsYesOrNoCategoryType">
     *       &lt;attribute name="DateOfOverride" type="{http://www.w3.org/2001/XMLSchema}date" />
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
    public static class Override {

        @XmlValue
        protected AUCodeSetsYesOrNoCategoryType value;
        @XmlAttribute(name = "DateOfOverride")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateOfOverride;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link AUCodeSetsYesOrNoCategoryType }
         *     
         */
        public AUCodeSetsYesOrNoCategoryType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link AUCodeSetsYesOrNoCategoryType }
         *     
         */
        public void setValue(AUCodeSetsYesOrNoCategoryType value) {
            this.value = value;
        }

        /**
         * Gets the value of the dateOfOverride property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateOfOverride() {
            return dateOfOverride;
        }

        /**
         * Sets the value of the dateOfOverride property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateOfOverride(XMLGregorianCalendar value) {
            this.dateOfOverride = value;
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
     *         &lt;element name="RoomInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded"/>
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
        "roomInfoRefId"
    })
    public static class RoomList {

        @XmlElement(name = "RoomInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> roomInfoRefId;

        /**
         * Gets the value of the roomInfoRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the roomInfoRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRoomInfoRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getRoomInfoRefId() {
            if (roomInfoRefId == null) {
                roomInfoRefId = new ArrayList<String>();
            }
            return this.roomInfoRefId;
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
     *         &lt;element name="StudentPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded"/>
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
    public static class StudentList {

        @XmlElement(name = "StudentPersonalRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
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
     *         &lt;element name="TeacherCover" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="StaffPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType"/>
     *                   &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
     *                   &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
     *                   &lt;element name="FinishTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
     *                   &lt;element name="Credit" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="Supervision" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="Weighting" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
        "teacherCover"
    })
    public static class TeacherList {

        @XmlElement(name = "TeacherCover", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
        protected List<ScheduledActivityType.TeacherList.TeacherCover> teacherCover;

        /**
         * Gets the value of the teacherCover property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the teacherCover property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTeacherCover().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ScheduledActivityType.TeacherList.TeacherCover }
         * 
         * 
         */
        public List<ScheduledActivityType.TeacherList.TeacherCover> getTeacherCover() {
            if (teacherCover == null) {
                teacherCover = new ArrayList<ScheduledActivityType.TeacherList.TeacherCover>();
            }
            return this.teacherCover;
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
         *         &lt;element name="StaffPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType"/>
         *         &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
         *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
         *         &lt;element name="FinishTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
         *         &lt;element name="Credit" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="Supervision" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="Weighting" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
            "startTime",
            "finishTime",
            "credit",
            "supervision",
            "weighting"
        })
        public static class TeacherCover {

            @XmlElement(name = "StaffPersonalRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String staffPersonalRefId;
            @XmlElementRef(name = "StaffLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> staffLocalId;
            @XmlElementRef(name = "StartTime", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> startTime;
            @XmlElementRef(name = "FinishTime", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<XMLGregorianCalendar> finishTime;
            @XmlElementRef(name = "Credit", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> credit;
            @XmlElementRef(name = "Supervision", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<String> supervision;
            @XmlElementRef(name = "Weighting", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<BigDecimal> weighting;

            /**
             * Gets the value of the staffPersonalRefId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStaffPersonalRefId() {
                return staffPersonalRefId;
            }

            /**
             * Sets the value of the staffPersonalRefId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStaffPersonalRefId(String value) {
                this.staffPersonalRefId = value;
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
             * Gets the value of the finishTime property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *     
             */
            public JAXBElement<XMLGregorianCalendar> getFinishTime() {
                return finishTime;
            }

            /**
             * Sets the value of the finishTime property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
             *     
             */
            public void setFinishTime(JAXBElement<XMLGregorianCalendar> value) {
                this.finishTime = value;
            }

            /**
             * Gets the value of the credit property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getCredit() {
                return credit;
            }

            /**
             * Sets the value of the credit property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setCredit(JAXBElement<String> value) {
                this.credit = value;
            }

            /**
             * Gets the value of the supervision property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public JAXBElement<String> getSupervision() {
                return supervision;
            }

            /**
             * Sets the value of the supervision property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link String }{@code >}
             *     
             */
            public void setSupervision(JAXBElement<String> value) {
                this.supervision = value;
            }

            /**
             * Gets the value of the weighting property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *     
             */
            public JAXBElement<BigDecimal> getWeighting() {
                return weighting;
            }

            /**
             * Sets the value of the weighting property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
             *     
             */
            public void setWeighting(JAXBElement<BigDecimal> value) {
                this.weighting = value;
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
     *         &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded"/>
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
        "teachingGroupRefId"
    })
    public static class TeachingGroupList {

        @XmlElement(name = "TeachingGroupRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> teachingGroupRefId;

        /**
         * Gets the value of the teachingGroupRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the teachingGroupRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTeachingGroupRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getTeachingGroupRefId() {
            if (teachingGroupRefId == null) {
                teachingGroupRefId = new ArrayList<String>();
            }
            return this.teachingGroupRefId;
        }

    }

}
