
package sif3.infra.common.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The data processor, "means a natural or legal person, public authority, agency or other body which processes personal data on behalf of the controller", see GDPR.
 * 
 * <p>Java class for dataProcessorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataProcessorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataProcessorName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="privacyPolicyURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="privacyContact" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreContactInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="notifyDataControllerOnAccessRequests" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dataProcessorContactForAccessRequests" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreContactInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="purposeList" type="{http://www.sifassociation.org/infrastructure/3.3}purposeListType" minOccurs="0"/>
 *         &lt;element name="alternatePurposeList" type="{http://www.sifassociation.org/infrastructure/3.3}alternatePurposeListType" minOccurs="0"/>
 *         &lt;element name="deidentifiedPurposeList" type="{http://www.sifassociation.org/infrastructure/3.3}deidentifiedPurposeListType" minOccurs="0"/>
 *         &lt;element name="dataUsageMarketingAllowed" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="personalInformationUpdatedFromSource" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="dataBreachNotification" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dataBreachContact" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreContactInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="employeesMustComplyWithAgreement" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="employeeConfidentialityAgreement" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="employeeTrainingList" type="{http://www.sifassociation.org/infrastructure/3.3}employeeTrainingListType" minOccurs="0"/>
 *         &lt;element name="passwordEmployeeAccessStandard" type="{http://www.sifassociation.org/infrastructure/3.3}passwordEmployeeAccessStandardType" minOccurs="0"/>
 *         &lt;element name="securityTestRequiredList" type="{http://www.sifassociation.org/infrastructure/3.3}securityTestRequiredListType" minOccurs="0"/>
 *         &lt;element name="countryImpactedList" type="{http://www.sifassociation.org/infrastructure/3.3}countryImpactedListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataProcessorType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "dataProcessorName",
    "privacyPolicyURL",
    "privacyContact",
    "notifyDataControllerOnAccessRequests",
    "dataProcessorContactForAccessRequests",
    "purposeList",
    "alternatePurposeList",
    "deidentifiedPurposeList",
    "dataUsageMarketingAllowed",
    "personalInformationUpdatedFromSource",
    "dataBreachNotification",
    "dataBreachContact",
    "employeesMustComplyWithAgreement",
    "employeeConfidentialityAgreement",
    "employeeTrainingList",
    "passwordEmployeeAccessStandard",
    "securityTestRequiredList",
    "countryImpactedList"
})
public class DataProcessorType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String dataProcessorName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String privacyPolicyURL;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<GCoreContactInfoType> privacyContact;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String notifyDataControllerOnAccessRequests;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<GCoreContactInfoType> dataProcessorContactForAccessRequests;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected PurposeListType purposeList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected AlternatePurposeListType alternatePurposeList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected DeidentifiedPurposeListType deidentifiedPurposeList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String dataUsageMarketingAllowed;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected BigInteger personalInformationUpdatedFromSource;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String dataBreachNotification;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<GCoreContactInfoType> dataBreachContact;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String employeesMustComplyWithAgreement;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String employeeConfidentialityAgreement;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected EmployeeTrainingListType employeeTrainingList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected PasswordEmployeeAccessStandardType passwordEmployeeAccessStandard;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected SecurityTestRequiredListType securityTestRequiredList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected CountryImpactedListType countryImpactedList;

    /**
     * Gets the value of the dataProcessorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataProcessorName() {
        return dataProcessorName;
    }

    /**
     * Sets the value of the dataProcessorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataProcessorName(String value) {
        this.dataProcessorName = value;
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
     * Gets the value of the notifyDataControllerOnAccessRequests property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifyDataControllerOnAccessRequests() {
        return notifyDataControllerOnAccessRequests;
    }

    /**
     * Sets the value of the notifyDataControllerOnAccessRequests property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifyDataControllerOnAccessRequests(String value) {
        this.notifyDataControllerOnAccessRequests = value;
    }

    /**
     * Gets the value of the dataProcessorContactForAccessRequests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataProcessorContactForAccessRequests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataProcessorContactForAccessRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GCoreContactInfoType }
     * 
     * 
     */
    public List<GCoreContactInfoType> getDataProcessorContactForAccessRequests() {
        if (dataProcessorContactForAccessRequests == null) {
            dataProcessorContactForAccessRequests = new ArrayList<GCoreContactInfoType>();
        }
        return this.dataProcessorContactForAccessRequests;
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

    /**
     * Gets the value of the alternatePurposeList property.
     * 
     * @return
     *     possible object is
     *     {@link AlternatePurposeListType }
     *     
     */
    public AlternatePurposeListType getAlternatePurposeList() {
        return alternatePurposeList;
    }

    /**
     * Sets the value of the alternatePurposeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlternatePurposeListType }
     *     
     */
    public void setAlternatePurposeList(AlternatePurposeListType value) {
        this.alternatePurposeList = value;
    }

    /**
     * Gets the value of the deidentifiedPurposeList property.
     * 
     * @return
     *     possible object is
     *     {@link DeidentifiedPurposeListType }
     *     
     */
    public DeidentifiedPurposeListType getDeidentifiedPurposeList() {
        return deidentifiedPurposeList;
    }

    /**
     * Sets the value of the deidentifiedPurposeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeidentifiedPurposeListType }
     *     
     */
    public void setDeidentifiedPurposeList(DeidentifiedPurposeListType value) {
        this.deidentifiedPurposeList = value;
    }

    /**
     * Gets the value of the dataUsageMarketingAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataUsageMarketingAllowed() {
        return dataUsageMarketingAllowed;
    }

    /**
     * Sets the value of the dataUsageMarketingAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataUsageMarketingAllowed(String value) {
        this.dataUsageMarketingAllowed = value;
    }

    /**
     * Gets the value of the personalInformationUpdatedFromSource property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPersonalInformationUpdatedFromSource() {
        return personalInformationUpdatedFromSource;
    }

    /**
     * Sets the value of the personalInformationUpdatedFromSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPersonalInformationUpdatedFromSource(BigInteger value) {
        this.personalInformationUpdatedFromSource = value;
    }

    /**
     * Gets the value of the dataBreachNotification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataBreachNotification() {
        return dataBreachNotification;
    }

    /**
     * Sets the value of the dataBreachNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataBreachNotification(String value) {
        this.dataBreachNotification = value;
    }

    /**
     * Gets the value of the dataBreachContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataBreachContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataBreachContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GCoreContactInfoType }
     * 
     * 
     */
    public List<GCoreContactInfoType> getDataBreachContact() {
        if (dataBreachContact == null) {
            dataBreachContact = new ArrayList<GCoreContactInfoType>();
        }
        return this.dataBreachContact;
    }

    /**
     * Gets the value of the employeesMustComplyWithAgreement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeesMustComplyWithAgreement() {
        return employeesMustComplyWithAgreement;
    }

    /**
     * Sets the value of the employeesMustComplyWithAgreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeesMustComplyWithAgreement(String value) {
        this.employeesMustComplyWithAgreement = value;
    }

    /**
     * Gets the value of the employeeConfidentialityAgreement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeConfidentialityAgreement() {
        return employeeConfidentialityAgreement;
    }

    /**
     * Sets the value of the employeeConfidentialityAgreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeConfidentialityAgreement(String value) {
        this.employeeConfidentialityAgreement = value;
    }

    /**
     * Gets the value of the employeeTrainingList property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeTrainingListType }
     *     
     */
    public EmployeeTrainingListType getEmployeeTrainingList() {
        return employeeTrainingList;
    }

    /**
     * Sets the value of the employeeTrainingList property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeTrainingListType }
     *     
     */
    public void setEmployeeTrainingList(EmployeeTrainingListType value) {
        this.employeeTrainingList = value;
    }

    /**
     * Gets the value of the passwordEmployeeAccessStandard property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordEmployeeAccessStandardType }
     *     
     */
    public PasswordEmployeeAccessStandardType getPasswordEmployeeAccessStandard() {
        return passwordEmployeeAccessStandard;
    }

    /**
     * Sets the value of the passwordEmployeeAccessStandard property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordEmployeeAccessStandardType }
     *     
     */
    public void setPasswordEmployeeAccessStandard(PasswordEmployeeAccessStandardType value) {
        this.passwordEmployeeAccessStandard = value;
    }

    /**
     * Gets the value of the securityTestRequiredList property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityTestRequiredListType }
     *     
     */
    public SecurityTestRequiredListType getSecurityTestRequiredList() {
        return securityTestRequiredList;
    }

    /**
     * Sets the value of the securityTestRequiredList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityTestRequiredListType }
     *     
     */
    public void setSecurityTestRequiredList(SecurityTestRequiredListType value) {
        this.securityTestRequiredList = value;
    }

    /**
     * Gets the value of the countryImpactedList property.
     * 
     * @return
     *     possible object is
     *     {@link CountryImpactedListType }
     *     
     */
    public CountryImpactedListType getCountryImpactedList() {
        return countryImpactedList;
    }

    /**
     * Sets the value of the countryImpactedList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryImpactedListType }
     *     
     */
    public void setCountryImpactedList(CountryImpactedListType value) {
        this.countryImpactedList = value;
    }

}
