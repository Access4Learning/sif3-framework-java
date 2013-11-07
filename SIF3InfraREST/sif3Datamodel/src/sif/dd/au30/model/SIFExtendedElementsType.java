
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SIF_ExtendedElementsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_ExtendedElementsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SIF_ExtendedElement" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.SIFinfo.org/au/datamodel/1.3}ExtendedContentType">
 *                 &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *                 &lt;attribute name="SIF_Action">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="Delete"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
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
@XmlType(name = "SIF_ExtendedElementsType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "sifExtendedElement"
})
public class SIFExtendedElementsType {

    @XmlElement(name = "SIF_ExtendedElement", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected List<SIFExtendedElementsType.SIFExtendedElement> sifExtendedElement;

    /**
     * Gets the value of the sifExtendedElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sifExtendedElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSIFExtendedElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SIFExtendedElementsType.SIFExtendedElement }
     * 
     * 
     */
    public List<SIFExtendedElementsType.SIFExtendedElement> getSIFExtendedElement() {
        if (sifExtendedElement == null) {
            sifExtendedElement = new ArrayList<SIFExtendedElementsType.SIFExtendedElement>();
        }
        return this.sifExtendedElement;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.SIFinfo.org/au/datamodel/1.3}ExtendedContentType">
     *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
     *       &lt;attribute name="SIF_Action">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="Delete"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class SIFExtendedElement
        extends ExtendedContentType
    {

        @XmlAttribute(name = "Name", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String name;
        @XmlAttribute(name = "SIF_Action")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sifAction;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the sifAction property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSIFAction() {
            return sifAction;
        }

        /**
         * Sets the value of the sifAction property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSIFAction(String value) {
            this.sifAction = value;
        }

    }

}
