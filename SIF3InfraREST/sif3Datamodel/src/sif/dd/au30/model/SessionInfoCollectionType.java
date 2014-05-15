
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SessionInfoCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SessionInfoCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SessionInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}SessionInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionInfoCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sessionInfo"
})
public class SessionInfoCollectionType {

    @XmlElement(name = "SessionInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<SessionInfoType> sessionInfo;

    /**
     * Gets the value of the sessionInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sessionInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSessionInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SessionInfoType }
     * 
     * 
     */
    public List<SessionInfoType> getSessionInfo() {
        if (sessionInfo == null) {
            sessionInfo = new ArrayList<SessionInfoType>();
        }
        return this.sessionInfo;
    }

}
