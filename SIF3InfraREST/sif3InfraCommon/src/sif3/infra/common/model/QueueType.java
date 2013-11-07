
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for queueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="polling" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="IMMEDIATE"/>
 *               &lt;enumeration value="LONG"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ownerId" type="{http://www.sifassociation.org/infrastructure/3.0}uuidType" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="queueURI" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="idleTimeout" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="minWaitTime" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/>
 *         &lt;element name="maxConcurrentConnections" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}unsignedInt">
 *               &lt;minInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="created" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="lastAccessed" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="lastModified" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="messageCount" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.sifassociation.org/infrastructure/3.0}uuidType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queueType", namespace = "http://www.sifassociation.org/infrastructure/3.0", propOrder = {
    "polling",
    "ownerId",
    "name",
    "queueURI",
    "idleTimeout",
    "minWaitTime",
    "maxConcurrentConnections",
    "created",
    "lastAccessed",
    "lastModified",
    "messageCount"
})
public class QueueType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected String polling;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String ownerId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String name;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String queueURI;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlSchemaType(name = "unsignedInt")
    protected long idleTimeout;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlSchemaType(name = "unsignedInt")
    protected Long minWaitTime;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    protected Long maxConcurrentConnections;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar created;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastAccessed;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModified;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.0")
    @XmlSchemaType(name = "unsignedInt")
    protected long messageCount;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;

    /**
     * Gets the value of the polling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolling() {
        return polling;
    }

    /**
     * Sets the value of the polling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolling(String value) {
        this.polling = value;
    }

    /**
     * Gets the value of the ownerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of the ownerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerId(String value) {
        this.ownerId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the queueURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueueURI() {
        return queueURI;
    }

    /**
     * Sets the value of the queueURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueueURI(String value) {
        this.queueURI = value;
    }

    /**
     * Gets the value of the idleTimeout property.
     * 
     */
    public long getIdleTimeout() {
        return idleTimeout;
    }

    /**
     * Sets the value of the idleTimeout property.
     * 
     */
    public void setIdleTimeout(long value) {
        this.idleTimeout = value;
    }

    /**
     * Gets the value of the minWaitTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMinWaitTime() {
        return minWaitTime;
    }

    /**
     * Sets the value of the minWaitTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMinWaitTime(Long value) {
        this.minWaitTime = value;
    }

    /**
     * Gets the value of the maxConcurrentConnections property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxConcurrentConnections() {
        return maxConcurrentConnections;
    }

    /**
     * Sets the value of the maxConcurrentConnections property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxConcurrentConnections(Long value) {
        this.maxConcurrentConnections = value;
    }

    /**
     * Gets the value of the created property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreated(XMLGregorianCalendar value) {
        this.created = value;
    }

    /**
     * Gets the value of the lastAccessed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastAccessed() {
        return lastAccessed;
    }

    /**
     * Sets the value of the lastAccessed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastAccessed(XMLGregorianCalendar value) {
        this.lastAccessed = value;
    }

    /**
     * Gets the value of the lastModified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModified() {
        return lastModified;
    }

    /**
     * Sets the value of the lastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModified(XMLGregorianCalendar value) {
        this.lastModified = value;
    }

    /**
     * Gets the value of the messageCount property.
     * 
     */
    public long getMessageCount() {
        return messageCount;
    }

    /**
     * Sets the value of the messageCount property.
     * 
     */
    public void setMessageCount(long value) {
        this.messageCount = value;
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

}
