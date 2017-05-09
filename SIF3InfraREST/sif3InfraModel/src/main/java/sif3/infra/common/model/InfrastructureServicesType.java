
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for infrastructureServicesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="infrastructureServicesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="infrastructureService" type="{http://www.sifassociation.org/infrastructure/3.2.1}infrastructureServiceType" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infrastructureServicesType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "infrastructureService"
})
public class InfrastructureServicesType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", required = true)
    protected List<InfrastructureServiceType> infrastructureService;

    /**
     * Gets the value of the infrastructureService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infrastructureService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfrastructureService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfrastructureServiceType }
     * 
     * 
     */
    public List<InfrastructureServiceType> getInfrastructureService() {
        if (infrastructureService == null) {
            infrastructureService = new ArrayList<InfrastructureServiceType>();
        }
        return this.infrastructureService;
    }

}
