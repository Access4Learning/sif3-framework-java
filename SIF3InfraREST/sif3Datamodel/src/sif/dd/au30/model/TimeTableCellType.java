
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


/**
 * 
 *         The purpose of this object is to identify a specific cell within a particular TimeTable.  A time table is a structure that represents all the available days and times (periods) for which a particular teaching group, or subject can be scheduled.
 *       
 * 
 * <p>Java class for TimeTableCellType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeTableCellType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeTableRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="TimeTableSubjectRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="TeachingGroupRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="RoomInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="StaffPersonalRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="TimeTableLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SubjectLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="TeachingGroupLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="RoomNumber" type="{http://www.sifassociation.org/au/datamodel/1.3}HomeroomNumberType" minOccurs="0"/>
 *         &lt;element name="StaffLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="DayId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="PeriodId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="CellType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
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
@XmlType(name = "TimeTableCellType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "timeTableRefId",
    "timeTableSubjectRefId",
    "teachingGroupRefId",
    "roomInfoRefId",
    "staffPersonalRefId",
    "timeTableLocalId",
    "subjectLocalId",
    "teachingGroupLocalId",
    "roomNumber",
    "staffLocalId",
    "dayId",
    "periodId",
    "cellType",
    "schoolInfoRefId",
    "schoolLocalId",
    "sifMetadata",
    "sifExtendedElements"
})
public class TimeTableCellType {

    @XmlElement(name = "TimeTableRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String timeTableRefId;
    @XmlElement(name = "TimeTableSubjectRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String timeTableSubjectRefId;
    @XmlElement(name = "TeachingGroupRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String teachingGroupRefId;
    @XmlElement(name = "RoomInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String roomInfoRefId;
    @XmlElementRef(name = "StaffPersonalRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> staffPersonalRefId;
    @XmlElementRef(name = "TimeTableLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> timeTableLocalId;
    @XmlElementRef(name = "SubjectLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> subjectLocalId;
    @XmlElementRef(name = "TeachingGroupLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> teachingGroupLocalId;
    @XmlElementRef(name = "RoomNumber", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomNumber;
    @XmlElementRef(name = "StaffLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> staffLocalId;
    @XmlElement(name = "DayId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dayId;
    @XmlElement(name = "PeriodId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String periodId;
    @XmlElement(name = "CellType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String cellType;
    @XmlElementRef(name = "SchoolInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolInfoRefId;
    @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolLocalId;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the timeTableRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeTableRefId() {
        return timeTableRefId;
    }

    /**
     * Sets the value of the timeTableRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeTableRefId(String value) {
        this.timeTableRefId = value;
    }

    /**
     * Gets the value of the timeTableSubjectRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeTableSubjectRefId() {
        return timeTableSubjectRefId;
    }

    /**
     * Sets the value of the timeTableSubjectRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeTableSubjectRefId(String value) {
        this.timeTableSubjectRefId = value;
    }

    /**
     * Gets the value of the teachingGroupRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeachingGroupRefId() {
        return teachingGroupRefId;
    }

    /**
     * Sets the value of the teachingGroupRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeachingGroupRefId(String value) {
        this.teachingGroupRefId = value;
    }

    /**
     * Gets the value of the roomInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomInfoRefId() {
        return roomInfoRefId;
    }

    /**
     * Sets the value of the roomInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomInfoRefId(String value) {
        this.roomInfoRefId = value;
    }

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
     * Gets the value of the timeTableLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTimeTableLocalId() {
        return timeTableLocalId;
    }

    /**
     * Sets the value of the timeTableLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTimeTableLocalId(JAXBElement<String> value) {
        this.timeTableLocalId = value;
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
     * Gets the value of the teachingGroupLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTeachingGroupLocalId() {
        return teachingGroupLocalId;
    }

    /**
     * Sets the value of the teachingGroupLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTeachingGroupLocalId(JAXBElement<String> value) {
        this.teachingGroupLocalId = value;
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
     * Gets the value of the cellType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellType() {
        return cellType;
    }

    /**
     * Sets the value of the cellType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellType(String value) {
        this.cellType = value;
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
