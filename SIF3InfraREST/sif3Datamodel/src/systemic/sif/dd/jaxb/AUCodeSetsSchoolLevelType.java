
package systemic.sif.dd.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsSchoolLevelType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsSchoolLevelType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="Camp"/>
 *     &lt;enumeration value="Commty"/>
 *     &lt;enumeration value="EarlyCh"/>
 *     &lt;enumeration value="JunPri"/>
 *     &lt;enumeration value="Kgarten"/>
 *     &lt;enumeration value="Kind"/>
 *     &lt;enumeration value="Lang"/>
 *     &lt;enumeration value="MCH"/>
 *     &lt;enumeration value="Middle"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="PreSch"/>
 *     &lt;enumeration value="Pri/Sec"/>
 *     &lt;enumeration value="Prim"/>
 *     &lt;enumeration value="Sec"/>
 *     &lt;enumeration value="Senior"/>
 *     &lt;enumeration value="Special"/>
 *     &lt;enumeration value="Specif"/>
 *     &lt;enumeration value="Supp"/>
 *     &lt;enumeration value="Unknown"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsSchoolLevelType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsSchoolLevelType {


    /**
     * Camp
     * 
     */
    @XmlEnumValue("Camp")
    CAMP("Camp"),

    /**
     * Community College
     * 
     */
    @XmlEnumValue("Commty")
    COMMTY("Commty"),

    /**
     * Early Childhood
     * 
     */
    @XmlEnumValue("EarlyCh")
    EARLY_CH("EarlyCh"),

    /**
     * Junior Primary
     * 
     */
    @XmlEnumValue("JunPri")
    JUN_PRI("JunPri"),

    /**
     * Kindergarten only
     * 
     */
    @XmlEnumValue("Kgarten")
    KGARTEN("Kgarten"),

    /**
     * Preschool/Kindergarten
     * 
     */
    @XmlEnumValue("Kind")
    KIND("Kind"),

    /**
     * Language
     * 
     */
    @XmlEnumValue("Lang")
    LANG("Lang"),

    /**
     * Maternal Child Health Centre
     * 
     */
    MCH("MCH"),

    /**
     * Middle School
     * 
     */
    @XmlEnumValue("Middle")
    MIDDLE("Middle"),

    /**
     * Other
     * 
     */
    @XmlEnumValue("Other")
    OTHER("Other"),

    /**
     * PreSchool only
     * 
     */
    @XmlEnumValue("PreSch")
    PRE_SCH("PreSch"),

    /**
     * Primary/Seconday Combined
     * 
     */
    @XmlEnumValue("Pri/Sec")
    PRI_SEC("Pri/Sec"),

    /**
     * Primary
     * 
     */
    @XmlEnumValue("Prim")
    PRIM("Prim"),

    /**
     * Secondary
     * 
     */
    @XmlEnumValue("Sec")
    SEC("Sec"),

    /**
     * Senior Secondary School
     * 
     */
    @XmlEnumValue("Senior")
    SENIOR("Senior"),

    /**
     * Special
     * 
     */
    @XmlEnumValue("Special")
    SPECIAL("Special"),

    /**
     * Specific Purpose
     * 
     */
    @XmlEnumValue("Specif")
    SPECIF("Specif"),

    /**
     * SupportCentre
     * 
     */
    @XmlEnumValue("Supp")
    SUPP("Supp"),

    /**
     * Unknown
     * 
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown");
    private final String value;

    AUCodeSetsSchoolLevelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AUCodeSetsSchoolLevelType fromValue(String v) {
        for (AUCodeSetsSchoolLevelType c: AUCodeSetsSchoolLevelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
