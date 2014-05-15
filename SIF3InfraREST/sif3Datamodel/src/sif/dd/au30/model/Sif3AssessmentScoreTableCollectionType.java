
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Sif3AssessmentScoreTableCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sif3AssessmentScoreTableCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sif3AssessmentScoreTable" type="{http://www.sifassociation.org/au/datamodel/1.3}Sif3AssessmentScoreTableType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sif3AssessmentScoreTableCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "sif3AssessmentScoreTable"
})
public class Sif3AssessmentScoreTableCollectionType {

    @XmlElement(name = "Sif3AssessmentScoreTable", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<Sif3AssessmentScoreTableType> sif3AssessmentScoreTable;

    /**
     * Gets the value of the sif3AssessmentScoreTable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sif3AssessmentScoreTable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSif3AssessmentScoreTable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Sif3AssessmentScoreTableType }
     * 
     * 
     */
    public List<Sif3AssessmentScoreTableType> getSif3AssessmentScoreTable() {
        if (sif3AssessmentScoreTable == null) {
            sif3AssessmentScoreTable = new ArrayList<Sif3AssessmentScoreTableType>();
        }
        return this.sif3AssessmentScoreTable;
    }

}
