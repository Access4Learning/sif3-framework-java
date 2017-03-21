
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for phaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="phaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="states" type="{http://www.sifassociation.org/infrastructure/3.2.1}stateCollectionType"/>
 *         &lt;element name="required" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="rights" type="{http://www.sifassociation.org/infrastructure/3.2.1}rightsType"/>
 *         &lt;element name="statesRights" type="{http://www.sifassociation.org/infrastructure/3.2.1}rightsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "phaseType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "name",
    "states",
    "required",
    "rights",
    "statesRights"
})
public class PhaseType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String name;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected StateCollectionType states;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected boolean required;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected RightsType rights;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected RightsType statesRights;

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
     * Gets the value of the states property.
     * 
     * @return
     *     possible object is
     *     {@link StateCollectionType }
     *     
     */
    public StateCollectionType getStates() {
        return states;
    }

    /**
     * Sets the value of the states property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateCollectionType }
     *     
     */
    public void setStates(StateCollectionType value) {
        this.states = value;
    }

    /**
     * Gets the value of the required property.
     * 
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets the value of the required property.
     * 
     */
    public void setRequired(boolean value) {
        this.required = value;
    }

    /**
     * Gets the value of the rights property.
     * 
     * @return
     *     possible object is
     *     {@link RightsType }
     *     
     */
    public RightsType getRights() {
        return rights;
    }

    /**
     * Sets the value of the rights property.
     * 
     * @param value
     *     allowed object is
     *     {@link RightsType }
     *     
     */
    public void setRights(RightsType value) {
        this.rights = value;
    }

    /**
     * Gets the value of the statesRights property.
     * 
     * @return
     *     possible object is
     *     {@link RightsType }
     *     
     */
    public RightsType getStatesRights() {
        return statesRights;
    }

    /**
     * Sets the value of the statesRights property.
     * 
     * @param value
     *     allowed object is
     *     {@link RightsType }
     *     
     */
    public void setStatesRights(RightsType value) {
        this.statesRights = value;
    }

}
