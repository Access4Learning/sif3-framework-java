
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * <p>Java class for parameterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parameterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="default" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="description" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="required" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="enumerations" type="{http://www.sifassociation.org/infrastructure/3.3}enumerationsType" minOccurs="0"/>
 *         &lt;element name="range" type="{http://www.sifassociation.org/infrastructure/3.3}rangeType" minOccurs="0"/>
 *         &lt;element name="minLength" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="maxLength" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parameterType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "name",
    "type",
    "_default",
    "description",
    "required",
    "enumerations",
    "range",
    "minLength",
    "maxLength"
})
public class ParameterType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String name;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String type;
    @XmlElement(name = "default", namespace = "http://www.sifassociation.org/infrastructure/3.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String _default;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String description;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected Boolean required;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected EnumerationsType enumerations;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected RangeType range;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    @XmlSchemaType(name = "unsignedInt")
    protected Long minLength;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    @XmlSchemaType(name = "unsignedInt")
    protected Long maxLength;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefault(String value) {
        this._default = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the required property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequired() {
        return required;
    }

    /**
     * Sets the value of the required property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequired(Boolean value) {
        this.required = value;
    }

    /**
     * Gets the value of the enumerations property.
     * 
     * @return
     *     possible object is
     *     {@link EnumerationsType }
     *     
     */
    public EnumerationsType getEnumerations() {
        return enumerations;
    }

    /**
     * Sets the value of the enumerations property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumerationsType }
     *     
     */
    public void setEnumerations(EnumerationsType value) {
        this.enumerations = value;
    }

    /**
     * Gets the value of the range property.
     * 
     * @return
     *     possible object is
     *     {@link RangeType }
     *     
     */
    public RangeType getRange() {
        return range;
    }

    /**
     * Sets the value of the range property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeType }
     *     
     */
    public void setRange(RangeType value) {
        this.range = value;
    }

    /**
     * Gets the value of the minLength property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMinLength(Long value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxLength(Long value) {
        this.maxLength = value;
    }

}
