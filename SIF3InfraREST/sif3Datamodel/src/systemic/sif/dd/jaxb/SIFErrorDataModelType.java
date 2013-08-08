
package systemic.sif.dd.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This element is used to signal an unsuccessful response.
 * 
 * <p>Java class for SIF_ErrorDataModelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_ErrorDataModelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SIF_Category" type="{http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureErrorCategoryType" minOccurs="0"/>
 *         &lt;element name="SIF_Code" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union memberTypes=" {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureXMLValidationErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureEncryptionErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureAuthenticationErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureAccessAndPermissionErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureRegistrationErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureProvisionErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureSubscriptionErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureRequestAndResponseErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureEventReportingAndProcessingErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureTransportErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureSystemErrorType {http://www.SIFinfo.org/au/datamodel/1.3}InfrastructureGenericMessageHandlingErrorType {http://www.w3.org/2001/XMLSchema}token">
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SIF_Desc" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SIF_ExtendedDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF_ErrorDataModelType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "sifCategory",
    "sifCode",
    "sifDesc",
    "sifExtendedDesc"
})
public class SIFErrorDataModelType {

    @XmlElement(name = "SIF_Category", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sifCategory;
    @XmlElement(name = "SIF_Code", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected String sifCode;
    @XmlElement(name = "SIF_Desc", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected String sifDesc;
    @XmlElementRef(name = "SIF_ExtendedDesc", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sifExtendedDesc;

    /**
     * Gets the value of the sifCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIFCategory() {
        return sifCategory;
    }

    /**
     * Sets the value of the sifCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIFCategory(String value) {
        this.sifCategory = value;
    }

    /**
     * Gets the value of the sifCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIFCode() {
        return sifCode;
    }

    /**
     * Sets the value of the sifCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIFCode(String value) {
        this.sifCode = value;
    }

    /**
     * Gets the value of the sifDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIFDesc() {
        return sifDesc;
    }

    /**
     * Sets the value of the sifDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIFDesc(String value) {
        this.sifDesc = value;
    }

    /**
     * Gets the value of the sifExtendedDesc property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSIFExtendedDesc() {
        return sifExtendedDesc;
    }

    /**
     * Sets the value of the sifExtendedDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSIFExtendedDesc(JAXBElement<String> value) {
        this.sifExtendedDesc = value;
    }

}
