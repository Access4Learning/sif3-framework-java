
package systemic.sif.dd.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This element contains address data.
 * 
 * <p>Java class for AddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Street" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Line1" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="Line2" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="Line3" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="Complex" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="StreetNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="StreetPrefix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="StreetName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="StreetType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="StreetSuffix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="ApartmentType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="ApartmentNumberPrefix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="ApartmentNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="ApartmentNumberSuffix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="StateProvince" type="{http://www.SIFinfo.org/au/datamodel/1.3}StateProvinceType" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://www.SIFinfo.org/au/datamodel/1.3}CountryType" minOccurs="0"/>
 *         &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="GridLocation" type="{http://www.SIFinfo.org/au/datamodel/1.3}GridLocationType" minOccurs="0"/>
 *         &lt;element name="MapReference" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="XCoordinate" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                   &lt;element name="YCoordinate" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RadioContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Community" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="LocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="AddressGlobalUID" type="{http://www.SIFinfo.org/au/datamodel/1.3}GUIDType" minOccurs="0"/>
 *         &lt;element name="StatisticalAreas" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StatisticalArea" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
 *                           &lt;attribute name="SpatialUnitType" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="MB"/>
 *                                 &lt;enumeration value="SA1"/>
 *                                 &lt;enumeration value="SA2"/>
 *                                 &lt;enumeration value="SA3"/>
 *                                 &lt;enumeration value="SA4"/>
 *                                 &lt;enumeration value="GCCSA"/>
 *                                 &lt;enumeration value="S/T"/>
 *                                 &lt;enumeration value="LG"/>
 *                                 &lt;enumeration value="TR"/>
 *                                 &lt;enumeration value="ILOC"/>
 *                                 &lt;enumeration value="IARE"/>
 *                                 &lt;enumeration value="IREG"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAddressTypeType" />
 *       &lt;attribute name="Role" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsAddressRoleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "street",
    "city",
    "stateProvince",
    "country",
    "postalCode",
    "gridLocation",
    "mapReference",
    "radioContact",
    "community",
    "localId",
    "addressGlobalUID",
    "statisticalAreas"
})
@XmlSeeAlso({
    systemic.sif.dd.jaxb.AddressListType.Address.class
})
public class AddressType {

    @XmlElement(name = "Street", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected AddressType.Street street;
    @XmlElement(name = "City", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String city;
    @XmlElementRef(name = "StateProvince", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stateProvince;
    @XmlElementRef(name = "Country", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> country;
    @XmlElement(name = "PostalCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String postalCode;
    @XmlElementRef(name = "GridLocation", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<GridLocationType> gridLocation;
    @XmlElementRef(name = "MapReference", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressType.MapReference> mapReference;
    @XmlElementRef(name = "RadioContact", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> radioContact;
    @XmlElementRef(name = "Community", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> community;
    @XmlElementRef(name = "LocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> localId;
    @XmlElementRef(name = "AddressGlobalUID", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> addressGlobalUID;
    @XmlElementRef(name = "StatisticalAreas", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AddressType.StatisticalAreas> statisticalAreas;
    @XmlAttribute(name = "Type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "Role", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String role;

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType.Street }
     *     
     */
    public AddressType.Street getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType.Street }
     *     
     */
    public void setStreet(AddressType.Street value) {
        this.street = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the stateProvince property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStateProvince() {
        return stateProvince;
    }

    /**
     * Sets the value of the stateProvince property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStateProvince(JAXBElement<String> value) {
        this.stateProvince = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCountry(JAXBElement<String> value) {
        this.country = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the gridLocation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GridLocationType }{@code >}
     *     
     */
    public JAXBElement<GridLocationType> getGridLocation() {
        return gridLocation;
    }

    /**
     * Sets the value of the gridLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GridLocationType }{@code >}
     *     
     */
    public void setGridLocation(JAXBElement<GridLocationType> value) {
        this.gridLocation = value;
    }

    /**
     * Gets the value of the mapReference property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressType.MapReference }{@code >}
     *     
     */
    public JAXBElement<AddressType.MapReference> getMapReference() {
        return mapReference;
    }

    /**
     * Sets the value of the mapReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressType.MapReference }{@code >}
     *     
     */
    public void setMapReference(JAXBElement<AddressType.MapReference> value) {
        this.mapReference = value;
    }

    /**
     * Gets the value of the radioContact property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRadioContact() {
        return radioContact;
    }

    /**
     * Sets the value of the radioContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRadioContact(JAXBElement<String> value) {
        this.radioContact = value;
    }

    /**
     * Gets the value of the community property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCommunity() {
        return community;
    }

    /**
     * Sets the value of the community property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCommunity(JAXBElement<String> value) {
        this.community = value;
    }

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
     * Gets the value of the addressGlobalUID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAddressGlobalUID() {
        return addressGlobalUID;
    }

    /**
     * Sets the value of the addressGlobalUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAddressGlobalUID(JAXBElement<String> value) {
        this.addressGlobalUID = value;
    }

    /**
     * Gets the value of the statisticalAreas property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddressType.StatisticalAreas }{@code >}
     *     
     */
    public JAXBElement<AddressType.StatisticalAreas> getStatisticalAreas() {
        return statisticalAreas;
    }

    /**
     * Sets the value of the statisticalAreas property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddressType.StatisticalAreas }{@code >}
     *     
     */
    public void setStatisticalAreas(JAXBElement<AddressType.StatisticalAreas> value) {
        this.statisticalAreas = value;
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

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
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
     *         &lt;element name="XCoordinate" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="YCoordinate" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Type" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "xCoordinate",
        "yCoordinate"
    })
    public static class MapReference {

        @XmlElement(name = "XCoordinate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String xCoordinate;
        @XmlElement(name = "YCoordinate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String yCoordinate;
        @XmlAttribute(name = "Type", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String type;

        /**
         * Gets the value of the xCoordinate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXCoordinate() {
            return xCoordinate;
        }

        /**
         * Sets the value of the xCoordinate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXCoordinate(String value) {
            this.xCoordinate = value;
        }

        /**
         * Gets the value of the yCoordinate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getYCoordinate() {
            return yCoordinate;
        }

        /**
         * Sets the value of the yCoordinate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setYCoordinate(String value) {
            this.yCoordinate = value;
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
     *         &lt;element name="StatisticalArea" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
     *                 &lt;attribute name="SpatialUnitType" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="MB"/>
     *                       &lt;enumeration value="SA1"/>
     *                       &lt;enumeration value="SA2"/>
     *                       &lt;enumeration value="SA3"/>
     *                       &lt;enumeration value="SA4"/>
     *                       &lt;enumeration value="GCCSA"/>
     *                       &lt;enumeration value="S/T"/>
     *                       &lt;enumeration value="LG"/>
     *                       &lt;enumeration value="TR"/>
     *                       &lt;enumeration value="ILOC"/>
     *                       &lt;enumeration value="IARE"/>
     *                       &lt;enumeration value="IREG"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
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
        "statisticalArea"
    })
    public static class StatisticalAreas {

        @XmlElementRef(name = "StatisticalArea", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<AddressType.StatisticalAreas.StatisticalArea> statisticalArea;

        /**
         * Gets the value of the statisticalArea property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link AddressType.StatisticalAreas.StatisticalArea }{@code >}
         *     
         */
        public JAXBElement<AddressType.StatisticalAreas.StatisticalArea> getStatisticalArea() {
            return statisticalArea;
        }

        /**
         * Sets the value of the statisticalArea property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link AddressType.StatisticalAreas.StatisticalArea }{@code >}
         *     
         */
        public void setStatisticalArea(JAXBElement<AddressType.StatisticalAreas.StatisticalArea> value) {
            this.statisticalArea = value;
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
         *       &lt;attribute name="SpatialUnitType" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="MB"/>
         *             &lt;enumeration value="SA1"/>
         *             &lt;enumeration value="SA2"/>
         *             &lt;enumeration value="SA3"/>
         *             &lt;enumeration value="SA4"/>
         *             &lt;enumeration value="GCCSA"/>
         *             &lt;enumeration value="S/T"/>
         *             &lt;enumeration value="LG"/>
         *             &lt;enumeration value="TR"/>
         *             &lt;enumeration value="ILOC"/>
         *             &lt;enumeration value="IARE"/>
         *             &lt;enumeration value="IREG"/>
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
        public static class StatisticalArea {

            @XmlValue
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String value;
            @XmlAttribute(name = "SpatialUnitType", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String spatialUnitType;

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
             * Gets the value of the spatialUnitType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSpatialUnitType() {
                return spatialUnitType;
            }

            /**
             * Sets the value of the spatialUnitType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSpatialUnitType(String value) {
                this.spatialUnitType = value;
            }

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
     *         &lt;element name="Line1" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="Line2" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="Line3" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="Complex" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="StreetNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="StreetPrefix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="StreetName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="StreetType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="StreetSuffix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="ApartmentType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="ApartmentNumberPrefix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="ApartmentNumber" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *         &lt;element name="ApartmentNumberSuffix" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
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
        "line1",
        "line2",
        "line3",
        "complex",
        "streetNumber",
        "streetPrefix",
        "streetName",
        "streetType",
        "streetSuffix",
        "apartmentType",
        "apartmentNumberPrefix",
        "apartmentNumber",
        "apartmentNumberSuffix"
    })
    public static class Street {

        @XmlElement(name = "Line1", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String line1;
        @XmlElementRef(name = "Line2", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> line2;
        @XmlElementRef(name = "Line3", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> line3;
        @XmlElementRef(name = "Complex", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> complex;
        @XmlElementRef(name = "StreetNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> streetNumber;
        @XmlElementRef(name = "StreetPrefix", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> streetPrefix;
        @XmlElementRef(name = "StreetName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> streetName;
        @XmlElementRef(name = "StreetType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> streetType;
        @XmlElementRef(name = "StreetSuffix", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> streetSuffix;
        @XmlElementRef(name = "ApartmentType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> apartmentType;
        @XmlElementRef(name = "ApartmentNumberPrefix", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> apartmentNumberPrefix;
        @XmlElementRef(name = "ApartmentNumber", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> apartmentNumber;
        @XmlElementRef(name = "ApartmentNumberSuffix", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> apartmentNumberSuffix;

        /**
         * Gets the value of the line1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLine1() {
            return line1;
        }

        /**
         * Sets the value of the line1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLine1(String value) {
            this.line1 = value;
        }

        /**
         * Gets the value of the line2 property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getLine2() {
            return line2;
        }

        /**
         * Sets the value of the line2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setLine2(JAXBElement<String> value) {
            this.line2 = value;
        }

        /**
         * Gets the value of the line3 property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getLine3() {
            return line3;
        }

        /**
         * Sets the value of the line3 property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setLine3(JAXBElement<String> value) {
            this.line3 = value;
        }

        /**
         * Gets the value of the complex property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getComplex() {
            return complex;
        }

        /**
         * Sets the value of the complex property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setComplex(JAXBElement<String> value) {
            this.complex = value;
        }

        /**
         * Gets the value of the streetNumber property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getStreetNumber() {
            return streetNumber;
        }

        /**
         * Sets the value of the streetNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setStreetNumber(JAXBElement<String> value) {
            this.streetNumber = value;
        }

        /**
         * Gets the value of the streetPrefix property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getStreetPrefix() {
            return streetPrefix;
        }

        /**
         * Sets the value of the streetPrefix property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setStreetPrefix(JAXBElement<String> value) {
            this.streetPrefix = value;
        }

        /**
         * Gets the value of the streetName property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getStreetName() {
            return streetName;
        }

        /**
         * Sets the value of the streetName property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setStreetName(JAXBElement<String> value) {
            this.streetName = value;
        }

        /**
         * Gets the value of the streetType property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getStreetType() {
            return streetType;
        }

        /**
         * Sets the value of the streetType property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setStreetType(JAXBElement<String> value) {
            this.streetType = value;
        }

        /**
         * Gets the value of the streetSuffix property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getStreetSuffix() {
            return streetSuffix;
        }

        /**
         * Sets the value of the streetSuffix property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setStreetSuffix(JAXBElement<String> value) {
            this.streetSuffix = value;
        }

        /**
         * Gets the value of the apartmentType property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getApartmentType() {
            return apartmentType;
        }

        /**
         * Sets the value of the apartmentType property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setApartmentType(JAXBElement<String> value) {
            this.apartmentType = value;
        }

        /**
         * Gets the value of the apartmentNumberPrefix property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getApartmentNumberPrefix() {
            return apartmentNumberPrefix;
        }

        /**
         * Sets the value of the apartmentNumberPrefix property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setApartmentNumberPrefix(JAXBElement<String> value) {
            this.apartmentNumberPrefix = value;
        }

        /**
         * Gets the value of the apartmentNumber property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getApartmentNumber() {
            return apartmentNumber;
        }

        /**
         * Sets the value of the apartmentNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setApartmentNumber(JAXBElement<String> value) {
            this.apartmentNumber = value;
        }

        /**
         * Gets the value of the apartmentNumberSuffix property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getApartmentNumberSuffix() {
            return apartmentNumberSuffix;
        }

        /**
         * Sets the value of the apartmentNumberSuffix property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setApartmentNumberSuffix(JAXBElement<String> value) {
            this.apartmentNumberSuffix = value;
        }

    }

}
