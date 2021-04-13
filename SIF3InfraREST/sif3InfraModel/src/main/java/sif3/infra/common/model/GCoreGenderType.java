
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gCoreGenderType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="gCoreGenderType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="X"/>
 *     &lt;enumeration value="U"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "gCoreGenderType", namespace = "http://www.sifassociation.org/infrastructure/3.3")
@XmlEnum
public enum GCoreGenderType {

    M,
    F,
    X,
    U;

    public String value() {
        return name();
    }

    public static GCoreGenderType fromValue(String v) {
        return valueOf(v);
    }

}
