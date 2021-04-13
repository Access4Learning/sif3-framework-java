
package sif3.infra.common.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A List of benchmarks - where a benchmark is a set of technical or process standards to ensure the clause is met.
 * 
 * <p>Java class for benchmarkListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="benchmarkListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="benchmark" type="{http://www.sifassociation.org/infrastructure/3.3}benchmarkType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "benchmarkListType", namespace = "http://www.sifassociation.org/infrastructure/3.3", propOrder = {
    "benchmark"
})
public class BenchmarkListType {

    @XmlElement(namespace = "http://www.sifassociation.org/infrastructure/3.3")
    protected List<BenchmarkType> benchmark;

    /**
     * Gets the value of the benchmark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the benchmark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBenchmark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BenchmarkType }
     * 
     * 
     */
    public List<BenchmarkType> getBenchmark() {
        if (benchmark == null) {
            benchmark = new ArrayList<BenchmarkType>();
        }
        return this.benchmark;
    }

}
