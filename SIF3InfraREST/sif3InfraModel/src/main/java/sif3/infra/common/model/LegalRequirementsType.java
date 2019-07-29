
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contains a list of the legal clauses within the contract.
 * 
 * <p>Java class for legalRequirementsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="legalRequirementsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clauseList" type="{http://www.sifassociation.org/infrastructure/3.3}clauseListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "legalRequirementsType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "clauseList"
})
public class LegalRequirementsType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    protected ClauseListType clauseList;

    /**
     * Gets the value of the clauseList property.
     * 
     * @return
     *     possible object is
     *     {@link ClauseListType }
     *     
     */
    public ClauseListType getClauseList() {
        return clauseList;
    }

    /**
     * Sets the value of the clauseList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClauseListType }
     *     
     */
    public void setClauseList(ClauseListType value) {
        this.clauseList = value;
    }

}
