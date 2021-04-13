
package sif3.infra.common.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sif3.infra.common.model package. 
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

    private final static QName _Job_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "job");
    private final static QName _Xquerys_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "xquerys");
    private final static QName _Queue_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "queue");
    private final static QName _Providers_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "providers");
    private final static QName _DeleteRequest_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "deleteRequest");
    private final static QName _DeleteRequests_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "deleteRequests");
    private final static QName _Errors_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "errors");
    private final static QName _UpdateResponse_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "updateResponse");
    private final static QName _Namespaces_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "namespaces");
    private final static QName _Namespace_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "namespace");
    private final static QName _ProvisionRequest_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "provisionRequest");
    private final static QName _Pods_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "pods");
    private final static QName _Queues_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "queues");
    private final static QName _Alerts_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "alerts");
    private final static QName _Environment_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "environment");
    private final static QName _Zones_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "zones");
    private final static QName _DeleteResponses_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "deleteResponses");
    private final static QName _CreateResponse_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "createResponse");
    private final static QName _Xquery_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "xquery");
    private final static QName _Jobs_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "jobs");
    private final static QName _CreateResponses_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "createResponses");
    private final static QName _ProvisionRequests_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "provisionRequests");
    private final static QName _CodeSets_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "codeSets");
    private final static QName _State_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "state");
    private final static QName _Alert_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "alert");
    private final static QName _Provider_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "provider");
    private final static QName _Zone_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "zone");
    private final static QName _DeleteResponse_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "deleteResponse");
    private final static QName _CodeSet_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "codeSet");
    private final static QName _Error_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "error");
    private final static QName _States_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "states");
    private final static QName _UpdateResponses_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "updateResponses");
    private final static QName _Subscription_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "subscription");
    private final static QName _Pod_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "pod");
    private final static QName _Environments_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "environments");
    private final static QName _Subscriptions_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.3", "subscriptions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sif3.infra.common.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubscriptionCollectionType }
     * 
     */
    public SubscriptionCollectionType createSubscriptionCollectionType() {
        return new SubscriptionCollectionType();
    }

    /**
     * Create an instance of {@link PodType }
     * 
     */
    public PodType createPodType() {
        return new PodType();
    }

    /**
     * Create an instance of {@link EnvironmentCollectionType }
     * 
     */
    public EnvironmentCollectionType createEnvironmentCollectionType() {
        return new EnvironmentCollectionType();
    }

    /**
     * Create an instance of {@link SubscriptionType }
     * 
     */
    public SubscriptionType createSubscriptionType() {
        return new SubscriptionType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link StateCollectionType }
     * 
     */
    public StateCollectionType createStateCollectionType() {
        return new StateCollectionType();
    }

    /**
     * Create an instance of {@link UpdateResponseCollectionType }
     * 
     */
    public UpdateResponseCollectionType createUpdateResponseCollectionType() {
        return new UpdateResponseCollectionType();
    }

    /**
     * Create an instance of {@link AlertType }
     * 
     */
    public AlertType createAlertType() {
        return new AlertType();
    }

    /**
     * Create an instance of {@link ProviderType }
     * 
     */
    public ProviderType createProviderType() {
        return new ProviderType();
    }

    /**
     * Create an instance of {@link ZoneType }
     * 
     */
    public ZoneType createZoneType() {
        return new ZoneType();
    }

    /**
     * Create an instance of {@link DeleteResponseType }
     * 
     */
    public DeleteResponseType createDeleteResponseType() {
        return new DeleteResponseType();
    }

    /**
     * Create an instance of {@link CodeSetType }
     * 
     */
    public CodeSetType createCodeSetType() {
        return new CodeSetType();
    }

    /**
     * Create an instance of {@link StateType }
     * 
     */
    public StateType createStateType() {
        return new StateType();
    }

    /**
     * Create an instance of {@link ProvisionRequestCollectionType }
     * 
     */
    public ProvisionRequestCollectionType createProvisionRequestCollectionType() {
        return new ProvisionRequestCollectionType();
    }

    /**
     * Create an instance of {@link CodeSetCollectionType }
     * 
     */
    public CodeSetCollectionType createCodeSetCollectionType() {
        return new CodeSetCollectionType();
    }

    /**
     * Create an instance of {@link CreateResponseCollectionType }
     * 
     */
    public CreateResponseCollectionType createCreateResponseCollectionType() {
        return new CreateResponseCollectionType();
    }

    /**
     * Create an instance of {@link XqueryType }
     * 
     */
    public XqueryType createXqueryType() {
        return new XqueryType();
    }

    /**
     * Create an instance of {@link JobCollectionType }
     * 
     */
    public JobCollectionType createJobCollectionType() {
        return new JobCollectionType();
    }

    /**
     * Create an instance of {@link CreateResponseType }
     * 
     */
    public CreateResponseType createCreateResponseType() {
        return new CreateResponseType();
    }

    /**
     * Create an instance of {@link ZoneCollectionType }
     * 
     */
    public ZoneCollectionType createZoneCollectionType() {
        return new ZoneCollectionType();
    }

    /**
     * Create an instance of {@link DeleteResponseCollectionType }
     * 
     */
    public DeleteResponseCollectionType createDeleteResponseCollectionType() {
        return new DeleteResponseCollectionType();
    }

    /**
     * Create an instance of {@link AlertCollectionType }
     * 
     */
    public AlertCollectionType createAlertCollectionType() {
        return new AlertCollectionType();
    }

    /**
     * Create an instance of {@link EnvironmentType }
     * 
     */
    public EnvironmentType createEnvironmentType() {
        return new EnvironmentType();
    }

    /**
     * Create an instance of {@link QueueCollectionType }
     * 
     */
    public QueueCollectionType createQueueCollectionType() {
        return new QueueCollectionType();
    }

    /**
     * Create an instance of {@link NamespaceType }
     * 
     */
    public NamespaceType createNamespaceType() {
        return new NamespaceType();
    }

    /**
     * Create an instance of {@link ProvisionRequestType }
     * 
     */
    public ProvisionRequestType createProvisionRequestType() {
        return new ProvisionRequestType();
    }

    /**
     * Create an instance of {@link PodCollectionType }
     * 
     */
    public PodCollectionType createPodCollectionType() {
        return new PodCollectionType();
    }

    /**
     * Create an instance of {@link JobType }
     * 
     */
    public JobType createJobType() {
        return new JobType();
    }

    /**
     * Create an instance of {@link XqueryCollectionType }
     * 
     */
    public XqueryCollectionType createXqueryCollectionType() {
        return new XqueryCollectionType();
    }

    /**
     * Create an instance of {@link QueueType }
     * 
     */
    public QueueType createQueueType() {
        return new QueueType();
    }

    /**
     * Create an instance of {@link ProviderCollectionType }
     * 
     */
    public ProviderCollectionType createProviderCollectionType() {
        return new ProviderCollectionType();
    }

    /**
     * Create an instance of {@link DeleteRequestType }
     * 
     */
    public DeleteRequestType createDeleteRequestType() {
        return new DeleteRequestType();
    }

    /**
     * Create an instance of {@link DeleteRequestCollectionType }
     * 
     */
    public DeleteRequestCollectionType createDeleteRequestCollectionType() {
        return new DeleteRequestCollectionType();
    }

    /**
     * Create an instance of {@link ErrorCollectionType }
     * 
     */
    public ErrorCollectionType createErrorCollectionType() {
        return new ErrorCollectionType();
    }

    /**
     * Create an instance of {@link UpdateResponseType }
     * 
     */
    public UpdateResponseType createUpdateResponseType() {
        return new UpdateResponseType();
    }

    /**
     * Create an instance of {@link NamespaceCollectionType }
     * 
     */
    public NamespaceCollectionType createNamespaceCollectionType() {
        return new NamespaceCollectionType();
    }

    /**
     * Create an instance of {@link FieldListType }
     * 
     */
    public FieldListType createFieldListType() {
        return new FieldListType();
    }

    /**
     * Create an instance of {@link ConditionListType }
     * 
     */
    public ConditionListType createConditionListType() {
        return new ConditionListType();
    }

    /**
     * Create an instance of {@link ContractType }
     * 
     */
    public ContractType createContractType() {
        return new ContractType();
    }

    /**
     * Create an instance of {@link GCorePhoneNumberType }
     * 
     */
    public GCorePhoneNumberType createGCorePhoneNumberType() {
        return new GCorePhoneNumberType();
    }

    /**
     * Create an instance of {@link RightType }
     * 
     */
    public RightType createRightType() {
        return new RightType();
    }

    /**
     * Create an instance of {@link AliasesType }
     * 
     */
    public AliasesType createAliasesType() {
        return new AliasesType();
    }

    /**
     * Create an instance of {@link EndpointType }
     * 
     */
    public EndpointType createEndpointType() {
        return new EndpointType();
    }

    /**
     * Create an instance of {@link RangeType }
     * 
     */
    public RangeType createRangeType() {
        return new RangeType();
    }

    /**
     * Create an instance of {@link DataAccessType }
     * 
     */
    public DataAccessType createDataAccessType() {
        return new DataAccessType();
    }

    /**
     * Create an instance of {@link SelectedContentType }
     * 
     */
    public SelectedContentType createSelectedContentType() {
        return new SelectedContentType();
    }

    /**
     * Create an instance of {@link SecurityTechnologyListType }
     * 
     */
    public SecurityTechnologyListType createSecurityTechnologyListType() {
        return new SecurityTechnologyListType();
    }

    /**
     * Create an instance of {@link DeleteIdType }
     * 
     */
    public DeleteIdType createDeleteIdType() {
        return new DeleteIdType();
    }

    /**
     * Create an instance of {@link ExtendedContentType }
     * 
     */
    public ExtendedContentType createExtendedContentType() {
        return new ExtendedContentType();
    }

    /**
     * Create an instance of {@link LegalRequirementsType }
     * 
     */
    public LegalRequirementsType createLegalRequirementsType() {
        return new LegalRequirementsType();
    }

    /**
     * Create an instance of {@link PropertiesType }
     * 
     */
    public PropertiesType createPropertiesType() {
        return new PropertiesType();
    }

    /**
     * Create an instance of {@link ServicesType }
     * 
     */
    public ServicesType createServicesType() {
        return new ServicesType();
    }

    /**
     * Create an instance of {@link ConditionType }
     * 
     */
    public ConditionType createConditionType() {
        return new ConditionType();
    }

    /**
     * Create an instance of {@link PartyToDeleteDataType }
     * 
     */
    public PartyToDeleteDataType createPartyToDeleteDataType() {
        return new PartyToDeleteDataType();
    }

    /**
     * Create an instance of {@link FieldType }
     * 
     */
    public FieldType createFieldType() {
        return new FieldType();
    }

    /**
     * Create an instance of {@link PhaseCollectionType }
     * 
     */
    public PhaseCollectionType createPhaseCollectionType() {
        return new PhaseCollectionType();
    }

    /**
     * Create an instance of {@link GCoreAddressListType }
     * 
     */
    public GCoreAddressListType createGCoreAddressListType() {
        return new GCoreAddressListType();
    }

    /**
     * Create an instance of {@link DataDeletionType }
     * 
     */
    public DataDeletionType createDataDeletionType() {
        return new DataDeletionType();
    }

    /**
     * Create an instance of {@link QuerySupportType }
     * 
     */
    public QuerySupportType createQuerySupportType() {
        return new QuerySupportType();
    }

    /**
     * Create an instance of {@link InfrastructureServicesType }
     * 
     */
    public InfrastructureServicesType createInfrastructureServicesType() {
        return new InfrastructureServicesType();
    }

    /**
     * Create an instance of {@link ProvisionedZonesType }
     * 
     */
    public ProvisionedZonesType createProvisionedZonesType() {
        return new ProvisionedZonesType();
    }

    /**
     * Create an instance of {@link MediaTypesType }
     * 
     */
    public MediaTypesType createMediaTypesType() {
        return new MediaTypesType();
    }

    /**
     * Create an instance of {@link ZoneContextType }
     * 
     */
    public ZoneContextType createZoneContextType() {
        return new ZoneContextType();
    }

    /**
     * Create an instance of {@link TechnicalRequirementsType }
     * 
     */
    public TechnicalRequirementsType createTechnicalRequirementsType() {
        return new TechnicalRequirementsType();
    }

    /**
     * Create an instance of {@link ZoneContextListType }
     * 
     */
    public ZoneContextListType createZoneContextListType() {
        return new ZoneContextListType();
    }

    /**
     * Create an instance of {@link ClauseListType }
     * 
     */
    public ClauseListType createClauseListType() {
        return new ClauseListType();
    }

    /**
     * Create an instance of {@link ProtocolType }
     * 
     */
    public ProtocolType createProtocolType() {
        return new ProtocolType();
    }

    /**
     * Create an instance of {@link CodeItemType }
     * 
     */
    public CodeItemType createCodeItemType() {
        return new CodeItemType();
    }

    /**
     * Create an instance of {@link EmployeeTrainingListType }
     * 
     */
    public EmployeeTrainingListType createEmployeeTrainingListType() {
        return new EmployeeTrainingListType();
    }

    /**
     * Create an instance of {@link CreatesType }
     * 
     */
    public CreatesType createCreatesType() {
        return new CreatesType();
    }

    /**
     * Create an instance of {@link CodeItemsType }
     * 
     */
    public CodeItemsType createCodeItemsType() {
        return new CodeItemsType();
    }

    /**
     * Create an instance of {@link DeleteStatusType }
     * 
     */
    public DeleteStatusType createDeleteStatusType() {
        return new DeleteStatusType();
    }

    /**
     * Create an instance of {@link ProvisionedZoneType }
     * 
     */
    public ProvisionedZoneType createProvisionedZoneType() {
        return new ProvisionedZoneType();
    }

    /**
     * Create an instance of {@link EnumerationType }
     * 
     */
    public EnumerationType createEnumerationType() {
        return new EnumerationType();
    }

    /**
     * Create an instance of {@link PrivacyListType }
     * 
     */
    public PrivacyListType createPrivacyListType() {
        return new PrivacyListType();
    }

    /**
     * Create an instance of {@link EnumerationsType }
     * 
     */
    public EnumerationsType createEnumerationsType() {
        return new EnumerationsType();
    }

    /**
     * Create an instance of {@link ObligationType }
     * 
     */
    public ObligationType createObligationType() {
        return new ObligationType();
    }

    /**
     * Create an instance of {@link DataProcessorType }
     * 
     */
    public DataProcessorType createDataProcessorType() {
        return new DataProcessorType();
    }

    /**
     * Create an instance of {@link PrivacyType }
     * 
     */
    public PrivacyType createPrivacyType() {
        return new PrivacyType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link SecurityTestRequiredListType }
     * 
     */
    public SecurityTestRequiredListType createSecurityTestRequiredListType() {
        return new SecurityTestRequiredListType();
    }

    /**
     * Create an instance of {@link CodeType }
     * 
     */
    public CodeType createCodeType() {
        return new CodeType();
    }

    /**
     * Create an instance of {@link GCoreNameType }
     * 
     */
    public GCoreNameType createGCoreNameType() {
        return new GCoreNameType();
    }

    /**
     * Create an instance of {@link ClauseType }
     * 
     */
    public ClauseType createClauseType() {
        return new ClauseType();
    }

    /**
     * Create an instance of {@link AppIDListType }
     * 
     */
    public AppIDListType createAppIDListType() {
        return new AppIDListType();
    }

    /**
     * Create an instance of {@link InitializationType }
     * 
     */
    public InitializationType createInitializationType() {
        return new InitializationType();
    }

    /**
     * Create an instance of {@link ProductIdentityType }
     * 
     */
    public ProductIdentityType createProductIdentityType() {
        return new ProductIdentityType();
    }

    /**
     * Create an instance of {@link BenchmarkListType }
     * 
     */
    public BenchmarkListType createBenchmarkListType() {
        return new BenchmarkListType();
    }

    /**
     * Create an instance of {@link EndpointListType }
     * 
     */
    public EndpointListType createEndpointListType() {
        return new EndpointListType();
    }

    /**
     * Create an instance of {@link LawListType }
     * 
     */
    public LawListType createLawListType() {
        return new LawListType();
    }

    /**
     * Create an instance of {@link PurposeListType }
     * 
     */
    public PurposeListType createPurposeListType() {
        return new PurposeListType();
    }

    /**
     * Create an instance of {@link DataSubProcessorType }
     * 
     */
    public DataSubProcessorType createDataSubProcessorType() {
        return new DataSubProcessorType();
    }

    /**
     * Create an instance of {@link DataSubjectType }
     * 
     */
    public DataSubjectType createDataSubjectType() {
        return new DataSubjectType();
    }

    /**
     * Create an instance of {@link LawType }
     * 
     */
    public LawType createLawType() {
        return new LawType();
    }

    /**
     * Create an instance of {@link DataSubProcessorListType }
     * 
     */
    public DataSubProcessorListType createDataSubProcessorListType() {
        return new DataSubProcessorListType();
    }

    /**
     * Create an instance of {@link InfrastructureServiceType }
     * 
     */
    public InfrastructureServiceType createInfrastructureServiceType() {
        return new InfrastructureServiceType();
    }

    /**
     * Create an instance of {@link BenchmarkType }
     * 
     */
    public BenchmarkType createBenchmarkType() {
        return new BenchmarkType();
    }

    /**
     * Create an instance of {@link GCoreAddressType }
     * 
     */
    public GCoreAddressType createGCoreAddressType() {
        return new GCoreAddressType();
    }

    /**
     * Create an instance of {@link DeleteStatusCollectionType }
     * 
     */
    public DeleteStatusCollectionType createDeleteStatusCollectionType() {
        return new DeleteStatusCollectionType();
    }

    /**
     * Create an instance of {@link SecurityTechnologyType }
     * 
     */
    public SecurityTechnologyType createSecurityTechnologyType() {
        return new SecurityTechnologyType();
    }

    /**
     * Create an instance of {@link DeidentifiedPurposeListType }
     * 
     */
    public DeidentifiedPurposeListType createDeidentifiedPurposeListType() {
        return new DeidentifiedPurposeListType();
    }

    /**
     * Create an instance of {@link EmployeeTrainingType }
     * 
     */
    public EmployeeTrainingType createEmployeeTrainingType() {
        return new EmployeeTrainingType();
    }

    /**
     * Create an instance of {@link CreateType }
     * 
     */
    public CreateType createCreateType() {
        return new CreateType();
    }

    /**
     * Create an instance of {@link PasswordEmployeeAccessStandardType }
     * 
     */
    public PasswordEmployeeAccessStandardType createPasswordEmployeeAccessStandardType() {
        return new PasswordEmployeeAccessStandardType();
    }

    /**
     * Create an instance of {@link PhaseType }
     * 
     */
    public PhaseType createPhaseType() {
        return new PhaseType();
    }

    /**
     * Create an instance of {@link GCoreContactInfoType }
     * 
     */
    public GCoreContactInfoType createGCoreContactInfoType() {
        return new GCoreContactInfoType();
    }

    /**
     * Create an instance of {@link PropertyType }
     * 
     */
    public PropertyType createPropertyType() {
        return new PropertyType();
    }

    /**
     * Create an instance of {@link ObligationListType }
     * 
     */
    public ObligationListType createObligationListType() {
        return new ObligationListType();
    }

    /**
     * Create an instance of {@link ParametersType }
     * 
     */
    public ParametersType createParametersType() {
        return new ParametersType();
    }

    /**
     * Create an instance of {@link ApplicationInfoType }
     * 
     */
    public ApplicationInfoType createApplicationInfoType() {
        return new ApplicationInfoType();
    }

    /**
     * Create an instance of {@link CountryImpactedType }
     * 
     */
    public CountryImpactedType createCountryImpactedType() {
        return new CountryImpactedType();
    }

    /**
     * Create an instance of {@link ParameterType }
     * 
     */
    public ParameterType createParameterType() {
        return new ParameterType();
    }

    /**
     * Create an instance of {@link AlternatePurposeListType }
     * 
     */
    public AlternatePurposeListType createAlternatePurposeListType() {
        return new AlternatePurposeListType();
    }

    /**
     * Create an instance of {@link AdapterFingerprintListType }
     * 
     */
    public AdapterFingerprintListType createAdapterFingerprintListType() {
        return new AdapterFingerprintListType();
    }

    /**
     * Create an instance of {@link RightsType }
     * 
     */
    public RightsType createRightsType() {
        return new RightsType();
    }

    /**
     * Create an instance of {@link PropertyNVListType }
     * 
     */
    public PropertyNVListType createPropertyNVListType() {
        return new PropertyNVListType();
    }

    /**
     * Create an instance of {@link PrivacyObligationsDocumentType }
     * 
     */
    public PrivacyObligationsDocumentType createPrivacyObligationsDocumentType() {
        return new PrivacyObligationsDocumentType();
    }

    /**
     * Create an instance of {@link SecurityTestRequiredType }
     * 
     */
    public SecurityTestRequiredType createSecurityTestRequiredType() {
        return new SecurityTestRequiredType();
    }

    /**
     * Create an instance of {@link GCoreEmailListType }
     * 
     */
    public GCoreEmailListType createGCoreEmailListType() {
        return new GCoreEmailListType();
    }

    /**
     * Create an instance of {@link DataControllerType }
     * 
     */
    public DataControllerType createDataControllerType() {
        return new DataControllerType();
    }

    /**
     * Create an instance of {@link CountryImpactedListType }
     * 
     */
    public CountryImpactedListType createCountryImpactedListType() {
        return new CountryImpactedListType();
    }

    /**
     * Create an instance of {@link DeleteIdCollectionType }
     * 
     */
    public DeleteIdCollectionType createDeleteIdCollectionType() {
        return new DeleteIdCollectionType();
    }

    /**
     * Create an instance of {@link PropertyNVType }
     * 
     */
    public PropertyNVType createPropertyNVType() {
        return new PropertyNVType();
    }

    /**
     * Create an instance of {@link GCorePhoneNumberListType }
     * 
     */
    public GCorePhoneNumberListType createGCorePhoneNumberListType() {
        return new GCorePhoneNumberListType();
    }

    /**
     * Create an instance of {@link AliasType }
     * 
     */
    public AliasType createAliasType() {
        return new AliasType();
    }

    /**
     * Create an instance of {@link UpdatesType }
     * 
     */
    public UpdatesType createUpdatesType() {
        return new UpdatesType();
    }

    /**
     * Create an instance of {@link UpdateType }
     * 
     */
    public UpdateType createUpdateType() {
        return new UpdateType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JobType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "job")
    public JAXBElement<JobType> createJob(JobType value) {
        return new JAXBElement<JobType>(_Job_QNAME, JobType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XqueryCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "xquerys")
    public JAXBElement<XqueryCollectionType> createXquerys(XqueryCollectionType value) {
        return new JAXBElement<XqueryCollectionType>(_Xquerys_QNAME, XqueryCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "queue")
    public JAXBElement<QueueType> createQueue(QueueType value) {
        return new JAXBElement<QueueType>(_Queue_QNAME, QueueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "providers")
    public JAXBElement<ProviderCollectionType> createProviders(ProviderCollectionType value) {
        return new JAXBElement<ProviderCollectionType>(_Providers_QNAME, ProviderCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "deleteRequest")
    public JAXBElement<DeleteRequestType> createDeleteRequest(DeleteRequestType value) {
        return new JAXBElement<DeleteRequestType>(_DeleteRequest_QNAME, DeleteRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRequestCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "deleteRequests")
    public JAXBElement<DeleteRequestCollectionType> createDeleteRequests(DeleteRequestCollectionType value) {
        return new JAXBElement<DeleteRequestCollectionType>(_DeleteRequests_QNAME, DeleteRequestCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "errors")
    public JAXBElement<ErrorCollectionType> createErrors(ErrorCollectionType value) {
        return new JAXBElement<ErrorCollectionType>(_Errors_QNAME, ErrorCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "updateResponse")
    public JAXBElement<UpdateResponseType> createUpdateResponse(UpdateResponseType value) {
        return new JAXBElement<UpdateResponseType>(_UpdateResponse_QNAME, UpdateResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamespaceCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "namespaces")
    public JAXBElement<NamespaceCollectionType> createNamespaces(NamespaceCollectionType value) {
        return new JAXBElement<NamespaceCollectionType>(_Namespaces_QNAME, NamespaceCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamespaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "namespace")
    public JAXBElement<NamespaceType> createNamespace(NamespaceType value) {
        return new JAXBElement<NamespaceType>(_Namespace_QNAME, NamespaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvisionRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "provisionRequest")
    public JAXBElement<ProvisionRequestType> createProvisionRequest(ProvisionRequestType value) {
        return new JAXBElement<ProvisionRequestType>(_ProvisionRequest_QNAME, ProvisionRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PodCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "pods")
    public JAXBElement<PodCollectionType> createPods(PodCollectionType value) {
        return new JAXBElement<PodCollectionType>(_Pods_QNAME, PodCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "queues")
    public JAXBElement<QueueCollectionType> createQueues(QueueCollectionType value) {
        return new JAXBElement<QueueCollectionType>(_Queues_QNAME, QueueCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlertCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "alerts")
    public JAXBElement<AlertCollectionType> createAlerts(AlertCollectionType value) {
        return new JAXBElement<AlertCollectionType>(_Alerts_QNAME, AlertCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvironmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "environment")
    public JAXBElement<EnvironmentType> createEnvironment(EnvironmentType value) {
        return new JAXBElement<EnvironmentType>(_Environment_QNAME, EnvironmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZoneCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "zones")
    public JAXBElement<ZoneCollectionType> createZones(ZoneCollectionType value) {
        return new JAXBElement<ZoneCollectionType>(_Zones_QNAME, ZoneCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponseCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "deleteResponses")
    public JAXBElement<DeleteResponseCollectionType> createDeleteResponses(DeleteResponseCollectionType value) {
        return new JAXBElement<DeleteResponseCollectionType>(_DeleteResponses_QNAME, DeleteResponseCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "createResponse")
    public JAXBElement<CreateResponseType> createCreateResponse(CreateResponseType value) {
        return new JAXBElement<CreateResponseType>(_CreateResponse_QNAME, CreateResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XqueryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "xquery")
    public JAXBElement<XqueryType> createXquery(XqueryType value) {
        return new JAXBElement<XqueryType>(_Xquery_QNAME, XqueryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JobCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "jobs")
    public JAXBElement<JobCollectionType> createJobs(JobCollectionType value) {
        return new JAXBElement<JobCollectionType>(_Jobs_QNAME, JobCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponseCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "createResponses")
    public JAXBElement<CreateResponseCollectionType> createCreateResponses(CreateResponseCollectionType value) {
        return new JAXBElement<CreateResponseCollectionType>(_CreateResponses_QNAME, CreateResponseCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvisionRequestCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "provisionRequests")
    public JAXBElement<ProvisionRequestCollectionType> createProvisionRequests(ProvisionRequestCollectionType value) {
        return new JAXBElement<ProvisionRequestCollectionType>(_ProvisionRequests_QNAME, ProvisionRequestCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeSetCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "codeSets")
    public JAXBElement<CodeSetCollectionType> createCodeSets(CodeSetCollectionType value) {
        return new JAXBElement<CodeSetCollectionType>(_CodeSets_QNAME, CodeSetCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "state")
    public JAXBElement<StateType> createState(StateType value) {
        return new JAXBElement<StateType>(_State_QNAME, StateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlertType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "alert")
    public JAXBElement<AlertType> createAlert(AlertType value) {
        return new JAXBElement<AlertType>(_Alert_QNAME, AlertType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "provider")
    public JAXBElement<ProviderType> createProvider(ProviderType value) {
        return new JAXBElement<ProviderType>(_Provider_QNAME, ProviderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZoneType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "zone")
    public JAXBElement<ZoneType> createZone(ZoneType value) {
        return new JAXBElement<ZoneType>(_Zone_QNAME, ZoneType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "deleteResponse")
    public JAXBElement<DeleteResponseType> createDeleteResponse(DeleteResponseType value) {
        return new JAXBElement<DeleteResponseType>(_DeleteResponse_QNAME, DeleteResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeSetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "codeSet")
    public JAXBElement<CodeSetType> createCodeSet(CodeSetType value) {
        return new JAXBElement<CodeSetType>(_CodeSet_QNAME, CodeSetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "error")
    public JAXBElement<ErrorType> createError(ErrorType value) {
        return new JAXBElement<ErrorType>(_Error_QNAME, ErrorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StateCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "states")
    public JAXBElement<StateCollectionType> createStates(StateCollectionType value) {
        return new JAXBElement<StateCollectionType>(_States_QNAME, StateCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponseCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "updateResponses")
    public JAXBElement<UpdateResponseCollectionType> createUpdateResponses(UpdateResponseCollectionType value) {
        return new JAXBElement<UpdateResponseCollectionType>(_UpdateResponses_QNAME, UpdateResponseCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscriptionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "subscription")
    public JAXBElement<SubscriptionType> createSubscription(SubscriptionType value) {
        return new JAXBElement<SubscriptionType>(_Subscription_QNAME, SubscriptionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "pod")
    public JAXBElement<PodType> createPod(PodType value) {
        return new JAXBElement<PodType>(_Pod_QNAME, PodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvironmentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "environments")
    public JAXBElement<EnvironmentCollectionType> createEnvironments(EnvironmentCollectionType value) {
        return new JAXBElement<EnvironmentCollectionType>(_Environments_QNAME, EnvironmentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscriptionCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.3", name = "subscriptions")
    public JAXBElement<SubscriptionCollectionType> createSubscriptions(SubscriptionCollectionType value) {
        return new JAXBElement<SubscriptionCollectionType>(_Subscriptions_QNAME, SubscriptionCollectionType.class, null, value);
    }

}
