
package systemic.sif.dd.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         Demographics information about the student, contact, staff member, etc. 
 *       
 * 
 * <p>Java class for DemographicsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DemographicsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IndigenousStatus" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsIndigenousStatusType" minOccurs="0"/>
 *         &lt;element name="Sex" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsSexCodeType" minOccurs="0"/>
 *         &lt;element name="BirthDate" type="{http://www.SIFinfo.org/au/datamodel/1.3}BirthDateType" minOccurs="0"/>
 *         &lt;element name="BirthDateVerification" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsBirthdateVerificationType" minOccurs="0"/>
 *         &lt;element name="PlaceOfBirth" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="StateOfBirth" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceType" minOccurs="0"/>
 *         &lt;element name="CountryOfBirth" type="{http://www.SIFinfo.org/au/datamodel/1.3}CountryType" minOccurs="0"/>
 *         &lt;element name="CountriesOfCitizenship" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CountryOfCitizenship" type="{http://www.SIFinfo.org/au/datamodel/1.3}CountryType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CountriesOfResidency" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CountryOfResidency" type="{http://www.SIFinfo.org/au/datamodel/1.3}CountryType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CountryArrivalDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="AustralianCitizenshipStatus" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAustralianCitizenshipStatusType" minOccurs="0"/>
 *         &lt;element name="EnglishProficiency" type="{http://www.SIFinfo.org/au/datamodel/1.3}EnglishProficiencyType" minOccurs="0"/>
 *         &lt;element name="LanguageList" type="{http://www.SIFinfo.org/au/datamodel/1.3}LanguageListType" minOccurs="0"/>
 *         &lt;element name="DwellingArrangement" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsDwellingArrangementType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Religion" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAustralianStandardClasSIFicationOfReligiousGroupsASCRGType" minOccurs="0"/>
 *                   &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ReligiousEventList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ReligiousEvent" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                             &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ReligiousRegion" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="PermanentResident" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsPermanentResidentStatusType" minOccurs="0"/>
 *         &lt;element name="VisaSubClass" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union memberTypes=" {http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsVisaSubClassType {http://www.w3.org/2001/XMLSchema}string">
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VisaStatisticalCode" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="VisaExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="ImmunisationCertificateStatus" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsImmunisationCertificateStatusType" minOccurs="0"/>
 *         &lt;element name="CulturalBackground" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAustralianStandardClasSIFicationOfCulturalAndEthnicGroupsASCCEGType" minOccurs="0"/>
 *         &lt;element name="MaritalStatus" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsMaritalStatusAIHWType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DemographicsType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "indigenousStatus",
    "sex",
    "birthDate",
    "birthDateVerification",
    "placeOfBirth",
    "stateOfBirth",
    "countryOfBirth",
    "countriesOfCitizenship",
    "countriesOfResidency",
    "countryArrivalDate",
    "australianCitizenshipStatus",
    "englishProficiency",
    "languageList",
    "dwellingArrangement",
    "religion",
    "religiousEventList",
    "religiousRegion",
    "permanentResident",
    "visaSubClass",
    "visaStatisticalCode",
    "visaExpiryDate",
    "immunisationCertificateStatus",
    "culturalBackground",
    "maritalStatus"
})
public class DemographicsType {

    @XmlElementRef(name = "IndigenousStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> indigenousStatus;
    @XmlElementRef(name = "Sex", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sex;
    @XmlElementRef(name = "BirthDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> birthDate;
    @XmlElementRef(name = "BirthDateVerification", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> birthDateVerification;
    @XmlElementRef(name = "PlaceOfBirth", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> placeOfBirth;
    @XmlElementRef(name = "StateOfBirth", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stateOfBirth;
    @XmlElementRef(name = "CountryOfBirth", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> countryOfBirth;
    @XmlElementRef(name = "CountriesOfCitizenship", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<DemographicsType.CountriesOfCitizenship> countriesOfCitizenship;
    @XmlElementRef(name = "CountriesOfResidency", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<DemographicsType.CountriesOfResidency> countriesOfResidency;
    @XmlElementRef(name = "CountryArrivalDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> countryArrivalDate;
    @XmlElementRef(name = "AustralianCitizenshipStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> australianCitizenshipStatus;
    @XmlElementRef(name = "EnglishProficiency", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<EnglishProficiencyType> englishProficiency;
    @XmlElementRef(name = "LanguageList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LanguageListType> languageList;
    @XmlElementRef(name = "DwellingArrangement", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<DemographicsType.DwellingArrangement> dwellingArrangement;
    @XmlElementRef(name = "Religion", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<DemographicsType.Religion> religion;
    @XmlElementRef(name = "ReligiousEventList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<DemographicsType.ReligiousEventList> religiousEventList;
    @XmlElementRef(name = "ReligiousRegion", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> religiousRegion;
    @XmlElementRef(name = "PermanentResident", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> permanentResident;
    @XmlElementRef(name = "VisaSubClass", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> visaSubClass;
    @XmlElementRef(name = "VisaStatisticalCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> visaStatisticalCode;
    @XmlElementRef(name = "VisaExpiryDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> visaExpiryDate;
    @XmlElementRef(name = "ImmunisationCertificateStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsImmunisationCertificateStatusType> immunisationCertificateStatus;
    @XmlElementRef(name = "CulturalBackground", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> culturalBackground;
    @XmlElementRef(name = "MaritalStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> maritalStatus;

    /**
     * Gets the value of the indigenousStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIndigenousStatus() {
        return indigenousStatus;
    }

    /**
     * Sets the value of the indigenousStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIndigenousStatus(JAXBElement<String> value) {
        this.indigenousStatus = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSex(JAXBElement<String> value) {
        this.sex = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setBirthDate(JAXBElement<XMLGregorianCalendar> value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the birthDateVerification property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBirthDateVerification() {
        return birthDateVerification;
    }

    /**
     * Sets the value of the birthDateVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBirthDateVerification(JAXBElement<String> value) {
        this.birthDateVerification = value;
    }

    /**
     * Gets the value of the placeOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets the value of the placeOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlaceOfBirth(JAXBElement<String> value) {
        this.placeOfBirth = value;
    }

    /**
     * Gets the value of the stateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStateOfBirth() {
        return stateOfBirth;
    }

    /**
     * Sets the value of the stateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStateOfBirth(JAXBElement<String> value) {
        this.stateOfBirth = value;
    }

    /**
     * Gets the value of the countryOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCountryOfBirth() {
        return countryOfBirth;
    }

    /**
     * Sets the value of the countryOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCountryOfBirth(JAXBElement<String> value) {
        this.countryOfBirth = value;
    }

    /**
     * Gets the value of the countriesOfCitizenship property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.CountriesOfCitizenship }{@code >}
     *     
     */
    public JAXBElement<DemographicsType.CountriesOfCitizenship> getCountriesOfCitizenship() {
        return countriesOfCitizenship;
    }

    /**
     * Sets the value of the countriesOfCitizenship property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.CountriesOfCitizenship }{@code >}
     *     
     */
    public void setCountriesOfCitizenship(JAXBElement<DemographicsType.CountriesOfCitizenship> value) {
        this.countriesOfCitizenship = value;
    }

    /**
     * Gets the value of the countriesOfResidency property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.CountriesOfResidency }{@code >}
     *     
     */
    public JAXBElement<DemographicsType.CountriesOfResidency> getCountriesOfResidency() {
        return countriesOfResidency;
    }

    /**
     * Sets the value of the countriesOfResidency property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.CountriesOfResidency }{@code >}
     *     
     */
    public void setCountriesOfResidency(JAXBElement<DemographicsType.CountriesOfResidency> value) {
        this.countriesOfResidency = value;
    }

    /**
     * Gets the value of the countryArrivalDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getCountryArrivalDate() {
        return countryArrivalDate;
    }

    /**
     * Sets the value of the countryArrivalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setCountryArrivalDate(JAXBElement<XMLGregorianCalendar> value) {
        this.countryArrivalDate = value;
    }

    /**
     * Gets the value of the australianCitizenshipStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAustralianCitizenshipStatus() {
        return australianCitizenshipStatus;
    }

    /**
     * Sets the value of the australianCitizenshipStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAustralianCitizenshipStatus(JAXBElement<String> value) {
        this.australianCitizenshipStatus = value;
    }

    /**
     * Gets the value of the englishProficiency property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EnglishProficiencyType }{@code >}
     *     
     */
    public JAXBElement<EnglishProficiencyType> getEnglishProficiency() {
        return englishProficiency;
    }

    /**
     * Sets the value of the englishProficiency property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EnglishProficiencyType }{@code >}
     *     
     */
    public void setEnglishProficiency(JAXBElement<EnglishProficiencyType> value) {
        this.englishProficiency = value;
    }

    /**
     * Gets the value of the languageList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}
     *     
     */
    public JAXBElement<LanguageListType> getLanguageList() {
        return languageList;
    }

    /**
     * Sets the value of the languageList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}
     *     
     */
    public void setLanguageList(JAXBElement<LanguageListType> value) {
        this.languageList = value;
    }

    /**
     * Gets the value of the dwellingArrangement property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.DwellingArrangement }{@code >}
     *     
     */
    public JAXBElement<DemographicsType.DwellingArrangement> getDwellingArrangement() {
        return dwellingArrangement;
    }

    /**
     * Sets the value of the dwellingArrangement property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.DwellingArrangement }{@code >}
     *     
     */
    public void setDwellingArrangement(JAXBElement<DemographicsType.DwellingArrangement> value) {
        this.dwellingArrangement = value;
    }

    /**
     * Gets the value of the religion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.Religion }{@code >}
     *     
     */
    public JAXBElement<DemographicsType.Religion> getReligion() {
        return religion;
    }

    /**
     * Sets the value of the religion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.Religion }{@code >}
     *     
     */
    public void setReligion(JAXBElement<DemographicsType.Religion> value) {
        this.religion = value;
    }

    /**
     * Gets the value of the religiousEventList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.ReligiousEventList }{@code >}
     *     
     */
    public JAXBElement<DemographicsType.ReligiousEventList> getReligiousEventList() {
        return religiousEventList;
    }

    /**
     * Sets the value of the religiousEventList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DemographicsType.ReligiousEventList }{@code >}
     *     
     */
    public void setReligiousEventList(JAXBElement<DemographicsType.ReligiousEventList> value) {
        this.religiousEventList = value;
    }

    /**
     * Gets the value of the religiousRegion property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReligiousRegion() {
        return religiousRegion;
    }

    /**
     * Sets the value of the religiousRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReligiousRegion(JAXBElement<String> value) {
        this.religiousRegion = value;
    }

    /**
     * Gets the value of the permanentResident property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPermanentResident() {
        return permanentResident;
    }

    /**
     * Sets the value of the permanentResident property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPermanentResident(JAXBElement<String> value) {
        this.permanentResident = value;
    }

    /**
     * Gets the value of the visaSubClass property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVisaSubClass() {
        return visaSubClass;
    }

    /**
     * Sets the value of the visaSubClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVisaSubClass(JAXBElement<String> value) {
        this.visaSubClass = value;
    }

    /**
     * Gets the value of the visaStatisticalCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVisaStatisticalCode() {
        return visaStatisticalCode;
    }

    /**
     * Sets the value of the visaStatisticalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVisaStatisticalCode(JAXBElement<String> value) {
        this.visaStatisticalCode = value;
    }

    /**
     * Gets the value of the visaExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getVisaExpiryDate() {
        return visaExpiryDate;
    }

    /**
     * Sets the value of the visaExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setVisaExpiryDate(JAXBElement<XMLGregorianCalendar> value) {
        this.visaExpiryDate = value;
    }

    /**
     * Gets the value of the immunisationCertificateStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsImmunisationCertificateStatusType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsImmunisationCertificateStatusType> getImmunisationCertificateStatus() {
        return immunisationCertificateStatus;
    }

    /**
     * Sets the value of the immunisationCertificateStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsImmunisationCertificateStatusType }{@code >}
     *     
     */
    public void setImmunisationCertificateStatus(JAXBElement<AUCodeSetsImmunisationCertificateStatusType> value) {
        this.immunisationCertificateStatus = value;
    }

    /**
     * Gets the value of the culturalBackground property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCulturalBackground() {
        return culturalBackground;
    }

    /**
     * Sets the value of the culturalBackground property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCulturalBackground(JAXBElement<String> value) {
        this.culturalBackground = value;
    }

    /**
     * Gets the value of the maritalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the value of the maritalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMaritalStatus(JAXBElement<String> value) {
        this.maritalStatus = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CountryOfCitizenship" type="{http://www.SIFinfo.org/au/datamodel/1.3}CountryType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "countryOfCitizenship"
    })
    public static class CountriesOfCitizenship {

        @XmlElement(name = "CountryOfCitizenship", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<String> countryOfCitizenship;

        /**
         * Gets the value of the countryOfCitizenship property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the countryOfCitizenship property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCountryOfCitizenship().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCountryOfCitizenship() {
            if (countryOfCitizenship == null) {
                countryOfCitizenship = new ArrayList<String>();
            }
            return this.countryOfCitizenship;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CountryOfResidency" type="{http://www.SIFinfo.org/au/datamodel/1.3}CountryType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "countryOfResidency"
    })
    public static class CountriesOfResidency {

        @XmlElement(name = "CountryOfResidency", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<String> countryOfResidency;

        /**
         * Gets the value of the countryOfResidency property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the countryOfResidency property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCountryOfResidency().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCountryOfResidency() {
            if (countryOfResidency == null) {
                countryOfResidency = new ArrayList<String>();
            }
            return this.countryOfResidency;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsDwellingArrangementType" minOccurs="0"/>
     *         &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "code",
        "otherCodeList"
    })
    public static class DwellingArrangement {

        @XmlElement(name = "Code", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String code;
        @XmlElementRef(name = "OtherCodeList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<OtherCodeListType> otherCodeList;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the otherCodeList property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
         *     
         */
        public JAXBElement<OtherCodeListType> getOtherCodeList() {
            return otherCodeList;
        }

        /**
         * Sets the value of the otherCodeList property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
         *     
         */
        public void setOtherCodeList(JAXBElement<OtherCodeListType> value) {
            this.otherCodeList = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Code" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAustralianStandardClasSIFicationOfReligiousGroupsASCRGType" minOccurs="0"/>
     *         &lt;element name="OtherCodeList" type="{http://www.SIFinfo.org/au/datamodel/1.3}OtherCodeListType" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "code",
        "otherCodeList"
    })
    public static class Religion {

        @XmlElement(name = "Code", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String code;
        @XmlElementRef(name = "OtherCodeList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<OtherCodeListType> otherCodeList;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the otherCodeList property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
         *     
         */
        public JAXBElement<OtherCodeListType> getOtherCodeList() {
            return otherCodeList;
        }

        /**
         * Sets the value of the otherCodeList property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}
         *     
         */
        public void setOtherCodeList(JAXBElement<OtherCodeListType> value) {
            this.otherCodeList = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ReligiousEvent" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                   &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "religiousEvent"
    })
    public static class ReligiousEventList {

        @XmlElement(name = "ReligiousEvent", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<DemographicsType.ReligiousEventList.ReligiousEvent> religiousEvent;

        /**
         * Gets the value of the religiousEvent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the religiousEvent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReligiousEvent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DemographicsType.ReligiousEventList.ReligiousEvent }
         * 
         * 
         */
        public List<DemographicsType.ReligiousEventList.ReligiousEvent> getReligiousEvent() {
            if (religiousEvent == null) {
                religiousEvent = new ArrayList<DemographicsType.ReligiousEventList.ReligiousEvent>();
            }
            return this.religiousEvent;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "type",
            "date"
        })
        public static class ReligiousEvent {

            @XmlElement(name = "Type", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String type;
            @XmlElement(name = "Date", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar date;

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

            /**
             * Gets the value of the date property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getDate() {
                return date;
            }

            /**
             * Sets the value of the date property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setDate(XMLGregorianCalendar value) {
                this.date = value;
            }

        }

    }

}
