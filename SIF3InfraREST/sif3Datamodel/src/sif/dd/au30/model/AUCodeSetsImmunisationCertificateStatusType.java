
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsImmunisationCertificateStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsImmunisationCertificateStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="N"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsImmunisationCertificateStatusType", namespace = "http://www.sifassociation.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsImmunisationCertificateStatusType {


    /**
     * Complete
     * 
     */
    C,

    /**
     * Incomplete
     * 
     */
    I,

    /**
     * Not Sighted
     * 
     */
    N;

    public String value() {
        return name();
    }

    public static AUCodeSetsImmunisationCertificateStatusType fromValue(String v) {
        return valueOf(v);
    }

}
