
package systemic.sif.dd.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsYesOrNoCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsYesOrNoCategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="N"/>
 *     &lt;enumeration value="U"/>
 *     &lt;enumeration value="X"/>
 *     &lt;enumeration value="Y"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsYesOrNoCategoryType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsYesOrNoCategoryType {


    /**
     * No
     * 
     */
    N,

    /**
     * Unknown
     * 
     */
    U,

    /**
     * Not Provided
     * 
     */
    X,

    /**
     * Yes
     * 
     */
    Y;

    public String value() {
        return name();
    }

    public static AUCodeSetsYesOrNoCategoryType fromValue(String v) {
        return valueOf(v);
    }

}
