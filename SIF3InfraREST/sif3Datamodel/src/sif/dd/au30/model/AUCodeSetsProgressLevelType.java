
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsProgressLevelType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsProgressLevelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Below"/>
 *     &lt;enumeration value="At"/>
 *     &lt;enumeration value="Above"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsProgressLevelType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsProgressLevelType {


    /**
     * Below level
     * 
     */
    @XmlEnumValue("Below")
    BELOW("Below"),

    /**
     * At level
     * 
     */
    @XmlEnumValue("At")
    AT("At"),

    /**
     * Above level
     * 
     */
    @XmlEnumValue("Above")
    ABOVE("Above");
    private final String value;

    AUCodeSetsProgressLevelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AUCodeSetsProgressLevelType fromValue(String v) {
        for (AUCodeSetsProgressLevelType c: AUCodeSetsProgressLevelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
