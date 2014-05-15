
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AggregateStatisticInfoCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AggregateStatisticInfoCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AggregateStatisticInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}AggregateStatisticInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AggregateStatisticInfoCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "aggregateStatisticInfo"
})
public class AggregateStatisticInfoCollectionType {

    @XmlElement(name = "AggregateStatisticInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<AggregateStatisticInfoType> aggregateStatisticInfo;

    /**
     * Gets the value of the aggregateStatisticInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aggregateStatisticInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAggregateStatisticInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AggregateStatisticInfoType }
     * 
     * 
     */
    public List<AggregateStatisticInfoType> getAggregateStatisticInfo() {
        if (aggregateStatisticInfo == null) {
            aggregateStatisticInfo = new ArrayList<AggregateStatisticInfoType>();
        }
        return this.aggregateStatisticInfo;
    }

}
