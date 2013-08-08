
package systemic.sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="zone" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="version" type="{http://www.sifassociation.org/infrastructure/3.0}versionType"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;choice>
 *           &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
 *           &lt;element name="codeItems">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="codeItem">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="code">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                     &lt;minLength value="1"/>
 *                                     &lt;maxLength value="16"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
 *                               &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
 *                               &lt;element name="value">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                     &lt;minLength value="0"/>
 *                                     &lt;maxLength value="128"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="description" minOccurs="0">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
 *                                     &lt;minLength value="0"/>
 *                                     &lt;maxLength value="1024"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="definition">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
 *                                     &lt;minLength value="0"/>
 *                                     &lt;maxLength value="4096"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="aliases" minOccurs="0">
 *                                 &lt;complexType>
 *                                   &lt;complexContent>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                       &lt;sequence>
 *                                         &lt;element name="alias">
 *                                           &lt;complexType>
 *                                             &lt;complexContent>
 *                                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                 &lt;sequence>
 *                                                   &lt;element name="code">
 *                                                     &lt;complexType>
 *                                                       &lt;complexContent>
 *                                                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                           &lt;sequence>
 *                                                             &lt;element name="old" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                                                             &lt;element name="official" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                                                             &lt;element name="value">
 *                                                               &lt;simpleType>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                                                   &lt;minLength value="1"/>
 *                                                                   &lt;maxLength value="16"/>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/simpleType>
 *                                                             &lt;/element>
 *                                                           &lt;/sequence>
 *                                                         &lt;/restriction>
 *                                                       &lt;/complexContent>
 *                                                     &lt;/complexType>
 *                                                   &lt;/element>
 *                                                   &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
 *                                                   &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
 *                                                 &lt;/sequence>
 *                                               &lt;/restriction>
 *                                             &lt;/complexContent>
 *                                           &lt;/complexType>
 *                                         &lt;/element>
 *                                       &lt;/sequence>
 *                                     &lt;/restriction>
 *                                   &lt;/complexContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                               &lt;element name="action">
 *                                 &lt;simpleType>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                     &lt;enumeration value="ADD"/>
 *                                     &lt;enumeration value="CHANGE"/>
 *                                     &lt;enumeration value="DEPRECATED"/>
 *                                     &lt;enumeration value="DELETE"/>
 *                                   &lt;/restriction>
 *                                 &lt;/simpleType>
 *                               &lt;/element>
 *                               &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;minLength value="0"/>
 *             &lt;maxLength value="12"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zone",
    "version",
    "timestamp",
    "source",
    "codeItems"
})
@XmlRootElement(name = "codeSet", namespace = "http://www.sifassociation.org/infrastructure/3.0")
public class CodeSet {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String zone;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String version;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    protected String source;
    protected CodeSet.CodeItems codeItems;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;

    /**
     * Gets the value of the zone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZone() {
        return zone;
    }

    /**
     * Sets the value of the zone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZone(String value) {
        this.zone = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the codeItems property.
     * 
     * @return
     *     possible object is
     *     {@link CodeSet.CodeItems }
     *     
     */
    public CodeSet.CodeItems getCodeItems() {
        return codeItems;
    }

    /**
     * Sets the value of the codeItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeSet.CodeItems }
     *     
     */
    public void setCodeItems(CodeSet.CodeItems value) {
        this.codeItems = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     *         &lt;element name="codeItem">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="code">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;minLength value="1"/>
     *                         &lt;maxLength value="16"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
     *                   &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
     *                   &lt;element name="value">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                         &lt;minLength value="0"/>
     *                         &lt;maxLength value="128"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="description" minOccurs="0">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
     *                         &lt;minLength value="0"/>
     *                         &lt;maxLength value="1024"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="definition">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
     *                         &lt;minLength value="0"/>
     *                         &lt;maxLength value="4096"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="aliases" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="alias">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="code">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="old" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                                                 &lt;element name="official" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                                                 &lt;element name="value">
     *                                                   &lt;simpleType>
     *                                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                                                       &lt;minLength value="1"/>
     *                                                       &lt;maxLength value="16"/>
     *                                                     &lt;/restriction>
     *                                                   &lt;/simpleType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
     *                                       &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
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
     *                   &lt;element name="action">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="ADD"/>
     *                         &lt;enumeration value="CHANGE"/>
     *                         &lt;enumeration value="DEPRECATED"/>
     *                         &lt;enumeration value="DELETE"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
        "codeItem"
    })
    public static class CodeItems {

        @XmlElement(required = true)
        protected CodeSet.CodeItems.CodeItem codeItem;

        /**
         * Gets the value of the codeItem property.
         * 
         * @return
         *     possible object is
         *     {@link CodeSet.CodeItems.CodeItem }
         *     
         */
        public CodeSet.CodeItems.CodeItem getCodeItem() {
            return codeItem;
        }

        /**
         * Sets the value of the codeItem property.
         * 
         * @param value
         *     allowed object is
         *     {@link CodeSet.CodeItems.CodeItem }
         *     
         */
        public void setCodeItem(CodeSet.CodeItems.CodeItem value) {
            this.codeItem = value;
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
         *         &lt;element name="code">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;minLength value="1"/>
         *               &lt;maxLength value="16"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
         *         &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
         *         &lt;element name="value">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *               &lt;minLength value="0"/>
         *               &lt;maxLength value="128"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="description" minOccurs="0">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
         *               &lt;minLength value="0"/>
         *               &lt;maxLength value="1024"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="definition">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}normalizedString">
         *               &lt;minLength value="0"/>
         *               &lt;maxLength value="4096"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="aliases" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="alias">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="code">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="old" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *                                       &lt;element name="official" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *                                       &lt;element name="value">
         *                                         &lt;simpleType>
         *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *                                             &lt;minLength value="1"/>
         *                                             &lt;maxLength value="16"/>
         *                                           &lt;/restriction>
         *                                         &lt;/simpleType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
         *                             &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
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
         *         &lt;element name="action">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *               &lt;enumeration value="ADD"/>
         *               &lt;enumeration value="CHANGE"/>
         *               &lt;enumeration value="DEPRECATED"/>
         *               &lt;enumeration value="DELETE"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
         *         &lt;/element>
         *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
            "source",
            "namespace",
            "value",
            "description",
            "definition",
            "aliases",
            "action",
            "timestamp"
        })
        public static class CodeItem {

            @XmlElement(required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String code;
            @XmlElement(required = true, nillable = true)
            protected String source;
            @XmlElement(required = true, nillable = true)
            protected String namespace;
            @XmlElement(required = true, nillable = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String value;
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String description;
            @XmlElement(required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String definition;
            protected CodeSet.CodeItems.CodeItem.Aliases aliases;
            @XmlElement(required = true)
            protected String action;
            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar timestamp;

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
             * Gets the value of the source property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSource() {
                return source;
            }

            /**
             * Sets the value of the source property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSource(String value) {
                this.source = value;
            }

            /**
             * Gets the value of the namespace property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNamespace() {
                return namespace;
            }

            /**
             * Sets the value of the namespace property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNamespace(String value) {
                this.namespace = value;
            }

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
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the definition property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefinition() {
                return definition;
            }

            /**
             * Sets the value of the definition property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefinition(String value) {
                this.definition = value;
            }

            /**
             * Gets the value of the aliases property.
             * 
             * @return
             *     possible object is
             *     {@link CodeSet.CodeItems.CodeItem.Aliases }
             *     
             */
            public CodeSet.CodeItems.CodeItem.Aliases getAliases() {
                return aliases;
            }

            /**
             * Sets the value of the aliases property.
             * 
             * @param value
             *     allowed object is
             *     {@link CodeSet.CodeItems.CodeItem.Aliases }
             *     
             */
            public void setAliases(CodeSet.CodeItems.CodeItem.Aliases value) {
                this.aliases = value;
            }

            /**
             * Gets the value of the action property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAction() {
                return action;
            }

            /**
             * Sets the value of the action property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAction(String value) {
                this.action = value;
            }

            /**
             * Gets the value of the timestamp property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getTimestamp() {
                return timestamp;
            }

            /**
             * Sets the value of the timestamp property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setTimestamp(XMLGregorianCalendar value) {
                this.timestamp = value;
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
             *         &lt;element name="alias">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="code">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="old" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
             *                             &lt;element name="official" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
             *                             &lt;element name="value">
             *                               &lt;simpleType>
             *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
             *                                   &lt;minLength value="1"/>
             *                                   &lt;maxLength value="16"/>
             *                                 &lt;/restriction>
             *                               &lt;/simpleType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
             *                   &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
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
                "alias"
            })
            public static class Aliases {

                @XmlElement(required = true)
                protected CodeSet.CodeItems.CodeItem.Aliases.Alias alias;

                /**
                 * Gets the value of the alias property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link CodeSet.CodeItems.CodeItem.Aliases.Alias }
                 *     
                 */
                public CodeSet.CodeItems.CodeItem.Aliases.Alias getAlias() {
                    return alias;
                }

                /**
                 * Sets the value of the alias property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link CodeSet.CodeItems.CodeItem.Aliases.Alias }
                 *     
                 */
                public void setAlias(CodeSet.CodeItems.CodeItem.Aliases.Alias value) {
                    this.alias = value;
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
                 *         &lt;element name="code">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="old" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
                 *                   &lt;element name="official" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
                 *                   &lt;element name="value">
                 *                     &lt;simpleType>
                 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
                 *                         &lt;minLength value="1"/>
                 *                         &lt;maxLength value="16"/>
                 *                       &lt;/restriction>
                 *                     &lt;/simpleType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="source" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
                 *         &lt;element name="namespace" type="{http://www.sifassociation.org/infrastructure/3.0}uriType"/>
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
                    "source",
                    "namespace"
                })
                public static class Alias {

                    @XmlElement(required = true)
                    protected CodeSet.CodeItems.CodeItem.Aliases.Alias.Code code;
                    @XmlElement(required = true, nillable = true)
                    protected String source;
                    @XmlElement(required = true, nillable = true)
                    protected String namespace;

                    /**
                     * Gets the value of the code property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link CodeSet.CodeItems.CodeItem.Aliases.Alias.Code }
                     *     
                     */
                    public CodeSet.CodeItems.CodeItem.Aliases.Alias.Code getCode() {
                        return code;
                    }

                    /**
                     * Sets the value of the code property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link CodeSet.CodeItems.CodeItem.Aliases.Alias.Code }
                     *     
                     */
                    public void setCode(CodeSet.CodeItems.CodeItem.Aliases.Alias.Code value) {
                        this.code = value;
                    }

                    /**
                     * Gets the value of the source property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSource() {
                        return source;
                    }

                    /**
                     * Sets the value of the source property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSource(String value) {
                        this.source = value;
                    }

                    /**
                     * Gets the value of the namespace property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNamespace() {
                        return namespace;
                    }

                    /**
                     * Sets the value of the namespace property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNamespace(String value) {
                        this.namespace = value;
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
                     *         &lt;element name="old" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
                     *         &lt;element name="official" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
                     *         &lt;element name="value">
                     *           &lt;simpleType>
                     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
                     *               &lt;minLength value="1"/>
                     *               &lt;maxLength value="16"/>
                     *             &lt;/restriction>
                     *           &lt;/simpleType>
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
                        "old",
                        "official",
                        "value"
                    })
                    public static class Code {

                        protected boolean old;
                        protected boolean official;
                        @XmlElement(required = true)
                        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                        protected String value;

                        /**
                         * Gets the value of the old property.
                         * 
                         */
                        public boolean isOld() {
                            return old;
                        }

                        /**
                         * Sets the value of the old property.
                         * 
                         */
                        public void setOld(boolean value) {
                            this.old = value;
                        }

                        /**
                         * Gets the value of the official property.
                         * 
                         */
                        public boolean isOfficial() {
                            return official;
                        }

                        /**
                         * Sets the value of the official property.
                         * 
                         */
                        public void setOfficial(boolean value) {
                            this.official = value;
                        }

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

                    }

                }

            }

        }

    }

}
