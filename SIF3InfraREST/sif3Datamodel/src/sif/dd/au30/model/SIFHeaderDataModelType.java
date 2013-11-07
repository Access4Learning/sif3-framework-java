
package sif.dd.au30.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Header information associated with a message.
 * 
 * <p>Java class for SIF_HeaderDataModelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_HeaderDataModelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SIF_MsgId" type="{http://www.SIFinfo.org/au/datamodel/1.3}MsgIdType" minOccurs="0"/>
 *         &lt;element name="SIF_Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="SIF_Security" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SIF_SecureChannel" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_AuthenticationLevel" minOccurs="0"/>
 *                             &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_EncryptionLevel" minOccurs="0"/>
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
 *         &lt;element name="SIF_SourceId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SIF_DestinationId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;maxLength value="64"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SIF_Contexts" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ContextsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF_HeaderDataModelType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "sifMsgId",
    "sifTimestamp",
    "sifSecurity",
    "sifSourceId",
    "sifDestinationId",
    "sifContexts"
})
public class SIFHeaderDataModelType {

    @XmlElement(name = "SIF_MsgId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sifMsgId;
    @XmlElement(name = "SIF_Timestamp", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sifTimestamp;
    @XmlElementRef(name = "SIF_Security", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFHeaderDataModelType.SIFSecurity> sifSecurity;
    @XmlElement(name = "SIF_SourceId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sifSourceId;
    @XmlElementRef(name = "SIF_DestinationId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sifDestinationId;
    @XmlElementRef(name = "SIF_Contexts", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFContextsType> sifContexts;

    /**
     * Gets the value of the sifMsgId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIFMsgId() {
        return sifMsgId;
    }

    /**
     * Sets the value of the sifMsgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIFMsgId(String value) {
        this.sifMsgId = value;
    }

    /**
     * Gets the value of the sifTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSIFTimestamp() {
        return sifTimestamp;
    }

    /**
     * Sets the value of the sifTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSIFTimestamp(XMLGregorianCalendar value) {
        this.sifTimestamp = value;
    }

    /**
     * Gets the value of the sifSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFHeaderDataModelType.SIFSecurity }{@code >}
     *     
     */
    public JAXBElement<SIFHeaderDataModelType.SIFSecurity> getSIFSecurity() {
        return sifSecurity;
    }

    /**
     * Sets the value of the sifSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFHeaderDataModelType.SIFSecurity }{@code >}
     *     
     */
    public void setSIFSecurity(JAXBElement<SIFHeaderDataModelType.SIFSecurity> value) {
        this.sifSecurity = value;
    }

    /**
     * Gets the value of the sifSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIFSourceId() {
        return sifSourceId;
    }

    /**
     * Sets the value of the sifSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIFSourceId(String value) {
        this.sifSourceId = value;
    }

    /**
     * Gets the value of the sifDestinationId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSIFDestinationId() {
        return sifDestinationId;
    }

    /**
     * Sets the value of the sifDestinationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSIFDestinationId(JAXBElement<String> value) {
        this.sifDestinationId = value;
    }

    /**
     * Gets the value of the sifContexts property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFContextsType }{@code >}
     *     
     */
    public JAXBElement<SIFContextsType> getSIFContexts() {
        return sifContexts;
    }

    /**
     * Sets the value of the sifContexts property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFContextsType }{@code >}
     *     
     */
    public void setSIFContexts(JAXBElement<SIFContextsType> value) {
        this.sifContexts = value;
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
     *         &lt;element name="SIF_SecureChannel" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_AuthenticationLevel" minOccurs="0"/>
     *                   &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_EncryptionLevel" minOccurs="0"/>
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
        "sifSecureChannel"
    })
    public static class SIFSecurity {

        @XmlElement(name = "SIF_SecureChannel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel sifSecureChannel;

        /**
         * Gets the value of the sifSecureChannel property.
         * 
         * @return
         *     possible object is
         *     {@link SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel }
         *     
         */
        public SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel getSIFSecureChannel() {
            return sifSecureChannel;
        }

        /**
         * Sets the value of the sifSecureChannel property.
         * 
         * @param value
         *     allowed object is
         *     {@link SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel }
         *     
         */
        public void setSIFSecureChannel(SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel value) {
            this.sifSecureChannel = value;
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
         *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_AuthenticationLevel" minOccurs="0"/>
         *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_EncryptionLevel" minOccurs="0"/>
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
            "sifAuthenticationLevel",
            "sifEncryptionLevel"
        })
        public static class SIFSecureChannel {

            @XmlElement(name = "SIF_AuthenticationLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected Long sifAuthenticationLevel;
            @XmlElement(name = "SIF_EncryptionLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
            protected Long sifEncryptionLevel;

            /**
             * Gets the value of the sifAuthenticationLevel property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getSIFAuthenticationLevel() {
                return sifAuthenticationLevel;
            }

            /**
             * Sets the value of the sifAuthenticationLevel property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setSIFAuthenticationLevel(Long value) {
                this.sifAuthenticationLevel = value;
            }

            /**
             * Gets the value of the sifEncryptionLevel property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getSIFEncryptionLevel() {
                return sifEncryptionLevel;
            }

            /**
             * Sets the value of the sifEncryptionLevel property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setSIFEncryptionLevel(Long value) {
                this.sifEncryptionLevel = value;
            }

        }

    }

}
