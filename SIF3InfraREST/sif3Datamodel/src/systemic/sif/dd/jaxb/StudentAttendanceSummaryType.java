
package systemic.sif.dd.jaxb;

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
 * This object provides a summary of a student's daily attendance and membership information for a given school during the time period between the StartDate and EndDate, inclusive.  As such, there may be multiple instances of this object for a student in a school over the course of a school year.
 * 
 * <p>Java class for StudentAttendanceSummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentAttendanceSummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StudentAttendanceSummaryRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="StartDay" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="EndDay" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="FTE" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="1"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DaysAttended" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="ExcusedAbsences" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="UnexcusedAbsences" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DaysTardy" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DaysInMembership" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StudentPersonalRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *       &lt;attribute name="SchoolInfoRefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" />
 *       &lt;attribute name="SchoolYear" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolYearType" />
 *       &lt;attribute name="StartDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="EndDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentAttendanceSummaryType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "studentAttendanceSummaryRefId",
    "startDay",
    "endDay",
    "fte",
    "daysAttended",
    "excusedAbsences",
    "unexcusedAbsences",
    "daysTardy",
    "daysInMembership",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentAttendanceSummaryType {

    @XmlElementRef(name = "StudentAttendanceSummaryRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> studentAttendanceSummaryRefId;
    @XmlElementRef(name = "StartDay", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> startDay;
    @XmlElementRef(name = "EndDay", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<Long> endDay;
    @XmlElementRef(name = "FTE", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> fte;
    @XmlElement(name = "DaysAttended", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected BigDecimal daysAttended;
    @XmlElement(name = "ExcusedAbsences", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected BigDecimal excusedAbsences;
    @XmlElement(name = "UnexcusedAbsences", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected BigDecimal unexcusedAbsences;
    @XmlElementRef(name = "DaysTardy", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> daysTardy;
    @XmlElement(name = "DaysInMembership", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected BigDecimal daysInMembership;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "StudentPersonalRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String studentPersonalRefId;
    @XmlAttribute(name = "SchoolInfoRefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlAttribute(name = "SchoolYear", required = true)
    protected XMLGregorianCalendar schoolYear;
    @XmlAttribute(name = "StartDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlAttribute(name = "EndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;

    /**
     * Gets the value of the studentAttendanceSummaryRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStudentAttendanceSummaryRefId() {
        return studentAttendanceSummaryRefId;
    }

    /**
     * Sets the value of the studentAttendanceSummaryRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStudentAttendanceSummaryRefId(JAXBElement<String> value) {
        this.studentAttendanceSummaryRefId = value;
    }

    /**
     * Gets the value of the startDay property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getStartDay() {
        return startDay;
    }

    /**
     * Sets the value of the startDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setStartDay(JAXBElement<Long> value) {
        this.startDay = value;
    }

    /**
     * Gets the value of the endDay property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public JAXBElement<Long> getEndDay() {
        return endDay;
    }

    /**
     * Sets the value of the endDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Long }{@code >}
     *     
     */
    public void setEndDay(JAXBElement<Long> value) {
        this.endDay = value;
    }

    /**
     * Gets the value of the fte property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getFTE() {
        return fte;
    }

    /**
     * Sets the value of the fte property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setFTE(JAXBElement<BigDecimal> value) {
        this.fte = value;
    }

    /**
     * Gets the value of the daysAttended property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDaysAttended() {
        return daysAttended;
    }

    /**
     * Sets the value of the daysAttended property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDaysAttended(BigDecimal value) {
        this.daysAttended = value;
    }

    /**
     * Gets the value of the excusedAbsences property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExcusedAbsences() {
        return excusedAbsences;
    }

    /**
     * Sets the value of the excusedAbsences property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExcusedAbsences(BigDecimal value) {
        this.excusedAbsences = value;
    }

    /**
     * Gets the value of the unexcusedAbsences property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnexcusedAbsences() {
        return unexcusedAbsences;
    }

    /**
     * Sets the value of the unexcusedAbsences property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnexcusedAbsences(BigDecimal value) {
        this.unexcusedAbsences = value;
    }

    /**
     * Gets the value of the daysTardy property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getDaysTardy() {
        return daysTardy;
    }

    /**
     * Sets the value of the daysTardy property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setDaysTardy(JAXBElement<BigDecimal> value) {
        this.daysTardy = value;
    }

    /**
     * Gets the value of the daysInMembership property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDaysInMembership() {
        return daysInMembership;
    }

    /**
     * Sets the value of the daysInMembership property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDaysInMembership(BigDecimal value) {
        this.daysInMembership = value;
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
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

}
