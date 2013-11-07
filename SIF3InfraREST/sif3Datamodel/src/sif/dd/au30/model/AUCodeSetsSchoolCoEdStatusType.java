
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsSchoolCoEdStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsSchoolCoEdStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="M"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsSchoolCoEdStatusType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsSchoolCoEdStatusType {


    /**
     * Co-Educational
     * 
     */
    C,

    /**
     * Female
     * 
     */
    F,

    /**
     * Male
     * 
     */
    M;

    public String value() {
        return name();
    }

    public static AUCodeSetsSchoolCoEdStatusType fromValue(String v) {
        return valueOf(v);
    }

}
