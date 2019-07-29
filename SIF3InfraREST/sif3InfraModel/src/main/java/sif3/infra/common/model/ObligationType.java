
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A requirement imposed by the contract.
 * 
 * <p>Java class for obligationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obligationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="obligationDescription" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="obligationURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="benchmarkList" type="{http://www.sifassociation.org/infrastructure/3.3}benchmarkListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obligationType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "obligationDescription",
    "obligationURL",
    "benchmarkList"
})
public class ObligationType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String obligationDescription;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String obligationURL;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected BenchmarkListType benchmarkList;

    /**
     * Gets the value of the obligationDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObligationDescription() {
        return obligationDescription;
    }

    /**
     * Sets the value of the obligationDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObligationDescription(String value) {
        this.obligationDescription = value;
    }

    /**
     * Gets the value of the obligationURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObligationURL() {
        return obligationURL;
    }

    /**
     * Sets the value of the obligationURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObligationURL(String value) {
        this.obligationURL = value;
    }

    /**
     * Gets the value of the benchmarkList property.
     * 
     * @return
     *     possible object is
     *     {@link BenchmarkListType }
     *     
     */
    public BenchmarkListType getBenchmarkList() {
        return benchmarkList;
    }

    /**
     * Sets the value of the benchmarkList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BenchmarkListType }
     *     
     */
    public void setBenchmarkList(BenchmarkListType value) {
        this.benchmarkList = value;
    }

}
