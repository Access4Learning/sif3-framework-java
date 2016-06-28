package sif3.infra.rest.functional;

import javax.ws.rs.core.MediaType;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;
import sif3.infra.common.model.JobType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.PhaseType;
import sif3.infra.rest.provider.BaseFunctionalServiceProvider;


public class BasePhaseActions implements IPhaseActions {
	protected ObjectFactory objectFactory = new ObjectFactory();
	private BaseFunctionalServiceProvider provider;
	
	public BasePhaseActions(BaseFunctionalServiceProvider provider) {
		this.provider = provider;
	}
	
	@Override
	public String create(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException {
		throw new UnsupportedQueryException("CREATE is not supported");
	}

	@Override
	public String retrieve(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException, UnsupportedMediaTypeException, UnsupportedQueryException {
		throw new UnsupportedQueryException("RETRIEVE is not supported");
	}

	@Override
	public String update(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException {
		throw new UnsupportedQueryException("UPDATE is not supported");
	}

	@Override
	public String delete(JobType job, PhaseType phase, String payload, MediaType requestMediaType, MediaType responseMediaType, SIFZone zone, SIFContext context) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException, UnsupportedQueryException {
		throw new UnsupportedQueryException("DELETE is not supported");
	}

	
	/**
	 * @return the provider
	 */
	public BaseFunctionalServiceProvider getProvider() {
		return provider;
	}
}
