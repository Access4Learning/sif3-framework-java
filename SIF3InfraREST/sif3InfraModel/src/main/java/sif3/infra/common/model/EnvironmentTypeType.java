
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for environmentTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="environmentTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="DIRECT"/>
 *     &lt;enumeration value="BROKERED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "environmentTypeType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
@XmlEnum
public enum EnvironmentTypeType {

    DIRECT,
    BROKERED;

    public String value() {
        return name();
    }

    public static EnvironmentTypeType fromValue(String v) {
        return valueOf(v);
    }

}
