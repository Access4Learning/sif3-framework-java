
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Details of technical requirements that need to be met to achieve the POD.
 * 
 * <p>Java class for technicalRequirementsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="technicalRequirementsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataAccess" type="{http://www.sifassociation.org/infrastructure/3.3}dataAccessType" minOccurs="0"/>
 *         &lt;element name="conditionList" type="{http://www.sifassociation.org/infrastructure/3.3}conditionListType" minOccurs="0"/>
 *         &lt;element name="dataSubject" type="{http://www.sifassociation.org/infrastructure/3.3}dataSubjectType" minOccurs="0"/>
 *         &lt;element name="dataDeletion" type="{http://www.sifassociation.org/infrastructure/3.3}dataDeletionType" minOccurs="0"/>
 *         &lt;element name="securityTechnologyList" type="{http://www.sifassociation.org/infrastructure/3.3}securityTechnologyListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "technicalRequirementsType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "dataAccess",
    "conditionList",
    "dataSubject",
    "dataDeletion",
    "securityTechnologyList"
})
public class TechnicalRequirementsType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected DataAccessType dataAccess;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected ConditionListType conditionList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected DataSubjectType dataSubject;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected DataDeletionType dataDeletion;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected SecurityTechnologyListType securityTechnologyList;

    /**
     * Gets the value of the dataAccess property.
     * 
     * @return
     *     possible object is
     *     {@link DataAccessType }
     *     
     */
    public DataAccessType getDataAccess() {
        return dataAccess;
    }

    /**
     * Sets the value of the dataAccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataAccessType }
     *     
     */
    public void setDataAccess(DataAccessType value) {
        this.dataAccess = value;
    }

    /**
     * Gets the value of the conditionList property.
     * 
     * @return
     *     possible object is
     *     {@link ConditionListType }
     *     
     */
    public ConditionListType getConditionList() {
        return conditionList;
    }

    /**
     * Sets the value of the conditionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConditionListType }
     *     
     */
    public void setConditionList(ConditionListType value) {
        this.conditionList = value;
    }

    /**
     * Gets the value of the dataSubject property.
     * 
     * @return
     *     possible object is
     *     {@link DataSubjectType }
     *     
     */
    public DataSubjectType getDataSubject() {
        return dataSubject;
    }

    /**
     * Sets the value of the dataSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSubjectType }
     *     
     */
    public void setDataSubject(DataSubjectType value) {
        this.dataSubject = value;
    }

    /**
     * Gets the value of the dataDeletion property.
     * 
     * @return
     *     possible object is
     *     {@link DataDeletionType }
     *     
     */
    public DataDeletionType getDataDeletion() {
        return dataDeletion;
    }

    /**
     * Sets the value of the dataDeletion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataDeletionType }
     *     
     */
    public void setDataDeletion(DataDeletionType value) {
        this.dataDeletion = value;
    }

    /**
     * Gets the value of the securityTechnologyList property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityTechnologyListType }
     *     
     */
    public SecurityTechnologyListType getSecurityTechnologyList() {
        return securityTechnologyList;
    }

    /**
     * Sets the value of the securityTechnologyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityTechnologyListType }
     *     
     */
    public void setSecurityTechnologyList(SecurityTechnologyListType value) {
        this.securityTechnologyList = value;
    }

}
