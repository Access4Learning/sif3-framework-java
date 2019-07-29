
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
 * Details of organisations who process data on behalf of the data processor.
 * 
 * <p>Java class for dataSubProcessorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataSubProcessorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subProcessorName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="privacyPolicyURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="privacyContact" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreContactInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="purposeList" type="{http://www.sifassociation.org/infrastructure/3.3}purposeListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSubProcessorType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "subProcessorName",
    "privacyPolicyURL",
    "privacyContact",
    "purposeList"
})
public class DataSubProcessorType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String subProcessorName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String privacyPolicyURL;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<GCoreContactInfoType> privacyContact;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected PurposeListType purposeList;

    /**
     * Gets the value of the subProcessorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubProcessorName() {
        return subProcessorName;
    }

    /**
     * Sets the value of the subProcessorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubProcessorName(String value) {
        this.subProcessorName = value;
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
     * Gets the value of the purposeList property.
     * 
     * @return
     *     possible object is
     *     {@link PurposeListType }
     *     
     */
    public PurposeListType getPurposeList() {
        return purposeList;
    }

    /**
     * Sets the value of the purposeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurposeListType }
     *     
     */
    public void setPurposeList(PurposeListType value) {
        this.purposeList = value;
    }

}
