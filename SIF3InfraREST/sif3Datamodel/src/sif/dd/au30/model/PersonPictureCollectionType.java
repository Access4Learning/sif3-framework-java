
package sif.dd.au30.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PersonPictureCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonPictureCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PersonPicture" type="{http://www.sifassociation.org/au/datamodel/1.3}PersonPictureType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonPictureCollectionType", namespace = "http://www.sifassociation.org/au/datamodel/1.3", propOrder = {
    "personPicture"
})
public class PersonPictureCollectionType {

    @XmlElement(name = "PersonPicture", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
    protected List<PersonPictureType> personPicture;

    /**
     * Gets the value of the personPicture property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personPicture property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonPicture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonPictureType }
     * 
     * 
     */
    public List<PersonPictureType> getPersonPicture() {
        if (personPicture == null) {
            personPicture = new ArrayList<PersonPictureType>();
        }
        return this.personPicture;
    }

}
