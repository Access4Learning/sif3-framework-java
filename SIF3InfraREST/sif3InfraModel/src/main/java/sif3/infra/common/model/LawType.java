
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Information about the specific law.
 * 
 * <p>Java class for lawType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lawType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lawName" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="lawDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lawType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "lawName",
    "lawDescription"
})
public class LawType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String lawName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String lawDescription;

    /**
     * Gets the value of the lawName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawName() {
        return lawName;
    }

    /**
     * Sets the value of the lawName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawName(String value) {
        this.lawName = value;
    }

    /**
     * Gets the value of the lawDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawDescription() {
        return lawDescription;
    }

    /**
     * Sets the value of the lawDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawDescription(String value) {
        this.lawDescription = value;
    }

}
