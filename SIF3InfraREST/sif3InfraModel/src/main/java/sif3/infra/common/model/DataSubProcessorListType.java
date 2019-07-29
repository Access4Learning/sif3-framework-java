
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The list of data sub-Processors who are the bodies that process data on behalf of the Data Processor.
 * 
 * <p>Java class for dataSubProcessorListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataSubProcessorListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataSubProcessor" type="{http://www.sifassociation.org/infrastructure/3.3}dataSubProcessorType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSubProcessorListType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "dataSubProcessor"
})
public class DataSubProcessorListType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<DataSubProcessorType> dataSubProcessor;

    /**
     * Gets the value of the dataSubProcessor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSubProcessor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSubProcessor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSubProcessorType }
     * 
     * 
     */
    public List<DataSubProcessorType> getDataSubProcessor() {
        if (dataSubProcessor == null) {
            dataSubProcessor = new ArrayList<DataSubProcessorType>();
        }
        return this.dataSubProcessor;
    }

}
