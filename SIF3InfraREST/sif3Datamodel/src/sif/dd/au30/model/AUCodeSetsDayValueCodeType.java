
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsDayValueCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsDayValueCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="AM"/>
 *     &lt;enumeration value="Full"/>
 *     &lt;enumeration value="N/A"/>
 *     &lt;enumeration value="Partial"/>
 *     &lt;enumeration value="PM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsDayValueCodeType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsDayValueCodeType {


    /**
     * Morning
     * 
     */
    AM("AM"),

    /**
     * All Day
     * 
     */
    @XmlEnumValue("Full")
    FULL("Full"),

    /**
     * Not Applicable
     * 
     */
    @XmlEnumValue("N/A")
    N_A("N/A"),

    /**
     * Partial Day
     * 
     */
    @XmlEnumValue("Partial")
    PARTIAL("Partial"),

    /**
     * Afternoon
     * 
     */
    PM("PM");
    private final String value;

    AUCodeSetsDayValueCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AUCodeSetsDayValueCodeType fromValue(String v) {
        for (AUCodeSetsDayValueCodeType c: AUCodeSetsDayValueCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
