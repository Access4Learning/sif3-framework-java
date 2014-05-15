
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AggregateCharacteristicInfoCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregateCharacteristicInfoCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AggregateCharacteristicInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}AggregateCharacteristicInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregateCharacteristicInfoCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "aggregateCharacteristicInfo"
})
public class AggregateCharacteristicInfoCollectionType {

    @XmlElement(name = "AggregateCharacteristicInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<AggregateCharacteristicInfoType> aggregateCharacteristicInfo;

    /**
     * Gets the value of the aggregateCharacteristicInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aggregateCharacteristicInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAggregateCharacteristicInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AggregateCharacteristicInfoType }
     * 
     * 
     */
    public List<AggregateCharacteristicInfoType> getAggregateCharacteristicInfo() {
        if (aggregateCharacteristicInfo == null) {
            aggregateCharacteristicInfo = new ArrayList<AggregateCharacteristicInfoType>();
        }
        return this.aggregateCharacteristicInfo;
    }

}
