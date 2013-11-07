
package sif.dd.au30.model;

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
 *         This object provides the link between Period Attendance and the school's timetable.  It allows subject and teaching group information to be communicated to/from the attendance system.
 *       
 * 
 * <p>Java class for SessionInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SessionInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="TimeTableCellRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolYear" minOccurs="0"/>
 *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}LocalId" minOccurs="0"/>
 *         &lt;element name="TimeTableSubjectLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="TeachingGroupLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="StaffPersonalLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="RoomNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="DayId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="PeriodId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SessionDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element name="FinishTime" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *         &lt;element name="RollMarked" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
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
@XmlType(name = "SessionInfoType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "schoolInfoRefId",
    "timeTableCellRefId",
    "schoolYear",
    "localId",
    "timeTableSubjectLocalId",
    "teachingGroupLocalId",
    "schoolLocalId",
    "staffPersonalLocalId",
    "roomNumber",
    "dayId",
    "periodId",
    "sessionDate",
    "startTime",
    "finishTime",
    "rollMarked",
    "sifMetadata",
    "sifExtendedElements"
})
public class SessionInfoType {

    @XmlElement(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlElement(name = "TimeTableCellRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String timeTableCellRefId;
    @XmlElement(name = "SchoolYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected XMLGregorianCalendar schoolYear;
    @XmlElement(name = "LocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String localId;
    @XmlElement(name = "TimeTableSubjectLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String timeTableSubjectLocalId;
    @XmlElement(name = "TeachingGroupLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String teachingGroupLocalId;
    @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolLocalId;
    @XmlElementRef(name = "StaffPersonalLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> staffPersonalLocalId;
    @XmlElementRef(name = "RoomNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomNumber;
    @XmlElement(name = "DayId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dayId;
    @XmlElement(name = "PeriodId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String periodId;
    @XmlElement(name = "SessionDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sessionDate;
    @XmlElementRef(name = "StartTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> startTime;
    @XmlElementRef(name = "FinishTime", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> finishTime;
    @XmlElementRef(name = "RollMarked", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> rollMarked;
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
     *     {@link String }
     *     
     */
    public String getTimeTableCellRefId() {
        return timeTableCellRefId;
    }

    /**
     * Sets the value of the timeTableCellRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeTableCellRefId(String value) {
        this.timeTableCellRefId = value;
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
     * Gets the value of the timeTableSubjectLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeTableSubjectLocalId() {
        return timeTableSubjectLocalId;
    }

    /**
     * Sets the value of the timeTableSubjectLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeTableSubjectLocalId(String value) {
        this.timeTableSubjectLocalId = value;
    }

    /**
     * Gets the value of the teachingGroupLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeachingGroupLocalId() {
        return teachingGroupLocalId;
    }

    /**
     * Sets the value of the teachingGroupLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeachingGroupLocalId(String value) {
        this.teachingGroupLocalId = value;
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
     * Gets the value of the staffPersonalLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStaffPersonalLocalId() {
        return staffPersonalLocalId;
    }

    /**
     * Sets the value of the staffPersonalLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStaffPersonalLocalId(JAXBElement<String> value) {
        this.staffPersonalLocalId = value;
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
     * Gets the value of the sessionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSessionDate() {
        return sessionDate;
    }

    /**
     * Sets the value of the sessionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSessionDate(XMLGregorianCalendar value) {
        this.sessionDate = value;
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
     * Gets the value of the rollMarked property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getRollMarked() {
        return rollMarked;
    }

    /**
     * Sets the value of the rollMarked property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setRollMarked(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.rollMarked = value;
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

}
