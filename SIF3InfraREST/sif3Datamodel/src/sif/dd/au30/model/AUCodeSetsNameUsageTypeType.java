
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsNameUsageTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsNameUsageTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AKA"/>
 *     &lt;enumeration value="BTH"/>
 *     &lt;enumeration value="LGL"/>
 *     &lt;enumeration value="MDN"/>
 *     &lt;enumeration value="NEW"/>
 *     &lt;enumeration value="OTH"/>
 *     &lt;enumeration value="PRF"/>
 *     &lt;enumeration value="PRV"/>
 *     &lt;enumeration value="STG"/>
 *     &lt;enumeration value="TRB"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsNameUsageTypeType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsNameUsageTypeType {


    /**
     * Also known as or alias
     * 
     */
    AKA,

    /**
     * Name at Birth
     * 
     */
    BTH,

    /**
     * Legal Name of the client as defined by the organisation which collects it (legal not defined in this standard)
     * 
     */
    LGL,

    /**
     * Maiden Name
     * 
     */
    MDN,

    /**
     * New born identification name
     * 
     */
    NEW,

    /**
     * Non specific name usage type
     * 
     */
    OTH,

    /**
     * Preferred name
     * 
     */
    PRF,

    /**
     * Previous name
     * 
     */
    PRV,

    /**
     * Stage name
     * 
     */
    STG,

    /**
     * Tribal Name
     * 
     */
    TRB;

    public String value() {
        return name();
    }

    public static AUCodeSetsNameUsageTypeType fromValue(String v) {
        return valueOf(v);
    }

}
