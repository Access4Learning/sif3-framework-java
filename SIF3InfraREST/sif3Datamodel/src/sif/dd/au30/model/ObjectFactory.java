
package sif.dd.au30.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sif.dd.au30.model package. 
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

    private final static QName _AssessmentPackageTypeXMLData_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "XMLData");
    private final static QName _AssessmentPackageTypeSIFMetadata_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_Metadata");
    private final static QName _AssessmentPackageTypeSIFExtendedElements_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_ExtendedElements");
    private final static QName _LanguageList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LanguageList");
    private final static QName _Sif3AssessmentAsset_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentAsset");
    private final static QName _Demographics_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Demographics");
    private final static QName _Sif3AssessmentItemAssociations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentItemAssociations");
    private final static QName _SchoolInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolInfo");
    private final static QName _SIFQuery_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_Query");
    private final static QName _ProjectedGraduationYear_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProjectedGraduationYear");
    private final static QName _StudentScoreSets_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentScoreSets");
    private final static QName _LearningResourcePackage_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningResourcePackage");
    private final static QName _ReportAuthorityInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportAuthorityInfos");
    private final static QName _SectionInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionInfo");
    private final static QName _Sif3AssessmentItemAssociation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentItemAssociation");
    private final static QName _SIFContext_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_Context");
    private final static QName _TeachingGroup_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeachingGroup");
    private final static QName _CalendarSummary_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CalendarSummary");
    private final static QName _StudentSnapshots_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSnapshots");
    private final static QName _StudentPersonals_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentPersonals");
    private final static QName _TermInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TermInfos");
    private final static QName _Sif3AssessmentSubTests_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentSubTests");
    private final static QName _SubjectArea_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubjectArea");
    private final static QName _EmailList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EmailList");
    private final static QName _StaffAssignment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffAssignment");
    private final static QName _Sif3AssessmentSessions_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentSessions");
    private final static QName _PhoneNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PhoneNumber");
    private final static QName _TeachingGroups_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeachingGroups");
    private final static QName _StateProvinceId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StateProvinceId");
    private final static QName _Country_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Country");
    private final static QName _SubjectAreaList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubjectAreaList");
    private final static QName _SchoolYear_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolYear");
    private final static QName _SchoolCourseInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolCourseInfos");
    private final static QName _StudentSchoolEnrollment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSchoolEnrollment");
    private final static QName _AssessmentRegistration_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentRegistration");
    private final static QName _Sif3AssessmentScoreTable_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentScoreTable");
    private final static QName _AssessmentForms_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentForms");
    private final static QName _AssessmentRegistrations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentRegistrations");
    private final static QName _StudentActivityInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentActivityInfos");
    private final static QName _TimeTableSubjects_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableSubjects");
    private final static QName _HomeroomNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "HomeroomNumber");
    private final static QName _OtherNames_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OtherNames");
    private final static QName _Activity_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Activity");
    private final static QName _Email_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Email");
    private final static QName _StaffPersonals_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffPersonals");
    private final static QName _Sif3AssessmentRubrics_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentRubrics");
    private final static QName _OtherCodeList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OtherCodeList");
    private final static QName _AssessmentAdministrations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentAdministrations");
    private final static QName _StudentDailyAttendances_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentDailyAttendances");
    private final static QName _SIFReportObject_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_ReportObject");
    private final static QName _PersonPicture_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PersonPicture");
    private final static QName _ReportManifest_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportManifest");
    private final static QName _StudentParticipations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentParticipations");
    private final static QName _StateProvince_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StateProvince");
    private final static QName _AssessmentForm_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentForm");
    private final static QName _StudentAttendanceSummary_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentAttendanceSummary");
    private final static QName _AggregateStatisticFacts_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AggregateStatisticFacts");
    private final static QName _Sif3AssessmentAdministration_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentAdministration");
    private final static QName _SchoolPrograms_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolPrograms");
    private final static QName _LifeCycle_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LifeCycle");
    private final static QName _StudentPeriodAttendance_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentPeriodAttendance");
    private final static QName _ReportManifests_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportManifests");
    private final static QName _SummaryEnrollmentInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SummaryEnrollmentInfo");
    private final static QName _SummaryEnrollmentInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SummaryEnrollmentInfos");
    private final static QName _StudentContactRelationships_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentContactRelationships");
    private final static QName _Sif3AssessmentRegistration_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentRegistration");
    private final static QName _PhoneNumberList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PhoneNumberList");
    private final static QName _StaffAssignments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffAssignments");
    private final static QName _CalendarSummarys_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CalendarSummarys");
    private final static QName _StudentContactRelationship_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentContactRelationship");
    private final static QName _SIFAuthenticationLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_AuthenticationLevel");
    private final static QName _AggregateCharacteristicInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AggregateCharacteristicInfo");
    private final static QName _PublishInDirectory_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PublishInDirectory");
    private final static QName _ResourceUsage_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ResourceUsage");
    private final static QName _Identitys_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Identitys");
    private final static QName _AggregateStatisticFact_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AggregateStatisticFact");
    private final static QName _AggregateCharacteristicInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AggregateCharacteristicInfos");
    private final static QName _AddressList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AddressList");
    private final static QName _Sif3AssessmentItem_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentItem");
    private final static QName _AggregateStatisticInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AggregateStatisticInfo");
    private final static QName _LearningStandardDocument_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningStandardDocument");
    private final static QName _LearningResourcePackages_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningResourcePackages");
    private final static QName _LearningResource_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningResource");
    private final static QName _AttendanceCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AttendanceCode");
    private final static QName _SIFEncryptionLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_EncryptionLevel");
    private final static QName _ACStrandSubjectArea_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ACStrandSubjectArea");
    private final static QName _ResourceUsages_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ResourceUsages");
    private final static QName _EducationalLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EducationalLevel");
    private final static QName _EducationFilter_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EducationFilter");
    private final static QName _Sif3AssessmentScoreTables_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentScoreTables");
    private final static QName _AggregateStatisticInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AggregateStatisticInfos");
    private final static QName _ElectronicIdList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ElectronicIdList");
    private final static QName _Sif3AssessmentRubric_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentRubric");
    private final static QName _ElectronicId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ElectronicId");
    private final static QName _Address_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Address");
    private final static QName _TimeTable_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTable");
    private final static QName _AssessmentItem_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentItem");
    private final static QName _YearLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "YearLevel");
    private final static QName _Sif3AssessmentForm_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentForm");
    private final static QName _SessionInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SessionInfo");
    private final static QName _AssessmentItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentItems");
    private final static QName _RoomInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RoomInfos");
    private final static QName _Assessment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Assessment");
    private final static QName _TimeElement_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeElement");
    private final static QName _TimeTableCells_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableCells");
    private final static QName _Sif3Assessments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3Assessments");
    private final static QName _SystemRole_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SystemRole");
    private final static QName _StudentActivityParticipations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentActivityParticipations");
    private final static QName _TimeTableCell_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableCell");
    private final static QName _StudentSDTN_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSDTN");
    private final static QName _LearningStandardDocuments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningStandardDocuments");
    private final static QName _AssessmentSubTest_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentSubTest");
    private final static QName _LearningStandardItem_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningStandardItem");
    private final static QName _LearningStandardItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningStandardItems");
    private final static QName _StudentSectionEnrollments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSectionEnrollments");
    private final static QName _BirthDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "BirthDate");
    private final static QName _Sif3AssessmentAssets_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentAssets");
    private final static QName _Sif3StudentResponseSets_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3StudentResponseSets");
    private final static QName _TimeTableSubject_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableSubject");
    private final static QName _Sif3StudentResponseSet_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3StudentResponseSet");
    private final static QName _SchoolProgramss_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolProgramss");
    private final static QName _ContactInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ContactInfo");
    private final static QName _Relationship_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Relationship");
    private final static QName _Sif3AssessmentItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentItems");
    private final static QName _AssessmentSubTests_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentSubTests");
    private final static QName _CalendarDates_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CalendarDates");
    private final static QName _StudentSnapshot_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSnapshot");
    private final static QName _LocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocalId");
    private final static QName _StudentPeriodAttendances_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentPeriodAttendances");
    private final static QName _StudentActivityParticipation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentActivityParticipation");
    private final static QName _TermInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TermInfo");
    private final static QName _RoomInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RoomInfo");
    private final static QName _AssessmentPackages_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentPackages");
    private final static QName _StudentActivityInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentActivityInfo");
    private final static QName _StudentContactPersonal_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentContactPersonal");
    private final static QName _SchoolContactList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolContactList");
    private final static QName _StudentPersonal_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentPersonal");
    private final static QName _SIFContexts_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_Contexts");
    private final static QName _Sif3AssessmentForms_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentForms");
    private final static QName _YearLevels_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "YearLevels");
    private final static QName _SchoolURL_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolURL");
    private final static QName _PersonPictures_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PersonPictures");
    private final static QName _PrincipalInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PrincipalInfo");
    private final static QName _StudentContactPersonals_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentContactPersonals");
    private final static QName _AssessmentPackage_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentPackage");
    private final static QName _ReportAuthorityInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportAuthorityInfo");
    private final static QName _StudentAttendanceSummarys_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentAttendanceSummarys");
    private final static QName _StaffPersonal_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffPersonal");
    private final static QName _Sif3AssessmentAdministrations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentAdministrations");
    private final static QName _CalendarDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CalendarDate");
    private final static QName _AssessmentAdministration_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentAdministration");
    private final static QName _SectionInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionInfos");
    private final static QName _StudentParticipation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentParticipation");
    private final static QName _GridLocation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GridLocation");
    private final static QName _Sif3AssessmentSession_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentSession");
    private final static QName _SystemRoles_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SystemRoles");
    private final static QName _EnglishProficiency_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EnglishProficiency");
    private final static QName _Sif3AssessmentSection_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentSection");
    private final static QName _LEAInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LEAInfos");
    private final static QName _LearningResources_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningResources");
    private final static QName _Sif3Assessment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3Assessment");
    private final static QName _Assessments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Assessments");
    private final static QName _Sif3AssessmentSubTest_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentSubTest");
    private final static QName _StudentSchoolEnrollments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSchoolEnrollments");
    private final static QName _OnTimeGraduationYear_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OnTimeGraduationYear");
    private final static QName _TimeTables_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTables");
    private final static QName _Location_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Location");
    private final static QName _Sif3AssessmentSections_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentSections");
    private final static QName _SchoolInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolInfos");
    private final static QName _Sif3AssessmentRegistrations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sif3AssessmentRegistrations");
    private final static QName _OperationalStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OperationalStatus");
    private final static QName _StudentSDTNs_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSDTNs");
    private final static QName _StudentDailyAttendance_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentDailyAttendance");
    private final static QName _SchoolCourseInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolCourseInfo");
    private final static QName _StudentSectionEnrollment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSectionEnrollment");
    private final static QName _LEAInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LEAInfo");
    private final static QName _StudentScoreSet_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentScoreSet");
    private final static QName _Activitys_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Activitys");
    private final static QName _SIFReportObjects_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_ReportObjects");
    private final static QName _PersonInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PersonInfo");
    private final static QName _GraduationDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GraduationDate");
    private final static QName _Name_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Name");
    private final static QName _ProgramStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramStatus");
    private final static QName _SessionInfos_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SessionInfos");
    private final static QName _Identity_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Identity");
    private final static QName _TimeTableSubjectTypeAcademicYear_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AcademicYear");
    private final static QName _TimeTableSubjectTypeAcademicYearRange_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AcademicYearRange");
    private final static QName _TimeTableSubjectTypeFaculty_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Faculty");
    private final static QName _TimeTableSubjectTypeSemester_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Semester");
    private final static QName _TimeTableSubjectTypeSubjectType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubjectType");
    private final static QName _TimeTableSubjectTypeProposedMinClassSize_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProposedMinClassSize");
    private final static QName _TimeTableSubjectTypeSchoolInfoRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolInfoRefId");
    private final static QName _TimeTableSubjectTypeCourseLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CourseLocalId");
    private final static QName _TimeTableSubjectTypeProposedMaxClassSize_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProposedMaxClassSize");
    private final static QName _TimeTableSubjectTypeSchoolCourseInfoRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolCourseInfoRefId");
    private final static QName _TimeTableSubjectTypeSubjectShortName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubjectShortName");
    private final static QName _TimeTableSubjectTypeSchoolLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolLocalId");
    private final static QName _ResourceUsageTypeResourceUsageContentTypeLocalDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocalDescription");
    private final static QName _StudentParticipationTypeReferralDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReferralDate");
    private final static QName _StudentParticipationTypeProgramAvailability_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramAvailability");
    private final static QName _StudentParticipationTypePlacementParentalConsentDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PlacementParentalConsentDate");
    private final static QName _StudentParticipationTypeGiftedEligibilityCriteria_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GiftedEligibilityCriteria");
    private final static QName _StudentParticipationTypeEvaluationExtensionDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EvaluationExtensionDate");
    private final static QName _StudentParticipationTypeParticipationContact_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ParticipationContact");
    private final static QName _StudentParticipationTypeProgramPlacementDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramPlacementDate");
    private final static QName _StudentParticipationTypeReferralSource_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReferralSource");
    private final static QName _StudentParticipationTypeEntryPerson_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EntryPerson");
    private final static QName _StudentParticipationTypeEvaluationDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EvaluationDate");
    private final static QName _StudentParticipationTypeReevaluationDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReevaluationDate");
    private final static QName _StudentParticipationTypeExtendedSchoolYear_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExtendedSchoolYear");
    private final static QName _StudentParticipationTypeNOREPDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NOREPDate");
    private final static QName _StudentParticipationTypeExtendedDay_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExtendedDay");
    private final static QName _StudentParticipationTypeProgramEligibilityDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramEligibilityDate");
    private final static QName _StudentParticipationTypeEvaluationParentalConsentDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EvaluationParentalConsentDate");
    private final static QName _StudentParticipationTypeProgramFundingSources_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramFundingSources");
    private final static QName _StudentParticipationTypeProgramPlanDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramPlanDate");
    private final static QName _StudentParticipationTypeExtensionComments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExtensionComments");
    private final static QName _StudentParticipationTypeProgramType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramType");
    private final static QName _StudentParticipationTypeStudentSpecialEducationFTE_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSpecialEducationFTE");
    private final static QName _StudentParticipationTypeProgramPlanEffectiveDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ProgramPlanEffectiveDate");
    private final static QName _StudentSnapshotTypeStudentSnapshotRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSnapshotRefId");
    private final static QName _StudentSnapshotTypeSex_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Sex");
    private final static QName _StudentSnapshotTypeAge_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Age");
    private final static QName _StudentSnapshotTypeStudentSubjectChoiceList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSubjectChoiceList");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentFullYearEnrollment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FullYearEnrollment");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentSchoolName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolName");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentHomeroom_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Homeroom");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentStudentSchoolEnrollmentRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSchoolEnrollmentRefId");
    private final static QName _Sif3AssessmentItemTypeAssessmentItemBanksAssessmentItemBankAssessmentItemBankName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentItemBankName");
    private final static QName _StudentScoreSetTypeScoresScoreNumberOfResponses_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NumberOfResponses");
    private final static QName _StudentScoreSetTypeScoresScoreDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Description");
    private final static QName _StudentScoreSetTypeScoresScoreDiagnosticStatement_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DiagnosticStatement");
    private final static QName _StudentSDTNTypeStudentPersonalRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentPersonalRefId");
    private final static QName _StudentSDTNTypeAreasOfInterestList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AreasOfInterestList");
    private final static QName _StudentSDTNTypeYoungCarersRole_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "YoungCarersRole");
    private final static QName _StudentSDTNTypePastoralCare_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PastoralCare");
    private final static QName _StudentSDTNTypeReasonForLeaving_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReasonForLeaving");
    private final static QName _StudentSDTNTypeFurtherInformation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FurtherInformation");
    private final static QName _StudentSDTNTypeLiteracy_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Literacy");
    private final static QName _StudentSDTNTypeAttendanceConcerns_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AttendanceConcerns");
    private final static QName _StudentSDTNTypeHealthNeeds_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "HealthNeeds");
    private final static QName _StudentSDTNTypePreviousSchoolsList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PreviousSchoolsList");
    private final static QName _StudentSDTNTypeEnrollmentDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EnrollmentDate");
    private final static QName _StudentSDTNTypeCareerGuidanceFileHeld_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CareerGuidanceFileHeld");
    private final static QName _StudentSDTNTypeDepartureDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DepartureDate");
    private final static QName _StudentSDTNTypeAdjustedEducationProgram_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AdjustedEducationProgram");
    private final static QName _StudentSDTNTypeNumeracy_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Numeracy");
    private final static QName _StudentSDTNTypeAcceleratedProgram_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AcceleratedProgram");
    private final static QName _StudentSDTNTypeOtherLearningAreasList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OtherLearningAreasList");
    private final static QName _StudentSDTNTypeNegotiatedCurriculumPlan_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NegotiatedCurriculumPlan");
    private final static QName _StudentSDTNTypeLatestStudentReportAvailable_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LatestStudentReportAvailable");
    private final static QName _StudentSDTNTypeOtherLearningSupport_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OtherLearningSupport");
    private final static QName _StudentSDTNTypeSchoolCounsellorFileHeld_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolCounsellorFileHeld");
    private final static QName _StudentSDTNTypeFurtherInformationContactName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ContactName");
    private final static QName _AssessmentTypeAssessmentId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentId");
    private final static QName _AssessmentTypeAssessmentPackageRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentPackageRefId");
    private final static QName _AssessmentTypeAssessmentDescriptors_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentDescriptors");
    private final static QName _TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStaffLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffLocalId");
    private final static QName _TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStartTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StartTime");
    private final static QName _TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodPeriodId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PeriodId");
    private final static QName _TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodRoomNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RoomNumber");
    private final static QName _TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodCellType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CellType");
    private final static QName _TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodTimeTableCellRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableCellRefId");
    private final static QName _CalendarDateTeacherAttendance_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeacherAttendance");
    private final static QName _CalendarDateStudentAttendance_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentAttendance");
    private final static QName _CalendarDateAdministratorAttendance_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AdministratorAttendance");
    private final static QName _CalendarDateCalendarDateNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CalendarDateNumber");
    private final static QName _CalendarDateCalendarDateRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CalendarDateRefId");
    private final static QName _StudentSchoolEnrollmentTypePromotionInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PromotionInfo");
    private final static QName _StudentSchoolEnrollmentTypeCalendar_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Calendar");
    private final static QName _StudentSchoolEnrollmentTypeCounselor_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Counselor");
    private final static QName _StudentSchoolEnrollmentTypeExitDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExitDate");
    private final static QName _StudentSchoolEnrollmentTypeFTPTStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FTPTStatus");
    private final static QName _StudentSchoolEnrollmentTypeEntryType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EntryType");
    private final static QName _StudentSchoolEnrollmentTypeExitStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExitStatus");
    private final static QName _StudentSchoolEnrollmentTypeFFPOS_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FFPOS");
    private final static QName _StudentSchoolEnrollmentTypePreviousSchool_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PreviousSchool");
    private final static QName _StudentSchoolEnrollmentTypeCatchmentStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CatchmentStatus");
    private final static QName _StudentSchoolEnrollmentTypeAdvisor_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Advisor");
    private final static QName _StudentSchoolEnrollmentTypeHouse_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "House");
    private final static QName _StudentSchoolEnrollmentTypeExitType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExitType");
    private final static QName _StudentSchoolEnrollmentTypeHomegroup_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Homegroup");
    private final static QName _StudentSchoolEnrollmentTypeRecordClosureReason_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RecordClosureReason");
    private final static QName _StudentSchoolEnrollmentTypeDestinationSchool_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DestinationSchool");
    private final static QName _StudentSchoolEnrollmentTypeFTE_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FTE");
    private final static QName _StudentSchoolEnrollmentTypeIndividualLearningPlan_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "IndividualLearningPlan");
    private final static QName _StaffPersonalTypeEmploymentStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EmploymentStatus");
    private final static QName _StaffPersonalTypeOtherIdList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OtherIdList");
    private final static QName _StaffPersonalTypeTitle_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Title");
    private final static QName _AssessmentItemTypeItemScoreMinimum_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemScoreMinimum");
    private final static QName _AssessmentItemTypePerformanceLevels_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PerformanceLevels");
    private final static QName _AssessmentItemTypeItemScoreMaximum_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemScoreMaximum");
    private final static QName _AssessmentItemTypeStem_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Stem");
    private final static QName _AssessmentItemTypeResponseChoices_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ResponseChoices");
    private final static QName _AssessmentItemTypeStimulus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Stimulus");
    private final static QName _AssessmentItemTypeItemName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemName");
    private final static QName _LearningStandardDocumentTypeEndOfLifeDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EndOfLifeDate");
    private final static QName _LearningStandardDocumentTypeLocalArchiveDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocalArchiveDate");
    private final static QName _LearningStandardDocumentTypeAuthors_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Authors");
    private final static QName _LearningStandardDocumentTypeDocumentDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DocumentDate");
    private final static QName _LearningStandardDocumentTypeLocalAdoptionDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocalAdoptionDate");
    private final static QName _LearningStandardDocumentTypeRichDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RichDescription");
    private final static QName _LearningStandardDocumentTypeCopyright_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Copyright");
    private final static QName _LearningStandardDocumentTypeRelatedLearningStandards_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RelatedLearningStandards");
    private final static QName _LearningStandardDocumentTypeRepositoryDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RepositoryDate");
    private final static QName _LearningStandardDocumentTypeOrganizationContactPoint_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OrganizationContactPoint");
    private final static QName _SIFQueryDataModelTypeSIFConditionGroup_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_ConditionGroup");
    private final static QName _SIFQueryDataModelTypeSIFExample_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_Example");
    private final static QName _StudentActivityParticipationTypeRole_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Role");
    private final static QName _StudentActivityParticipationTypeRecognitionList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RecognitionList");
    private final static QName _StudentActivityParticipationTypeParticipationComment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ParticipationComment");
    private final static QName _StudentActivityParticipationTypeEndDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EndDate");
    private final static QName _StudentActivityParticipationTypeStartDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StartDate");
    private final static QName _StudentDailyAttendanceTypeTimeIn_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeIn");
    private final static QName _StudentDailyAttendanceTypeTimeOut_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeOut");
    private final static QName _StudentDailyAttendanceTypeAbsenceValue_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AbsenceValue");
    private final static QName _StudentDailyAttendanceTypeDayValue_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DayValue");
    private final static QName _StudentDailyAttendanceTypeAttendanceNote_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AttendanceNote");
    private final static QName _AggregateStatisticInfoTypeEffectiveDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EffectiveDate");
    private final static QName _AggregateStatisticInfoTypeMeasure_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Measure");
    private final static QName _AggregateStatisticInfoTypeDiscontinueDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DiscontinueDate");
    private final static QName _AggregateStatisticInfoTypeCalculationRule_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CalculationRule");
    private final static QName _AggregateStatisticInfoTypeApprovalDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ApprovalDate");
    private final static QName _AggregateStatisticInfoTypeExpirationDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExpirationDate");
    private final static QName _AggregateStatisticInfoTypeSource_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Source");
    private final static QName _AggregateStatisticInfoTypeExclusionRules_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ExclusionRules");
    private final static QName _AssessmentRegistrationTypeLEAInfoRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LEAInfoRefId");
    private final static QName _AssessmentRegistrationTypeStudentYearLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentYearLevel");
    private final static QName _AssessmentRegistrationTypeStudentSpecialConditions_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSpecialConditions");
    private final static QName _AssessmentRegistrationTypeStaffPersonalRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffPersonalRefId");
    private final static QName _AssessmentRegistrationTypeAssessmentStudentSnapshot_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentStudentSnapshot");
    private final static QName _AssessmentRegistrationTypeAssessmentYearLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentYearLevel");
    private final static QName _SchoolProgramsTypeSchoolProgramListProgramCategory_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Category");
    private final static QName _StudentSDTNTypePastoralCareIndividualBehaviourPlan_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "IndividualBehaviourPlan");
    private final static QName _StudentSDTNTypePastoralCareDisciplinaryAbsences_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DisciplinaryAbsences");
    private final static QName _SIFErrorDataModelTypeSIFExtendedDesc_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_ExtendedDesc");
    private final static QName _AddressTypeAddressGlobalUID_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AddressGlobalUID");
    private final static QName _AddressTypeRadioContact_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RadioContact");
    private final static QName _AddressTypeMapReference_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MapReference");
    private final static QName _AddressTypeCommunity_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Community");
    private final static QName _AddressTypeStatisticalAreas_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StatisticalAreas");
    private final static QName _TermInfoTypeAttendanceTerm_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AttendanceTerm");
    private final static QName _TermInfoTypeTermSpan_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TermSpan");
    private final static QName _TermInfoTypeTrack_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Track");
    private final static QName _TermInfoTypeMarkingTerm_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MarkingTerm");
    private final static QName _TermInfoTypeTermCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TermCode");
    private final static QName _TermInfoTypeSchedulingTerm_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchedulingTerm");
    private final static QName _TermInfoTypeRelativeDuration_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RelativeDuration");
    private final static QName _LearningResourceTypeComponentsComponentStrategies_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Strategies");
    private final static QName _LearningResourceTypeComponentsComponentAssociatedObjects_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssociatedObjects");
    private final static QName _LearningStandardItemTypeStandardSettingBodySettingBodyName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SettingBodyName");
    private final static QName _Sif3AssessmentItemTypeResponseChoicesChoiceResponseFeedback_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ResponseFeedback");
    private final static QName _Sif3AssessmentItemTypeResponseChoicesChoiceCreditValue_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CreditValue");
    private final static QName _Sif3AssessmentItemTypeResponseChoicesChoiceChoiceLabel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ChoiceLabel");
    private final static QName _AssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresUpperCut_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "UpperCut");
    private final static QName _AssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresLowerCut_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LowerCut");
    private final static QName _Sif3AssessmentSessionTypeScheduledStartDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScheduledStartDateTime");
    private final static QName _Sif3AssessmentSessionTypeStaffPersonalRefIds_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffPersonalRefIds");
    private final static QName _Sif3AssessmentSessionTypeScheduledEndDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScheduledEndDateTime");
    private final static QName _Sif3AssessmentSessionTypeActualEndDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ActualEndDateTime");
    private final static QName _Sif3AssessmentSessionTypeActualStartDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ActualStartDateTime");
    private final static QName _Sif3AssessmentSessionTypeAssessmentAdministrationRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentAdministrationRefId");
    private final static QName _Sif3AssessmentSessionTypeAssessmentFormRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentFormRefId");
    private final static QName _Sif3AssessmentSessionTypeUnusualEvents_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "UnusualEvents");
    private final static QName _Sif3StudentResponseSetTypeItemsItemTimeOnItem_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeOnItem");
    private final static QName _Sif3StudentResponseSetTypeItemsItemTraitScores_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TraitScores");
    private final static QName _Sif3StudentResponseSetTypeItemsItemAttemptStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AttemptStatus");
    private final static QName _Sif3StudentResponseSetTypeItemsItemItemScore_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemScore");
    private final static QName _Sif3StudentResponseSetTypeItemsItemResponseLocation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ResponseLocation");
    private final static QName _Sif3StudentResponseSetTypeItemsItemComments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Comments");
    private final static QName _Sif3StudentResponseSetTypeItemsItemAssessmentRubricRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentRubricRefId");
    private final static QName _Sif3StudentResponseSetTypeItemsItemFeedbackItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FeedbackItems");
    private final static QName _Sif3StudentResponseSetTypeItemsItemViewStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ViewStatus");
    private final static QName _Sif3StudentResponseSetTypeItemsItemItemNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemNumber");
    private final static QName _Sif3StudentResponseSetTypeItemsItemResponseCorrectness_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ResponseCorrectness");
    private final static QName _Sif3StudentResponseSetTypeItemsItemNumberOfAttempts_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NumberOfAttempts");
    private final static QName _Sif3StudentResponseSetTypeItemsItemItemAids_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemAids");
    private final static QName _Sif3StudentResponseSetTypeItemsItemItemScoreCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemScoreCode");
    private final static QName _SystemRoleTypeSystemContextListSystemContextRoleList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RoleList");
    private final static QName _AssessmentAdministrationTypeSpecialConditions_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SpecialConditions");
    private final static QName _AssessmentAdministrationTypeFinishDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FinishDateTime");
    private final static QName _AssessmentAdministrationTypeStartDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StartDateTime");
    private final static QName _AssessmentAdministrationTypeAdministrationName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AdministrationName");
    private final static QName _AssessmentAdministrationTypeDueDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DueDateTime");
    private final static QName _AssessmentItemTypePerformanceLevelsPerformanceLevelCutScores_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CutScores");
    private final static QName _TeachingGroupTypeLongName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LongName");
    private final static QName _TeachingGroupTypeTeachingGroupPeriodList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeachingGroupPeriodList");
    private final static QName _TeachingGroupTypeCurriculumLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CurriculumLevel");
    private final static QName _TeachingGroupTypeSchoolCourseLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolCourseLocalId");
    private final static QName _TeachingGroupTypeMaxClassSize_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MaxClassSize");
    private final static QName _TeachingGroupTypeTeacherList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeacherList");
    private final static QName _TeachingGroupTypeSet_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Set");
    private final static QName _TeachingGroupTypeTimeTableSubjectRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableSubjectRefId");
    private final static QName _TeachingGroupTypeBlock_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Block");
    private final static QName _TeachingGroupTypeTimeTableSubjectLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableSubjectLocalId");
    private final static QName _TeachingGroupTypeMinClassSize_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MinClassSize");
    private final static QName _TeachingGroupTypeStudentList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentList");
    private final static QName _LifeCycleTypeCreatedCreators_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Creators");
    private final static QName _AddressTypeStreetStreetType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StreetType");
    private final static QName _AddressTypeStreetStreetSuffix_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StreetSuffix");
    private final static QName _AddressTypeStreetApartmentNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ApartmentNumber");
    private final static QName _AddressTypeStreetStreetNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StreetNumber");
    private final static QName _AddressTypeStreetComplex_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Complex");
    private final static QName _AddressTypeStreetStreetPrefix_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StreetPrefix");
    private final static QName _AddressTypeStreetLine3_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Line3");
    private final static QName _AddressTypeStreetLine2_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Line2");
    private final static QName _AddressTypeStreetApartmentNumberSuffix_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ApartmentNumberSuffix");
    private final static QName _AddressTypeStreetApartmentType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ApartmentType");
    private final static QName _AddressTypeStreetStreetName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StreetName");
    private final static QName _AddressTypeStreetApartmentNumberPrefix_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ApartmentNumberPrefix");
    private final static QName _SystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScopeRoleScopeRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RoleScopeRefId");
    private final static QName _SystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScopeRoleScopeName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RoleScopeName");
    private final static QName _Sif3AssessmentRegistrationTypeTestingStatusesTestingStatusTestingStatusDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TestingStatusDescription");
    private final static QName _StudentPersonalTypeAlertMessages_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AlertMessages");
    private final static QName _StudentPersonalTypeDisability_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Disability");
    private final static QName _StudentPersonalTypePrePrimaryEducation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PrePrimaryEducation");
    private final static QName _StudentPersonalTypeEconomicDisadvantage_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EconomicDisadvantage");
    private final static QName _StudentPersonalTypeMedicalAlertMessages_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MedicalAlertMessages");
    private final static QName _StudentPersonalTypeESL_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ESL");
    private final static QName _StudentPersonalTypeAcceptableUsePolicy_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AcceptableUsePolicy");
    private final static QName _StudentPersonalTypeFirstAUSchoolEnrollment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FirstAUSchoolEnrollment");
    private final static QName _StudentPersonalTypeIntegrationAide_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "IntegrationAide");
    private final static QName _StudentPersonalTypeMostRecent_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MostRecent");
    private final static QName _StudentPersonalTypeGiftedTalented_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GiftedTalented");
    private final static QName _LearningResourceTypeLearningResourcePackageRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningResourcePackageRefId");
    private final static QName _LearningResourceTypeAuthor_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Author");
    private final static QName _LearningResourceTypeSubjectAreas_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubjectAreas");
    private final static QName _LearningResourceTypeApprovals_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Approvals");
    private final static QName _LearningResourceTypeUseAgreement_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "UseAgreement");
    private final static QName _LearningResourceTypeContacts_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Contacts");
    private final static QName _LearningResourceTypeMediaTypes_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MediaTypes");
    private final static QName _LearningResourceTypeAgreementDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AgreementDate");
    private final static QName _LearningResourceTypeStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Status");
    private final static QName _LearningResourceTypeEvaluations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Evaluations");
    private final static QName _LearningResourceTypeLearningStandards_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningStandards");
    private final static QName _ReportManifestTypeReportFormatList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportFormatList");
    private final static QName _ReportManifestTypeReceivingAuthority_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReceivingAuthority");
    private final static QName _ReportManifestTypeReportingPeriod_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportingPeriod");
    private final static QName _ReportManifestTypeSIFExtendedQuery_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_ExtendedQuery");
    private final static QName _ReportManifestTypeSIFQueryGroup_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_QueryGroup");
    private final static QName _Sif3StudentResponseSetTypeItemsItemCommentsCommentCommentDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CommentDescription");
    private final static QName _SessionInfoTypeRollMarked_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RollMarked");
    private final static QName _SessionInfoTypeFinishTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FinishTime");
    private final static QName _SessionInfoTypeStaffPersonalLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffPersonalLocalId");
    private final static QName _StudentScoreSetTypeAssessmentRegistrationRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentRegistrationRefId");
    private final static QName _StudentSDTNTypeOtherLearningAreasListOtherLearningAreaResult_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Result");
    private final static QName _SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStudentStudentLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentLocalId");
    private final static QName _RoomInfoTypeStaffList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffList");
    private final static QName _RoomInfoTypeCapacity_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Capacity");
    private final static QName _RoomInfoTypeSize_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Size");
    private final static QName _RoomInfoTypeBuilding_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Building");
    private final static QName _AddressTypeStatisticalAreasStatisticalArea_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StatisticalArea");
    private final static QName _PhoneNumberTypeListedStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ListedStatus");
    private final static QName _PhoneNumberTypeExtension_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Extension");
    private final static QName _ResourceUsageTypeResourceReportColumnListResourceReportColumnColumnDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ColumnDescription");
    private final static QName _ResourceUsageTypeResourceReportColumnListResourceReportColumnColumnDelimiter_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ColumnDelimiter");
    private final static QName _Sif3AssessmentTypeAssessmentSubjects_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentSubjects");
    private final static QName _Sif3AssessmentTypeAssessmentProvider_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentProvider");
    private final static QName _Sif3AssessmentTypeAssessmentIdentifiers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentIdentifiers");
    private final static QName _Sif3AssessmentTypeAssessmentItemBanks_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentItemBanks");
    private final static QName _Sif3AssessmentTypeLearningStandardItemRefIds_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningStandardItemRefIds");
    private final static QName _Sif3AssessmentTypeAssessmentLanguages_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentLanguages");
    private final static QName _Sif3AssessmentTypeAssessmentGradeLevels_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentGradeLevels");
    private final static QName _TimeElementTypeEndDateTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EndDateTime");
    private final static QName _TimeElementTypeSpanGaps_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SpanGaps");
    private final static QName _LifeCycleTypeCreated_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Created");
    private final static QName _LifeCycleTypeTimeElements_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeElements");
    private final static QName _LifeCycleTypeModificationHistory_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ModificationHistory");
    private final static QName _StudentSnapshotTypeHomeEnrollmentSchoolNo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolNo");
    private final static QName _StudentContactRelationshipTypeMainlySpeaksEnglishAtHome_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MainlySpeaksEnglishAtHome");
    private final static QName _StudentContactRelationshipTypeContactSequenceSource_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ContactSequenceSource");
    private final static QName _StudentContactRelationshipTypeStudentContactRelationshipRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentContactRelationshipRefId");
    private final static QName _StudentContactRelationshipTypeContactFlags_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ContactFlags");
    private final static QName _StudentContactRelationshipTypeHouseholdList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "HouseholdList");
    private final static QName _StudentContactRelationshipTypeContactSequence_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ContactSequence");
    private final static QName _StudentContactRelationshipTypeParentRelationshipStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ParentRelationshipStatus");
    private final static QName _LEAInfoTypeSLA_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SLA");
    private final static QName _LEAInfoTypeEducationAgencyType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EducationAgencyType");
    private final static QName _LEAInfoTypeCommonwealthId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CommonwealthId");
    private final static QName _LEAInfoTypeLEAContactList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LEAContactList");
    private final static QName _LEAInfoTypeLEAURL_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LEAURL");
    private final static QName _LEAInfoTypeJurisdictionLowerHouse_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "JurisdictionLowerHouse");
    private final static QName _BaseNameTypePreferredFamilyNameFirst_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PreferredFamilyNameFirst");
    private final static QName _BaseNameTypePreferredFamilyName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PreferredFamilyName");
    private final static QName _BaseNameTypeFamilyName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FamilyName");
    private final static QName _BaseNameTypePreferredGivenName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PreferredGivenName");
    private final static QName _BaseNameTypeFamilyNameFirst_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FamilyNameFirst");
    private final static QName _BaseNameTypeMiddleName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MiddleName");
    private final static QName _BaseNameTypeFullName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FullName");
    private final static QName _BaseNameTypeSuffix_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Suffix");
    private final static QName _BaseNameTypeGivenName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GivenName");
    private final static QName _SIFReportObjectTypeReportInfoReportSubmitterInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportSubmitterInfo");
    private final static QName _ContactInfoTypePositionTitle_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PositionTitle");
    private final static QName _ActivityTypeTechnicalRequirementsTechnicalRequirement_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TechnicalRequirement");
    private final static QName _DemographicsTypeVisaStatisticalCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "VisaStatisticalCode");
    private final static QName _DemographicsTypePlaceOfBirth_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PlaceOfBirth");
    private final static QName _DemographicsTypeVisaExpiryDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "VisaExpiryDate");
    private final static QName _DemographicsTypeCulturalBackground_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CulturalBackground");
    private final static QName _DemographicsTypeAustralianCitizenshipStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AustralianCitizenshipStatus");
    private final static QName _DemographicsTypeImmunisationCertificateStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ImmunisationCertificateStatus");
    private final static QName _DemographicsTypeBirthDateVerification_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "BirthDateVerification");
    private final static QName _DemographicsTypePermanentResident_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PermanentResident");
    private final static QName _DemographicsTypeReligiousEventList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReligiousEventList");
    private final static QName _DemographicsTypeReligion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Religion");
    private final static QName _DemographicsTypeCountryOfBirth_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CountryOfBirth");
    private final static QName _DemographicsTypeIndigenousStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "IndigenousStatus");
    private final static QName _DemographicsTypeCountryArrivalDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CountryArrivalDate");
    private final static QName _DemographicsTypeMaritalStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MaritalStatus");
    private final static QName _DemographicsTypeCountriesOfResidency_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CountriesOfResidency");
    private final static QName _DemographicsTypeDwellingArrangement_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DwellingArrangement");
    private final static QName _DemographicsTypeReligiousRegion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReligiousRegion");
    private final static QName _DemographicsTypeCountriesOfCitizenship_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CountriesOfCitizenship");
    private final static QName _DemographicsTypeVisaSubClass_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "VisaSubClass");
    private final static QName _DemographicsTypeStateOfBirth_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StateOfBirth");
    private final static QName _ReportManifestTypeReportingPeriodEndSubmitDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EndSubmitDate");
    private final static QName _ReportManifestTypeReportingPeriodEndReportDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EndReportDate");
    private final static QName _ReportManifestTypeReportingPeriodDueDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DueDate");
    private final static QName _ReportManifestTypeReportingPeriodBeginSubmitDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "BeginSubmitDate");
    private final static QName _ReportManifestTypeReportingPeriodBeginReportDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "BeginReportDate");
    private final static QName _TimeTableTypeTimeTableCreationDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableCreationDate");
    private final static QName _TimeTableTypeTeachingPeriodsPerDay_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeachingPeriodsPerDay");
    private final static QName _Sif3AssessmentSubTestTypeSubTestIdentifiers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubTestIdentifiers");
    private final static QName _Sif3AssessmentSubTestTypeSubTestPublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubTestPublishDate");
    private final static QName _Sif3AssessmentSubTestTypeSubTestSubjectAreas_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubTestSubjectAreas");
    private final static QName _Sif3AssessmentSubTestTypeSubTestTier_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubTestTier");
    private final static QName _Sif3AssessmentSubTestTypeContainerOnly_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ContainerOnly");
    private final static QName _Sif3AssessmentSubTestTypeAssessmentSubTestRefIds_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentSubTestRefIds");
    private final static QName _Sif3AssessmentSubTestTypeScoreReporting_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreReporting");
    private final static QName _Sif3AssessmentSubTestTypeAbbreviation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Abbreviation");
    private final static QName _Sif3AssessmentSubTestTypeSubTestGradeLevels_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubTestGradeLevels");
    private final static QName _Sif3AssessmentSubTestTypeNumberOfItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NumberOfItems");
    private final static QName _Sif3AssessmentSubTestTypeSubTestVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubTestVersion");
    private final static QName _StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoicePreferenceNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PreferenceNumber");
    private final static QName _StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceOtherSchoolLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OtherSchoolLocalId");
    private final static QName _StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceStudyDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudyDescription");
    private final static QName _StudentPersonalTypeMostRecentParent2Language_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent2Language");
    private final static QName _StudentPersonalTypeMostRecentParent1Language_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent1Language");
    private final static QName _StudentPersonalTypeMostRecentParent2EmploymentType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent2EmploymentType");
    private final static QName _StudentPersonalTypeMostRecentParent1NonSchoolEducation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent1NonSchoolEducation");
    private final static QName _StudentPersonalTypeMostRecentParent1SchoolEducationLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent1SchoolEducationLevel");
    private final static QName _StudentPersonalTypeMostRecentParent1EmploymentType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent1EmploymentType");
    private final static QName _StudentPersonalTypeMostRecentHomeroomLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "HomeroomLocalId");
    private final static QName _StudentPersonalTypeMostRecentParent2SchoolEducationLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent2SchoolEducationLevel");
    private final static QName _StudentPersonalTypeMostRecentParent2NonSchoolEducation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Parent2NonSchoolEducation");
    private final static QName _SIFExtendedQueryDataModelTypeSIFWhere_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_Where");
    private final static QName _SIFExtendedQueryDataModelTypeSIFOrderBy_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_OrderBy");
    private final static QName _SIFExtendedQueryDataModelTypeSIFDestinationProvider_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_DestinationProvider");
    private final static QName _SchoolProgramsTypeSchoolProgramList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolProgramList");
    private final static QName _AggregateCharacteristicInfoTypeElementName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ElementName");
    private final static QName _ReportAuthorityInfoTypeAuthorityDepartment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AuthorityDepartment");
    private final static QName _Sif3AssessmentSubTestTypeScoreReportingMinimum_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Minimum");
    private final static QName _Sif3AssessmentSubTestTypeScoreReportingScoreTableRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreTableRefId");
    private final static QName _Sif3AssessmentSubTestTypeScoreReportingMaximum_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Maximum");
    private final static QName _StudentSectionEnrollmentTypeEntryDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EntryDate");
    private final static QName _Sif3AssessmentRegistrationTypeDaysOfInstruction_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DaysOfInstruction");
    private final static QName _Sif3AssessmentRegistrationTypeTestingStatuses_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TestingStatuses");
    private final static QName _Sif3AssessmentRegistrationTypeScorePublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScorePublishDate");
    private final static QName _Sif3AssessmentRegistrationTypeRetestIndicator_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RetestIndicator");
    private final static QName _Sif3AssessmentRegistrationTypeTestAttemptIdentifier_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TestAttemptIdentifier");
    private final static QName _Sif3AssessmentRegistrationTypeStudentSpecialEvents_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentSpecialEvents");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentPlatform_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentPlatform");
    private final static QName _Sif3AssessmentRegistrationTypeStudentGradeLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentGradeLevel");
    private final static QName _Sif3AssessmentRegistrationTypeSectionInfoRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionInfoRefId");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentSessionRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentSessionRefId");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentGradeLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentGradeLevel");
    private final static QName _Sif3AssessmentRubricTypeScoresScoreScoreCodeDefinition_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreCodeDefinition");
    private final static QName _Sif3AssessmentRubricTypeScoresScoreScoreCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreCode");
    private final static QName _Sif3AssessmentRubricTypeScoresScoreScoreComments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreComments");
    private final static QName _Sif3AssessmentRubricTypeScoresScoreScoreDescriptions_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreDescriptions");
    private final static QName _AssessmentSubTestTypeScoreRange_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreRange");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotNeglectedDelinquent_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NeglectedDelinquent");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotGraduationOnTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GraduationOnTime");
    private final static QName _Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotGraduationAward_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GraduationAward");
    private final static QName _StudentSDTNTypeAreasOfInterestListActivityInfoStudentActivityInfoRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentActivityInfoRefId");
    private final static QName _SectionInfoTypeSchoolCourseInfoOverride_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolCourseInfoOverride");
    private final static QName _SectionInfoTypeMediumOfInstruction_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MediumOfInstruction");
    private final static QName _SectionInfoTypeTermInfoRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TermInfoRefId");
    private final static QName _SectionInfoTypeLocationOfInstruction_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocationOfInstruction");
    private final static QName _SectionInfoTypeSectionCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionCode");
    private final static QName _SectionInfoTypeLanguageOfInstruction_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LanguageOfInstruction");
    private final static QName _SectionInfoTypeCourseSectionCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CourseSectionCode");
    private final static QName _SectionInfoTypeSummerSchool_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SummerSchool");
    private final static QName _SectionInfoTypeCountForAttendance_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CountForAttendance");
    private final static QName _StudentSchoolEnrollmentTypePromotionInfoPromotionStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PromotionStatus");
    private final static QName _StudentAttendanceSummaryTypeDaysTardy_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DaysTardy");
    private final static QName _StudentAttendanceSummaryTypeStudentAttendanceSummaryRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentAttendanceSummaryRefId");
    private final static QName _StudentAttendanceSummaryTypeStartDay_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StartDay");
    private final static QName _StudentAttendanceSummaryTypeEndDay_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EndDay");
    private final static QName _AssessmentFormTypeLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Level");
    private final static QName _AssessmentFormTypePeriod_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Period");
    private final static QName _AssessmentFormTypeFormNumbers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FormNumbers");
    private final static QName _AssessmentFormTypeAssessmentType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentType");
    private final static QName _StudentContactRelationshipTypeContactFlagsHasCustody_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "HasCustody");
    private final static QName _StudentContactRelationshipTypeContactFlagsFeesBilling_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FeesBilling");
    private final static QName _StudentContactRelationshipTypeContactFlagsReceivesAssessmentReport_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReceivesAssessmentReport");
    private final static QName _StudentContactRelationshipTypeContactFlagsEmergencyContact_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EmergencyContact");
    private final static QName _StudentContactRelationshipTypeContactFlagsPickupRights_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PickupRights");
    private final static QName _StudentContactRelationshipTypeContactFlagsAttendanceContact_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AttendanceContact");
    private final static QName _StudentContactRelationshipTypeContactFlagsPrimaryCareProvider_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PrimaryCareProvider");
    private final static QName _StudentContactRelationshipTypeContactFlagsAccessToRecords_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AccessToRecords");
    private final static QName _StudentContactRelationshipTypeContactFlagsFamilyMail_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FamilyMail");
    private final static QName _StudentContactRelationshipTypeContactFlagsInterventionOrder_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "InterventionOrder");
    private final static QName _StudentContactRelationshipTypeContactFlagsParentLegalGuardian_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ParentLegalGuardian");
    private final static QName _StudentContactRelationshipTypeContactFlagsDisciplinaryContact_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DisciplinaryContact");
    private final static QName _StudentContactRelationshipTypeContactFlagsLivesWith_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LivesWith");
    private final static QName _SIFReportObjectTypeReportInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportInfo");
    private final static QName _SIFReportObjectTypeSIFExtendedQueryResults_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_ExtendedQueryResults");
    private final static QName _SIFReportObjectTypeReportData_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReportData");
    private final static QName _Sif3AssessmentItemTypeAssessmentItemPlatforms_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentItemPlatforms");
    private final static QName _Sif3AssessmentItemTypeItemRubricIds_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemRubricIds");
    private final static QName _Sif3AssessmentItemTypeItemFeedbackCorrect_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemFeedbackCorrect");
    private final static QName _Sif3AssessmentItemTypeItemFeedbackIncorrect_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemFeedbackIncorrect");
    private final static QName _Sif3AssessmentItemTypeAssessmentItemAssetRefIds_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentItemAssetRefIds");
    private final static QName _Sif3AssessmentItemTypeItemFeedbackHint_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemFeedbackHint");
    private final static QName _Sif3AssessmentItemTypeItemPublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemPublishDate");
    private final static QName _Sif3AssessmentItemTypeItemVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemVersion");
    private final static QName _Sif3AssessmentSectionTypeSectionItemsSectionItemItemSequence_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemSequence");
    private final static QName _LearningResourceTypeEvaluationsEvaluationDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Date");
    private final static QName _SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStudent_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Student");
    private final static QName _SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStaff_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Staff");
    private final static QName _SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EnrollmentList");
    private final static QName _SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentSchoolStateProvinceId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolStateProvinceId");
    private final static QName _StudentContactPersonalTypeNonSchoolEducation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NonSchoolEducation");
    private final static QName _StudentContactPersonalTypeEmploymentType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EmploymentType");
    private final static QName _StudentContactPersonalTypeSchoolEducationalLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolEducationalLevel");
    private final static QName _Sif3AssessmentAdministrationTypeAdministrationAssessments_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AdministrationAssessments");
    private final static QName _Sif3AssessmentAdministrationTypeAdministrationCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AdministrationCode");
    private final static QName _Sif3AssessmentAdministrationTypeOrganizations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Organizations");
    private final static QName _StudentPeriodAttendanceTypeSessionInfoRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SessionInfoRefId");
    private final static QName _StudentPeriodAttendanceTypeTimetablePeriod_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimetablePeriod");
    private final static QName _StudentPeriodAttendanceTypeAuditInfo_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AuditInfo");
    private final static QName _StudentPeriodAttendanceTypeAttendanceComment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AttendanceComment");
    private final static QName _StaffAssignmentTypeStaffSubjectListStaffSubjectSubjectLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubjectLocalId");
    private final static QName _Sif3AssessmentScoreTableTypeScoreTableIdentifiers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreTableIdentifiers");
    private final static QName _Sif3AssessmentScoreTableTypeScoreTablePublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreTablePublishDate");
    private final static QName _Sif3AssessmentScoreTableTypeScoreTableVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreTableVersion");
    private final static QName _Sif3AssessmentScoreTableTypeScoreValues_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoreValues");
    private final static QName _SIFReportObjectTypeReportInfoReportSubmitterInfoSIFRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_RefId");
    private final static QName _SIFReportObjectTypeReportInfoReportSubmitterInfoSubmitterDepartment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubmitterDepartment");
    private final static QName _SIFReportObjectTypeReportInfoReportSubmitterInfoSubmitterNotes_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SubmitterNotes");
    private final static QName _ActivityTypeLearningObjectives_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LearningObjectives");
    private final static QName _ActivityTypeStudents_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Students");
    private final static QName _ActivityTypePoints_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Points");
    private final static QName _ActivityTypeEssentialMaterials_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EssentialMaterials");
    private final static QName _ActivityTypeAssessmentRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentRefId");
    private final static QName _ActivityTypeActivityWeight_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ActivityWeight");
    private final static QName _ActivityTypeTechnicalRequirements_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TechnicalRequirements");
    private final static QName _ActivityTypePrerequisites_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Prerequisites");
    private final static QName _ActivityTypeSoftwareRequirementList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SoftwareRequirementList");
    private final static QName _ActivityTypeMaxAttemptsAllowed_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MaxAttemptsAllowed");
    private final static QName _ActivityTypeEvaluation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Evaluation");
    private final static QName _ActivityTypePreamble_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Preamble");
    private final static QName _ActivityTypeSourceObjects_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SourceObjects");
    private final static QName _Sif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackDescription_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FeedbackDescription");
    private final static QName _Sif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackSource_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FeedbackSource");
    private final static QName _Sif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FeedbackCode");
    private final static QName _Sif3AssessmentAssetTypeAssetIdentifiers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetIdentifiers");
    private final static QName _Sif3AssessmentAssetTypeAssetLanguage_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetLanguage");
    private final static QName _Sif3AssessmentAssetTypeAssetName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetName");
    private final static QName _Sif3AssessmentAssetTypeAssetVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetVersion");
    private final static QName _Sif3AssessmentAssetTypeAssetLearningStandards_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetLearningStandards");
    private final static QName _Sif3AssessmentAssetTypeAssetOwner_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetOwner");
    private final static QName _Sif3AssessmentAssetTypeAssetSubjects_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetSubjects");
    private final static QName _Sif3AssessmentAssetTypeAssetGradeLevels_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetGradeLevels");
    private final static QName _Sif3AssessmentAssetTypeAssetPublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssetPublishDate");
    private final static QName _Sif3AssessmentRubricTypeScores_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Scores");
    private final static QName _Sif3AssessmentRubricTypeScoringGuideReference_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ScoringGuideReference");
    private final static QName _Sif3AssessmentRubricTypeRubricVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RubricVersion");
    private final static QName _Sif3AssessmentRubricTypeRubricPublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RubricPublishDate");
    private final static QName _Sif3AssessmentRubricTypeRubricIdentifiers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RubricIdentifiers");
    private final static QName _PrincipalInfoTypeContactTitle_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ContactTitle");
    private final static QName _SIFHeaderDataModelTypeSIFSecurity_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_Security");
    private final static QName _SIFHeaderDataModelTypeSIFDestinationId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SIF_DestinationId");
    private final static QName _LearningStandardDocumentTypeCopyrightHolder_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Holder");
    private final static QName _LearningStandardItemTypeStandardIdentifierAlternateIdentificationCodes_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AlternateIdentificationCodes");
    private final static QName _LearningStandardItemTypeStandardIdentifierIndicatorNumber_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "IndicatorNumber");
    private final static QName _LearningStandardItemTypeStandardIdentifierBenchmark_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Benchmark");
    private final static QName _IdentityTypeIdentityAssertions_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "IdentityAssertions");
    private final static QName _IdentityTypePasswordList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PasswordList");
    private final static QName _IdentityTypeAuthenticationSourceGlobalUID_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AuthenticationSourceGlobalUID");
    private final static QName _CalendarSummaryTypeFirstInstructionDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FirstInstructionDate");
    private final static QName _CalendarSummaryTypeLastInstructionDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LastInstructionDate");
    private final static QName _CalendarSummaryTypeInstructionalMinutes_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "InstructionalMinutes");
    private final static QName _CalendarSummaryTypeMinutesPerDay_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "MinutesPerDay");
    private final static QName _LanguageListTypeLanguageDialect_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Dialect");
    private final static QName _LanguageListTypeLanguageLanguageType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LanguageType");
    private final static QName _TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodEndTime_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "EndTime");
    private final static QName _TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodBellPeriod_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "BellPeriod");
    private final static QName _TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodUseInAttendanceCalculations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "UseInAttendanceCalculations");
    private final static QName _TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodRegularSchoolPeriod_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RegularSchoolPeriod");
    private final static QName _SystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RoleScopeList");
    private final static QName _LearningStandardItemTypeRelatedLearningStandardItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "RelatedLearningStandardItems");
    private final static QName _LearningStandardItemTypeStandardIdentifier_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StandardIdentifier");
    private final static QName _LearningStandardItemTypeStatementCodes_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StatementCodes");
    private final static QName _LearningStandardItemTypeStandardSettingBody_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StandardSettingBody");
    private final static QName _LearningStandardItemTypeResources_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Resources");
    private final static QName _LearningStandardItemTypeLevel5_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Level5");
    private final static QName _LearningStandardItemTypeLevel4_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Level4");
    private final static QName _LearningStandardItemTypeStatements_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Statements");
    private final static QName _LearningStandardItemTypePredecessorItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PredecessorItems");
    private final static QName _StudentSDTNTypePreviousSchoolsListPreviousSchoolReasonLeft_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReasonLeft");
    private final static QName _Sif3AssessmentScoreTableTypeScoreValuesScoreValueFeedbackList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FeedbackList");
    private final static QName _Sif3AssessmentScoreTableTypeScoreValuesScoreValuePassFailIndicator_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "PassFailIndicator");
    private final static QName _Sif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TraitScoreCode");
    private final static QName _Sif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreValue_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TraitScoreValue");
    private final static QName _Sif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TraitScoreType");
    private final static QName _StudentActivityInfoTypeCurricularStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CurricularStatus");
    private final static QName _StudentActivityInfoTypeStudentActivityLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StudentActivityLevel");
    private final static QName _AggregateStatisticFactTypeExcluded_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Excluded");
    private final static QName _ActivityTypeActivityTimeFinishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FinishDate");
    private final static QName _ActivityTypeActivityTimeDuration_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Duration");
    private final static QName _StaffAssignmentTypeStaffActivity_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffActivity");
    private final static QName _StaffAssignmentTypeJobStartDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "JobStartDate");
    private final static QName _StaffAssignmentTypeJobEndDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "JobEndDate");
    private final static QName _StaffAssignmentTypeCasualReliefTeacher_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CasualReliefTeacher");
    private final static QName _StaffAssignmentTypeJobFTE_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "JobFTE");
    private final static QName _StaffAssignmentTypeJobFunction_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "JobFunction");
    private final static QName _StaffAssignmentTypeStaffSubjectList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StaffSubjectList");
    private final static QName _TimeTableCellTypeTimeTableLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TimeTableLocalId");
    private final static QName _TimeTableCellTypeTeachingGroupLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeachingGroupLocalId");
    private final static QName _SectionInfoTypeSchoolCourseInfoOverrideCourseCredits_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CourseCredits");
    private final static QName _SectionInfoTypeSchoolCourseInfoOverrideDistrictCourseCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "DistrictCourseCode");
    private final static QName _SectionInfoTypeSchoolCourseInfoOverrideStateCourseCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "StateCourseCode");
    private final static QName _SectionInfoTypeSchoolCourseInfoOverrideCourseTitle_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CourseTitle");
    private final static QName _SectionInfoTypeSchoolCourseInfoOverrideInstructionalLevel_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "InstructionalLevel");
    private final static QName _SectionInfoTypeSchoolCourseInfoOverrideCourseCode_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CourseCode");
    private final static QName _SchoolCourseInfoTypeCourseContent_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CourseContent");
    private final static QName _SchoolCourseInfoTypeCoreAcademicCourse_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CoreAcademicCourse");
    private final static QName _SchoolCourseInfoTypeGraduationRequirement_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GraduationRequirement");
    private final static QName _SchoolCourseInfoTypeDepartment_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Department");
    private final static QName _Sif3AssessmentFormTypeFormAccommodations_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FormAccommodations");
    private final static QName _Sif3AssessmentFormTypeFormIdentifiers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FormIdentifiers");
    private final static QName _Sif3AssessmentFormTypeFormVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FormVersion");
    private final static QName _Sif3AssessmentFormTypeGradeLevels_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "GradeLevels");
    private final static QName _Sif3AssessmentFormTypeAssessmentFormLanguages_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentFormLanguages");
    private final static QName _Sif3AssessmentFormTypeAssessmentFormSubjects_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentFormSubjects");
    private final static QName _Sif3AssessmentFormTypeAssessmentSections_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentSections");
    private final static QName _Sif3AssessmentFormTypeAssessmentAssetRefIds_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentAssetRefIds");
    private final static QName _Sif3AssessmentFormTypeAssessmentPlatforms_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "AssessmentPlatforms");
    private final static QName _Sif3AssessmentFormTypeFormPublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FormPublishDate");
    private final static QName _SchoolInfoTypeReligiousAffiliation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ReligiousAffiliation");
    private final static QName _SchoolInfoTypeFederalElectorate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "FederalElectorate");
    private final static QName _SchoolInfoTypeCampus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Campus");
    private final static QName _SchoolInfoTypeEntityOpen_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Entity_Open");
    private final static QName _SchoolInfoTypeLocalGovernmentArea_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocalGovernmentArea");
    private final static QName _SchoolInfoTypeSchoolDistrict_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolDistrict");
    private final static QName _SchoolInfoTypeNonGovSystemicStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "NonGovSystemicStatus");
    private final static QName _SchoolInfoTypeSystem_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "System");
    private final static QName _SchoolInfoTypeSchoolGroupList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolGroupList");
    private final static QName _SchoolInfoTypeEntityClose_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Entity_Close");
    private final static QName _SchoolInfoTypeSchoolFocusList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolFocusList");
    private final static QName _SchoolInfoTypeSchoolGeographicLocation_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolGeographicLocation");
    private final static QName _SchoolInfoTypeSessionType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SessionType");
    private final static QName _SchoolInfoTypeBoardingSchoolStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "BoardingSchoolStatus");
    private final static QName _SchoolInfoTypeOtherLEA_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OtherLEA");
    private final static QName _SchoolInfoTypeSchoolDistrictLocalId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolDistrictLocalId");
    private final static QName _SchoolInfoTypeSchoolCoEdStatus_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolCoEdStatus");
    private final static QName _SchoolInfoTypeARIA_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ARIA");
    private final static QName _SchoolInfoTypeSchoolType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolType");
    private final static QName _SchoolInfoTypeIndependentSchool_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "IndependentSchool");
    private final static QName _SchoolInfoTypeSchoolEmailList_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SchoolEmailList");
    private final static QName _LocationTypeLocationRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocationRefId");
    private final static QName _LocationTypeLocationName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "LocationName");
    private final static QName _PersonPictureTypeOKToPublish_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OKToPublish");
    private final static QName _SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentTeachingGroupRefId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "TeachingGroupRefId");
    private final static QName _SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentShortName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ShortName");
    private final static QName _Sif3AssessmentSectionTypeSectionSealed_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionSealed");
    private final static QName _Sif3AssessmentSectionTypeItemSelectionAlgorithm_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemSelectionAlgorithm");
    private final static QName _Sif3AssessmentSectionTypeSectionAssets_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionAssets");
    private final static QName _Sif3AssessmentSectionTypeSectionItems_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionItems");
    private final static QName _Sif3AssessmentSectionTypeSectionTimeLimit_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionTimeLimit");
    private final static QName _Sif3AssessmentSectionTypeSectionName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionName");
    private final static QName _Sif3AssessmentSectionTypeSectionVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionVersion");
    private final static QName _Sif3AssessmentSectionTypeSectionIdentifiers_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionIdentifiers");
    private final static QName _Sif3AssessmentSectionTypeItemSelectionAlgorithmName_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ItemSelectionAlgorithmName");
    private final static QName _Sif3AssessmentSectionTypeSectionReentry_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionReentry");
    private final static QName _Sif3AssessmentSectionTypeSectionPublishDate_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "SectionPublishDate");
    private final static QName _SchoolInfoTypeCampusCampusType_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "CampusType");
    private final static QName _SchoolInfoTypeCampusParentSchoolId_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "ParentSchoolId");
    private final static QName _ActivityTypeSoftwareRequirementListSoftwareRequirementOS_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "OS");
    private final static QName _ActivityTypeSoftwareRequirementListSoftwareRequirementVersion_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Version");
    private final static QName _ActivityTypeSoftwareRequirementListSoftwareRequirementVendor_QNAME = new QName("http://www.sifassociation.org/au/datamodel/1.3", "Vendor");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sif.dd.au30.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SIFQueryDataModelType }
     * 
     */
    public SIFQueryDataModelType createSIFQueryDataModelType() {
        return new SIFQueryDataModelType();
    }

    /**
     * Create an instance of {@link SIFQueryDataModelType.SIFConditionGroup }
     * 
     */
    public SIFQueryDataModelType.SIFConditionGroup createSIFQueryDataModelTypeSIFConditionGroup() {
        return new SIFQueryDataModelType.SIFConditionGroup();
    }

    /**
     * Create an instance of {@link SIFQueryDataModelType.SIFConditionGroup.SIFConditions }
     * 
     */
    public SIFQueryDataModelType.SIFConditionGroup.SIFConditions createSIFQueryDataModelTypeSIFConditionGroupSIFConditions() {
        return new SIFQueryDataModelType.SIFConditionGroup.SIFConditions();
    }

    /**
     * Create an instance of {@link SIFHeaderDataModelType }
     * 
     */
    public SIFHeaderDataModelType createSIFHeaderDataModelType() {
        return new SIFHeaderDataModelType();
    }

    /**
     * Create an instance of {@link SIFHeaderDataModelType.SIFSecurity }
     * 
     */
    public SIFHeaderDataModelType.SIFSecurity createSIFHeaderDataModelTypeSIFSecurity() {
        return new SIFHeaderDataModelType.SIFSecurity();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryResultsDataModelType }
     * 
     */
    public SIFExtendedQueryResultsDataModelType createSIFExtendedQueryResultsDataModelType() {
        return new SIFExtendedQueryResultsDataModelType();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryResultsDataModelType.SIFRows }
     * 
     */
    public SIFExtendedQueryResultsDataModelType.SIFRows createSIFExtendedQueryResultsDataModelTypeSIFRows() {
        return new SIFExtendedQueryResultsDataModelType.SIFRows();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryResultsDataModelType.SIFColumnHeaders }
     * 
     */
    public SIFExtendedQueryResultsDataModelType.SIFColumnHeaders createSIFExtendedQueryResultsDataModelTypeSIFColumnHeaders() {
        return new SIFExtendedQueryResultsDataModelType.SIFColumnHeaders();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType }
     * 
     */
    public SIFExtendedQueryDataModelType createSIFExtendedQueryDataModelType() {
        return new SIFExtendedQueryDataModelType();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFOrderBy }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFOrderBy createSIFExtendedQueryDataModelTypeSIFOrderBy() {
        return new SIFExtendedQueryDataModelType.SIFOrderBy();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFWhere }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFWhere createSIFExtendedQueryDataModelTypeSIFWhere() {
        return new SIFExtendedQueryDataModelType.SIFWhere();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup createSIFExtendedQueryDataModelTypeSIFWhereSIFConditionGroup() {
        return new SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions createSIFExtendedQueryDataModelTypeSIFWhereSIFConditionGroupSIFConditions() {
        return new SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions.SIFCondition }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions.SIFCondition createSIFExtendedQueryDataModelTypeSIFWhereSIFConditionGroupSIFConditionsSIFCondition() {
        return new SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions.SIFCondition();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFFrom }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFFrom createSIFExtendedQueryDataModelTypeSIFFrom() {
        return new SIFExtendedQueryDataModelType.SIFFrom();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFFrom.SIFJoin }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFFrom.SIFJoin createSIFExtendedQueryDataModelTypeSIFFromSIFJoin() {
        return new SIFExtendedQueryDataModelType.SIFFrom.SIFJoin();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn createSIFExtendedQueryDataModelTypeSIFFromSIFJoinSIFJoinOn() {
        return new SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFSelect }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFSelect createSIFExtendedQueryDataModelTypeSIFSelect() {
        return new SIFExtendedQueryDataModelType.SIFSelect();
    }

    /**
     * Create an instance of {@link AbstractContentPackageType }
     * 
     */
    public AbstractContentPackageType createAbstractContentPackageType() {
        return new AbstractContentPackageType();
    }

    /**
     * Create an instance of {@link AbstractContentElementType }
     * 
     */
    public AbstractContentElementType createAbstractContentElementType() {
        return new AbstractContentElementType();
    }

    /**
     * Create an instance of {@link IdentityType }
     * 
     */
    public IdentityType createIdentityType() {
        return new IdentityType();
    }

    /**
     * Create an instance of {@link IdentityType.PasswordList }
     * 
     */
    public IdentityType.PasswordList createIdentityTypePasswordList() {
        return new IdentityType.PasswordList();
    }

    /**
     * Create an instance of {@link IdentityType.IdentityAssertions }
     * 
     */
    public IdentityType.IdentityAssertions createIdentityTypeIdentityAssertions() {
        return new IdentityType.IdentityAssertions();
    }

    /**
     * Create an instance of {@link LocationType }
     * 
     */
    public LocationType createLocationType() {
        return new LocationType();
    }

    /**
     * Create an instance of {@link LEAInfoType }
     * 
     */
    public LEAInfoType createLEAInfoType() {
        return new LEAInfoType();
    }

    /**
     * Create an instance of {@link LEAInfoType.LEAContactList }
     * 
     */
    public LEAInfoType.LEAContactList createLEAInfoTypeLEAContactList() {
        return new LEAInfoType.LEAContactList();
    }

    /**
     * Create an instance of {@link StudentScoreSetType }
     * 
     */
    public StudentScoreSetType createStudentScoreSetType() {
        return new StudentScoreSetType();
    }

    /**
     * Create an instance of {@link StudentScoreSetType.Scores }
     * 
     */
    public StudentScoreSetType.Scores createStudentScoreSetTypeScores() {
        return new StudentScoreSetType.Scores();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSectionType }
     * 
     */
    public Sif3AssessmentSectionType createSif3AssessmentSectionType() {
        return new Sif3AssessmentSectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSectionType.SectionItems }
     * 
     */
    public Sif3AssessmentSectionType.SectionItems createSif3AssessmentSectionTypeSectionItems() {
        return new Sif3AssessmentSectionType.SectionItems();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSectionType.SectionIdentifiers }
     * 
     */
    public Sif3AssessmentSectionType.SectionIdentifiers createSif3AssessmentSectionTypeSectionIdentifiers() {
        return new Sif3AssessmentSectionType.SectionIdentifiers();
    }

    /**
     * Create an instance of {@link SIFExtendedElementsType }
     * 
     */
    public SIFExtendedElementsType createSIFExtendedElementsType() {
        return new SIFExtendedElementsType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType }
     * 
     */
    public Sif3AssessmentSubTestType createSif3AssessmentSubTestType() {
        return new Sif3AssessmentSubTestType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType.AssessmentItems }
     * 
     */
    public Sif3AssessmentSubTestType.AssessmentItems createSif3AssessmentSubTestTypeAssessmentItems() {
        return new Sif3AssessmentSubTestType.AssessmentItems();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType.SubTestIdentifiers }
     * 
     */
    public Sif3AssessmentSubTestType.SubTestIdentifiers createSif3AssessmentSubTestTypeSubTestIdentifiers() {
        return new Sif3AssessmentSubTestType.SubTestIdentifiers();
    }

    /**
     * Create an instance of {@link Sif3AssessmentType }
     * 
     */
    public Sif3AssessmentType createSif3AssessmentType() {
        return new Sif3AssessmentType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentType.AssessmentItemBanks }
     * 
     */
    public Sif3AssessmentType.AssessmentItemBanks createSif3AssessmentTypeAssessmentItemBanks() {
        return new Sif3AssessmentType.AssessmentItemBanks();
    }

    /**
     * Create an instance of {@link Sif3AssessmentType.AssessmentIdentifiers }
     * 
     */
    public Sif3AssessmentType.AssessmentIdentifiers createSif3AssessmentTypeAssessmentIdentifiers() {
        return new Sif3AssessmentType.AssessmentIdentifiers();
    }

    /**
     * Create an instance of {@link StaffPersonalType }
     * 
     */
    public StaffPersonalType createStaffPersonalType() {
        return new StaffPersonalType();
    }

    /**
     * Create an instance of {@link StaffPersonalType.OtherIdList }
     * 
     */
    public StaffPersonalType.OtherIdList createStaffPersonalTypeOtherIdList() {
        return new StaffPersonalType.OtherIdList();
    }

    /**
     * Create an instance of {@link AssessmentPackageType }
     * 
     */
    public AssessmentPackageType createAssessmentPackageType() {
        return new AssessmentPackageType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSessionType }
     * 
     */
    public Sif3AssessmentSessionType createSif3AssessmentSessionType() {
        return new Sif3AssessmentSessionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSessionType.UnusualEvents }
     * 
     */
    public Sif3AssessmentSessionType.UnusualEvents createSif3AssessmentSessionTypeUnusualEvents() {
        return new Sif3AssessmentSessionType.UnusualEvents();
    }

    /**
     * Create an instance of {@link StudentParticipationType }
     * 
     */
    public StudentParticipationType createStudentParticipationType() {
        return new StudentParticipationType();
    }

    /**
     * Create an instance of {@link StudentParticipationType.ProgramFundingSources }
     * 
     */
    public StudentParticipationType.ProgramFundingSources createStudentParticipationTypeProgramFundingSources() {
        return new StudentParticipationType.ProgramFundingSources();
    }

    /**
     * Create an instance of {@link AssessmentAdministrationType }
     * 
     */
    public AssessmentAdministrationType createAssessmentAdministrationType() {
        return new AssessmentAdministrationType();
    }

    /**
     * Create an instance of {@link AssessmentAdministrationType.SpecialConditions }
     * 
     */
    public AssessmentAdministrationType.SpecialConditions createAssessmentAdministrationTypeSpecialConditions() {
        return new AssessmentAdministrationType.SpecialConditions();
    }

    /**
     * Create an instance of {@link CalendarDate }
     * 
     */
    public CalendarDate createCalendarDate() {
        return new CalendarDate();
    }

    /**
     * Create an instance of {@link RoomInfoType }
     * 
     */
    public RoomInfoType createRoomInfoType() {
        return new RoomInfoType();
    }

    /**
     * Create an instance of {@link StudentActivityParticipationType }
     * 
     */
    public StudentActivityParticipationType createStudentActivityParticipationType() {
        return new StudentActivityParticipationType();
    }

    /**
     * Create an instance of {@link StudentSnapshotType }
     * 
     */
    public StudentSnapshotType createStudentSnapshotType() {
        return new StudentSnapshotType();
    }

    /**
     * Create an instance of {@link StudentSnapshotType.HomeEnrollment }
     * 
     */
    public StudentSnapshotType.HomeEnrollment createStudentSnapshotTypeHomeEnrollment() {
        return new StudentSnapshotType.HomeEnrollment();
    }

    /**
     * Create an instance of {@link StudentSnapshotType.StudentSubjectChoiceList }
     * 
     */
    public StudentSnapshotType.StudentSubjectChoiceList createStudentSnapshotTypeStudentSubjectChoiceList() {
        return new StudentSnapshotType.StudentSubjectChoiceList();
    }

    /**
     * Create an instance of {@link StudentPersonalType }
     * 
     */
    public StudentPersonalType createStudentPersonalType() {
        return new StudentPersonalType();
    }

    /**
     * Create an instance of {@link StudentPersonalType.OtherIdList }
     * 
     */
    public StudentPersonalType.OtherIdList createStudentPersonalTypeOtherIdList() {
        return new StudentPersonalType.OtherIdList();
    }

    /**
     * Create an instance of {@link StudentPersonalType.MedicalAlertMessages }
     * 
     */
    public StudentPersonalType.MedicalAlertMessages createStudentPersonalTypeMedicalAlertMessages() {
        return new StudentPersonalType.MedicalAlertMessages();
    }

    /**
     * Create an instance of {@link StudentPersonalType.AlertMessages }
     * 
     */
    public StudentPersonalType.AlertMessages createStudentPersonalTypeAlertMessages() {
        return new StudentPersonalType.AlertMessages();
    }

    /**
     * Create an instance of {@link StudentActivityInfoType }
     * 
     */
    public StudentActivityInfoType createStudentActivityInfoType() {
        return new StudentActivityInfoType();
    }

    /**
     * Create an instance of {@link StudentContactPersonalType }
     * 
     */
    public StudentContactPersonalType createStudentContactPersonalType() {
        return new StudentContactPersonalType();
    }

    /**
     * Create an instance of {@link StudentContactPersonalType.OtherIdList }
     * 
     */
    public StudentContactPersonalType.OtherIdList createStudentContactPersonalTypeOtherIdList() {
        return new StudentContactPersonalType.OtherIdList();
    }

    /**
     * Create an instance of {@link SchoolContactListType }
     * 
     */
    public SchoolContactListType createSchoolContactListType() {
        return new SchoolContactListType();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType }
     * 
     */
    public Sif3StudentResponseSetType createSif3StudentResponseSetType() {
        return new Sif3StudentResponseSetType();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items }
     * 
     */
    public Sif3StudentResponseSetType.Items createSif3StudentResponseSetTypeItems() {
        return new Sif3StudentResponseSetType.Items();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item createSif3StudentResponseSetTypeItemsItem() {
        return new Sif3StudentResponseSetType.Items.Item();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item.FeedbackItems }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item.FeedbackItems createSif3StudentResponseSetTypeItemsItemFeedbackItems() {
        return new Sif3StudentResponseSetType.Items.Item.FeedbackItems();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item.TraitScores }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item.TraitScores createSif3StudentResponseSetTypeItemsItemTraitScores() {
        return new Sif3StudentResponseSetType.Items.Item.TraitScores();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item.Comments }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item.Comments createSif3StudentResponseSetTypeItemsItemComments() {
        return new Sif3StudentResponseSetType.Items.Item.Comments();
    }

    /**
     * Create an instance of {@link TimeTableSubjectType }
     * 
     */
    public TimeTableSubjectType createTimeTableSubjectType() {
        return new TimeTableSubjectType();
    }

    /**
     * Create an instance of {@link AssessmentSubTestType }
     * 
     */
    public AssessmentSubTestType createAssessmentSubTestType() {
        return new AssessmentSubTestType();
    }

    /**
     * Create an instance of {@link AssessmentSubTestType.PerformanceLevels }
     * 
     */
    public AssessmentSubTestType.PerformanceLevels createAssessmentSubTestTypePerformanceLevels() {
        return new AssessmentSubTestType.PerformanceLevels();
    }

    /**
     * Create an instance of {@link AssessmentSubTestType.PerformanceLevels.PerformanceLevel }
     * 
     */
    public AssessmentSubTestType.PerformanceLevels.PerformanceLevel createAssessmentSubTestTypePerformanceLevelsPerformanceLevel() {
        return new AssessmentSubTestType.PerformanceLevels.PerformanceLevel();
    }

    /**
     * Create an instance of {@link LearningStandardItemType }
     * 
     */
    public LearningStandardItemType createLearningStandardItemType() {
        return new LearningStandardItemType();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.RelatedLearningStandardItems }
     * 
     */
    public LearningStandardItemType.RelatedLearningStandardItems createLearningStandardItemTypeRelatedLearningStandardItems() {
        return new LearningStandardItemType.RelatedLearningStandardItems();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.StandardIdentifier }
     * 
     */
    public LearningStandardItemType.StandardIdentifier createLearningStandardItemTypeStandardIdentifier() {
        return new LearningStandardItemType.StandardIdentifier();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.Resources }
     * 
     */
    public LearningStandardItemType.Resources createLearningStandardItemTypeResources() {
        return new LearningStandardItemType.Resources();
    }

    /**
     * Create an instance of {@link StudentSDTNType }
     * 
     */
    public StudentSDTNType createStudentSDTNType() {
        return new StudentSDTNType();
    }

    /**
     * Create an instance of {@link StudentSDTNType.OtherLearningAreasList }
     * 
     */
    public StudentSDTNType.OtherLearningAreasList createStudentSDTNTypeOtherLearningAreasList() {
        return new StudentSDTNType.OtherLearningAreasList();
    }

    /**
     * Create an instance of {@link StudentSDTNType.AreasOfInterestList }
     * 
     */
    public StudentSDTNType.AreasOfInterestList createStudentSDTNTypeAreasOfInterestList() {
        return new StudentSDTNType.AreasOfInterestList();
    }

    /**
     * Create an instance of {@link StudentSDTNType.PreviousSchoolsList }
     * 
     */
    public StudentSDTNType.PreviousSchoolsList createStudentSDTNTypePreviousSchoolsList() {
        return new StudentSDTNType.PreviousSchoolsList();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType }
     * 
     */
    public Sif3AssessmentFormType createSif3AssessmentFormType() {
        return new Sif3AssessmentFormType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.AssessmentSections }
     * 
     */
    public Sif3AssessmentFormType.AssessmentSections createSif3AssessmentFormTypeAssessmentSections() {
        return new Sif3AssessmentFormType.AssessmentSections();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.FormIdentifiers }
     * 
     */
    public Sif3AssessmentFormType.FormIdentifiers createSif3AssessmentFormTypeFormIdentifiers() {
        return new Sif3AssessmentFormType.FormIdentifiers();
    }

    /**
     * Create an instance of {@link AssessmentItemType }
     * 
     */
    public AssessmentItemType createAssessmentItemType() {
        return new AssessmentItemType();
    }

    /**
     * Create an instance of {@link AssessmentItemType.PerformanceLevels }
     * 
     */
    public AssessmentItemType.PerformanceLevels createAssessmentItemTypePerformanceLevels() {
        return new AssessmentItemType.PerformanceLevels();
    }

    /**
     * Create an instance of {@link AssessmentItemType.PerformanceLevels.PerformanceLevel }
     * 
     */
    public AssessmentItemType.PerformanceLevels.PerformanceLevel createAssessmentItemTypePerformanceLevelsPerformanceLevel() {
        return new AssessmentItemType.PerformanceLevels.PerformanceLevel();
    }

    /**
     * Create an instance of {@link AssessmentItemType.ResponseChoices }
     * 
     */
    public AssessmentItemType.ResponseChoices createAssessmentItemTypeResponseChoices() {
        return new AssessmentItemType.ResponseChoices();
    }

    /**
     * Create an instance of {@link TimeTableType }
     * 
     */
    public TimeTableType createTimeTableType() {
        return new TimeTableType();
    }

    /**
     * Create an instance of {@link TimeTableType.TimeTableDayList }
     * 
     */
    public TimeTableType.TimeTableDayList createTimeTableTypeTimeTableDayList() {
        return new TimeTableType.TimeTableDayList();
    }

    /**
     * Create an instance of {@link TimeTableType.TimeTableDayList.TimeTableDay }
     * 
     */
    public TimeTableType.TimeTableDayList.TimeTableDay createTimeTableTypeTimeTableDayListTimeTableDay() {
        return new TimeTableType.TimeTableDayList.TimeTableDay();
    }

    /**
     * Create an instance of {@link TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList }
     * 
     */
    public TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodList() {
        return new TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList();
    }

    /**
     * Create an instance of {@link SystemRoleType }
     * 
     */
    public SystemRoleType createSystemRoleType() {
        return new SystemRoleType();
    }

    /**
     * Create an instance of {@link SystemRoleType.SystemContextList }
     * 
     */
    public SystemRoleType.SystemContextList createSystemRoleTypeSystemContextList() {
        return new SystemRoleType.SystemContextList();
    }

    /**
     * Create an instance of {@link SystemRoleType.SystemContextList.SystemContext }
     * 
     */
    public SystemRoleType.SystemContextList.SystemContext createSystemRoleTypeSystemContextListSystemContext() {
        return new SystemRoleType.SystemContextList.SystemContext();
    }

    /**
     * Create an instance of {@link SystemRoleType.SystemContextList.SystemContext.RoleList }
     * 
     */
    public SystemRoleType.SystemContextList.SystemContext.RoleList createSystemRoleTypeSystemContextListSystemContextRoleList() {
        return new SystemRoleType.SystemContextList.SystemContext.RoleList();
    }

    /**
     * Create an instance of {@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role }
     * 
     */
    public SystemRoleType.SystemContextList.SystemContext.RoleList.Role createSystemRoleTypeSystemContextListSystemContextRoleListRole() {
        return new SystemRoleType.SystemContextList.SystemContext.RoleList.Role();
    }

    /**
     * Create an instance of {@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList }
     * 
     */
    public SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList createSystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeList() {
        return new SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList();
    }

    /**
     * Create an instance of {@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope }
     * 
     */
    public SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope createSystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScope() {
        return new SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope();
    }

    /**
     * Create an instance of {@link TimeElementType }
     * 
     */
    public TimeElementType createTimeElementType() {
        return new TimeElementType();
    }

    /**
     * Create an instance of {@link TimeElementType.SpanGaps }
     * 
     */
    public TimeElementType.SpanGaps createTimeElementTypeSpanGaps() {
        return new TimeElementType.SpanGaps();
    }

    /**
     * Create an instance of {@link AssessmentType }
     * 
     */
    public AssessmentType createAssessmentType() {
        return new AssessmentType();
    }

    /**
     * Create an instance of {@link EducationFilterType }
     * 
     */
    public EducationFilterType createEducationFilterType() {
        return new EducationFilterType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link AddressType.StatisticalAreas }
     * 
     */
    public AddressType.StatisticalAreas createAddressTypeStatisticalAreas() {
        return new AddressType.StatisticalAreas();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType }
     * 
     */
    public Sif3AssessmentRubricType createSif3AssessmentRubricType() {
        return new Sif3AssessmentRubricType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType.Scores }
     * 
     */
    public Sif3AssessmentRubricType.Scores createSif3AssessmentRubricTypeScores() {
        return new Sif3AssessmentRubricType.Scores();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType.Scores.Score }
     * 
     */
    public Sif3AssessmentRubricType.Scores.Score createSif3AssessmentRubricTypeScoresScore() {
        return new Sif3AssessmentRubricType.Scores.Score();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType.Scores.Score.ScoreComments }
     * 
     */
    public Sif3AssessmentRubricType.Scores.Score.ScoreComments createSif3AssessmentRubricTypeScoresScoreScoreComments() {
        return new Sif3AssessmentRubricType.Scores.Score.ScoreComments();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType.RubricIdentifiers }
     * 
     */
    public Sif3AssessmentRubricType.RubricIdentifiers createSif3AssessmentRubricTypeRubricIdentifiers() {
        return new Sif3AssessmentRubricType.RubricIdentifiers();
    }

    /**
     * Create an instance of {@link AggregateStatisticFactType }
     * 
     */
    public AggregateStatisticFactType createAggregateStatisticFactType() {
        return new AggregateStatisticFactType();
    }

    /**
     * Create an instance of {@link ResourceUsageType }
     * 
     */
    public ResourceUsageType createResourceUsageType() {
        return new ResourceUsageType();
    }

    /**
     * Create an instance of {@link ResourceUsageType.ResourceReportLineList }
     * 
     */
    public ResourceUsageType.ResourceReportLineList createResourceUsageTypeResourceReportLineList() {
        return new ResourceUsageType.ResourceReportLineList();
    }

    /**
     * Create an instance of {@link ResourceUsageType.ResourceReportLineList.ResourceReportLine }
     * 
     */
    public ResourceUsageType.ResourceReportLineList.ResourceReportLine createResourceUsageTypeResourceReportLineListResourceReportLine() {
        return new ResourceUsageType.ResourceReportLineList.ResourceReportLine();
    }

    /**
     * Create an instance of {@link ResourceUsageType.ResourceReportColumnList }
     * 
     */
    public ResourceUsageType.ResourceReportColumnList createResourceUsageTypeResourceReportColumnList() {
        return new ResourceUsageType.ResourceReportColumnList();
    }

    /**
     * Create an instance of {@link StudentContactRelationshipType }
     * 
     */
    public StudentContactRelationshipType createStudentContactRelationshipType() {
        return new StudentContactRelationshipType();
    }

    /**
     * Create an instance of {@link LearningResourceType }
     * 
     */
    public LearningResourceType createLearningResourceType() {
        return new LearningResourceType();
    }

    /**
     * Create an instance of {@link LearningResourceType.Components }
     * 
     */
    public LearningResourceType.Components createLearningResourceTypeComponents() {
        return new LearningResourceType.Components();
    }

    /**
     * Create an instance of {@link LearningResourceType.Components.Component }
     * 
     */
    public LearningResourceType.Components.Component createLearningResourceTypeComponentsComponent() {
        return new LearningResourceType.Components.Component();
    }

    /**
     * Create an instance of {@link LearningResourceType.Components.Component.AssociatedObjects }
     * 
     */
    public LearningResourceType.Components.Component.AssociatedObjects createLearningResourceTypeComponentsComponentAssociatedObjects() {
        return new LearningResourceType.Components.Component.AssociatedObjects();
    }

    /**
     * Create an instance of {@link LearningResourceType.Evaluations }
     * 
     */
    public LearningResourceType.Evaluations createLearningResourceTypeEvaluations() {
        return new LearningResourceType.Evaluations();
    }

    /**
     * Create an instance of {@link LearningResourceType.Approvals }
     * 
     */
    public LearningResourceType.Approvals createLearningResourceTypeApprovals() {
        return new LearningResourceType.Approvals();
    }

    /**
     * Create an instance of {@link LearningResourceType.Contacts }
     * 
     */
    public LearningResourceType.Contacts createLearningResourceTypeContacts() {
        return new LearningResourceType.Contacts();
    }

    /**
     * Create an instance of {@link LearningStandardDocumentType }
     * 
     */
    public LearningStandardDocumentType createLearningStandardDocumentType() {
        return new LearningStandardDocumentType();
    }

    /**
     * Create an instance of {@link AggregateStatisticInfoType }
     * 
     */
    public AggregateStatisticInfoType createAggregateStatisticInfoType() {
        return new AggregateStatisticInfoType();
    }

    /**
     * Create an instance of {@link AggregateStatisticInfoType.ExclusionRules }
     * 
     */
    public AggregateStatisticInfoType.ExclusionRules createAggregateStatisticInfoTypeExclusionRules() {
        return new AggregateStatisticInfoType.ExclusionRules();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType }
     * 
     */
    public Sif3AssessmentItemType createSif3AssessmentItemType() {
        return new Sif3AssessmentItemType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.AssessmentItemBanks }
     * 
     */
    public Sif3AssessmentItemType.AssessmentItemBanks createSif3AssessmentItemTypeAssessmentItemBanks() {
        return new Sif3AssessmentItemType.AssessmentItemBanks();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.ResponseChoices }
     * 
     */
    public Sif3AssessmentItemType.ResponseChoices createSif3AssessmentItemTypeResponseChoices() {
        return new Sif3AssessmentItemType.ResponseChoices();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.AssessmentIdentifiers }
     * 
     */
    public Sif3AssessmentItemType.AssessmentIdentifiers createSif3AssessmentItemTypeAssessmentIdentifiers() {
        return new Sif3AssessmentItemType.AssessmentIdentifiers();
    }

    /**
     * Create an instance of {@link AddressListType }
     * 
     */
    public AddressListType createAddressListType() {
        return new AddressListType();
    }

    /**
     * Create an instance of {@link StudentPeriodAttendanceType }
     * 
     */
    public StudentPeriodAttendanceType createStudentPeriodAttendanceType() {
        return new StudentPeriodAttendanceType();
    }

    /**
     * Create an instance of {@link StudentPeriodAttendanceType.AuditInfo }
     * 
     */
    public StudentPeriodAttendanceType.AuditInfo createStudentPeriodAttendanceTypeAuditInfo() {
        return new StudentPeriodAttendanceType.AuditInfo();
    }

    /**
     * Create an instance of {@link LifeCycleType }
     * 
     */
    public LifeCycleType createLifeCycleType() {
        return new LifeCycleType();
    }

    /**
     * Create an instance of {@link LifeCycleType.ModificationHistory }
     * 
     */
    public LifeCycleType.ModificationHistory createLifeCycleTypeModificationHistory() {
        return new LifeCycleType.ModificationHistory();
    }

    /**
     * Create an instance of {@link LifeCycleType.Created }
     * 
     */
    public LifeCycleType.Created createLifeCycleTypeCreated() {
        return new LifeCycleType.Created();
    }

    /**
     * Create an instance of {@link LifeCycleType.Created.Creators }
     * 
     */
    public LifeCycleType.Created.Creators createLifeCycleTypeCreatedCreators() {
        return new LifeCycleType.Created.Creators();
    }

    /**
     * Create an instance of {@link SchoolProgramsType }
     * 
     */
    public SchoolProgramsType createSchoolProgramsType() {
        return new SchoolProgramsType();
    }

    /**
     * Create an instance of {@link SchoolProgramsType.SchoolProgramList }
     * 
     */
    public SchoolProgramsType.SchoolProgramList createSchoolProgramsTypeSchoolProgramList() {
        return new SchoolProgramsType.SchoolProgramList();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAdministrationType }
     * 
     */
    public Sif3AssessmentAdministrationType createSif3AssessmentAdministrationType() {
        return new Sif3AssessmentAdministrationType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAdministrationType.Organizations }
     * 
     */
    public Sif3AssessmentAdministrationType.Organizations createSif3AssessmentAdministrationTypeOrganizations() {
        return new Sif3AssessmentAdministrationType.Organizations();
    }

    /**
     * Create an instance of {@link AssessmentFormType }
     * 
     */
    public AssessmentFormType createAssessmentFormType() {
        return new AssessmentFormType();
    }

    /**
     * Create an instance of {@link SIFMetadataType }
     * 
     */
    public SIFMetadataType createSIFMetadataType() {
        return new SIFMetadataType();
    }

    /**
     * Create an instance of {@link PhoneNumberListType }
     * 
     */
    public PhoneNumberListType createPhoneNumberListType() {
        return new PhoneNumberListType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType }
     * 
     */
    public Sif3AssessmentRegistrationType createSif3AssessmentRegistrationType() {
        return new Sif3AssessmentRegistrationType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType.AssessmentStudentSnapshot }
     * 
     */
    public Sif3AssessmentRegistrationType.AssessmentStudentSnapshot createSif3AssessmentRegistrationTypeAssessmentStudentSnapshot() {
        return new Sif3AssessmentRegistrationType.AssessmentStudentSnapshot();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment }
     * 
     */
    public Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollment() {
        return new Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType.TestingStatuses }
     * 
     */
    public Sif3AssessmentRegistrationType.TestingStatuses createSif3AssessmentRegistrationTypeTestingStatuses() {
        return new Sif3AssessmentRegistrationType.TestingStatuses();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType.StudentSpecialEvents }
     * 
     */
    public Sif3AssessmentRegistrationType.StudentSpecialEvents createSif3AssessmentRegistrationTypeStudentSpecialEvents() {
        return new Sif3AssessmentRegistrationType.StudentSpecialEvents();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType }
     * 
     */
    public SummaryEnrollmentInfoType createSummaryEnrollmentInfoType() {
        return new SummaryEnrollmentInfoType();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType.SchoolEnrollmentList }
     * 
     */
    public SummaryEnrollmentInfoType.SchoolEnrollmentList createSummaryEnrollmentInfoTypeSchoolEnrollmentList() {
        return new SummaryEnrollmentInfoType.SchoolEnrollmentList();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment }
     * 
     */
    public SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollment() {
        return new SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList }
     * 
     */
    public SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentList() {
        return new SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList();
    }

    /**
     * Create an instance of {@link PersonPictureType }
     * 
     */
    public PersonPictureType createPersonPictureType() {
        return new PersonPictureType();
    }

    /**
     * Create an instance of {@link SIFReportObjectType }
     * 
     */
    public SIFReportObjectType createSIFReportObjectType() {
        return new SIFReportObjectType();
    }

    /**
     * Create an instance of {@link SIFReportObjectType.ReportInfo }
     * 
     */
    public SIFReportObjectType.ReportInfo createSIFReportObjectTypeReportInfo() {
        return new SIFReportObjectType.ReportInfo();
    }

    /**
     * Create an instance of {@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo }
     * 
     */
    public SIFReportObjectType.ReportInfo.ReportSubmitterInfo createSIFReportObjectTypeReportInfoReportSubmitterInfo() {
        return new SIFReportObjectType.ReportInfo.ReportSubmitterInfo();
    }

    /**
     * Create an instance of {@link OtherCodeListType }
     * 
     */
    public OtherCodeListType createOtherCodeListType() {
        return new OtherCodeListType();
    }

    /**
     * Create an instance of {@link ReportManifestType }
     * 
     */
    public ReportManifestType createReportManifestType() {
        return new ReportManifestType();
    }

    /**
     * Create an instance of {@link ReportManifestType.ReportFormatList }
     * 
     */
    public ReportManifestType.ReportFormatList createReportManifestTypeReportFormatList() {
        return new ReportManifestType.ReportFormatList();
    }

    /**
     * Create an instance of {@link OtherNamesType }
     * 
     */
    public OtherNamesType createOtherNamesType() {
        return new OtherNamesType();
    }

    /**
     * Create an instance of {@link ActivityType }
     * 
     */
    public ActivityType createActivityType() {
        return new ActivityType();
    }

    /**
     * Create an instance of {@link ActivityType.ActivityTime }
     * 
     */
    public ActivityType.ActivityTime createActivityTypeActivityTime() {
        return new ActivityType.ActivityTime();
    }

    /**
     * Create an instance of {@link ActivityType.SourceObjects }
     * 
     */
    public ActivityType.SourceObjects createActivityTypeSourceObjects() {
        return new ActivityType.SourceObjects();
    }

    /**
     * Create an instance of {@link ActivityType.SoftwareRequirementList }
     * 
     */
    public ActivityType.SoftwareRequirementList createActivityTypeSoftwareRequirementList() {
        return new ActivityType.SoftwareRequirementList();
    }

    /**
     * Create an instance of {@link AssessmentRegistrationType }
     * 
     */
    public AssessmentRegistrationType createAssessmentRegistrationType() {
        return new AssessmentRegistrationType();
    }

    /**
     * Create an instance of {@link AssessmentRegistrationType.StudentSpecialConditions }
     * 
     */
    public AssessmentRegistrationType.StudentSpecialConditions createAssessmentRegistrationTypeStudentSpecialConditions() {
        return new AssessmentRegistrationType.StudentSpecialConditions();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType }
     * 
     */
    public Sif3AssessmentScoreTableType createSif3AssessmentScoreTableType() {
        return new Sif3AssessmentScoreTableType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType.ScoreValues }
     * 
     */
    public Sif3AssessmentScoreTableType.ScoreValues createSif3AssessmentScoreTableTypeScoreValues() {
        return new Sif3AssessmentScoreTableType.ScoreValues();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue }
     * 
     */
    public Sif3AssessmentScoreTableType.ScoreValues.ScoreValue createSif3AssessmentScoreTableTypeScoreValuesScoreValue() {
        return new Sif3AssessmentScoreTableType.ScoreValues.ScoreValue();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList }
     * 
     */
    public Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList createSif3AssessmentScoreTableTypeScoreValuesScoreValueFeedbackList() {
        return new Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType.ScoreTableIdentifiers }
     * 
     */
    public Sif3AssessmentScoreTableType.ScoreTableIdentifiers createSif3AssessmentScoreTableTypeScoreTableIdentifiers() {
        return new Sif3AssessmentScoreTableType.ScoreTableIdentifiers();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType }
     * 
     */
    public StudentSchoolEnrollmentType createStudentSchoolEnrollmentType() {
        return new StudentSchoolEnrollmentType();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.StudentSubjectChoiceList }
     * 
     */
    public StudentSchoolEnrollmentType.StudentSubjectChoiceList createStudentSchoolEnrollmentTypeStudentSubjectChoiceList() {
        return new StudentSchoolEnrollmentType.StudentSubjectChoiceList();
    }

    /**
     * Create an instance of {@link SectionInfoType }
     * 
     */
    public SectionInfoType createSectionInfoType() {
        return new SectionInfoType();
    }

    /**
     * Create an instance of {@link StaffAssignmentType }
     * 
     */
    public StaffAssignmentType createStaffAssignmentType() {
        return new StaffAssignmentType();
    }

    /**
     * Create an instance of {@link StaffAssignmentType.StaffSubjectList }
     * 
     */
    public StaffAssignmentType.StaffSubjectList createStaffAssignmentTypeStaffSubjectList() {
        return new StaffAssignmentType.StaffSubjectList();
    }

    /**
     * Create an instance of {@link EmailListType }
     * 
     */
    public EmailListType createEmailListType() {
        return new EmailListType();
    }

    /**
     * Create an instance of {@link TeachingGroupType }
     * 
     */
    public TeachingGroupType createTeachingGroupType() {
        return new TeachingGroupType();
    }

    /**
     * Create an instance of {@link TeachingGroupType.TeachingGroupPeriodList }
     * 
     */
    public TeachingGroupType.TeachingGroupPeriodList createTeachingGroupTypeTeachingGroupPeriodList() {
        return new TeachingGroupType.TeachingGroupPeriodList();
    }

    /**
     * Create an instance of {@link TeachingGroupType.TeacherList }
     * 
     */
    public TeachingGroupType.TeacherList createTeachingGroupTypeTeacherList() {
        return new TeachingGroupType.TeacherList();
    }

    /**
     * Create an instance of {@link TeachingGroupType.StudentList }
     * 
     */
    public TeachingGroupType.StudentList createTeachingGroupTypeStudentList() {
        return new TeachingGroupType.StudentList();
    }

    /**
     * Create an instance of {@link DemographicsType }
     * 
     */
    public DemographicsType createDemographicsType() {
        return new DemographicsType();
    }

    /**
     * Create an instance of {@link DemographicsType.ReligiousEventList }
     * 
     */
    public DemographicsType.ReligiousEventList createDemographicsTypeReligiousEventList() {
        return new DemographicsType.ReligiousEventList();
    }

    /**
     * Create an instance of {@link LanguageListType }
     * 
     */
    public LanguageListType createLanguageListType() {
        return new LanguageListType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAssetType }
     * 
     */
    public Sif3AssessmentAssetType createSif3AssessmentAssetType() {
        return new Sif3AssessmentAssetType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAssetType.AssetIdentifiers }
     * 
     */
    public Sif3AssessmentAssetType.AssetIdentifiers createSif3AssessmentAssetTypeAssetIdentifiers() {
        return new Sif3AssessmentAssetType.AssetIdentifiers();
    }

    /**
     * Create an instance of {@link SIFQueryType }
     * 
     */
    public SIFQueryType createSIFQueryType() {
        return new SIFQueryType();
    }

    /**
     * Create an instance of {@link SIFQueryType.SIFConditionGroup }
     * 
     */
    public SIFQueryType.SIFConditionGroup createSIFQueryTypeSIFConditionGroup() {
        return new SIFQueryType.SIFConditionGroup();
    }

    /**
     * Create an instance of {@link SIFQueryType.SIFConditionGroup.SIFConditions }
     * 
     */
    public SIFQueryType.SIFConditionGroup.SIFConditions createSIFQueryTypeSIFConditionGroupSIFConditions() {
        return new SIFQueryType.SIFConditionGroup.SIFConditions();
    }

    /**
     * Create an instance of {@link SchoolInfoType }
     * 
     */
    public SchoolInfoType createSchoolInfoType() {
        return new SchoolInfoType();
    }

    /**
     * Create an instance of {@link SchoolInfoType.OtherIdList }
     * 
     */
    public SchoolInfoType.OtherIdList createSchoolInfoTypeOtherIdList() {
        return new SchoolInfoType.OtherIdList();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemAssociationCollectionType }
     * 
     */
    public Sif3AssessmentItemAssociationCollectionType createSif3AssessmentItemAssociationCollectionType() {
        return new Sif3AssessmentItemAssociationCollectionType();
    }

    /**
     * Create an instance of {@link StudentSnapshotCollectionType }
     * 
     */
    public StudentSnapshotCollectionType createStudentSnapshotCollectionType() {
        return new StudentSnapshotCollectionType();
    }

    /**
     * Create an instance of {@link CalendarSummaryType }
     * 
     */
    public CalendarSummaryType createCalendarSummaryType() {
        return new CalendarSummaryType();
    }

    /**
     * Create an instance of {@link TermInfoCollectionType }
     * 
     */
    public TermInfoCollectionType createTermInfoCollectionType() {
        return new TermInfoCollectionType();
    }

    /**
     * Create an instance of {@link StudentCollectionType }
     * 
     */
    public StudentCollectionType createStudentCollectionType() {
        return new StudentCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestCollectionType }
     * 
     */
    public Sif3AssessmentSubTestCollectionType createSif3AssessmentSubTestCollectionType() {
        return new Sif3AssessmentSubTestCollectionType();
    }

    /**
     * Create an instance of {@link SubjectAreaType }
     * 
     */
    public SubjectAreaType createSubjectAreaType() {
        return new SubjectAreaType();
    }

    /**
     * Create an instance of {@link StudentScoreSetCollectionType }
     * 
     */
    public StudentScoreSetCollectionType createStudentScoreSetCollectionType() {
        return new StudentScoreSetCollectionType();
    }

    /**
     * Create an instance of {@link LearningResourcePackageType }
     * 
     */
    public LearningResourcePackageType createLearningResourcePackageType() {
        return new LearningResourcePackageType();
    }

    /**
     * Create an instance of {@link ReportAuthorityInfoCollectionType }
     * 
     */
    public ReportAuthorityInfoCollectionType createReportAuthorityInfoCollectionType() {
        return new ReportAuthorityInfoCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemAssociationType }
     * 
     */
    public Sif3AssessmentItemAssociationType createSif3AssessmentItemAssociationType() {
        return new Sif3AssessmentItemAssociationType();
    }

    /**
     * Create an instance of {@link SchoolCourseInfoCollectionType }
     * 
     */
    public SchoolCourseInfoCollectionType createSchoolCourseInfoCollectionType() {
        return new SchoolCourseInfoCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSessionCollectionType }
     * 
     */
    public Sif3AssessmentSessionCollectionType createSif3AssessmentSessionCollectionType() {
        return new Sif3AssessmentSessionCollectionType();
    }

    /**
     * Create an instance of {@link TeachingGroupCollectionType }
     * 
     */
    public TeachingGroupCollectionType createTeachingGroupCollectionType() {
        return new TeachingGroupCollectionType();
    }

    /**
     * Create an instance of {@link PhoneNumberType }
     * 
     */
    public PhoneNumberType createPhoneNumberType() {
        return new PhoneNumberType();
    }

    /**
     * Create an instance of {@link SubjectAreaListType }
     * 
     */
    public SubjectAreaListType createSubjectAreaListType() {
        return new SubjectAreaListType();
    }

    /**
     * Create an instance of {@link EmailType }
     * 
     */
    public EmailType createEmailType() {
        return new EmailType();
    }

    /**
     * Create an instance of {@link StudentActivityInfoCollectionType }
     * 
     */
    public StudentActivityInfoCollectionType createStudentActivityInfoCollectionType() {
        return new StudentActivityInfoCollectionType();
    }

    /**
     * Create an instance of {@link AssessmentRegistrationCollectionType }
     * 
     */
    public AssessmentRegistrationCollectionType createAssessmentRegistrationCollectionType() {
        return new AssessmentRegistrationCollectionType();
    }

    /**
     * Create an instance of {@link AssessmentFormCollectionType }
     * 
     */
    public AssessmentFormCollectionType createAssessmentFormCollectionType() {
        return new AssessmentFormCollectionType();
    }

    /**
     * Create an instance of {@link TimeTableSubjectCollectionType }
     * 
     */
    public TimeTableSubjectCollectionType createTimeTableSubjectCollectionType() {
        return new TimeTableSubjectCollectionType();
    }

    /**
     * Create an instance of {@link StudentParticipationCollectionType }
     * 
     */
    public StudentParticipationCollectionType createStudentParticipationCollectionType() {
        return new StudentParticipationCollectionType();
    }

    /**
     * Create an instance of {@link StaffCollectionType }
     * 
     */
    public StaffCollectionType createStaffCollectionType() {
        return new StaffCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricCollectionType }
     * 
     */
    public Sif3AssessmentRubricCollectionType createSif3AssessmentRubricCollectionType() {
        return new Sif3AssessmentRubricCollectionType();
    }

    /**
     * Create an instance of {@link AssessmentAdministrationCollectionType }
     * 
     */
    public AssessmentAdministrationCollectionType createAssessmentAdministrationCollectionType() {
        return new AssessmentAdministrationCollectionType();
    }

    /**
     * Create an instance of {@link StudentDailyAttendanceCollectionType }
     * 
     */
    public StudentDailyAttendanceCollectionType createStudentDailyAttendanceCollectionType() {
        return new StudentDailyAttendanceCollectionType();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoCollectionType }
     * 
     */
    public SummaryEnrollmentInfoCollectionType createSummaryEnrollmentInfoCollectionType() {
        return new SummaryEnrollmentInfoCollectionType();
    }

    /**
     * Create an instance of {@link StudentContactRelationshipCollectionType }
     * 
     */
    public StudentContactRelationshipCollectionType createStudentContactRelationshipCollectionType() {
        return new StudentContactRelationshipCollectionType();
    }

    /**
     * Create an instance of {@link StaffAssignmentCollectionType }
     * 
     */
    public StaffAssignmentCollectionType createStaffAssignmentCollectionType() {
        return new StaffAssignmentCollectionType();
    }

    /**
     * Create an instance of {@link CalendarSummaryCollectionType }
     * 
     */
    public CalendarSummaryCollectionType createCalendarSummaryCollectionType() {
        return new CalendarSummaryCollectionType();
    }

    /**
     * Create an instance of {@link StudentAttendanceSummaryType }
     * 
     */
    public StudentAttendanceSummaryType createStudentAttendanceSummaryType() {
        return new StudentAttendanceSummaryType();
    }

    /**
     * Create an instance of {@link AggregateStatisticFactCollectionType }
     * 
     */
    public AggregateStatisticFactCollectionType createAggregateStatisticFactCollectionType() {
        return new AggregateStatisticFactCollectionType();
    }

    /**
     * Create an instance of {@link ReportManifestCollectionType }
     * 
     */
    public ReportManifestCollectionType createReportManifestCollectionType() {
        return new ReportManifestCollectionType();
    }

    /**
     * Create an instance of {@link AggregateCharacteristicInfoCollectionType }
     * 
     */
    public AggregateCharacteristicInfoCollectionType createAggregateCharacteristicInfoCollectionType() {
        return new AggregateCharacteristicInfoCollectionType();
    }

    /**
     * Create an instance of {@link LearningResourcePackageCollectionType }
     * 
     */
    public LearningResourcePackageCollectionType createLearningResourcePackageCollectionType() {
        return new LearningResourcePackageCollectionType();
    }

    /**
     * Create an instance of {@link AggregateCharacteristicInfoType }
     * 
     */
    public AggregateCharacteristicInfoType createAggregateCharacteristicInfoType() {
        return new AggregateCharacteristicInfoType();
    }

    /**
     * Create an instance of {@link IdentityCollectionType }
     * 
     */
    public IdentityCollectionType createIdentityCollectionType() {
        return new IdentityCollectionType();
    }

    /**
     * Create an instance of {@link AggregateStatisticInfoCollectionType }
     * 
     */
    public AggregateStatisticInfoCollectionType createAggregateStatisticInfoCollectionType() {
        return new AggregateStatisticInfoCollectionType();
    }

    /**
     * Create an instance of {@link ElectronicIdListType }
     * 
     */
    public ElectronicIdListType createElectronicIdListType() {
        return new ElectronicIdListType();
    }

    /**
     * Create an instance of {@link ElectronicIdType }
     * 
     */
    public ElectronicIdType createElectronicIdType() {
        return new ElectronicIdType();
    }

    /**
     * Create an instance of {@link AttendanceCodeType }
     * 
     */
    public AttendanceCodeType createAttendanceCodeType() {
        return new AttendanceCodeType();
    }

    /**
     * Create an instance of {@link ACStrandSubjectAreaType }
     * 
     */
    public ACStrandSubjectAreaType createACStrandSubjectAreaType() {
        return new ACStrandSubjectAreaType();
    }

    /**
     * Create an instance of {@link ResourceUsageCollectionType }
     * 
     */
    public ResourceUsageCollectionType createResourceUsageCollectionType() {
        return new ResourceUsageCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableCollectionType }
     * 
     */
    public Sif3AssessmentScoreTableCollectionType createSif3AssessmentScoreTableCollectionType() {
        return new Sif3AssessmentScoreTableCollectionType();
    }

    /**
     * Create an instance of {@link TimeTableCellCollectionType }
     * 
     */
    public TimeTableCellCollectionType createTimeTableCellCollectionType() {
        return new TimeTableCellCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentCollectionType }
     * 
     */
    public Sif3AssessmentCollectionType createSif3AssessmentCollectionType() {
        return new Sif3AssessmentCollectionType();
    }

    /**
     * Create an instance of {@link YearLevelType }
     * 
     */
    public YearLevelType createYearLevelType() {
        return new YearLevelType();
    }

    /**
     * Create an instance of {@link SessionInfoType }
     * 
     */
    public SessionInfoType createSessionInfoType() {
        return new SessionInfoType();
    }

    /**
     * Create an instance of {@link RoomInfoCollectionType }
     * 
     */
    public RoomInfoCollectionType createRoomInfoCollectionType() {
        return new RoomInfoCollectionType();
    }

    /**
     * Create an instance of {@link AssessmentItemCollectionType }
     * 
     */
    public AssessmentItemCollectionType createAssessmentItemCollectionType() {
        return new AssessmentItemCollectionType();
    }

    /**
     * Create an instance of {@link LearningStandardDocumentCollectionType }
     * 
     */
    public LearningStandardDocumentCollectionType createLearningStandardDocumentCollectionType() {
        return new LearningStandardDocumentCollectionType();
    }

    /**
     * Create an instance of {@link StudentActivityParticipationCollectionType }
     * 
     */
    public StudentActivityParticipationCollectionType createStudentActivityParticipationCollectionType() {
        return new StudentActivityParticipationCollectionType();
    }

    /**
     * Create an instance of {@link TimeTableCellType }
     * 
     */
    public TimeTableCellType createTimeTableCellType() {
        return new TimeTableCellType();
    }

    /**
     * Create an instance of {@link SchoolProgramsCollectionType }
     * 
     */
    public SchoolProgramsCollectionType createSchoolProgramsCollectionType() {
        return new SchoolProgramsCollectionType();
    }

    /**
     * Create an instance of {@link LearningStandardItemCollectionType }
     * 
     */
    public LearningStandardItemCollectionType createLearningStandardItemCollectionType() {
        return new LearningStandardItemCollectionType();
    }

    /**
     * Create an instance of {@link StudentSectionEnrollmentCollectionType }
     * 
     */
    public StudentSectionEnrollmentCollectionType createStudentSectionEnrollmentCollectionType() {
        return new StudentSectionEnrollmentCollectionType();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetCollectionType }
     * 
     */
    public Sif3StudentResponseSetCollectionType createSif3StudentResponseSetCollectionType() {
        return new Sif3StudentResponseSetCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAssetCollectionType }
     * 
     */
    public Sif3AssessmentAssetCollectionType createSif3AssessmentAssetCollectionType() {
        return new Sif3AssessmentAssetCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormCollectionType }
     * 
     */
    public Sif3AssessmentFormCollectionType createSif3AssessmentFormCollectionType() {
        return new Sif3AssessmentFormCollectionType();
    }

    /**
     * Create an instance of {@link SIFContextsType }
     * 
     */
    public SIFContextsType createSIFContextsType() {
        return new SIFContextsType();
    }

    /**
     * Create an instance of {@link YearLevelsType }
     * 
     */
    public YearLevelsType createYearLevelsType() {
        return new YearLevelsType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemCollectionType }
     * 
     */
    public Sif3AssessmentItemCollectionType createSif3AssessmentItemCollectionType() {
        return new Sif3AssessmentItemCollectionType();
    }

    /**
     * Create an instance of {@link RelationshipType }
     * 
     */
    public RelationshipType createRelationshipType() {
        return new RelationshipType();
    }

    /**
     * Create an instance of {@link ContactInfoType }
     * 
     */
    public ContactInfoType createContactInfoType() {
        return new ContactInfoType();
    }

    /**
     * Create an instance of {@link CalendarDateCollectionType }
     * 
     */
    public CalendarDateCollectionType createCalendarDateCollectionType() {
        return new CalendarDateCollectionType();
    }

    /**
     * Create an instance of {@link AssessmentSubTestCollectionType }
     * 
     */
    public AssessmentSubTestCollectionType createAssessmentSubTestCollectionType() {
        return new AssessmentSubTestCollectionType();
    }

    /**
     * Create an instance of {@link StudentPeriodAttendanceCollectionType }
     * 
     */
    public StudentPeriodAttendanceCollectionType createStudentPeriodAttendanceCollectionType() {
        return new StudentPeriodAttendanceCollectionType();
    }

    /**
     * Create an instance of {@link TermInfoType }
     * 
     */
    public TermInfoType createTermInfoType() {
        return new TermInfoType();
    }

    /**
     * Create an instance of {@link AssessmentPackageCollectionType }
     * 
     */
    public AssessmentPackageCollectionType createAssessmentPackageCollectionType() {
        return new AssessmentPackageCollectionType();
    }

    /**
     * Create an instance of {@link SectionInfoCollectionType }
     * 
     */
    public SectionInfoCollectionType createSectionInfoCollectionType() {
        return new SectionInfoCollectionType();
    }

    /**
     * Create an instance of {@link GridLocationType }
     * 
     */
    public GridLocationType createGridLocationType() {
        return new GridLocationType();
    }

    /**
     * Create an instance of {@link SystemRoleCollectionType }
     * 
     */
    public SystemRoleCollectionType createSystemRoleCollectionType() {
        return new SystemRoleCollectionType();
    }

    /**
     * Create an instance of {@link PersonPictureCollectionType }
     * 
     */
    public PersonPictureCollectionType createPersonPictureCollectionType() {
        return new PersonPictureCollectionType();
    }

    /**
     * Create an instance of {@link StudentContactCollectionType }
     * 
     */
    public StudentContactCollectionType createStudentContactCollectionType() {
        return new StudentContactCollectionType();
    }

    /**
     * Create an instance of {@link PrincipalInfoType }
     * 
     */
    public PrincipalInfoType createPrincipalInfoType() {
        return new PrincipalInfoType();
    }

    /**
     * Create an instance of {@link StudentAttendanceSummaryCollectionType }
     * 
     */
    public StudentAttendanceSummaryCollectionType createStudentAttendanceSummaryCollectionType() {
        return new StudentAttendanceSummaryCollectionType();
    }

    /**
     * Create an instance of {@link ReportAuthorityInfoType }
     * 
     */
    public ReportAuthorityInfoType createReportAuthorityInfoType() {
        return new ReportAuthorityInfoType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAdministrationCollectionType }
     * 
     */
    public Sif3AssessmentAdministrationCollectionType createSif3AssessmentAdministrationCollectionType() {
        return new Sif3AssessmentAdministrationCollectionType();
    }

    /**
     * Create an instance of {@link AssessmentCollectionType }
     * 
     */
    public AssessmentCollectionType createAssessmentCollectionType() {
        return new AssessmentCollectionType();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentCollectionType }
     * 
     */
    public StudentSchoolEnrollmentCollectionType createStudentSchoolEnrollmentCollectionType() {
        return new StudentSchoolEnrollmentCollectionType();
    }

    /**
     * Create an instance of {@link EnglishProficiencyType }
     * 
     */
    public EnglishProficiencyType createEnglishProficiencyType() {
        return new EnglishProficiencyType();
    }

    /**
     * Create an instance of {@link LEAInfoCollectionType }
     * 
     */
    public LEAInfoCollectionType createLEAInfoCollectionType() {
        return new LEAInfoCollectionType();
    }

    /**
     * Create an instance of {@link LearningResourceCollectionType }
     * 
     */
    public LearningResourceCollectionType createLearningResourceCollectionType() {
        return new LearningResourceCollectionType();
    }

    /**
     * Create an instance of {@link StudentSDTNCollectionType }
     * 
     */
    public StudentSDTNCollectionType createStudentSDTNCollectionType() {
        return new StudentSDTNCollectionType();
    }

    /**
     * Create an instance of {@link StudentDailyAttendanceType }
     * 
     */
    public StudentDailyAttendanceType createStudentDailyAttendanceType() {
        return new StudentDailyAttendanceType();
    }

    /**
     * Create an instance of {@link StudentSectionEnrollmentType }
     * 
     */
    public StudentSectionEnrollmentType createStudentSectionEnrollmentType() {
        return new StudentSectionEnrollmentType();
    }

    /**
     * Create an instance of {@link SchoolCourseInfoType }
     * 
     */
    public SchoolCourseInfoType createSchoolCourseInfoType() {
        return new SchoolCourseInfoType();
    }

    /**
     * Create an instance of {@link TimeTableCollectionType }
     * 
     */
    public TimeTableCollectionType createTimeTableCollectionType() {
        return new TimeTableCollectionType();
    }

    /**
     * Create an instance of {@link SchoolCollectionType }
     * 
     */
    public SchoolCollectionType createSchoolCollectionType() {
        return new SchoolCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSectionCollectionType }
     * 
     */
    public Sif3AssessmentSectionCollectionType createSif3AssessmentSectionCollectionType() {
        return new Sif3AssessmentSectionCollectionType();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationCollectionType }
     * 
     */
    public Sif3AssessmentRegistrationCollectionType createSif3AssessmentRegistrationCollectionType() {
        return new Sif3AssessmentRegistrationCollectionType();
    }

    /**
     * Create an instance of {@link SessionInfoCollectionType }
     * 
     */
    public SessionInfoCollectionType createSessionInfoCollectionType() {
        return new SessionInfoCollectionType();
    }

    /**
     * Create an instance of {@link ProgramStatusType }
     * 
     */
    public ProgramStatusType createProgramStatusType() {
        return new ProgramStatusType();
    }

    /**
     * Create an instance of {@link NameType }
     * 
     */
    public NameType createNameType() {
        return new NameType();
    }

    /**
     * Create an instance of {@link ActivityCollectionType }
     * 
     */
    public ActivityCollectionType createActivityCollectionType() {
        return new ActivityCollectionType();
    }

    /**
     * Create an instance of {@link SIFReportObjectCollectionType }
     * 
     */
    public SIFReportObjectCollectionType createSIFReportObjectCollectionType() {
        return new SIFReportObjectCollectionType();
    }

    /**
     * Create an instance of {@link PersonInfoType }
     * 
     */
    public PersonInfoType createPersonInfoType() {
        return new PersonInfoType();
    }

    /**
     * Create an instance of {@link SelectedContentType }
     * 
     */
    public SelectedContentType createSelectedContentType() {
        return new SelectedContentType();
    }

    /**
     * Create an instance of {@link MonetaryAmountType }
     * 
     */
    public MonetaryAmountType createMonetaryAmountType() {
        return new MonetaryAmountType();
    }

    /**
     * Create an instance of {@link SIFErrorDataModelType }
     * 
     */
    public SIFErrorDataModelType createSIFErrorDataModelType() {
        return new SIFErrorDataModelType();
    }

    /**
     * Create an instance of {@link ReportPackageType }
     * 
     */
    public ReportPackageType createReportPackageType() {
        return new ReportPackageType();
    }

    /**
     * Create an instance of {@link ExtendedContentType }
     * 
     */
    public ExtendedContentType createExtendedContentType() {
        return new ExtendedContentType();
    }

    /**
     * Create an instance of {@link BaseNameType }
     * 
     */
    public BaseNameType createBaseNameType() {
        return new BaseNameType();
    }

    /**
     * Create an instance of {@link NameOfRecordType }
     * 
     */
    public NameOfRecordType createNameOfRecordType() {
        return new NameOfRecordType();
    }

    /**
     * Create an instance of {@link ReportDataObjectType }
     * 
     */
    public ReportDataObjectType createReportDataObjectType() {
        return new ReportDataObjectType();
    }

    /**
     * Create an instance of {@link OtherNameType }
     * 
     */
    public OtherNameType createOtherNameType() {
        return new OtherNameType();
    }

    /**
     * Create an instance of {@link ObjectType }
     * 
     */
    public ObjectType createObjectType() {
        return new ObjectType();
    }

    /**
     * Create an instance of {@link SIFQueryDataModelType.SIFQueryObject }
     * 
     */
    public SIFQueryDataModelType.SIFQueryObject createSIFQueryDataModelTypeSIFQueryObject() {
        return new SIFQueryDataModelType.SIFQueryObject();
    }

    /**
     * Create an instance of {@link SIFQueryDataModelType.SIFConditionGroup.SIFConditions.SIFCondition }
     * 
     */
    public SIFQueryDataModelType.SIFConditionGroup.SIFConditions.SIFCondition createSIFQueryDataModelTypeSIFConditionGroupSIFConditionsSIFCondition() {
        return new SIFQueryDataModelType.SIFConditionGroup.SIFConditions.SIFCondition();
    }

    /**
     * Create an instance of {@link SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel }
     * 
     */
    public SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel createSIFHeaderDataModelTypeSIFSecuritySIFSecureChannel() {
        return new SIFHeaderDataModelType.SIFSecurity.SIFSecureChannel();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryResultsDataModelType.SIFRows.R }
     * 
     */
    public SIFExtendedQueryResultsDataModelType.SIFRows.R createSIFExtendedQueryResultsDataModelTypeSIFRowsR() {
        return new SIFExtendedQueryResultsDataModelType.SIFRows.R();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryResultsDataModelType.SIFColumnHeaders.SIFElement }
     * 
     */
    public SIFExtendedQueryResultsDataModelType.SIFColumnHeaders.SIFElement createSIFExtendedQueryResultsDataModelTypeSIFColumnHeadersSIFElement() {
        return new SIFExtendedQueryResultsDataModelType.SIFColumnHeaders.SIFElement();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFOrderBy.SIFElement }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFOrderBy.SIFElement createSIFExtendedQueryDataModelTypeSIFOrderBySIFElement() {
        return new SIFExtendedQueryDataModelType.SIFOrderBy.SIFElement();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions.SIFCondition.SIFElement }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions.SIFCondition.SIFElement createSIFExtendedQueryDataModelTypeSIFWhereSIFConditionGroupSIFConditionsSIFConditionSIFElement() {
        return new SIFExtendedQueryDataModelType.SIFWhere.SIFConditionGroup.SIFConditions.SIFCondition.SIFElement();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn.SIFLeftElement }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn.SIFLeftElement createSIFExtendedQueryDataModelTypeSIFFromSIFJoinSIFJoinOnSIFLeftElement() {
        return new SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn.SIFLeftElement();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn.SIFRightElement }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn.SIFRightElement createSIFExtendedQueryDataModelTypeSIFFromSIFJoinSIFJoinOnSIFRightElement() {
        return new SIFExtendedQueryDataModelType.SIFFrom.SIFJoin.SIFJoinOn.SIFRightElement();
    }

    /**
     * Create an instance of {@link SIFExtendedQueryDataModelType.SIFSelect.SIFElement }
     * 
     */
    public SIFExtendedQueryDataModelType.SIFSelect.SIFElement createSIFExtendedQueryDataModelTypeSIFSelectSIFElement() {
        return new SIFExtendedQueryDataModelType.SIFSelect.SIFElement();
    }

    /**
     * Create an instance of {@link AbstractContentPackageType.XMLData }
     * 
     */
    public AbstractContentPackageType.XMLData createAbstractContentPackageTypeXMLData() {
        return new AbstractContentPackageType.XMLData();
    }

    /**
     * Create an instance of {@link AbstractContentPackageType.TextData }
     * 
     */
    public AbstractContentPackageType.TextData createAbstractContentPackageTypeTextData() {
        return new AbstractContentPackageType.TextData();
    }

    /**
     * Create an instance of {@link AbstractContentPackageType.BinaryData }
     * 
     */
    public AbstractContentPackageType.BinaryData createAbstractContentPackageTypeBinaryData() {
        return new AbstractContentPackageType.BinaryData();
    }

    /**
     * Create an instance of {@link AbstractContentPackageType.Reference }
     * 
     */
    public AbstractContentPackageType.Reference createAbstractContentPackageTypeReference() {
        return new AbstractContentPackageType.Reference();
    }

    /**
     * Create an instance of {@link AbstractContentElementType.XMLData }
     * 
     */
    public AbstractContentElementType.XMLData createAbstractContentElementTypeXMLData() {
        return new AbstractContentElementType.XMLData();
    }

    /**
     * Create an instance of {@link AbstractContentElementType.TextData }
     * 
     */
    public AbstractContentElementType.TextData createAbstractContentElementTypeTextData() {
        return new AbstractContentElementType.TextData();
    }

    /**
     * Create an instance of {@link AbstractContentElementType.BinaryData }
     * 
     */
    public AbstractContentElementType.BinaryData createAbstractContentElementTypeBinaryData() {
        return new AbstractContentElementType.BinaryData();
    }

    /**
     * Create an instance of {@link AbstractContentElementType.Reference }
     * 
     */
    public AbstractContentElementType.Reference createAbstractContentElementTypeReference() {
        return new AbstractContentElementType.Reference();
    }

    /**
     * Create an instance of {@link IdentityType.SIFRefId }
     * 
     */
    public IdentityType.SIFRefId createIdentityTypeSIFRefId() {
        return new IdentityType.SIFRefId();
    }

    /**
     * Create an instance of {@link IdentityType.PasswordList.Password }
     * 
     */
    public IdentityType.PasswordList.Password createIdentityTypePasswordListPassword() {
        return new IdentityType.PasswordList.Password();
    }

    /**
     * Create an instance of {@link IdentityType.IdentityAssertions.IdentityAssertion }
     * 
     */
    public IdentityType.IdentityAssertions.IdentityAssertion createIdentityTypeIdentityAssertionsIdentityAssertion() {
        return new IdentityType.IdentityAssertions.IdentityAssertion();
    }

    /**
     * Create an instance of {@link LocationType.LocationRefId }
     * 
     */
    public LocationType.LocationRefId createLocationTypeLocationRefId() {
        return new LocationType.LocationRefId();
    }

    /**
     * Create an instance of {@link LEAInfoType.EducationAgencyType }
     * 
     */
    public LEAInfoType.EducationAgencyType createLEAInfoTypeEducationAgencyType() {
        return new LEAInfoType.EducationAgencyType();
    }

    /**
     * Create an instance of {@link LEAInfoType.LEAContactList.LEAContact }
     * 
     */
    public LEAInfoType.LEAContactList.LEAContact createLEAInfoTypeLEAContactListLEAContact() {
        return new LEAInfoType.LEAContactList.LEAContact();
    }

    /**
     * Create an instance of {@link StudentScoreSetType.Scores.Score }
     * 
     */
    public StudentScoreSetType.Scores.Score createStudentScoreSetTypeScoresScore() {
        return new StudentScoreSetType.Scores.Score();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSectionType.SectionAssets }
     * 
     */
    public Sif3AssessmentSectionType.SectionAssets createSif3AssessmentSectionTypeSectionAssets() {
        return new Sif3AssessmentSectionType.SectionAssets();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSectionType.SectionItems.SectionItem }
     * 
     */
    public Sif3AssessmentSectionType.SectionItems.SectionItem createSif3AssessmentSectionTypeSectionItemsSectionItem() {
        return new Sif3AssessmentSectionType.SectionItems.SectionItem();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSectionType.SectionIdentifiers.SectionIdentifier }
     * 
     */
    public Sif3AssessmentSectionType.SectionIdentifiers.SectionIdentifier createSif3AssessmentSectionTypeSectionIdentifiersSectionIdentifier() {
        return new Sif3AssessmentSectionType.SectionIdentifiers.SectionIdentifier();
    }

    /**
     * Create an instance of {@link SIFExtendedElementsType.SIFExtendedElement }
     * 
     */
    public SIFExtendedElementsType.SIFExtendedElement createSIFExtendedElementsTypeSIFExtendedElement() {
        return new SIFExtendedElementsType.SIFExtendedElement();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType.ScoreReporting }
     * 
     */
    public Sif3AssessmentSubTestType.ScoreReporting createSif3AssessmentSubTestTypeScoreReporting() {
        return new Sif3AssessmentSubTestType.ScoreReporting();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType.AssessmentSubTestRefIds }
     * 
     */
    public Sif3AssessmentSubTestType.AssessmentSubTestRefIds createSif3AssessmentSubTestTypeAssessmentSubTestRefIds() {
        return new Sif3AssessmentSubTestType.AssessmentSubTestRefIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType.LearningStandardItemRefIds }
     * 
     */
    public Sif3AssessmentSubTestType.LearningStandardItemRefIds createSif3AssessmentSubTestTypeLearningStandardItemRefIds() {
        return new Sif3AssessmentSubTestType.LearningStandardItemRefIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType.AssessmentItems.AssessmentItem }
     * 
     */
    public Sif3AssessmentSubTestType.AssessmentItems.AssessmentItem createSif3AssessmentSubTestTypeAssessmentItemsAssessmentItem() {
        return new Sif3AssessmentSubTestType.AssessmentItems.AssessmentItem();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSubTestType.SubTestIdentifiers.SubTestIdentifier }
     * 
     */
    public Sif3AssessmentSubTestType.SubTestIdentifiers.SubTestIdentifier createSif3AssessmentSubTestTypeSubTestIdentifiersSubTestIdentifier() {
        return new Sif3AssessmentSubTestType.SubTestIdentifiers.SubTestIdentifier();
    }

    /**
     * Create an instance of {@link Sif3AssessmentType.AssessmentDescriptors }
     * 
     */
    public Sif3AssessmentType.AssessmentDescriptors createSif3AssessmentTypeAssessmentDescriptors() {
        return new Sif3AssessmentType.AssessmentDescriptors();
    }

    /**
     * Create an instance of {@link Sif3AssessmentType.LearningStandardItemRefIds }
     * 
     */
    public Sif3AssessmentType.LearningStandardItemRefIds createSif3AssessmentTypeLearningStandardItemRefIds() {
        return new Sif3AssessmentType.LearningStandardItemRefIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentType.AssessmentItemBanks.AssessmentItemBank }
     * 
     */
    public Sif3AssessmentType.AssessmentItemBanks.AssessmentItemBank createSif3AssessmentTypeAssessmentItemBanksAssessmentItemBank() {
        return new Sif3AssessmentType.AssessmentItemBanks.AssessmentItemBank();
    }

    /**
     * Create an instance of {@link Sif3AssessmentType.AssessmentIdentifiers.AssessmentIdentifier }
     * 
     */
    public Sif3AssessmentType.AssessmentIdentifiers.AssessmentIdentifier createSif3AssessmentTypeAssessmentIdentifiersAssessmentIdentifier() {
        return new Sif3AssessmentType.AssessmentIdentifiers.AssessmentIdentifier();
    }

    /**
     * Create an instance of {@link StaffPersonalType.OtherIdList.OtherId }
     * 
     */
    public StaffPersonalType.OtherIdList.OtherId createStaffPersonalTypeOtherIdListOtherId() {
        return new StaffPersonalType.OtherIdList.OtherId();
    }

    /**
     * Create an instance of {@link AssessmentPackageType.XMLData }
     * 
     */
    public AssessmentPackageType.XMLData createAssessmentPackageTypeXMLData() {
        return new AssessmentPackageType.XMLData();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSessionType.StaffPersonalRefIds }
     * 
     */
    public Sif3AssessmentSessionType.StaffPersonalRefIds createSif3AssessmentSessionTypeStaffPersonalRefIds() {
        return new Sif3AssessmentSessionType.StaffPersonalRefIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentSessionType.UnusualEvents.UnusualEvent }
     * 
     */
    public Sif3AssessmentSessionType.UnusualEvents.UnusualEvent createSif3AssessmentSessionTypeUnusualEventsUnusualEvent() {
        return new Sif3AssessmentSessionType.UnusualEvents.UnusualEvent();
    }

    /**
     * Create an instance of {@link StudentParticipationType.ManagingSchool }
     * 
     */
    public StudentParticipationType.ManagingSchool createStudentParticipationTypeManagingSchool() {
        return new StudentParticipationType.ManagingSchool();
    }

    /**
     * Create an instance of {@link StudentParticipationType.ReferralSource }
     * 
     */
    public StudentParticipationType.ReferralSource createStudentParticipationTypeReferralSource() {
        return new StudentParticipationType.ReferralSource();
    }

    /**
     * Create an instance of {@link StudentParticipationType.ProgramAvailability }
     * 
     */
    public StudentParticipationType.ProgramAvailability createStudentParticipationTypeProgramAvailability() {
        return new StudentParticipationType.ProgramAvailability();
    }

    /**
     * Create an instance of {@link StudentParticipationType.ProgramFundingSources.ProgramFundingSource }
     * 
     */
    public StudentParticipationType.ProgramFundingSources.ProgramFundingSource createStudentParticipationTypeProgramFundingSourcesProgramFundingSource() {
        return new StudentParticipationType.ProgramFundingSources.ProgramFundingSource();
    }

    /**
     * Create an instance of {@link AssessmentAdministrationType.SpecialConditions.SpecialCondition }
     * 
     */
    public AssessmentAdministrationType.SpecialConditions.SpecialCondition createAssessmentAdministrationTypeSpecialConditionsSpecialCondition() {
        return new AssessmentAdministrationType.SpecialConditions.SpecialCondition();
    }

    /**
     * Create an instance of {@link CalendarDate.CalendarDateType }
     * 
     */
    public CalendarDate.CalendarDateType createCalendarDateCalendarDateType() {
        return new CalendarDate.CalendarDateType();
    }

    /**
     * Create an instance of {@link CalendarDate.StudentAttendance }
     * 
     */
    public CalendarDate.StudentAttendance createCalendarDateStudentAttendance() {
        return new CalendarDate.StudentAttendance();
    }

    /**
     * Create an instance of {@link CalendarDate.TeacherAttendance }
     * 
     */
    public CalendarDate.TeacherAttendance createCalendarDateTeacherAttendance() {
        return new CalendarDate.TeacherAttendance();
    }

    /**
     * Create an instance of {@link CalendarDate.AdministratorAttendance }
     * 
     */
    public CalendarDate.AdministratorAttendance createCalendarDateAdministratorAttendance() {
        return new CalendarDate.AdministratorAttendance();
    }

    /**
     * Create an instance of {@link RoomInfoType.StaffList }
     * 
     */
    public RoomInfoType.StaffList createRoomInfoTypeStaffList() {
        return new RoomInfoType.StaffList();
    }

    /**
     * Create an instance of {@link StudentActivityParticipationType.RecognitionList }
     * 
     */
    public StudentActivityParticipationType.RecognitionList createStudentActivityParticipationTypeRecognitionList() {
        return new StudentActivityParticipationType.RecognitionList();
    }

    /**
     * Create an instance of {@link StudentSnapshotType.HomeEnrollment.Homeroom }
     * 
     */
    public StudentSnapshotType.HomeEnrollment.Homeroom createStudentSnapshotTypeHomeEnrollmentHomeroom() {
        return new StudentSnapshotType.HomeEnrollment.Homeroom();
    }

    /**
     * Create an instance of {@link StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice }
     * 
     */
    public StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice createStudentSnapshotTypeStudentSubjectChoiceListStudentSubjectChoice() {
        return new StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice();
    }

    /**
     * Create an instance of {@link StudentPersonalType.MostRecent }
     * 
     */
    public StudentPersonalType.MostRecent createStudentPersonalTypeMostRecent() {
        return new StudentPersonalType.MostRecent();
    }

    /**
     * Create an instance of {@link StudentPersonalType.OtherIdList.OtherId }
     * 
     */
    public StudentPersonalType.OtherIdList.OtherId createStudentPersonalTypeOtherIdListOtherId() {
        return new StudentPersonalType.OtherIdList.OtherId();
    }

    /**
     * Create an instance of {@link StudentPersonalType.MedicalAlertMessages.MedicalAlertMessage }
     * 
     */
    public StudentPersonalType.MedicalAlertMessages.MedicalAlertMessage createStudentPersonalTypeMedicalAlertMessagesMedicalAlertMessage() {
        return new StudentPersonalType.MedicalAlertMessages.MedicalAlertMessage();
    }

    /**
     * Create an instance of {@link StudentPersonalType.AlertMessages.AlertMessage }
     * 
     */
    public StudentPersonalType.AlertMessages.AlertMessage createStudentPersonalTypeAlertMessagesAlertMessage() {
        return new StudentPersonalType.AlertMessages.AlertMessage();
    }

    /**
     * Create an instance of {@link StudentActivityInfoType.StudentActivityType }
     * 
     */
    public StudentActivityInfoType.StudentActivityType createStudentActivityInfoTypeStudentActivityType() {
        return new StudentActivityInfoType.StudentActivityType();
    }

    /**
     * Create an instance of {@link StudentContactPersonalType.OtherIdList.OtherId }
     * 
     */
    public StudentContactPersonalType.OtherIdList.OtherId createStudentContactPersonalTypeOtherIdListOtherId() {
        return new StudentContactPersonalType.OtherIdList.OtherId();
    }

    /**
     * Create an instance of {@link SchoolContactListType.SchoolContact }
     * 
     */
    public SchoolContactListType.SchoolContact createSchoolContactListTypeSchoolContact() {
        return new SchoolContactListType.SchoolContact();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item.ItemAids }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item.ItemAids createSif3StudentResponseSetTypeItemsItemItemAids() {
        return new Sif3StudentResponseSetType.Items.Item.ItemAids();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem createSif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItem() {
        return new Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore createSif3StudentResponseSetTypeItemsItemTraitScoresTraitScore() {
        return new Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore();
    }

    /**
     * Create an instance of {@link Sif3StudentResponseSetType.Items.Item.Comments.Comment }
     * 
     */
    public Sif3StudentResponseSetType.Items.Item.Comments.Comment createSif3StudentResponseSetTypeItemsItemCommentsComment() {
        return new Sif3StudentResponseSetType.Items.Item.Comments.Comment();
    }

    /**
     * Create an instance of {@link TimeTableSubjectType.AcademicYearRange }
     * 
     */
    public TimeTableSubjectType.AcademicYearRange createTimeTableSubjectTypeAcademicYearRange() {
        return new TimeTableSubjectType.AcademicYearRange();
    }

    /**
     * Create an instance of {@link AssessmentSubTestType.ScoreRange }
     * 
     */
    public AssessmentSubTestType.ScoreRange createAssessmentSubTestTypeScoreRange() {
        return new AssessmentSubTestType.ScoreRange();
    }

    /**
     * Create an instance of {@link AssessmentSubTestType.AssessmentSubTestRefIds }
     * 
     */
    public AssessmentSubTestType.AssessmentSubTestRefIds createAssessmentSubTestTypeAssessmentSubTestRefIds() {
        return new AssessmentSubTestType.AssessmentSubTestRefIds();
    }

    /**
     * Create an instance of {@link AssessmentSubTestType.LearningStandardItemRefIds }
     * 
     */
    public AssessmentSubTestType.LearningStandardItemRefIds createAssessmentSubTestTypeLearningStandardItemRefIds() {
        return new AssessmentSubTestType.LearningStandardItemRefIds();
    }

    /**
     * Create an instance of {@link AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores }
     * 
     */
    public AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores createAssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScores() {
        return new AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.StandardSettingBody }
     * 
     */
    public LearningStandardItemType.StandardSettingBody createLearningStandardItemTypeStandardSettingBody() {
        return new LearningStandardItemType.StandardSettingBody();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.StandardHierarchyLevel }
     * 
     */
    public LearningStandardItemType.StandardHierarchyLevel createLearningStandardItemTypeStandardHierarchyLevel() {
        return new LearningStandardItemType.StandardHierarchyLevel();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.PredecessorItems }
     * 
     */
    public LearningStandardItemType.PredecessorItems createLearningStandardItemTypePredecessorItems() {
        return new LearningStandardItemType.PredecessorItems();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.StatementCodes }
     * 
     */
    public LearningStandardItemType.StatementCodes createLearningStandardItemTypeStatementCodes() {
        return new LearningStandardItemType.StatementCodes();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.Statements }
     * 
     */
    public LearningStandardItemType.Statements createLearningStandardItemTypeStatements() {
        return new LearningStandardItemType.Statements();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.RelatedLearningStandardItems.LearningStandardItemRefId }
     * 
     */
    public LearningStandardItemType.RelatedLearningStandardItems.LearningStandardItemRefId createLearningStandardItemTypeRelatedLearningStandardItemsLearningStandardItemRefId() {
        return new LearningStandardItemType.RelatedLearningStandardItems.LearningStandardItemRefId();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes }
     * 
     */
    public LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes createLearningStandardItemTypeStandardIdentifierAlternateIdentificationCodes() {
        return new LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes();
    }

    /**
     * Create an instance of {@link LearningStandardItemType.Resources.LearningResourceRefId }
     * 
     */
    public LearningStandardItemType.Resources.LearningResourceRefId createLearningStandardItemTypeResourcesLearningResourceRefId() {
        return new LearningStandardItemType.Resources.LearningResourceRefId();
    }

    /**
     * Create an instance of {@link StudentSDTNType.FurtherInformation }
     * 
     */
    public StudentSDTNType.FurtherInformation createStudentSDTNTypeFurtherInformation() {
        return new StudentSDTNType.FurtherInformation();
    }

    /**
     * Create an instance of {@link StudentSDTNType.PastoralCare }
     * 
     */
    public StudentSDTNType.PastoralCare createStudentSDTNTypePastoralCare() {
        return new StudentSDTNType.PastoralCare();
    }

    /**
     * Create an instance of {@link StudentSDTNType.OtherLearningAreasList.OtherLearningArea }
     * 
     */
    public StudentSDTNType.OtherLearningAreasList.OtherLearningArea createStudentSDTNTypeOtherLearningAreasListOtherLearningArea() {
        return new StudentSDTNType.OtherLearningAreasList.OtherLearningArea();
    }

    /**
     * Create an instance of {@link StudentSDTNType.AreasOfInterestList.ActivityInfo }
     * 
     */
    public StudentSDTNType.AreasOfInterestList.ActivityInfo createStudentSDTNTypeAreasOfInterestListActivityInfo() {
        return new StudentSDTNType.AreasOfInterestList.ActivityInfo();
    }

    /**
     * Create an instance of {@link StudentSDTNType.PreviousSchoolsList.PreviousSchool }
     * 
     */
    public StudentSDTNType.PreviousSchoolsList.PreviousSchool createStudentSDTNTypePreviousSchoolsListPreviousSchool() {
        return new StudentSDTNType.PreviousSchoolsList.PreviousSchool();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.FormAccommodations }
     * 
     */
    public Sif3AssessmentFormType.FormAccommodations createSif3AssessmentFormTypeFormAccommodations() {
        return new Sif3AssessmentFormType.FormAccommodations();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.AssessmentSubTestRefIds }
     * 
     */
    public Sif3AssessmentFormType.AssessmentSubTestRefIds createSif3AssessmentFormTypeAssessmentSubTestRefIds() {
        return new Sif3AssessmentFormType.AssessmentSubTestRefIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.AssessmentPlatforms }
     * 
     */
    public Sif3AssessmentFormType.AssessmentPlatforms createSif3AssessmentFormTypeAssessmentPlatforms() {
        return new Sif3AssessmentFormType.AssessmentPlatforms();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.AssessmentAssetRefIds }
     * 
     */
    public Sif3AssessmentFormType.AssessmentAssetRefIds createSif3AssessmentFormTypeAssessmentAssetRefIds() {
        return new Sif3AssessmentFormType.AssessmentAssetRefIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.AssessmentSections.AssessmentSection }
     * 
     */
    public Sif3AssessmentFormType.AssessmentSections.AssessmentSection createSif3AssessmentFormTypeAssessmentSectionsAssessmentSection() {
        return new Sif3AssessmentFormType.AssessmentSections.AssessmentSection();
    }

    /**
     * Create an instance of {@link Sif3AssessmentFormType.FormIdentifiers.FormIdentifier }
     * 
     */
    public Sif3AssessmentFormType.FormIdentifiers.FormIdentifier createSif3AssessmentFormTypeFormIdentifiersFormIdentifier() {
        return new Sif3AssessmentFormType.FormIdentifiers.FormIdentifier();
    }

    /**
     * Create an instance of {@link AssessmentItemType.LearningStandardItems }
     * 
     */
    public AssessmentItemType.LearningStandardItems createAssessmentItemTypeLearningStandardItems() {
        return new AssessmentItemType.LearningStandardItems();
    }

    /**
     * Create an instance of {@link AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores }
     * 
     */
    public AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores createAssessmentItemTypePerformanceLevelsPerformanceLevelCutScores() {
        return new AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores();
    }

    /**
     * Create an instance of {@link AssessmentItemType.ResponseChoices.Choice }
     * 
     */
    public AssessmentItemType.ResponseChoices.Choice createAssessmentItemTypeResponseChoicesChoice() {
        return new AssessmentItemType.ResponseChoices.Choice();
    }

    /**
     * Create an instance of {@link TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod }
     * 
     */
    public TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriod() {
        return new TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod();
    }

    /**
     * Create an instance of {@link SystemRoleType.SIFRefId }
     * 
     */
    public SystemRoleType.SIFRefId createSystemRoleTypeSIFRefId() {
        return new SystemRoleType.SIFRefId();
    }

    /**
     * Create an instance of {@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId }
     * 
     */
    public SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId createSystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScopeRoleScopeRefId() {
        return new SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId();
    }

    /**
     * Create an instance of {@link TimeElementType.SpanGaps.SpanGap }
     * 
     */
    public TimeElementType.SpanGaps.SpanGap createTimeElementTypeSpanGapsSpanGap() {
        return new TimeElementType.SpanGaps.SpanGap();
    }

    /**
     * Create an instance of {@link AssessmentType.AssessmentDescriptors }
     * 
     */
    public AssessmentType.AssessmentDescriptors createAssessmentTypeAssessmentDescriptors() {
        return new AssessmentType.AssessmentDescriptors();
    }

    /**
     * Create an instance of {@link EducationFilterType.LearningStandardItems }
     * 
     */
    public EducationFilterType.LearningStandardItems createEducationFilterTypeLearningStandardItems() {
        return new EducationFilterType.LearningStandardItems();
    }

    /**
     * Create an instance of {@link AddressType.Street }
     * 
     */
    public AddressType.Street createAddressTypeStreet() {
        return new AddressType.Street();
    }

    /**
     * Create an instance of {@link AddressType.MapReference }
     * 
     */
    public AddressType.MapReference createAddressTypeMapReference() {
        return new AddressType.MapReference();
    }

    /**
     * Create an instance of {@link AddressType.StatisticalAreas.StatisticalArea }
     * 
     */
    public AddressType.StatisticalAreas.StatisticalArea createAddressTypeStatisticalAreasStatisticalArea() {
        return new AddressType.StatisticalAreas.StatisticalArea();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions }
     * 
     */
    public Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions createSif3AssessmentRubricTypeScoresScoreScoreDescriptions() {
        return new Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType.Scores.Score.ScoreComments.ScoreComment }
     * 
     */
    public Sif3AssessmentRubricType.Scores.Score.ScoreComments.ScoreComment createSif3AssessmentRubricTypeScoresScoreScoreCommentsScoreComment() {
        return new Sif3AssessmentRubricType.Scores.Score.ScoreComments.ScoreComment();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRubricType.RubricIdentifiers.RubricIdentifier }
     * 
     */
    public Sif3AssessmentRubricType.RubricIdentifiers.RubricIdentifier createSif3AssessmentRubricTypeRubricIdentifiersRubricIdentifier() {
        return new Sif3AssessmentRubricType.RubricIdentifiers.RubricIdentifier();
    }

    /**
     * Create an instance of {@link AggregateStatisticFactType.Characteristics }
     * 
     */
    public AggregateStatisticFactType.Characteristics createAggregateStatisticFactTypeCharacteristics() {
        return new AggregateStatisticFactType.Characteristics();
    }

    /**
     * Create an instance of {@link ResourceUsageType.ResourceUsageContentType }
     * 
     */
    public ResourceUsageType.ResourceUsageContentType createResourceUsageTypeResourceUsageContentType() {
        return new ResourceUsageType.ResourceUsageContentType();
    }

    /**
     * Create an instance of {@link ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId }
     * 
     */
    public ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId createResourceUsageTypeResourceReportLineListResourceReportLineSIFRefId() {
        return new ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId();
    }

    /**
     * Create an instance of {@link ResourceUsageType.ResourceReportColumnList.ResourceReportColumn }
     * 
     */
    public ResourceUsageType.ResourceReportColumnList.ResourceReportColumn createResourceUsageTypeResourceReportColumnListResourceReportColumn() {
        return new ResourceUsageType.ResourceReportColumnList.ResourceReportColumn();
    }

    /**
     * Create an instance of {@link StudentContactRelationshipType.HouseholdList }
     * 
     */
    public StudentContactRelationshipType.HouseholdList createStudentContactRelationshipTypeHouseholdList() {
        return new StudentContactRelationshipType.HouseholdList();
    }

    /**
     * Create an instance of {@link StudentContactRelationshipType.ContactFlags }
     * 
     */
    public StudentContactRelationshipType.ContactFlags createStudentContactRelationshipTypeContactFlags() {
        return new StudentContactRelationshipType.ContactFlags();
    }

    /**
     * Create an instance of {@link LearningResourceType.Location }
     * 
     */
    public LearningResourceType.Location createLearningResourceTypeLocation() {
        return new LearningResourceType.Location();
    }

    /**
     * Create an instance of {@link LearningResourceType.SubjectAreas }
     * 
     */
    public LearningResourceType.SubjectAreas createLearningResourceTypeSubjectAreas() {
        return new LearningResourceType.SubjectAreas();
    }

    /**
     * Create an instance of {@link LearningResourceType.MediaTypes }
     * 
     */
    public LearningResourceType.MediaTypes createLearningResourceTypeMediaTypes() {
        return new LearningResourceType.MediaTypes();
    }

    /**
     * Create an instance of {@link LearningResourceType.LearningStandards }
     * 
     */
    public LearningResourceType.LearningStandards createLearningResourceTypeLearningStandards() {
        return new LearningResourceType.LearningStandards();
    }

    /**
     * Create an instance of {@link LearningResourceType.Components.Component.Strategies }
     * 
     */
    public LearningResourceType.Components.Component.Strategies createLearningResourceTypeComponentsComponentStrategies() {
        return new LearningResourceType.Components.Component.Strategies();
    }

    /**
     * Create an instance of {@link LearningResourceType.Components.Component.AssociatedObjects.AssociatedObject }
     * 
     */
    public LearningResourceType.Components.Component.AssociatedObjects.AssociatedObject createLearningResourceTypeComponentsComponentAssociatedObjectsAssociatedObject() {
        return new LearningResourceType.Components.Component.AssociatedObjects.AssociatedObject();
    }

    /**
     * Create an instance of {@link LearningResourceType.Evaluations.Evaluation }
     * 
     */
    public LearningResourceType.Evaluations.Evaluation createLearningResourceTypeEvaluationsEvaluation() {
        return new LearningResourceType.Evaluations.Evaluation();
    }

    /**
     * Create an instance of {@link LearningResourceType.Approvals.Approval }
     * 
     */
    public LearningResourceType.Approvals.Approval createLearningResourceTypeApprovalsApproval() {
        return new LearningResourceType.Approvals.Approval();
    }

    /**
     * Create an instance of {@link LearningResourceType.Contacts.Contact }
     * 
     */
    public LearningResourceType.Contacts.Contact createLearningResourceTypeContactsContact() {
        return new LearningResourceType.Contacts.Contact();
    }

    /**
     * Create an instance of {@link LearningStandardDocumentType.Organizations }
     * 
     */
    public LearningStandardDocumentType.Organizations createLearningStandardDocumentTypeOrganizations() {
        return new LearningStandardDocumentType.Organizations();
    }

    /**
     * Create an instance of {@link LearningStandardDocumentType.Authors }
     * 
     */
    public LearningStandardDocumentType.Authors createLearningStandardDocumentTypeAuthors() {
        return new LearningStandardDocumentType.Authors();
    }

    /**
     * Create an instance of {@link LearningStandardDocumentType.SubjectAreas }
     * 
     */
    public LearningStandardDocumentType.SubjectAreas createLearningStandardDocumentTypeSubjectAreas() {
        return new LearningStandardDocumentType.SubjectAreas();
    }

    /**
     * Create an instance of {@link LearningStandardDocumentType.Copyright }
     * 
     */
    public LearningStandardDocumentType.Copyright createLearningStandardDocumentTypeCopyright() {
        return new LearningStandardDocumentType.Copyright();
    }

    /**
     * Create an instance of {@link LearningStandardDocumentType.RelatedLearningStandards }
     * 
     */
    public LearningStandardDocumentType.RelatedLearningStandards createLearningStandardDocumentTypeRelatedLearningStandards() {
        return new LearningStandardDocumentType.RelatedLearningStandards();
    }

    /**
     * Create an instance of {@link AggregateStatisticInfoType.CalculationRule }
     * 
     */
    public AggregateStatisticInfoType.CalculationRule createAggregateStatisticInfoTypeCalculationRule() {
        return new AggregateStatisticInfoType.CalculationRule();
    }

    /**
     * Create an instance of {@link AggregateStatisticInfoType.ExclusionRules.ExclusionRule }
     * 
     */
    public AggregateStatisticInfoType.ExclusionRules.ExclusionRule createAggregateStatisticInfoTypeExclusionRulesExclusionRule() {
        return new AggregateStatisticInfoType.ExclusionRules.ExclusionRule();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.LearningStandardItems }
     * 
     */
    public Sif3AssessmentItemType.LearningStandardItems createSif3AssessmentItemTypeLearningStandardItems() {
        return new Sif3AssessmentItemType.LearningStandardItems();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.AssessmentItemAssetRefIds }
     * 
     */
    public Sif3AssessmentItemType.AssessmentItemAssetRefIds createSif3AssessmentItemTypeAssessmentItemAssetRefIds() {
        return new Sif3AssessmentItemType.AssessmentItemAssetRefIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.ItemRubricIds }
     * 
     */
    public Sif3AssessmentItemType.ItemRubricIds createSif3AssessmentItemTypeItemRubricIds() {
        return new Sif3AssessmentItemType.ItemRubricIds();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.AssessmentItemPlatforms }
     * 
     */
    public Sif3AssessmentItemType.AssessmentItemPlatforms createSif3AssessmentItemTypeAssessmentItemPlatforms() {
        return new Sif3AssessmentItemType.AssessmentItemPlatforms();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank }
     * 
     */
    public Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank createSif3AssessmentItemTypeAssessmentItemBanksAssessmentItemBank() {
        return new Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.ResponseChoices.Choice }
     * 
     */
    public Sif3AssessmentItemType.ResponseChoices.Choice createSif3AssessmentItemTypeResponseChoicesChoice() {
        return new Sif3AssessmentItemType.ResponseChoices.Choice();
    }

    /**
     * Create an instance of {@link Sif3AssessmentItemType.AssessmentIdentifiers.AssessmentIdentifier }
     * 
     */
    public Sif3AssessmentItemType.AssessmentIdentifiers.AssessmentIdentifier createSif3AssessmentItemTypeAssessmentIdentifiersAssessmentIdentifier() {
        return new Sif3AssessmentItemType.AssessmentIdentifiers.AssessmentIdentifier();
    }

    /**
     * Create an instance of {@link AddressListType.Address }
     * 
     */
    public AddressListType.Address createAddressListTypeAddress() {
        return new AddressListType.Address();
    }

    /**
     * Create an instance of {@link StudentPeriodAttendanceType.AuditInfo.CreationUser }
     * 
     */
    public StudentPeriodAttendanceType.AuditInfo.CreationUser createStudentPeriodAttendanceTypeAuditInfoCreationUser() {
        return new StudentPeriodAttendanceType.AuditInfo.CreationUser();
    }

    /**
     * Create an instance of {@link LifeCycleType.TimeElements }
     * 
     */
    public LifeCycleType.TimeElements createLifeCycleTypeTimeElements() {
        return new LifeCycleType.TimeElements();
    }

    /**
     * Create an instance of {@link LifeCycleType.ModificationHistory.Modified }
     * 
     */
    public LifeCycleType.ModificationHistory.Modified createLifeCycleTypeModificationHistoryModified() {
        return new LifeCycleType.ModificationHistory.Modified();
    }

    /**
     * Create an instance of {@link LifeCycleType.Created.Creators.Creator }
     * 
     */
    public LifeCycleType.Created.Creators.Creator createLifeCycleTypeCreatedCreatorsCreator() {
        return new LifeCycleType.Created.Creators.Creator();
    }

    /**
     * Create an instance of {@link SchoolProgramsType.SchoolProgramList.Program }
     * 
     */
    public SchoolProgramsType.SchoolProgramList.Program createSchoolProgramsTypeSchoolProgramListProgram() {
        return new SchoolProgramsType.SchoolProgramList.Program();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAdministrationType.AdministrationAssessments }
     * 
     */
    public Sif3AssessmentAdministrationType.AdministrationAssessments createSif3AssessmentAdministrationTypeAdministrationAssessments() {
        return new Sif3AssessmentAdministrationType.AdministrationAssessments();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAdministrationType.Organizations.Organization }
     * 
     */
    public Sif3AssessmentAdministrationType.Organizations.Organization createSif3AssessmentAdministrationTypeOrganizationsOrganization() {
        return new Sif3AssessmentAdministrationType.Organizations.Organization();
    }

    /**
     * Create an instance of {@link AssessmentFormType.FormNumbers }
     * 
     */
    public AssessmentFormType.FormNumbers createAssessmentFormTypeFormNumbers() {
        return new AssessmentFormType.FormNumbers();
    }

    /**
     * Create an instance of {@link AssessmentFormType.AssessmentSubTestRefIds }
     * 
     */
    public AssessmentFormType.AssessmentSubTestRefIds createAssessmentFormTypeAssessmentSubTestRefIds() {
        return new AssessmentFormType.AssessmentSubTestRefIds();
    }

    /**
     * Create an instance of {@link SIFMetadataType.TimeElements }
     * 
     */
    public SIFMetadataType.TimeElements createSIFMetadataTypeTimeElements() {
        return new SIFMetadataType.TimeElements();
    }

    /**
     * Create an instance of {@link PhoneNumberListType.PhoneNumber }
     * 
     */
    public PhoneNumberListType.PhoneNumber createPhoneNumberListTypePhoneNumber() {
        return new PhoneNumberListType.PhoneNumber();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom }
     * 
     */
    public Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentHomeroom() {
        return new Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType.TestingStatuses.TestingStatus }
     * 
     */
    public Sif3AssessmentRegistrationType.TestingStatuses.TestingStatus createSif3AssessmentRegistrationTypeTestingStatusesTestingStatus() {
        return new Sif3AssessmentRegistrationType.TestingStatuses.TestingStatus();
    }

    /**
     * Create an instance of {@link Sif3AssessmentRegistrationType.StudentSpecialEvents.StudentSpecialEvent }
     * 
     */
    public Sif3AssessmentRegistrationType.StudentSpecialEvents.StudentSpecialEvent createSif3AssessmentRegistrationTypeStudentSpecialEventsStudentSpecialEvent() {
        return new Sif3AssessmentRegistrationType.StudentSpecialEvents.StudentSpecialEvent();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType.ParentObjectRefId }
     * 
     */
    public SummaryEnrollmentInfoType.ParentObjectRefId createSummaryEnrollmentInfoTypeParentObjectRefId() {
        return new SummaryEnrollmentInfoType.ParentObjectRefId();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student }
     * 
     */
    public SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStudent() {
        return new SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff }
     * 
     */
    public SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStaff() {
        return new SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff();
    }

    /**
     * Create an instance of {@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment }
     * 
     */
    public SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollment() {
        return new SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment();
    }

    /**
     * Create an instance of {@link PersonPictureType.ParentObjectRefId }
     * 
     */
    public PersonPictureType.ParentObjectRefId createPersonPictureTypeParentObjectRefId() {
        return new PersonPictureType.ParentObjectRefId();
    }

    /**
     * Create an instance of {@link PersonPictureType.PictureSource }
     * 
     */
    public PersonPictureType.PictureSource createPersonPictureTypePictureSource() {
        return new PersonPictureType.PictureSource();
    }

    /**
     * Create an instance of {@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId }
     * 
     */
    public SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId createSIFReportObjectTypeReportInfoReportSubmitterInfoSIFRefId() {
        return new SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId();
    }

    /**
     * Create an instance of {@link OtherCodeListType.OtherCode }
     * 
     */
    public OtherCodeListType.OtherCode createOtherCodeListTypeOtherCode() {
        return new OtherCodeListType.OtherCode();
    }

    /**
     * Create an instance of {@link ReportManifestType.ReceivingAuthority }
     * 
     */
    public ReportManifestType.ReceivingAuthority createReportManifestTypeReceivingAuthority() {
        return new ReportManifestType.ReceivingAuthority();
    }

    /**
     * Create an instance of {@link ReportManifestType.ReportingPeriod }
     * 
     */
    public ReportManifestType.ReportingPeriod createReportManifestTypeReportingPeriod() {
        return new ReportManifestType.ReportingPeriod();
    }

    /**
     * Create an instance of {@link ReportManifestType.ReportDefinitionSource }
     * 
     */
    public ReportManifestType.ReportDefinitionSource createReportManifestTypeReportDefinitionSource() {
        return new ReportManifestType.ReportDefinitionSource();
    }

    /**
     * Create an instance of {@link ReportManifestType.SIFQueryGroup }
     * 
     */
    public ReportManifestType.SIFQueryGroup createReportManifestTypeSIFQueryGroup() {
        return new ReportManifestType.SIFQueryGroup();
    }

    /**
     * Create an instance of {@link ReportManifestType.ReportFormatList.ReportFormat }
     * 
     */
    public ReportManifestType.ReportFormatList.ReportFormat createReportManifestTypeReportFormatListReportFormat() {
        return new ReportManifestType.ReportFormatList.ReportFormat();
    }

    /**
     * Create an instance of {@link OtherNamesType.Name }
     * 
     */
    public OtherNamesType.Name createOtherNamesTypeName() {
        return new OtherNamesType.Name();
    }

    /**
     * Create an instance of {@link ActivityType.TechnicalRequirements }
     * 
     */
    public ActivityType.TechnicalRequirements createActivityTypeTechnicalRequirements() {
        return new ActivityType.TechnicalRequirements();
    }

    /**
     * Create an instance of {@link ActivityType.EssentialMaterials }
     * 
     */
    public ActivityType.EssentialMaterials createActivityTypeEssentialMaterials() {
        return new ActivityType.EssentialMaterials();
    }

    /**
     * Create an instance of {@link ActivityType.LearningObjectives }
     * 
     */
    public ActivityType.LearningObjectives createActivityTypeLearningObjectives() {
        return new ActivityType.LearningObjectives();
    }

    /**
     * Create an instance of {@link ActivityType.LearningStandards }
     * 
     */
    public ActivityType.LearningStandards createActivityTypeLearningStandards() {
        return new ActivityType.LearningStandards();
    }

    /**
     * Create an instance of {@link ActivityType.Prerequisites }
     * 
     */
    public ActivityType.Prerequisites createActivityTypePrerequisites() {
        return new ActivityType.Prerequisites();
    }

    /**
     * Create an instance of {@link ActivityType.Students }
     * 
     */
    public ActivityType.Students createActivityTypeStudents() {
        return new ActivityType.Students();
    }

    /**
     * Create an instance of {@link ActivityType.Evaluation }
     * 
     */
    public ActivityType.Evaluation createActivityTypeEvaluation() {
        return new ActivityType.Evaluation();
    }

    /**
     * Create an instance of {@link ActivityType.LearningResources }
     * 
     */
    public ActivityType.LearningResources createActivityTypeLearningResources() {
        return new ActivityType.LearningResources();
    }

    /**
     * Create an instance of {@link ActivityType.ActivityTime.Duration }
     * 
     */
    public ActivityType.ActivityTime.Duration createActivityTypeActivityTimeDuration() {
        return new ActivityType.ActivityTime.Duration();
    }

    /**
     * Create an instance of {@link ActivityType.SourceObjects.SourceObject }
     * 
     */
    public ActivityType.SourceObjects.SourceObject createActivityTypeSourceObjectsSourceObject() {
        return new ActivityType.SourceObjects.SourceObject();
    }

    /**
     * Create an instance of {@link ActivityType.SoftwareRequirementList.SoftwareRequirement }
     * 
     */
    public ActivityType.SoftwareRequirementList.SoftwareRequirement createActivityTypeSoftwareRequirementListSoftwareRequirement() {
        return new ActivityType.SoftwareRequirementList.SoftwareRequirement();
    }

    /**
     * Create an instance of {@link AssessmentRegistrationType.AssessmentStudentSnapshot }
     * 
     */
    public AssessmentRegistrationType.AssessmentStudentSnapshot createAssessmentRegistrationTypeAssessmentStudentSnapshot() {
        return new AssessmentRegistrationType.AssessmentStudentSnapshot();
    }

    /**
     * Create an instance of {@link AssessmentRegistrationType.StudentSpecialConditions.StudentSpecialCondition }
     * 
     */
    public AssessmentRegistrationType.StudentSpecialConditions.StudentSpecialCondition createAssessmentRegistrationTypeStudentSpecialConditionsStudentSpecialCondition() {
        return new AssessmentRegistrationType.StudentSpecialConditions.StudentSpecialCondition();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue }
     * 
     */
    public Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue createSif3AssessmentScoreTableTypeScoreValuesScoreValueDerivedValue() {
        return new Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.DerivedValue();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback }
     * 
     */
    public Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback createSif3AssessmentScoreTableTypeScoreValuesScoreValueFeedbackListFeedback() {
        return new Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback();
    }

    /**
     * Create an instance of {@link Sif3AssessmentScoreTableType.ScoreTableIdentifiers.ScoreTableIdentifier }
     * 
     */
    public Sif3AssessmentScoreTableType.ScoreTableIdentifiers.ScoreTableIdentifier createSif3AssessmentScoreTableTypeScoreTableIdentifiersScoreTableIdentifier() {
        return new Sif3AssessmentScoreTableType.ScoreTableIdentifiers.ScoreTableIdentifier();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.EntryType }
     * 
     */
    public StudentSchoolEnrollmentType.EntryType createStudentSchoolEnrollmentTypeEntryType() {
        return new StudentSchoolEnrollmentType.EntryType();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.Homeroom }
     * 
     */
    public StudentSchoolEnrollmentType.Homeroom createStudentSchoolEnrollmentTypeHomeroom() {
        return new StudentSchoolEnrollmentType.Homeroom();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.Advisor }
     * 
     */
    public StudentSchoolEnrollmentType.Advisor createStudentSchoolEnrollmentTypeAdvisor() {
        return new StudentSchoolEnrollmentType.Advisor();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.Counselor }
     * 
     */
    public StudentSchoolEnrollmentType.Counselor createStudentSchoolEnrollmentTypeCounselor() {
        return new StudentSchoolEnrollmentType.Counselor();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.Calendar }
     * 
     */
    public StudentSchoolEnrollmentType.Calendar createStudentSchoolEnrollmentTypeCalendar() {
        return new StudentSchoolEnrollmentType.Calendar();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.ExitStatus }
     * 
     */
    public StudentSchoolEnrollmentType.ExitStatus createStudentSchoolEnrollmentTypeExitStatus() {
        return new StudentSchoolEnrollmentType.ExitStatus();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.ExitType }
     * 
     */
    public StudentSchoolEnrollmentType.ExitType createStudentSchoolEnrollmentTypeExitType() {
        return new StudentSchoolEnrollmentType.ExitType();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.CatchmentStatus }
     * 
     */
    public StudentSchoolEnrollmentType.CatchmentStatus createStudentSchoolEnrollmentTypeCatchmentStatus() {
        return new StudentSchoolEnrollmentType.CatchmentStatus();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.PromotionInfo }
     * 
     */
    public StudentSchoolEnrollmentType.PromotionInfo createStudentSchoolEnrollmentTypePromotionInfo() {
        return new StudentSchoolEnrollmentType.PromotionInfo();
    }

    /**
     * Create an instance of {@link StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice }
     * 
     */
    public StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice createStudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoice() {
        return new StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice();
    }

    /**
     * Create an instance of {@link SectionInfoType.MediumOfInstruction }
     * 
     */
    public SectionInfoType.MediumOfInstruction createSectionInfoTypeMediumOfInstruction() {
        return new SectionInfoType.MediumOfInstruction();
    }

    /**
     * Create an instance of {@link SectionInfoType.LanguageOfInstruction }
     * 
     */
    public SectionInfoType.LanguageOfInstruction createSectionInfoTypeLanguageOfInstruction() {
        return new SectionInfoType.LanguageOfInstruction();
    }

    /**
     * Create an instance of {@link SectionInfoType.LocationOfInstruction }
     * 
     */
    public SectionInfoType.LocationOfInstruction createSectionInfoTypeLocationOfInstruction() {
        return new SectionInfoType.LocationOfInstruction();
    }

    /**
     * Create an instance of {@link SectionInfoType.SchoolCourseInfoOverride }
     * 
     */
    public SectionInfoType.SchoolCourseInfoOverride createSectionInfoTypeSchoolCourseInfoOverride() {
        return new SectionInfoType.SchoolCourseInfoOverride();
    }

    /**
     * Create an instance of {@link StaffAssignmentType.StaffActivity }
     * 
     */
    public StaffAssignmentType.StaffActivity createStaffAssignmentTypeStaffActivity() {
        return new StaffAssignmentType.StaffActivity();
    }

    /**
     * Create an instance of {@link StaffAssignmentType.StaffSubjectList.StaffSubject }
     * 
     */
    public StaffAssignmentType.StaffSubjectList.StaffSubject createStaffAssignmentTypeStaffSubjectListStaffSubject() {
        return new StaffAssignmentType.StaffSubjectList.StaffSubject();
    }

    /**
     * Create an instance of {@link EmailListType.Email }
     * 
     */
    public EmailListType.Email createEmailListTypeEmail() {
        return new EmailListType.Email();
    }

    /**
     * Create an instance of {@link TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod }
     * 
     */
    public TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod createTeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriod() {
        return new TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod();
    }

    /**
     * Create an instance of {@link TeachingGroupType.TeacherList.TeachingGroupTeacher }
     * 
     */
    public TeachingGroupType.TeacherList.TeachingGroupTeacher createTeachingGroupTypeTeacherListTeachingGroupTeacher() {
        return new TeachingGroupType.TeacherList.TeachingGroupTeacher();
    }

    /**
     * Create an instance of {@link TeachingGroupType.StudentList.TeachingGroupStudent }
     * 
     */
    public TeachingGroupType.StudentList.TeachingGroupStudent createTeachingGroupTypeStudentListTeachingGroupStudent() {
        return new TeachingGroupType.StudentList.TeachingGroupStudent();
    }

    /**
     * Create an instance of {@link DemographicsType.CountriesOfCitizenship }
     * 
     */
    public DemographicsType.CountriesOfCitizenship createDemographicsTypeCountriesOfCitizenship() {
        return new DemographicsType.CountriesOfCitizenship();
    }

    /**
     * Create an instance of {@link DemographicsType.CountriesOfResidency }
     * 
     */
    public DemographicsType.CountriesOfResidency createDemographicsTypeCountriesOfResidency() {
        return new DemographicsType.CountriesOfResidency();
    }

    /**
     * Create an instance of {@link DemographicsType.DwellingArrangement }
     * 
     */
    public DemographicsType.DwellingArrangement createDemographicsTypeDwellingArrangement() {
        return new DemographicsType.DwellingArrangement();
    }

    /**
     * Create an instance of {@link DemographicsType.Religion }
     * 
     */
    public DemographicsType.Religion createDemographicsTypeReligion() {
        return new DemographicsType.Religion();
    }

    /**
     * Create an instance of {@link DemographicsType.ReligiousEventList.ReligiousEvent }
     * 
     */
    public DemographicsType.ReligiousEventList.ReligiousEvent createDemographicsTypeReligiousEventListReligiousEvent() {
        return new DemographicsType.ReligiousEventList.ReligiousEvent();
    }

    /**
     * Create an instance of {@link LanguageListType.Language }
     * 
     */
    public LanguageListType.Language createLanguageListTypeLanguage() {
        return new LanguageListType.Language();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAssetType.AssetLearningStandards }
     * 
     */
    public Sif3AssessmentAssetType.AssetLearningStandards createSif3AssessmentAssetTypeAssetLearningStandards() {
        return new Sif3AssessmentAssetType.AssetLearningStandards();
    }

    /**
     * Create an instance of {@link Sif3AssessmentAssetType.AssetIdentifiers.AssetIdentifier }
     * 
     */
    public Sif3AssessmentAssetType.AssetIdentifiers.AssetIdentifier createSif3AssessmentAssetTypeAssetIdentifiersAssetIdentifier() {
        return new Sif3AssessmentAssetType.AssetIdentifiers.AssetIdentifier();
    }

    /**
     * Create an instance of {@link SIFQueryType.SIFQueryObject }
     * 
     */
    public SIFQueryType.SIFQueryObject createSIFQueryTypeSIFQueryObject() {
        return new SIFQueryType.SIFQueryObject();
    }

    /**
     * Create an instance of {@link SIFQueryType.SIFConditionGroup.SIFConditions.SIFCondition }
     * 
     */
    public SIFQueryType.SIFConditionGroup.SIFConditions.SIFCondition createSIFQueryTypeSIFConditionGroupSIFConditionsSIFCondition() {
        return new SIFQueryType.SIFConditionGroup.SIFConditions.SIFCondition();
    }

    /**
     * Create an instance of {@link SchoolInfoType.OtherLEA }
     * 
     */
    public SchoolInfoType.OtherLEA createSchoolInfoTypeOtherLEA() {
        return new SchoolInfoType.OtherLEA();
    }

    /**
     * Create an instance of {@link SchoolInfoType.SchoolFocusList }
     * 
     */
    public SchoolInfoType.SchoolFocusList createSchoolInfoTypeSchoolFocusList() {
        return new SchoolInfoType.SchoolFocusList();
    }

    /**
     * Create an instance of {@link SchoolInfoType.Campus }
     * 
     */
    public SchoolInfoType.Campus createSchoolInfoTypeCampus() {
        return new SchoolInfoType.Campus();
    }

    /**
     * Create an instance of {@link SchoolInfoType.SchoolGroupList }
     * 
     */
    public SchoolInfoType.SchoolGroupList createSchoolInfoTypeSchoolGroupList() {
        return new SchoolInfoType.SchoolGroupList();
    }

    /**
     * Create an instance of {@link SchoolInfoType.OtherIdList.OtherId }
     * 
     */
    public SchoolInfoType.OtherIdList.OtherId createSchoolInfoTypeOtherIdListOtherId() {
        return new SchoolInfoType.OtherIdList.OtherId();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentPackageType.XMLData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "XMLData", scope = AssessmentPackageType.class)
    public JAXBElement<AssessmentPackageType.XMLData> createAssessmentPackageTypeXMLData(AssessmentPackageType.XMLData value) {
        return new JAXBElement<AssessmentPackageType.XMLData>(_AssessmentPackageTypeXMLData_QNAME, AssessmentPackageType.XMLData.class, AssessmentPackageType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AssessmentPackageType.class)
    public JAXBElement<SIFMetadataType> createAssessmentPackageTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AssessmentPackageType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AssessmentPackageType.class)
    public JAXBElement<SIFExtendedElementsType> createAssessmentPackageTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AssessmentPackageType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LanguageList")
    public JAXBElement<LanguageListType> createLanguageList(LanguageListType value) {
        return new JAXBElement<LanguageListType>(_LanguageList_QNAME, LanguageListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentAsset")
    public JAXBElement<Sif3AssessmentAssetType> createSif3AssessmentAsset(Sif3AssessmentAssetType value) {
        return new JAXBElement<Sif3AssessmentAssetType>(_Sif3AssessmentAsset_QNAME, Sif3AssessmentAssetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemographicsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Demographics")
    public JAXBElement<DemographicsType> createDemographics(DemographicsType value) {
        return new JAXBElement<DemographicsType>(_Demographics_QNAME, DemographicsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemAssociationCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentItemAssociations")
    public JAXBElement<Sif3AssessmentItemAssociationCollectionType> createSif3AssessmentItemAssociations(Sif3AssessmentItemAssociationCollectionType value) {
        return new JAXBElement<Sif3AssessmentItemAssociationCollectionType>(_Sif3AssessmentItemAssociations_QNAME, Sif3AssessmentItemAssociationCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfo")
    public JAXBElement<SchoolInfoType> createSchoolInfo(SchoolInfoType value) {
        return new JAXBElement<SchoolInfoType>(_SchoolInfo_QNAME, SchoolInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFQueryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Query")
    public JAXBElement<SIFQueryType> createSIFQuery(SIFQueryType value) {
        return new JAXBElement<SIFQueryType>(_SIFQuery_QNAME, SIFQueryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProjectedGraduationYear")
    public JAXBElement<XMLGregorianCalendar> createProjectedGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ProjectedGraduationYear_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentScoreSetCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentScoreSets")
    public JAXBElement<StudentScoreSetCollectionType> createStudentScoreSets(StudentScoreSetCollectionType value) {
        return new JAXBElement<StudentScoreSetCollectionType>(_StudentScoreSets_QNAME, StudentScoreSetCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourcePackageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningResourcePackage")
    public JAXBElement<LearningResourcePackageType> createLearningResourcePackage(LearningResourcePackageType value) {
        return new JAXBElement<LearningResourcePackageType>(_LearningResourcePackage_QNAME, LearningResourcePackageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportAuthorityInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportAuthorityInfos")
    public JAXBElement<ReportAuthorityInfoCollectionType> createReportAuthorityInfos(ReportAuthorityInfoCollectionType value) {
        return new JAXBElement<ReportAuthorityInfoCollectionType>(_ReportAuthorityInfos_QNAME, ReportAuthorityInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SectionInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionInfo")
    public JAXBElement<SectionInfoType> createSectionInfo(SectionInfoType value) {
        return new JAXBElement<SectionInfoType>(_SectionInfo_QNAME, SectionInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemAssociationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentItemAssociation")
    public JAXBElement<Sif3AssessmentItemAssociationType> createSif3AssessmentItemAssociation(Sif3AssessmentItemAssociationType value) {
        return new JAXBElement<Sif3AssessmentItemAssociationType>(_Sif3AssessmentItemAssociation_QNAME, Sif3AssessmentItemAssociationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Context")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSIFContext(String value) {
        return new JAXBElement<String>(_SIFContext_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TeachingGroupType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeachingGroup")
    public JAXBElement<TeachingGroupType> createTeachingGroup(TeachingGroupType value) {
        return new JAXBElement<TeachingGroupType>(_TeachingGroup_QNAME, TeachingGroupType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarSummaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CalendarSummary")
    public JAXBElement<CalendarSummaryType> createCalendarSummary(CalendarSummaryType value) {
        return new JAXBElement<CalendarSummaryType>(_CalendarSummary_QNAME, CalendarSummaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSnapshotCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSnapshots")
    public JAXBElement<StudentSnapshotCollectionType> createStudentSnapshots(StudentSnapshotCollectionType value) {
        return new JAXBElement<StudentSnapshotCollectionType>(_StudentSnapshots_QNAME, StudentSnapshotCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentPersonals")
    public JAXBElement<StudentCollectionType> createStudentPersonals(StudentCollectionType value) {
        return new JAXBElement<StudentCollectionType>(_StudentPersonals_QNAME, StudentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TermInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TermInfos")
    public JAXBElement<TermInfoCollectionType> createTermInfos(TermInfoCollectionType value) {
        return new JAXBElement<TermInfoCollectionType>(_TermInfos_QNAME, TermInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSubTestCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentSubTests")
    public JAXBElement<Sif3AssessmentSubTestCollectionType> createSif3AssessmentSubTests(Sif3AssessmentSubTestCollectionType value) {
        return new JAXBElement<Sif3AssessmentSubTestCollectionType>(_Sif3AssessmentSubTests_QNAME, Sif3AssessmentSubTestCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectArea")
    public JAXBElement<SubjectAreaType> createSubjectArea(SubjectAreaType value) {
        return new JAXBElement<SubjectAreaType>(_SubjectArea_QNAME, SubjectAreaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmailList")
    public JAXBElement<EmailListType> createEmailList(EmailListType value) {
        return new JAXBElement<EmailListType>(_EmailList_QNAME, EmailListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaffAssignmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffAssignment")
    public JAXBElement<StaffAssignmentType> createStaffAssignment(StaffAssignmentType value) {
        return new JAXBElement<StaffAssignmentType>(_StaffAssignment_QNAME, StaffAssignmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSessionCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentSessions")
    public JAXBElement<Sif3AssessmentSessionCollectionType> createSif3AssessmentSessions(Sif3AssessmentSessionCollectionType value) {
        return new JAXBElement<Sif3AssessmentSessionCollectionType>(_Sif3AssessmentSessions_QNAME, Sif3AssessmentSessionCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumber")
    public JAXBElement<PhoneNumberType> createPhoneNumber(PhoneNumberType value) {
        return new JAXBElement<PhoneNumberType>(_PhoneNumber_QNAME, PhoneNumberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TeachingGroupCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeachingGroups")
    public JAXBElement<TeachingGroupCollectionType> createTeachingGroups(TeachingGroupCollectionType value) {
        return new JAXBElement<TeachingGroupCollectionType>(_TeachingGroups_QNAME, TeachingGroupCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Country")
    public JAXBElement<String> createCountry(String value) {
        return new JAXBElement<String>(_Country_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectAreaList")
    public JAXBElement<SubjectAreaListType> createSubjectAreaList(SubjectAreaListType value) {
        return new JAXBElement<SubjectAreaListType>(_SubjectAreaList_QNAME, SubjectAreaListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear")
    public JAXBElement<XMLGregorianCalendar> createSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolCourseInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCourseInfos")
    public JAXBElement<SchoolCourseInfoCollectionType> createSchoolCourseInfos(SchoolCourseInfoCollectionType value) {
        return new JAXBElement<SchoolCourseInfoCollectionType>(_SchoolCourseInfos_QNAME, SchoolCourseInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSchoolEnrollment")
    public JAXBElement<StudentSchoolEnrollmentType> createStudentSchoolEnrollment(StudentSchoolEnrollmentType value) {
        return new JAXBElement<StudentSchoolEnrollmentType>(_StudentSchoolEnrollment_QNAME, StudentSchoolEnrollmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentRegistrationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentRegistration")
    public JAXBElement<AssessmentRegistrationType> createAssessmentRegistration(AssessmentRegistrationType value) {
        return new JAXBElement<AssessmentRegistrationType>(_AssessmentRegistration_QNAME, AssessmentRegistrationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentScoreTable")
    public JAXBElement<Sif3AssessmentScoreTableType> createSif3AssessmentScoreTable(Sif3AssessmentScoreTableType value) {
        return new JAXBElement<Sif3AssessmentScoreTableType>(_Sif3AssessmentScoreTable_QNAME, Sif3AssessmentScoreTableType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentFormCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentForms")
    public JAXBElement<AssessmentFormCollectionType> createAssessmentForms(AssessmentFormCollectionType value) {
        return new JAXBElement<AssessmentFormCollectionType>(_AssessmentForms_QNAME, AssessmentFormCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentRegistrationCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentRegistrations")
    public JAXBElement<AssessmentRegistrationCollectionType> createAssessmentRegistrations(AssessmentRegistrationCollectionType value) {
        return new JAXBElement<AssessmentRegistrationCollectionType>(_AssessmentRegistrations_QNAME, AssessmentRegistrationCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentActivityInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentActivityInfos")
    public JAXBElement<StudentActivityInfoCollectionType> createStudentActivityInfos(StudentActivityInfoCollectionType value) {
        return new JAXBElement<StudentActivityInfoCollectionType>(_StudentActivityInfos_QNAME, StudentActivityInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTableSubjectCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableSubjects")
    public JAXBElement<TimeTableSubjectCollectionType> createTimeTableSubjects(TimeTableSubjectCollectionType value) {
        return new JAXBElement<TimeTableSubjectCollectionType>(_TimeTableSubjects_QNAME, TimeTableSubjectCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HomeroomNumber")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createHomeroomNumber(String value) {
        return new JAXBElement<String>(_HomeroomNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherNamesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherNames")
    public JAXBElement<OtherNamesType> createOtherNames(OtherNamesType value) {
        return new JAXBElement<OtherNamesType>(_OtherNames_QNAME, OtherNamesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Activity")
    public JAXBElement<ActivityType> createActivity(ActivityType value) {
        return new JAXBElement<ActivityType>(_Activity_QNAME, ActivityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Email")
    public JAXBElement<EmailType> createEmail(EmailType value) {
        return new JAXBElement<EmailType>(_Email_QNAME, EmailType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaffCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonals")
    public JAXBElement<StaffCollectionType> createStaffPersonals(StaffCollectionType value) {
        return new JAXBElement<StaffCollectionType>(_StaffPersonals_QNAME, StaffCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRubricCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentRubrics")
    public JAXBElement<Sif3AssessmentRubricCollectionType> createSif3AssessmentRubrics(Sif3AssessmentRubricCollectionType value) {
        return new JAXBElement<Sif3AssessmentRubricCollectionType>(_Sif3AssessmentRubrics_QNAME, Sif3AssessmentRubricCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList")
    public JAXBElement<OtherCodeListType> createOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentAdministrationCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentAdministrations")
    public JAXBElement<AssessmentAdministrationCollectionType> createAssessmentAdministrations(AssessmentAdministrationCollectionType value) {
        return new JAXBElement<AssessmentAdministrationCollectionType>(_AssessmentAdministrations_QNAME, AssessmentAdministrationCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentDailyAttendanceCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentDailyAttendances")
    public JAXBElement<StudentDailyAttendanceCollectionType> createStudentDailyAttendances(StudentDailyAttendanceCollectionType value) {
        return new JAXBElement<StudentDailyAttendanceCollectionType>(_StudentDailyAttendances_QNAME, StudentDailyAttendanceCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFReportObjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ReportObject")
    public JAXBElement<SIFReportObjectType> createSIFReportObject(SIFReportObjectType value) {
        return new JAXBElement<SIFReportObjectType>(_SIFReportObject_QNAME, SIFReportObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonPictureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PersonPicture")
    public JAXBElement<PersonPictureType> createPersonPicture(PersonPictureType value) {
        return new JAXBElement<PersonPictureType>(_PersonPicture_QNAME, PersonPictureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportManifestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportManifest")
    public JAXBElement<ReportManifestType> createReportManifest(ReportManifestType value) {
        return new JAXBElement<ReportManifestType>(_ReportManifest_QNAME, ReportManifestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentParticipationCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentParticipations")
    public JAXBElement<StudentParticipationCollectionType> createStudentParticipations(StudentParticipationCollectionType value) {
        return new JAXBElement<StudentParticipationCollectionType>(_StudentParticipations_QNAME, StudentParticipationCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvince")
    public JAXBElement<String> createStateProvince(String value) {
        return new JAXBElement<String>(_StateProvince_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentFormType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentForm")
    public JAXBElement<AssessmentFormType> createAssessmentForm(AssessmentFormType value) {
        return new JAXBElement<AssessmentFormType>(_AssessmentForm_QNAME, AssessmentFormType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentAttendanceSummaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentAttendanceSummary")
    public JAXBElement<StudentAttendanceSummaryType> createStudentAttendanceSummary(StudentAttendanceSummaryType value) {
        return new JAXBElement<StudentAttendanceSummaryType>(_StudentAttendanceSummary_QNAME, StudentAttendanceSummaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateStatisticFactCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AggregateStatisticFacts")
    public JAXBElement<AggregateStatisticFactCollectionType> createAggregateStatisticFacts(AggregateStatisticFactCollectionType value) {
        return new JAXBElement<AggregateStatisticFactCollectionType>(_AggregateStatisticFacts_QNAME, AggregateStatisticFactCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAdministrationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentAdministration")
    public JAXBElement<Sif3AssessmentAdministrationType> createSif3AssessmentAdministration(Sif3AssessmentAdministrationType value) {
        return new JAXBElement<Sif3AssessmentAdministrationType>(_Sif3AssessmentAdministration_QNAME, Sif3AssessmentAdministrationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolProgramsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolPrograms")
    public JAXBElement<SchoolProgramsType> createSchoolPrograms(SchoolProgramsType value) {
        return new JAXBElement<SchoolProgramsType>(_SchoolPrograms_QNAME, SchoolProgramsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LifeCycleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LifeCycle")
    public JAXBElement<LifeCycleType> createLifeCycle(LifeCycleType value) {
        return new JAXBElement<LifeCycleType>(_LifeCycle_QNAME, LifeCycleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPeriodAttendanceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentPeriodAttendance")
    public JAXBElement<StudentPeriodAttendanceType> createStudentPeriodAttendance(StudentPeriodAttendanceType value) {
        return new JAXBElement<StudentPeriodAttendanceType>(_StudentPeriodAttendance_QNAME, StudentPeriodAttendanceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportManifestCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportManifests")
    public JAXBElement<ReportManifestCollectionType> createReportManifests(ReportManifestCollectionType value) {
        return new JAXBElement<ReportManifestCollectionType>(_ReportManifests_QNAME, ReportManifestCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SummaryEnrollmentInfo")
    public JAXBElement<SummaryEnrollmentInfoType> createSummaryEnrollmentInfo(SummaryEnrollmentInfoType value) {
        return new JAXBElement<SummaryEnrollmentInfoType>(_SummaryEnrollmentInfo_QNAME, SummaryEnrollmentInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SummaryEnrollmentInfos")
    public JAXBElement<SummaryEnrollmentInfoCollectionType> createSummaryEnrollmentInfos(SummaryEnrollmentInfoCollectionType value) {
        return new JAXBElement<SummaryEnrollmentInfoCollectionType>(_SummaryEnrollmentInfos_QNAME, SummaryEnrollmentInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentContactRelationshipCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentContactRelationships")
    public JAXBElement<StudentContactRelationshipCollectionType> createStudentContactRelationships(StudentContactRelationshipCollectionType value) {
        return new JAXBElement<StudentContactRelationshipCollectionType>(_StudentContactRelationships_QNAME, StudentContactRelationshipCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRegistrationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentRegistration")
    public JAXBElement<Sif3AssessmentRegistrationType> createSif3AssessmentRegistration(Sif3AssessmentRegistrationType value) {
        return new JAXBElement<Sif3AssessmentRegistrationType>(_Sif3AssessmentRegistration_QNAME, Sif3AssessmentRegistrationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumberList")
    public JAXBElement<PhoneNumberListType> createPhoneNumberList(PhoneNumberListType value) {
        return new JAXBElement<PhoneNumberListType>(_PhoneNumberList_QNAME, PhoneNumberListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaffAssignmentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffAssignments")
    public JAXBElement<StaffAssignmentCollectionType> createStaffAssignments(StaffAssignmentCollectionType value) {
        return new JAXBElement<StaffAssignmentCollectionType>(_StaffAssignments_QNAME, StaffAssignmentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata")
    public JAXBElement<SIFMetadataType> createSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarSummaryCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CalendarSummarys")
    public JAXBElement<CalendarSummaryCollectionType> createCalendarSummarys(CalendarSummaryCollectionType value) {
        return new JAXBElement<CalendarSummaryCollectionType>(_CalendarSummarys_QNAME, CalendarSummaryCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentContactRelationshipType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentContactRelationship")
    public JAXBElement<StudentContactRelationshipType> createStudentContactRelationship(StudentContactRelationshipType value) {
        return new JAXBElement<StudentContactRelationshipType>(_StudentContactRelationship_QNAME, StudentContactRelationshipType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_AuthenticationLevel")
    public JAXBElement<Long> createSIFAuthenticationLevel(Long value) {
        return new JAXBElement<Long>(_SIFAuthenticationLevel_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateCharacteristicInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AggregateCharacteristicInfo")
    public JAXBElement<AggregateCharacteristicInfoType> createAggregateCharacteristicInfo(AggregateCharacteristicInfoType value) {
        return new JAXBElement<AggregateCharacteristicInfoType>(_AggregateCharacteristicInfo_QNAME, AggregateCharacteristicInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PublishInDirectory")
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createPublishInDirectory(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_PublishInDirectory_QNAME, AUCodeSetsYesOrNoCategoryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceUsageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ResourceUsage")
    public JAXBElement<ResourceUsageType> createResourceUsage(ResourceUsageType value) {
        return new JAXBElement<ResourceUsageType>(_ResourceUsage_QNAME, ResourceUsageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Identitys")
    public JAXBElement<IdentityCollectionType> createIdentitys(IdentityCollectionType value) {
        return new JAXBElement<IdentityCollectionType>(_Identitys_QNAME, IdentityCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateStatisticFactType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AggregateStatisticFact")
    public JAXBElement<AggregateStatisticFactType> createAggregateStatisticFact(AggregateStatisticFactType value) {
        return new JAXBElement<AggregateStatisticFactType>(_AggregateStatisticFact_QNAME, AggregateStatisticFactType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateCharacteristicInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AggregateCharacteristicInfos")
    public JAXBElement<AggregateCharacteristicInfoCollectionType> createAggregateCharacteristicInfos(AggregateCharacteristicInfoCollectionType value) {
        return new JAXBElement<AggregateCharacteristicInfoCollectionType>(_AggregateCharacteristicInfos_QNAME, AggregateCharacteristicInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AddressList")
    public JAXBElement<AddressListType> createAddressList(AddressListType value) {
        return new JAXBElement<AddressListType>(_AddressList_QNAME, AddressListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentItem")
    public JAXBElement<Sif3AssessmentItemType> createSif3AssessmentItem(Sif3AssessmentItemType value) {
        return new JAXBElement<Sif3AssessmentItemType>(_Sif3AssessmentItem_QNAME, Sif3AssessmentItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateStatisticInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AggregateStatisticInfo")
    public JAXBElement<AggregateStatisticInfoType> createAggregateStatisticInfo(AggregateStatisticInfoType value) {
        return new JAXBElement<AggregateStatisticInfoType>(_AggregateStatisticInfo_QNAME, AggregateStatisticInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardDocumentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardDocument")
    public JAXBElement<LearningStandardDocumentType> createLearningStandardDocument(LearningStandardDocumentType value) {
        return new JAXBElement<LearningStandardDocumentType>(_LearningStandardDocument_QNAME, LearningStandardDocumentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourcePackageCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningResourcePackages")
    public JAXBElement<LearningResourcePackageCollectionType> createLearningResourcePackages(LearningResourcePackageCollectionType value) {
        return new JAXBElement<LearningResourcePackageCollectionType>(_LearningResourcePackages_QNAME, LearningResourcePackageCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningResource")
    public JAXBElement<LearningResourceType> createLearningResource(LearningResourceType value) {
        return new JAXBElement<LearningResourceType>(_LearningResource_QNAME, LearningResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttendanceCodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AttendanceCode")
    public JAXBElement<AttendanceCodeType> createAttendanceCode(AttendanceCodeType value) {
        return new JAXBElement<AttendanceCodeType>(_AttendanceCode_QNAME, AttendanceCodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_EncryptionLevel")
    public JAXBElement<Long> createSIFEncryptionLevel(Long value) {
        return new JAXBElement<Long>(_SIFEncryptionLevel_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ACStrandSubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ACStrandSubjectArea")
    public JAXBElement<ACStrandSubjectAreaType> createACStrandSubjectArea(ACStrandSubjectAreaType value) {
        return new JAXBElement<ACStrandSubjectAreaType>(_ACStrandSubjectArea_QNAME, ACStrandSubjectAreaType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceUsageCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ResourceUsages")
    public JAXBElement<ResourceUsageCollectionType> createResourceUsages(ResourceUsageCollectionType value) {
        return new JAXBElement<ResourceUsageCollectionType>(_ResourceUsages_QNAME, ResourceUsageCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EducationalLevel")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createEducationalLevel(String value) {
        return new JAXBElement<String>(_EducationalLevel_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EducationFilterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EducationFilter")
    public JAXBElement<EducationFilterType> createEducationFilter(EducationFilterType value) {
        return new JAXBElement<EducationFilterType>(_EducationFilter_QNAME, EducationFilterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentScoreTables")
    public JAXBElement<Sif3AssessmentScoreTableCollectionType> createSif3AssessmentScoreTables(Sif3AssessmentScoreTableCollectionType value) {
        return new JAXBElement<Sif3AssessmentScoreTableCollectionType>(_Sif3AssessmentScoreTables_QNAME, Sif3AssessmentScoreTableCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateStatisticInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AggregateStatisticInfos")
    public JAXBElement<AggregateStatisticInfoCollectionType> createAggregateStatisticInfos(AggregateStatisticInfoCollectionType value) {
        return new JAXBElement<AggregateStatisticInfoCollectionType>(_AggregateStatisticInfos_QNAME, AggregateStatisticInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElectronicIdListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ElectronicIdList")
    public JAXBElement<ElectronicIdListType> createElectronicIdList(ElectronicIdListType value) {
        return new JAXBElement<ElectronicIdListType>(_ElectronicIdList_QNAME, ElectronicIdListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRubricType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentRubric")
    public JAXBElement<Sif3AssessmentRubricType> createSif3AssessmentRubric(Sif3AssessmentRubricType value) {
        return new JAXBElement<Sif3AssessmentRubricType>(_Sif3AssessmentRubric_QNAME, Sif3AssessmentRubricType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElectronicIdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ElectronicId")
    public JAXBElement<ElectronicIdType> createElectronicId(ElectronicIdType value) {
        return new JAXBElement<ElectronicIdType>(_ElectronicId_QNAME, ElectronicIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address")
    public JAXBElement<AddressType> createAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTableType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTable")
    public JAXBElement<TimeTableType> createTimeTable(TimeTableType value) {
        return new JAXBElement<TimeTableType>(_TimeTable_QNAME, TimeTableType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItem")
    public JAXBElement<AssessmentItemType> createAssessmentItem(AssessmentItemType value) {
        return new JAXBElement<AssessmentItemType>(_AssessmentItem_QNAME, AssessmentItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevel")
    public JAXBElement<YearLevelType> createYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_YearLevel_QNAME, YearLevelType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentForm")
    public JAXBElement<Sif3AssessmentFormType> createSif3AssessmentForm(Sif3AssessmentFormType value) {
        return new JAXBElement<Sif3AssessmentFormType>(_Sif3AssessmentForm_QNAME, Sif3AssessmentFormType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SessionInfo")
    public JAXBElement<SessionInfoType> createSessionInfo(SessionInfoType value) {
        return new JAXBElement<SessionInfoType>(_SessionInfo_QNAME, SessionInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentItemCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItems")
    public JAXBElement<AssessmentItemCollectionType> createAssessmentItems(AssessmentItemCollectionType value) {
        return new JAXBElement<AssessmentItemCollectionType>(_AssessmentItems_QNAME, AssessmentItemCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RoomInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoomInfos")
    public JAXBElement<RoomInfoCollectionType> createRoomInfos(RoomInfoCollectionType value) {
        return new JAXBElement<RoomInfoCollectionType>(_RoomInfos_QNAME, RoomInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Assessment")
    public JAXBElement<AssessmentType> createAssessment(AssessmentType value) {
        return new JAXBElement<AssessmentType>(_Assessment_QNAME, AssessmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeElement")
    public JAXBElement<TimeElementType> createTimeElement(TimeElementType value) {
        return new JAXBElement<TimeElementType>(_TimeElement_QNAME, TimeElementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTableCellCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableCells")
    public JAXBElement<TimeTableCellCollectionType> createTimeTableCells(TimeTableCellCollectionType value) {
        return new JAXBElement<TimeTableCellCollectionType>(_TimeTableCells_QNAME, TimeTableCellCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3Assessments")
    public JAXBElement<Sif3AssessmentCollectionType> createSif3Assessments(Sif3AssessmentCollectionType value) {
        return new JAXBElement<Sif3AssessmentCollectionType>(_Sif3Assessments_QNAME, Sif3AssessmentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemRoleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SystemRole")
    public JAXBElement<SystemRoleType> createSystemRole(SystemRoleType value) {
        return new JAXBElement<SystemRoleType>(_SystemRole_QNAME, SystemRoleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentActivityParticipationCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentActivityParticipations")
    public JAXBElement<StudentActivityParticipationCollectionType> createStudentActivityParticipations(StudentActivityParticipationCollectionType value) {
        return new JAXBElement<StudentActivityParticipationCollectionType>(_StudentActivityParticipations_QNAME, StudentActivityParticipationCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTableCellType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableCell")
    public JAXBElement<TimeTableCellType> createTimeTableCell(TimeTableCellType value) {
        return new JAXBElement<TimeTableCellType>(_TimeTableCell_QNAME, TimeTableCellType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSDTNType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSDTN")
    public JAXBElement<StudentSDTNType> createStudentSDTN(StudentSDTNType value) {
        return new JAXBElement<StudentSDTNType>(_StudentSDTN_QNAME, StudentSDTNType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardDocumentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardDocuments")
    public JAXBElement<LearningStandardDocumentCollectionType> createLearningStandardDocuments(LearningStandardDocumentCollectionType value) {
        return new JAXBElement<LearningStandardDocumentCollectionType>(_LearningStandardDocuments_QNAME, LearningStandardDocumentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentSubTestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubTest")
    public JAXBElement<AssessmentSubTestType> createAssessmentSubTest(AssessmentSubTestType value) {
        return new JAXBElement<AssessmentSubTestType>(_AssessmentSubTest_QNAME, AssessmentSubTestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItem")
    public JAXBElement<LearningStandardItemType> createLearningStandardItem(LearningStandardItemType value) {
        return new JAXBElement<LearningStandardItemType>(_LearningStandardItem_QNAME, LearningStandardItemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItems")
    public JAXBElement<LearningStandardItemCollectionType> createLearningStandardItems(LearningStandardItemCollectionType value) {
        return new JAXBElement<LearningStandardItemCollectionType>(_LearningStandardItems_QNAME, LearningStandardItemCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSectionEnrollmentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSectionEnrollments")
    public JAXBElement<StudentSectionEnrollmentCollectionType> createStudentSectionEnrollments(StudentSectionEnrollmentCollectionType value) {
        return new JAXBElement<StudentSectionEnrollmentCollectionType>(_StudentSectionEnrollments_QNAME, StudentSectionEnrollmentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BirthDate")
    public JAXBElement<XMLGregorianCalendar> createBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BirthDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentAssets")
    public JAXBElement<Sif3AssessmentAssetCollectionType> createSif3AssessmentAssets(Sif3AssessmentAssetCollectionType value) {
        return new JAXBElement<Sif3AssessmentAssetCollectionType>(_Sif3AssessmentAssets_QNAME, Sif3AssessmentAssetCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3StudentResponseSetCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3StudentResponseSets")
    public JAXBElement<Sif3StudentResponseSetCollectionType> createSif3StudentResponseSets(Sif3StudentResponseSetCollectionType value) {
        return new JAXBElement<Sif3StudentResponseSetCollectionType>(_Sif3StudentResponseSets_QNAME, Sif3StudentResponseSetCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTableSubjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableSubject")
    public JAXBElement<TimeTableSubjectType> createTimeTableSubject(TimeTableSubjectType value) {
        return new JAXBElement<TimeTableSubjectType>(_TimeTableSubject_QNAME, TimeTableSubjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3StudentResponseSetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3StudentResponseSet")
    public JAXBElement<Sif3StudentResponseSetType> createSif3StudentResponseSet(Sif3StudentResponseSetType value) {
        return new JAXBElement<Sif3StudentResponseSetType>(_Sif3StudentResponseSet_QNAME, Sif3StudentResponseSetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolProgramsCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolProgramss")
    public JAXBElement<SchoolProgramsCollectionType> createSchoolProgramss(SchoolProgramsCollectionType value) {
        return new JAXBElement<SchoolProgramsCollectionType>(_SchoolProgramss_QNAME, SchoolProgramsCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContactInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactInfo")
    public JAXBElement<ContactInfoType> createContactInfo(ContactInfoType value) {
        return new JAXBElement<ContactInfoType>(_ContactInfo_QNAME, ContactInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelationshipType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Relationship")
    public JAXBElement<RelationshipType> createRelationship(RelationshipType value) {
        return new JAXBElement<RelationshipType>(_Relationship_QNAME, RelationshipType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentItems")
    public JAXBElement<Sif3AssessmentItemCollectionType> createSif3AssessmentItems(Sif3AssessmentItemCollectionType value) {
        return new JAXBElement<Sif3AssessmentItemCollectionType>(_Sif3AssessmentItems_QNAME, Sif3AssessmentItemCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentSubTestCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubTests")
    public JAXBElement<AssessmentSubTestCollectionType> createAssessmentSubTests(AssessmentSubTestCollectionType value) {
        return new JAXBElement<AssessmentSubTestCollectionType>(_AssessmentSubTests_QNAME, AssessmentSubTestCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarDateCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CalendarDates")
    public JAXBElement<CalendarDateCollectionType> createCalendarDates(CalendarDateCollectionType value) {
        return new JAXBElement<CalendarDateCollectionType>(_CalendarDates_QNAME, CalendarDateCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSnapshotType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSnapshot")
    public JAXBElement<StudentSnapshotType> createStudentSnapshot(StudentSnapshotType value) {
        return new JAXBElement<StudentSnapshotType>(_StudentSnapshot_QNAME, StudentSnapshotType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalId")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLocalId(String value) {
        return new JAXBElement<String>(_LocalId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPeriodAttendanceCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentPeriodAttendances")
    public JAXBElement<StudentPeriodAttendanceCollectionType> createStudentPeriodAttendances(StudentPeriodAttendanceCollectionType value) {
        return new JAXBElement<StudentPeriodAttendanceCollectionType>(_StudentPeriodAttendances_QNAME, StudentPeriodAttendanceCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentActivityParticipationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentActivityParticipation")
    public JAXBElement<StudentActivityParticipationType> createStudentActivityParticipation(StudentActivityParticipationType value) {
        return new JAXBElement<StudentActivityParticipationType>(_StudentActivityParticipation_QNAME, StudentActivityParticipationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TermInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TermInfo")
    public JAXBElement<TermInfoType> createTermInfo(TermInfoType value) {
        return new JAXBElement<TermInfoType>(_TermInfo_QNAME, TermInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RoomInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoomInfo")
    public JAXBElement<RoomInfoType> createRoomInfo(RoomInfoType value) {
        return new JAXBElement<RoomInfoType>(_RoomInfo_QNAME, RoomInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentPackageCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentPackages")
    public JAXBElement<AssessmentPackageCollectionType> createAssessmentPackages(AssessmentPackageCollectionType value) {
        return new JAXBElement<AssessmentPackageCollectionType>(_AssessmentPackages_QNAME, AssessmentPackageCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentActivityInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentActivityInfo")
    public JAXBElement<StudentActivityInfoType> createStudentActivityInfo(StudentActivityInfoType value) {
        return new JAXBElement<StudentActivityInfoType>(_StudentActivityInfo_QNAME, StudentActivityInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentContactPersonalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentContactPersonal")
    public JAXBElement<StudentContactPersonalType> createStudentContactPersonal(StudentContactPersonalType value) {
        return new JAXBElement<StudentContactPersonalType>(_StudentContactPersonal_QNAME, StudentContactPersonalType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolContactListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolContactList")
    public JAXBElement<SchoolContactListType> createSchoolContactList(SchoolContactListType value) {
        return new JAXBElement<SchoolContactListType>(_SchoolContactList_QNAME, SchoolContactListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPersonalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentPersonal")
    public JAXBElement<StudentPersonalType> createStudentPersonal(StudentPersonalType value) {
        return new JAXBElement<StudentPersonalType>(_StudentPersonal_QNAME, StudentPersonalType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFContextsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Contexts")
    public JAXBElement<SIFContextsType> createSIFContexts(SIFContextsType value) {
        return new JAXBElement<SIFContextsType>(_SIFContexts_QNAME, SIFContextsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentForms")
    public JAXBElement<Sif3AssessmentFormCollectionType> createSif3AssessmentForms(Sif3AssessmentFormCollectionType value) {
        return new JAXBElement<Sif3AssessmentFormCollectionType>(_Sif3AssessmentForms_QNAME, Sif3AssessmentFormCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels")
    public JAXBElement<YearLevelsType> createYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolURL")
    public JAXBElement<String> createSchoolURL(String value) {
        return new JAXBElement<String>(_SchoolURL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonPictureCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PersonPictures")
    public JAXBElement<PersonPictureCollectionType> createPersonPictures(PersonPictureCollectionType value) {
        return new JAXBElement<PersonPictureCollectionType>(_PersonPictures_QNAME, PersonPictureCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrincipalInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PrincipalInfo")
    public JAXBElement<PrincipalInfoType> createPrincipalInfo(PrincipalInfoType value) {
        return new JAXBElement<PrincipalInfoType>(_PrincipalInfo_QNAME, PrincipalInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentContactCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentContactPersonals")
    public JAXBElement<StudentContactCollectionType> createStudentContactPersonals(StudentContactCollectionType value) {
        return new JAXBElement<StudentContactCollectionType>(_StudentContactPersonals_QNAME, StudentContactCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentPackageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentPackage")
    public JAXBElement<AssessmentPackageType> createAssessmentPackage(AssessmentPackageType value) {
        return new JAXBElement<AssessmentPackageType>(_AssessmentPackage_QNAME, AssessmentPackageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportAuthorityInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportAuthorityInfo")
    public JAXBElement<ReportAuthorityInfoType> createReportAuthorityInfo(ReportAuthorityInfoType value) {
        return new JAXBElement<ReportAuthorityInfoType>(_ReportAuthorityInfo_QNAME, ReportAuthorityInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentAttendanceSummaryCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentAttendanceSummarys")
    public JAXBElement<StudentAttendanceSummaryCollectionType> createStudentAttendanceSummarys(StudentAttendanceSummaryCollectionType value) {
        return new JAXBElement<StudentAttendanceSummaryCollectionType>(_StudentAttendanceSummarys_QNAME, StudentAttendanceSummaryCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaffPersonalType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonal")
    public JAXBElement<StaffPersonalType> createStaffPersonal(StaffPersonalType value) {
        return new JAXBElement<StaffPersonalType>(_StaffPersonal_QNAME, StaffPersonalType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAdministrationCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentAdministrations")
    public JAXBElement<Sif3AssessmentAdministrationCollectionType> createSif3AssessmentAdministrations(Sif3AssessmentAdministrationCollectionType value) {
        return new JAXBElement<Sif3AssessmentAdministrationCollectionType>(_Sif3AssessmentAdministrations_QNAME, Sif3AssessmentAdministrationCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CalendarDate")
    public JAXBElement<CalendarDate> createCalendarDate(CalendarDate value) {
        return new JAXBElement<CalendarDate>(_CalendarDate_QNAME, CalendarDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentAdministrationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentAdministration")
    public JAXBElement<AssessmentAdministrationType> createAssessmentAdministration(AssessmentAdministrationType value) {
        return new JAXBElement<AssessmentAdministrationType>(_AssessmentAdministration_QNAME, AssessmentAdministrationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SectionInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionInfos")
    public JAXBElement<SectionInfoCollectionType> createSectionInfos(SectionInfoCollectionType value) {
        return new JAXBElement<SectionInfoCollectionType>(_SectionInfos_QNAME, SectionInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentParticipationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentParticipation")
    public JAXBElement<StudentParticipationType> createStudentParticipation(StudentParticipationType value) {
        return new JAXBElement<StudentParticipationType>(_StudentParticipation_QNAME, StudentParticipationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridLocationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GridLocation")
    public JAXBElement<GridLocationType> createGridLocation(GridLocationType value) {
        return new JAXBElement<GridLocationType>(_GridLocation_QNAME, GridLocationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSessionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentSession")
    public JAXBElement<Sif3AssessmentSessionType> createSif3AssessmentSession(Sif3AssessmentSessionType value) {
        return new JAXBElement<Sif3AssessmentSessionType>(_Sif3AssessmentSession_QNAME, Sif3AssessmentSessionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemRoleCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SystemRoles")
    public JAXBElement<SystemRoleCollectionType> createSystemRoles(SystemRoleCollectionType value) {
        return new JAXBElement<SystemRoleCollectionType>(_SystemRoles_QNAME, SystemRoleCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnglishProficiencyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EnglishProficiency")
    public JAXBElement<EnglishProficiencyType> createEnglishProficiency(EnglishProficiencyType value) {
        return new JAXBElement<EnglishProficiencyType>(_EnglishProficiency_QNAME, EnglishProficiencyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentSection")
    public JAXBElement<Sif3AssessmentSectionType> createSif3AssessmentSection(Sif3AssessmentSectionType value) {
        return new JAXBElement<Sif3AssessmentSectionType>(_Sif3AssessmentSection_QNAME, Sif3AssessmentSectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LEAInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfos")
    public JAXBElement<LEAInfoCollectionType> createLEAInfos(LEAInfoCollectionType value) {
        return new JAXBElement<LEAInfoCollectionType>(_LEAInfos_QNAME, LEAInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningResources")
    public JAXBElement<LearningResourceCollectionType> createLearningResources(LearningResourceCollectionType value) {
        return new JAXBElement<LearningResourceCollectionType>(_LearningResources_QNAME, LearningResourceCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3Assessment")
    public JAXBElement<Sif3AssessmentType> createSif3Assessment(Sif3AssessmentType value) {
        return new JAXBElement<Sif3AssessmentType>(_Sif3Assessment_QNAME, Sif3AssessmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Assessments")
    public JAXBElement<AssessmentCollectionType> createAssessments(AssessmentCollectionType value) {
        return new JAXBElement<AssessmentCollectionType>(_Assessments_QNAME, AssessmentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSubTestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentSubTest")
    public JAXBElement<Sif3AssessmentSubTestType> createSif3AssessmentSubTest(Sif3AssessmentSubTestType value) {
        return new JAXBElement<Sif3AssessmentSubTestType>(_Sif3AssessmentSubTest_QNAME, Sif3AssessmentSubTestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements")
    public JAXBElement<SIFExtendedElementsType> createSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSchoolEnrollments")
    public JAXBElement<StudentSchoolEnrollmentCollectionType> createStudentSchoolEnrollments(StudentSchoolEnrollmentCollectionType value) {
        return new JAXBElement<StudentSchoolEnrollmentCollectionType>(_StudentSchoolEnrollments_QNAME, StudentSchoolEnrollmentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OnTimeGraduationYear")
    public JAXBElement<XMLGregorianCalendar> createOnTimeGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OnTimeGraduationYear_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTableCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTables")
    public JAXBElement<TimeTableCollectionType> createTimeTables(TimeTableCollectionType value) {
        return new JAXBElement<TimeTableCollectionType>(_TimeTables_QNAME, TimeTableCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Location")
    public JAXBElement<LocationType> createLocation(LocationType value) {
        return new JAXBElement<LocationType>(_Location_QNAME, LocationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSectionCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentSections")
    public JAXBElement<Sif3AssessmentSectionCollectionType> createSif3AssessmentSections(Sif3AssessmentSectionCollectionType value) {
        return new JAXBElement<Sif3AssessmentSectionCollectionType>(_Sif3AssessmentSections_QNAME, Sif3AssessmentSectionCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfos")
    public JAXBElement<SchoolCollectionType> createSchoolInfos(SchoolCollectionType value) {
        return new JAXBElement<SchoolCollectionType>(_SchoolInfos_QNAME, SchoolCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRegistrationCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sif3AssessmentRegistrations")
    public JAXBElement<Sif3AssessmentRegistrationCollectionType> createSif3AssessmentRegistrations(Sif3AssessmentRegistrationCollectionType value) {
        return new JAXBElement<Sif3AssessmentRegistrationCollectionType>(_Sif3AssessmentRegistrations_QNAME, Sif3AssessmentRegistrationCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsOperationalStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OperationalStatus")
    public JAXBElement<AUCodeSetsOperationalStatusType> createOperationalStatus(AUCodeSetsOperationalStatusType value) {
        return new JAXBElement<AUCodeSetsOperationalStatusType>(_OperationalStatus_QNAME, AUCodeSetsOperationalStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSDTNCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSDTNs")
    public JAXBElement<StudentSDTNCollectionType> createStudentSDTNs(StudentSDTNCollectionType value) {
        return new JAXBElement<StudentSDTNCollectionType>(_StudentSDTNs_QNAME, StudentSDTNCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentDailyAttendanceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentDailyAttendance")
    public JAXBElement<StudentDailyAttendanceType> createStudentDailyAttendance(StudentDailyAttendanceType value) {
        return new JAXBElement<StudentDailyAttendanceType>(_StudentDailyAttendance_QNAME, StudentDailyAttendanceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolCourseInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCourseInfo")
    public JAXBElement<SchoolCourseInfoType> createSchoolCourseInfo(SchoolCourseInfoType value) {
        return new JAXBElement<SchoolCourseInfoType>(_SchoolCourseInfo_QNAME, SchoolCourseInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSectionEnrollmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSectionEnrollment")
    public JAXBElement<StudentSectionEnrollmentType> createStudentSectionEnrollment(StudentSectionEnrollmentType value) {
        return new JAXBElement<StudentSectionEnrollmentType>(_StudentSectionEnrollment_QNAME, StudentSectionEnrollmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LEAInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfo")
    public JAXBElement<LEAInfoType> createLEAInfo(LEAInfoType value) {
        return new JAXBElement<LEAInfoType>(_LEAInfo_QNAME, LEAInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentScoreSetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentScoreSet")
    public JAXBElement<StudentScoreSetType> createStudentScoreSet(StudentScoreSetType value) {
        return new JAXBElement<StudentScoreSetType>(_StudentScoreSet_QNAME, StudentScoreSetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Activitys")
    public JAXBElement<ActivityCollectionType> createActivitys(ActivityCollectionType value) {
        return new JAXBElement<ActivityCollectionType>(_Activitys_QNAME, ActivityCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFReportObjectCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ReportObjects")
    public JAXBElement<SIFReportObjectCollectionType> createSIFReportObjects(SIFReportObjectCollectionType value) {
        return new JAXBElement<SIFReportObjectCollectionType>(_SIFReportObjects_QNAME, SIFReportObjectCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PersonInfo")
    public JAXBElement<PersonInfoType> createPersonInfo(PersonInfoType value) {
        return new JAXBElement<PersonInfoType>(_PersonInfo_QNAME, PersonInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GraduationDate")
    public JAXBElement<String> createGraduationDate(String value) {
        return new JAXBElement<String>(_GraduationDate_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Name")
    public JAXBElement<NameType> createName(NameType value) {
        return new JAXBElement<NameType>(_Name_QNAME, NameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProgramStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramStatus")
    public JAXBElement<ProgramStatusType> createProgramStatus(ProgramStatusType value) {
        return new JAXBElement<ProgramStatusType>(_ProgramStatus_QNAME, ProgramStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionInfoCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SessionInfos")
    public JAXBElement<SessionInfoCollectionType> createSessionInfos(SessionInfoCollectionType value) {
        return new JAXBElement<SessionInfoCollectionType>(_SessionInfos_QNAME, SessionInfoCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Identity")
    public JAXBElement<IdentityType> createIdentity(IdentityType value) {
        return new JAXBElement<IdentityType>(_Identity_QNAME, IdentityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = TimeTableSubjectType.class)
    public JAXBElement<OtherCodeListType> createTimeTableSubjectTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AcademicYear", scope = TimeTableSubjectType.class)
    public JAXBElement<YearLevelType> createTimeTableSubjectTypeAcademicYear(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_TimeTableSubjectTypeAcademicYear_QNAME, YearLevelType.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = TimeTableSubjectType.class)
    public JAXBElement<XMLGregorianCalendar> createTimeTableSubjectTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTableSubjectType.AcademicYearRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AcademicYearRange", scope = TimeTableSubjectType.class)
    public JAXBElement<TimeTableSubjectType.AcademicYearRange> createTimeTableSubjectTypeAcademicYearRange(TimeTableSubjectType.AcademicYearRange value) {
        return new JAXBElement<TimeTableSubjectType.AcademicYearRange>(_TimeTableSubjectTypeAcademicYearRange_QNAME, TimeTableSubjectType.AcademicYearRange.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Faculty", scope = TimeTableSubjectType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableSubjectTypeFaculty(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeFaculty_QNAME, String.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Semester", scope = TimeTableSubjectType.class)
    public JAXBElement<Long> createTimeTableSubjectTypeSemester(Long value) {
        return new JAXBElement<Long>(_TimeTableSubjectTypeSemester_QNAME, Long.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectType", scope = TimeTableSubjectType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableSubjectTypeSubjectType(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSubjectType_QNAME, String.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProposedMinClassSize", scope = TimeTableSubjectType.class)
    public JAXBElement<BigDecimal> createTimeTableSubjectTypeProposedMinClassSize(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TimeTableSubjectTypeProposedMinClassSize_QNAME, BigDecimal.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = TimeTableSubjectType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableSubjectTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseLocalId", scope = TimeTableSubjectType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableSubjectTypeCourseLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeCourseLocalId_QNAME, String.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProposedMaxClassSize", scope = TimeTableSubjectType.class)
    public JAXBElement<BigDecimal> createTimeTableSubjectTypeProposedMaxClassSize(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TimeTableSubjectTypeProposedMaxClassSize_QNAME, BigDecimal.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCourseInfoRefId", scope = TimeTableSubjectType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableSubjectTypeSchoolCourseInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolCourseInfoRefId_QNAME, String.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectShortName", scope = TimeTableSubjectType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableSubjectTypeSubjectShortName(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSubjectShortName_QNAME, String.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = TimeTableSubjectType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableSubjectTypeSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = TimeTableSubjectType.class)
    public JAXBElement<SIFMetadataType> createTimeTableSubjectTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = TimeTableSubjectType.class)
    public JAXBElement<SIFExtendedElementsType> createTimeTableSubjectTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, TimeTableSubjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalDescription", scope = ResourceUsageType.ResourceUsageContentType.class)
    public JAXBElement<String> createResourceUsageTypeResourceUsageContentTypeLocalDescription(String value) {
        return new JAXBElement<String>(_ResourceUsageTypeResourceUsageContentTypeLocalDescription_QNAME, String.class, ResourceUsageType.ResourceUsageContentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReferralDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeReferralDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeReferralDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentParticipationType.ProgramAvailability }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramAvailability", scope = StudentParticipationType.class)
    public JAXBElement<StudentParticipationType.ProgramAvailability> createStudentParticipationTypeProgramAvailability(StudentParticipationType.ProgramAvailability value) {
        return new JAXBElement<StudentParticipationType.ProgramAvailability>(_StudentParticipationTypeProgramAvailability_QNAME, StudentParticipationType.ProgramAvailability.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PlacementParentalConsentDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypePlacementParentalConsentDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypePlacementParentalConsentDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GiftedEligibilityCriteria", scope = StudentParticipationType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentParticipationTypeGiftedEligibilityCriteria(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentParticipationTypeGiftedEligibilityCriteria_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EvaluationExtensionDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeEvaluationExtensionDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeEvaluationExtensionDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ParticipationContact", scope = StudentParticipationType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentParticipationTypeParticipationContact(String value) {
        return new JAXBElement<String>(_StudentParticipationTypeParticipationContact_QNAME, String.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramPlacementDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeProgramPlacementDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeProgramPlacementDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentParticipationType.class)
    public JAXBElement<SIFMetadataType> createStudentParticipationTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentParticipationType.ReferralSource }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReferralSource", scope = StudentParticipationType.class)
    public JAXBElement<StudentParticipationType.ReferralSource> createStudentParticipationTypeReferralSource(StudentParticipationType.ReferralSource value) {
        return new JAXBElement<StudentParticipationType.ReferralSource>(_StudentParticipationTypeReferralSource_QNAME, StudentParticipationType.ReferralSource.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentParticipationType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentParticipationTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EntryPerson", scope = StudentParticipationType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentParticipationTypeEntryPerson(String value) {
        return new JAXBElement<String>(_StudentParticipationTypeEntryPerson_QNAME, String.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EvaluationDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeEvaluationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeEvaluationDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReevaluationDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeReevaluationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeReevaluationDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExtendedSchoolYear", scope = StudentParticipationType.class)
    public JAXBElement<Boolean> createStudentParticipationTypeExtendedSchoolYear(Boolean value) {
        return new JAXBElement<Boolean>(_StudentParticipationTypeExtendedSchoolYear_QNAME, Boolean.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NOREPDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeNOREPDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeNOREPDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExtendedDay", scope = StudentParticipationType.class)
    public JAXBElement<Boolean> createStudentParticipationTypeExtendedDay(Boolean value) {
        return new JAXBElement<Boolean>(_StudentParticipationTypeExtendedDay_QNAME, Boolean.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramEligibilityDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeProgramEligibilityDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeProgramEligibilityDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EvaluationParentalConsentDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeEvaluationParentalConsentDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeEvaluationParentalConsentDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentParticipationType.ProgramFundingSources }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramFundingSources", scope = StudentParticipationType.class)
    public JAXBElement<StudentParticipationType.ProgramFundingSources> createStudentParticipationTypeProgramFundingSources(StudentParticipationType.ProgramFundingSources value) {
        return new JAXBElement<StudentParticipationType.ProgramFundingSources>(_StudentParticipationTypeProgramFundingSources_QNAME, StudentParticipationType.ProgramFundingSources.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramPlanDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeProgramPlanDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeProgramPlanDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProgramStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramStatus", scope = StudentParticipationType.class)
    public JAXBElement<ProgramStatusType> createStudentParticipationTypeProgramStatus(ProgramStatusType value) {
        return new JAXBElement<ProgramStatusType>(_ProgramStatus_QNAME, ProgramStatusType.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExtensionComments", scope = StudentParticipationType.class)
    public JAXBElement<String> createStudentParticipationTypeExtensionComments(String value) {
        return new JAXBElement<String>(_StudentParticipationTypeExtensionComments_QNAME, String.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramType", scope = StudentParticipationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentParticipationTypeProgramType(String value) {
        return new JAXBElement<String>(_StudentParticipationTypeProgramType_QNAME, String.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSpecialEducationFTE", scope = StudentParticipationType.class)
    public JAXBElement<BigDecimal> createStudentParticipationTypeStudentSpecialEducationFTE(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_StudentParticipationTypeStudentSpecialEducationFTE_QNAME, BigDecimal.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProgramPlanEffectiveDate", scope = StudentParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentParticipationTypeProgramPlanEffectiveDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentParticipationTypeProgramPlanEffectiveDate_QNAME, XMLGregorianCalendar.class, StudentParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OnTimeGraduationYear", scope = StudentSnapshotType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSnapshotTypeOnTimeGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OnTimeGraduationYear_QNAME, XMLGregorianCalendar.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = StudentSnapshotType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSnapshotRefId", scope = StudentSnapshotType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeStudentSnapshotRefId(String value) {
        return new JAXBElement<String>(_StudentSnapshotTypeStudentSnapshotRefId_QNAME, String.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BirthDate", scope = StudentSnapshotType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSnapshotTypeBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BirthDate_QNAME, XMLGregorianCalendar.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sex", scope = StudentSnapshotType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeSex(String value) {
        return new JAXBElement<String>(_StudentSnapshotTypeSex_QNAME, String.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Age", scope = StudentSnapshotType.class)
    public JAXBElement<Long> createStudentSnapshotTypeAge(Long value) {
        return new JAXBElement<Long>(_StudentSnapshotTypeAge_QNAME, Long.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSnapshotType.StudentSubjectChoiceList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSubjectChoiceList", scope = StudentSnapshotType.class)
    public JAXBElement<StudentSnapshotType.StudentSubjectChoiceList> createStudentSnapshotTypeStudentSubjectChoiceList(StudentSnapshotType.StudentSubjectChoiceList value) {
        return new JAXBElement<StudentSnapshotType.StudentSubjectChoiceList>(_StudentSnapshotTypeStudentSubjectChoiceList_QNAME, StudentSnapshotType.StudentSubjectChoiceList.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentSnapshotType.class)
    public JAXBElement<SIFMetadataType> createStudentSnapshotTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentSnapshotType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentSnapshotTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProjectedGraduationYear", scope = StudentSnapshotType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSnapshotTypeProjectedGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ProjectedGraduationYear_QNAME, XMLGregorianCalendar.class, StudentSnapshotType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = RelationshipType.class)
    public JAXBElement<OtherCodeListType> createRelationshipTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, RelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FullYearEnrollment", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentFullYearEnrollment(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentFullYearEnrollment_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolName", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentSchoolName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentSchoolName_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Homeroom", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    public JAXBElement<Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentHomeroom(Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom value) {
        return new JAXBElement<Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentHomeroom_QNAME, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.Homeroom.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalId", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentLocalId(String value) {
        return new JAXBElement<String>(_LocalId_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HomeroomNumber", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentHomeroomNumber(String value) {
        return new JAXBElement<String>(_HomeroomNumber_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSchoolEnrollmentRefId", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentStudentSchoolEnrollmentRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentStudentSchoolEnrollmentRefId_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItemBankName", scope = Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentItemTypeAssessmentItemBanksAssessmentItemBankAssessmentItemBankName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeAssessmentItemBanksAssessmentItemBankAssessmentItemBankName_QNAME, String.class, Sif3AssessmentItemType.AssessmentItemBanks.AssessmentItemBank.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NumberOfResponses", scope = StudentScoreSetType.Scores.Score.class)
    public JAXBElement<Long> createStudentScoreSetTypeScoresScoreNumberOfResponses(Long value) {
        return new JAXBElement<Long>(_StudentScoreSetTypeScoresScoreNumberOfResponses_QNAME, Long.class, StudentScoreSetType.Scores.Score.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = StudentScoreSetType.Scores.Score.class)
    public JAXBElement<String> createStudentScoreSetTypeScoresScoreDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, StudentScoreSetType.Scores.Score.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DiagnosticStatement", scope = StudentScoreSetType.Scores.Score.class)
    public JAXBElement<String> createStudentScoreSetTypeScoresScoreDiagnosticStatement(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDiagnosticStatement_QNAME, String.class, StudentScoreSetType.Scores.Score.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentPersonalRefId", scope = StudentSDTNType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSDTNTypeStudentPersonalRefId(String value) {
        return new JAXBElement<String>(_StudentSDTNTypeStudentPersonalRefId_QNAME, String.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSDTNType.AreasOfInterestList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AreasOfInterestList", scope = StudentSDTNType.class)
    public JAXBElement<StudentSDTNType.AreasOfInterestList> createStudentSDTNTypeAreasOfInterestList(StudentSDTNType.AreasOfInterestList value) {
        return new JAXBElement<StudentSDTNType.AreasOfInterestList>(_StudentSDTNTypeAreasOfInterestList_QNAME, StudentSDTNType.AreasOfInterestList.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevel", scope = StudentSDTNType.class)
    public JAXBElement<YearLevelType> createStudentSDTNTypeYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_YearLevel_QNAME, YearLevelType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrincipalInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PrincipalInfo", scope = StudentSDTNType.class)
    public JAXBElement<PrincipalInfoType> createStudentSDTNTypePrincipalInfo(PrincipalInfoType value) {
        return new JAXBElement<PrincipalInfoType>(_PrincipalInfo_QNAME, PrincipalInfoType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YoungCarersRole", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeYoungCarersRole(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeYoungCarersRole_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSDTNType.PastoralCare }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PastoralCare", scope = StudentSDTNType.class)
    public JAXBElement<StudentSDTNType.PastoralCare> createStudentSDTNTypePastoralCare(StudentSDTNType.PastoralCare value) {
        return new JAXBElement<StudentSDTNType.PastoralCare>(_StudentSDTNTypePastoralCare_QNAME, StudentSDTNType.PastoralCare.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReasonForLeaving", scope = StudentSDTNType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSDTNTypeReasonForLeaving(String value) {
        return new JAXBElement<String>(_StudentSDTNTypeReasonForLeaving_QNAME, String.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSDTNType.FurtherInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FurtherInformation", scope = StudentSDTNType.class)
    public JAXBElement<StudentSDTNType.FurtherInformation> createStudentSDTNTypeFurtherInformation(StudentSDTNType.FurtherInformation value) {
        return new JAXBElement<StudentSDTNType.FurtherInformation>(_StudentSDTNTypeFurtherInformation_QNAME, StudentSDTNType.FurtherInformation.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = StudentSDTNType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSDTNTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Literacy", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsProgressLevelType> createStudentSDTNTypeLiteracy(AUCodeSetsProgressLevelType value) {
        return new JAXBElement<AUCodeSetsProgressLevelType>(_StudentSDTNTypeLiteracy_QNAME, AUCodeSetsProgressLevelType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = StudentSDTNType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSDTNTypeSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AttendanceConcerns", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeAttendanceConcerns(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeAttendanceConcerns_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentSDTNType.class)
    public JAXBElement<SIFMetadataType> createStudentSDTNTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentSDTNType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentSDTNTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HealthNeeds", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeHealthNeeds(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeHealthNeeds_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSDTNType.PreviousSchoolsList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PreviousSchoolsList", scope = StudentSDTNType.class)
    public JAXBElement<StudentSDTNType.PreviousSchoolsList> createStudentSDTNTypePreviousSchoolsList(StudentSDTNType.PreviousSchoolsList value) {
        return new JAXBElement<StudentSDTNType.PreviousSchoolsList>(_StudentSDTNTypePreviousSchoolsList_QNAME, StudentSDTNType.PreviousSchoolsList.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EnrollmentDate", scope = StudentSDTNType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSDTNTypeEnrollmentDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentSDTNTypeEnrollmentDate_QNAME, XMLGregorianCalendar.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CareerGuidanceFileHeld", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeCareerGuidanceFileHeld(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeCareerGuidanceFileHeld_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DepartureDate", scope = StudentSDTNType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSDTNTypeDepartureDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentSDTNTypeDepartureDate_QNAME, XMLGregorianCalendar.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AdjustedEducationProgram", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeAdjustedEducationProgram(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeAdjustedEducationProgram_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AddressList", scope = StudentSDTNType.class)
    public JAXBElement<AddressListType> createStudentSDTNTypeAddressList(AddressListType value) {
        return new JAXBElement<AddressListType>(_AddressList_QNAME, AddressListType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Numeracy", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsProgressLevelType> createStudentSDTNTypeNumeracy(AUCodeSetsProgressLevelType value) {
        return new JAXBElement<AUCodeSetsProgressLevelType>(_StudentSDTNTypeNumeracy_QNAME, AUCodeSetsProgressLevelType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AcceleratedProgram", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeAcceleratedProgram(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeAcceleratedProgram_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSDTNType.OtherLearningAreasList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherLearningAreasList", scope = StudentSDTNType.class)
    public JAXBElement<StudentSDTNType.OtherLearningAreasList> createStudentSDTNTypeOtherLearningAreasList(StudentSDTNType.OtherLearningAreasList value) {
        return new JAXBElement<StudentSDTNType.OtherLearningAreasList>(_StudentSDTNTypeOtherLearningAreasList_QNAME, StudentSDTNType.OtherLearningAreasList.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NegotiatedCurriculumPlan", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeNegotiatedCurriculumPlan(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeNegotiatedCurriculumPlan_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LatestStudentReportAvailable", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeLatestStudentReportAvailable(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeLatestStudentReportAvailable_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherLearningSupport", scope = StudentSDTNType.class)
    public JAXBElement<String> createStudentSDTNTypeOtherLearningSupport(String value) {
        return new JAXBElement<String>(_StudentSDTNTypeOtherLearningSupport_QNAME, String.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCounsellorFileHeld", scope = StudentSDTNType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypeSchoolCounsellorFileHeld(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeSchoolCounsellorFileHeld_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumberList", scope = StudentSDTNType.FurtherInformation.class)
    public JAXBElement<PhoneNumberListType> createStudentSDTNTypeFurtherInformationPhoneNumberList(PhoneNumberListType value) {
        return new JAXBElement<PhoneNumberListType>(_PhoneNumberList_QNAME, PhoneNumberListType.class, StudentSDTNType.FurtherInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmailList", scope = StudentSDTNType.FurtherInformation.class)
    public JAXBElement<EmailListType> createStudentSDTNTypeFurtherInformationEmailList(EmailListType value) {
        return new JAXBElement<EmailListType>(_EmailList_QNAME, EmailListType.class, StudentSDTNType.FurtherInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameOfRecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactName", scope = StudentSDTNType.FurtherInformation.class)
    public JAXBElement<NameOfRecordType> createStudentSDTNTypeFurtherInformationContactName(NameOfRecordType value) {
        return new JAXBElement<NameOfRecordType>(_StudentSDTNTypeFurtherInformationContactName_QNAME, NameOfRecordType.class, StudentSDTNType.FurtherInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = ResourceUsageType.class)
    public JAXBElement<SIFMetadataType> createResourceUsageTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, ResourceUsageType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = ResourceUsageType.class)
    public JAXBElement<SIFExtendedElementsType> createResourceUsageTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, ResourceUsageType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentId", scope = AssessmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAssessmentTypeAssessmentId(String value) {
        return new JAXBElement<String>(_AssessmentTypeAssessmentId_QNAME, String.class, AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentPackageRefId", scope = AssessmentType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentTypeAssessmentPackageRefId(String value) {
        return new JAXBElement<String>(_AssessmentTypeAssessmentPackageRefId_QNAME, String.class, AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentType.AssessmentDescriptors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentDescriptors", scope = AssessmentType.class)
    public JAXBElement<AssessmentType.AssessmentDescriptors> createAssessmentTypeAssessmentDescriptors(AssessmentType.AssessmentDescriptors value) {
        return new JAXBElement<AssessmentType.AssessmentDescriptors>(_AssessmentTypeAssessmentDescriptors_QNAME, AssessmentType.AssessmentDescriptors.class, AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AssessmentType.class)
    public JAXBElement<SIFMetadataType> createAssessmentTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AssessmentType.class)
    public JAXBElement<SIFExtendedElementsType> createAssessmentTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffLocalId", scope = TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStaffLocalId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStaffLocalId_QNAME, String.class, TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartTime", scope = TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class)
    public JAXBElement<XMLGregorianCalendar> createTeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStartTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStartTime_QNAME, XMLGregorianCalendar.class, TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PeriodId", scope = TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodPeriodId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodPeriodId_QNAME, String.class, TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoomNumber", scope = TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodRoomNumber(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodRoomNumber_QNAME, String.class, TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CellType", scope = TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodCellType(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodCellType_QNAME, String.class, TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableCellRefId", scope = TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodTimeTableCellRefId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodTimeTableCellRefId_QNAME, String.class, TeachingGroupType.TeachingGroupPeriodList.TeachingGroupPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = SectionInfoType.LanguageOfInstruction.class)
    public JAXBElement<OtherCodeListType> createSectionInfoTypeLanguageOfInstructionOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, SectionInfoType.LanguageOfInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarDate.TeacherAttendance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeacherAttendance", scope = CalendarDate.class)
    public JAXBElement<CalendarDate.TeacherAttendance> createCalendarDateTeacherAttendance(CalendarDate.TeacherAttendance value) {
        return new JAXBElement<CalendarDate.TeacherAttendance>(_CalendarDateTeacherAttendance_QNAME, CalendarDate.TeacherAttendance.class, CalendarDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarDate.StudentAttendance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentAttendance", scope = CalendarDate.class)
    public JAXBElement<CalendarDate.StudentAttendance> createCalendarDateStudentAttendance(CalendarDate.StudentAttendance value) {
        return new JAXBElement<CalendarDate.StudentAttendance>(_CalendarDateStudentAttendance_QNAME, CalendarDate.StudentAttendance.class, CalendarDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalendarDate.AdministratorAttendance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AdministratorAttendance", scope = CalendarDate.class)
    public JAXBElement<CalendarDate.AdministratorAttendance> createCalendarDateAdministratorAttendance(CalendarDate.AdministratorAttendance value) {
        return new JAXBElement<CalendarDate.AdministratorAttendance>(_CalendarDateAdministratorAttendance_QNAME, CalendarDate.AdministratorAttendance.class, CalendarDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CalendarDateNumber", scope = CalendarDate.class)
    public JAXBElement<Long> createCalendarDateCalendarDateNumber(Long value) {
        return new JAXBElement<Long>(_CalendarDateCalendarDateNumber_QNAME, Long.class, CalendarDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CalendarDateRefId", scope = CalendarDate.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createCalendarDateCalendarDateRefId(String value) {
        return new JAXBElement<String>(_CalendarDateCalendarDateRefId_QNAME, String.class, CalendarDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = CalendarDate.class)
    public JAXBElement<SIFMetadataType> createCalendarDateSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, CalendarDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = CalendarDate.class)
    public JAXBElement<SIFExtendedElementsType> createCalendarDateSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, CalendarDate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.PromotionInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PromotionInfo", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.PromotionInfo> createStudentSchoolEnrollmentTypePromotionInfo(StudentSchoolEnrollmentType.PromotionInfo value) {
        return new JAXBElement<StudentSchoolEnrollmentType.PromotionInfo>(_StudentSchoolEnrollmentTypePromotionInfo_QNAME, StudentSchoolEnrollmentType.PromotionInfo.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Homeroom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Homeroom", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.Homeroom> createStudentSchoolEnrollmentTypeHomeroom(StudentSchoolEnrollmentType.Homeroom value) {
        return new JAXBElement<StudentSchoolEnrollmentType.Homeroom>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentHomeroom_QNAME, StudentSchoolEnrollmentType.Homeroom.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevel", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<YearLevelType> createStudentSchoolEnrollmentTypeYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_YearLevel_QNAME, YearLevelType.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Calendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Calendar", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.Calendar> createStudentSchoolEnrollmentTypeCalendar(StudentSchoolEnrollmentType.Calendar value) {
        return new JAXBElement<StudentSchoolEnrollmentType.Calendar>(_StudentSchoolEnrollmentTypeCalendar_QNAME, StudentSchoolEnrollmentType.Calendar.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Counselor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Counselor", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.Counselor> createStudentSchoolEnrollmentTypeCounselor(StudentSchoolEnrollmentType.Counselor value) {
        return new JAXBElement<StudentSchoolEnrollmentType.Counselor>(_StudentSchoolEnrollmentTypeCounselor_QNAME, StudentSchoolEnrollmentType.Counselor.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExitDate", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSchoolEnrollmentTypeExitDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentSchoolEnrollmentTypeExitDate_QNAME, XMLGregorianCalendar.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FTPTStatus", scope = StudentSchoolEnrollmentType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypeFTPTStatus(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeFTPTStatus_QNAME, String.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.EntryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EntryType", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.EntryType> createStudentSchoolEnrollmentTypeEntryType(StudentSchoolEnrollmentType.EntryType value) {
        return new JAXBElement<StudentSchoolEnrollmentType.EntryType>(_StudentSchoolEnrollmentTypeEntryType_QNAME, StudentSchoolEnrollmentType.EntryType.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.ExitStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExitStatus", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.ExitStatus> createStudentSchoolEnrollmentTypeExitStatus(StudentSchoolEnrollmentType.ExitStatus value) {
        return new JAXBElement<StudentSchoolEnrollmentType.ExitStatus>(_StudentSchoolEnrollmentTypeExitStatus_QNAME, StudentSchoolEnrollmentType.ExitStatus.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.StudentSubjectChoiceList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSubjectChoiceList", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.StudentSubjectChoiceList> createStudentSchoolEnrollmentTypeStudentSubjectChoiceList(StudentSchoolEnrollmentType.StudentSubjectChoiceList value) {
        return new JAXBElement<StudentSchoolEnrollmentType.StudentSubjectChoiceList>(_StudentSnapshotTypeStudentSubjectChoiceList_QNAME, StudentSchoolEnrollmentType.StudentSubjectChoiceList.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FFPOS", scope = StudentSchoolEnrollmentType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypeFFPOS(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeFFPOS_QNAME, String.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PreviousSchool", scope = StudentSchoolEnrollmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypePreviousSchool(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypePreviousSchool_QNAME, String.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.CatchmentStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CatchmentStatus", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.CatchmentStatus> createStudentSchoolEnrollmentTypeCatchmentStatus(StudentSchoolEnrollmentType.CatchmentStatus value) {
        return new JAXBElement<StudentSchoolEnrollmentType.CatchmentStatus>(_StudentSchoolEnrollmentTypeCatchmentStatus_QNAME, StudentSchoolEnrollmentType.CatchmentStatus.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.Advisor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Advisor", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.Advisor> createStudentSchoolEnrollmentTypeAdvisor(StudentSchoolEnrollmentType.Advisor value) {
        return new JAXBElement<StudentSchoolEnrollmentType.Advisor>(_StudentSchoolEnrollmentTypeAdvisor_QNAME, StudentSchoolEnrollmentType.Advisor.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "House", scope = StudentSchoolEnrollmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypeHouse(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeHouse_QNAME, String.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSchoolEnrollmentType.ExitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExitType", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<StudentSchoolEnrollmentType.ExitType> createStudentSchoolEnrollmentTypeExitType(StudentSchoolEnrollmentType.ExitType value) {
        return new JAXBElement<StudentSchoolEnrollmentType.ExitType>(_StudentSchoolEnrollmentTypeExitType_QNAME, StudentSchoolEnrollmentType.ExitType.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Homegroup", scope = StudentSchoolEnrollmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypeHomegroup(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeHomegroup_QNAME, String.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RecordClosureReason", scope = StudentSchoolEnrollmentType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypeRecordClosureReason(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeRecordClosureReason_QNAME, String.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DestinationSchool", scope = StudentSchoolEnrollmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypeDestinationSchool(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeDestinationSchool_QNAME, String.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<SIFMetadataType> createStudentSchoolEnrollmentTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FTE", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<BigDecimal> createStudentSchoolEnrollmentTypeFTE(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_StudentSchoolEnrollmentTypeFTE_QNAME, BigDecimal.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentSchoolEnrollmentTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "IndividualLearningPlan", scope = StudentSchoolEnrollmentType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSchoolEnrollmentTypeIndividualLearningPlan(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSchoolEnrollmentTypeIndividualLearningPlan_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSchoolEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemographicsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Demographics", scope = PersonInfoType.class)
    public JAXBElement<DemographicsType> createPersonInfoTypeDemographics(DemographicsType value) {
        return new JAXBElement<DemographicsType>(_Demographics_QNAME, DemographicsType.class, PersonInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherNamesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherNames", scope = PersonInfoType.class)
    public JAXBElement<OtherNamesType> createPersonInfoTypeOtherNames(OtherNamesType value) {
        return new JAXBElement<OtherNamesType>(_OtherNames_QNAME, OtherNamesType.class, PersonInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AddressList", scope = PersonInfoType.class)
    public JAXBElement<AddressListType> createPersonInfoTypeAddressList(AddressListType value) {
        return new JAXBElement<AddressListType>(_AddressList_QNAME, AddressListType.class, PersonInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumberList", scope = PersonInfoType.class)
    public JAXBElement<PhoneNumberListType> createPersonInfoTypePhoneNumberList(PhoneNumberListType value) {
        return new JAXBElement<PhoneNumberListType>(_PhoneNumberList_QNAME, PhoneNumberListType.class, PersonInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmailList", scope = PersonInfoType.class)
    public JAXBElement<EmailListType> createPersonInfoTypeEmailList(EmailListType value) {
        return new JAXBElement<EmailListType>(_EmailList_QNAME, EmailListType.class, PersonInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentSchoolEnrollmentType.EntryType.class)
    public JAXBElement<OtherCodeListType> createStudentSchoolEnrollmentTypeEntryTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentSchoolEnrollmentType.EntryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsStaffStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmploymentStatus", scope = StaffPersonalType.class)
    public JAXBElement<AUCodeSetsStaffStatusType> createStaffPersonalTypeEmploymentStatus(AUCodeSetsStaffStatusType value) {
        return new JAXBElement<AUCodeSetsStaffStatusType>(_StaffPersonalTypeEmploymentStatus_QNAME, AUCodeSetsStaffStatusType.class, StaffPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = StaffPersonalType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStaffPersonalTypeStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, StaffPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaffPersonalType.OtherIdList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherIdList", scope = StaffPersonalType.class)
    public JAXBElement<StaffPersonalType.OtherIdList> createStaffPersonalTypeOtherIdList(StaffPersonalType.OtherIdList value) {
        return new JAXBElement<StaffPersonalType.OtherIdList>(_StaffPersonalTypeOtherIdList_QNAME, StaffPersonalType.OtherIdList.class, StaffPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Title", scope = StaffPersonalType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStaffPersonalTypeTitle(String value) {
        return new JAXBElement<String>(_StaffPersonalTypeTitle_QNAME, String.class, StaffPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElectronicIdListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ElectronicIdList", scope = StaffPersonalType.class)
    public JAXBElement<ElectronicIdListType> createStaffPersonalTypeElectronicIdList(ElectronicIdListType value) {
        return new JAXBElement<ElectronicIdListType>(_ElectronicIdList_QNAME, ElectronicIdListType.class, StaffPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StaffPersonalType.class)
    public JAXBElement<SIFMetadataType> createStaffPersonalTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StaffPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StaffPersonalType.class)
    public JAXBElement<SIFExtendedElementsType> createStaffPersonalTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StaffPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentItemType.LearningStandardItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItems", scope = AssessmentItemType.class)
    public JAXBElement<AssessmentItemType.LearningStandardItems> createAssessmentItemTypeLearningStandardItems(AssessmentItemType.LearningStandardItems value) {
        return new JAXBElement<AssessmentItemType.LearningStandardItems>(_LearningStandardItems_QNAME, AssessmentItemType.LearningStandardItems.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemScoreMinimum", scope = AssessmentItemType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentItemTypeItemScoreMinimum(String value) {
        return new JAXBElement<String>(_AssessmentItemTypeItemScoreMinimum_QNAME, String.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentItemType.PerformanceLevels }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PerformanceLevels", scope = AssessmentItemType.class)
    public JAXBElement<AssessmentItemType.PerformanceLevels> createAssessmentItemTypePerformanceLevels(AssessmentItemType.PerformanceLevels value) {
        return new JAXBElement<AssessmentItemType.PerformanceLevels>(_AssessmentItemTypePerformanceLevels_QNAME, AssessmentItemType.PerformanceLevels.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemScoreMaximum", scope = AssessmentItemType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentItemTypeItemScoreMaximum(String value) {
        return new JAXBElement<String>(_AssessmentItemTypeItemScoreMaximum_QNAME, String.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Stem", scope = AssessmentItemType.class)
    public JAXBElement<AbstractContentElementType> createAssessmentItemTypeStem(AbstractContentElementType value) {
        return new JAXBElement<AbstractContentElementType>(_AssessmentItemTypeStem_QNAME, AbstractContentElementType.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AssessmentItemType.class)
    public JAXBElement<SIFMetadataType> createAssessmentItemTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentItemType.ResponseChoices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ResponseChoices", scope = AssessmentItemType.class)
    public JAXBElement<AssessmentItemType.ResponseChoices> createAssessmentItemTypeResponseChoices(AssessmentItemType.ResponseChoices value) {
        return new JAXBElement<AssessmentItemType.ResponseChoices>(_AssessmentItemTypeResponseChoices_QNAME, AssessmentItemType.ResponseChoices.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Stimulus", scope = AssessmentItemType.class)
    public JAXBElement<AbstractContentElementType> createAssessmentItemTypeStimulus(AbstractContentElementType value) {
        return new JAXBElement<AbstractContentElementType>(_AssessmentItemTypeStimulus_QNAME, AbstractContentElementType.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AssessmentItemType.class)
    public JAXBElement<SIFExtendedElementsType> createAssessmentItemTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemName", scope = AssessmentItemType.class)
    public JAXBElement<String> createAssessmentItemTypeItemName(String value) {
        return new JAXBElement<String>(_AssessmentItemTypeItemName_QNAME, String.class, AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndOfLifeDate", scope = LearningStandardDocumentType.class)
    public JAXBElement<XMLGregorianCalendar> createLearningStandardDocumentTypeEndOfLifeDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningStandardDocumentTypeEndOfLifeDate_QNAME, XMLGregorianCalendar.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalArchiveDate", scope = LearningStandardDocumentType.class)
    public JAXBElement<XMLGregorianCalendar> createLearningStandardDocumentTypeLocalArchiveDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningStandardDocumentTypeLocalArchiveDate_QNAME, XMLGregorianCalendar.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.Authors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Authors", scope = LearningStandardDocumentType.class)
    public JAXBElement<LearningStandardDocumentType.Authors> createLearningStandardDocumentTypeAuthors(LearningStandardDocumentType.Authors value) {
        return new JAXBElement<LearningStandardDocumentType.Authors>(_LearningStandardDocumentTypeAuthors_QNAME, LearningStandardDocumentType.Authors.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DocumentDate", scope = LearningStandardDocumentType.class)
    public JAXBElement<XMLGregorianCalendar> createLearningStandardDocumentTypeDocumentDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningStandardDocumentTypeDocumentDate_QNAME, XMLGregorianCalendar.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = LearningStandardDocumentType.class)
    public JAXBElement<String> createLearningStandardDocumentTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalAdoptionDate", scope = LearningStandardDocumentType.class)
    public JAXBElement<XMLGregorianCalendar> createLearningStandardDocumentTypeLocalAdoptionDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningStandardDocumentTypeLocalAdoptionDate_QNAME, XMLGregorianCalendar.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RichDescription", scope = LearningStandardDocumentType.class)
    public JAXBElement<AbstractContentElementType> createLearningStandardDocumentTypeRichDescription(AbstractContentElementType value) {
        return new JAXBElement<AbstractContentElementType>(_LearningStandardDocumentTypeRichDescription_QNAME, AbstractContentElementType.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.Copyright }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Copyright", scope = LearningStandardDocumentType.class)
    public JAXBElement<LearningStandardDocumentType.Copyright> createLearningStandardDocumentTypeCopyright(LearningStandardDocumentType.Copyright value) {
        return new JAXBElement<LearningStandardDocumentType.Copyright>(_LearningStandardDocumentTypeCopyright_QNAME, LearningStandardDocumentType.Copyright.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardDocumentType.RelatedLearningStandards }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RelatedLearningStandards", scope = LearningStandardDocumentType.class)
    public JAXBElement<LearningStandardDocumentType.RelatedLearningStandards> createLearningStandardDocumentTypeRelatedLearningStandards(LearningStandardDocumentType.RelatedLearningStandards value) {
        return new JAXBElement<LearningStandardDocumentType.RelatedLearningStandards>(_LearningStandardDocumentTypeRelatedLearningStandards_QNAME, LearningStandardDocumentType.RelatedLearningStandards.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RepositoryDate", scope = LearningStandardDocumentType.class)
    public JAXBElement<XMLGregorianCalendar> createLearningStandardDocumentTypeRepositoryDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningStandardDocumentTypeRepositoryDate_QNAME, XMLGregorianCalendar.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OrganizationContactPoint", scope = LearningStandardDocumentType.class)
    public JAXBElement<String> createLearningStandardDocumentTypeOrganizationContactPoint(String value) {
        return new JAXBElement<String>(_LearningStandardDocumentTypeOrganizationContactPoint_QNAME, String.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = LearningStandardDocumentType.class)
    public JAXBElement<SIFMetadataType> createLearningStandardDocumentTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = LearningStandardDocumentType.class)
    public JAXBElement<SIFExtendedElementsType> createLearningStandardDocumentTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = LearningStandardDocumentType.class)
    public JAXBElement<YearLevelsType> createLearningStandardDocumentTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, LearningStandardDocumentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFQueryDataModelType.SIFConditionGroup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ConditionGroup", scope = SIFQueryDataModelType.class)
    public JAXBElement<SIFQueryDataModelType.SIFConditionGroup> createSIFQueryDataModelTypeSIFConditionGroup(SIFQueryDataModelType.SIFConditionGroup value) {
        return new JAXBElement<SIFQueryDataModelType.SIFConditionGroup>(_SIFQueryDataModelTypeSIFConditionGroup_QNAME, SIFQueryDataModelType.SIFConditionGroup.class, SIFQueryDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Example", scope = SIFQueryDataModelType.class)
    public JAXBElement<ObjectType> createSIFQueryDataModelTypeSIFExample(ObjectType value) {
        return new JAXBElement<ObjectType>(_SIFQueryDataModelTypeSIFExample_QNAME, ObjectType.class, SIFQueryDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Role", scope = StudentActivityParticipationType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentActivityParticipationTypeRole(String value) {
        return new JAXBElement<String>(_StudentActivityParticipationTypeRole_QNAME, String.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentActivityParticipationType.RecognitionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RecognitionList", scope = StudentActivityParticipationType.class)
    public JAXBElement<StudentActivityParticipationType.RecognitionList> createStudentActivityParticipationTypeRecognitionList(StudentActivityParticipationType.RecognitionList value) {
        return new JAXBElement<StudentActivityParticipationType.RecognitionList>(_StudentActivityParticipationTypeRecognitionList_QNAME, StudentActivityParticipationType.RecognitionList.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ParticipationComment", scope = StudentActivityParticipationType.class)
    public JAXBElement<String> createStudentActivityParticipationTypeParticipationComment(String value) {
        return new JAXBElement<String>(_StudentActivityParticipationTypeParticipationComment_QNAME, String.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = StudentActivityParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentActivityParticipationTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDate", scope = StudentActivityParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentActivityParticipationTypeEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeEndDate_QNAME, XMLGregorianCalendar.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDate", scope = StudentActivityParticipationType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentActivityParticipationTypeStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeStartDate_QNAME, XMLGregorianCalendar.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentActivityParticipationType.class)
    public JAXBElement<SIFMetadataType> createStudentActivityParticipationTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentActivityParticipationType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentActivityParticipationTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentActivityParticipationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeIn", scope = StudentDailyAttendanceType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentDailyAttendanceTypeTimeIn(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentDailyAttendanceTypeTimeIn_QNAME, XMLGregorianCalendar.class, StudentDailyAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeOut", scope = StudentDailyAttendanceType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentDailyAttendanceTypeTimeOut(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentDailyAttendanceTypeTimeOut_QNAME, XMLGregorianCalendar.class, StudentDailyAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AbsenceValue", scope = StudentDailyAttendanceType.class)
    public JAXBElement<BigDecimal> createStudentDailyAttendanceTypeAbsenceValue(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_StudentDailyAttendanceTypeAbsenceValue_QNAME, BigDecimal.class, StudentDailyAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsDayValueCodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DayValue", scope = StudentDailyAttendanceType.class)
    public JAXBElement<AUCodeSetsDayValueCodeType> createStudentDailyAttendanceTypeDayValue(AUCodeSetsDayValueCodeType value) {
        return new JAXBElement<AUCodeSetsDayValueCodeType>(_StudentDailyAttendanceTypeDayValue_QNAME, AUCodeSetsDayValueCodeType.class, StudentDailyAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentDailyAttendanceType.class)
    public JAXBElement<SIFMetadataType> createStudentDailyAttendanceTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentDailyAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentDailyAttendanceType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentDailyAttendanceTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentDailyAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AttendanceNote", scope = StudentDailyAttendanceType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentDailyAttendanceTypeAttendanceNote(String value) {
        return new JAXBElement<String>(_StudentDailyAttendanceTypeAttendanceNote_QNAME, String.class, StudentDailyAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = LifeCycleType.ModificationHistory.Modified.class)
    public JAXBElement<String> createLifeCycleTypeModificationHistoryModifiedDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, LifeCycleType.ModificationHistory.Modified.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EffectiveDate", scope = AggregateStatisticInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createAggregateStatisticInfoTypeEffectiveDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AggregateStatisticInfoTypeEffectiveDate_QNAME, XMLGregorianCalendar.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Location", scope = AggregateStatisticInfoType.class)
    public JAXBElement<LocationType> createAggregateStatisticInfoTypeLocation(LocationType value) {
        return new JAXBElement<LocationType>(_Location_QNAME, LocationType.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Measure", scope = AggregateStatisticInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAggregateStatisticInfoTypeMeasure(String value) {
        return new JAXBElement<String>(_AggregateStatisticInfoTypeMeasure_QNAME, String.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DiscontinueDate", scope = AggregateStatisticInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createAggregateStatisticInfoTypeDiscontinueDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AggregateStatisticInfoTypeDiscontinueDate_QNAME, XMLGregorianCalendar.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateStatisticInfoType.CalculationRule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CalculationRule", scope = AggregateStatisticInfoType.class)
    public JAXBElement<AggregateStatisticInfoType.CalculationRule> createAggregateStatisticInfoTypeCalculationRule(AggregateStatisticInfoType.CalculationRule value) {
        return new JAXBElement<AggregateStatisticInfoType.CalculationRule>(_AggregateStatisticInfoTypeCalculationRule_QNAME, AggregateStatisticInfoType.CalculationRule.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ApprovalDate", scope = AggregateStatisticInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createAggregateStatisticInfoTypeApprovalDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AggregateStatisticInfoTypeApprovalDate_QNAME, XMLGregorianCalendar.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExpirationDate", scope = AggregateStatisticInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createAggregateStatisticInfoTypeExpirationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AggregateStatisticInfoTypeExpirationDate_QNAME, XMLGregorianCalendar.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Source", scope = AggregateStatisticInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAggregateStatisticInfoTypeSource(String value) {
        return new JAXBElement<String>(_AggregateStatisticInfoTypeSource_QNAME, String.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AggregateStatisticInfoType.ExclusionRules }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExclusionRules", scope = AggregateStatisticInfoType.class)
    public JAXBElement<AggregateStatisticInfoType.ExclusionRules> createAggregateStatisticInfoTypeExclusionRules(AggregateStatisticInfoType.ExclusionRules value) {
        return new JAXBElement<AggregateStatisticInfoType.ExclusionRules>(_AggregateStatisticInfoTypeExclusionRules_QNAME, AggregateStatisticInfoType.ExclusionRules.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AggregateStatisticInfoType.class)
    public JAXBElement<SIFMetadataType> createAggregateStatisticInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AggregateStatisticInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createAggregateStatisticInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AggregateStatisticInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = ProgramStatusType.class)
    public JAXBElement<OtherCodeListType> createProgramStatusTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, ProgramStatusType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentRegistrationTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfoRefId", scope = AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentRegistrationTypeLEAInfoRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeLEAInfoRefId_QNAME, String.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentYearLevel", scope = AssessmentRegistrationType.class)
    public JAXBElement<YearLevelType> createAssessmentRegistrationTypeStudentYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_AssessmentRegistrationTypeStudentYearLevel_QNAME, YearLevelType.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentRegistrationType.StudentSpecialConditions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSpecialConditions", scope = AssessmentRegistrationType.class)
    public JAXBElement<AssessmentRegistrationType.StudentSpecialConditions> createAssessmentRegistrationTypeStudentSpecialConditions(AssessmentRegistrationType.StudentSpecialConditions value) {
        return new JAXBElement<AssessmentRegistrationType.StudentSpecialConditions>(_AssessmentRegistrationTypeStudentSpecialConditions_QNAME, AssessmentRegistrationType.StudentSpecialConditions.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonalRefId", scope = AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentRegistrationTypeStaffPersonalRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeStaffPersonalRefId_QNAME, String.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentRegistrationType.AssessmentStudentSnapshot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentStudentSnapshot", scope = AssessmentRegistrationType.class)
    public JAXBElement<AssessmentRegistrationType.AssessmentStudentSnapshot> createAssessmentRegistrationTypeAssessmentStudentSnapshot(AssessmentRegistrationType.AssessmentStudentSnapshot value) {
        return new JAXBElement<AssessmentRegistrationType.AssessmentStudentSnapshot>(_AssessmentRegistrationTypeAssessmentStudentSnapshot_QNAME, AssessmentRegistrationType.AssessmentStudentSnapshot.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentYearLevel", scope = AssessmentRegistrationType.class)
    public JAXBElement<YearLevelType> createAssessmentRegistrationTypeAssessmentYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_AssessmentRegistrationTypeAssessmentYearLevel_QNAME, YearLevelType.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AssessmentRegistrationType.class)
    public JAXBElement<SIFMetadataType> createAssessmentRegistrationTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AssessmentRegistrationType.class)
    public JAXBElement<SIFExtendedElementsType> createAssessmentRegistrationTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = SchoolProgramsType.SchoolProgramList.Program.class)
    public JAXBElement<OtherCodeListType> createSchoolProgramsTypeSchoolProgramListProgramOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, SchoolProgramsType.SchoolProgramList.Program.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Category", scope = SchoolProgramsType.SchoolProgramList.Program.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolProgramsTypeSchoolProgramListProgramCategory(String value) {
        return new JAXBElement<String>(_SchoolProgramsTypeSchoolProgramListProgramCategory_QNAME, String.class, SchoolProgramsType.SchoolProgramList.Program.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "IndividualBehaviourPlan", scope = StudentSDTNType.PastoralCare.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypePastoralCareIndividualBehaviourPlan(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypePastoralCareIndividualBehaviourPlan_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.PastoralCare.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DisciplinaryAbsences", scope = StudentSDTNType.PastoralCare.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentSDTNTypePastoralCareDisciplinaryAbsences(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypePastoralCareDisciplinaryAbsences_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentSDTNType.PastoralCare.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = CalendarDate.CalendarDateType.class)
    public JAXBElement<OtherCodeListType> createCalendarDateCalendarDateTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, CalendarDate.CalendarDateType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedDesc", scope = SIFErrorDataModelType.class)
    public JAXBElement<String> createSIFErrorDataModelTypeSIFExtendedDesc(String value) {
        return new JAXBElement<String>(_SIFErrorDataModelTypeSIFExtendedDesc_QNAME, String.class, SIFErrorDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SystemRoleType.class)
    public JAXBElement<SIFMetadataType> createSystemRoleTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SystemRoleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SystemRoleType.class)
    public JAXBElement<SIFExtendedElementsType> createSystemRoleTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SystemRoleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvince", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeStateProvince(String value) {
        return new JAXBElement<String>(_StateProvince_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AddressGlobalUID", scope = AddressType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAddressTypeAddressGlobalUID(String value) {
        return new JAXBElement<String>(_AddressTypeAddressGlobalUID_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalId", scope = AddressType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeLocalId(String value) {
        return new JAXBElement<String>(_LocalId_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RadioContact", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeRadioContact(String value) {
        return new JAXBElement<String>(_AddressTypeRadioContact_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType.MapReference }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MapReference", scope = AddressType.class)
    public JAXBElement<AddressType.MapReference> createAddressTypeMapReference(AddressType.MapReference value) {
        return new JAXBElement<AddressType.MapReference>(_AddressTypeMapReference_QNAME, AddressType.MapReference.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Country", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeCountry(String value) {
        return new JAXBElement<String>(_Country_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Community", scope = AddressType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeCommunity(String value) {
        return new JAXBElement<String>(_AddressTypeCommunity_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridLocationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GridLocation", scope = AddressType.class)
    public JAXBElement<GridLocationType> createAddressTypeGridLocation(GridLocationType value) {
        return new JAXBElement<GridLocationType>(_GridLocation_QNAME, GridLocationType.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType.StatisticalAreas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StatisticalAreas", scope = AddressType.class)
    public JAXBElement<AddressType.StatisticalAreas> createAddressTypeStatisticalAreas(AddressType.StatisticalAreas value) {
        return new JAXBElement<AddressType.StatisticalAreas>(_AddressTypeStatisticalAreas_QNAME, AddressType.StatisticalAreas.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AttendanceTerm", scope = TermInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createTermInfoTypeAttendanceTerm(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_TermInfoTypeAttendanceTerm_QNAME, AUCodeSetsYesOrNoCategoryType.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TermSpan", scope = TermInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTermInfoTypeTermSpan(String value) {
        return new JAXBElement<String>(_TermInfoTypeTermSpan_QNAME, String.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Track", scope = TermInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTermInfoTypeTrack(String value) {
        return new JAXBElement<String>(_TermInfoTypeTrack_QNAME, String.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MarkingTerm", scope = TermInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createTermInfoTypeMarkingTerm(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_TermInfoTypeMarkingTerm_QNAME, AUCodeSetsYesOrNoCategoryType.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TermCode", scope = TermInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTermInfoTypeTermCode(String value) {
        return new JAXBElement<String>(_TermInfoTypeTermCode_QNAME, String.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = TermInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTermInfoTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchedulingTerm", scope = TermInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createTermInfoTypeSchedulingTerm(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_TermInfoTypeSchedulingTerm_QNAME, AUCodeSetsYesOrNoCategoryType.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = TermInfoType.class)
    public JAXBElement<SIFMetadataType> createTermInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = TermInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createTermInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RelativeDuration", scope = TermInfoType.class)
    public JAXBElement<BigDecimal> createTermInfoTypeRelativeDuration(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TermInfoTypeRelativeDuration_QNAME, BigDecimal.class, TermInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.Components.Component.Strategies }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Strategies", scope = LearningResourceType.Components.Component.class)
    public JAXBElement<LearningResourceType.Components.Component.Strategies> createLearningResourceTypeComponentsComponentStrategies(LearningResourceType.Components.Component.Strategies value) {
        return new JAXBElement<LearningResourceType.Components.Component.Strategies>(_LearningResourceTypeComponentsComponentStrategies_QNAME, LearningResourceType.Components.Component.Strategies.class, LearningResourceType.Components.Component.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.Components.Component.AssociatedObjects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssociatedObjects", scope = LearningResourceType.Components.Component.class)
    public JAXBElement<LearningResourceType.Components.Component.AssociatedObjects> createLearningResourceTypeComponentsComponentAssociatedObjects(LearningResourceType.Components.Component.AssociatedObjects value) {
        return new JAXBElement<LearningResourceType.Components.Component.AssociatedObjects>(_LearningResourceTypeComponentsComponentAssociatedObjects_QNAME, LearningResourceType.Components.Component.AssociatedObjects.class, LearningResourceType.Components.Component.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = LearningResourceType.Components.Component.class)
    public JAXBElement<String> createLearningResourceTypeComponentsComponentDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, LearningResourceType.Components.Component.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvince", scope = LearningStandardItemType.StandardSettingBody.class)
    public JAXBElement<String> createLearningStandardItemTypeStandardSettingBodyStateProvince(String value) {
        return new JAXBElement<String>(_StateProvince_QNAME, String.class, LearningStandardItemType.StandardSettingBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SettingBodyName", scope = LearningStandardItemType.StandardSettingBody.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningStandardItemTypeStandardSettingBodySettingBodyName(String value) {
        return new JAXBElement<String>(_LearningStandardItemTypeStandardSettingBodySettingBodyName_QNAME, String.class, LearningStandardItemType.StandardSettingBody.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ResponseFeedback", scope = Sif3AssessmentItemType.ResponseChoices.Choice.class)
    public JAXBElement<String> createSif3AssessmentItemTypeResponseChoicesChoiceResponseFeedback(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeResponseChoicesChoiceResponseFeedback_QNAME, String.class, Sif3AssessmentItemType.ResponseChoices.Choice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CreditValue", scope = Sif3AssessmentItemType.ResponseChoices.Choice.class)
    public JAXBElement<Float> createSif3AssessmentItemTypeResponseChoicesChoiceCreditValue(Float value) {
        return new JAXBElement<Float>(_Sif3AssessmentItemTypeResponseChoicesChoiceCreditValue_QNAME, Float.class, Sif3AssessmentItemType.ResponseChoices.Choice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ChoiceLabel", scope = Sif3AssessmentItemType.ResponseChoices.Choice.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentItemTypeResponseChoicesChoiceChoiceLabel(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeResponseChoicesChoiceChoiceLabel_QNAME, String.class, Sif3AssessmentItemType.ResponseChoices.Choice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentSchoolEnrollmentType.ExitType.class)
    public JAXBElement<OtherCodeListType> createStudentSchoolEnrollmentTypeExitTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentSchoolEnrollmentType.ExitType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = LEAInfoType.EducationAgencyType.class)
    public JAXBElement<OtherCodeListType> createLEAInfoTypeEducationAgencyTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, LEAInfoType.EducationAgencyType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "UpperCut", scope = AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresUpperCut(String value) {
        return new JAXBElement<String>(_AssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresUpperCut_QNAME, String.class, AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LowerCut", scope = AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresLowerCut(String value) {
        return new JAXBElement<String>(_AssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresLowerCut_QNAME, String.class, AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentSchoolEnrollmentType.ExitStatus.class)
    public JAXBElement<OtherCodeListType> createStudentSchoolEnrollmentTypeExitStatusOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentSchoolEnrollmentType.ExitStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentPersonalRefId", scope = TeachingGroupType.StudentList.TeachingGroupStudent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeStudentListTeachingGroupStudentStudentPersonalRefId(String value) {
        return new JAXBElement<String>(_StudentSDTNTypeStudentPersonalRefId_QNAME, String.class, TeachingGroupType.StudentList.TeachingGroupStudent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScheduledStartDateTime", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentSessionTypeScheduledStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentSessionTypeScheduledStartDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSessionType.StaffPersonalRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonalRefIds", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<Sif3AssessmentSessionType.StaffPersonalRefIds> createSif3AssessmentSessionTypeStaffPersonalRefIds(Sif3AssessmentSessionType.StaffPersonalRefIds value) {
        return new JAXBElement<Sif3AssessmentSessionType.StaffPersonalRefIds>(_Sif3AssessmentSessionTypeStaffPersonalRefIds_QNAME, Sif3AssessmentSessionType.StaffPersonalRefIds.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScheduledEndDateTime", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentSessionTypeScheduledEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentSessionTypeScheduledEndDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ActualEndDateTime", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentSessionTypeActualEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentSessionTypeActualEndDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ActualStartDateTime", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentSessionTypeActualStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentSessionTypeActualStartDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentAdministrationRefId", scope = Sif3AssessmentSessionType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSessionTypeAssessmentAdministrationRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSessionTypeAssessmentAdministrationRefId_QNAME, String.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = Sif3AssessmentSessionType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSessionTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfoRefId", scope = Sif3AssessmentSessionType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSessionTypeLEAInfoRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeLEAInfoRefId_QNAME, String.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentFormRefId", scope = Sif3AssessmentSessionType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSessionTypeAssessmentFormRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSessionTypeAssessmentFormRefId_QNAME, String.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSessionType.UnusualEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "UnusualEvents", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<Sif3AssessmentSessionType.UnusualEvents> createSif3AssessmentSessionTypeUnusualEvents(Sif3AssessmentSessionType.UnusualEvents value) {
        return new JAXBElement<Sif3AssessmentSessionType.UnusualEvents>(_Sif3AssessmentSessionTypeUnusualEvents_QNAME, Sif3AssessmentSessionType.UnusualEvents.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentSessionTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<AddressType> createSif3AssessmentSessionTypeAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentSessionType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentSessionTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentSessionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link javax.xml.datatype.Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeOnItem", scope = Sif3StudentResponseSetType.Items.Item.class)
    public JAXBElement<javax.xml.datatype.Duration> createSif3StudentResponseSetTypeItemsItemTimeOnItem(javax.xml.datatype.Duration value) {
        return new JAXBElement<javax.xml.datatype.Duration>(_Sif3StudentResponseSetTypeItemsItemTimeOnItem_QNAME, javax.xml.datatype.Duration.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3StudentResponseSetType.Items.Item.TraitScores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TraitScores", scope = Sif3StudentResponseSetType.Items.Item.class)
    public JAXBElement<Sif3StudentResponseSetType.Items.Item.TraitScores> createSif3StudentResponseSetTypeItemsItemTraitScores(Sif3StudentResponseSetType.Items.Item.TraitScores value) {
        return new JAXBElement<Sif3StudentResponseSetType.Items.Item.TraitScores>(_Sif3StudentResponseSetTypeItemsItemTraitScores_QNAME, Sif3StudentResponseSetType.Items.Item.TraitScores.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AttemptStatus", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemAttemptStatus(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemAttemptStatus_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemScore", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemItemScore(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemItemScore_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ResponseLocation", scope = Sif3StudentResponseSetType.Items.Item.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemResponseLocation(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemResponseLocation_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3StudentResponseSetType.Items.Item.Comments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Comments", scope = Sif3StudentResponseSetType.Items.Item.class)
    public JAXBElement<Sif3StudentResponseSetType.Items.Item.Comments> createSif3StudentResponseSetTypeItemsItemComments(Sif3StudentResponseSetType.Items.Item.Comments value) {
        return new JAXBElement<Sif3StudentResponseSetType.Items.Item.Comments>(_Sif3StudentResponseSetTypeItemsItemComments_QNAME, Sif3StudentResponseSetType.Items.Item.Comments.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentRubricRefId", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemAssessmentRubricRefId(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemAssessmentRubricRefId_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3StudentResponseSetType.Items.Item.FeedbackItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FeedbackItems", scope = Sif3StudentResponseSetType.Items.Item.class)
    public JAXBElement<Sif3StudentResponseSetType.Items.Item.FeedbackItems> createSif3StudentResponseSetTypeItemsItemFeedbackItems(Sif3StudentResponseSetType.Items.Item.FeedbackItems value) {
        return new JAXBElement<Sif3StudentResponseSetType.Items.Item.FeedbackItems>(_Sif3StudentResponseSetTypeItemsItemFeedbackItems_QNAME, Sif3StudentResponseSetType.Items.Item.FeedbackItems.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ViewStatus", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemViewStatus(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemViewStatus_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemNumber", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemItemNumber(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemItemNumber_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ResponseCorrectness", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemResponseCorrectness(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemResponseCorrectness_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NumberOfAttempts", scope = Sif3StudentResponseSetType.Items.Item.class)
    public JAXBElement<Long> createSif3StudentResponseSetTypeItemsItemNumberOfAttempts(Long value) {
        return new JAXBElement<Long>(_Sif3StudentResponseSetTypeItemsItemNumberOfAttempts_QNAME, Long.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3StudentResponseSetType.Items.Item.ItemAids }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemAids", scope = Sif3StudentResponseSetType.Items.Item.class)
    public JAXBElement<Sif3StudentResponseSetType.Items.Item.ItemAids> createSif3StudentResponseSetTypeItemsItemItemAids(Sif3StudentResponseSetType.Items.Item.ItemAids value) {
        return new JAXBElement<Sif3StudentResponseSetType.Items.Item.ItemAids>(_Sif3StudentResponseSetTypeItemsItemItemAids_QNAME, Sif3StudentResponseSetType.Items.Item.ItemAids.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemScoreCode", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemItemScoreCode(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemItemScoreCode_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemName", scope = Sif3StudentResponseSetType.Items.Item.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemItemName(String value) {
        return new JAXBElement<String>(_AssessmentItemTypeItemName_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CreditValue", scope = AssessmentItemType.ResponseChoices.Choice.class)
    public JAXBElement<Float> createAssessmentItemTypeResponseChoicesChoiceCreditValue(Float value) {
        return new JAXBElement<Float>(_Sif3AssessmentItemTypeResponseChoicesChoiceCreditValue_QNAME, Float.class, AssessmentItemType.ResponseChoices.Choice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ChoiceLabel", scope = AssessmentItemType.ResponseChoices.Choice.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAssessmentItemTypeResponseChoicesChoiceChoiceLabel(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeResponseChoicesChoiceChoiceLabel_QNAME, String.class, AssessmentItemType.ResponseChoices.Choice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoleList", scope = SystemRoleType.SystemContextList.SystemContext.class)
    public JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList> createSystemRoleTypeSystemContextListSystemContextRoleList(SystemRoleType.SystemContextList.SystemContext.RoleList value) {
        return new JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList>(_SystemRoleTypeSystemContextListSystemContextRoleList_QNAME, SystemRoleType.SystemContextList.SystemContext.RoleList.class, SystemRoleType.SystemContextList.SystemContext.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = AssessmentAdministrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentAdministrationTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfoRefId", scope = AssessmentAdministrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentAdministrationTypeLEAInfoRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeLEAInfoRefId_QNAME, String.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentAdministrationType.SpecialConditions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SpecialConditions", scope = AssessmentAdministrationType.class)
    public JAXBElement<AssessmentAdministrationType.SpecialConditions> createAssessmentAdministrationTypeSpecialConditions(AssessmentAdministrationType.SpecialConditions value) {
        return new JAXBElement<AssessmentAdministrationType.SpecialConditions>(_AssessmentAdministrationTypeSpecialConditions_QNAME, AssessmentAdministrationType.SpecialConditions.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonalRefId", scope = AssessmentAdministrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentAdministrationTypeStaffPersonalRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeStaffPersonalRefId_QNAME, String.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FinishDateTime", scope = AssessmentAdministrationType.class)
    public JAXBElement<XMLGregorianCalendar> createAssessmentAdministrationTypeFinishDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeFinishDateTime_QNAME, XMLGregorianCalendar.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDateTime", scope = AssessmentAdministrationType.class)
    public JAXBElement<XMLGregorianCalendar> createAssessmentAdministrationTypeStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeStartDateTime_QNAME, XMLGregorianCalendar.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AdministrationName", scope = AssessmentAdministrationType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAssessmentAdministrationTypeAdministrationName(String value) {
        return new JAXBElement<String>(_AssessmentAdministrationTypeAdministrationName_QNAME, String.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DueDateTime", scope = AssessmentAdministrationType.class)
    public JAXBElement<XMLGregorianCalendar> createAssessmentAdministrationTypeDueDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeDueDateTime_QNAME, XMLGregorianCalendar.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AssessmentAdministrationType.class)
    public JAXBElement<SIFMetadataType> createAssessmentAdministrationTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AssessmentAdministrationType.class)
    public JAXBElement<SIFExtendedElementsType> createAssessmentAdministrationTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address", scope = AssessmentAdministrationType.class)
    public JAXBElement<AddressType> createAssessmentAdministrationTypeAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CutScores", scope = AssessmentItemType.PerformanceLevels.PerformanceLevel.class)
    public JAXBElement<AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores> createAssessmentItemTypePerformanceLevelsPerformanceLevelCutScores(AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores value) {
        return new JAXBElement<AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores>(_AssessmentItemTypePerformanceLevelsPerformanceLevelCutScores_QNAME, AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores.class, AssessmentItemType.PerformanceLevels.PerformanceLevel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = SubjectAreaType.class)
    public JAXBElement<OtherCodeListType> createSubjectAreaTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, SubjectAreaType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectArea", scope = ACStrandSubjectAreaType.class)
    public JAXBElement<SubjectAreaType> createACStrandSubjectAreaTypeSubjectArea(SubjectAreaType value) {
        return new JAXBElement<SubjectAreaType>(_SubjectArea_QNAME, SubjectAreaType.class, ACStrandSubjectAreaType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentParticipationType.ProgramFundingSources.ProgramFundingSource.class)
    public JAXBElement<OtherCodeListType> createStudentParticipationTypeProgramFundingSourcesProgramFundingSourceOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentParticipationType.ProgramFundingSources.ProgramFundingSource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LongName", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeLongName(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeLongName_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TeachingGroupType.TeachingGroupPeriodList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeachingGroupPeriodList", scope = TeachingGroupType.class)
    public JAXBElement<TeachingGroupType.TeachingGroupPeriodList> createTeachingGroupTypeTeachingGroupPeriodList(TeachingGroupType.TeachingGroupPeriodList value) {
        return new JAXBElement<TeachingGroupType.TeachingGroupPeriodList>(_TeachingGroupTypeTeachingGroupPeriodList_QNAME, TeachingGroupType.TeachingGroupPeriodList.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CurriculumLevel", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeCurriculumLevel(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeCurriculumLevel_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCourseLocalId", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeSchoolCourseLocalId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeSchoolCourseLocalId_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Semester", scope = TeachingGroupType.class)
    public JAXBElement<Long> createTeachingGroupTypeSemester(Long value) {
        return new JAXBElement<Long>(_TimeTableSubjectTypeSemester_QNAME, Long.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MaxClassSize", scope = TeachingGroupType.class)
    public JAXBElement<BigInteger> createTeachingGroupTypeMaxClassSize(BigInteger value) {
        return new JAXBElement<BigInteger>(_TeachingGroupTypeMaxClassSize_QNAME, BigInteger.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TeachingGroupType.TeacherList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeacherList", scope = TeachingGroupType.class)
    public JAXBElement<TeachingGroupType.TeacherList> createTeachingGroupTypeTeacherList(TeachingGroupType.TeacherList value) {
        return new JAXBElement<TeachingGroupType.TeacherList>(_TeachingGroupTypeTeacherList_QNAME, TeachingGroupType.TeacherList.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Set", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeSet(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeSet_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCourseInfoRefId", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeSchoolCourseInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolCourseInfoRefId_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableSubjectRefId", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTimeTableSubjectRefId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTimeTableSubjectRefId_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Block", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeBlock(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeBlock_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableSubjectLocalId", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTimeTableSubjectLocalId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTimeTableSubjectLocalId_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MinClassSize", scope = TeachingGroupType.class)
    public JAXBElement<BigInteger> createTeachingGroupTypeMinClassSize(BigInteger value) {
        return new JAXBElement<BigInteger>(_TeachingGroupTypeMinClassSize_QNAME, BigInteger.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TeachingGroupType.StudentList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentList", scope = TeachingGroupType.class)
    public JAXBElement<TeachingGroupType.StudentList> createTeachingGroupTypeStudentList(TeachingGroupType.StudentList value) {
        return new JAXBElement<TeachingGroupType.StudentList>(_TeachingGroupTypeStudentList_QNAME, TeachingGroupType.StudentList.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = TeachingGroupType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = TeachingGroupType.class)
    public JAXBElement<SIFMetadataType> createTeachingGroupTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = TeachingGroupType.class)
    public JAXBElement<SIFExtendedElementsType> createTeachingGroupTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, TeachingGroupType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LifeCycleType.Created.Creators }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Creators", scope = LifeCycleType.Created.class)
    public JAXBElement<LifeCycleType.Created.Creators> createLifeCycleTypeCreatedCreators(LifeCycleType.Created.Creators value) {
        return new JAXBElement<LifeCycleType.Created.Creators>(_LifeCycleTypeCreatedCreators_QNAME, LifeCycleType.Created.Creators.class, LifeCycleType.Created.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StreetType", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetStreetType(String value) {
        return new JAXBElement<String>(_AddressTypeStreetStreetType_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StreetSuffix", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetStreetSuffix(String value) {
        return new JAXBElement<String>(_AddressTypeStreetStreetSuffix_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ApartmentNumber", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetApartmentNumber(String value) {
        return new JAXBElement<String>(_AddressTypeStreetApartmentNumber_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StreetNumber", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetStreetNumber(String value) {
        return new JAXBElement<String>(_AddressTypeStreetStreetNumber_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Complex", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetComplex(String value) {
        return new JAXBElement<String>(_AddressTypeStreetComplex_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StreetPrefix", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetStreetPrefix(String value) {
        return new JAXBElement<String>(_AddressTypeStreetStreetPrefix_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Line3", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetLine3(String value) {
        return new JAXBElement<String>(_AddressTypeStreetLine3_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Line2", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetLine2(String value) {
        return new JAXBElement<String>(_AddressTypeStreetLine2_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ApartmentNumberSuffix", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetApartmentNumberSuffix(String value) {
        return new JAXBElement<String>(_AddressTypeStreetApartmentNumberSuffix_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ApartmentType", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetApartmentType(String value) {
        return new JAXBElement<String>(_AddressTypeStreetApartmentType_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StreetName", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetStreetName(String value) {
        return new JAXBElement<String>(_AddressTypeStreetStreetName_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ApartmentNumberPrefix", scope = AddressType.Street.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createAddressTypeStreetApartmentNumberPrefix(String value) {
        return new JAXBElement<String>(_AddressTypeStreetApartmentNumberPrefix_QNAME, String.class, AddressType.Street.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoleScopeRefId", scope = SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.class)
    public JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId> createSystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScopeRoleScopeRefId(SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId value) {
        return new JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId>(_SystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScopeRoleScopeRefId_QNAME, SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.RoleScopeRefId.class, SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoleScopeName", scope = SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScopeRoleScopeName(String value) {
        return new JAXBElement<String>(_SystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeListRoleScopeRoleScopeName_QNAME, String.class, SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.RoleScope.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TestingStatusDescription", scope = Sif3AssessmentRegistrationType.TestingStatuses.TestingStatus.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeTestingStatusesTestingStatusTestingStatusDescription(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeTestingStatusesTestingStatusTestingStatusDescription_QNAME, String.class, Sif3AssessmentRegistrationType.TestingStatuses.TestingStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPersonalType.AlertMessages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AlertMessages", scope = StudentPersonalType.class)
    public JAXBElement<StudentPersonalType.AlertMessages> createStudentPersonalTypeAlertMessages(StudentPersonalType.AlertMessages value) {
        return new JAXBElement<StudentPersonalType.AlertMessages>(_StudentPersonalTypeAlertMessages_QNAME, StudentPersonalType.AlertMessages.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OnTimeGraduationYear", scope = StudentPersonalType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentPersonalTypeOnTimeGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OnTimeGraduationYear_QNAME, XMLGregorianCalendar.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Disability", scope = StudentPersonalType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentPersonalTypeDisability(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeDisability_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = StudentPersonalType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YoungCarersRole", scope = StudentPersonalType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentPersonalTypeYoungCarersRole(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentSDTNTypeYoungCarersRole_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPersonalType.OtherIdList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherIdList", scope = StudentPersonalType.class)
    public JAXBElement<StudentPersonalType.OtherIdList> createStudentPersonalTypeOtherIdList(StudentPersonalType.OtherIdList value) {
        return new JAXBElement<StudentPersonalType.OtherIdList>(_StaffPersonalTypeOtherIdList_QNAME, StudentPersonalType.OtherIdList.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PrePrimaryEducation", scope = StudentPersonalType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypePrePrimaryEducation(String value) {
        return new JAXBElement<String>(_StudentPersonalTypePrePrimaryEducation_QNAME, String.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EconomicDisadvantage", scope = StudentPersonalType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentPersonalTypeEconomicDisadvantage(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeEconomicDisadvantage_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPersonalType.MedicalAlertMessages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MedicalAlertMessages", scope = StudentPersonalType.class)
    public JAXBElement<StudentPersonalType.MedicalAlertMessages> createStudentPersonalTypeMedicalAlertMessages(StudentPersonalType.MedicalAlertMessages value) {
        return new JAXBElement<StudentPersonalType.MedicalAlertMessages>(_StudentPersonalTypeMedicalAlertMessages_QNAME, StudentPersonalType.MedicalAlertMessages.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ESL", scope = StudentPersonalType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentPersonalTypeESL(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeESL_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AcceptableUsePolicy", scope = StudentPersonalType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentPersonalTypeAcceptableUsePolicy(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeAcceptableUsePolicy_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProjectedGraduationYear", scope = StudentPersonalType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentPersonalTypeProjectedGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ProjectedGraduationYear_QNAME, XMLGregorianCalendar.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GraduationDate", scope = StudentPersonalType.class)
    public JAXBElement<String> createStudentPersonalTypeGraduationDate(String value) {
        return new JAXBElement<String>(_GraduationDate_QNAME, String.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FirstAUSchoolEnrollment", scope = StudentPersonalType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentPersonalTypeFirstAUSchoolEnrollment(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentPersonalTypeFirstAUSchoolEnrollment_QNAME, XMLGregorianCalendar.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElectronicIdListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ElectronicIdList", scope = StudentPersonalType.class)
    public JAXBElement<ElectronicIdListType> createStudentPersonalTypeElectronicIdList(ElectronicIdListType value) {
        return new JAXBElement<ElectronicIdListType>(_ElectronicIdList_QNAME, ElectronicIdListType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "IntegrationAide", scope = StudentPersonalType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentPersonalTypeIntegrationAide(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeIntegrationAide_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPersonalType.MostRecent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MostRecent", scope = StudentPersonalType.class)
    public JAXBElement<StudentPersonalType.MostRecent> createStudentPersonalTypeMostRecent(StudentPersonalType.MostRecent value) {
        return new JAXBElement<StudentPersonalType.MostRecent>(_StudentPersonalTypeMostRecent_QNAME, StudentPersonalType.MostRecent.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GiftedTalented", scope = StudentPersonalType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentPersonalTypeGiftedTalented(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeGiftedTalented_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentPersonalType.class)
    public JAXBElement<SIFMetadataType> createStudentPersonalTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentPersonalType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentPersonalTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningResourcePackageRefId", scope = LearningResourceType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLearningResourceTypeLearningResourcePackageRefId(String value) {
        return new JAXBElement<String>(_LearningResourceTypeLearningResourcePackageRefId_QNAME, String.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Author", scope = LearningResourceType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningResourceTypeAuthor(String value) {
        return new JAXBElement<String>(_LearningResourceTypeAuthor_QNAME, String.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.Location }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Location", scope = LearningResourceType.class)
    public JAXBElement<LearningResourceType.Location> createLearningResourceTypeLocation(LearningResourceType.Location value) {
        return new JAXBElement<LearningResourceType.Location>(_Location_QNAME, LearningResourceType.Location.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.SubjectAreas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectAreas", scope = LearningResourceType.class)
    public JAXBElement<LearningResourceType.SubjectAreas> createLearningResourceTypeSubjectAreas(LearningResourceType.SubjectAreas value) {
        return new JAXBElement<LearningResourceType.SubjectAreas>(_LearningResourceTypeSubjectAreas_QNAME, LearningResourceType.SubjectAreas.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.Approvals }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Approvals", scope = LearningResourceType.class)
    public JAXBElement<LearningResourceType.Approvals> createLearningResourceTypeApprovals(LearningResourceType.Approvals value) {
        return new JAXBElement<LearningResourceType.Approvals>(_LearningResourceTypeApprovals_QNAME, LearningResourceType.Approvals.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = LearningResourceType.class)
    public JAXBElement<String> createLearningResourceTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "UseAgreement", scope = LearningResourceType.class)
    public JAXBElement<String> createLearningResourceTypeUseAgreement(String value) {
        return new JAXBElement<String>(_LearningResourceTypeUseAgreement_QNAME, String.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.Contacts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Contacts", scope = LearningResourceType.class)
    public JAXBElement<LearningResourceType.Contacts> createLearningResourceTypeContacts(LearningResourceType.Contacts value) {
        return new JAXBElement<LearningResourceType.Contacts>(_LearningResourceTypeContacts_QNAME, LearningResourceType.Contacts.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.MediaTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MediaTypes", scope = LearningResourceType.class)
    public JAXBElement<LearningResourceType.MediaTypes> createLearningResourceTypeMediaTypes(LearningResourceType.MediaTypes value) {
        return new JAXBElement<LearningResourceType.MediaTypes>(_LearningResourceTypeMediaTypes_QNAME, LearningResourceType.MediaTypes.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AgreementDate", scope = LearningResourceType.class)
    public JAXBElement<XMLGregorianCalendar> createLearningResourceTypeAgreementDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningResourceTypeAgreementDate_QNAME, XMLGregorianCalendar.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Status", scope = LearningResourceType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningResourceTypeStatus(String value) {
        return new JAXBElement<String>(_LearningResourceTypeStatus_QNAME, String.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.Evaluations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Evaluations", scope = LearningResourceType.class)
    public JAXBElement<LearningResourceType.Evaluations> createLearningResourceTypeEvaluations(LearningResourceType.Evaluations value) {
        return new JAXBElement<LearningResourceType.Evaluations>(_LearningResourceTypeEvaluations_QNAME, LearningResourceType.Evaluations.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = LearningResourceType.class)
    public JAXBElement<SIFMetadataType> createLearningResourceTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = LearningResourceType.class)
    public JAXBElement<SIFExtendedElementsType> createLearningResourceTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = LearningResourceType.class)
    public JAXBElement<YearLevelsType> createLearningResourceTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningResourceType.LearningStandards }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandards", scope = LearningResourceType.class)
    public JAXBElement<LearningResourceType.LearningStandards> createLearningResourceTypeLearningStandards(LearningResourceType.LearningStandards value) {
        return new JAXBElement<LearningResourceType.LearningStandards>(_LearningResourceTypeLearningStandards_QNAME, LearningResourceType.LearningStandards.class, LearningResourceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportManifestType.ReportFormatList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportFormatList", scope = ReportManifestType.class)
    public JAXBElement<ReportManifestType.ReportFormatList> createReportManifestTypeReportFormatList(ReportManifestType.ReportFormatList value) {
        return new JAXBElement<ReportManifestType.ReportFormatList>(_ReportManifestTypeReportFormatList_QNAME, ReportManifestType.ReportFormatList.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportManifestType.ReceivingAuthority }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReceivingAuthority", scope = ReportManifestType.class)
    public JAXBElement<ReportManifestType.ReceivingAuthority> createReportManifestTypeReceivingAuthority(ReportManifestType.ReceivingAuthority value) {
        return new JAXBElement<ReportManifestType.ReceivingAuthority>(_ReportManifestTypeReceivingAuthority_QNAME, ReportManifestType.ReceivingAuthority.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportManifestType.ReportingPeriod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportingPeriod", scope = ReportManifestType.class)
    public JAXBElement<ReportManifestType.ReportingPeriod> createReportManifestTypeReportingPeriod(ReportManifestType.ReportingPeriod value) {
        return new JAXBElement<ReportManifestType.ReportingPeriod>(_ReportManifestTypeReportingPeriod_QNAME, ReportManifestType.ReportingPeriod.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedQueryDataModelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedQuery", scope = ReportManifestType.class)
    public JAXBElement<SIFExtendedQueryDataModelType> createReportManifestTypeSIFExtendedQuery(SIFExtendedQueryDataModelType value) {
        return new JAXBElement<SIFExtendedQueryDataModelType>(_ReportManifestTypeSIFExtendedQuery_QNAME, SIFExtendedQueryDataModelType.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = ReportManifestType.class)
    public JAXBElement<String> createReportManifestTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = ReportManifestType.class)
    public JAXBElement<SIFMetadataType> createReportManifestTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportManifestType.SIFQueryGroup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_QueryGroup", scope = ReportManifestType.class)
    public JAXBElement<ReportManifestType.SIFQueryGroup> createReportManifestTypeSIFQueryGroup(ReportManifestType.SIFQueryGroup value) {
        return new JAXBElement<ReportManifestType.SIFQueryGroup>(_ReportManifestTypeSIFQueryGroup_QNAME, ReportManifestType.SIFQueryGroup.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = ReportManifestType.class)
    public JAXBElement<SIFExtendedElementsType> createReportManifestTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, ReportManifestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = Sif3AssessmentAdministrationType.Organizations.Organization.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAdministrationTypeOrganizationsOrganizationSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, Sif3AssessmentAdministrationType.Organizations.Organization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfoRefId", scope = Sif3AssessmentAdministrationType.Organizations.Organization.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAdministrationTypeOrganizationsOrganizationLEAInfoRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeLEAInfoRefId_QNAME, String.class, Sif3AssessmentAdministrationType.Organizations.Organization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CommentDescription", scope = Sif3StudentResponseSetType.Items.Item.Comments.Comment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemCommentsCommentCommentDescription(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemCommentsCommentCommentDescription_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.Comments.Comment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartTime", scope = SessionInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createSessionInfoTypeStartTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStartTime_QNAME, XMLGregorianCalendar.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RollMarked", scope = SessionInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSessionInfoTypeRollMarked(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_SessionInfoTypeRollMarked_QNAME, AUCodeSetsYesOrNoCategoryType.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoomNumber", scope = SessionInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSessionInfoTypeRoomNumber(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodRoomNumber_QNAME, String.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = SessionInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSessionInfoTypeSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FinishTime", scope = SessionInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createSessionInfoTypeFinishTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SessionInfoTypeFinishTime_QNAME, XMLGregorianCalendar.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SessionInfoType.class)
    public JAXBElement<SIFMetadataType> createSessionInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonalLocalId", scope = SessionInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSessionInfoTypeStaffPersonalLocalId(String value) {
        return new JAXBElement<String>(_SessionInfoTypeStaffPersonalLocalId_QNAME, String.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SessionInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createSessionInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SessionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentRegistrationRefId", scope = StudentScoreSetType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentScoreSetTypeAssessmentRegistrationRefId(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeAssessmentRegistrationRefId_QNAME, String.class, StudentScoreSetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FinishDateTime", scope = StudentScoreSetType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentScoreSetTypeFinishDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeFinishDateTime_QNAME, XMLGregorianCalendar.class, StudentScoreSetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDateTime", scope = StudentScoreSetType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentScoreSetTypeStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeStartDateTime_QNAME, XMLGregorianCalendar.class, StudentScoreSetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentScoreSetType.class)
    public JAXBElement<SIFMetadataType> createStudentScoreSetTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentScoreSetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentScoreSetType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentScoreSetTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentScoreSetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EducationFilterType.LearningStandardItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItems", scope = EducationFilterType.class)
    public JAXBElement<EducationFilterType.LearningStandardItems> createEducationFilterTypeLearningStandardItems(EducationFilterType.LearningStandardItems value) {
        return new JAXBElement<EducationFilterType.LearningStandardItems>(_LearningStandardItems_QNAME, EducationFilterType.LearningStandardItems.class, EducationFilterType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsProgressLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Result", scope = StudentSDTNType.OtherLearningAreasList.OtherLearningArea.class)
    public JAXBElement<AUCodeSetsProgressLevelType> createStudentSDTNTypeOtherLearningAreasListOtherLearningAreaResult(AUCodeSetsProgressLevelType value) {
        return new JAXBElement<AUCodeSetsProgressLevelType>(_StudentSDTNTypeOtherLearningAreasListOtherLearningAreaResult_QNAME, AUCodeSetsProgressLevelType.class, StudentSDTNType.OtherLearningAreasList.OtherLearningArea.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentLocalId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStudentStudentLocalId(String value) {
        return new JAXBElement<String>(_SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStudentStudentLocalId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RoomInfoType.StaffList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffList", scope = RoomInfoType.class)
    public JAXBElement<RoomInfoType.StaffList> createRoomInfoTypeStaffList(RoomInfoType.StaffList value) {
        return new JAXBElement<RoomInfoType.StaffList>(_RoomInfoTypeStaffList_QNAME, RoomInfoType.StaffList.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumber", scope = RoomInfoType.class)
    public JAXBElement<PhoneNumberType> createRoomInfoTypePhoneNumber(PhoneNumberType value) {
        return new JAXBElement<PhoneNumberType>(_PhoneNumber_QNAME, PhoneNumberType.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Capacity", scope = RoomInfoType.class)
    public JAXBElement<Long> createRoomInfoTypeCapacity(Long value) {
        return new JAXBElement<Long>(_RoomInfoTypeCapacity_QNAME, Long.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HomeroomNumber", scope = RoomInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createRoomInfoTypeHomeroomNumber(String value) {
        return new JAXBElement<String>(_HomeroomNumber_QNAME, String.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Size", scope = RoomInfoType.class)
    public JAXBElement<BigDecimal> createRoomInfoTypeSize(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_RoomInfoTypeSize_QNAME, BigDecimal.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = RoomInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createRoomInfoTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = RoomInfoType.class)
    public JAXBElement<SIFMetadataType> createRoomInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = RoomInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createRoomInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Building", scope = RoomInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createRoomInfoTypeBuilding(String value) {
        return new JAXBElement<String>(_RoomInfoTypeBuilding_QNAME, String.class, RoomInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType.StatisticalAreas.StatisticalArea }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StatisticalArea", scope = AddressType.StatisticalAreas.class)
    public JAXBElement<AddressType.StatisticalAreas.StatisticalArea> createAddressTypeStatisticalAreasStatisticalArea(AddressType.StatisticalAreas.StatisticalArea value) {
        return new JAXBElement<AddressType.StatisticalAreas.StatisticalArea>(_AddressTypeStatisticalAreasStatisticalArea_QNAME, AddressType.StatisticalAreas.StatisticalArea.class, AddressType.StatisticalAreas.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ListedStatus", scope = PhoneNumberType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createPhoneNumberTypeListedStatus(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_PhoneNumberTypeListedStatus_QNAME, AUCodeSetsYesOrNoCategoryType.class, PhoneNumberType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Extension", scope = PhoneNumberType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createPhoneNumberTypeExtension(String value) {
        return new JAXBElement<String>(_PhoneNumberTypeExtension_QNAME, String.class, PhoneNumberType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonalRefId", scope = TeachingGroupType.TeacherList.TeachingGroupTeacher.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTeachingGroupTypeTeacherListTeachingGroupTeacherStaffPersonalRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeStaffPersonalRefId_QNAME, String.class, TeachingGroupType.TeacherList.TeachingGroupTeacher.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ColumnDescription", scope = ResourceUsageType.ResourceReportColumnList.ResourceReportColumn.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createResourceUsageTypeResourceReportColumnListResourceReportColumnColumnDescription(String value) {
        return new JAXBElement<String>(_ResourceUsageTypeResourceReportColumnListResourceReportColumnColumnDescription_QNAME, String.class, ResourceUsageType.ResourceReportColumnList.ResourceReportColumn.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ColumnDelimiter", scope = ResourceUsageType.ResourceReportColumnList.ResourceReportColumn.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createResourceUsageTypeResourceReportColumnListResourceReportColumnColumnDelimiter(String value) {
        return new JAXBElement<String>(_ResourceUsageTypeResourceReportColumnListResourceReportColumnColumnDelimiter_QNAME, String.class, ResourceUsageType.ResourceReportColumnList.ResourceReportColumn.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubjects", scope = Sif3AssessmentType.class)
    public JAXBElement<SubjectAreaListType> createSif3AssessmentTypeAssessmentSubjects(SubjectAreaListType value) {
        return new JAXBElement<SubjectAreaListType>(_Sif3AssessmentTypeAssessmentSubjects_QNAME, SubjectAreaListType.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentProvider", scope = Sif3AssessmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentTypeAssessmentProvider(String value) {
        return new JAXBElement<String>(_Sif3AssessmentTypeAssessmentProvider_QNAME, String.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentType.AssessmentIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentIdentifiers", scope = Sif3AssessmentType.class)
    public JAXBElement<Sif3AssessmentType.AssessmentIdentifiers> createSif3AssessmentTypeAssessmentIdentifiers(Sif3AssessmentType.AssessmentIdentifiers value) {
        return new JAXBElement<Sif3AssessmentType.AssessmentIdentifiers>(_Sif3AssessmentTypeAssessmentIdentifiers_QNAME, Sif3AssessmentType.AssessmentIdentifiers.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentType.AssessmentItemBanks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItemBanks", scope = Sif3AssessmentType.class)
    public JAXBElement<Sif3AssessmentType.AssessmentItemBanks> createSif3AssessmentTypeAssessmentItemBanks(Sif3AssessmentType.AssessmentItemBanks value) {
        return new JAXBElement<Sif3AssessmentType.AssessmentItemBanks>(_Sif3AssessmentTypeAssessmentItemBanks_QNAME, Sif3AssessmentType.AssessmentItemBanks.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentPackageRefId", scope = Sif3AssessmentType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentTypeAssessmentPackageRefId(String value) {
        return new JAXBElement<String>(_AssessmentTypeAssessmentPackageRefId_QNAME, String.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentType.LearningStandardItemRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItemRefIds", scope = Sif3AssessmentType.class)
    public JAXBElement<Sif3AssessmentType.LearningStandardItemRefIds> createSif3AssessmentTypeLearningStandardItemRefIds(Sif3AssessmentType.LearningStandardItemRefIds value) {
        return new JAXBElement<Sif3AssessmentType.LearningStandardItemRefIds>(_Sif3AssessmentTypeLearningStandardItemRefIds_QNAME, Sif3AssessmentType.LearningStandardItemRefIds.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentLanguages", scope = Sif3AssessmentType.class)
    public JAXBElement<LanguageListType> createSif3AssessmentTypeAssessmentLanguages(LanguageListType value) {
        return new JAXBElement<LanguageListType>(_Sif3AssessmentTypeAssessmentLanguages_QNAME, LanguageListType.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentType.AssessmentDescriptors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentDescriptors", scope = Sif3AssessmentType.class)
    public JAXBElement<Sif3AssessmentType.AssessmentDescriptors> createSif3AssessmentTypeAssessmentDescriptors(Sif3AssessmentType.AssessmentDescriptors value) {
        return new JAXBElement<Sif3AssessmentType.AssessmentDescriptors>(_AssessmentTypeAssessmentDescriptors_QNAME, Sif3AssessmentType.AssessmentDescriptors.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentGradeLevels", scope = Sif3AssessmentType.class)
    public JAXBElement<YearLevelsType> createSif3AssessmentTypeAssessmentGradeLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_Sif3AssessmentTypeAssessmentGradeLevels_QNAME, YearLevelsType.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDateTime", scope = TimeElementType.class)
    public JAXBElement<XMLGregorianCalendar> createTimeElementTypeEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TimeElementTypeEndDateTime_QNAME, XMLGregorianCalendar.class, TimeElementType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeElementType.SpanGaps }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SpanGaps", scope = TimeElementType.class)
    public JAXBElement<TimeElementType.SpanGaps> createTimeElementTypeSpanGaps(TimeElementType.SpanGaps value) {
        return new JAXBElement<TimeElementType.SpanGaps>(_TimeElementTypeSpanGaps_QNAME, TimeElementType.SpanGaps.class, TimeElementType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDateTime", scope = TimeElementType.class)
    public JAXBElement<XMLGregorianCalendar> createTimeElementTypeStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeStartDateTime_QNAME, XMLGregorianCalendar.class, TimeElementType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LifeCycleType.Created }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Created", scope = LifeCycleType.class)
    public JAXBElement<LifeCycleType.Created> createLifeCycleTypeCreated(LifeCycleType.Created value) {
        return new JAXBElement<LifeCycleType.Created>(_LifeCycleTypeCreated_QNAME, LifeCycleType.Created.class, LifeCycleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LifeCycleType.TimeElements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeElements", scope = LifeCycleType.class)
    public JAXBElement<LifeCycleType.TimeElements> createLifeCycleTypeTimeElements(LifeCycleType.TimeElements value) {
        return new JAXBElement<LifeCycleType.TimeElements>(_LifeCycleTypeTimeElements_QNAME, LifeCycleType.TimeElements.class, LifeCycleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LifeCycleType.ModificationHistory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ModificationHistory", scope = LifeCycleType.class)
    public JAXBElement<LifeCycleType.ModificationHistory> createLifeCycleTypeModificationHistory(LifeCycleType.ModificationHistory value) {
        return new JAXBElement<LifeCycleType.ModificationHistory>(_LifeCycleTypeModificationHistory_QNAME, LifeCycleType.ModificationHistory.class, LifeCycleType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = StudentSnapshotType.HomeEnrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeHomeEnrollmentSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolName", scope = StudentSnapshotType.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeHomeEnrollmentSchoolName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentSchoolName_QNAME, String.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentSnapshotType.HomeEnrollment.Homeroom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Homeroom", scope = StudentSnapshotType.HomeEnrollment.class)
    public JAXBElement<StudentSnapshotType.HomeEnrollment.Homeroom> createStudentSnapshotTypeHomeEnrollmentHomeroom(StudentSnapshotType.HomeEnrollment.Homeroom value) {
        return new JAXBElement<StudentSnapshotType.HomeEnrollment.Homeroom>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentHomeroom_QNAME, StudentSnapshotType.HomeEnrollment.Homeroom.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevel", scope = StudentSnapshotType.HomeEnrollment.class)
    public JAXBElement<YearLevelType> createStudentSnapshotTypeHomeEnrollmentYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_YearLevel_QNAME, YearLevelType.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalId", scope = StudentSnapshotType.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeHomeEnrollmentLocalId(String value) {
        return new JAXBElement<String>(_LocalId_QNAME, String.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HomeroomNumber", scope = StudentSnapshotType.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeHomeEnrollmentHomeroomNumber(String value) {
        return new JAXBElement<String>(_HomeroomNumber_QNAME, String.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSchoolEnrollmentRefId", scope = StudentSnapshotType.HomeEnrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeHomeEnrollmentStudentSchoolEnrollmentRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentStudentSchoolEnrollmentRefId_QNAME, String.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolNo", scope = StudentSnapshotType.HomeEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeHomeEnrollmentSchoolNo(String value) {
        return new JAXBElement<String>(_StudentSnapshotTypeHomeEnrollmentSchoolNo_QNAME, String.class, StudentSnapshotType.HomeEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MainlySpeaksEnglishAtHome", scope = StudentContactRelationshipType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeMainlySpeaksEnglishAtHome(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeMainlySpeaksEnglishAtHome_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsSourceCodeTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactSequenceSource", scope = StudentContactRelationshipType.class)
    public JAXBElement<AUCodeSetsSourceCodeTypeType> createStudentContactRelationshipTypeContactSequenceSource(AUCodeSetsSourceCodeTypeType value) {
        return new JAXBElement<AUCodeSetsSourceCodeTypeType>(_StudentContactRelationshipTypeContactSequenceSource_QNAME, AUCodeSetsSourceCodeTypeType.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentContactRelationshipRefId", scope = StudentContactRelationshipType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentContactRelationshipTypeStudentContactRelationshipRefId(String value) {
        return new JAXBElement<String>(_StudentContactRelationshipTypeStudentContactRelationshipRefId_QNAME, String.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentContactRelationshipType.ContactFlags }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactFlags", scope = StudentContactRelationshipType.class)
    public JAXBElement<StudentContactRelationshipType.ContactFlags> createStudentContactRelationshipTypeContactFlags(StudentContactRelationshipType.ContactFlags value) {
        return new JAXBElement<StudentContactRelationshipType.ContactFlags>(_StudentContactRelationshipTypeContactFlags_QNAME, StudentContactRelationshipType.ContactFlags.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentContactRelationshipType.HouseholdList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HouseholdList", scope = StudentContactRelationshipType.class)
    public JAXBElement<StudentContactRelationshipType.HouseholdList> createStudentContactRelationshipTypeHouseholdList(StudentContactRelationshipType.HouseholdList value) {
        return new JAXBElement<StudentContactRelationshipType.HouseholdList>(_StudentContactRelationshipTypeHouseholdList_QNAME, StudentContactRelationshipType.HouseholdList.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactSequence", scope = StudentContactRelationshipType.class)
    public JAXBElement<Long> createStudentContactRelationshipTypeContactSequence(Long value) {
        return new JAXBElement<Long>(_StudentContactRelationshipTypeContactSequence_QNAME, Long.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentContactRelationshipType.class)
    public JAXBElement<SIFMetadataType> createStudentContactRelationshipTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ParentRelationshipStatus", scope = StudentContactRelationshipType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentContactRelationshipTypeParentRelationshipStatus(String value) {
        return new JAXBElement<String>(_StudentContactRelationshipTypeParentRelationshipStatus_QNAME, String.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentContactRelationshipType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentContactRelationshipTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentContactRelationshipType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = LEAInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLEAInfoTypeStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SLA", scope = LEAInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLEAInfoTypeSLA(String value) {
        return new JAXBElement<String>(_LEAInfoTypeSLA_QNAME, String.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LEAInfoType.EducationAgencyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EducationAgencyType", scope = LEAInfoType.class)
    public JAXBElement<LEAInfoType.EducationAgencyType> createLEAInfoTypeEducationAgencyType(LEAInfoType.EducationAgencyType value) {
        return new JAXBElement<LEAInfoType.EducationAgencyType>(_LEAInfoTypeEducationAgencyType_QNAME, LEAInfoType.EducationAgencyType.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsOperationalStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OperationalStatus", scope = LEAInfoType.class)
    public JAXBElement<AUCodeSetsOperationalStatusType> createLEAInfoTypeOperationalStatus(AUCodeSetsOperationalStatusType value) {
        return new JAXBElement<AUCodeSetsOperationalStatusType>(_OperationalStatus_QNAME, AUCodeSetsOperationalStatusType.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CommonwealthId", scope = LEAInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLEAInfoTypeCommonwealthId(String value) {
        return new JAXBElement<String>(_LEAInfoTypeCommonwealthId_QNAME, String.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AddressList", scope = LEAInfoType.class)
    public JAXBElement<AddressListType> createLEAInfoTypeAddressList(AddressListType value) {
        return new JAXBElement<AddressListType>(_AddressList_QNAME, AddressListType.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LEAInfoType.LEAContactList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAContactList", scope = LEAInfoType.class)
    public JAXBElement<LEAInfoType.LEAContactList> createLEAInfoTypeLEAContactList(LEAInfoType.LEAContactList value) {
        return new JAXBElement<LEAInfoType.LEAContactList>(_LEAInfoTypeLEAContactList_QNAME, LEAInfoType.LEAContactList.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumberList", scope = LEAInfoType.class)
    public JAXBElement<PhoneNumberListType> createLEAInfoTypePhoneNumberList(PhoneNumberListType value) {
        return new JAXBElement<PhoneNumberListType>(_PhoneNumberList_QNAME, PhoneNumberListType.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = LEAInfoType.class)
    public JAXBElement<SIFMetadataType> createLEAInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAURL", scope = LEAInfoType.class)
    public JAXBElement<String> createLEAInfoTypeLEAURL(String value) {
        return new JAXBElement<String>(_LEAInfoTypeLEAURL_QNAME, String.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = LEAInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createLEAInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "JurisdictionLowerHouse", scope = LEAInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLEAInfoTypeJurisdictionLowerHouse(String value) {
        return new JAXBElement<String>(_LEAInfoTypeJurisdictionLowerHouse_QNAME, String.class, LEAInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PreferredFamilyNameFirst", scope = BaseNameType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createBaseNameTypePreferredFamilyNameFirst(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_BaseNameTypePreferredFamilyNameFirst_QNAME, AUCodeSetsYesOrNoCategoryType.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PreferredFamilyName", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypePreferredFamilyName(String value) {
        return new JAXBElement<String>(_BaseNameTypePreferredFamilyName_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FamilyName", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypeFamilyName(String value) {
        return new JAXBElement<String>(_BaseNameTypeFamilyName_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Title", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypeTitle(String value) {
        return new JAXBElement<String>(_StaffPersonalTypeTitle_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PreferredGivenName", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypePreferredGivenName(String value) {
        return new JAXBElement<String>(_BaseNameTypePreferredGivenName_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FamilyNameFirst", scope = BaseNameType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createBaseNameTypeFamilyNameFirst(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_BaseNameTypeFamilyNameFirst_QNAME, AUCodeSetsYesOrNoCategoryType.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MiddleName", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypeMiddleName(String value) {
        return new JAXBElement<String>(_BaseNameTypeMiddleName_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FullName", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypeFullName(String value) {
        return new JAXBElement<String>(_BaseNameTypeFullName_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Suffix", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypeSuffix(String value) {
        return new JAXBElement<String>(_BaseNameTypeSuffix_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GivenName", scope = BaseNameType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createBaseNameTypeGivenName(String value) {
        return new JAXBElement<String>(_BaseNameTypeGivenName_QNAME, String.class, BaseNameType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItemBankName", scope = Sif3AssessmentType.AssessmentItemBanks.AssessmentItemBank.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentTypeAssessmentItemBanksAssessmentItemBankAssessmentItemBankName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeAssessmentItemBanksAssessmentItemBankAssessmentItemBankName_QNAME, String.class, Sif3AssessmentType.AssessmentItemBanks.AssessmentItemBank.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = SIFReportObjectType.ReportInfo.class)
    public JAXBElement<String> createSIFReportObjectTypeReportInfoDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, SIFReportObjectType.ReportInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportSubmitterInfo", scope = SIFReportObjectType.ReportInfo.class)
    public JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo> createSIFReportObjectTypeReportInfoReportSubmitterInfo(SIFReportObjectType.ReportInfo.ReportSubmitterInfo value) {
        return new JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo>(_SIFReportObjectTypeReportInfoReportSubmitterInfo_QNAME, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class, SIFReportObjectType.ReportInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Role", scope = ContactInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createContactInfoTypeRole(String value) {
        return new JAXBElement<String>(_StudentActivityParticipationTypeRole_QNAME, String.class, ContactInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumberList", scope = ContactInfoType.class)
    public JAXBElement<PhoneNumberListType> createContactInfoTypePhoneNumberList(PhoneNumberListType value) {
        return new JAXBElement<PhoneNumberListType>(_PhoneNumberList_QNAME, PhoneNumberListType.class, ContactInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PositionTitle", scope = ContactInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createContactInfoTypePositionTitle(String value) {
        return new JAXBElement<String>(_ContactInfoTypePositionTitle_QNAME, String.class, ContactInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmailList", scope = ContactInfoType.class)
    public JAXBElement<EmailListType> createContactInfoTypeEmailList(EmailListType value) {
        return new JAXBElement<EmailListType>(_EmailList_QNAME, EmailListType.class, ContactInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address", scope = ContactInfoType.class)
    public JAXBElement<AddressType> createContactInfoTypeAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, ContactInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TechnicalRequirement", scope = ActivityType.TechnicalRequirements.class)
    public JAXBElement<String> createActivityTypeTechnicalRequirementsTechnicalRequirement(String value) {
        return new JAXBElement<String>(_ActivityTypeTechnicalRequirementsTechnicalRequirement_QNAME, String.class, ActivityType.TechnicalRequirements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LifeCycleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LifeCycle", scope = SIFMetadataType.class)
    public JAXBElement<LifeCycleType> createSIFMetadataTypeLifeCycle(LifeCycleType value) {
        return new JAXBElement<LifeCycleType>(_LifeCycle_QNAME, LifeCycleType.class, SIFMetadataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EducationFilterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EducationFilter", scope = SIFMetadataType.class)
    public JAXBElement<EducationFilterType> createSIFMetadataTypeEducationFilter(EducationFilterType value) {
        return new JAXBElement<EducationFilterType>(_EducationFilter_QNAME, EducationFilterType.class, SIFMetadataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType.TimeElements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeElements", scope = SIFMetadataType.class)
    public JAXBElement<SIFMetadataType.TimeElements> createSIFMetadataTypeTimeElements(SIFMetadataType.TimeElements value) {
        return new JAXBElement<SIFMetadataType.TimeElements>(_LifeCycleTypeTimeElements_QNAME, SIFMetadataType.TimeElements.class, SIFMetadataType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "VisaStatisticalCode", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeVisaStatisticalCode(String value) {
        return new JAXBElement<String>(_DemographicsTypeVisaStatisticalCode_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PlaceOfBirth", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypePlaceOfBirth(String value) {
        return new JAXBElement<String>(_DemographicsTypePlaceOfBirth_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LanguageList", scope = DemographicsType.class)
    public JAXBElement<LanguageListType> createDemographicsTypeLanguageList(LanguageListType value) {
        return new JAXBElement<LanguageListType>(_LanguageList_QNAME, LanguageListType.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "VisaExpiryDate", scope = DemographicsType.class)
    public JAXBElement<XMLGregorianCalendar> createDemographicsTypeVisaExpiryDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DemographicsTypeVisaExpiryDate_QNAME, XMLGregorianCalendar.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BirthDate", scope = DemographicsType.class)
    public JAXBElement<XMLGregorianCalendar> createDemographicsTypeBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BirthDate_QNAME, XMLGregorianCalendar.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CulturalBackground", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeCulturalBackground(String value) {
        return new JAXBElement<String>(_DemographicsTypeCulturalBackground_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AustralianCitizenshipStatus", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeAustralianCitizenshipStatus(String value) {
        return new JAXBElement<String>(_DemographicsTypeAustralianCitizenshipStatus_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsImmunisationCertificateStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ImmunisationCertificateStatus", scope = DemographicsType.class)
    public JAXBElement<AUCodeSetsImmunisationCertificateStatusType> createDemographicsTypeImmunisationCertificateStatus(AUCodeSetsImmunisationCertificateStatusType value) {
        return new JAXBElement<AUCodeSetsImmunisationCertificateStatusType>(_DemographicsTypeImmunisationCertificateStatus_QNAME, AUCodeSetsImmunisationCertificateStatusType.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BirthDateVerification", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeBirthDateVerification(String value) {
        return new JAXBElement<String>(_DemographicsTypeBirthDateVerification_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PermanentResident", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypePermanentResident(String value) {
        return new JAXBElement<String>(_DemographicsTypePermanentResident_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemographicsType.ReligiousEventList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReligiousEventList", scope = DemographicsType.class)
    public JAXBElement<DemographicsType.ReligiousEventList> createDemographicsTypeReligiousEventList(DemographicsType.ReligiousEventList value) {
        return new JAXBElement<DemographicsType.ReligiousEventList>(_DemographicsTypeReligiousEventList_QNAME, DemographicsType.ReligiousEventList.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemographicsType.Religion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Religion", scope = DemographicsType.class)
    public JAXBElement<DemographicsType.Religion> createDemographicsTypeReligion(DemographicsType.Religion value) {
        return new JAXBElement<DemographicsType.Religion>(_DemographicsTypeReligion_QNAME, DemographicsType.Religion.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CountryOfBirth", scope = DemographicsType.class)
    public JAXBElement<String> createDemographicsTypeCountryOfBirth(String value) {
        return new JAXBElement<String>(_DemographicsTypeCountryOfBirth_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnglishProficiencyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EnglishProficiency", scope = DemographicsType.class)
    public JAXBElement<EnglishProficiencyType> createDemographicsTypeEnglishProficiency(EnglishProficiencyType value) {
        return new JAXBElement<EnglishProficiencyType>(_EnglishProficiency_QNAME, EnglishProficiencyType.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "IndigenousStatus", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeIndigenousStatus(String value) {
        return new JAXBElement<String>(_DemographicsTypeIndigenousStatus_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CountryArrivalDate", scope = DemographicsType.class)
    public JAXBElement<XMLGregorianCalendar> createDemographicsTypeCountryArrivalDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DemographicsTypeCountryArrivalDate_QNAME, XMLGregorianCalendar.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MaritalStatus", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeMaritalStatus(String value) {
        return new JAXBElement<String>(_DemographicsTypeMaritalStatus_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemographicsType.CountriesOfResidency }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CountriesOfResidency", scope = DemographicsType.class)
    public JAXBElement<DemographicsType.CountriesOfResidency> createDemographicsTypeCountriesOfResidency(DemographicsType.CountriesOfResidency value) {
        return new JAXBElement<DemographicsType.CountriesOfResidency>(_DemographicsTypeCountriesOfResidency_QNAME, DemographicsType.CountriesOfResidency.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sex", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeSex(String value) {
        return new JAXBElement<String>(_StudentSnapshotTypeSex_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemographicsType.DwellingArrangement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DwellingArrangement", scope = DemographicsType.class)
    public JAXBElement<DemographicsType.DwellingArrangement> createDemographicsTypeDwellingArrangement(DemographicsType.DwellingArrangement value) {
        return new JAXBElement<DemographicsType.DwellingArrangement>(_DemographicsTypeDwellingArrangement_QNAME, DemographicsType.DwellingArrangement.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReligiousRegion", scope = DemographicsType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createDemographicsTypeReligiousRegion(String value) {
        return new JAXBElement<String>(_DemographicsTypeReligiousRegion_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DemographicsType.CountriesOfCitizenship }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CountriesOfCitizenship", scope = DemographicsType.class)
    public JAXBElement<DemographicsType.CountriesOfCitizenship> createDemographicsTypeCountriesOfCitizenship(DemographicsType.CountriesOfCitizenship value) {
        return new JAXBElement<DemographicsType.CountriesOfCitizenship>(_DemographicsTypeCountriesOfCitizenship_QNAME, DemographicsType.CountriesOfCitizenship.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "VisaSubClass", scope = DemographicsType.class)
    public JAXBElement<String> createDemographicsTypeVisaSubClass(String value) {
        return new JAXBElement<String>(_DemographicsTypeVisaSubClass_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateOfBirth", scope = DemographicsType.class)
    public JAXBElement<String> createDemographicsTypeStateOfBirth(String value) {
        return new JAXBElement<String>(_DemographicsTypeStateOfBirth_QNAME, String.class, DemographicsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndSubmitDate", scope = ReportManifestType.ReportingPeriod.class)
    public JAXBElement<XMLGregorianCalendar> createReportManifestTypeReportingPeriodEndSubmitDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReportManifestTypeReportingPeriodEndSubmitDate_QNAME, XMLGregorianCalendar.class, ReportManifestType.ReportingPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndReportDate", scope = ReportManifestType.ReportingPeriod.class)
    public JAXBElement<XMLGregorianCalendar> createReportManifestTypeReportingPeriodEndReportDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReportManifestTypeReportingPeriodEndReportDate_QNAME, XMLGregorianCalendar.class, ReportManifestType.ReportingPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DueDate", scope = ReportManifestType.ReportingPeriod.class)
    public JAXBElement<XMLGregorianCalendar> createReportManifestTypeReportingPeriodDueDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReportManifestTypeReportingPeriodDueDate_QNAME, XMLGregorianCalendar.class, ReportManifestType.ReportingPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BeginSubmitDate", scope = ReportManifestType.ReportingPeriod.class)
    public JAXBElement<XMLGregorianCalendar> createReportManifestTypeReportingPeriodBeginSubmitDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReportManifestTypeReportingPeriodBeginSubmitDate_QNAME, XMLGregorianCalendar.class, ReportManifestType.ReportingPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BeginReportDate", scope = ReportManifestType.ReportingPeriod.class)
    public JAXBElement<XMLGregorianCalendar> createReportManifestTypeReportingPeriodBeginReportDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReportManifestTypeReportingPeriodBeginReportDate_QNAME, XMLGregorianCalendar.class, ReportManifestType.ReportingPeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = TimeTableType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolName", scope = TimeTableType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableTypeSchoolName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotHomeEnrollmentSchoolName_QNAME, String.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalId", scope = TimeTableType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableTypeLocalId(String value) {
        return new JAXBElement<String>(_LocalId_QNAME, String.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableCreationDate", scope = TimeTableType.class)
    public JAXBElement<XMLGregorianCalendar> createTimeTableTypeTimeTableCreationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TimeTableTypeTimeTableCreationDate_QNAME, XMLGregorianCalendar.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeachingPeriodsPerDay", scope = TimeTableType.class)
    public JAXBElement<Long> createTimeTableTypeTeachingPeriodsPerDay(Long value) {
        return new JAXBElement<Long>(_TimeTableTypeTeachingPeriodsPerDay_QNAME, Long.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = TimeTableType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableTypeSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDate", scope = TimeTableType.class)
    public JAXBElement<XMLGregorianCalendar> createTimeTableTypeEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeEndDate_QNAME, XMLGregorianCalendar.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDate", scope = TimeTableType.class)
    public JAXBElement<XMLGregorianCalendar> createTimeTableTypeStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeStartDate_QNAME, XMLGregorianCalendar.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = TimeTableType.class)
    public JAXBElement<SIFMetadataType> createTimeTableTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = TimeTableType.class)
    public JAXBElement<SIFExtendedElementsType> createTimeTableTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, TimeTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentParticipationType.ProgramAvailability.class)
    public JAXBElement<OtherCodeListType> createStudentParticipationTypeProgramAvailabilityOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentParticipationType.ProgramAvailability.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSubTestType.SubTestIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubTestIdentifiers", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Sif3AssessmentSubTestType.SubTestIdentifiers> createSif3AssessmentSubTestTypeSubTestIdentifiers(Sif3AssessmentSubTestType.SubTestIdentifiers value) {
        return new JAXBElement<Sif3AssessmentSubTestType.SubTestIdentifiers>(_Sif3AssessmentSubTestTypeSubTestIdentifiers_QNAME, Sif3AssessmentSubTestType.SubTestIdentifiers.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSubTestType.AssessmentItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItems", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Sif3AssessmentSubTestType.AssessmentItems> createSif3AssessmentSubTestTypeAssessmentItems(Sif3AssessmentSubTestType.AssessmentItems value) {
        return new JAXBElement<Sif3AssessmentSubTestType.AssessmentItems>(_AssessmentItems_QNAME, Sif3AssessmentSubTestType.AssessmentItems.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubTestPublishDate", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentSubTestTypeSubTestPublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentSubTestTypeSubTestPublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubTestSubjectAreas", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<SubjectAreaListType> createSif3AssessmentSubTestTypeSubTestSubjectAreas(SubjectAreaListType value) {
        return new JAXBElement<SubjectAreaListType>(_Sif3AssessmentSubTestTypeSubTestSubjectAreas_QNAME, SubjectAreaListType.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubTestTier", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Long> createSif3AssessmentSubTestTypeSubTestTier(Long value) {
        return new JAXBElement<Long>(_Sif3AssessmentSubTestTypeSubTestTier_QNAME, Long.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<String> createSif3AssessmentSubTestTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContainerOnly", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Boolean> createSif3AssessmentSubTestTypeContainerOnly(Boolean value) {
        return new JAXBElement<Boolean>(_Sif3AssessmentSubTestTypeContainerOnly_QNAME, Boolean.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSubTestType.AssessmentSubTestRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubTestRefIds", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Sif3AssessmentSubTestType.AssessmentSubTestRefIds> createSif3AssessmentSubTestTypeAssessmentSubTestRefIds(Sif3AssessmentSubTestType.AssessmentSubTestRefIds value) {
        return new JAXBElement<Sif3AssessmentSubTestType.AssessmentSubTestRefIds>(_Sif3AssessmentSubTestTypeAssessmentSubTestRefIds_QNAME, Sif3AssessmentSubTestType.AssessmentSubTestRefIds.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSubTestType.ScoreReporting }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreReporting", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Sif3AssessmentSubTestType.ScoreReporting> createSif3AssessmentSubTestTypeScoreReporting(Sif3AssessmentSubTestType.ScoreReporting value) {
        return new JAXBElement<Sif3AssessmentSubTestType.ScoreReporting>(_Sif3AssessmentSubTestTypeScoreReporting_QNAME, Sif3AssessmentSubTestType.ScoreReporting.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Abbreviation", scope = Sif3AssessmentSubTestType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSubTestTypeAbbreviation(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeAbbreviation_QNAME, String.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubTestGradeLevels", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<YearLevelsType> createSif3AssessmentSubTestTypeSubTestGradeLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_Sif3AssessmentSubTestTypeSubTestGradeLevels_QNAME, YearLevelsType.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NumberOfItems", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Long> createSif3AssessmentSubTestTypeNumberOfItems(Long value) {
        return new JAXBElement<Long>(_Sif3AssessmentSubTestTypeNumberOfItems_QNAME, Long.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubTestVersion", scope = Sif3AssessmentSubTestType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSubTestTypeSubTestVersion(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeSubTestVersion_QNAME, String.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSubTestType.LearningStandardItemRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItemRefIds", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<Sif3AssessmentSubTestType.LearningStandardItemRefIds> createSif3AssessmentSubTestTypeLearningStandardItemRefIds(Sif3AssessmentSubTestType.LearningStandardItemRefIds value) {
        return new JAXBElement<Sif3AssessmentSubTestType.LearningStandardItemRefIds>(_Sif3AssessmentTypeLearningStandardItemRefIds_QNAME, Sif3AssessmentSubTestType.LearningStandardItemRefIds.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentSubTestTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentSubTestType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentSubTestTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PreferenceNumber", scope = StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice.class)
    public JAXBElement<Long> createStudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoicePreferenceNumber(Long value) {
        return new JAXBElement<Long>(_StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoicePreferenceNumber_QNAME, Long.class, StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherSchoolLocalId", scope = StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceOtherSchoolLocalId(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceOtherSchoolLocalId_QNAME, String.class, StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudyDescription", scope = StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice.class)
    public JAXBElement<SubjectAreaType> createStudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceStudyDescription(SubjectAreaType value) {
        return new JAXBElement<SubjectAreaType>(_StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceStudyDescription_QNAME, SubjectAreaType.class, StudentSchoolEnrollmentType.StudentSubjectChoiceList.StudentSubjectChoice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent2Language", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent2Language(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent2Language_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevel", scope = StudentPersonalType.MostRecent.class)
    public JAXBElement<YearLevelType> createStudentPersonalTypeMostRecentYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_YearLevel_QNAME, YearLevelType.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent1Language", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent1Language(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent1Language_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent2EmploymentType", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent2EmploymentType(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent2EmploymentType_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent1NonSchoolEducation", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent1NonSchoolEducation(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent1NonSchoolEducation_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent1SchoolEducationLevel", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent1SchoolEducationLevel(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent1SchoolEducationLevel_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent1EmploymentType", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent1EmploymentType(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent1EmploymentType_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HomeroomLocalId", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentHomeroomLocalId(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentHomeroomLocalId_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent2SchoolEducationLevel", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent2SchoolEducationLevel(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent2SchoolEducationLevel_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Parent2NonSchoolEducation", scope = StudentPersonalType.MostRecent.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPersonalTypeMostRecentParent2NonSchoolEducation(String value) {
        return new JAXBElement<String>(_StudentPersonalTypeMostRecentParent2NonSchoolEducation_QNAME, String.class, StudentPersonalType.MostRecent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedQueryDataModelType.SIFWhere }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Where", scope = SIFExtendedQueryDataModelType.class)
    public JAXBElement<SIFExtendedQueryDataModelType.SIFWhere> createSIFExtendedQueryDataModelTypeSIFWhere(SIFExtendedQueryDataModelType.SIFWhere value) {
        return new JAXBElement<SIFExtendedQueryDataModelType.SIFWhere>(_SIFExtendedQueryDataModelTypeSIFWhere_QNAME, SIFExtendedQueryDataModelType.SIFWhere.class, SIFExtendedQueryDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedQueryDataModelType.SIFOrderBy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_OrderBy", scope = SIFExtendedQueryDataModelType.class)
    public JAXBElement<SIFExtendedQueryDataModelType.SIFOrderBy> createSIFExtendedQueryDataModelTypeSIFOrderBy(SIFExtendedQueryDataModelType.SIFOrderBy value) {
        return new JAXBElement<SIFExtendedQueryDataModelType.SIFOrderBy>(_SIFExtendedQueryDataModelTypeSIFOrderBy_QNAME, SIFExtendedQueryDataModelType.SIFOrderBy.class, SIFExtendedQueryDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_DestinationProvider", scope = SIFExtendedQueryDataModelType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSIFExtendedQueryDataModelTypeSIFDestinationProvider(String value) {
        return new JAXBElement<String>(_SIFExtendedQueryDataModelTypeSIFDestinationProvider_QNAME, String.class, SIFExtendedQueryDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolProgramsType.SchoolProgramList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolProgramList", scope = SchoolProgramsType.class)
    public JAXBElement<SchoolProgramsType.SchoolProgramList> createSchoolProgramsTypeSchoolProgramList(SchoolProgramsType.SchoolProgramList value) {
        return new JAXBElement<SchoolProgramsType.SchoolProgramList>(_SchoolProgramsTypeSchoolProgramList_QNAME, SchoolProgramsType.SchoolProgramList.class, SchoolProgramsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = SchoolProgramsType.class)
    public JAXBElement<XMLGregorianCalendar> createSchoolProgramsTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, SchoolProgramsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SchoolProgramsType.class)
    public JAXBElement<SIFMetadataType> createSchoolProgramsTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SchoolProgramsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SchoolProgramsType.class)
    public JAXBElement<SIFExtendedElementsType> createSchoolProgramsTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SchoolProgramsType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ElementName", scope = AggregateCharacteristicInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAggregateCharacteristicInfoTypeElementName(String value) {
        return new JAXBElement<String>(_AggregateCharacteristicInfoTypeElementName_QNAME, String.class, AggregateCharacteristicInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = AggregateCharacteristicInfoType.class)
    public JAXBElement<String> createAggregateCharacteristicInfoTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, AggregateCharacteristicInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AggregateCharacteristicInfoType.class)
    public JAXBElement<SIFMetadataType> createAggregateCharacteristicInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AggregateCharacteristicInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AggregateCharacteristicInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createAggregateCharacteristicInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AggregateCharacteristicInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContactInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactInfo", scope = ReportAuthorityInfoType.class)
    public JAXBElement<ContactInfoType> createReportAuthorityInfoTypeContactInfo(ContactInfoType value) {
        return new JAXBElement<ContactInfoType>(_ContactInfo_QNAME, ContactInfoType.class, ReportAuthorityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumber", scope = ReportAuthorityInfoType.class)
    public JAXBElement<PhoneNumberType> createReportAuthorityInfoTypePhoneNumber(PhoneNumberType value) {
        return new JAXBElement<PhoneNumberType>(_PhoneNumber_QNAME, PhoneNumberType.class, ReportAuthorityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AuthorityDepartment", scope = ReportAuthorityInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createReportAuthorityInfoTypeAuthorityDepartment(String value) {
        return new JAXBElement<String>(_ReportAuthorityInfoTypeAuthorityDepartment_QNAME, String.class, ReportAuthorityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = ReportAuthorityInfoType.class)
    public JAXBElement<SIFMetadataType> createReportAuthorityInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, ReportAuthorityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = ReportAuthorityInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createReportAuthorityInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, ReportAuthorityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address", scope = ReportAuthorityInfoType.class)
    public JAXBElement<AddressType> createReportAuthorityInfoTypeAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, ReportAuthorityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Minimum", scope = Sif3AssessmentSubTestType.ScoreReporting.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSubTestTypeScoreReportingMinimum(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeScoreReportingMinimum_QNAME, String.class, Sif3AssessmentSubTestType.ScoreReporting.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreTableRefId", scope = Sif3AssessmentSubTestType.ScoreReporting.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSubTestTypeScoreReportingScoreTableRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeScoreReportingScoreTableRefId_QNAME, String.class, Sif3AssessmentSubTestType.ScoreReporting.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Maximum", scope = Sif3AssessmentSubTestType.ScoreReporting.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSubTestTypeScoreReportingMaximum(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeScoreReportingMaximum_QNAME, String.class, Sif3AssessmentSubTestType.ScoreReporting.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDateTime", scope = TimeElementType.SpanGaps.SpanGap.class)
    public JAXBElement<XMLGregorianCalendar> createTimeElementTypeSpanGapsSpanGapEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TimeElementTypeEndDateTime_QNAME, XMLGregorianCalendar.class, TimeElementType.SpanGaps.SpanGap.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDateTime", scope = TimeElementType.SpanGaps.SpanGap.class)
    public JAXBElement<XMLGregorianCalendar> createTimeElementTypeSpanGapsSpanGapStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeStartDateTime_QNAME, XMLGregorianCalendar.class, TimeElementType.SpanGaps.SpanGap.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentActivityInfoType.StudentActivityType.class)
    public JAXBElement<OtherCodeListType> createStudentActivityInfoTypeStudentActivityTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentActivityInfoType.StudentActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "UpperCut", scope = AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentItemTypePerformanceLevelsPerformanceLevelCutScoresUpperCut(String value) {
        return new JAXBElement<String>(_AssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresUpperCut_QNAME, String.class, AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LowerCut", scope = AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentItemTypePerformanceLevelsPerformanceLevelCutScoresLowerCut(String value) {
        return new JAXBElement<String>(_AssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScoresLowerCut_QNAME, String.class, AssessmentItemType.PerformanceLevels.PerformanceLevel.CutScores.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StaffAssignmentType.StaffActivity.class)
    public JAXBElement<OtherCodeListType> createStaffAssignmentTypeStaffActivityOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StaffAssignmentType.StaffActivity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EntryDate", scope = StudentSectionEnrollmentType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSectionEnrollmentTypeEntryDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentSectionEnrollmentTypeEntryDate_QNAME, XMLGregorianCalendar.class, StudentSectionEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = StudentSectionEnrollmentType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSectionEnrollmentTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, StudentSectionEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ExitDate", scope = StudentSectionEnrollmentType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentSectionEnrollmentTypeExitDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentSchoolEnrollmentTypeExitDate_QNAME, XMLGregorianCalendar.class, StudentSectionEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentSectionEnrollmentType.class)
    public JAXBElement<SIFMetadataType> createStudentSectionEnrollmentTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentSectionEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentSectionEnrollmentType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentSectionEnrollmentTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentSectionEnrollmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDateTime", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentRegistrationTypeEndDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TimeElementTypeEndDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DaysOfInstruction", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<Integer> createSif3AssessmentRegistrationTypeDaysOfInstruction(Integer value) {
        return new JAXBElement<Integer>(_Sif3AssessmentRegistrationTypeDaysOfInstruction_QNAME, Integer.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRegistrationType.TestingStatuses }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TestingStatuses", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<Sif3AssessmentRegistrationType.TestingStatuses> createSif3AssessmentRegistrationTypeTestingStatuses(Sif3AssessmentRegistrationType.TestingStatuses value) {
        return new JAXBElement<Sif3AssessmentRegistrationType.TestingStatuses>(_Sif3AssessmentRegistrationTypeTestingStatuses_QNAME, Sif3AssessmentRegistrationType.TestingStatuses.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScorePublishDate", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentRegistrationTypeScorePublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentRegistrationTypeScorePublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RetestIndicator", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeRetestIndicator(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeRetestIndicator_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TestAttemptIdentifier", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeTestAttemptIdentifier(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeTestAttemptIdentifier_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRegistrationType.StudentSpecialEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentSpecialEvents", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<Sif3AssessmentRegistrationType.StudentSpecialEvents> createSif3AssessmentRegistrationTypeStudentSpecialEvents(Sif3AssessmentRegistrationType.StudentSpecialEvents value) {
        return new JAXBElement<Sif3AssessmentRegistrationType.StudentSpecialEvents>(_Sif3AssessmentRegistrationTypeStudentSpecialEvents_QNAME, Sif3AssessmentRegistrationType.StudentSpecialEvents.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentAdministrationRefId", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentAdministrationRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSessionTypeAssessmentAdministrationRefId_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentPlatform", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentPlatform(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentPlatform_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentGradeLevel", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<YearLevelType> createSif3AssessmentRegistrationTypeStudentGradeLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_Sif3AssessmentRegistrationTypeStudentGradeLevel_QNAME, YearLevelType.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfoRefId", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeLEAInfoRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeLEAInfoRefId_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionInfoRefId", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeSectionInfoRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeSectionInfoRefId_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentFormRefId", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentFormRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSessionTypeAssessmentFormRefId_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonalRefId", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeStaffPersonalRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeStaffPersonalRefId_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDateTime", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentRegistrationTypeStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeStartDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRegistrationType.AssessmentStudentSnapshot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentStudentSnapshot", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<Sif3AssessmentRegistrationType.AssessmentStudentSnapshot> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshot(Sif3AssessmentRegistrationType.AssessmentStudentSnapshot value) {
        return new JAXBElement<Sif3AssessmentRegistrationType.AssessmentStudentSnapshot>(_AssessmentRegistrationTypeAssessmentStudentSnapshot_QNAME, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSessionRefId", scope = Sif3AssessmentRegistrationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentSessionRefId(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentSessionRefId_QNAME, String.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentRegistrationTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentGradeLevel", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<YearLevelType> createSif3AssessmentRegistrationTypeAssessmentGradeLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_Sif3AssessmentRegistrationTypeAssessmentGradeLevel_QNAME, YearLevelType.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentRegistrationType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentRegistrationTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentRegistrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreCodeDefinition", scope = Sif3AssessmentRubricType.Scores.Score.class)
    public JAXBElement<AbstractContentElementType> createSif3AssessmentRubricTypeScoresScoreScoreCodeDefinition(AbstractContentElementType value) {
        return new JAXBElement<AbstractContentElementType>(_Sif3AssessmentRubricTypeScoresScoreScoreCodeDefinition_QNAME, AbstractContentElementType.class, Sif3AssessmentRubricType.Scores.Score.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreCode", scope = Sif3AssessmentRubricType.Scores.Score.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRubricTypeScoresScoreScoreCode(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRubricTypeScoresScoreScoreCode_QNAME, String.class, Sif3AssessmentRubricType.Scores.Score.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRubricType.Scores.Score.ScoreComments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreComments", scope = Sif3AssessmentRubricType.Scores.Score.class)
    public JAXBElement<Sif3AssessmentRubricType.Scores.Score.ScoreComments> createSif3AssessmentRubricTypeScoresScoreScoreComments(Sif3AssessmentRubricType.Scores.Score.ScoreComments value) {
        return new JAXBElement<Sif3AssessmentRubricType.Scores.Score.ScoreComments>(_Sif3AssessmentRubricTypeScoresScoreScoreComments_QNAME, Sif3AssessmentRubricType.Scores.Score.ScoreComments.class, Sif3AssessmentRubricType.Scores.Score.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreDescriptions", scope = Sif3AssessmentRubricType.Scores.Score.class)
    public JAXBElement<Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions> createSif3AssessmentRubricTypeScoresScoreScoreDescriptions(Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions value) {
        return new JAXBElement<Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions>(_Sif3AssessmentRubricTypeScoresScoreScoreDescriptions_QNAME, Sif3AssessmentRubricType.Scores.Score.ScoreDescriptions.class, Sif3AssessmentRubricType.Scores.Score.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentSubTestType.ScoreRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreRange", scope = AssessmentSubTestType.class)
    public JAXBElement<AssessmentSubTestType.ScoreRange> createAssessmentSubTestTypeScoreRange(AssessmentSubTestType.ScoreRange value) {
        return new JAXBElement<AssessmentSubTestType.ScoreRange>(_AssessmentSubTestTypeScoreRange_QNAME, AssessmentSubTestType.ScoreRange.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentSubTestType.PerformanceLevels }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PerformanceLevels", scope = AssessmentSubTestType.class)
    public JAXBElement<AssessmentSubTestType.PerformanceLevels> createAssessmentSubTestTypePerformanceLevels(AssessmentSubTestType.PerformanceLevels value) {
        return new JAXBElement<AssessmentSubTestType.PerformanceLevels>(_AssessmentItemTypePerformanceLevels_QNAME, AssessmentSubTestType.PerformanceLevels.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubTestTier", scope = AssessmentSubTestType.class)
    public JAXBElement<Long> createAssessmentSubTestTypeSubTestTier(Long value) {
        return new JAXBElement<Long>(_Sif3AssessmentSubTestTypeSubTestTier_QNAME, Long.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = AssessmentSubTestType.class)
    public JAXBElement<String> createAssessmentSubTestTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContainerOnly", scope = AssessmentSubTestType.class)
    public JAXBElement<Boolean> createAssessmentSubTestTypeContainerOnly(Boolean value) {
        return new JAXBElement<Boolean>(_Sif3AssessmentSubTestTypeContainerOnly_QNAME, Boolean.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentSubTestType.AssessmentSubTestRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubTestRefIds", scope = AssessmentSubTestType.class)
    public JAXBElement<AssessmentSubTestType.AssessmentSubTestRefIds> createAssessmentSubTestTypeAssessmentSubTestRefIds(AssessmentSubTestType.AssessmentSubTestRefIds value) {
        return new JAXBElement<AssessmentSubTestType.AssessmentSubTestRefIds>(_Sif3AssessmentSubTestTypeAssessmentSubTestRefIds_QNAME, AssessmentSubTestType.AssessmentSubTestRefIds.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Abbreviation", scope = AssessmentSubTestType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentSubTestTypeAbbreviation(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeAbbreviation_QNAME, String.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NumberOfItems", scope = AssessmentSubTestType.class)
    public JAXBElement<Long> createAssessmentSubTestTypeNumberOfItems(Long value) {
        return new JAXBElement<Long>(_Sif3AssessmentSubTestTypeNumberOfItems_QNAME, Long.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentSubTestType.LearningStandardItemRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItemRefIds", scope = AssessmentSubTestType.class)
    public JAXBElement<AssessmentSubTestType.LearningStandardItemRefIds> createAssessmentSubTestTypeLearningStandardItemRefIds(AssessmentSubTestType.LearningStandardItemRefIds value) {
        return new JAXBElement<AssessmentSubTestType.LearningStandardItemRefIds>(_Sif3AssessmentTypeLearningStandardItemRefIds_QNAME, AssessmentSubTestType.LearningStandardItemRefIds.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectArea", scope = AssessmentSubTestType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentSubTestTypeSubjectArea(String value) {
        return new JAXBElement<String>(_SubjectArea_QNAME, String.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AssessmentSubTestType.class)
    public JAXBElement<SIFMetadataType> createAssessmentSubTestTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = AssessmentSubTestType.class)
    public JAXBElement<YearLevelsType> createAssessmentSubTestTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AssessmentSubTestType.class)
    public JAXBElement<SIFExtendedElementsType> createAssessmentSubTestTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AssessmentSubTestType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OnTimeGraduationYear", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotOnTimeGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OnTimeGraduationYear_QNAME, XMLGregorianCalendar.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NeglectedDelinquent", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotNeglectedDelinquent(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotNeglectedDelinquent_QNAME, AUCodeSetsYesOrNoCategoryType.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BirthDate", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BirthDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EconomicDisadvantage", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotEconomicDisadvantage(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeEconomicDisadvantage_QNAME, AUCodeSetsYesOrNoCategoryType.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Age", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<Long> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotAge(Long value) {
        return new JAXBElement<Long>(_StudentSnapshotTypeAge_QNAME, Long.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ProjectedGraduationYear", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotProjectedGraduationYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ProjectedGraduationYear_QNAME, XMLGregorianCalendar.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GraduationOnTime", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotGraduationOnTime(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotGraduationOnTime_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GraduationDate", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotGraduationDate(String value) {
        return new JAXBElement<String>(_GraduationDate_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sex", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotSex(String value) {
        return new JAXBElement<String>(_StudentSnapshotTypeSex_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Name", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<NameType> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotName(NameType value) {
        return new JAXBElement<NameType>(_Name_QNAME, NameType.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GiftedTalented", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotGiftedTalented(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentPersonalTypeGiftedTalented_QNAME, AUCodeSetsYesOrNoCategoryType.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GraduationAward", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotGraduationAward(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRegistrationTypeAssessmentStudentSnapshotGraduationAward_QNAME, String.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address", scope = Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<AddressType> createSif3AssessmentRegistrationTypeAssessmentStudentSnapshotAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, Sif3AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentActivityInfoRefId", scope = StudentSDTNType.AreasOfInterestList.ActivityInfo.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSDTNTypeAreasOfInterestListActivityInfoStudentActivityInfoRefId(String value) {
        return new JAXBElement<String>(_StudentSDTNTypeAreasOfInterestListActivityInfoStudentActivityInfoRefId_QNAME, String.class, StudentSDTNType.AreasOfInterestList.ActivityInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SectionInfoType.SchoolCourseInfoOverride }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCourseInfoOverride", scope = SectionInfoType.class)
    public JAXBElement<SectionInfoType.SchoolCourseInfoOverride> createSectionInfoTypeSchoolCourseInfoOverride(SectionInfoType.SchoolCourseInfoOverride value) {
        return new JAXBElement<SectionInfoType.SchoolCourseInfoOverride>(_SectionInfoTypeSchoolCourseInfoOverride_QNAME, SectionInfoType.SchoolCourseInfoOverride.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SectionInfoType.MediumOfInstruction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MediumOfInstruction", scope = SectionInfoType.class)
    public JAXBElement<SectionInfoType.MediumOfInstruction> createSectionInfoTypeMediumOfInstruction(SectionInfoType.MediumOfInstruction value) {
        return new JAXBElement<SectionInfoType.MediumOfInstruction>(_SectionInfoTypeMediumOfInstruction_QNAME, SectionInfoType.MediumOfInstruction.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TermInfoRefId", scope = SectionInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeTermInfoRefId(String value) {
        return new JAXBElement<String>(_SectionInfoTypeTermInfoRefId_QNAME, String.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = SectionInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createSectionInfoTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SectionInfoType.LocationOfInstruction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocationOfInstruction", scope = SectionInfoType.class)
    public JAXBElement<SectionInfoType.LocationOfInstruction> createSectionInfoTypeLocationOfInstruction(SectionInfoType.LocationOfInstruction value) {
        return new JAXBElement<SectionInfoType.LocationOfInstruction>(_SectionInfoTypeLocationOfInstruction_QNAME, SectionInfoType.LocationOfInstruction.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionCode", scope = SectionInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSectionCode(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSectionCode_QNAME, String.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = SectionInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SectionInfoType.LanguageOfInstruction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LanguageOfInstruction", scope = SectionInfoType.class)
    public JAXBElement<SectionInfoType.LanguageOfInstruction> createSectionInfoTypeLanguageOfInstruction(SectionInfoType.LanguageOfInstruction value) {
        return new JAXBElement<SectionInfoType.LanguageOfInstruction>(_SectionInfoTypeLanguageOfInstruction_QNAME, SectionInfoType.LanguageOfInstruction.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseSectionCode", scope = SectionInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeCourseSectionCode(String value) {
        return new JAXBElement<String>(_SectionInfoTypeCourseSectionCode_QNAME, String.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SummerSchool", scope = SectionInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSummerSchool(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSummerSchool_QNAME, String.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CountForAttendance", scope = SectionInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeCountForAttendance(String value) {
        return new JAXBElement<String>(_SectionInfoTypeCountForAttendance_QNAME, String.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SectionInfoType.class)
    public JAXBElement<SIFMetadataType> createSectionInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SectionInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createSectionInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SectionInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PromotionStatus", scope = StudentSchoolEnrollmentType.PromotionInfo.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentSchoolEnrollmentTypePromotionInfoPromotionStatus(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypePromotionInfoPromotionStatus_QNAME, String.class, StudentSchoolEnrollmentType.PromotionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DaysTardy", scope = StudentAttendanceSummaryType.class)
    public JAXBElement<BigDecimal> createStudentAttendanceSummaryTypeDaysTardy(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_StudentAttendanceSummaryTypeDaysTardy_QNAME, BigDecimal.class, StudentAttendanceSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentAttendanceSummaryRefId", scope = StudentAttendanceSummaryType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentAttendanceSummaryTypeStudentAttendanceSummaryRefId(String value) {
        return new JAXBElement<String>(_StudentAttendanceSummaryTypeStudentAttendanceSummaryRefId_QNAME, String.class, StudentAttendanceSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDay", scope = StudentAttendanceSummaryType.class)
    public JAXBElement<Long> createStudentAttendanceSummaryTypeStartDay(Long value) {
        return new JAXBElement<Long>(_StudentAttendanceSummaryTypeStartDay_QNAME, Long.class, StudentAttendanceSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentAttendanceSummaryType.class)
    public JAXBElement<SIFMetadataType> createStudentAttendanceSummaryTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentAttendanceSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FTE", scope = StudentAttendanceSummaryType.class)
    public JAXBElement<BigDecimal> createStudentAttendanceSummaryTypeFTE(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_StudentSchoolEnrollmentTypeFTE_QNAME, BigDecimal.class, StudentAttendanceSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentAttendanceSummaryType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentAttendanceSummaryTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentAttendanceSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDay", scope = StudentAttendanceSummaryType.class)
    public JAXBElement<Long> createStudentAttendanceSummaryTypeEndDay(Long value) {
        return new JAXBElement<Long>(_StudentAttendanceSummaryTypeEndDay_QNAME, Long.class, StudentAttendanceSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentItemAssociationType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentItemAssociationTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentItemAssociationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentItemAssociationType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentItemAssociationTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentItemAssociationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentParticipationType.ReferralSource.class)
    public JAXBElement<OtherCodeListType> createStudentParticipationTypeReferralSourceOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentParticipationType.ReferralSource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentFormType.AssessmentSubTestRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubTestRefIds", scope = AssessmentFormType.class)
    public JAXBElement<AssessmentFormType.AssessmentSubTestRefIds> createAssessmentFormTypeAssessmentSubTestRefIds(AssessmentFormType.AssessmentSubTestRefIds value) {
        return new JAXBElement<AssessmentFormType.AssessmentSubTestRefIds>(_Sif3AssessmentSubTestTypeAssessmentSubTestRefIds_QNAME, AssessmentFormType.AssessmentSubTestRefIds.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Level", scope = AssessmentFormType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentFormTypeLevel(String value) {
        return new JAXBElement<String>(_AssessmentFormTypeLevel_QNAME, String.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Period", scope = AssessmentFormType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentFormTypePeriod(String value) {
        return new JAXBElement<String>(_AssessmentFormTypePeriod_QNAME, String.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentFormType.FormNumbers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FormNumbers", scope = AssessmentFormType.class)
    public JAXBElement<AssessmentFormType.FormNumbers> createAssessmentFormTypeFormNumbers(AssessmentFormType.FormNumbers value) {
        return new JAXBElement<AssessmentFormType.FormNumbers>(_AssessmentFormTypeFormNumbers_QNAME, AssessmentFormType.FormNumbers.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AssessmentFormType.class)
    public JAXBElement<SIFMetadataType> createAssessmentFormTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentType", scope = AssessmentFormType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentFormTypeAssessmentType(String value) {
        return new JAXBElement<String>(_AssessmentFormTypeAssessmentType_QNAME, String.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AssessmentFormType.class)
    public JAXBElement<SIFExtendedElementsType> createAssessmentFormTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = AssessmentFormType.class)
    public JAXBElement<YearLevelsType> createAssessmentFormTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "HasCustody", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsHasCustody(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsHasCustody_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FeesBilling", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsFeesBilling(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsFeesBilling_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReceivesAssessmentReport", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsReceivesAssessmentReport(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsReceivesAssessmentReport_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmergencyContact", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsEmergencyContact(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsEmergencyContact_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PickupRights", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsPickupRights(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsPickupRights_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AttendanceContact", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsAttendanceContact(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsAttendanceContact_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PrimaryCareProvider", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsPrimaryCareProvider(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsPrimaryCareProvider_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AccessToRecords", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsAccessToRecords(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsAccessToRecords_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FamilyMail", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsFamilyMail(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsFamilyMail_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "InterventionOrder", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsInterventionOrder(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsInterventionOrder_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ParentLegalGuardian", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsParentLegalGuardian(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsParentLegalGuardian_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DisciplinaryContact", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsDisciplinaryContact(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsDisciplinaryContact_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LivesWith", scope = StudentContactRelationshipType.ContactFlags.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStudentContactRelationshipTypeContactFlagsLivesWith(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StudentContactRelationshipTypeContactFlagsLivesWith_QNAME, AUCodeSetsYesOrNoCategoryType.class, StudentContactRelationshipType.ContactFlags.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportInfo", scope = SIFReportObjectType.class)
    public JAXBElement<SIFReportObjectType.ReportInfo> createSIFReportObjectTypeReportInfo(SIFReportObjectType.ReportInfo value) {
        return new JAXBElement<SIFReportObjectType.ReportInfo>(_SIFReportObjectTypeReportInfo_QNAME, SIFReportObjectType.ReportInfo.class, SIFReportObjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedQueryResultsDataModelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedQueryResults", scope = SIFReportObjectType.class)
    public JAXBElement<SIFExtendedQueryResultsDataModelType> createSIFReportObjectTypeSIFExtendedQueryResults(SIFExtendedQueryResultsDataModelType value) {
        return new JAXBElement<SIFExtendedQueryResultsDataModelType>(_SIFReportObjectTypeSIFExtendedQueryResults_QNAME, SIFExtendedQueryResultsDataModelType.class, SIFReportObjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportDataObjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReportData", scope = SIFReportObjectType.class)
    public JAXBElement<ReportDataObjectType> createSIFReportObjectTypeReportData(ReportDataObjectType value) {
        return new JAXBElement<ReportDataObjectType>(_SIFReportObjectTypeReportData_QNAME, ReportDataObjectType.class, SIFReportObjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SIFReportObjectType.class)
    public JAXBElement<SIFMetadataType> createSIFReportObjectTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SIFReportObjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SIFReportObjectType.class)
    public JAXBElement<SIFExtendedElementsType> createSIFReportObjectTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SIFReportObjectType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemPlatforms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItemPlatforms", scope = Sif3AssessmentItemType.class)
    public JAXBElement<Sif3AssessmentItemType.AssessmentItemPlatforms> createSif3AssessmentItemTypeAssessmentItemPlatforms(Sif3AssessmentItemType.AssessmentItemPlatforms value) {
        return new JAXBElement<Sif3AssessmentItemType.AssessmentItemPlatforms>(_Sif3AssessmentItemTypeAssessmentItemPlatforms_QNAME, Sif3AssessmentItemType.AssessmentItemPlatforms.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.LearningStandardItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandardItems", scope = Sif3AssessmentItemType.class)
    public JAXBElement<Sif3AssessmentItemType.LearningStandardItems> createSif3AssessmentItemTypeLearningStandardItems(Sif3AssessmentItemType.LearningStandardItems value) {
        return new JAXBElement<Sif3AssessmentItemType.LearningStandardItems>(_LearningStandardItems_QNAME, Sif3AssessmentItemType.LearningStandardItems.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemBanks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItemBanks", scope = Sif3AssessmentItemType.class)
    public JAXBElement<Sif3AssessmentItemType.AssessmentItemBanks> createSif3AssessmentItemTypeAssessmentItemBanks(Sif3AssessmentItemType.AssessmentItemBanks value) {
        return new JAXBElement<Sif3AssessmentItemType.AssessmentItemBanks>(_Sif3AssessmentTypeAssessmentItemBanks_QNAME, Sif3AssessmentItemType.AssessmentItemBanks.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemScoreMaximum", scope = Sif3AssessmentItemType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentItemTypeItemScoreMaximum(String value) {
        return new JAXBElement<String>(_AssessmentItemTypeItemScoreMaximum_QNAME, String.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.ItemRubricIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemRubricIds", scope = Sif3AssessmentItemType.class)
    public JAXBElement<Sif3AssessmentItemType.ItemRubricIds> createSif3AssessmentItemTypeItemRubricIds(Sif3AssessmentItemType.ItemRubricIds value) {
        return new JAXBElement<Sif3AssessmentItemType.ItemRubricIds>(_Sif3AssessmentItemTypeItemRubricIds_QNAME, Sif3AssessmentItemType.ItemRubricIds.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentLanguages", scope = Sif3AssessmentItemType.class)
    public JAXBElement<LanguageListType> createSif3AssessmentItemTypeAssessmentLanguages(LanguageListType value) {
        return new JAXBElement<LanguageListType>(_Sif3AssessmentTypeAssessmentLanguages_QNAME, LanguageListType.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractContentElementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Stem", scope = Sif3AssessmentItemType.class)
    public JAXBElement<AbstractContentElementType> createSif3AssessmentItemTypeStem(AbstractContentElementType value) {
        return new JAXBElement<AbstractContentElementType>(_AssessmentItemTypeStem_QNAME, AbstractContentElementType.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentGradeLevels", scope = Sif3AssessmentItemType.class)
    public JAXBElement<YearLevelsType> createSif3AssessmentItemTypeAssessmentGradeLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_Sif3AssessmentTypeAssessmentGradeLevels_QNAME, YearLevelsType.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubjects", scope = Sif3AssessmentItemType.class)
    public JAXBElement<SubjectAreaListType> createSif3AssessmentItemTypeAssessmentSubjects(SubjectAreaListType value) {
        return new JAXBElement<SubjectAreaListType>(_Sif3AssessmentTypeAssessmentSubjects_QNAME, SubjectAreaListType.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemScoreMinimum", scope = Sif3AssessmentItemType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentItemTypeItemScoreMinimum(String value) {
        return new JAXBElement<String>(_AssessmentItemTypeItemScoreMinimum_QNAME, String.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentIdentifiers", scope = Sif3AssessmentItemType.class)
    public JAXBElement<Sif3AssessmentItemType.AssessmentIdentifiers> createSif3AssessmentItemTypeAssessmentIdentifiers(Sif3AssessmentItemType.AssessmentIdentifiers value) {
        return new JAXBElement<Sif3AssessmentItemType.AssessmentIdentifiers>(_Sif3AssessmentTypeAssessmentIdentifiers_QNAME, Sif3AssessmentItemType.AssessmentIdentifiers.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemFeedbackCorrect", scope = Sif3AssessmentItemType.class)
    public JAXBElement<String> createSif3AssessmentItemTypeItemFeedbackCorrect(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeItemFeedbackCorrect_QNAME, String.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemFeedbackIncorrect", scope = Sif3AssessmentItemType.class)
    public JAXBElement<String> createSif3AssessmentItemTypeItemFeedbackIncorrect(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeItemFeedbackIncorrect_QNAME, String.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.AssessmentItemAssetRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentItemAssetRefIds", scope = Sif3AssessmentItemType.class)
    public JAXBElement<Sif3AssessmentItemType.AssessmentItemAssetRefIds> createSif3AssessmentItemTypeAssessmentItemAssetRefIds(Sif3AssessmentItemType.AssessmentItemAssetRefIds value) {
        return new JAXBElement<Sif3AssessmentItemType.AssessmentItemAssetRefIds>(_Sif3AssessmentItemTypeAssessmentItemAssetRefIds_QNAME, Sif3AssessmentItemType.AssessmentItemAssetRefIds.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemFeedbackHint", scope = Sif3AssessmentItemType.class)
    public JAXBElement<String> createSif3AssessmentItemTypeItemFeedbackHint(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeItemFeedbackHint_QNAME, String.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemPublishDate", scope = Sif3AssessmentItemType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentItemTypeItemPublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentItemTypeItemPublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentItemType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentItemTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentItemType.ResponseChoices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ResponseChoices", scope = Sif3AssessmentItemType.class)
    public JAXBElement<Sif3AssessmentItemType.ResponseChoices> createSif3AssessmentItemTypeResponseChoices(Sif3AssessmentItemType.ResponseChoices value) {
        return new JAXBElement<Sif3AssessmentItemType.ResponseChoices>(_AssessmentItemTypeResponseChoices_QNAME, Sif3AssessmentItemType.ResponseChoices.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentItemType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentItemTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemVersion", scope = Sif3AssessmentItemType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentItemTypeItemVersion(String value) {
        return new JAXBElement<String>(_Sif3AssessmentItemTypeItemVersion_QNAME, String.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemName", scope = Sif3AssessmentItemType.class)
    public JAXBElement<String> createSif3AssessmentItemTypeItemName(String value) {
        return new JAXBElement<String>(_AssessmentItemTypeItemName_QNAME, String.class, Sif3AssessmentItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PublishInDirectory", scope = LEAInfoType.LEAContactList.LEAContact.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createLEAInfoTypeLEAContactListLEAContactPublishInDirectory(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_PublishInDirectory_QNAME, AUCodeSetsYesOrNoCategoryType.class, LEAInfoType.LEAContactList.LEAContact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = DemographicsType.Religion.class)
    public JAXBElement<OtherCodeListType> createDemographicsTypeReligionOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, DemographicsType.Religion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemSequence", scope = Sif3AssessmentSectionType.SectionItems.SectionItem.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSectionTypeSectionItemsSectionItemItemSequence(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSectionTypeSectionItemsSectionItemItemSequence_QNAME, String.class, Sif3AssessmentSectionType.SectionItems.SectionItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = EnglishProficiencyType.class)
    public JAXBElement<OtherCodeListType> createEnglishProficiencyTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, EnglishProficiencyType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = DemographicsType.DwellingArrangement.class)
    public JAXBElement<OtherCodeListType> createDemographicsTypeDwellingArrangementOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, DemographicsType.DwellingArrangement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = ActivityType.Evaluation.class)
    public JAXBElement<String> createActivityTypeEvaluationDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, ActivityType.Evaluation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Name", scope = LearningResourceType.Evaluations.Evaluation.class)
    public JAXBElement<NameType> createLearningResourceTypeEvaluationsEvaluationName(NameType value) {
        return new JAXBElement<NameType>(_Name_QNAME, NameType.class, LearningResourceType.Evaluations.Evaluation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = LearningResourceType.Evaluations.Evaluation.class)
    public JAXBElement<String> createLearningResourceTypeEvaluationsEvaluationDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, LearningResourceType.Evaluations.Evaluation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Date", scope = LearningResourceType.Evaluations.Evaluation.class)
    public JAXBElement<XMLGregorianCalendar> createLearningResourceTypeEvaluationsEvaluationDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningResourceTypeEvaluationsEvaluationDate_QNAME, XMLGregorianCalendar.class, LearningResourceType.Evaluations.Evaluation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CutScores", scope = AssessmentSubTestType.PerformanceLevels.PerformanceLevel.class)
    public JAXBElement<AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores> createAssessmentSubTestTypePerformanceLevelsPerformanceLevelCutScores(AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores value) {
        return new JAXBElement<AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores>(_AssessmentItemTypePerformanceLevelsPerformanceLevelCutScores_QNAME, AssessmentSubTestType.PerformanceLevels.PerformanceLevel.CutScores.class, AssessmentSubTestType.PerformanceLevels.PerformanceLevel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Student", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class)
    public JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStudent(SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student value) {
        return new JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student>(_SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStudent_QNAME, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Student.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Staff", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class)
    public JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStaff(SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff value) {
        return new JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff>(_SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStaff_QNAME, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EnrollmentList", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class)
    public JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentList(SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList value) {
        return new JAXBElement<SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList>(_SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentList_QNAME, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolStateProvinceId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentSchoolStateProvinceId(String value) {
        return new JAXBElement<String>(_SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentSchoolStateProvinceId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NonSchoolEducation", scope = StudentContactPersonalType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentContactPersonalTypeNonSchoolEducation(String value) {
        return new JAXBElement<String>(_StudentContactPersonalTypeNonSchoolEducation_QNAME, String.class, StudentContactPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalId", scope = StudentContactPersonalType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentContactPersonalTypeLocalId(String value) {
        return new JAXBElement<String>(_LocalId_QNAME, String.class, StudentContactPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentContactPersonalType.OtherIdList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherIdList", scope = StudentContactPersonalType.class)
    public JAXBElement<StudentContactPersonalType.OtherIdList> createStudentContactPersonalTypeOtherIdList(StudentContactPersonalType.OtherIdList value) {
        return new JAXBElement<StudentContactPersonalType.OtherIdList>(_StaffPersonalTypeOtherIdList_QNAME, StudentContactPersonalType.OtherIdList.class, StudentContactPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmploymentType", scope = StudentContactPersonalType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentContactPersonalTypeEmploymentType(String value) {
        return new JAXBElement<String>(_StudentContactPersonalTypeEmploymentType_QNAME, String.class, StudentContactPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolEducationalLevel", scope = StudentContactPersonalType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentContactPersonalTypeSchoolEducationalLevel(String value) {
        return new JAXBElement<String>(_StudentContactPersonalTypeSchoolEducationalLevel_QNAME, String.class, StudentContactPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentContactPersonalType.class)
    public JAXBElement<SIFMetadataType> createStudentContactPersonalTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentContactPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentContactPersonalType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentContactPersonalTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentContactPersonalType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAdministrationType.AdministrationAssessments }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AdministrationAssessments", scope = Sif3AssessmentAdministrationType.class)
    public JAXBElement<Sif3AssessmentAdministrationType.AdministrationAssessments> createSif3AssessmentAdministrationTypeAdministrationAssessments(Sif3AssessmentAdministrationType.AdministrationAssessments value) {
        return new JAXBElement<Sif3AssessmentAdministrationType.AdministrationAssessments>(_Sif3AssessmentAdministrationTypeAdministrationAssessments_QNAME, Sif3AssessmentAdministrationType.AdministrationAssessments.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AdministrationCode", scope = Sif3AssessmentAdministrationType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAdministrationTypeAdministrationCode(String value) {
        return new JAXBElement<String>(_Sif3AssessmentAdministrationTypeAdministrationCode_QNAME, String.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FinishDateTime", scope = Sif3AssessmentAdministrationType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentAdministrationTypeFinishDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeFinishDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDateTime", scope = Sif3AssessmentAdministrationType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentAdministrationTypeStartDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_AssessmentAdministrationTypeStartDateTime_QNAME, XMLGregorianCalendar.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AdministrationName", scope = Sif3AssessmentAdministrationType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAdministrationTypeAdministrationName(String value) {
        return new JAXBElement<String>(_AssessmentAdministrationTypeAdministrationName_QNAME, String.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAdministrationType.Organizations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Organizations", scope = Sif3AssessmentAdministrationType.class)
    public JAXBElement<Sif3AssessmentAdministrationType.Organizations> createSif3AssessmentAdministrationTypeOrganizations(Sif3AssessmentAdministrationType.Organizations value) {
        return new JAXBElement<Sif3AssessmentAdministrationType.Organizations>(_Sif3AssessmentAdministrationTypeOrganizations_QNAME, Sif3AssessmentAdministrationType.Organizations.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentAdministrationType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentAdministrationTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentAdministrationType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentAdministrationTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentAdministrationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeIn", scope = StudentPeriodAttendanceType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentPeriodAttendanceTypeTimeIn(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentDailyAttendanceTypeTimeIn_QNAME, XMLGregorianCalendar.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SessionInfoRefId", scope = StudentPeriodAttendanceType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentPeriodAttendanceTypeSessionInfoRefId(String value) {
        return new JAXBElement<String>(_StudentPeriodAttendanceTypeSessionInfoRefId_QNAME, String.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeOut", scope = StudentPeriodAttendanceType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentPeriodAttendanceTypeTimeOut(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentDailyAttendanceTypeTimeOut_QNAME, XMLGregorianCalendar.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimetablePeriod", scope = StudentPeriodAttendanceType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentPeriodAttendanceTypeTimetablePeriod(String value) {
        return new JAXBElement<String>(_StudentPeriodAttendanceTypeTimetablePeriod_QNAME, String.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StudentPeriodAttendanceType.AuditInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AuditInfo", scope = StudentPeriodAttendanceType.class)
    public JAXBElement<StudentPeriodAttendanceType.AuditInfo> createStudentPeriodAttendanceTypeAuditInfo(StudentPeriodAttendanceType.AuditInfo value) {
        return new JAXBElement<StudentPeriodAttendanceType.AuditInfo>(_StudentPeriodAttendanceTypeAuditInfo_QNAME, StudentPeriodAttendanceType.AuditInfo.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = StudentPeriodAttendanceType.class)
    public JAXBElement<XMLGregorianCalendar> createStudentPeriodAttendanceTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AttendanceComment", scope = StudentPeriodAttendanceType.class)
    public JAXBElement<String> createStudentPeriodAttendanceTypeAttendanceComment(String value) {
        return new JAXBElement<String>(_StudentPeriodAttendanceTypeAttendanceComment_QNAME, String.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentPeriodAttendanceType.class)
    public JAXBElement<SIFMetadataType> createStudentPeriodAttendanceTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentPeriodAttendanceType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentPeriodAttendanceTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentPeriodAttendanceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectLocalId", scope = StaffAssignmentType.StaffSubjectList.StaffSubject.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStaffAssignmentTypeStaffSubjectListStaffSubjectSubjectLocalId(String value) {
        return new JAXBElement<String>(_StaffAssignmentTypeStaffSubjectListStaffSubjectSubjectLocalId_QNAME, String.class, StaffAssignmentType.StaffSubjectList.StaffSubject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableSubjectRefId", scope = StaffAssignmentType.StaffSubjectList.StaffSubject.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStaffAssignmentTypeStaffSubjectListStaffSubjectTimeTableSubjectRefId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTimeTableSubjectRefId_QNAME, String.class, StaffAssignmentType.StaffSubjectList.StaffSubject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreTableIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreTableIdentifiers", scope = Sif3AssessmentScoreTableType.class)
    public JAXBElement<Sif3AssessmentScoreTableType.ScoreTableIdentifiers> createSif3AssessmentScoreTableTypeScoreTableIdentifiers(Sif3AssessmentScoreTableType.ScoreTableIdentifiers value) {
        return new JAXBElement<Sif3AssessmentScoreTableType.ScoreTableIdentifiers>(_Sif3AssessmentScoreTableTypeScoreTableIdentifiers_QNAME, Sif3AssessmentScoreTableType.ScoreTableIdentifiers.class, Sif3AssessmentScoreTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreTablePublishDate", scope = Sif3AssessmentScoreTableType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentScoreTableTypeScoreTablePublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentScoreTableTypeScoreTablePublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentScoreTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreTableVersion", scope = Sif3AssessmentScoreTableType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentScoreTableTypeScoreTableVersion(String value) {
        return new JAXBElement<String>(_Sif3AssessmentScoreTableTypeScoreTableVersion_QNAME, String.class, Sif3AssessmentScoreTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreValues }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoreValues", scope = Sif3AssessmentScoreTableType.class)
    public JAXBElement<Sif3AssessmentScoreTableType.ScoreValues> createSif3AssessmentScoreTableTypeScoreValues(Sif3AssessmentScoreTableType.ScoreValues value) {
        return new JAXBElement<Sif3AssessmentScoreTableType.ScoreValues>(_Sif3AssessmentScoreTableTypeScoreValues_QNAME, Sif3AssessmentScoreTableType.ScoreValues.class, Sif3AssessmentScoreTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentScoreTableType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentScoreTableTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentScoreTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentScoreTableType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentScoreTableTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentScoreTableType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContactInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactInfo", scope = SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class)
    public JAXBElement<ContactInfoType> createSIFReportObjectTypeReportInfoReportSubmitterInfoContactInfo(ContactInfoType value) {
        return new JAXBElement<ContactInfoType>(_ContactInfo_QNAME, ContactInfoType.class, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumber", scope = SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class)
    public JAXBElement<PhoneNumberType> createSIFReportObjectTypeReportInfoReportSubmitterInfoPhoneNumber(PhoneNumberType value) {
        return new JAXBElement<PhoneNumberType>(_PhoneNumber_QNAME, PhoneNumberType.class, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_RefId", scope = SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class)
    public JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId> createSIFReportObjectTypeReportInfoReportSubmitterInfoSIFRefId(SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId value) {
        return new JAXBElement<SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId>(_SIFReportObjectTypeReportInfoReportSubmitterInfoSIFRefId_QNAME, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.SIFRefId.class, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubmitterDepartment", scope = SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSIFReportObjectTypeReportInfoReportSubmitterInfoSubmitterDepartment(String value) {
        return new JAXBElement<String>(_SIFReportObjectTypeReportInfoReportSubmitterInfoSubmitterDepartment_QNAME, String.class, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubmitterNotes", scope = SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class)
    public JAXBElement<String> createSIFReportObjectTypeReportInfoReportSubmitterInfoSubmitterNotes(String value) {
        return new JAXBElement<String>(_SIFReportObjectTypeReportInfoReportSubmitterInfoSubmitterNotes_QNAME, String.class, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address", scope = SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class)
    public JAXBElement<AddressType> createSIFReportObjectTypeReportInfoReportSubmitterInfoAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, SIFReportObjectType.ReportInfo.ReportSubmitterInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.LearningObjectives }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningObjectives", scope = ActivityType.class)
    public JAXBElement<ActivityType.LearningObjectives> createActivityTypeLearningObjectives(ActivityType.LearningObjectives value) {
        return new JAXBElement<ActivityType.LearningObjectives>(_ActivityTypeLearningObjectives_QNAME, ActivityType.LearningObjectives.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.Students }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Students", scope = ActivityType.class)
    public JAXBElement<ActivityType.Students> createActivityTypeStudents(ActivityType.Students value) {
        return new JAXBElement<ActivityType.Students>(_ActivityTypeStudents_QNAME, ActivityType.Students.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Points", scope = ActivityType.class)
    public JAXBElement<Long> createActivityTypePoints(Long value) {
        return new JAXBElement<Long>(_ActivityTypePoints_QNAME, Long.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Title", scope = ActivityType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createActivityTypeTitle(String value) {
        return new JAXBElement<String>(_StaffPersonalTypeTitle_QNAME, String.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.EssentialMaterials }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EssentialMaterials", scope = ActivityType.class)
    public JAXBElement<ActivityType.EssentialMaterials> createActivityTypeEssentialMaterials(ActivityType.EssentialMaterials value) {
        return new JAXBElement<ActivityType.EssentialMaterials>(_ActivityTypeEssentialMaterials_QNAME, ActivityType.EssentialMaterials.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentRefId", scope = ActivityType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createActivityTypeAssessmentRefId(String value) {
        return new JAXBElement<String>(_ActivityTypeAssessmentRefId_QNAME, String.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ActivityWeight", scope = ActivityType.class)
    public JAXBElement<BigDecimal> createActivityTypeActivityWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ActivityTypeActivityWeight_QNAME, BigDecimal.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.TechnicalRequirements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TechnicalRequirements", scope = ActivityType.class)
    public JAXBElement<ActivityType.TechnicalRequirements> createActivityTypeTechnicalRequirements(ActivityType.TechnicalRequirements value) {
        return new JAXBElement<ActivityType.TechnicalRequirements>(_ActivityTypeTechnicalRequirements_QNAME, ActivityType.TechnicalRequirements.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.Prerequisites }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Prerequisites", scope = ActivityType.class)
    public JAXBElement<ActivityType.Prerequisites> createActivityTypePrerequisites(ActivityType.Prerequisites value) {
        return new JAXBElement<ActivityType.Prerequisites>(_ActivityTypePrerequisites_QNAME, ActivityType.Prerequisites.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.SoftwareRequirementList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SoftwareRequirementList", scope = ActivityType.class)
    public JAXBElement<ActivityType.SoftwareRequirementList> createActivityTypeSoftwareRequirementList(ActivityType.SoftwareRequirementList value) {
        return new JAXBElement<ActivityType.SoftwareRequirementList>(_ActivityTypeSoftwareRequirementList_QNAME, ActivityType.SoftwareRequirementList.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.LearningResources }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningResources", scope = ActivityType.class)
    public JAXBElement<ActivityType.LearningResources> createActivityTypeLearningResources(ActivityType.LearningResources value) {
        return new JAXBElement<ActivityType.LearningResources>(_LearningResources_QNAME, ActivityType.LearningResources.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MaxAttemptsAllowed", scope = ActivityType.class)
    public JAXBElement<Long> createActivityTypeMaxAttemptsAllowed(Long value) {
        return new JAXBElement<Long>(_ActivityTypeMaxAttemptsAllowed_QNAME, Long.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.Evaluation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Evaluation", scope = ActivityType.class)
    public JAXBElement<ActivityType.Evaluation> createActivityTypeEvaluation(ActivityType.Evaluation value) {
        return new JAXBElement<ActivityType.Evaluation>(_ActivityTypeEvaluation_QNAME, ActivityType.Evaluation.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Preamble", scope = ActivityType.class)
    public JAXBElement<String> createActivityTypePreamble(String value) {
        return new JAXBElement<String>(_ActivityTypePreamble_QNAME, String.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.SourceObjects }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SourceObjects", scope = ActivityType.class)
    public JAXBElement<ActivityType.SourceObjects> createActivityTypeSourceObjects(ActivityType.SourceObjects value) {
        return new JAXBElement<ActivityType.SourceObjects>(_ActivityTypeSourceObjects_QNAME, ActivityType.SourceObjects.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = ActivityType.class)
    public JAXBElement<SIFMetadataType> createActivityTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectArea", scope = ActivityType.class)
    public JAXBElement<SubjectAreaType> createActivityTypeSubjectArea(SubjectAreaType value) {
        return new JAXBElement<SubjectAreaType>(_SubjectArea_QNAME, SubjectAreaType.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = ActivityType.class)
    public JAXBElement<SIFExtendedElementsType> createActivityTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.LearningStandards }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LearningStandards", scope = ActivityType.class)
    public JAXBElement<ActivityType.LearningStandards> createActivityTypeLearningStandards(ActivityType.LearningStandards value) {
        return new JAXBElement<ActivityType.LearningStandards>(_LearningResourceTypeLearningStandards_QNAME, ActivityType.LearningStandards.class, ActivityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FeedbackDescription", scope = Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackDescription(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackDescription_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FeedbackSource", scope = Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackSource(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackSource_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FeedbackCode", scope = Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackCode(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemFeedbackCode_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DiagnosticStatement", scope = Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemFeedbackItemsFeedbackItemDiagnosticStatement(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDiagnosticStatement_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.FeedbackItems.FeedbackItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PublishInDirectory", scope = SchoolContactListType.SchoolContact.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSchoolContactListTypeSchoolContactPublishInDirectory(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_PublishInDirectory_QNAME, AUCodeSetsYesOrNoCategoryType.class, SchoolContactListType.SchoolContact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BirthDate", scope = AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    public JAXBElement<XMLGregorianCalendar> createAssessmentRegistrationTypeAssessmentStudentSnapshotBirthDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_BirthDate_QNAME, XMLGregorianCalendar.class, AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Sex", scope = AssessmentRegistrationType.AssessmentStudentSnapshot.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentRegistrationTypeAssessmentStudentSnapshotSex(String value) {
        return new JAXBElement<String>(_StudentSnapshotTypeSex_QNAME, String.class, AssessmentRegistrationType.AssessmentStudentSnapshot.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetType.AssetIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetIdentifiers", scope = Sif3AssessmentAssetType.class)
    public JAXBElement<Sif3AssessmentAssetType.AssetIdentifiers> createSif3AssessmentAssetTypeAssetIdentifiers(Sif3AssessmentAssetType.AssetIdentifiers value) {
        return new JAXBElement<Sif3AssessmentAssetType.AssetIdentifiers>(_Sif3AssessmentAssetTypeAssetIdentifiers_QNAME, Sif3AssessmentAssetType.AssetIdentifiers.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetLanguage", scope = Sif3AssessmentAssetType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAssetTypeAssetLanguage(String value) {
        return new JAXBElement<String>(_Sif3AssessmentAssetTypeAssetLanguage_QNAME, String.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetName", scope = Sif3AssessmentAssetType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAssetTypeAssetName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentAssetTypeAssetName_QNAME, String.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetVersion", scope = Sif3AssessmentAssetType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAssetTypeAssetVersion(String value) {
        return new JAXBElement<String>(_Sif3AssessmentAssetTypeAssetVersion_QNAME, String.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentAssetType.AssetLearningStandards }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetLearningStandards", scope = Sif3AssessmentAssetType.class)
    public JAXBElement<Sif3AssessmentAssetType.AssetLearningStandards> createSif3AssessmentAssetTypeAssetLearningStandards(Sif3AssessmentAssetType.AssetLearningStandards value) {
        return new JAXBElement<Sif3AssessmentAssetType.AssetLearningStandards>(_Sif3AssessmentAssetTypeAssetLearningStandards_QNAME, Sif3AssessmentAssetType.AssetLearningStandards.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetOwner", scope = Sif3AssessmentAssetType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentAssetTypeAssetOwner(String value) {
        return new JAXBElement<String>(_Sif3AssessmentAssetTypeAssetOwner_QNAME, String.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentAssetType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentAssetTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetSubjects", scope = Sif3AssessmentAssetType.class)
    public JAXBElement<SubjectAreaListType> createSif3AssessmentAssetTypeAssetSubjects(SubjectAreaListType value) {
        return new JAXBElement<SubjectAreaListType>(_Sif3AssessmentAssetTypeAssetSubjects_QNAME, SubjectAreaListType.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentAssetType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentAssetTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetGradeLevels", scope = Sif3AssessmentAssetType.class)
    public JAXBElement<YearLevelsType> createSif3AssessmentAssetTypeAssetGradeLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_Sif3AssessmentAssetTypeAssetGradeLevels_QNAME, YearLevelsType.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssetPublishDate", scope = Sif3AssessmentAssetType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentAssetTypeAssetPublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentAssetTypeAssetPublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentAssetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRubricType.Scores }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Scores", scope = Sif3AssessmentRubricType.class)
    public JAXBElement<Sif3AssessmentRubricType.Scores> createSif3AssessmentRubricTypeScores(Sif3AssessmentRubricType.Scores value) {
        return new JAXBElement<Sif3AssessmentRubricType.Scores>(_Sif3AssessmentRubricTypeScores_QNAME, Sif3AssessmentRubricType.Scores.class, Sif3AssessmentRubricType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ScoringGuideReference", scope = Sif3AssessmentRubricType.class)
    public JAXBElement<String> createSif3AssessmentRubricTypeScoringGuideReference(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRubricTypeScoringGuideReference_QNAME, String.class, Sif3AssessmentRubricType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RubricVersion", scope = Sif3AssessmentRubricType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentRubricTypeRubricVersion(String value) {
        return new JAXBElement<String>(_Sif3AssessmentRubricTypeRubricVersion_QNAME, String.class, Sif3AssessmentRubricType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RubricPublishDate", scope = Sif3AssessmentRubricType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentRubricTypeRubricPublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentRubricTypeRubricPublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentRubricType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentRubricType.RubricIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RubricIdentifiers", scope = Sif3AssessmentRubricType.class)
    public JAXBElement<Sif3AssessmentRubricType.RubricIdentifiers> createSif3AssessmentRubricTypeRubricIdentifiers(Sif3AssessmentRubricType.RubricIdentifiers value) {
        return new JAXBElement<Sif3AssessmentRubricType.RubricIdentifiers>(_Sif3AssessmentRubricTypeRubricIdentifiers_QNAME, Sif3AssessmentRubricType.RubricIdentifiers.class, Sif3AssessmentRubricType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentRubricType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentRubricTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentRubricType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentRubricType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentRubricTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentRubricType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ContactTitle", scope = PrincipalInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createPrincipalInfoTypeContactTitle(String value) {
        return new JAXBElement<String>(_PrincipalInfoTypeContactTitle_QNAME, String.class, PrincipalInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumberList", scope = PrincipalInfoType.class)
    public JAXBElement<PhoneNumberListType> createPrincipalInfoTypePhoneNumberList(PhoneNumberListType value) {
        return new JAXBElement<PhoneNumberListType>(_PhoneNumberList_QNAME, PhoneNumberListType.class, PrincipalInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EmailList", scope = PrincipalInfoType.class)
    public JAXBElement<EmailListType> createPrincipalInfoTypeEmailList(EmailListType value) {
        return new JAXBElement<EmailListType>(_EmailList_QNAME, EmailListType.class, PrincipalInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFHeaderDataModelType.SIFSecurity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Security", scope = SIFHeaderDataModelType.class)
    public JAXBElement<SIFHeaderDataModelType.SIFSecurity> createSIFHeaderDataModelTypeSIFSecurity(SIFHeaderDataModelType.SIFSecurity value) {
        return new JAXBElement<SIFHeaderDataModelType.SIFSecurity>(_SIFHeaderDataModelTypeSIFSecurity_QNAME, SIFHeaderDataModelType.SIFSecurity.class, SIFHeaderDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFContextsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Contexts", scope = SIFHeaderDataModelType.class)
    public JAXBElement<SIFContextsType> createSIFHeaderDataModelTypeSIFContexts(SIFContextsType value) {
        return new JAXBElement<SIFContextsType>(_SIFContexts_QNAME, SIFContextsType.class, SIFHeaderDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_DestinationId", scope = SIFHeaderDataModelType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSIFHeaderDataModelTypeSIFDestinationId(String value) {
        return new JAXBElement<String>(_SIFHeaderDataModelTypeSIFDestinationId_QNAME, String.class, SIFHeaderDataModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = SectionInfoType.LocationOfInstruction.class)
    public JAXBElement<OtherCodeListType> createSectionInfoTypeLocationOfInstructionOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, SectionInfoType.LocationOfInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Holder", scope = LearningStandardDocumentType.Copyright.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningStandardDocumentTypeCopyrightHolder(String value) {
        return new JAXBElement<String>(_LearningStandardDocumentTypeCopyrightHolder_QNAME, String.class, LearningStandardDocumentType.Copyright.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Date", scope = LearningStandardDocumentType.Copyright.class)
    public JAXBElement<XMLGregorianCalendar> createLearningStandardDocumentTypeCopyrightDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LearningResourceTypeEvaluationsEvaluationDate_QNAME, XMLGregorianCalendar.class, LearningStandardDocumentType.Copyright.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AlternateIdentificationCodes", scope = LearningStandardItemType.StandardIdentifier.class)
    public JAXBElement<LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes> createLearningStandardItemTypeStandardIdentifierAlternateIdentificationCodes(LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes value) {
        return new JAXBElement<LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes>(_LearningStandardItemTypeStandardIdentifierAlternateIdentificationCodes_QNAME, LearningStandardItemType.StandardIdentifier.AlternateIdentificationCodes.class, LearningStandardItemType.StandardIdentifier.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevel", scope = LearningStandardItemType.StandardIdentifier.class)
    public JAXBElement<YearLevelType> createLearningStandardItemTypeStandardIdentifierYearLevel(YearLevelType value) {
        return new JAXBElement<YearLevelType>(_YearLevel_QNAME, YearLevelType.class, LearningStandardItemType.StandardIdentifier.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "IndicatorNumber", scope = LearningStandardItemType.StandardIdentifier.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningStandardItemTypeStandardIdentifierIndicatorNumber(String value) {
        return new JAXBElement<String>(_LearningStandardItemTypeStandardIdentifierIndicatorNumber_QNAME, String.class, LearningStandardItemType.StandardIdentifier.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Benchmark", scope = LearningStandardItemType.StandardIdentifier.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningStandardItemTypeStandardIdentifierBenchmark(String value) {
        return new JAXBElement<String>(_LearningStandardItemTypeStandardIdentifierBenchmark_QNAME, String.class, LearningStandardItemType.StandardIdentifier.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = SectionInfoType.MediumOfInstruction.class)
    public JAXBElement<OtherCodeListType> createSectionInfoTypeMediumOfInstructionOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, SectionInfoType.MediumOfInstruction.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityType.IdentityAssertions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "IdentityAssertions", scope = IdentityType.class)
    public JAXBElement<IdentityType.IdentityAssertions> createIdentityTypeIdentityAssertions(IdentityType.IdentityAssertions value) {
        return new JAXBElement<IdentityType.IdentityAssertions>(_IdentityTypeIdentityAssertions_QNAME, IdentityType.IdentityAssertions.class, IdentityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityType.PasswordList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PasswordList", scope = IdentityType.class)
    public JAXBElement<IdentityType.PasswordList> createIdentityTypePasswordList(IdentityType.PasswordList value) {
        return new JAXBElement<IdentityType.PasswordList>(_IdentityTypePasswordList_QNAME, IdentityType.PasswordList.class, IdentityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AuthenticationSourceGlobalUID", scope = IdentityType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createIdentityTypeAuthenticationSourceGlobalUID(String value) {
        return new JAXBElement<String>(_IdentityTypeAuthenticationSourceGlobalUID_QNAME, String.class, IdentityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = IdentityType.class)
    public JAXBElement<SIFMetadataType> createIdentityTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, IdentityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = IdentityType.class)
    public JAXBElement<SIFExtendedElementsType> createIdentityTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, IdentityType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FirstInstructionDate", scope = CalendarSummaryType.class)
    public JAXBElement<XMLGregorianCalendar> createCalendarSummaryTypeFirstInstructionDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CalendarSummaryTypeFirstInstructionDate_QNAME, XMLGregorianCalendar.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LastInstructionDate", scope = CalendarSummaryType.class)
    public JAXBElement<XMLGregorianCalendar> createCalendarSummaryTypeLastInstructionDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CalendarSummaryTypeLastInstructionDate_QNAME, XMLGregorianCalendar.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GraduationDate", scope = CalendarSummaryType.class)
    public JAXBElement<String> createCalendarSummaryTypeGraduationDate(String value) {
        return new JAXBElement<String>(_GraduationDate_QNAME, String.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "InstructionalMinutes", scope = CalendarSummaryType.class)
    public JAXBElement<Long> createCalendarSummaryTypeInstructionalMinutes(Long value) {
        return new JAXBElement<Long>(_CalendarSummaryTypeInstructionalMinutes_QNAME, Long.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = CalendarSummaryType.class)
    public JAXBElement<String> createCalendarSummaryTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "MinutesPerDay", scope = CalendarSummaryType.class)
    public JAXBElement<Long> createCalendarSummaryTypeMinutesPerDay(Long value) {
        return new JAXBElement<Long>(_CalendarSummaryTypeMinutesPerDay_QNAME, Long.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDate", scope = CalendarSummaryType.class)
    public JAXBElement<XMLGregorianCalendar> createCalendarSummaryTypeEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeEndDate_QNAME, XMLGregorianCalendar.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDate", scope = CalendarSummaryType.class)
    public JAXBElement<XMLGregorianCalendar> createCalendarSummaryTypeStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeStartDate_QNAME, XMLGregorianCalendar.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = CalendarSummaryType.class)
    public JAXBElement<SIFMetadataType> createCalendarSummaryTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = CalendarSummaryType.class)
    public JAXBElement<SIFExtendedElementsType> createCalendarSummaryTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = CalendarSummaryType.class)
    public JAXBElement<YearLevelsType> createCalendarSummaryTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, CalendarSummaryType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumber", scope = LearningResourceType.Contacts.Contact.class)
    public JAXBElement<PhoneNumberType> createLearningResourceTypeContactsContactPhoneNumber(PhoneNumberType value) {
        return new JAXBElement<PhoneNumberType>(_PhoneNumber_QNAME, PhoneNumberType.class, LearningResourceType.Contacts.Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Name", scope = LearningResourceType.Contacts.Contact.class)
    public JAXBElement<NameType> createLearningResourceTypeContactsContactName(NameType value) {
        return new JAXBElement<NameType>(_Name_QNAME, NameType.class, LearningResourceType.Contacts.Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Email", scope = LearningResourceType.Contacts.Contact.class)
    public JAXBElement<EmailType> createLearningResourceTypeContactsContactEmail(EmailType value) {
        return new JAXBElement<EmailType>(_Email_QNAME, EmailType.class, LearningResourceType.Contacts.Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Address", scope = LearningResourceType.Contacts.Contact.class)
    public JAXBElement<AddressType> createLearningResourceTypeContactsContactAddress(AddressType value) {
        return new JAXBElement<AddressType>(_Address_QNAME, AddressType.class, LearningResourceType.Contacts.Contact.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_RefId", scope = ResourceUsageType.ResourceReportLineList.ResourceReportLine.class)
    public JAXBElement<ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId> createResourceUsageTypeResourceReportLineListResourceReportLineSIFRefId(ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId value) {
        return new JAXBElement<ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId>(_SIFReportObjectTypeReportInfoReportSubmitterInfoSIFRefId_QNAME, ResourceUsageType.ResourceReportLineList.ResourceReportLine.SIFRefId.class, ResourceUsageType.ResourceReportLineList.ResourceReportLine.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndDate", scope = ResourceUsageType.ResourceReportLineList.ResourceReportLine.class)
    public JAXBElement<XMLGregorianCalendar> createResourceUsageTypeResourceReportLineListResourceReportLineEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeEndDate_QNAME, XMLGregorianCalendar.class, ResourceUsageType.ResourceReportLineList.ResourceReportLine.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = LanguageListType.Language.class)
    public JAXBElement<OtherCodeListType> createLanguageListTypeLanguageOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, LanguageListType.Language.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Dialect", scope = LanguageListType.Language.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLanguageListTypeLanguageDialect(String value) {
        return new JAXBElement<String>(_LanguageListTypeLanguageDialect_QNAME, String.class, LanguageListType.Language.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LanguageType", scope = LanguageListType.Language.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLanguageListTypeLanguageLanguageType(String value) {
        return new JAXBElement<String>(_LanguageListTypeLanguageLanguageType_QNAME, String.class, LanguageListType.Language.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "EndTime", scope = TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class)
    public JAXBElement<XMLGregorianCalendar> createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodEndTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodEndTime_QNAME, XMLGregorianCalendar.class, TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartTime", scope = TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class)
    public JAXBElement<XMLGregorianCalendar> createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodStartTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStartTime_QNAME, XMLGregorianCalendar.class, TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "InstructionalMinutes", scope = TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class)
    public JAXBElement<Long> createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodInstructionalMinutes(Long value) {
        return new JAXBElement<Long>(_CalendarSummaryTypeInstructionalMinutes_QNAME, Long.class, TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BellPeriod", scope = TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodBellPeriod(String value) {
        return new JAXBElement<String>(_TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodBellPeriod_QNAME, String.class, TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "UseInAttendanceCalculations", scope = TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodUseInAttendanceCalculations(String value) {
        return new JAXBElement<String>(_TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodUseInAttendanceCalculations_QNAME, String.class, TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RegularSchoolPeriod", scope = TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodRegularSchoolPeriod(String value) {
        return new JAXBElement<String>(_TimeTableTypeTimeTableDayListTimeTableDayTimeTablePeriodListTimeTablePeriodRegularSchoolPeriod_QNAME, String.class, TimeTableType.TimeTableDayList.TimeTableDay.TimeTablePeriodList.TimeTablePeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoleScopeList", scope = SystemRoleType.SystemContextList.SystemContext.RoleList.Role.class)
    public JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList> createSystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeList(SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList value) {
        return new JAXBElement<SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList>(_SystemRoleTypeSystemContextListSystemContextRoleListRoleRoleScopeList_QNAME, SystemRoleType.SystemContextList.SystemContext.RoleList.Role.RoleScopeList.class, SystemRoleType.SystemContextList.SystemContext.RoleList.Role.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3StudentResponseSetType.class)
    public JAXBElement<SIFMetadataType> createSif3StudentResponseSetTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3StudentResponseSetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3StudentResponseSetType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3StudentResponseSetTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3StudentResponseSetType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.RelatedLearningStandardItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RelatedLearningStandardItems", scope = LearningStandardItemType.class)
    public JAXBElement<LearningStandardItemType.RelatedLearningStandardItems> createLearningStandardItemTypeRelatedLearningStandardItems(LearningStandardItemType.RelatedLearningStandardItems value) {
        return new JAXBElement<LearningStandardItemType.RelatedLearningStandardItems>(_LearningStandardItemTypeRelatedLearningStandardItems_QNAME, LearningStandardItemType.RelatedLearningStandardItems.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.StandardIdentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StandardIdentifier", scope = LearningStandardItemType.class)
    public JAXBElement<LearningStandardItemType.StandardIdentifier> createLearningStandardItemTypeStandardIdentifier(LearningStandardItemType.StandardIdentifier value) {
        return new JAXBElement<LearningStandardItemType.StandardIdentifier>(_LearningStandardItemTypeStandardIdentifier_QNAME, LearningStandardItemType.StandardIdentifier.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.StatementCodes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StatementCodes", scope = LearningStandardItemType.class)
    public JAXBElement<LearningStandardItemType.StatementCodes> createLearningStandardItemTypeStatementCodes(LearningStandardItemType.StatementCodes value) {
        return new JAXBElement<LearningStandardItemType.StatementCodes>(_LearningStandardItemTypeStatementCodes_QNAME, LearningStandardItemType.StatementCodes.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ACStrandSubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ACStrandSubjectArea", scope = LearningStandardItemType.class)
    public JAXBElement<ACStrandSubjectAreaType> createLearningStandardItemTypeACStrandSubjectArea(ACStrandSubjectAreaType value) {
        return new JAXBElement<ACStrandSubjectAreaType>(_ACStrandSubjectArea_QNAME, ACStrandSubjectAreaType.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.StandardSettingBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StandardSettingBody", scope = LearningStandardItemType.class)
    public JAXBElement<LearningStandardItemType.StandardSettingBody> createLearningStandardItemTypeStandardSettingBody(LearningStandardItemType.StandardSettingBody value) {
        return new JAXBElement<LearningStandardItemType.StandardSettingBody>(_LearningStandardItemTypeStandardSettingBody_QNAME, LearningStandardItemType.StandardSettingBody.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.Resources }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Resources", scope = LearningStandardItemType.class)
    public JAXBElement<LearningStandardItemType.Resources> createLearningStandardItemTypeResources(LearningStandardItemType.Resources value) {
        return new JAXBElement<LearningStandardItemType.Resources>(_LearningStandardItemTypeResources_QNAME, LearningStandardItemType.Resources.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Level5", scope = LearningStandardItemType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningStandardItemTypeLevel5(String value) {
        return new JAXBElement<String>(_LearningStandardItemTypeLevel5_QNAME, String.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Level4", scope = LearningStandardItemType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createLearningStandardItemTypeLevel4(String value) {
        return new JAXBElement<String>(_LearningStandardItemTypeLevel4_QNAME, String.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.Statements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Statements", scope = LearningStandardItemType.class)
    public JAXBElement<LearningStandardItemType.Statements> createLearningStandardItemTypeStatements(LearningStandardItemType.Statements value) {
        return new JAXBElement<LearningStandardItemType.Statements>(_LearningStandardItemTypeStatements_QNAME, LearningStandardItemType.Statements.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LearningStandardItemType.PredecessorItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PredecessorItems", scope = LearningStandardItemType.class)
    public JAXBElement<LearningStandardItemType.PredecessorItems> createLearningStandardItemTypePredecessorItems(LearningStandardItemType.PredecessorItems value) {
        return new JAXBElement<LearningStandardItemType.PredecessorItems>(_LearningStandardItemTypePredecessorItems_QNAME, LearningStandardItemType.PredecessorItems.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = LearningStandardItemType.class)
    public JAXBElement<SIFMetadataType> createLearningStandardItemTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = LearningStandardItemType.class)
    public JAXBElement<SIFExtendedElementsType> createLearningStandardItemTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, LearningStandardItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReasonLeft", scope = StudentSDTNType.PreviousSchoolsList.PreviousSchool.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSDTNTypePreviousSchoolsListPreviousSchoolReasonLeft(String value) {
        return new JAXBElement<String>(_StudentSDTNTypePreviousSchoolsListPreviousSchoolReasonLeft_QNAME, String.class, StudentSDTNType.PreviousSchoolsList.PreviousSchool.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FeedbackList", scope = Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.class)
    public JAXBElement<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList> createSif3AssessmentScoreTableTypeScoreValuesScoreValueFeedbackList(Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList value) {
        return new JAXBElement<Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList>(_Sif3AssessmentScoreTableTypeScoreValuesScoreValueFeedbackList_QNAME, Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.class, Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PassFailIndicator", scope = Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentScoreTableTypeScoreValuesScoreValuePassFailIndicator(String value) {
        return new JAXBElement<String>(_Sif3AssessmentScoreTableTypeScoreValuesScoreValuePassFailIndicator_QNAME, String.class, Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TraitScoreCode", scope = Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreCode(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreCode_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TraitScoreValue", scope = Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreValue(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreValue_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TraitScoreType", scope = Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreType(String value) {
        return new JAXBElement<String>(_Sif3StudentResponseSetTypeItemsItemTraitScoresTraitScoreTraitScoreType_QNAME, String.class, Sif3StudentResponseSetType.Items.Item.TraitScores.TraitScore.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Location", scope = StudentActivityInfoType.class)
    public JAXBElement<LocationType> createStudentActivityInfoTypeLocation(LocationType value) {
        return new JAXBElement<LocationType>(_Location_QNAME, LocationType.class, StudentActivityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CurricularStatus", scope = StudentActivityInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createStudentActivityInfoTypeCurricularStatus(String value) {
        return new JAXBElement<String>(_StudentActivityInfoTypeCurricularStatus_QNAME, String.class, StudentActivityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = StudentActivityInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentActivityInfoTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, StudentActivityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StudentActivityInfoType.class)
    public JAXBElement<SIFMetadataType> createStudentActivityInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StudentActivityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudentActivityLevel", scope = StudentActivityInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentActivityInfoTypeStudentActivityLevel(String value) {
        return new JAXBElement<String>(_StudentActivityInfoTypeStudentActivityLevel_QNAME, String.class, StudentActivityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StudentActivityInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createStudentActivityInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StudentActivityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = StudentActivityInfoType.class)
    public JAXBElement<YearLevelsType> createStudentActivityInfoTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, StudentActivityInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = AttendanceCodeType.class)
    public JAXBElement<OtherCodeListType> createAttendanceCodeTypeOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, AttendanceCodeType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Excluded", scope = AggregateStatisticFactType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAggregateStatisticFactTypeExcluded(String value) {
        return new JAXBElement<String>(_AggregateStatisticFactTypeExcluded_QNAME, String.class, AggregateStatisticFactType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = AggregateStatisticFactType.class)
    public JAXBElement<SIFMetadataType> createAggregateStatisticFactTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, AggregateStatisticFactType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = AggregateStatisticFactType.class)
    public JAXBElement<SIFExtendedElementsType> createAggregateStatisticFactTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, AggregateStatisticFactType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OtherCodeListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherCodeList", scope = StudentSchoolEnrollmentType.CatchmentStatus.class)
    public JAXBElement<OtherCodeListType> createStudentSchoolEnrollmentTypeCatchmentStatusOtherCodeList(OtherCodeListType value) {
        return new JAXBElement<OtherCodeListType>(_OtherCodeList_QNAME, OtherCodeListType.class, StudentSchoolEnrollmentType.CatchmentStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FinishDate", scope = ActivityType.ActivityTime.class)
    public JAXBElement<XMLGregorianCalendar> createActivityTypeActivityTimeFinishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ActivityTypeActivityTimeFinishDate_QNAME, XMLGregorianCalendar.class, ActivityType.ActivityTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StartDate", scope = ActivityType.ActivityTime.class)
    public JAXBElement<XMLGregorianCalendar> createActivityTypeActivityTimeStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StudentActivityParticipationTypeStartDate_QNAME, XMLGregorianCalendar.class, ActivityType.ActivityTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DueDate", scope = ActivityType.ActivityTime.class)
    public JAXBElement<XMLGregorianCalendar> createActivityTypeActivityTimeDueDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReportManifestTypeReportingPeriodDueDate_QNAME, XMLGregorianCalendar.class, ActivityType.ActivityTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivityType.ActivityTime.Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Duration", scope = ActivityType.ActivityTime.class)
    public JAXBElement<ActivityType.ActivityTime.Duration> createActivityTypeActivityTimeDuration(ActivityType.ActivityTime.Duration value) {
        return new JAXBElement<ActivityType.ActivityTime.Duration>(_ActivityTypeActivityTimeDuration_QNAME, ActivityType.ActivityTime.Duration.class, ActivityType.ActivityTime.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaffAssignmentType.StaffActivity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffActivity", scope = StaffAssignmentType.class)
    public JAXBElement<StaffAssignmentType.StaffActivity> createStaffAssignmentTypeStaffActivity(StaffAssignmentType.StaffActivity value) {
        return new JAXBElement<StaffAssignmentType.StaffActivity>(_StaffAssignmentTypeStaffActivity_QNAME, StaffAssignmentType.StaffActivity.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = StaffAssignmentType.class)
    public JAXBElement<XMLGregorianCalendar> createStaffAssignmentTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "JobStartDate", scope = StaffAssignmentType.class)
    public JAXBElement<XMLGregorianCalendar> createStaffAssignmentTypeJobStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StaffAssignmentTypeJobStartDate_QNAME, XMLGregorianCalendar.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = StaffAssignmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStaffAssignmentTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "JobEndDate", scope = StaffAssignmentType.class)
    public JAXBElement<XMLGregorianCalendar> createStaffAssignmentTypeJobEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StaffAssignmentTypeJobEndDate_QNAME, XMLGregorianCalendar.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CasualReliefTeacher", scope = StaffAssignmentType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createStaffAssignmentTypeCasualReliefTeacher(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_StaffAssignmentTypeCasualReliefTeacher_QNAME, AUCodeSetsYesOrNoCategoryType.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "House", scope = StaffAssignmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStaffAssignmentTypeHouse(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeHouse_QNAME, String.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "JobFTE", scope = StaffAssignmentType.class)
    public JAXBElement<BigDecimal> createStaffAssignmentTypeJobFTE(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_StaffAssignmentTypeJobFTE_QNAME, BigDecimal.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Homegroup", scope = StaffAssignmentType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStaffAssignmentTypeHomegroup(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeHomegroup_QNAME, String.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "JobFunction", scope = StaffAssignmentType.class)
    public JAXBElement<String> createStaffAssignmentTypeJobFunction(String value) {
        return new JAXBElement<String>(_StaffAssignmentTypeJobFunction_QNAME, String.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StaffAssignmentType.StaffSubjectList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffSubjectList", scope = StaffAssignmentType.class)
    public JAXBElement<StaffAssignmentType.StaffSubjectList> createStaffAssignmentTypeStaffSubjectList(StaffAssignmentType.StaffSubjectList value) {
        return new JAXBElement<StaffAssignmentType.StaffSubjectList>(_StaffAssignmentTypeStaffSubjectList_QNAME, StaffAssignmentType.StaffSubjectList.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = StaffAssignmentType.class)
    public JAXBElement<SIFMetadataType> createStaffAssignmentTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = StaffAssignmentType.class)
    public JAXBElement<SIFExtendedElementsType> createStaffAssignmentTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = StaffAssignmentType.class)
    public JAXBElement<YearLevelsType> createStaffAssignmentTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, StaffAssignmentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolInfoRefId", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeSchoolInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolInfoRefId_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffLocalId", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeStaffLocalId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStaffLocalId_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectLocalId", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeSubjectLocalId(String value) {
        return new JAXBElement<String>(_StaffAssignmentTypeStaffSubjectListStaffSubjectSubjectLocalId_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableLocalId", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeTimeTableLocalId(String value) {
        return new JAXBElement<String>(_TimeTableCellTypeTimeTableLocalId_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffPersonalRefId", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeStaffPersonalRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeStaffPersonalRefId_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "RoomNumber", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeRoomNumber(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodRoomNumber_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = TimeTableCellType.class)
    public JAXBElement<SIFMetadataType> createTimeTableCellTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = TimeTableCellType.class)
    public JAXBElement<SIFExtendedElementsType> createTimeTableCellTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeachingGroupLocalId", scope = TimeTableCellType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createTimeTableCellTypeTeachingGroupLocalId(String value) {
        return new JAXBElement<String>(_TimeTableCellTypeTeachingGroupLocalId_QNAME, String.class, TimeTableCellType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PreferenceNumber", scope = StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice.class)
    public JAXBElement<Long> createStudentSnapshotTypeStudentSubjectChoiceListStudentSubjectChoicePreferenceNumber(Long value) {
        return new JAXBElement<Long>(_StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoicePreferenceNumber_QNAME, Long.class, StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherSchoolLocalId", scope = StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createStudentSnapshotTypeStudentSubjectChoiceListStudentSubjectChoiceOtherSchoolLocalId(String value) {
        return new JAXBElement<String>(_StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceOtherSchoolLocalId_QNAME, String.class, StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StudyDescription", scope = StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice.class)
    public JAXBElement<SubjectAreaType> createStudentSnapshotTypeStudentSubjectChoiceListStudentSubjectChoiceStudyDescription(SubjectAreaType value) {
        return new JAXBElement<SubjectAreaType>(_StudentSchoolEnrollmentTypeStudentSubjectChoiceListStudentSubjectChoiceStudyDescription_QNAME, SubjectAreaType.class, StudentSnapshotType.StudentSubjectChoiceList.StudentSubjectChoice.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseCredits", scope = SectionInfoType.SchoolCourseInfoOverride.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSchoolCourseInfoOverrideCourseCredits(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideCourseCredits_QNAME, String.class, SectionInfoType.SchoolCourseInfoOverride.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DistrictCourseCode", scope = SectionInfoType.SchoolCourseInfoOverride.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSchoolCourseInfoOverrideDistrictCourseCode(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideDistrictCourseCode_QNAME, String.class, SectionInfoType.SchoolCourseInfoOverride.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateCourseCode", scope = SectionInfoType.SchoolCourseInfoOverride.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSchoolCourseInfoOverrideStateCourseCode(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideStateCourseCode_QNAME, String.class, SectionInfoType.SchoolCourseInfoOverride.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseTitle", scope = SectionInfoType.SchoolCourseInfoOverride.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSchoolCourseInfoOverrideCourseTitle(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideCourseTitle_QNAME, String.class, SectionInfoType.SchoolCourseInfoOverride.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "InstructionalLevel", scope = SectionInfoType.SchoolCourseInfoOverride.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSchoolCourseInfoOverrideInstructionalLevel(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideInstructionalLevel_QNAME, String.class, SectionInfoType.SchoolCourseInfoOverride.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectArea", scope = SectionInfoType.SchoolCourseInfoOverride.class)
    public JAXBElement<SubjectAreaType> createSectionInfoTypeSchoolCourseInfoOverrideSubjectArea(SubjectAreaType value) {
        return new JAXBElement<SubjectAreaType>(_SubjectArea_QNAME, SubjectAreaType.class, SectionInfoType.SchoolCourseInfoOverride.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseCode", scope = SectionInfoType.SchoolCourseInfoOverride.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSectionInfoTypeSchoolCourseInfoOverrideCourseCode(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideCourseCode_QNAME, String.class, SectionInfoType.SchoolCourseInfoOverride.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "DistrictCourseCode", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeDistrictCourseCode(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideDistrictCourseCode_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseCredits", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeCourseCredits(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideCourseCredits_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseContent", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeCourseContent(String value) {
        return new JAXBElement<String>(_SchoolCourseInfoTypeCourseContent_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectAreaList", scope = SchoolCourseInfoType.class)
    public JAXBElement<SubjectAreaListType> createSchoolCourseInfoTypeSubjectAreaList(SubjectAreaListType value) {
        return new JAXBElement<SubjectAreaListType>(_SubjectAreaList_QNAME, SubjectAreaListType.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TermInfoRefId", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeTermInfoRefId(String value) {
        return new JAXBElement<String>(_SectionInfoTypeTermInfoRefId_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = SchoolCourseInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createSchoolCourseInfoTypeSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CoreAcademicCourse", scope = SchoolCourseInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSchoolCourseInfoTypeCoreAcademicCourse(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_SchoolCourseInfoTypeCoreAcademicCourse_QNAME, AUCodeSetsYesOrNoCategoryType.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GraduationRequirement", scope = SchoolCourseInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSchoolCourseInfoTypeGraduationRequirement(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_SchoolCourseInfoTypeGraduationRequirement_QNAME, AUCodeSetsYesOrNoCategoryType.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateCourseCode", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeStateCourseCode(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideStateCourseCode_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Department", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeDepartment(String value) {
        return new JAXBElement<String>(_SchoolCourseInfoTypeDepartment_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolLocalId", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeSchoolLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolLocalId_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "InstructionalLevel", scope = SchoolCourseInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolCourseInfoTypeInstructionalLevel(String value) {
        return new JAXBElement<String>(_SectionInfoTypeSchoolCourseInfoOverrideInstructionalLevel_QNAME, String.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SchoolCourseInfoType.class)
    public JAXBElement<SIFMetadataType> createSchoolCourseInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SchoolCourseInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createSchoolCourseInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SchoolCourseInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.FormAccommodations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FormAccommodations", scope = Sif3AssessmentFormType.class)
    public JAXBElement<Sif3AssessmentFormType.FormAccommodations> createSif3AssessmentFormTypeFormAccommodations(Sif3AssessmentFormType.FormAccommodations value) {
        return new JAXBElement<Sif3AssessmentFormType.FormAccommodations>(_Sif3AssessmentFormTypeFormAccommodations_QNAME, Sif3AssessmentFormType.FormAccommodations.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.FormIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FormIdentifiers", scope = Sif3AssessmentFormType.class)
    public JAXBElement<Sif3AssessmentFormType.FormIdentifiers> createSif3AssessmentFormTypeFormIdentifiers(Sif3AssessmentFormType.FormIdentifiers value) {
        return new JAXBElement<Sif3AssessmentFormType.FormIdentifiers>(_Sif3AssessmentFormTypeFormIdentifiers_QNAME, Sif3AssessmentFormType.FormIdentifiers.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FormVersion", scope = Sif3AssessmentFormType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentFormTypeFormVersion(String value) {
        return new JAXBElement<String>(_Sif3AssessmentFormTypeFormVersion_QNAME, String.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Period", scope = Sif3AssessmentFormType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentFormTypePeriod(String value) {
        return new JAXBElement<String>(_AssessmentFormTypePeriod_QNAME, String.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "GradeLevels", scope = Sif3AssessmentFormType.class)
    public JAXBElement<YearLevelsType> createSif3AssessmentFormTypeGradeLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_Sif3AssessmentFormTypeGradeLevels_QNAME, YearLevelsType.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LanguageListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentFormLanguages", scope = Sif3AssessmentFormType.class)
    public JAXBElement<LanguageListType> createSif3AssessmentFormTypeAssessmentFormLanguages(LanguageListType value) {
        return new JAXBElement<LanguageListType>(_Sif3AssessmentFormTypeAssessmentFormLanguages_QNAME, LanguageListType.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentType", scope = Sif3AssessmentFormType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentFormTypeAssessmentType(String value) {
        return new JAXBElement<String>(_AssessmentFormTypeAssessmentType_QNAME, String.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubjectAreaListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentFormSubjects", scope = Sif3AssessmentFormType.class)
    public JAXBElement<SubjectAreaListType> createSif3AssessmentFormTypeAssessmentFormSubjects(SubjectAreaListType value) {
        return new JAXBElement<SubjectAreaListType>(_Sif3AssessmentFormTypeAssessmentFormSubjects_QNAME, SubjectAreaListType.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Level", scope = Sif3AssessmentFormType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentFormTypeLevel(String value) {
        return new JAXBElement<String>(_AssessmentFormTypeLevel_QNAME, String.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentSubTestRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSubTestRefIds", scope = Sif3AssessmentFormType.class)
    public JAXBElement<Sif3AssessmentFormType.AssessmentSubTestRefIds> createSif3AssessmentFormTypeAssessmentSubTestRefIds(Sif3AssessmentFormType.AssessmentSubTestRefIds value) {
        return new JAXBElement<Sif3AssessmentFormType.AssessmentSubTestRefIds>(_Sif3AssessmentSubTestTypeAssessmentSubTestRefIds_QNAME, Sif3AssessmentFormType.AssessmentSubTestRefIds.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentSections }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentSections", scope = Sif3AssessmentFormType.class)
    public JAXBElement<Sif3AssessmentFormType.AssessmentSections> createSif3AssessmentFormTypeAssessmentSections(Sif3AssessmentFormType.AssessmentSections value) {
        return new JAXBElement<Sif3AssessmentFormType.AssessmentSections>(_Sif3AssessmentFormTypeAssessmentSections_QNAME, Sif3AssessmentFormType.AssessmentSections.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentAssetRefIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentAssetRefIds", scope = Sif3AssessmentFormType.class)
    public JAXBElement<Sif3AssessmentFormType.AssessmentAssetRefIds> createSif3AssessmentFormTypeAssessmentAssetRefIds(Sif3AssessmentFormType.AssessmentAssetRefIds value) {
        return new JAXBElement<Sif3AssessmentFormType.AssessmentAssetRefIds>(_Sif3AssessmentFormTypeAssessmentAssetRefIds_QNAME, Sif3AssessmentFormType.AssessmentAssetRefIds.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentFormType.AssessmentPlatforms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AssessmentPlatforms", scope = Sif3AssessmentFormType.class)
    public JAXBElement<Sif3AssessmentFormType.AssessmentPlatforms> createSif3AssessmentFormTypeAssessmentPlatforms(Sif3AssessmentFormType.AssessmentPlatforms value) {
        return new JAXBElement<Sif3AssessmentFormType.AssessmentPlatforms>(_Sif3AssessmentFormTypeAssessmentPlatforms_QNAME, Sif3AssessmentFormType.AssessmentPlatforms.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FormPublishDate", scope = Sif3AssessmentFormType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentFormTypeFormPublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentFormTypeFormPublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentFormType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentFormTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentFormType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentFormTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentFormType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ReligiousAffiliation", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeReligiousAffiliation(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeReligiousAffiliation_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolURL", scope = SchoolInfoType.class)
    public JAXBElement<String> createSchoolInfoTypeSchoolURL(String value) {
        return new JAXBElement<String>(_SchoolURL_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrincipalInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PrincipalInfo", scope = SchoolInfoType.class)
    public JAXBElement<PrincipalInfoType> createSchoolInfoTypePrincipalInfo(PrincipalInfoType value) {
        return new JAXBElement<PrincipalInfoType>(_PrincipalInfo_QNAME, PrincipalInfoType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolInfoType.OtherIdList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherIdList", scope = SchoolInfoType.class)
    public JAXBElement<SchoolInfoType.OtherIdList> createSchoolInfoTypeOtherIdList(SchoolInfoType.OtherIdList value) {
        return new JAXBElement<SchoolInfoType.OtherIdList>(_StaffPersonalTypeOtherIdList_QNAME, SchoolInfoType.OtherIdList.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "FederalElectorate", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeFederalElectorate(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeFederalElectorate_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolInfoType.Campus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Campus", scope = SchoolInfoType.class)
    public JAXBElement<SchoolInfoType.Campus> createSchoolInfoTypeCampus(SchoolInfoType.Campus value) {
        return new JAXBElement<SchoolInfoType.Campus>(_SchoolInfoTypeCampus_QNAME, SchoolInfoType.Campus.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SLA", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeSLA(String value) {
        return new JAXBElement<String>(_LEAInfoTypeSLA_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Entity_Open", scope = SchoolInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createSchoolInfoTypeEntityOpen(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolInfoTypeEntityOpen_QNAME, XMLGregorianCalendar.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalGovernmentArea", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeLocalGovernmentArea(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeLocalGovernmentArea_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolDistrict", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeSchoolDistrict(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeSchoolDistrict_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsSystemicStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "NonGovSystemicStatus", scope = SchoolInfoType.class)
    public JAXBElement<AUCodeSetsSystemicStatusType> createSchoolInfoTypeNonGovSystemicStatus(AUCodeSetsSystemicStatusType value) {
        return new JAXBElement<AUCodeSetsSystemicStatusType>(_SchoolInfoTypeNonGovSystemicStatus_QNAME, AUCodeSetsSystemicStatusType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "System", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeSystem(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeSystem_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CommonwealthId", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeCommonwealthId(String value) {
        return new JAXBElement<String>(_LEAInfoTypeCommonwealthId_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolInfoType.SchoolGroupList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolGroupList", scope = SchoolInfoType.class)
    public JAXBElement<SchoolInfoType.SchoolGroupList> createSchoolInfoTypeSchoolGroupList(SchoolInfoType.SchoolGroupList value) {
        return new JAXBElement<SchoolInfoType.SchoolGroupList>(_SchoolInfoTypeSchoolGroupList_QNAME, SchoolInfoType.SchoolGroupList.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Entity_Close", scope = SchoolInfoType.class)
    public JAXBElement<XMLGregorianCalendar> createSchoolInfoTypeEntityClose(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolInfoTypeEntityClose_QNAME, XMLGregorianCalendar.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolInfoType.SchoolFocusList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolFocusList", scope = SchoolInfoType.class)
    public JAXBElement<SchoolInfoType.SchoolFocusList> createSchoolInfoTypeSchoolFocusList(SchoolInfoType.SchoolFocusList value) {
        return new JAXBElement<SchoolInfoType.SchoolFocusList>(_SchoolInfoTypeSchoolFocusList_QNAME, SchoolInfoType.SchoolFocusList.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhoneNumberListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "PhoneNumberList", scope = SchoolInfoType.class)
    public JAXBElement<PhoneNumberListType> createSchoolInfoTypePhoneNumberList(PhoneNumberListType value) {
        return new JAXBElement<PhoneNumberListType>(_PhoneNumberList_QNAME, PhoneNumberListType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SchoolInfoType.class)
    public JAXBElement<SIFMetadataType> createSchoolInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolGeographicLocation", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeSchoolGeographicLocation(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeSchoolGeographicLocation_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SchoolInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createSchoolInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SessionType", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeSessionType(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeSessionType_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "BoardingSchoolStatus", scope = SchoolInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSchoolInfoTypeBoardingSchoolStatus(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_SchoolInfoTypeBoardingSchoolStatus_QNAME, AUCodeSetsYesOrNoCategoryType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolInfoType.OtherLEA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OtherLEA", scope = SchoolInfoType.class)
    public JAXBElement<SchoolInfoType.OtherLEA> createSchoolInfoTypeOtherLEA(SchoolInfoType.OtherLEA value) {
        return new JAXBElement<SchoolInfoType.OtherLEA>(_SchoolInfoTypeOtherLEA_QNAME, SchoolInfoType.OtherLEA.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolDistrictLocalId", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeSchoolDistrictLocalId(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeSchoolDistrictLocalId_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsOperationalStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OperationalStatus", scope = SchoolInfoType.class)
    public JAXBElement<AUCodeSetsOperationalStatusType> createSchoolInfoTypeOperationalStatus(AUCodeSetsOperationalStatusType value) {
        return new JAXBElement<AUCodeSetsOperationalStatusType>(_OperationalStatus_QNAME, AUCodeSetsOperationalStatusType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "AddressList", scope = SchoolInfoType.class)
    public JAXBElement<AddressListType> createSchoolInfoTypeAddressList(AddressListType value) {
        return new JAXBElement<AddressListType>(_AddressList_QNAME, AddressListType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolCoEdStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCoEdStatus", scope = SchoolInfoType.class)
    public JAXBElement<AUCodeSetsSchoolCoEdStatusType> createSchoolInfoTypeSchoolCoEdStatus(AUCodeSetsSchoolCoEdStatusType value) {
        return new JAXBElement<AUCodeSetsSchoolCoEdStatusType>(_SchoolInfoTypeSchoolCoEdStatus_QNAME, AUCodeSetsSchoolCoEdStatusType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "JurisdictionLowerHouse", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeJurisdictionLowerHouse(String value) {
        return new JAXBElement<String>(_LEAInfoTypeJurisdictionLowerHouse_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LEAInfoRefId", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeLEAInfoRefId(String value) {
        return new JAXBElement<String>(_AssessmentRegistrationTypeLEAInfoRefId_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocalId", scope = SchoolInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeLocalId(String value) {
        return new JAXBElement<String>(_LocalId_QNAME, String.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ARIA", scope = SchoolInfoType.class)
    public JAXBElement<BigDecimal> createSchoolInfoTypeARIA(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_SchoolInfoTypeARIA_QNAME, BigDecimal.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SchoolContactListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolContactList", scope = SchoolInfoType.class)
    public JAXBElement<SchoolContactListType> createSchoolInfoTypeSchoolContactList(SchoolContactListType value) {
        return new JAXBElement<SchoolContactListType>(_SchoolContactList_QNAME, SchoolContactListType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolType", scope = SchoolInfoType.class)
    public JAXBElement<AUCodeSetsSchoolLevelType> createSchoolInfoTypeSchoolType(AUCodeSetsSchoolLevelType value) {
        return new JAXBElement<AUCodeSetsSchoolLevelType>(_SchoolInfoTypeSchoolType_QNAME, AUCodeSetsSchoolLevelType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "IndependentSchool", scope = SchoolInfoType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createSchoolInfoTypeIndependentSchool(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_SchoolInfoTypeIndependentSchool_QNAME, AUCodeSetsYesOrNoCategoryType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolEmailList", scope = SchoolInfoType.class)
    public JAXBElement<EmailListType> createSchoolInfoTypeSchoolEmailList(EmailListType value) {
        return new JAXBElement<EmailListType>(_SchoolInfoTypeSchoolEmailList_QNAME, EmailListType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearLevelsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "YearLevels", scope = SchoolInfoType.class)
    public JAXBElement<YearLevelsType> createSchoolInfoTypeYearLevels(YearLevelsType value) {
        return new JAXBElement<YearLevelsType>(_YearLevels_QNAME, YearLevelsType.class, SchoolInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Source", scope = Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentScoreTableTypeScoreValuesScoreValueFeedbackListFeedbackSource(String value) {
        return new JAXBElement<String>(_AggregateStatisticInfoTypeSource_QNAME, String.class, Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Description", scope = Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentScoreTableTypeScoreValuesScoreValueFeedbackListFeedbackDescription(String value) {
        return new JAXBElement<String>(_StudentScoreSetTypeScoresScoreDescription_QNAME, String.class, Sif3AssessmentScoreTableType.ScoreValues.ScoreValue.FeedbackList.Feedback.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationType.LocationRefId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocationRefId", scope = LocationType.class)
    public JAXBElement<LocationType.LocationRefId> createLocationTypeLocationRefId(LocationType.LocationRefId value) {
        return new JAXBElement<LocationType.LocationRefId>(_LocationTypeLocationRefId_QNAME, LocationType.LocationRefId.class, LocationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "LocationName", scope = LocationType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createLocationTypeLocationName(String value) {
        return new JAXBElement<String>(_LocationTypeLocationName_QNAME, String.class, LocationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsYesOrNoCategoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OKToPublish", scope = PersonPictureType.class)
    public JAXBElement<AUCodeSetsYesOrNoCategoryType> createPersonPictureTypeOKToPublish(AUCodeSetsYesOrNoCategoryType value) {
        return new JAXBElement<AUCodeSetsYesOrNoCategoryType>(_PersonPictureTypeOKToPublish_QNAME, AUCodeSetsYesOrNoCategoryType.class, PersonPictureType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = PersonPictureType.class)
    public JAXBElement<SIFMetadataType> createPersonPictureTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, PersonPictureType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = PersonPictureType.class)
    public JAXBElement<SIFExtendedElementsType> createPersonPictureTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, PersonPictureType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SubjectLocalId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentSubjectLocalId(String value) {
        return new JAXBElement<String>(_StaffAssignmentTypeStaffSubjectListStaffSubjectSubjectLocalId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CourseLocalId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentCourseLocalId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeCourseLocalId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TimeTableSubjectRefId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentTimeTableSubjectRefId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTimeTableSubjectRefId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolCourseInfoRefId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentSchoolCourseInfoRefId(String value) {
        return new JAXBElement<String>(_TimeTableSubjectTypeSchoolCourseInfoRefId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "TeachingGroupRefId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentTeachingGroupRefId(String value) {
        return new JAXBElement<String>(_SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentTeachingGroupRefId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SchoolYear", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class)
    public JAXBElement<XMLGregorianCalendar> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentSchoolYear(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SchoolYear_QNAME, XMLGregorianCalendar.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ShortName", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentShortName(String value) {
        return new JAXBElement<String>(_SummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentEnrollmentListEnrollmentShortName_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.EnrollmentList.Enrollment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionSealed", scope = Sif3AssessmentSectionType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSectionTypeSectionSealed(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSectionTypeSectionSealed_QNAME, String.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemSelectionAlgorithm", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<String> createSif3AssessmentSectionTypeItemSelectionAlgorithm(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSectionTypeItemSelectionAlgorithm_QNAME, String.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSectionType.SectionAssets }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionAssets", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<Sif3AssessmentSectionType.SectionAssets> createSif3AssessmentSectionTypeSectionAssets(Sif3AssessmentSectionType.SectionAssets value) {
        return new JAXBElement<Sif3AssessmentSectionType.SectionAssets>(_Sif3AssessmentSectionTypeSectionAssets_QNAME, Sif3AssessmentSectionType.SectionAssets.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSectionType.SectionItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionItems", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<Sif3AssessmentSectionType.SectionItems> createSif3AssessmentSectionTypeSectionItems(Sif3AssessmentSectionType.SectionItems value) {
        return new JAXBElement<Sif3AssessmentSectionType.SectionItems>(_Sif3AssessmentSectionTypeSectionItems_QNAME, Sif3AssessmentSectionType.SectionItems.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link javax.xml.datatype.Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionTimeLimit", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<javax.xml.datatype.Duration> createSif3AssessmentSectionTypeSectionTimeLimit(javax.xml.datatype.Duration value) {
        return new JAXBElement<javax.xml.datatype.Duration>(_Sif3AssessmentSectionTypeSectionTimeLimit_QNAME, javax.xml.datatype.Duration.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionName", scope = Sif3AssessmentSectionType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSectionTypeSectionName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSectionTypeSectionName_QNAME, String.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionVersion", scope = Sif3AssessmentSectionType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSectionTypeSectionVersion(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSectionTypeSectionVersion_QNAME, String.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sif3AssessmentSectionType.SectionIdentifiers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionIdentifiers", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<Sif3AssessmentSectionType.SectionIdentifiers> createSif3AssessmentSectionTypeSectionIdentifiers(Sif3AssessmentSectionType.SectionIdentifiers value) {
        return new JAXBElement<Sif3AssessmentSectionType.SectionIdentifiers>(_Sif3AssessmentSectionTypeSectionIdentifiers_QNAME, Sif3AssessmentSectionType.SectionIdentifiers.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ItemSelectionAlgorithmName", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<String> createSif3AssessmentSectionTypeItemSelectionAlgorithmName(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSectionTypeItemSelectionAlgorithmName_QNAME, String.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionReentry", scope = Sif3AssessmentSectionType.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createSif3AssessmentSectionTypeSectionReentry(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSectionTypeSectionReentry_QNAME, String.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<SIFMetadataType> createSif3AssessmentSectionTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SectionPublishDate", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<XMLGregorianCalendar> createSif3AssessmentSectionTypeSectionPublishDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Sif3AssessmentSectionTypeSectionPublishDate_QNAME, XMLGregorianCalendar.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = Sif3AssessmentSectionType.class)
    public JAXBElement<SIFExtendedElementsType> createSif3AssessmentSectionTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, Sif3AssessmentSectionType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Minimum", scope = AssessmentSubTestType.ScoreRange.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentSubTestTypeScoreRangeMinimum(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeScoreReportingMinimum_QNAME, String.class, AssessmentSubTestType.ScoreRange.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Maximum", scope = AssessmentSubTestType.ScoreRange.class)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAssessmentSubTestTypeScoreRangeMaximum(String value) {
        return new JAXBElement<String>(_Sif3AssessmentSubTestTypeScoreReportingMaximum_QNAME, String.class, AssessmentSubTestType.ScoreRange.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AUCodeSetsSchoolLevelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "CampusType", scope = SchoolInfoType.Campus.class)
    public JAXBElement<AUCodeSetsSchoolLevelType> createSchoolInfoTypeCampusCampusType(AUCodeSetsSchoolLevelType value) {
        return new JAXBElement<AUCodeSetsSchoolLevelType>(_SchoolInfoTypeCampusCampusType_QNAME, AUCodeSetsSchoolLevelType.class, SchoolInfoType.Campus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "ParentSchoolId", scope = SchoolInfoType.Campus.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSchoolInfoTypeCampusParentSchoolId(String value) {
        return new JAXBElement<String>(_SchoolInfoTypeCampusParentSchoolId_QNAME, String.class, SchoolInfoType.Campus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StaffLocalId", scope = SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeSchoolEnrollmentListSchoolEnrollmentStaffStaffLocalId(String value) {
        return new JAXBElement<String>(_TeachingGroupTypeTeachingGroupPeriodListTeachingGroupPeriodStaffLocalId_QNAME, String.class, SummaryEnrollmentInfoType.SchoolEnrollmentList.SchoolEnrollment.Staff.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "OS", scope = ActivityType.SoftwareRequirementList.SoftwareRequirement.class)
    public JAXBElement<String> createActivityTypeSoftwareRequirementListSoftwareRequirementOS(String value) {
        return new JAXBElement<String>(_ActivityTypeSoftwareRequirementListSoftwareRequirementOS_QNAME, String.class, ActivityType.SoftwareRequirementList.SoftwareRequirement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Version", scope = ActivityType.SoftwareRequirementList.SoftwareRequirement.class)
    public JAXBElement<String> createActivityTypeSoftwareRequirementListSoftwareRequirementVersion(String value) {
        return new JAXBElement<String>(_ActivityTypeSoftwareRequirementListSoftwareRequirementVersion_QNAME, String.class, ActivityType.SoftwareRequirementList.SoftwareRequirement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "Vendor", scope = ActivityType.SoftwareRequirementList.SoftwareRequirement.class)
    public JAXBElement<String> createActivityTypeSoftwareRequirementListSoftwareRequirementVendor(String value) {
        return new JAXBElement<String>(_ActivityTypeSoftwareRequirementListSoftwareRequirementVendor_QNAME, String.class, ActivityType.SoftwareRequirementList.SoftwareRequirement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "StateProvinceId", scope = SummaryEnrollmentInfoType.class)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createSummaryEnrollmentInfoTypeStateProvinceId(String value) {
        return new JAXBElement<String>(_StateProvinceId_QNAME, String.class, SummaryEnrollmentInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFMetadataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_Metadata", scope = SummaryEnrollmentInfoType.class)
    public JAXBElement<SIFMetadataType> createSummaryEnrollmentInfoTypeSIFMetadata(SIFMetadataType value) {
        return new JAXBElement<SIFMetadataType>(_AssessmentPackageTypeSIFMetadata_QNAME, SIFMetadataType.class, SummaryEnrollmentInfoType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SIFExtendedElementsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/au/datamodel/1.3", name = "SIF_ExtendedElements", scope = SummaryEnrollmentInfoType.class)
    public JAXBElement<SIFExtendedElementsType> createSummaryEnrollmentInfoTypeSIFExtendedElements(SIFExtendedElementsType value) {
        return new JAXBElement<SIFExtendedElementsType>(_AssessmentPackageTypeSIFExtendedElements_QNAME, SIFExtendedElementsType.class, SummaryEnrollmentInfoType.class, value);
    }

}
