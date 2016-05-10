package zinet.sif3.demo.uk.rest;

import sif3.common.conversion.ModelObjectInfo;
import sif3.infra.common.model.JobCollectionType;
import sif3.infra.common.model.JobType;

public class PayloadConstants {

	public static final String UTF_8 = "UTF-8";

	public static final ModelObjectInfo PAYLOADS = new ModelObjectInfo("Payloads", JobCollectionType.class);
	public static final ModelObjectInfo PAYLOAD = new ModelObjectInfo("Payload", JobType.class);
}
