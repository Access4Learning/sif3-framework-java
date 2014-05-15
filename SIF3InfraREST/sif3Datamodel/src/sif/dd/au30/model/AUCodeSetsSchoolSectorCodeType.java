
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsSchoolSectorCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsSchoolSectorCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Gov"/>
 *     &lt;enumeration value="NG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsSchoolSectorCodeType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsSchoolSectorCodeType {


    /**
     * Government School
     * 
     */
    @XmlEnumValue("Gov")
    GOV("Gov"),

    /**
     * Non-Government School
     * 
     */
    NG("NG");
    private final String value;

    AUCodeSetsSchoolSectorCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AUCodeSetsSchoolSectorCodeType fromValue(String v) {
        for (AUCodeSetsSchoolSectorCodeType c: AUCodeSetsSchoolSectorCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
