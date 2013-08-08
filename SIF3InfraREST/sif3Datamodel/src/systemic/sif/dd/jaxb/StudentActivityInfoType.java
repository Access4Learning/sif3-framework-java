
package systemic.sif.dd.jaxb;

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
 * Co-curricular or extra-curricular activities (e.g., student organizations, inter-school sports, athletics, publications, band, orchestra, and service activities) in which students may participate.
 * 
 * <p>Java class for StudentActivityInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentActivityInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="StudentActivityType" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsActivityInvolvementCodeType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="StudentActivityLevel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="YearLevels" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="CurricularStatus" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsActivityTypeType" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocationType" minOccurs="0"/>
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
@XmlType(name = "StudentActivityInfoType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "title",
    "description",
    "studentActivityType",
    "studentActivityLevel",
    "yearLevels",
    "curricularStatus",
    "location",
    "sifMetadata",
    "sifExtendedElements"
})
public class StudentActivityInfoType {

    @XmlElement(name = "Title", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String title;
    @XmlElementRef(name = "Description", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElement(name = "StudentActivityType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected StudentActivityInfoType.StudentActivityType studentActivityType;
    @XmlElementRef(name = "StudentActivityLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> studentActivityLevel;
    @XmlElementRef(name = "YearLevels", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> yearLevels;
    @XmlElementRef(name = "CurricularStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> curricularStatus;
    @XmlElementRef(name = "Location", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LocationType> location;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = value;
    }

    /**
     * Gets the value of the studentActivityType property.
     * 
     * @return
     *     possible object is
     *     {@link StudentActivityInfoType.StudentActivityType }
     *     
     */
    public StudentActivityInfoType.StudentActivityType getStudentActivityType() {
        return studentActivityType;
    }

    /**
     * Sets the value of the studentActivityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentActivityInfoType.StudentActivityType }
     *     
     */
    public void setStudentActivityType(StudentActivityInfoType.StudentActivityType value) {
        this.studentActivityType = value;
    }

    /**
     * Gets the value of the studentActivityLevel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStudentActivityLevel() {
        return studentActivityLevel;
    }

    /**
     * Sets the value of the studentActivityLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStudentActivityLevel(JAXBElement<String> value) {
        this.studentActivityLevel = value;
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
     * Gets the value of the curricularStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCurricularStatus() {
        return curricularStatus;
    }

    /**
     * Sets the value of the curricularStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCurricularStatus(JAXBElement<String> value) {
        this.curricularStatus = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LocationType }{@code >}
     *     
     */
    public JAXBElement<LocationType> getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LocationType }{@code >}
     *     
     */
    public void setLocation(JAXBElement<LocationType> value) {
        this.location = value;
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
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsActivityInvolvementCodeType" minOccurs="0"/>
     *         &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
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
    public static class StudentActivityType {

        @XmlElement(name = "Code", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String code;
        @XmlElementRef(name = "OtherCodeList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
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

}
