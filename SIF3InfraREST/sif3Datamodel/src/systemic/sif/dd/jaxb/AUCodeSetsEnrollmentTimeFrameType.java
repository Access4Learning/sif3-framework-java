
package systemic.sif.dd.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsEnrollmentTimeFrameType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsEnrollmentTimeFrameType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="H"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsEnrollmentTimeFrameType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsEnrollmentTimeFrameType {


    /**
     * Current
     * 
     */
    C,

    /**
     * Future
     * 
     */
    F,

    /**
     * Historical
     * 
     */
    H;

    public String value() {
        return name();
    }

    public static AUCodeSetsEnrollmentTimeFrameType fromValue(String v) {
        return valueOf(v);
    }

}
