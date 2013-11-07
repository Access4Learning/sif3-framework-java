
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Extends BaseNameType to allow for Type attribute values excluding "name of record."  Used for other names to
 *         be included in addition to the name of record in objects.
 *       
 * 
 * <p>Java class for OtherNameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherNameType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.SIFinfo.org/au/datamodel/1.3}BaseNameType">
 *       &lt;attribute name="Type" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsNameUsageTypeType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherNameType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlSeeAlso({
    sif.dd.au30.model.OtherNamesType.Name.class
})
public class OtherNameType
    extends BaseNameType
{

    @XmlAttribute(name = "Type", required = true)
    protected AUCodeSetsNameUsageTypeType type;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link AUCodeSetsNameUsageTypeType }
     *     
     */
    public AUCodeSetsNameUsageTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AUCodeSetsNameUsageTypeType }
     *     
     */
    public void setType(AUCodeSetsNameUsageTypeType value) {
        this.type = value;
    }

}
