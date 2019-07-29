
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A list of the data that can be accessed and any rules that specifically apply.
 * 
 * <p>Java class for dataAccessType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataAccessType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clauseList" type="{http://www.sifassociation.org/infrastructure/3.3}clauseListType" minOccurs="0"/>
 *         &lt;element name="fieldList" type="{http://www.sifassociation.org/infrastructure/3.3}fieldListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataAccessType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "clauseList",
    "fieldList"
})
public class DataAccessType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected ClauseListType clauseList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected FieldListType fieldList;

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

    /**
     * Gets the value of the fieldList property.
     * 
     * @return
     *     possible object is
     *     {@link FieldListType }
     *     
     */
    public FieldListType getFieldList() {
        return fieldList;
    }

    /**
     * Sets the value of the fieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldListType }
     *     
     */
    public void setFieldList(FieldListType value) {
        this.fieldList = value;
    }

}
