
package sif3.infra.common.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The specific clauses obligations and benchmarks that applies to the subject - teacher, student or parent.
 * 
 * <p>Java class for dataSubjectType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataSubjectType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clauseList" type="{http://www.sifassociation.org/infrastructure/3.3}clauseListType" minOccurs="0"/>
 *         &lt;element name="benchmarkList" type="{http://www.sifassociation.org/infrastructure/3.3}benchmarkListType" minOccurs="0"/>
 *         &lt;element name="fieldList" type="{http://www.sifassociation.org/infrastructure/3.3}fieldListType" minOccurs="0"/>
 *         &lt;element name="respondInDays" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="requirePortability" minOccurs="0">
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
@XmlType(name = "dataSubjectType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "clauseList",
    "benchmarkList",
    "fieldList",
    "respondInDays",
    "requirePortability"
})
public class DataSubjectType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected ClauseListType clauseList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected BenchmarkListType benchmarkList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected FieldListType fieldList;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    protected BigInteger respondInDays;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3", nillable = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String requirePortability;

    /**
     * Gets the value of the clauseList property.
     * 
     * @return
     *     possible object is
     *     {@link ClauseListType }
     *     
     */
    public ClauseListType getClauseList() {
        return clauseList;
    }

    /**
     * Sets the value of the clauseList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClauseListType }
     *     
     */
    public void setClauseList(ClauseListType value) {
        this.clauseList = value;
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

    /**
     * Gets the value of the fieldList property.
     * 
     * @return
     *     possible object is
     *     {@link FieldListType }
     *     
     */
    public FieldListType getFieldList() {
        return fieldList;
    }

    /**
     * Sets the value of the fieldList property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldListType }
     *     
     */
    public void setFieldList(FieldListType value) {
        this.fieldList = value;
    }

    /**
     * Gets the value of the respondInDays property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRespondInDays() {
        return respondInDays;
    }

    /**
     * Sets the value of the respondInDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRespondInDays(BigInteger value) {
        this.respondInDays = value;
    }

    /**
     * Gets the value of the requirePortability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequirePortability() {
        return requirePortability;
    }

    /**
     * Sets the value of the requirePortability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequirePortability(String value) {
        this.requirePortability = value;
    }

}
