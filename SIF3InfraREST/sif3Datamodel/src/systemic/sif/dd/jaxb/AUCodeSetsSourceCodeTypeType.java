
package systemic.sif.dd.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsSourceCodeTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsSourceCodeTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="O"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="T"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsSourceCodeTypeType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsSourceCodeTypeType {


    /**
     * Provided by the child (ie pupil)
     * 
     */
    C,

    /**
     * Other 
     * 
     */
    O,

    /**
     * Provided by the parent
     * 
     */
    P,

    /**
     * Ascribed by the current school
     * 
     */
    S,

    /**
     * Ascribed by a previous school
     * 
     */
    T;

    public String value() {
        return name();
    }

    public static AUCodeSetsSourceCodeTypeType fromValue(String v) {
        return valueOf(v);
    }

}
