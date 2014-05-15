
package sif.dd.au30.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This object contains information about the school or campus.
 * 
 * <p>Java class for SchoolInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchoolInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="StateProvinceId" type="{http://www.sifassociation.org/au/datamodel/1.3}StateProvinceIdType" minOccurs="0"/>
 *         &lt;element name="CommonwealthId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="OtherIdList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="OtherId" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
 *                           &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SchoolName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="LEAInfoRefId" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" minOccurs="0"/>
 *         &lt;element name="OtherLEA" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>RefIdTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="LEAInfo"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SchoolDistrict" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SchoolDistrictLocalId" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SchoolType" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolLevelType" minOccurs="0"/>
 *         &lt;element name="SchoolFocusList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SchoolFocus" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolFocusCodeType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SchoolURL" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolURLType" minOccurs="0"/>
 *         &lt;element name="SchoolEmailList" type="{http://www.sifassociation.org/au/datamodel/1.3}EmailListType" minOccurs="0"/>
 *         &lt;element name="PrincipalInfo" type="{http://www.sifassociation.org/au/datamodel/1.3}PrincipalInfoType" minOccurs="0"/>
 *         &lt;element name="SchoolContactList" type="{http://www.sifassociation.org/au/datamodel/1.3}SchoolContactListType" minOccurs="0"/>
 *         &lt;element name="AddressList" type="{http://www.sifassociation.org/au/datamodel/1.3}AddressListType" minOccurs="0"/>
 *         &lt;element name="PhoneNumberList" type="{http://www.sifassociation.org/au/datamodel/1.3}PhoneNumberListType" minOccurs="0"/>
 *         &lt;element name="SessionType" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSessionTypeType" minOccurs="0"/>
 *         &lt;element name="YearLevels" type="{http://www.sifassociation.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="ARIA" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="OperationalStatus" type="{http://www.sifassociation.org/au/datamodel/1.3}OperationalStatusType" minOccurs="0"/>
 *         &lt;element name="FederalElectorate" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsFederalElectorateType" minOccurs="0"/>
 *         &lt;element name="Campus" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ParentSchoolId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="SchoolCampusId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="CampusType" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolLevelType" minOccurs="0"/>
 *                   &lt;element name="AdminStatus" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SchoolSector" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolSectorCodeType" minOccurs="0"/>
 *         &lt;element name="IndependentSchool" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="NonGovSystemicStatus" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSystemicStatusType" minOccurs="0"/>
 *         &lt;element name="System" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolSystemType" minOccurs="0"/>
 *         &lt;element name="ReligiousAffiliation" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAustralianStandardClassificationOfReligiousGroupsASCRGType" minOccurs="0"/>
 *         &lt;element name="SchoolGeographicLocation" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolLocationType" minOccurs="0"/>
 *         &lt;element name="LocalGovernmentArea" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="JurisdictionLowerHouse" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SLA" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsAustralianStandardGeographicalClassificationASGCType" minOccurs="0"/>
 *         &lt;element name="SchoolCoEdStatus" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolCoEdStatusType" minOccurs="0"/>
 *         &lt;element name="BoardingSchoolStatus" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="Entity_Open" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Entity_Close" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="SchoolGroupList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SchoolGroup" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SIF_Metadata" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.sifassociation.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.sifassociation.org/au/datamodel/1.3}RefIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchoolInfoType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "localId",
    "stateProvinceId",
    "commonwealthId",
    "otherIdList",
    "schoolName",
    "leaInfoRefId",
    "otherLEA",
    "schoolDistrict",
    "schoolDistrictLocalId",
    "schoolType",
    "schoolFocusList",
    "schoolURL",
    "schoolEmailList",
    "principalInfo",
    "schoolContactList",
    "addressList",
    "phoneNumberList",
    "sessionType",
    "yearLevels",
    "aria",
    "operationalStatus",
    "federalElectorate",
    "campus",
    "schoolSector",
    "independentSchool",
    "nonGovSystemicStatus",
    "system",
    "religiousAffiliation",
    "schoolGeographicLocation",
    "localGovernmentArea",
    "jurisdictionLowerHouse",
    "sla",
    "schoolCoEdStatus",
    "boardingSchoolStatus",
    "entityOpen",
    "entityClose",
    "schoolGroupList",
    "sifMetadata",
    "sifExtendedElements"
})
public class SchoolInfoType {

    @XmlElementRef(name = "LocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> localId;
    @XmlElementRef(name = "StateProvinceId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stateProvinceId;
    @XmlElementRef(name = "CommonwealthId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> commonwealthId;
    @XmlElementRef(name = "OtherIdList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SchoolInfoType.OtherIdList> otherIdList;
    @XmlElement(name = "SchoolName", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String schoolName;
    @XmlElementRef(name = "LEAInfoRefId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> leaInfoRefId;
    @XmlElementRef(name = "OtherLEA", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SchoolInfoType.OtherLEA> otherLEA;
    @XmlElementRef(name = "SchoolDistrict", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolDistrict;
    @XmlElementRef(name = "SchoolDistrictLocalId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolDistrictLocalId;
    @XmlElementRef(name = "SchoolType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsSchoolLevelType> schoolType;
    @XmlElementRef(name = "SchoolFocusList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SchoolInfoType.SchoolFocusList> schoolFocusList;
    @XmlElementRef(name = "SchoolURL", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolURL;
    @XmlElementRef(name = "SchoolEmailList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<EmailListType> schoolEmailList;
    @XmlElementRef(name = "PrincipalInfo", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<PrincipalInfoType> principalInfo;
    @XmlElementRef(name = "SchoolContactList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SchoolContactListType> schoolContactList;
    @XmlElementRef(name = "AddressList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressListType> addressList;
    @XmlElementRef(name = "PhoneNumberList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<PhoneNumberListType> phoneNumberList;
    @XmlElementRef(name = "SessionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sessionType;
    @XmlElementRef(name = "YearLevels", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> yearLevels;
    @XmlElementRef(name = "ARIA", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> aria;
    @XmlElementRef(name = "OperationalStatus", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsOperationalStatusType> operationalStatus;
    @XmlElementRef(name = "FederalElectorate", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> federalElectorate;
    @XmlElementRef(name = "Campus", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SchoolInfoType.Campus> campus;
    @XmlElement(name = "SchoolSector", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected AUCodeSetsSchoolSectorCodeType schoolSector;
    @XmlElementRef(name = "IndependentSchool", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> independentSchool;
    @XmlElementRef(name = "NonGovSystemicStatus", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsSystemicStatusType> nonGovSystemicStatus;
    @XmlElementRef(name = "System", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> system;
    @XmlElementRef(name = "ReligiousAffiliation", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> religiousAffiliation;
    @XmlElementRef(name = "SchoolGeographicLocation", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolGeographicLocation;
    @XmlElementRef(name = "LocalGovernmentArea", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> localGovernmentArea;
    @XmlElementRef(name = "JurisdictionLowerHouse", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> jurisdictionLowerHouse;
    @XmlElementRef(name = "SLA", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sla;
    @XmlElementRef(name = "SchoolCoEdStatus", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsSchoolCoEdStatusType> schoolCoEdStatus;
    @XmlElementRef(name = "BoardingSchoolStatus", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> boardingSchoolStatus;
    @XmlElementRef(name = "Entity_Open", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> entityOpen;
    @XmlElementRef(name = "Entity_Close", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> entityClose;
    @XmlElementRef(name = "SchoolGroupList", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SchoolInfoType.SchoolGroupList> schoolGroupList;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the localId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalId() {
        return localId;
    }

    /**
     * Sets the value of the localId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalId(JAXBElement<String> value) {
        this.localId = value;
    }

    /**
     * Gets the value of the stateProvinceId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStateProvinceId() {
        return stateProvinceId;
    }

    /**
     * Sets the value of the stateProvinceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStateProvinceId(JAXBElement<String> value) {
        this.stateProvinceId = value;
    }

    /**
     * Gets the value of the commonwealthId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCommonwealthId() {
        return commonwealthId;
    }

    /**
     * Sets the value of the commonwealthId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCommonwealthId(JAXBElement<String> value) {
        this.commonwealthId = value;
    }

    /**
     * Gets the value of the otherIdList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.OtherIdList }{@code >}
     *     
     */
    public JAXBElement<SchoolInfoType.OtherIdList> getOtherIdList() {
        return otherIdList;
    }

    /**
     * Sets the value of the otherIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.OtherIdList }{@code >}
     *     
     */
    public void setOtherIdList(JAXBElement<SchoolInfoType.OtherIdList> value) {
        this.otherIdList = value;
    }

    /**
     * Gets the value of the schoolName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Sets the value of the schoolName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolName(String value) {
        this.schoolName = value;
    }

    /**
     * Gets the value of the leaInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLEAInfoRefId() {
        return leaInfoRefId;
    }

    /**
     * Sets the value of the leaInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLEAInfoRefId(JAXBElement<String> value) {
        this.leaInfoRefId = value;
    }

    /**
     * Gets the value of the otherLEA property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.OtherLEA }{@code >}
     *     
     */
    public JAXBElement<SchoolInfoType.OtherLEA> getOtherLEA() {
        return otherLEA;
    }

    /**
     * Sets the value of the otherLEA property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.OtherLEA }{@code >}
     *     
     */
    public void setOtherLEA(JAXBElement<SchoolInfoType.OtherLEA> value) {
        this.otherLEA = value;
    }

    /**
     * Gets the value of the schoolDistrict property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolDistrict() {
        return schoolDistrict;
    }

    /**
     * Sets the value of the schoolDistrict property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolDistrict(JAXBElement<String> value) {
        this.schoolDistrict = value;
    }

    /**
     * Gets the value of the schoolDistrictLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolDistrictLocalId() {
        return schoolDistrictLocalId;
    }

    /**
     * Sets the value of the schoolDistrictLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolDistrictLocalId(JAXBElement<String> value) {
        this.schoolDistrictLocalId = value;
    }

    /**
     * Gets the value of the schoolType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolLevelType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsSchoolLevelType> getSchoolType() {
        return schoolType;
    }

    /**
     * Sets the value of the schoolType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolLevelType }{@code >}
     *     
     */
    public void setSchoolType(JAXBElement<AUCodeSetsSchoolLevelType> value) {
        this.schoolType = value;
    }

    /**
     * Gets the value of the schoolFocusList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.SchoolFocusList }{@code >}
     *     
     */
    public JAXBElement<SchoolInfoType.SchoolFocusList> getSchoolFocusList() {
        return schoolFocusList;
    }

    /**
     * Sets the value of the schoolFocusList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.SchoolFocusList }{@code >}
     *     
     */
    public void setSchoolFocusList(JAXBElement<SchoolInfoType.SchoolFocusList> value) {
        this.schoolFocusList = value;
    }

    /**
     * Gets the value of the schoolURL property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolURL() {
        return schoolURL;
    }

    /**
     * Sets the value of the schoolURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolURL(JAXBElement<String> value) {
        this.schoolURL = value;
    }

    /**
     * Gets the value of the schoolEmailList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EmailListType }{@code >}
     *     
     */
    public JAXBElement<EmailListType> getSchoolEmailList() {
        return schoolEmailList;
    }

    /**
     * Sets the value of the schoolEmailList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EmailListType }{@code >}
     *     
     */
    public void setSchoolEmailList(JAXBElement<EmailListType> value) {
        this.schoolEmailList = value;
    }

    /**
     * Gets the value of the principalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PrincipalInfoType }{@code >}
     *     
     */
    public JAXBElement<PrincipalInfoType> getPrincipalInfo() {
        return principalInfo;
    }

    /**
     * Sets the value of the principalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PrincipalInfoType }{@code >}
     *     
     */
    public void setPrincipalInfo(JAXBElement<PrincipalInfoType> value) {
        this.principalInfo = value;
    }

    /**
     * Gets the value of the schoolContactList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SchoolContactListType }{@code >}
     *     
     */
    public JAXBElement<SchoolContactListType> getSchoolContactList() {
        return schoolContactList;
    }

    /**
     * Sets the value of the schoolContactList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SchoolContactListType }{@code >}
     *     
     */
    public void setSchoolContactList(JAXBElement<SchoolContactListType> value) {
        this.schoolContactList = value;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public JAXBElement<AddressListType> getAddressList() {
        return addressList;
    }

    /**
     * Sets the value of the addressList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressListType }{@code >}
     *     
     */
    public void setAddressList(JAXBElement<AddressListType> value) {
        this.addressList = value;
    }

    /**
     * Gets the value of the phoneNumberList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}
     *     
     */
    public JAXBElement<PhoneNumberListType> getPhoneNumberList() {
        return phoneNumberList;
    }

    /**
     * Sets the value of the phoneNumberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}
     *     
     */
    public void setPhoneNumberList(JAXBElement<PhoneNumberListType> value) {
        this.phoneNumberList = value;
    }

    /**
     * Gets the value of the sessionType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSessionType() {
        return sessionType;
    }

    /**
     * Sets the value of the sessionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSessionType(JAXBElement<String> value) {
        this.sessionType = value;
    }

    /**
     * Gets the value of the yearLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public JAXBElement<YearLevelsType> getYearLevels() {
        return yearLevels;
    }

    /**
     * Sets the value of the yearLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public void setYearLevels(JAXBElement<YearLevelsType> value) {
        this.yearLevels = value;
    }

    /**
     * Gets the value of the aria property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getARIA() {
        return aria;
    }

    /**
     * Sets the value of the aria property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setARIA(JAXBElement<BigDecimal> value) {
        this.aria = value;
    }

    /**
     * Gets the value of the operationalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsOperationalStatusType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsOperationalStatusType> getOperationalStatus() {
        return operationalStatus;
    }

    /**
     * Sets the value of the operationalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsOperationalStatusType }{@code >}
     *     
     */
    public void setOperationalStatus(JAXBElement<AUCodeSetsOperationalStatusType> value) {
        this.operationalStatus = value;
    }

    /**
     * Gets the value of the federalElectorate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFederalElectorate() {
        return federalElectorate;
    }

    /**
     * Sets the value of the federalElectorate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFederalElectorate(JAXBElement<String> value) {
        this.federalElectorate = value;
    }

    /**
     * Gets the value of the campus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.Campus }{@code >}
     *     
     */
    public JAXBElement<SchoolInfoType.Campus> getCampus() {
        return campus;
    }

    /**
     * Sets the value of the campus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.Campus }{@code >}
     *     
     */
    public void setCampus(JAXBElement<SchoolInfoType.Campus> value) {
        this.campus = value;
    }

    /**
     * Gets the value of the schoolSector property.
     * 
     * @return
     *     possible object is
     *     {@link AUCodeSetsSchoolSectorCodeType }
     *     
     */
    public AUCodeSetsSchoolSectorCodeType getSchoolSector() {
        return schoolSector;
    }

    /**
     * Sets the value of the schoolSector property.
     * 
     * @param value
     *     allowed object is
     *     {@link AUCodeSetsSchoolSectorCodeType }
     *     
     */
    public void setSchoolSector(AUCodeSetsSchoolSectorCodeType value) {
        this.schoolSector = value;
    }

    /**
     * Gets the value of the independentSchool property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getIndependentSchool() {
        return independentSchool;
    }

    /**
     * Sets the value of the independentSchool property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setIndependentSchool(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.independentSchool = value;
    }

    /**
     * Gets the value of the nonGovSystemicStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSystemicStatusType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsSystemicStatusType> getNonGovSystemicStatus() {
        return nonGovSystemicStatus;
    }

    /**
     * Sets the value of the nonGovSystemicStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSystemicStatusType }{@code >}
     *     
     */
    public void setNonGovSystemicStatus(JAXBElement<AUCodeSetsSystemicStatusType> value) {
        this.nonGovSystemicStatus = value;
    }

    /**
     * Gets the value of the system property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSystem() {
        return system;
    }

    /**
     * Sets the value of the system property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSystem(JAXBElement<String> value) {
        this.system = value;
    }

    /**
     * Gets the value of the religiousAffiliation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReligiousAffiliation() {
        return religiousAffiliation;
    }

    /**
     * Sets the value of the religiousAffiliation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReligiousAffiliation(JAXBElement<String> value) {
        this.religiousAffiliation = value;
    }

    /**
     * Gets the value of the schoolGeographicLocation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolGeographicLocation() {
        return schoolGeographicLocation;
    }

    /**
     * Sets the value of the schoolGeographicLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolGeographicLocation(JAXBElement<String> value) {
        this.schoolGeographicLocation = value;
    }

    /**
     * Gets the value of the localGovernmentArea property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLocalGovernmentArea() {
        return localGovernmentArea;
    }

    /**
     * Sets the value of the localGovernmentArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLocalGovernmentArea(JAXBElement<String> value) {
        this.localGovernmentArea = value;
    }

    /**
     * Gets the value of the jurisdictionLowerHouse property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJurisdictionLowerHouse() {
        return jurisdictionLowerHouse;
    }

    /**
     * Sets the value of the jurisdictionLowerHouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJurisdictionLowerHouse(JAXBElement<String> value) {
        this.jurisdictionLowerHouse = value;
    }

    /**
     * Gets the value of the sla property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSLA() {
        return sla;
    }

    /**
     * Sets the value of the sla property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSLA(JAXBElement<String> value) {
        this.sla = value;
    }

    /**
     * Gets the value of the schoolCoEdStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolCoEdStatusType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsSchoolCoEdStatusType> getSchoolCoEdStatus() {
        return schoolCoEdStatus;
    }

    /**
     * Sets the value of the schoolCoEdStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolCoEdStatusType }{@code >}
     *     
     */
    public void setSchoolCoEdStatus(JAXBElement<AUCodeSetsSchoolCoEdStatusType> value) {
        this.schoolCoEdStatus = value;
    }

    /**
     * Gets the value of the boardingSchoolStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getBoardingSchoolStatus() {
        return boardingSchoolStatus;
    }

    /**
     * Sets the value of the boardingSchoolStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setBoardingSchoolStatus(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.boardingSchoolStatus = value;
    }

    /**
     * Gets the value of the entityOpen property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEntityOpen() {
        return entityOpen;
    }

    /**
     * Sets the value of the entityOpen property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEntityOpen(JAXBElement<XMLGregorianCalendar> value) {
        this.entityOpen = value;
    }

    /**
     * Gets the value of the entityClose property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEntityClose() {
        return entityClose;
    }

    /**
     * Sets the value of the entityClose property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEntityClose(JAXBElement<XMLGregorianCalendar> value) {
        this.entityClose = value;
    }

    /**
     * Gets the value of the schoolGroupList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.SchoolGroupList }{@code >}
     *     
     */
    public JAXBElement<SchoolInfoType.SchoolGroupList> getSchoolGroupList() {
        return schoolGroupList;
    }

    /**
     * Sets the value of the schoolGroupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SchoolInfoType.SchoolGroupList }{@code >}
     *     
     */
    public void setSchoolGroupList(JAXBElement<SchoolInfoType.SchoolGroupList> value) {
        this.schoolGroupList = value;
    }

    /**
     * Gets the value of the sifMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public JAXBElement<SIFMetadataType> getSIFMetadata() {
        return sifMetadata;
    }

    /**
     * Sets the value of the sifMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public void setSIFMetadata(JAXBElement<SIFMetadataType> value) {
        this.sifMetadata = value;
    }

    /**
     * Gets the value of the sifExtendedElements property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public JAXBElement<SIFExtendedElementsType> getSIFExtendedElements() {
        return sifExtendedElements;
    }

    /**
     * Sets the value of the sifExtendedElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public void setSIFExtendedElements(JAXBElement<SIFExtendedElementsType> value) {
        this.sifExtendedElements = value;
    }

    /**
     * Gets the value of the refId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefId() {
        return refId;
    }

    /**
     * Sets the value of the refId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefId(String value) {
        this.refId = value;
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
     *         &lt;element name="ParentSchoolId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="SchoolCampusId" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="CampusType" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolLevelType" minOccurs="0"/>
     *         &lt;element name="AdminStatus" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
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
        "parentSchoolId",
        "schoolCampusId",
        "campusType",
        "adminStatus"
    })
    public static class Campus {

        @XmlElementRef(name = "ParentSchoolId", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> parentSchoolId;
        @XmlElement(name = "SchoolCampusId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String schoolCampusId;
        @XmlElementRef(name = "CampusType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AUCodeSetsSchoolLevelType> campusType;
        @XmlElement(name = "AdminStatus", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected AUCodeSetsYesOrNoCategoryType adminStatus;

        /**
         * Gets the value of the parentSchoolId property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getParentSchoolId() {
            return parentSchoolId;
        }

        /**
         * Sets the value of the parentSchoolId property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setParentSchoolId(JAXBElement<String> value) {
            this.parentSchoolId = value;
        }

        /**
         * Gets the value of the schoolCampusId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSchoolCampusId() {
            return schoolCampusId;
        }

        /**
         * Sets the value of the schoolCampusId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSchoolCampusId(String value) {
            this.schoolCampusId = value;
        }

        /**
         * Gets the value of the campusType property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolLevelType }{@code >}
         *     
         */
        public JAXBElement<AUCodeSetsSchoolLevelType> getCampusType() {
            return campusType;
        }

        /**
         * Sets the value of the campusType property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolLevelType }{@code >}
         *     
         */
        public void setCampusType(JAXBElement<AUCodeSetsSchoolLevelType> value) {
            this.campusType = value;
        }

        /**
         * Gets the value of the adminStatus property.
         * 
         * @return
         *     possible object is
         *     {@link AUCodeSetsYesOrNoCategoryType }
         *     
         */
        public AUCodeSetsYesOrNoCategoryType getAdminStatus() {
            return adminStatus;
        }

        /**
         * Sets the value of the adminStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link AUCodeSetsYesOrNoCategoryType }
         *     
         */
        public void setAdminStatus(AUCodeSetsYesOrNoCategoryType value) {
            this.adminStatus = value;
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
     *         &lt;element name="OtherId" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
     *                 &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
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
        "otherId"
    })
    public static class OtherIdList {

        @XmlElement(name = "OtherId", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        protected List<SchoolInfoType.OtherIdList.OtherId> otherId;

        /**
         * Gets the value of the otherId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the otherId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOtherId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SchoolInfoType.OtherIdList.OtherId }
         * 
         * 
         */
        public List<SchoolInfoType.OtherIdList.OtherId> getOtherId() {
            if (otherId == null) {
                otherId = new ArrayList<SchoolInfoType.OtherIdList.OtherId>();
            }
            return this.otherId;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
         *       &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class OtherId {

            @XmlValue
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String value;
            @XmlAttribute(name = "Type", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String type;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

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

        }

    }


    /**
     * The ID (GUID) of another related education agency, such as a regional service agency.
     * 
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.sifassociation.org/au/datamodel/1.3>RefIdTypeOrEmpty">
     *       &lt;attribute name="SIF_RefObject" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="LEAInfo"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class OtherLEA {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "SIF_RefObject", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sifRefObject;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the sifRefObject property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSIFRefObject() {
            return sifRefObject;
        }

        /**
         * Sets the value of the sifRefObject property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSIFRefObject(String value) {
            this.sifRefObject = value;
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
     *         &lt;element name="SchoolFocus" type="{http://www.sifassociation.org/au/datamodel/1.3}AUCodeSetsSchoolFocusCodeType" maxOccurs="unbounded" minOccurs="0"/>
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
        "schoolFocus"
    })
    public static class SchoolFocusList {

        @XmlElement(name = "SchoolFocus", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> schoolFocus;

        /**
         * Gets the value of the schoolFocus property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the schoolFocus property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSchoolFocus().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getSchoolFocus() {
            if (schoolFocus == null) {
                schoolFocus = new ArrayList<String>();
            }
            return this.schoolFocus;
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
     *         &lt;element name="SchoolGroup" type="{http://www.sifassociation.org/au/datamodel/1.3}LocalIdType" maxOccurs="unbounded" minOccurs="0"/>
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
        "schoolGroup"
    })
    public static class SchoolGroupList {

        @XmlElement(name = "SchoolGroup", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected List<String> schoolGroup;

        /**
         * Gets the value of the schoolGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the schoolGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSchoolGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getSchoolGroup() {
            if (schoolGroup == null) {
                schoolGroup = new ArrayList<String>();
            }
            return this.schoolGroup;
        }

    }

}
