
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rightValueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="rightValueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="APPROVED"/>
 *     &lt;enumeration value="SUPPORTED"/>
 *     &lt;enumeration value="UNSUPPORTED"/>
 *     &lt;enumeration value="REJECTED"/>
 *     &lt;enumeration value="REQUESTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "rightValueType", namespace = "http://www.sifassociation.org/infrastructure/3.2")
@XmlEnum
public enum RightValueType {

    APPROVED,
    SUPPORTED,
    UNSUPPORTED,
    REJECTED,
    REQUESTED;

    public String value() {
        return name();
    }

    public static RightValueType fromValue(String v) {
        return valueOf(v);
    }

}
