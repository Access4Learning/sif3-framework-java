//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.09 at 02:58:53 PM PDT 
//


package sif.dd.us30.model;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.pds.sifatrest.api.dm.ps package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StateProvince_QNAME = new QName("", "state_province");
    private final static QName _BirthDate_QNAME = new QName("", "birth_date");
    private final static QName _Street_QNAME = new QName("", "street");
    private final static QName _Ssn_QNAME = new QName("", "ssn");
    private final static QName _Number_QNAME = new QName("", "number");
    private final static QName _StudentUsername_QNAME = new QName("", "student_username");
    private final static QName _City_QNAME = new QName("", "city");
    private final static QName _Id_QNAME = new QName("", "id");
    private final static QName _GridLocation_QNAME = new QName("", "grid_location");
    private final static QName _LocalId_QNAME = new QName("", "local_id");
    private final static QName _FirstName_QNAME = new QName("", "first_name");
    private final static QName _ProjectedGraduationYear_QNAME = new QName("", "projected_graduation_year");
    private final static QName _PostalCode_QNAME = new QName("", "postal_code");
    private final static QName _LastName_QNAME = new QName("", "last_name");
    private final static QName _Gender_QNAME = new QName("", "gender");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.pds.sifatrest.api.dm.ps
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Mailing }
     * 
     */
    public Mailing createMailing() {
        return new Mailing();
    }

    /**
     * Create an instance of {@link Physical }
     * 
     */
    public Physical createPhysical() {
        return new Physical();
    }

    /**
     * Create an instance of {@link Students }
     * 
     */
    public Students createStudents() {
        return new Students();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link Name }
     * 
     */
    public Name createName() {
        return new Name();
    }

    /**
     * Create an instance of {@link Demographics }
     * 
     */
    public Demographics createDemographics() {
        return new Demographics();
    }

    /**
     * Create an instance of {@link Addresses }
     * 
     */
    public Addresses createAddresses() {
        return new Addresses();
    }

    /**
     * Create an instance of {@link Phones }
     * 
     */
    public Phones createPhones() {
        return new Phones();
    }

    /**
     * Create an instance of {@link Main }
     * 
     */
    public Main createMain() {
        return new Main();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "state_province")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStateProvince(String value) {
        return new JAXBElement<String>(_StateProvince_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "birth_date")
    public JAXBElement<XMLGregorianCalendar> createBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BirthDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "street")
    public JAXBElement<String> createStreet(String value) {
        return new JAXBElement<String>(_Street_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ssn")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSsn(String value) {
        return new JAXBElement<String>(_Ssn_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "number")
    public JAXBElement<String> createNumber(String value) {
        return new JAXBElement<String>(_Number_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "student_username")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentUsername(String value) {
        return new JAXBElement<String>(_StudentUsername_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "city")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createCity(String value) {
        return new JAXBElement<String>(_City_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id")
    public JAXBElement<BigInteger> createId(BigInteger value) {
        return new JAXBElement<BigInteger>(_Id_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "grid_location")
    public JAXBElement<String> createGridLocation(String value) {
        return new JAXBElement<String>(_GridLocation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "local_id")
    public JAXBElement<BigInteger> createLocalId(BigInteger value) {
        return new JAXBElement<BigInteger>(_LocalId_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "first_name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createFirstName(String value) {
        return new JAXBElement<String>(_FirstName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "projected_graduation_year")
    public JAXBElement<BigInteger> createProjectedGraduationYear(BigInteger value) {
        return new JAXBElement<BigInteger>(_ProjectedGraduationYear_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "postal_code")
    public JAXBElement<BigInteger> createPostalCode(BigInteger value) {
        return new JAXBElement<BigInteger>(_PostalCode_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "last_name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLastName(String value) {
        return new JAXBElement<String>(_LastName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gender")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createGender(String value) {
        return new JAXBElement<String>(_Gender_QNAME, String.class, null, value);
    }

}
