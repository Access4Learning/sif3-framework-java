
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for serviceTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="serviceTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="UTILITY"/>
 *     &lt;enumeration value="OBJECT"/>
 *     &lt;enumeration value="FUNCTIONAL"/>
 *     &lt;enumeration value="SERVICEPATH"/>
 *     &lt;enumeration value="XQUERYTEMPLATE"/>
 *     &lt;enumeration value="SERVICE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "serviceTypeType", namespace = "http://www.sifassociation.org/infrastructure/3.3")
@XmlEnum
public enum ServiceTypeType {

    UTILITY,
    OBJECT,
    FUNCTIONAL,
    SERVICEPATH,
    XQUERYTEMPLATE,
    SERVICE;

    public String value() {
        return name();
    }

    public static ServiceTypeType fromValue(String v) {
        return valueOf(v);
    }

}
