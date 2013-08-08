
package systemic.sif.dd.jaxb;

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
 * This object is for course information.
 * 
 * <p>Java class for SchoolCourseInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SchoolCourseInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SchoolInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="SchoolLocalId" type="{http://www.SIFinfo.org/au/datamodel/1.3}LocalIdType" minOccurs="0"/>
 *         &lt;element name="SchoolYear" type="{http://www.SIFinfo.org/au/datamodel/1.3}SchoolYearType" minOccurs="0"/>
 *         &lt;element name="TermInfoRefId" type="{http://www.SIFinfo.org/au/datamodel/1.3}IdRefType" minOccurs="0"/>
 *         &lt;element name="CourseCode" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="StateCourseCode" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="DistrictCourseCode" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SubjectAreaList" type="{http://www.SIFinfo.org/au/datamodel/1.3}SubjectAreaListType" minOccurs="0"/>
 *         &lt;element name="CourseTitle" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="InstructionalLevel" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="CourseCredits" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="CoreAcademicCourse" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="GraduationRequirement" type="{http://www.SIFinfo.org/au/datamodel/1.3}AUCodeSetsYesOrNoCategoryType" minOccurs="0"/>
 *         &lt;element name="Department" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="CourseContent" type="{http://www.w3.org/2001/XMLSchema}normalizedString" minOccurs="0"/>
 *         &lt;element name="SIF_Metadata" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_MetadataType" minOccurs="0"/>
 *         &lt;element name="SIF_ExtendedElements" type="{http://www.SIFinfo.org/au/datamodel/1.3}SIF_ExtendedElementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="RefId" use="required" type="{http://www.SIFinfo.org/au/datamodel/1.3}RefIdType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SchoolCourseInfoType", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", propOrder = {
    "schoolInfoRefId",
    "schoolLocalId",
    "schoolYear",
    "termInfoRefId",
    "courseCode",
    "stateCourseCode",
    "districtCourseCode",
    "subjectAreaList",
    "courseTitle",
    "description",
    "instructionalLevel",
    "courseCredits",
    "coreAcademicCourse",
    "graduationRequirement",
    "department",
    "courseContent",
    "sifMetadata",
    "sifExtendedElements"
})
public class SchoolCourseInfoType {

    @XmlElement(name = "SchoolInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String schoolInfoRefId;
    @XmlElementRef(name = "SchoolLocalId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> schoolLocalId;
    @XmlElementRef(name = "SchoolYear", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> schoolYear;
    @XmlElementRef(name = "TermInfoRefId", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> termInfoRefId;
    @XmlElement(name = "CourseCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String courseCode;
    @XmlElementRef(name = "StateCourseCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> stateCourseCode;
    @XmlElementRef(name = "DistrictCourseCode", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> districtCourseCode;
    @XmlElementRef(name = "SubjectAreaList", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SubjectAreaListType> subjectAreaList;
    @XmlElement(name = "CourseTitle", namespace = "http://www.SIFinfo.org/au/datamodel/1.3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String courseTitle;
    @XmlElementRef(name = "Description", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "InstructionalLevel", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> instructionalLevel;
    @XmlElementRef(name = "CourseCredits", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> courseCredits;
    @XmlElementRef(name = "CoreAcademicCourse", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> coreAcademicCourse;
    @XmlElementRef(name = "GraduationRequirement", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<AUCodeSetsYesOrNoCategoryType> graduationRequirement;
    @XmlElementRef(name = "Department", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> department;
    @XmlElementRef(name = "CourseContent", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<String> courseContent;
    @XmlElementRef(name = "SIF_Metadata", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFMetadataType> sifMetadata;
    @XmlElementRef(name = "SIF_ExtendedElements", namespace = "http://www.SIFinfo.org/au/datamodel/1.3", type = JAXBElement.class, required = false)
    protected JAXBElement<SIFExtendedElementsType> sifExtendedElements;
    @XmlAttribute(name = "RefId", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String refId;

    /**
     * Gets the value of the schoolInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchoolInfoRefId() {
        return schoolInfoRefId;
    }

    /**
     * Sets the value of the schoolInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchoolInfoRefId(String value) {
        this.schoolInfoRefId = value;
    }

    /**
     * Gets the value of the schoolLocalId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSchoolLocalId() {
        return schoolLocalId;
    }

    /**
     * Sets the value of the schoolLocalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSchoolLocalId(JAXBElement<String> value) {
        this.schoolLocalId = value;
    }

    /**
     * Gets the value of the schoolYear property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the value of the schoolYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setSchoolYear(JAXBElement<XMLGregorianCalendar> value) {
        this.schoolYear = value;
    }

    /**
     * Gets the value of the termInfoRefId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTermInfoRefId() {
        return termInfoRefId;
    }

    /**
     * Sets the value of the termInfoRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTermInfoRefId(JAXBElement<String> value) {
        this.termInfoRefId = value;
    }

    /**
     * Gets the value of the courseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Sets the value of the courseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseCode(String value) {
        this.courseCode = value;
    }

    /**
     * Gets the value of the stateCourseCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStateCourseCode() {
        return stateCourseCode;
    }

    /**
     * Sets the value of the stateCourseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStateCourseCode(JAXBElement<String> value) {
        this.stateCourseCode = value;
    }

    /**
     * Gets the value of the districtCourseCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDistrictCourseCode() {
        return districtCourseCode;
    }

    /**
     * Sets the value of the districtCourseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDistrictCourseCode(JAXBElement<String> value) {
        this.districtCourseCode = value;
    }

    /**
     * Gets the value of the subjectAreaList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public JAXBElement<SubjectAreaListType> getSubjectAreaList() {
        return subjectAreaList;
    }

    /**
     * Sets the value of the subjectAreaList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}
     *     
     */
    public void setSubjectAreaList(JAXBElement<SubjectAreaListType> value) {
        this.subjectAreaList = value;
    }

    /**
     * Gets the value of the courseTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * Sets the value of the courseTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourseTitle(String value) {
        this.courseTitle = value;
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
     * Gets the value of the instructionalLevel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInstructionalLevel() {
        return instructionalLevel;
    }

    /**
     * Sets the value of the instructionalLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInstructionalLevel(JAXBElement<String> value) {
        this.instructionalLevel = value;
    }

    /**
     * Gets the value of the courseCredits property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCourseCredits() {
        return courseCredits;
    }

    /**
     * Sets the value of the courseCredits property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCourseCredits(JAXBElement<String> value) {
        this.courseCredits = value;
    }

    /**
     * Gets the value of the coreAcademicCourse property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getCoreAcademicCourse() {
        return coreAcademicCourse;
    }

    /**
     * Sets the value of the coreAcademicCourse property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setCoreAcademicCourse(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.coreAcademicCourse = value;
    }

    /**
     * Gets the value of the graduationRequirement property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> getGraduationRequirement() {
        return graduationRequirement;
    }

    /**
     * Sets the value of the graduationRequirement property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}
     *     
     */
    public void setGraduationRequirement(JAXBElement<AUCodeSetsYesOrNoCategoryType> value) {
        this.graduationRequirement = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDepartment(JAXBElement<String> value) {
        this.department = value;
    }

    /**
     * Gets the value of the courseContent property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCourseContent() {
        return courseContent;
    }

    /**
     * Sets the value of the courseContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCourseContent(JAXBElement<String> value) {
        this.courseContent = value;
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

}
