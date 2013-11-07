
package sif.dd.au30.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AUCodeSetsStateTerritoryCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AUCodeSetsStateTerritoryCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="ACT"/>
 *     &lt;enumeration value="NSW"/>
 *     &lt;enumeration value="NT"/>
 *     &lt;enumeration value="QLD"/>
 *     &lt;enumeration value="SA"/>
 *     &lt;enumeration value="TAS"/>
 *     &lt;enumeration value="VIC"/>
 *     &lt;enumeration value="WA"/>
 *     &lt;enumeration value="XXX"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AUCodeSetsStateTerritoryCodeType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum AUCodeSetsStateTerritoryCodeType {


    /**
     * Australian Capital Territory
     * 
     */
    ACT,

    /**
     * New South Wales
     * 
     */
    NSW,

    /**
     * Northern Territory
     * 
     */
    NT,

    /**
     * Queensland
     * 
     */
    QLD,

    /**
     * South Australia
     * 
     */
    SA,

    /**
     * Tasmania
     * 
     */
    TAS,

    /**
     * Victoria
     * 
     */
    VIC,

    /**
     * Western Australia
     * 
     */
    WA,

    /**
     * Not Provided
     * 
     */
    XXX;

    public String value() {
        return name();
    }

    public static AUCodeSetsStateTerritoryCodeType fromValue(String v) {
        return valueOf(v);
    }

}
