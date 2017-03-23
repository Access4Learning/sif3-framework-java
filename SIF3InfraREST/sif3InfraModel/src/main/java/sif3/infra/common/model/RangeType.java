
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rangeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rangeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rangeType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1", propOrder = {
    "start",
    "end"
})
public class RangeType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected long start;
    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
    protected long end;

    /**
     * Gets the value of the start property.
     * 
     */
    public long getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     */
    public void setStart(long value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     * 
     */
    public long getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     */
    public void setEnd(long value) {
        this.end = value;
    }

}
