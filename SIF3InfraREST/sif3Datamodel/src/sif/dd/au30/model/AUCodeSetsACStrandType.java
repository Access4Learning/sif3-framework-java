
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsACStrandType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsACStrandType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="A"/>
 *     &lt;enumeration value="B"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="G"/>
 *     &lt;enumeration value="H"/>
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="L"/>
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="S"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsACStrandType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsACStrandType {


    /**
     * The arts
     * 
     */
    A,

    /**
     * Business and economics
     * 
     */
    B,

    /**
     * Civics and citizenship
     * 
     */
    C,

    /**
     * Design
     * 
     */
    D,

    /**
     * English
     * 
     */
    E,

    /**
     * Geography
     * 
     */
    G,

    /**
     * History
     * 
     */
    H,

    /**
     * Information and communication technologies
     * 
     */
    I,

    /**
     * Languages
     * 
     */
    L,

    /**
     * Mathematics
     * 
     */
    M,

    /**
     * Health and physical education
     * 
     */
    P,

    /**
     * Science
     * 
     */
    S;

    public String value() {
        return name();
    }

    public static AUCodeSetsACStrandType fromValue(String v) {
        return valueOf(v);
    }

}
