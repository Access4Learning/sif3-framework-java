
package systemic.sif.dd.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DefinedProtocolsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DefinedProtocolsType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="HTTPS"/>
 *     &lt;enumeration value="HTTP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DefinedProtocolsType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
@XmlEnum
public enum DefinedProtocolsType {

    HTTPS,
    HTTP;

    public String value() {
        return name();
    }

    public static DefinedProtocolsType fromValue(String v) {
        return valueOf(v);
    }

}
