
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Details of a legal clause within the contract.
 * 
 * <p>Java class for clauseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="clauseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clauseLabel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="clauseReference" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="clauseContent" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="obligationList" type="{http://www.sifassociation.org/infrastructure/3.3}obligationListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clauseType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "clauseLabel",
    "clauseReference",
    "clauseContent",
    "obligationList"
})
public class ClauseType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String clauseLabel;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String clauseReference;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String clauseContent;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected ObligationListType obligationList;

    /**
     * Gets the value of the clauseLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClauseLabel() {
        return clauseLabel;
    }

    /**
     * Sets the value of the clauseLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClauseLabel(String value) {
        this.clauseLabel = value;
    }

    /**
     * Gets the value of the clauseReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClauseReference() {
        return clauseReference;
    }

    /**
     * Sets the value of the clauseReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClauseReference(String value) {
        this.clauseReference = value;
    }

    /**
     * Gets the value of the clauseContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClauseContent() {
        return clauseContent;
    }

    /**
     * Sets the value of the clauseContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClauseContent(String value) {
        this.clauseContent = value;
    }

    /**
     * Gets the value of the obligationList property.
     * 
     * @return
     *     possible object is
     *     {@link ObligationListType }
     *     
     */
    public ObligationListType getObligationList() {
        return obligationList;
    }

    /**
     * Sets the value of the obligationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObligationListType }
     *     
     */
    public void setObligationList(ObligationListType value) {
        this.obligationList = value;
    }

}
