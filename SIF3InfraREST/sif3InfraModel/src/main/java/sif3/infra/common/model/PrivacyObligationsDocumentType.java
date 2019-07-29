
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for privacyObligationsDocumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="privacyObligationsDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contract" type="{http://www.sifassociation.org/infrastructure/3.3}contractType" minOccurs="0"/>
 *         &lt;element name="legalRequirements" type="{http://www.sifassociation.org/infrastructure/3.3}legalRequirementsType" minOccurs="0"/>
 *         &lt;element name="technicalRequirements" type="{http://www.sifassociation.org/infrastructure/3.3}technicalRequirementsType" minOccurs="0"/>
 *         &lt;element name="dataController" type="{http://www.sifassociation.org/infrastructure/3.3}dataControllerType" minOccurs="0"/>
 *         &lt;element name="dataProcessor" type="{http://www.sifassociation.org/infrastructure/3.3}dataProcessorType" minOccurs="0"/>
 *         &lt;element name="dataSubProcessorList" type="{http://www.sifassociation.org/infrastructure/3.3}dataSubProcessorListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "privacyObligationsDocumentType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "contract",
    "legalRequirements",
    "technicalRequirements",
    "dataController",
    "dataProcessor",
    "dataSubProcessorList"
})
public class PrivacyObligationsDocumentType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected ContractType contract;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected LegalRequirementsType legalRequirements;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected TechnicalRequirementsType technicalRequirements;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected DataControllerType dataController;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected DataProcessorType dataProcessor;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected DataSubProcessorListType dataSubProcessorList;

    /**
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link ContractType }
     *     
     */
    public ContractType getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractType }
     *     
     */
    public void setContract(ContractType value) {
        this.contract = value;
    }

    /**
     * Gets the value of the legalRequirements property.
     * 
     * @return
     *     possible object is
     *     {@link LegalRequirementsType }
     *     
     */
    public LegalRequirementsType getLegalRequirements() {
        return legalRequirements;
    }

    /**
     * Sets the value of the legalRequirements property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalRequirementsType }
     *     
     */
    public void setLegalRequirements(LegalRequirementsType value) {
        this.legalRequirements = value;
    }

    /**
     * Gets the value of the technicalRequirements property.
     * 
     * @return
     *     possible object is
     *     {@link TechnicalRequirementsType }
     *     
     */
    public TechnicalRequirementsType getTechnicalRequirements() {
        return technicalRequirements;
    }

    /**
     * Sets the value of the technicalRequirements property.
     * 
     * @param value
     *     allowed object is
     *     {@link TechnicalRequirementsType }
     *     
     */
    public void setTechnicalRequirements(TechnicalRequirementsType value) {
        this.technicalRequirements = value;
    }

    /**
     * Gets the value of the dataController property.
     * 
     * @return
     *     possible object is
     *     {@link DataControllerType }
     *     
     */
    public DataControllerType getDataController() {
        return dataController;
    }

    /**
     * Sets the value of the dataController property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataControllerType }
     *     
     */
    public void setDataController(DataControllerType value) {
        this.dataController = value;
    }

    /**
     * Gets the value of the dataProcessor property.
     * 
     * @return
     *     possible object is
     *     {@link DataProcessorType }
     *     
     */
    public DataProcessorType getDataProcessor() {
        return dataProcessor;
    }

    /**
     * Sets the value of the dataProcessor property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataProcessorType }
     *     
     */
    public void setDataProcessor(DataProcessorType value) {
        this.dataProcessor = value;
    }

    /**
     * Gets the value of the dataSubProcessorList property.
     * 
     * @return
     *     possible object is
     *     {@link DataSubProcessorListType }
     *     
     */
    public DataSubProcessorListType getDataSubProcessorList() {
        return dataSubProcessorList;
    }

    /**
     * Sets the value of the dataSubProcessorList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSubProcessorListType }
     *     
     */
    public void setDataSubProcessorList(DataSubProcessorListType value) {
        this.dataSubProcessorList = value;
    }

}
