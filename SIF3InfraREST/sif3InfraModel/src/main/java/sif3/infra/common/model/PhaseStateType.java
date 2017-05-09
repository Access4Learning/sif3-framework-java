
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for phaseStateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="phaseStateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NOTAPPLICABLE"/>
 *     &lt;enumeration value="NOTSTARTED"/>
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="SKIPPED"/>
 *     &lt;enumeration value="INPROGRESS"/>
 *     &lt;enumeration value="COMPLETED"/>
 *     &lt;enumeration value="FAILED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "phaseStateType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
@XmlEnum
public enum PhaseStateType {

    NOTAPPLICABLE,
    NOTSTARTED,
    PENDING,
    SKIPPED,
    INPROGRESS,
    COMPLETED,
    FAILED;

    public String value() {
        return name();
    }

    public static PhaseStateType fromValue(String v) {
        return valueOf(v);
    }

}
