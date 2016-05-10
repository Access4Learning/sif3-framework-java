package zinet.sif3.demo.uk.rest.provider;

import sif3.common.conversion.ModelObjectInfo;
import sif3.infra.common.model.JobType;
import sif3.infra.common.utils.ServiceUtils;
import sif3.infra.rest.provider.BaseJobProvider;
import sif3.infra.common.utils.SIFRights;
import zinet.sif3.demo.uk.rest.PayloadConstants;
import zinet.sif3.demo.uk.rest.provider.actions.DefaultActions;
import zinet.sif3.demo.uk.rest.provider.actions.JsonActions;
import zinet.sif3.demo.uk.rest.provider.actions.XmlActions;

public class PayloadProvider extends BaseJobProvider {
	public PayloadProvider() {
		super("Payload");
		
		phaseActions.put("default", new DefaultActions());
    phaseActions.put("xml", new XmlActions());
    phaseActions.put("json", new JsonActions());
	}
	
	@Override
	protected void addPhases(JobType job) {
		ServiceUtils.addPhase(job, "default", true, new SIFRights().create().query().update().getRights());
		ServiceUtils.addPhase(job, "xml", true, new SIFRights().create().query().update().getRights());
		ServiceUtils.addPhase(job, "json", true, new SIFRights().create().query().update().getRights());
	}
	
	@Override
	protected void shutdownJob(JobType job) {
	}
	
	/*
	 * (non-Javadoc) @see sif3.infra.rest.provider.BaseProvider#shutdown()
	 */
	public void shutdown() {
	}
	
	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getSingleObjectClassInfo()
	 */
	@Override
	public ModelObjectInfo getSingleObjectClassInfo() {
		return PayloadConstants.PAYLOAD;
	}

	/*
	 * (non-Javadoc)
	 * @see sif3.common.interfaces.DataModelLink#getMultiObjectClassInfo()
	 */
	@Override
	public ModelObjectInfo getMultiObjectClassInfo() {
		return PayloadConstants.PAYLOADS;
	}
}
