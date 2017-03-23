
package sif3.infra.common.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for infrastructureServiceNamesType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="infrastructureServiceNamesType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="environment"/>
 *     &lt;enumeration value="provisionRequests"/>
 *     &lt;enumeration value="requestsConnector"/>
 *     &lt;enumeration value="eventsConnector"/>
 *     &lt;enumeration value="queues"/>
 *     &lt;enumeration value="subscriptions"/>
 *     &lt;enumeration value="servicesConnector"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "infrastructureServiceNamesType", namespace = "http://www.sifassociation.org/infrastructure/3.2.1")
@XmlEnum
public enum InfrastructureServiceNamesType {

    @XmlEnumValue("environment")
    ENVIRONMENT("environment"),
    @XmlEnumValue("provisionRequests")
    PROVISION_REQUESTS("provisionRequests"),
    @XmlEnumValue("requestsConnector")
    REQUESTS_CONNECTOR("requestsConnector"),
    @XmlEnumValue("eventsConnector")
    EVENTS_CONNECTOR("eventsConnector"),
    @XmlEnumValue("queues")
    QUEUES("queues"),
    @XmlEnumValue("subscriptions")
    SUBSCRIPTIONS("subscriptions"),
    @XmlEnumValue("servicesConnector")
    SERVICES_CONNECTOR("servicesConnector");
    private final String value;

    InfrastructureServiceNamesType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static InfrastructureServiceNamesType fromValue(String v) {
        for (InfrastructureServiceNamesType c: InfrastructureServiceNamesType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
