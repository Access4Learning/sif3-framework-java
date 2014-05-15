
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Previous, alternate or other names or aliases associated with a person.
 * 
 * <p>Java class for OtherNamesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherNamesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.sifassociation.org/au/datamodel/1.3}OtherNameType">
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
@XmlType(name = "OtherNamesType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "name"
})
public class OtherNamesType {

    @XmlElement(name = "Name", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<OtherNamesType.Name> name;

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OtherNamesType.Name }
     * 
     * 
     */
    public List<OtherNamesType.Name> getName() {
        if (name == null) {
            name = new ArrayList<OtherNamesType.Name>();
        }
        return this.name;
    }


    /**
     * 
     *         Name of the person.  Note: Type value of LGL may not occur here.
     *       
     * 
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.sifassociation.org/au/datamodel/1.3}OtherNameType">
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
    public static class Name
        extends OtherNameType
    {

        @XmlAttribute(name = "SIF_Action")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sifAction;

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
