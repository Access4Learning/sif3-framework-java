
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jobStateType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="jobStateType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="NOTSTARTED"/>
 *     &lt;enumeration value="INPROGRESS"/>
 *     &lt;enumeration value="COMPLETED"/>
 *     &lt;enumeration value="FAILED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "jobStateType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
@XmlEnum
public enum JobStateType {

    NOTSTARTED,
    INPROGRESS,
    COMPLETED,
    FAILED;

    public String value() {
        return name();
    }

    public static JobStateType fromValue(String v) {
        return valueOf(v);
    }

}
