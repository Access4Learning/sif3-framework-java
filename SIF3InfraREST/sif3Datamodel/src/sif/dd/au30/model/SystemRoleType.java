
package sif.dd.au30.model;

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
 *           The SystemRole Object defines the systems that a user has access to, the roles they perform within those systems, and the scope of those roles within the particular system.
 *         
 * 
 * <p>Java class for SystemRoleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SystemRoleType">
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
 *                       &lt;enumeration value="Identity"/>
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
 *         &lt;element name="SystemContextList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SystemContext" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RoleList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Role" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="RoleScopeList" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="RoleScope" maxOccurs="unbounded" minOccurs="0">
 *                                                             &lt;complexType>
 *                                                               &lt;complexContent>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                   &lt;sequence>
 *                                                                     &lt;element name="RoleScopeName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                                                                     &lt;element name="RoleScopeRefId" minOccurs="0">
 *                                                                       &lt;complexType>
 *                                                                         &lt;simpleContent>
 *                                                                           &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
 *                                                                             &lt;attribute name="SIF_RefObject" use="required">
 *                                                                               &lt;simpleType>
 *                                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                                                                   &lt;enumeration value="SchoolInfo"/>
 *                                                                                   &lt;enumeration value="TeachingGroup"/>
 *                                                                                   &lt;enumeration value="StudentActivityParticipation"/>
 *                                                                                 &lt;/restriction>
 *                                                                               &lt;/simpleType>
 *                                                                             &lt;/attribute>
 *                                                                           &lt;/extension>
 *                                                                         &lt;/simpleContent>
 *                                                                       &lt;/complexType>
 *                                                                     &lt;/element>
 *                                                                   &lt;/sequence>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/complexContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                               &lt;attribute name="RoleId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="SystemId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
@XmlType(name = "SystemRoleType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "sifRefId",
    "systemContextList",
    "sifMetadata",
    "sifExtendedElements"
})
public class SystemRoleType {

    @XmlElement(name = "SIF_RefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected SystemRoleType.SIFRefId sifRefId;
    @XmlElement(name = "SystemContextList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected SystemRoleType.SystemContextList systemContextList;
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
     *     {@link SystemRoleType.SIFRefId }
     *     
     */
    public SystemRoleType.SIFRefId getSIFRefId() {
        return sifRefId;
    }

    /**
     * Sets the value of the sifRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemRoleType.SIFRefId }
     *     
     */
    public void setSIFRefId(SystemRoleType.SIFRefId value) {
        this.sifRefId = value;
    }

    /**
     * Gets the value of the systemContextList property.
     * 
     * @return
     *     possible object is
     *     {@link SystemRoleType.SystemContextList }
     *     
     */
    public SystemRoleType.SystemContextList getSystemContextList() {
        return systemContextList;
    }

    /**
     * Sets the value of the systemContextList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemRoleType.SystemContextList }
     *     
     */
    public void setSystemContextList(SystemRoleType.SystemContextList value) {
        this.systemContextList = value;
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
     * The SIF RefId that provides the source Object for this SystemRole Object.
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
     *             &lt;enumeration value="Identity"/>
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
     *         &lt;element name="SystemContext" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RoleList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Role" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="RoleScopeList" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="RoleScope" maxOccurs="unbounded" minOccurs="0">
     *                                                   &lt;complexType>
     *                                                     &lt;complexContent>
     *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                         &lt;sequence>
     *                                                           &lt;element name="RoleScopeName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *                                                           &lt;element name="RoleScopeRefId" minOccurs="0">
     *                                                             &lt;complexType>
     *                                                               &lt;simpleContent>
     *                                                                 &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
     *                                                                   &lt;attribute name="SIF_RefObject" use="required">
     *                                                                     &lt;simpleType>
     *                                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                                                                         &lt;enumeration value="SchoolInfo"/>
     *                                                                         &lt;enumeration value="TeachingGroup"/>
     *                                                                         &lt;enumeration value="StudentActivityParticipation"/>
     *                                                                       &lt;/restriction>
     *                                                                     &lt;/simpleType>
     *                                                                   &lt;/attribute>
     *                                                                 &lt;/extension>
     *                                                               &lt;/simpleContent>
     *                                                             &lt;/complexType>
     *                                                           &lt;/element>
     *                                                         &lt;/sequence>
     *                                                       &lt;/restriction>
     *                                                     &lt;/complexContent>
     *                                                   &lt;/complexType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                     &lt;attribute name="RoleId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="SystemId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
        "systemContext"
    })
    public static class SystemContextList {

        @XmlElement(name = "SystemContext", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<SystemRoleType.SystemContextList.SystemContext> systemContext;

        /**
         * Gets the value of the systemContext property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the systemContext property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSystemContext().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SystemRoleType.SystemContextList.SystemContext }
         * 
         * 
         */
        public List<SystemRoleType.SystemContextList.SystemContext> getSystemContext() {
            if (systemContext == null) {
                systemContext = new ArrayList<SystemRoleType.SystemContextList.SystemContext>();
            }
            return this.systemContext;
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
         *         &lt;element name="RoleList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Role" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="RoleScopeList" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="RoleScope" maxOccurs="unbounded" minOccurs="0">
         *                                         &lt;complexType>
         *                                           &lt;complexContent>
         *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                               &lt;sequence>
         *                                                 &lt;element name="RoleScopeName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
         *                                                 &lt;element name="RoleScopeRefId" minOccurs="0">
         *                                                   &lt;complexType>
         *                                                     &lt;simpleContent>
         *                                                       &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
         *                                                         &lt;attribute name="SIF_RefObject" use="required">
         *                                                           &lt;simpleType>
         *                                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *                                                               &lt;enumeration value="SchoolInfo"/>
         *                                                               &lt;enumeration value="TeachingGroup"/>
         *                                                               &lt;enumeration value="StudentActivityParticipation"/>
         *                                                             &lt;/restriction>
         *                                                           &lt;/simpleType>
         *                                                         &lt;/attribute>
         *                                                       &lt;/extension>
         *                                                     &lt;/simpleContent>
         *                                                   &lt;/complexType>
         *                                                 &lt;/element>
         *                                               &lt;/sequence>
         *                                             &lt;/restriction>
         *                                           &lt;/complexContent>
         *                                         &lt;/complexType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                           &lt;attribute name="RoleId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="SystemId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "roleList"
        })
        public static class SystemContext {

            @XmlElementRef(name = "RoleList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
            protected JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList> roleList;
            @XmlAttribute(name = "SystemId", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            @XmlSchemaType(name = "normalizedString")
            protected String systemId;

            /**
             * Gets the value of the roleList property.
             * 
             * @return
             *     possible object is
             *     {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList }{@code >}
             *     
             */
            public JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList> getRoleList() {
                return roleList;
            }

            /**
             * Sets the value of the roleList property.
             * 
             * @param value
             *     allowed object is
             *     {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList }{@code >}
             *     
             */
            public void setRoleList(JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList> value) {
                this.roleList = value;
            }

            /**
             * Gets the value of the systemId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSystemId() {
                return systemId;
            }

            /**
             * Sets the value of the systemId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSystemId(String value) {
                this.systemId = value;
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
             *         &lt;element name="Role" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="RoleScopeList" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="RoleScope" maxOccurs="unbounded" minOccurs="0">
             *                               &lt;complexType>
             *                                 &lt;complexContent>
             *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                                     &lt;sequence>
             *                                       &lt;element name="RoleScopeName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
             *                                       &lt;element name="RoleScopeRefId" minOccurs="0">
             *                                         &lt;complexType>
             *                                           &lt;simpleContent>
             *                                             &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
             *                                               &lt;attribute name="SIF_RefObject" use="required">
             *                                                 &lt;simpleType>
             *                                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
             *                                                     &lt;enumeration value="SchoolInfo"/>
             *                                                     &lt;enumeration value="TeachingGroup"/>
             *                                                     &lt;enumeration value="StudentActivityParticipation"/>
             *                                                   &lt;/restriction>
             *                                                 &lt;/simpleType>
             *                                               &lt;/attribute>
             *                                             &lt;/extension>
             *                                           &lt;/simpleContent>
             *                                         &lt;/complexType>
             *                                       &lt;/element>
             *                                     &lt;/sequence>
             *                                   &lt;/restriction>
             *                                 &lt;/complexContent>
             *                               &lt;/complexType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *                 &lt;attribute name="RoleId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
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
                "role"
            })
            public static class RoleList {

                @XmlElement(name = "Role", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                protected List<SystemRoleType.SystemContextList.SystemContext.RoleList.Role> role;

                /**
                 * Gets the value of the role property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the role property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getRole().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role }
                 * 
                 * 
                 */
                public List<SystemRoleType.SystemContextList.SystemContext.RoleList.Role> getRole() {
                    if (role == null) {
                        role = new ArrayList<SystemRoleType.SystemContextList.SystemContext.RoleList.Role>();
                    }
                    return this.role;
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
                 *         &lt;element name="RoleScopeList" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="RoleScope" maxOccurs="unbounded" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;complexContent>
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                           &lt;sequence>
                 *                             &lt;element name="RoleScopeName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
                 *                             &lt;element name="RoleScopeRefId" minOccurs="0">
                 *                               &lt;complexType>
                 *                                 &lt;simpleContent>
                 *                                   &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
                 *                                     &lt;attribute name="SIF_RefObject" use="required">
                 *                                       &lt;simpleType>
                 *                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
                 *                                           &lt;enumeration value="SchoolInfo"/>
                 *                                           &lt;enumeration value="TeachingGroup"/>
                 *                                           &lt;enumeration value="StudentActivityParticipation"/>
                 *                                         &lt;/restriction>
                 *                                       &lt;/simpleType>
                 *                                     &lt;/attribute>
                 *                                   &lt;/extension>
                 *                                 &lt;/simpleContent>
                 *                               &lt;/complexType>
                 *                             &lt;/element>
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
                 *       &lt;/sequence>
                 *       &lt;attribute name="RoleId" use="required" type="{http://www.w3.org/2001/XMLSchema}normalizedString" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "roleScopeList"
                })
                public static class Role {

                    @XmlElementRef(name = "RoleScopeList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                    protected JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList> roleScopeList;
                    @XmlAttribute(name = "RoleId", required = true)
                    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
                    @XmlSchemaType(name = "normalizedString")
                    protected String roleId;

                    /**
                     * Gets the value of the roleScopeList property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList }{@code >}
                     *     
                     */
                    public JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList> getRoleScopeList() {
                        return roleScopeList;
                    }

                    /**
                     * Sets the value of the roleScopeList property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList }{@code >}
                     *     
                     */
                    public void setRoleScopeList(JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList> value) {
                        this.roleScopeList = value;
                    }

                    /**
                     * Gets the value of the roleId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getRoleId() {
                        return roleId;
                    }

                    /**
                     * Sets the value of the roleId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setRoleId(String value) {
                        this.roleId = value;
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
                     *         &lt;element name="RoleScope" maxOccurs="unbounded" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;complexContent>
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                 &lt;sequence>
                     *                   &lt;element name="RoleScopeName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
                     *                   &lt;element name="RoleScopeRefId" minOccurs="0">
                     *                     &lt;complexType>
                     *                       &lt;simpleContent>
                     *                         &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
                     *                           &lt;attribute name="SIF_RefObject" use="required">
                     *                             &lt;simpleType>
                     *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
                     *                                 &lt;enumeration value="SchoolInfo"/>
                     *                                 &lt;enumeration value="TeachingGroup"/>
                     *                                 &lt;enumeration value="StudentActivityParticipation"/>
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
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "roleScope"
                    })
                    public static class RoleScopeList {

                        @XmlElement(name = "RoleScope", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
                        protected List<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope> roleScope;

                        /**
                         * Gets the value of the roleScope property.
                         * 
                         * <p>
                         * This accessor method returns a reference to the live list,
                         * not a snapshot. Therefore any modification you make to the
                         * returned list will be present inside the JAXB object.
                         * This is why there is not a <CODE>set</CODE> method for the roleScope property.
                         * 
                         * <p>
                         * For example, to add a new item, do as follows:
                         * <pre>
                         *    getRoleScope().add(newItem);
                         * </pre>
                         * 
                         * 
                         * <p>
                         * Objects of the following type(s) are allowed in the list
                         * {@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope }
                         * 
                         * 
                         */
                        public List<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope> getRoleScope() {
                            if (roleScope == null) {
                                roleScope = new ArrayList<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope>();
                            }
                            return this.roleScope;
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
                         *         &lt;element name="RoleScopeName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
                         *         &lt;element name="RoleScopeRefId" minOccurs="0">
                         *           &lt;complexType>
                         *             &lt;simpleContent>
                         *               &lt;extension base="&lt;http://www.SIFinfo.org/au/datamodel/1.3>IdRefTypeOrEmpty">
                         *                 &lt;attribute name="SIF_RefObject" use="required">
                         *                   &lt;simpleType>
                         *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
                         *                       &lt;enumeration value="SchoolInfo"/>
                         *                       &lt;enumeration value="TeachingGroup"/>
                         *                       &lt;enumeration value="StudentActivityParticipation"/>
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
                            "roleScopeName",
                            "roleScopeRefId"
                        })
                        public static class RoleScope {

                            @XmlElementRef(name = "RoleScopeName", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                            protected JAXBElement<String> roleScopeName;
                            @XmlElementRef(name = "RoleScopeRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
                            protected JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId> roleScopeRefId;

                            /**
                             * Gets the value of the roleScopeName property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link JAXBElement }{@code <}{@link String }{@code >}
                             *     
                             */
                            public JAXBElement<String> getRoleScopeName() {
                                return roleScopeName;
                            }

                            /**
                             * Sets the value of the roleScopeName property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link JAXBElement }{@code <}{@link String }{@code >}
                             *     
                             */
                            public void setRoleScopeName(JAXBElement<String> value) {
                                this.roleScopeName = value;
                            }

                            /**
                             * Gets the value of the roleScopeRefId property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId }{@code >}
                             *     
                             */
                            public JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId> getRoleScopeRefId() {
                                return roleScopeRefId;
                            }

                            /**
                             * Sets the value of the roleScopeRefId property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId }{@code >}
                             *     
                             */
                            public void setRoleScopeRefId(JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId> value) {
                                this.roleScopeRefId = value;
                            }


                            /**
                             * The  SIF_RefId that provides  the source Object for this RoleScope.
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
                             *             &lt;enumeration value="SchoolInfo"/>
                             *             &lt;enumeration value="TeachingGroup"/>
                             *             &lt;enumeration value="StudentActivityParticipation"/>
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
                            public static class RoleScopeRefId {

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

                    }

                }

            }

        }

    }

}
