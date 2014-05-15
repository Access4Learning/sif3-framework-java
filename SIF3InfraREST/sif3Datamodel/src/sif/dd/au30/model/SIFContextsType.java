
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *             A list of SIF contexts that applies to a message or operation.  Typically where used as an optional element,
 *             the omission of this element implies the SIF_Default context applies.
 *           
 * 
 * <p>Java class for SIF_ContextsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIF_ContextsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.sifassociation.org/au/datamodel/1.3}SIF_Context" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIF_ContextsType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sifContext"
})
public class SIFContextsType {

    @XmlElement(name = "SIF_Context", namespace = "http://www.sifassociation.org/au/datamodel/1.3", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected List<String> sifContext;

    /**
     * Gets the value of the sifContext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sifContext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSIFContext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSIFContext() {
        if (sifContext == null) {
            sifContext = new ArrayList<String>();
        }
        return this.sifContext;
    }

}
