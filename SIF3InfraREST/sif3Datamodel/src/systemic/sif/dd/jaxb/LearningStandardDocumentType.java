
package systemic.sif.dd.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         A curriculum standards document or the like published by a national, state, district, school site, professional association or other interested party. The LearningStandardDocument reflects an expectation of student work.
 *       
 * 
 * <p>Java class for LearningStandardDocumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LearningStandardDocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RichDescription" type="{http://www.SIFinfo.org/au/datamodel/1.3}AbstractContentElementType" minOccurs="0"/>
 *         &lt;element name="Source" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="National"/>
 *               &lt;enumeration value="State"/>
 *               &lt;enumeration value="Regional"/>
 *               &lt;enumeration value="District"/>
 *               &lt;enumeration value="Site"/>
 *               &lt;enumeration value="Classroom"/>
 *               &lt;enumeration value="Publisher"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Organizations" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Organization" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Authors" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Author" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="OrganizationContactPoint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubjectAreas" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}ACStrandSubjectArea" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DocumentStatus" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *               &lt;enumeration value="Draft"/>
 *               &lt;enumeration value="Adopted"/>
 *               &lt;enumeration value="Archived"/>
 *               &lt;enumeration value="Unknown"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DocumentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="LocalAdoptionDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="LocalArchiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="EndOfLifeDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="Copyright" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="Holder" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="YearLevels" type="{http://www.SIFinfo.org/au/datamodel/1.3}YearLevelsType" minOccurs="0"/>
 *         &lt;element name="RepositoryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="LearningStandardItemRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="RelatedLearningStandards" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LearningStandardDocumentRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" />
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LearningStandardDocumentType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "title",
    "description",
    "richDescription",
    "source",
    "organizations",
    "authors",
    "organizationContactPoint",
    "subjectAreas",
    "documentStatus",
    "documentDate",
    "localAdoptionDate",
    "localArchiveDate",
    "endOfLifeDate",
    "copyright",
    "yearLevels",
    "repositoryDate",
    "learningStandardItemRefId",
    "relatedLearningStandards",
    "sifMetadata",
    "sifExtendedElements"
})
public class LearningStandardDocumentType {

    @XmlElement(name = "Title", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String title;
    @XmlElementRef(name = "Description", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "RichDescription", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AbstractContentElementType> richDescription;
    @XmlElement(name = "Source", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String source;
    @XmlElement(name = "Organizations", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected LearningStandardDocumentType.Organizations organizations;
    @XmlElementRef(name = "Authors", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LearningStandardDocumentType.Authors> authors;
    @XmlElementRef(name = "OrganizationContactPoint", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> organizationContactPoint;
    @XmlElement(name = "SubjectAreas", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    protected LearningStandardDocumentType.SubjectAreas subjectAreas;
    @XmlElement(name = "DocumentStatus", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String documentStatus;
    @XmlElementRef(name = "DocumentDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> documentDate;
    @XmlElementRef(name = "LocalAdoptionDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> localAdoptionDate;
    @XmlElementRef(name = "LocalArchiveDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> localArchiveDate;
    @XmlElementRef(name = "EndOfLifeDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> endOfLifeDate;
    @XmlElementRef(name = "Copyright", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LearningStandardDocumentType.Copyright> copyright;
    @XmlElementRef(name = "YearLevels", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<YearLevelsType> yearLevels;
    @XmlElementRef(name = "RepositoryDate", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> repositoryDate;
    @XmlElement(name = "LearningStandardItemRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String learningStandardItemRefId;
    @XmlElementRef(name = "RelatedLearningStandards", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<LearningStandardDocumentType.RelatedLearningStandards> relatedLearningStandards;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace", required = true)
    protected String lang;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = value;
    }

    /**
     * Gets the value of the richDescription property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
     *     
     */
    public JAXBElement<AbstractContentElementType> getRichDescription() {
        return richDescription;
    }

    /**
     * Sets the value of the richDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}
     *     
     */
    public void setRichDescription(JAXBElement<AbstractContentElementType> value) {
        this.richDescription = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the organizations property.
     * 
     * @return
     *     possible object is
     *     {@link LearningStandardDocumentType.Organizations }
     *     
     */
    public LearningStandardDocumentType.Organizations getOrganizations() {
        return organizations;
    }

    /**
     * Sets the value of the organizations property.
     * 
     * @param value
     *     allowed object is
     *     {@link LearningStandardDocumentType.Organizations }
     *     
     */
    public void setOrganizations(LearningStandardDocumentType.Organizations value) {
        this.organizations = value;
    }

    /**
     * Gets the value of the authors property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.Authors }{@code >}
     *     
     */
    public JAXBElement<LearningStandardDocumentType.Authors> getAuthors() {
        return authors;
    }

    /**
     * Sets the value of the authors property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.Authors }{@code >}
     *     
     */
    public void setAuthors(JAXBElement<LearningStandardDocumentType.Authors> value) {
        this.authors = value;
    }

    /**
     * Gets the value of the organizationContactPoint property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrganizationContactPoint() {
        return organizationContactPoint;
    }

    /**
     * Sets the value of the organizationContactPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrganizationContactPoint(JAXBElement<String> value) {
        this.organizationContactPoint = value;
    }

    /**
     * Gets the value of the subjectAreas property.
     * 
     * @return
     *     possible object is
     *     {@link LearningStandardDocumentType.SubjectAreas }
     *     
     */
    public LearningStandardDocumentType.SubjectAreas getSubjectAreas() {
        return subjectAreas;
    }

    /**
     * Sets the value of the subjectAreas property.
     * 
     * @param value
     *     allowed object is
     *     {@link LearningStandardDocumentType.SubjectAreas }
     *     
     */
    public void setSubjectAreas(LearningStandardDocumentType.SubjectAreas value) {
        this.subjectAreas = value;
    }

    /**
     * Gets the value of the documentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentStatus() {
        return documentStatus;
    }

    /**
     * Sets the value of the documentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentStatus(String value) {
        this.documentStatus = value;
    }

    /**
     * Gets the value of the documentDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getDocumentDate() {
        return documentDate;
    }

    /**
     * Sets the value of the documentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setDocumentDate(JAXBElement<XMLGregorianCalendar> value) {
        this.documentDate = value;
    }

    /**
     * Gets the value of the localAdoptionDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getLocalAdoptionDate() {
        return localAdoptionDate;
    }

    /**
     * Sets the value of the localAdoptionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setLocalAdoptionDate(JAXBElement<XMLGregorianCalendar> value) {
        this.localAdoptionDate = value;
    }

    /**
     * Gets the value of the localArchiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getLocalArchiveDate() {
        return localArchiveDate;
    }

    /**
     * Sets the value of the localArchiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setLocalArchiveDate(JAXBElement<XMLGregorianCalendar> value) {
        this.localArchiveDate = value;
    }

    /**
     * Gets the value of the endOfLifeDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getEndOfLifeDate() {
        return endOfLifeDate;
    }

    /**
     * Sets the value of the endOfLifeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setEndOfLifeDate(JAXBElement<XMLGregorianCalendar> value) {
        this.endOfLifeDate = value;
    }

    /**
     * Gets the value of the copyright property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.Copyright }{@code >}
     *     
     */
    public JAXBElement<LearningStandardDocumentType.Copyright> getCopyright() {
        return copyright;
    }

    /**
     * Sets the value of the copyright property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.Copyright }{@code >}
     *     
     */
    public void setCopyright(JAXBElement<LearningStandardDocumentType.Copyright> value) {
        this.copyright = value;
    }

    /**
     * Gets the value of the yearLevels property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public JAXBElement<YearLevelsType> getYearLevels() {
        return yearLevels;
    }

    /**
     * Sets the value of the yearLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}
     *     
     */
    public void setYearLevels(JAXBElement<YearLevelsType> value) {
        this.yearLevels = value;
    }

    /**
     * Gets the value of the repositoryDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getRepositoryDate() {
        return repositoryDate;
    }

    /**
     * Sets the value of the repositoryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setRepositoryDate(JAXBElement<XMLGregorianCalendar> value) {
        this.repositoryDate = value;
    }

    /**
     * Gets the value of the learningStandardItemRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLearningStandardItemRefId() {
        return learningStandardItemRefId;
    }

    /**
     * Sets the value of the learningStandardItemRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLearningStandardItemRefId(String value) {
        this.learningStandardItemRefId = value;
    }

    /**
     * Gets the value of the relatedLearningStandards property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.RelatedLearningStandards }{@code >}
     *     
     */
    public JAXBElement<LearningStandardDocumentType.RelatedLearningStandards> getRelatedLearningStandards() {
        return relatedLearningStandards;
    }

    /**
     * Sets the value of the relatedLearningStandards property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.RelatedLearningStandards }{@code >}
     *     
     */
    public void setRelatedLearningStandards(JAXBElement<LearningStandardDocumentType.RelatedLearningStandards> value) {
        this.relatedLearningStandards = value;
    }

    /**
     * Gets the value of the sifMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public JAXBElement<SIFMetadataType> getSIFMetadata() {
        return sifMetadata;
    }

    /**
     * Sets the value of the sifMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}
     *     
     */
    public void setSIFMetadata(JAXBElement<SIFMetadataType> value) {
        this.sifMetadata = value;
    }

    /**
     * Gets the value of the sifExtendedElements property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public JAXBElement<SIFExtendedElementsType> getSIFExtendedElements() {
        return sifExtendedElements;
    }

    /**
     * Sets the value of the sifExtendedElements property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}
     *     
     */
    public void setSIFExtendedElements(JAXBElement<SIFExtendedElementsType> value) {
        this.sifExtendedElements = value;
    }

    /**
     * Gets the value of the refId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefId() {
        return refId;
    }

    /**
     * Sets the value of the refId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefId(String value) {
        this.refId = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Author" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "author"
    })
    public static class Authors {

        @XmlElement(name = "Author", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected List<String> author;

        /**
         * Gets the value of the author property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the author property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAuthor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAuthor() {
            if (author == null) {
                author = new ArrayList<String>();
            }
            return this.author;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="Holder" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "date",
        "holder"
    })
    public static class Copyright {

        @XmlElementRef(name = "Date", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<XMLGregorianCalendar> date;
        @XmlElementRef(name = "Holder", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
        protected JAXBElement<String> holder;

        /**
         * Gets the value of the date property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public JAXBElement<XMLGregorianCalendar> getDate() {
            return date;
        }

        /**
         * Sets the value of the date property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
         *     
         */
        public void setDate(JAXBElement<XMLGregorianCalendar> value) {
            this.date = value;
        }

        /**
         * Gets the value of the holder property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public JAXBElement<String> getHolder() {
            return holder;
        }

        /**
         * Sets the value of the holder property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link String }{@code >}
         *     
         */
        public void setHolder(JAXBElement<String> value) {
            this.holder = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Organization" type="{http://www.w3.org/2001/XMLSchema}normalizedString" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "organization"
    })
    public static class Organizations {

        @XmlElement(name = "Organization", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected List<String> organization;

        /**
         * Gets the value of the organization property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the organization property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrganization().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getOrganization() {
            if (organization == null) {
                organization = new ArrayList<String>();
            }
            return this.organization;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="LearningStandardDocumentRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "learningStandardDocumentRefId"
    })
    public static class RelatedLearningStandards {

        @XmlElement(name = "LearningStandardDocumentRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected List<String> learningStandardDocumentRefId;

        /**
         * Gets the value of the learningStandardDocumentRefId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the learningStandardDocumentRefId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLearningStandardDocumentRefId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getLearningStandardDocumentRefId() {
            if (learningStandardDocumentRefId == null) {
                learningStandardDocumentRefId = new ArrayList<String>();
            }
            return this.learningStandardDocumentRefId;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://www.SIFinfo.org/au/datamodel/1.3}ACStrandSubjectArea" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "acStrandSubjectArea"
    })
    public static class SubjectAreas {

        @XmlElement(name = "ACStrandSubjectArea", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
        protected List<ACStrandSubjectAreaType> acStrandSubjectArea;

        /**
         * Gets the value of the acStrandSubjectArea property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the acStrandSubjectArea property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getACStrandSubjectArea().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ACStrandSubjectAreaType }
         * 
         * 
         */
        public List<ACStrandSubjectAreaType> getACStrandSubjectArea() {
            if (acStrandSubjectArea == null) {
                acStrandSubjectArea = new ArrayList<ACStrandSubjectAreaType>();
            }
            return this.acStrandSubjectArea;
        }

    }

}
