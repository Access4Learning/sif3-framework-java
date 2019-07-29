
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A set of technical or process standards to ensure the clause is met.
 * 
 * <p>Java class for benchmarkType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="benchmarkType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="benchmarkName" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="benchmarkId" type="{http://www.sifassociation.org/infrastructure/3.3}gCoreLocalIdType" minOccurs="0"/>
 *         &lt;element name="benchmarkDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="benchmarkURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "benchmarkType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "benchmarkName",
    "benchmarkId",
    "benchmarkDescription",
    "benchmarkURL"
})
public class BenchmarkType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String benchmarkName;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String benchmarkId;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String benchmarkDescription;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String benchmarkURL;

    /**
     * Gets the value of the benchmarkName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenchmarkName() {
        return benchmarkName;
    }

    /**
     * Sets the value of the benchmarkName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenchmarkName(String value) {
        this.benchmarkName = value;
    }

    /**
     * Gets the value of the benchmarkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenchmarkId() {
        return benchmarkId;
    }

    /**
     * Sets the value of the benchmarkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenchmarkId(String value) {
        this.benchmarkId = value;
    }

    /**
     * Gets the value of the benchmarkDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenchmarkDescription() {
        return benchmarkDescription;
    }

    /**
     * Sets the value of the benchmarkDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenchmarkDescription(String value) {
        this.benchmarkDescription = value;
    }

    /**
     * Gets the value of the benchmarkURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBenchmarkURL() {
        return benchmarkURL;
    }

    /**
     * Sets the value of the benchmarkURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBenchmarkURL(String value) {
        this.benchmarkURL = value;
    }

}
