
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The data controller, "means the natural or legal person, public authority, agency or other body which, alone or jointly with others, determines the purposes and means of the processing of personal data", see GDPR.
 * 
 * <p>Java class for dataControllerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataControllerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataControllerName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="privacyPolicyURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="privacyContact" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreContactInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="issuesNotificationContact" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreContactInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataControllerType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "dataControllerName",
    "privacyPolicyURL",
    "privacyContact",
    "issuesNotificationContact"
})
public class DataControllerType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String dataControllerName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String privacyPolicyURL;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<GCoreContactInfoType> privacyContact;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<GCoreContactInfoType> issuesNotificationContact;

    /**
     * Gets the value of the dataControllerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataControllerName() {
        return dataControllerName;
    }

    /**
     * Sets the value of the dataControllerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataControllerName(String value) {
        this.dataControllerName = value;
    }

    /**
     * Gets the value of the privacyPolicyURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivacyPolicyURL() {
        return privacyPolicyURL;
    }

    /**
     * Sets the value of the privacyPolicyURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivacyPolicyURL(String value) {
        this.privacyPolicyURL = value;
    }

    /**
     * Gets the value of the privacyContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the privacyContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrivacyContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GCoreContactInfoType }
     * 
     * 
     */
    public List<GCoreContactInfoType> getPrivacyContact() {
        if (privacyContact == null) {
            privacyContact = new ArrayList<GCoreContactInfoType>();
        }
        return this.privacyContact;
    }

    /**
     * Gets the value of the issuesNotificationContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issuesNotificationContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssuesNotificationContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GCoreContactInfoType }
     * 
     * 
     */
    public List<GCoreContactInfoType> getIssuesNotificationContact() {
        if (issuesNotificationContact == null) {
            issuesNotificationContact = new ArrayList<GCoreContactInfoType>();
        }
        return this.issuesNotificationContact;
    }

}
