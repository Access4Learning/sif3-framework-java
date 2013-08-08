
package systemic.sif.dd.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A commonly defined container for metadata elements defined within SIF.
 * 
 * <p>Java class for SIF_MetadataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_MetadataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeElements" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}TimeElement" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LifeCycle" type="{http://www.SIFinfo.org/au/datamodel/1.3}LifeCycleType" minOccurs="0"/>
 *         &lt;element name="EducationFilter" type="{http://www.SIFinfo.org/au/datamodel/1.3}EducationFilterType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF_MetadataType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "timeElements",
    "lifeCycle",
    "educationFilter"
})
public class SIFMetadataType {

    @XmlElementRef(name = "TimeElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType.TimeElements> timeElements;
    @XmlElementRef(name = "LifeCycle", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LifeCycleType> lifeCycle;
    @XmlElementRef(name = "EducationFilter", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<EducationFilterType> educationFilter;

    /**
     * Gets the value of the timeElements property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType.TimeElements }{@code >}
     *     
     */
    public JAXBElement<SIFMetadataType.TimeElements> getTimeElements() {
        return timeElements;
    }

    /**
     * Sets the value of the timeElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType.TimeElements }{@code >}
     *     
     */
    public void setTimeElements(JAXBElement<SIFMetadataType.TimeElements> value) {
        this.timeElements = value;
    }

    /**
     * Gets the value of the lifeCycle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LifeCycleType }{@code >}
     *     
     */
    public JAXBElement<LifeCycleType> getLifeCycle() {
        return lifeCycle;
    }

    /**
     * Sets the value of the lifeCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LifeCycleType }{@code >}
     *     
     */
    public void setLifeCycle(JAXBElement<LifeCycleType> value) {
        this.lifeCycle = value;
    }

    /**
     * Gets the value of the educationFilter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EducationFilterType }{@code >}
     *     
     */
    public JAXBElement<EducationFilterType> getEducationFilter() {
        return educationFilter;
    }

    /**
     * Sets the value of the educationFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EducationFilterType }{@code >}
     *     
     */
    public void setEducationFilter(JAXBElement<EducationFilterType> value) {
        this.educationFilter = value;
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
     *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}TimeElement" maxOccurs="unbounded" minOccurs="0"/>
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
        "timeElement"
    })
    public static class TimeElements {

        @XmlElement(name = "TimeElement", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<TimeElementType> timeElement;

        /**
         * Gets the value of the timeElement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the timeElement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTimeElement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TimeElementType }
         * 
         * 
         */
        public List<TimeElementType> getTimeElement() {
            if (timeElement == null) {
                timeElement = new ArrayList<TimeElementType>();
            }
            return this.timeElement;
        }

    }

}
