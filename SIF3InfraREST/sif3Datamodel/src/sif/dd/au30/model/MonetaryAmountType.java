
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * A monetary amount.
 * 
 * <p>Java class for MonetaryAmountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MonetaryAmountType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>XSDecimalOrEmpty">
 *       &lt;attribute name="Currency" type="{http://www.sifassociation.org/au/datamodel/1.3}ISO4217CurrencyNamesAndCodeElementsType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonetaryAmountType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "value"
})
public class MonetaryAmountType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Currency")
    protected ISO4217CurrencyNamesAndCodeElementsType currency;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link ISO4217CurrencyNamesAndCodeElementsType }
     *     
     */
    public ISO4217CurrencyNamesAndCodeElementsType getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO4217CurrencyNamesAndCodeElementsType }
     *     
     */
    public void setCurrency(ISO4217CurrencyNamesAndCodeElementsType value) {
        this.currency = value;
    }

}
