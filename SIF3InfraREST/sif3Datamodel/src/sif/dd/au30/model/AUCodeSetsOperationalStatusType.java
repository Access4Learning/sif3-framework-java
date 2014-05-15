
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsOperationalStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsOperationalStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="B"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="O"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="U"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsOperationalStatusType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsOperationalStatusType {


    /**
     * Building or Construction Started
     * 
     */
    B,

    /**
     * Closed
     * 
     */
    C,

    /**
     * Open
     * 
     */
    O,

    /**
     * Pending
     * 
     */
    P,

    /**
     * Site
     * 
     */
    S,

    /**
     * Unstaffed
     * 
     */
    U;

    public String value() {
        return name();
    }

    public static AUCodeSetsOperationalStatusType fromValue(String v) {
        return valueOf(v);
    }

}
