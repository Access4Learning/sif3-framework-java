
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Details of the security test required.
 * 
 * <p>Java class for securityTestRequiredType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="securityTestRequiredType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="testType" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="testFrequency" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="remediationRequiredIn" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="shareResults" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Y"/>
 *               &lt;enumeration value="N"/>
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
@XmlType(name = "securityTestRequiredType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "testType",
    "testFrequency",
    "remediationRequiredIn",
    "shareResults"
})
public class SecurityTestRequiredType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String testType;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String testFrequency;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String remediationRequiredIn;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String shareResults;

    /**
     * Gets the value of the testType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestType() {
        return testType;
    }

    /**
     * Sets the value of the testType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestType(String value) {
        this.testType = value;
    }

    /**
     * Gets the value of the testFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestFrequency() {
        return testFrequency;
    }

    /**
     * Sets the value of the testFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestFrequency(String value) {
        this.testFrequency = value;
    }

    /**
     * Gets the value of the remediationRequiredIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemediationRequiredIn() {
        return remediationRequiredIn;
    }

    /**
     * Sets the value of the remediationRequiredIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemediationRequiredIn(String value) {
        this.remediationRequiredIn = value;
    }

    /**
     * Gets the value of the shareResults property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShareResults() {
        return shareResults;
    }

    /**
     * Sets the value of the shareResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShareResults(String value) {
        this.shareResults = value;
    }

}
