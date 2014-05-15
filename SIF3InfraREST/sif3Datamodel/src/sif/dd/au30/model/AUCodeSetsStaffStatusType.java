
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsStaffStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsStaffStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="O"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsStaffStatusType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsStaffStatusType {


    /**
     * Active
     * 
     */
    A,

    /**
     * Inactive
     * 
     */
    I,

    /**
     * Suspended
     * 
     */
    S,

    /**
     * On Leave
     * 
     */
    O;

    public String value() {
        return name();
    }

    public static AUCodeSetsStaffStatusType fromValue(String v) {
        return valueOf(v);
    }

}
