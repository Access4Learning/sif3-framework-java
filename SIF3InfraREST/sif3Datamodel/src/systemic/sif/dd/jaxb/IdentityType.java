
package systemic.sif.dd.jaxb;

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


/**
 * 
 *         This object allows a system that stores network identities and/or passwords to share them with other applications through SIF. The provider of the Identity object may only support providing an identity or a password, or it may support both.
 *       
 * 
 * <p>Java class for IdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SIF_RefId" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                 &lt;attribute name="SIF_RefObject" use="required">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="StudentPersonal"/>
 *                       &lt;enumeration value="StaffPersonal"/>
 *                       &lt;enumeration value="StudentContactPersonal"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AuthenticationSource" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="AUAccessShibboleth"/>
 *               &lt;enumeration value="MSActiveDirectory"/>
 *               &lt;enumeration value="NovellNDS"/>
 *               &lt;enumeration value="OpenDirectory"/>
 *               &lt;enumeration value="OpenID"/>
 *               &lt;enumeration value="Other"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdentityAssertions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IdentityAssertion" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
 *                           &lt;attribute name="SchemaName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="PasswordList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Password" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>XSBase64BinaryOrEmpty">
 *                           &lt;attribute name="Algorithm" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="MD5"/>
 *                                 &lt;enumeration value="SHA1"/>
 *                                 &lt;enumeration value="DES"/>
 *                                 &lt;enumeration value="TripleDES"/>
 *                                 &lt;enumeration value="RC2"/>
 *                                 &lt;enumeration value="AES"/>
 *                                 &lt;enumeration value="RSA"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="KeyName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="AuthenticationSourceGlobalUID" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentityType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "sifRefId",
    "authenticationSource",
    "identityAssertions",
    "passwordList",
    "authenticationSourceGlobalUID",
    "sifMetadata",
    "sifExtendedElements"
})
public class IdentityType {

    @XmlElement(name = "SIF_RefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected IdentityType.SIFRefId sifRefId;
    @XmlElement(name = "AuthenticationSource", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String authenticationSource;
    @XmlElementRef(name = "IdentityAssertions", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<IdentityType.IdentityAssertions> identityAssertions;
    @XmlElementRef(name = "PasswordList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<IdentityType.PasswordList> passwordList;
    @XmlElementRef(name = "AuthenticationSourceGlobalUID", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> authenticationSourceGlobalUID;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the sifRefId property.
     * 
     * @return
     *     possible object is
     *     {@link IdentityType.SIFRefId }
     *     
     */
    public IdentityType.SIFRefId getSIFRefId() {
        return sifRefId;
    }

    /**
     * Sets the value of the sifRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityType.SIFRefId }
     *     
     */
    public void setSIFRefId(IdentityType.SIFRefId value) {
        this.sifRefId = value;
    }

    /**
     * Gets the value of the authenticationSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationSource() {
        return authenticationSource;
    }

    /**
     * Sets the value of the authenticationSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationSource(String value) {
        this.authenticationSource = value;
    }

    /**
     * Gets the value of the identityAssertions property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link IdentityType.IdentityAssertions }{@code >}
     *     
     */
    public JAXBElement<IdentityType.IdentityAssertions> getIdentityAssertions() {
        return identityAssertions;
    }

    /**
     * Sets the value of the identityAssertions property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link IdentityType.IdentityAssertions }{@code >}
     *     
     */
    public void setIdentityAssertions(JAXBElement<IdentityType.IdentityAssertions> value) {
        this.identityAssertions = value;
    }

    /**
     * Gets the value of the passwordList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link IdentityType.PasswordList }{@code >}
     *     
     */
    public JAXBElement<IdentityType.PasswordList> getPasswordList() {
        return passwordList;
    }

    /**
     * Sets the value of the passwordList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link IdentityType.PasswordList }{@code >}
     *     
     */
    public void setPasswordList(JAXBElement<IdentityType.PasswordList> value) {
        this.passwordList = value;
    }

    /**
     * Gets the value of the authenticationSourceGlobalUID property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAuthenticationSourceGlobalUID() {
        return authenticationSourceGlobalUID;
    }

    /**
     * Sets the value of the authenticationSourceGlobalUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAuthenticationSourceGlobalUID(JAXBElement<String> value) {
        this.authenticationSourceGlobalUID = value;
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
     *         &lt;element name="IdentityAssertion" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>normalizedString">
     *                 &lt;attribute name="SchemaName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        "identityAssertion"
    })
    public static class IdentityAssertions {

        @XmlElement(name = "IdentityAssertion", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<IdentityType.IdentityAssertions.IdentityAssertion> identityAssertion;

        /**
         * Gets the value of the identityAssertion property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the identityAssertion property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIdentityAssertion().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IdentityType.IdentityAssertions.IdentityAssertion }
         * 
         * 
         */
        public List<IdentityType.IdentityAssertions.IdentityAssertion> getIdentityAssertion() {
            if (identityAssertion == null) {
                identityAssertion = new ArrayList<IdentityType.IdentityAssertions.IdentityAssertion>();
            }
            return this.identityAssertion;
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
         *       &lt;attribute name="SchemaName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        public static class IdentityAssertion {

            @XmlValue
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String value;
            @XmlAttribute(name = "SchemaName", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String schemaName;

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
             * Gets the value of the schemaName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSchemaName() {
                return schemaName;
            }

            /**
             * Sets the value of the schemaName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSchemaName(String value) {
                this.schemaName = value;
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
     *         &lt;element name="Password" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>XSBase64BinaryOrEmpty">
     *                 &lt;attribute name="Algorithm" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="MD5"/>
     *                       &lt;enumeration value="SHA1"/>
     *                       &lt;enumeration value="DES"/>
     *                       &lt;enumeration value="TripleDES"/>
     *                       &lt;enumeration value="RC2"/>
     *                       &lt;enumeration value="AES"/>
     *                       &lt;enumeration value="RSA"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="KeyName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        "password"
    })
    public static class PasswordList {

        @XmlElement(name = "Password", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<IdentityType.PasswordList.Password> password;

        /**
         * Gets the value of the password property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the password property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPassword().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IdentityType.PasswordList.Password }
         * 
         * 
         */
        public List<IdentityType.PasswordList.Password> getPassword() {
            if (password == null) {
                password = new ArrayList<IdentityType.PasswordList.Password>();
            }
            return this.password;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>XSBase64BinaryOrEmpty">
         *       &lt;attribute name="Algorithm" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="MD5"/>
         *             &lt;enumeration value="SHA1"/>
         *             &lt;enumeration value="DES"/>
         *             &lt;enumeration value="TripleDES"/>
         *             &lt;enumeration value="RC2"/>
         *             &lt;enumeration value="AES"/>
         *             &lt;enumeration value="RSA"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="KeyName" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        public static class Password {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "Algorithm", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String algorithm;
            @XmlAttribute(name = "KeyName", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String keyName;

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
             * Gets the value of the algorithm property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAlgorithm() {
                return algorithm;
            }

            /**
             * Sets the value of the algorithm property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAlgorithm(String value) {
                this.algorithm = value;
            }

            /**
             * Gets the value of the keyName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKeyName() {
                return keyName;
            }

            /**
             * Sets the value of the keyName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKeyName(String value) {
                this.keyName = value;
            }

        }

    }


    /**
     * 
     *         The SIF RefId of a StudentPersonal, StaffPersonal, or StudentContactPersonal object.
     *       
     * 
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *       &lt;attribute name="SIF_RefObject" use="required">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="StudentPersonal"/>
     *             &lt;enumeration value="StaffPersonal"/>
     *             &lt;enumeration value="StudentContactPersonal"/>
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
    public static class SIFRefId {

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

}
