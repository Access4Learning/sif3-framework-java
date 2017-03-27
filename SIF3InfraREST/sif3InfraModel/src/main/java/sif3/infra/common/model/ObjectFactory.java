
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

    private final static QName _Environments_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "environments");
    private final static QName _State_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "state");
    private final static QName _Job_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "job");
    private final static QName _States_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "states");
    private final static QName _CodeSet_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "codeSet");
    private final static QName _Provider_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "provider");
    private final static QName _Zones_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "zones");
    private final static QName _Namespaces_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "namespaces");
    private final static QName _CodeSets_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "codeSets");
    private final static QName _Namespace_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "namespace");
    private final static QName _DeleteRequest_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "deleteRequest");
    private final static QName _Xquery_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "xquery");
    private final static QName _Queue_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "queue");
    private final static QName _Jobs_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "jobs");
    private final static QName _Xquerys_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "xquerys");
    private final static QName _Error_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "error");
    private final static QName _Subscriptions_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "subscriptions");
    private final static QName _ProvisionRequest_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "provisionRequest");
    private final static QName _Alert_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "alert");
    private final static QName _CreateResponse_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "createResponse");
    private final static QName _Queues_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "queues");
    private final static QName _Zone_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "zone");
    private final static QName _DeleteResponse_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "deleteResponse");
    private final static QName _Environment_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "environment");
    private final static QName _Subscription_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "subscription");
    private final static QName _Providers_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "providers");
    private final static QName _UpdateResponse_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "updateResponse");
    private final static QName _Alerts_QNAME = new QName("http://www.sifassociation.org/infrastructure/3.2.1", "alerts");
    private final static QName _AliasTypeSource_QNAME = new QName("", "source");
    private final static QName _AliasTypeNamespace_QNAME = new QName("", "namespace");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sif3.infra.common.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ParameterType }
     * 
     */
    public ParameterType createParameterType() {
        return new ParameterType();
    }

    /**
     * Create an instance of {@link XqueryType }
     * 
     */
    public XqueryType createXqueryType() {
        return new XqueryType();
    }

    /**
     * Create an instance of {@link DeleteRequestType }
     * 
     */
    public DeleteRequestType createDeleteRequestType() {
        return new DeleteRequestType();
    }

    /**
     * Create an instance of {@link QueueType }
     * 
     */
    public QueueType createQueueType() {
        return new QueueType();
    }

    /**
     * Create an instance of {@link JobCollectionType }
     * 
     */
    public JobCollectionType createJobCollectionType() {
        return new JobCollectionType();
    }

    /**
     * Create an instance of {@link ProvisionRequestType }
     * 
     */
    public ProvisionRequestType createProvisionRequestType() {
        return new ProvisionRequestType();
    }

    /**
     * Create an instance of {@link StateType }
     * 
     */
    public StateType createStateType() {
        return new StateType();
    }

    /**
     * Create an instance of {@link StateCollectionType }
     * 
     */
    public StateCollectionType createStateCollectionType() {
        return new StateCollectionType();
    }

    /**
     * Create an instance of {@link CodeSetType }
     * 
     */
    public CodeSetType createCodeSetType() {
        return new CodeSetType();
    }

    /**
     * Create an instance of {@link NamespaceType }
     * 
     */
    public NamespaceType createNamespaceType() {
        return new NamespaceType();
    }

    /**
     * Create an instance of {@link SubscriptionType }
     * 
     */
    public SubscriptionType createSubscriptionType() {
        return new SubscriptionType();
    }

    /**
     * Create an instance of {@link ProviderCollectionType }
     * 
     */
    public ProviderCollectionType createProviderCollectionType() {
        return new ProviderCollectionType();
    }

    /**
     * Create an instance of {@link AlertCollectionType }
     * 
     */
    public AlertCollectionType createAlertCollectionType() {
        return new AlertCollectionType();
    }

    /**
     * Create an instance of {@link UpdateResponseType }
     * 
     */
    public UpdateResponseType createUpdateResponseType() {
        return new UpdateResponseType();
    }

    /**
     * Create an instance of {@link CreateResponseType }
     * 
     */
    public CreateResponseType createCreateResponseType() {
        return new CreateResponseType();
    }

    /**
     * Create an instance of {@link XqueryCollectionType }
     * 
     */
    public XqueryCollectionType createXqueryCollectionType() {
        return new XqueryCollectionType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link SubscriptionCollectionType }
     * 
     */
    public SubscriptionCollectionType createSubscriptionCollectionType() {
        return new SubscriptionCollectionType();
    }

    /**
     * Create an instance of {@link AlertType }
     * 
     */
    public AlertType createAlertType() {
        return new AlertType();
    }

    /**
     * Create an instance of {@link EnvironmentCollectionType }
     * 
     */
    public EnvironmentCollectionType createEnvironmentCollectionType() {
        return new EnvironmentCollectionType();
    }

    /**
     * Create an instance of {@link JobType }
     * 
     */
    public JobType createJobType() {
        return new JobType();
    }

    /**
     * Create an instance of {@link ProviderType }
     * 
     */
    public ProviderType createProviderType() {
        return new ProviderType();
    }

    /**
     * Create an instance of {@link ZoneCollectionType }
     * 
     */
    public ZoneCollectionType createZoneCollectionType() {
        return new ZoneCollectionType();
    }

    /**
     * Create an instance of {@link CodeSetCollectionType }
     * 
     */
    public CodeSetCollectionType createCodeSetCollectionType() {
        return new CodeSetCollectionType();
    }

    /**
     * Create an instance of {@link NamespaceCollectionType }
     * 
     */
    public NamespaceCollectionType createNamespaceCollectionType() {
        return new NamespaceCollectionType();
    }

    /**
     * Create an instance of {@link DeleteResponseType }
     * 
     */
    public DeleteResponseType createDeleteResponseType() {
        return new DeleteResponseType();
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
     * Create an instance of {@link ZoneType }
     * 
     */
    public ZoneType createZoneType() {
        return new ZoneType();
    }

    /**
     * Create an instance of {@link PropertiesType }
     * 
     */
    public PropertiesType createPropertiesType() {
        return new PropertiesType();
    }

    /**
     * Create an instance of {@link ProtocolType }
     * 
     */
    public ProtocolType createProtocolType() {
        return new ProtocolType();
    }

    /**
     * Create an instance of {@link AliasType }
     * 
     */
    public AliasType createAliasType() {
        return new AliasType();
    }

    /**
     * Create an instance of {@link RightType }
     * 
     */
    public RightType createRightType() {
        return new RightType();
    }

    /**
     * Create an instance of {@link CreateType }
     * 
     */
    public CreateType createCreateType() {
        return new CreateType();
    }

    /**
     * Create an instance of {@link QuerySupportType }
     * 
     */
    public QuerySupportType createQuerySupportType() {
        return new QuerySupportType();
    }

    /**
     * Create an instance of {@link ProductIdentityType }
     * 
     */
    public ProductIdentityType createProductIdentityType() {
        return new ProductIdentityType();
    }

    /**
     * Create an instance of {@link InitializationType }
     * 
     */
    public InitializationType createInitializationType() {
        return new InitializationType();
    }

    /**
     * Create an instance of {@link AliasesType }
     * 
     */
    public AliasesType createAliasesType() {
        return new AliasesType();
    }

    /**
     * Create an instance of {@link CodeItemsType }
     * 
     */
    public CodeItemsType createCodeItemsType() {
        return new CodeItemsType();
    }

    /**
     * Create an instance of {@link CodeItemType }
     * 
     */
    public CodeItemType createCodeItemType() {
        return new CodeItemType();
    }

    /**
     * Create an instance of {@link ServicesType }
     * 
     */
    public ServicesType createServicesType() {
        return new ServicesType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link DeleteStatus }
     * 
     */
    public DeleteStatus createDeleteStatus() {
        return new DeleteStatus();
    }

    /**
     * Create an instance of {@link XpathType }
     * 
     */
    public XpathType createXpathType() {
        return new XpathType();
    }

    /**
     * Create an instance of {@link ApplicationInfoType }
     * 
     */
    public ApplicationInfoType createApplicationInfoType() {
        return new ApplicationInfoType();
    }

    /**
     * Create an instance of {@link EnumerationType }
     * 
     */
    public EnumerationType createEnumerationType() {
        return new EnumerationType();
    }

    /**
     * Create an instance of {@link DeleteIdCollection }
     * 
     */
    public DeleteIdCollection createDeleteIdCollection() {
        return new DeleteIdCollection();
    }

    /**
     * Create an instance of {@link RightsType }
     * 
     */
    public RightsType createRightsType() {
        return new RightsType();
    }

    /**
     * Create an instance of {@link RangeType }
     * 
     */
    public RangeType createRangeType() {
        return new RangeType();
    }

    /**
     * Create an instance of {@link InfrastructureServicesType }
     * 
     */
    public InfrastructureServicesType createInfrastructureServicesType() {
        return new InfrastructureServicesType();
    }

    /**
     * Create an instance of {@link UpdateType }
     * 
     */
    public UpdateType createUpdateType() {
        return new UpdateType();
    }

    /**
     * Create an instance of {@link NamespaceQualifierType }
     * 
     */
    public NamespaceQualifierType createNamespaceQualifierType() {
        return new NamespaceQualifierType();
    }

    /**
     * Create an instance of {@link MediaTypesType }
     * 
     */
    public MediaTypesType createMediaTypesType() {
        return new MediaTypesType();
    }

    /**
     * Create an instance of {@link IncludeType }
     * 
     */
    public IncludeType createIncludeType() {
        return new IncludeType();
    }

    /**
     * Create an instance of {@link DeleteStatusCollection }
     * 
     */
    public DeleteStatusCollection createDeleteStatusCollection() {
        return new DeleteStatusCollection();
    }

    /**
     * Create an instance of {@link InfrastructureServiceType }
     * 
     */
    public InfrastructureServiceType createInfrastructureServiceType() {
        return new InfrastructureServiceType();
    }

    /**
     * Create an instance of {@link PhaseType }
     * 
     */
    public PhaseType createPhaseType() {
        return new PhaseType();
    }

    /**
     * Create an instance of {@link ProvisionedZonesType }
     * 
     */
    public ProvisionedZonesType createProvisionedZonesType() {
        return new ProvisionedZonesType();
    }

    /**
     * Create an instance of {@link DeleteIdType }
     * 
     */
    public DeleteIdType createDeleteIdType() {
        return new DeleteIdType();
    }

    /**
     * Create an instance of {@link CodeType }
     * 
     */
    public CodeType createCodeType() {
        return new CodeType();
    }

    /**
     * Create an instance of {@link UpdatesType }
     * 
     */
    public UpdatesType createUpdatesType() {
        return new UpdatesType();
    }

    /**
     * Create an instance of {@link CreatesType }
     * 
     */
    public CreatesType createCreatesType() {
        return new CreatesType();
    }

    /**
     * Create an instance of {@link PropertyType }
     * 
     */
    public PropertyType createPropertyType() {
        return new PropertyType();
    }

    /**
     * Create an instance of {@link NamespaceQualifiersType }
     * 
     */
    public NamespaceQualifiersType createNamespaceQualifiersType() {
        return new NamespaceQualifiersType();
    }

    /**
     * Create an instance of {@link PhaseCollectionType }
     * 
     */
    public PhaseCollectionType createPhaseCollectionType() {
        return new PhaseCollectionType();
    }

    /**
     * Create an instance of {@link ProvisionedZoneType }
     * 
     */
    public ProvisionedZoneType createProvisionedZoneType() {
        return new ProvisionedZoneType();
    }

    /**
     * Create an instance of {@link ParameterType.Enumerations }
     * 
     */
    public ParameterType.Enumerations createParameterTypeEnumerations() {
        return new ParameterType.Enumerations();
    }

    /**
     * Create an instance of {@link XqueryType.Parameters }
     * 
     */
    public XqueryType.Parameters createXqueryTypeParameters() {
        return new XqueryType.Parameters();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvironmentCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "environments")
    public JAXBElement<EnvironmentCollectionType> createEnvironments(EnvironmentCollectionType value) {
        return new JAXBElement<EnvironmentCollectionType>(_Environments_QNAME, EnvironmentCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "state")
    public JAXBElement<StateType> createState(StateType value) {
        return new JAXBElement<StateType>(_State_QNAME, StateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JobType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "job")
    public JAXBElement<JobType> createJob(JobType value) {
        return new JAXBElement<JobType>(_Job_QNAME, JobType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StateCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "states")
    public JAXBElement<StateCollectionType> createStates(StateCollectionType value) {
        return new JAXBElement<StateCollectionType>(_States_QNAME, StateCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeSetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "codeSet")
    public JAXBElement<CodeSetType> createCodeSet(CodeSetType value) {
        return new JAXBElement<CodeSetType>(_CodeSet_QNAME, CodeSetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "provider")
    public JAXBElement<ProviderType> createProvider(ProviderType value) {
        return new JAXBElement<ProviderType>(_Provider_QNAME, ProviderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZoneCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "zones")
    public JAXBElement<ZoneCollectionType> createZones(ZoneCollectionType value) {
        return new JAXBElement<ZoneCollectionType>(_Zones_QNAME, ZoneCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamespaceCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "namespaces")
    public JAXBElement<NamespaceCollectionType> createNamespaces(NamespaceCollectionType value) {
        return new JAXBElement<NamespaceCollectionType>(_Namespaces_QNAME, NamespaceCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeSetCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "codeSets")
    public JAXBElement<CodeSetCollectionType> createCodeSets(CodeSetCollectionType value) {
        return new JAXBElement<CodeSetCollectionType>(_CodeSets_QNAME, CodeSetCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamespaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "namespace")
    public JAXBElement<NamespaceType> createNamespace(NamespaceType value) {
        return new JAXBElement<NamespaceType>(_Namespace_QNAME, NamespaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "deleteRequest")
    public JAXBElement<DeleteRequestType> createDeleteRequest(DeleteRequestType value) {
        return new JAXBElement<DeleteRequestType>(_DeleteRequest_QNAME, DeleteRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XqueryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "xquery")
    public JAXBElement<XqueryType> createXquery(XqueryType value) {
        return new JAXBElement<XqueryType>(_Xquery_QNAME, XqueryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "queue")
    public JAXBElement<QueueType> createQueue(QueueType value) {
        return new JAXBElement<QueueType>(_Queue_QNAME, QueueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JobCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "jobs")
    public JAXBElement<JobCollectionType> createJobs(JobCollectionType value) {
        return new JAXBElement<JobCollectionType>(_Jobs_QNAME, JobCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XqueryCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "xquerys")
    public JAXBElement<XqueryCollectionType> createXquerys(XqueryCollectionType value) {
        return new JAXBElement<XqueryCollectionType>(_Xquerys_QNAME, XqueryCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "error")
    public JAXBElement<ErrorType> createError(ErrorType value) {
        return new JAXBElement<ErrorType>(_Error_QNAME, ErrorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscriptionCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "subscriptions")
    public JAXBElement<SubscriptionCollectionType> createSubscriptions(SubscriptionCollectionType value) {
        return new JAXBElement<SubscriptionCollectionType>(_Subscriptions_QNAME, SubscriptionCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvisionRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "provisionRequest")
    public JAXBElement<ProvisionRequestType> createProvisionRequest(ProvisionRequestType value) {
        return new JAXBElement<ProvisionRequestType>(_ProvisionRequest_QNAME, ProvisionRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlertType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "alert")
    public JAXBElement<AlertType> createAlert(AlertType value) {
        return new JAXBElement<AlertType>(_Alert_QNAME, AlertType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "createResponse")
    public JAXBElement<CreateResponseType> createCreateResponse(CreateResponseType value) {
        return new JAXBElement<CreateResponseType>(_CreateResponse_QNAME, CreateResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "queues")
    public JAXBElement<QueueCollectionType> createQueues(QueueCollectionType value) {
        return new JAXBElement<QueueCollectionType>(_Queues_QNAME, QueueCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZoneType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "zone")
    public JAXBElement<ZoneType> createZone(ZoneType value) {
        return new JAXBElement<ZoneType>(_Zone_QNAME, ZoneType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "deleteResponse")
    public JAXBElement<DeleteResponseType> createDeleteResponse(DeleteResponseType value) {
        return new JAXBElement<DeleteResponseType>(_DeleteResponse_QNAME, DeleteResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvironmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "environment")
    public JAXBElement<EnvironmentType> createEnvironment(EnvironmentType value) {
        return new JAXBElement<EnvironmentType>(_Environment_QNAME, EnvironmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubscriptionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "subscription")
    public JAXBElement<SubscriptionType> createSubscription(SubscriptionType value) {
        return new JAXBElement<SubscriptionType>(_Subscription_QNAME, SubscriptionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "providers")
    public JAXBElement<ProviderCollectionType> createProviders(ProviderCollectionType value) {
        return new JAXBElement<ProviderCollectionType>(_Providers_QNAME, ProviderCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "updateResponse")
    public JAXBElement<UpdateResponseType> createUpdateResponse(UpdateResponseType value) {
        return new JAXBElement<UpdateResponseType>(_UpdateResponse_QNAME, UpdateResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlertCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.sifassociation.org/infrastructure/3.2.1", name = "alerts")
    public JAXBElement<AlertCollectionType> createAlerts(AlertCollectionType value) {
        return new JAXBElement<AlertCollectionType>(_Alerts_QNAME, AlertCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "source", scope = AliasType.class)
    public JAXBElement<String> createAliasTypeSource(String value) {
        return new JAXBElement<String>(_AliasTypeSource_QNAME, String.class, AliasType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "namespace", scope = AliasType.class)
    public JAXBElement<String> createAliasTypeNamespace(String value) {
        return new JAXBElement<String>(_AliasTypeNamespace_QNAME, String.class, AliasType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "source", scope = CodeItemType.class)
    public JAXBElement<String> createCodeItemTypeSource(String value) {
        return new JAXBElement<String>(_AliasTypeSource_QNAME, String.class, CodeItemType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "namespace", scope = CodeItemType.class)
    public JAXBElement<String> createCodeItemTypeNamespace(String value) {
        return new JAXBElement<String>(_AliasTypeNamespace_QNAME, String.class, CodeItemType.class, value);
    }

}
