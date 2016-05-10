package zinet.sif3.demo.uk.rest;

import sif.dd.uk20.model.LearnerPersonalCollectionType;
import sif.dd.uk20.model.LearnerPersonalType;
import sif3.common.conversion.ModelObjectInfo;

public class ModelObjectConstants {

	public static final String UTF_8 = "UTF-8";

	public static final ModelObjectInfo LEARNER_PERSONALS = new ModelObjectInfo("LearnerPersonals", LearnerPersonalCollectionType.class);
	public static final ModelObjectInfo LEARNER_PERSONAL = new ModelObjectInfo("LearnerPersonal", LearnerPersonalType.class);
}
