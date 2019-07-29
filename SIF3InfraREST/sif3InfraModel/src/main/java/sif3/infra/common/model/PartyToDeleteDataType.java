
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Who is responsible for deletion of this data?
 * 
 * <p>Java class for partyToDeleteDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyToDeleteDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="organisation" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreLocalIdType" minOccurs="0"/>
 *         &lt;element name="deletecontactInfo" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreContactInfoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyToDeleteDataType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "organisation",
    "deletecontactInfo"
})
public class PartyToDeleteDataType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String organisation;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected GCoreContactInfoType deletecontactInfo;

    /**
     * Gets the value of the organisation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisation() {
        return organisation;
    }

    /**
     * Sets the value of the organisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganisation(String value) {
        this.organisation = value;
    }

    /**
     * Gets the value of the deletecontactInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GCoreContactInfoType }
     *     
     */
    public GCoreContactInfoType getDeletecontactInfo() {
        return deletecontactInfo;
    }

    /**
     * Sets the value of the deletecontactInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GCoreContactInfoType }
     *     
     */
    public void setDeletecontactInfo(GCoreContactInfoType value) {
        this.deletecontactInfo = value;
    }

}
