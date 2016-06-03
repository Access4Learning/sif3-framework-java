
package sif3.infra.common.persist.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.exception.MarshalException;
import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.dao.BaseDAO;
import sif3.infra.common.model.JobType;
import sif3.infra.common.persist.model.RefIdObject;

public class SIF3JobDAO extends BaseDAO {

	protected final Logger logger = Logger.getLogger(getClass());

	@SuppressWarnings("unchecked")
	public List<JobType> getJobs(BasicTransaction tx) throws IllegalArgumentException, PersistenceException, UnmarshalException, UnsupportedMediaTypeException {

		try {
			List<RefIdObject> objs = tx.getSession().createCriteria(RefIdObject.class).list();
			List<JobType> jobs = new ArrayList<JobType>();
			for (RefIdObject obj : objs) {
				jobs.add(unmarshal(obj));
			}
			return jobs;
		} catch (HibernateException e) {
			throw new PersistenceException("Unable to retrieve SIF3 jobs.", e);
		}
	}

	@SuppressWarnings("unchecked")
	private RefIdObject getObjectByRefId(BasicTransaction tx, String refId) {
		if (StringUtils.isEmpty(refId)) {
			throw new IllegalArgumentException("ID is empty or null.");
		}

		Criteria criteria = tx.getSession().createCriteria(RefIdObject.class).add(Restrictions.eq("id", refId));
		List<RefIdObject> jobs = criteria.list();

		// There can only be a maximum of one
		if (jobs.isEmpty()) {
			// not in cache
			logger.debug("No job for id = '" + refId + "' exists.");
			return null;
		} else {
			// exists
			return jobs.get(0);
		}
	}

	public JobType getJobByRefId(BasicTransaction tx, String refId) throws IllegalArgumentException, PersistenceException {
		try {
			RefIdObject obj = getObjectByRefId(tx, refId);
			if (obj == null) {
				return null;
			} else {
				return unmarshal(obj);
			}
		} catch (HibernateException e) {
			throw new PersistenceException("Unable to retrieve SIF3 job for ID = '" + refId + "'.", e);
		} catch (UnmarshalException e) {
			throw new IllegalArgumentException("Unable to retrieve SIF3 job for ID = '" + refId + "'.", e);
		} catch (UnsupportedMediaTypeException e) {
			throw new IllegalArgumentException("Unable to retrieve SIF3 job for ID = '" + refId + "'.", e);
		}
	}

	public String save(BasicTransaction tx, JobType job) throws IllegalArgumentException, PersistenceException {
		if (job == null) {
			throw new IllegalArgumentException("Job is null.");
		}

		if(!job.isSetCreated()) {
			job.setCreated(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
			job.setLastModified(job.getCreated());
		}
		
		try {
			if (StringUtils.isEmpty(job.getId())) {
				logger.info("Attempting to save Job without an advisory ID");
				RefIdObject obj = new RefIdObject();
				job.setId((String) tx.getSession().save(obj));
				obj.setId(job.getId());
				obj.setObject(job);
				// logger.debug("XML to save:\n" + obj.getXml());
				tx.getSession().saveOrUpdate(obj.getId(), obj);
				return job.getId();
			} else {
				logger.info("Attempting to save Job using the advisory ID " + job.getId());
				RefIdObject obj = marshal(job);
				// logger.debug("XML to save:\n" + obj.getXml());
				tx.getSession().saveOrUpdate(obj.getId(), obj);
				return obj.getId();
			}
		} catch (HibernateException e) {
			throw new PersistenceException("Unable to save job object with ID='" + job.getId() + "'.", e);
		} catch (MarshalException e) {
			throw new IllegalArgumentException("Unable to retrieve SIF3 job for ID = '" + job.getId() + "'.", e);
		} catch (UnsupportedMediaTypeException e) {
			throw new IllegalArgumentException("Unable to retrieve SIF3 job for ID = '" + job.getId() + "'.", e);
		}
	}

	public void removeById(BasicTransaction tx, String id) throws PersistenceException {
		try {
			RefIdObject obj = getObjectByRefId(tx, id);
			if (obj != null) {
				tx.getSession().delete(id, obj);
			}
		} catch (HibernateException e) {
			throw new PersistenceException("Failed to delete Job with ID='" + id + "'.", e);
		}
	}

	private RefIdObject marshal(JobType job) throws MarshalException, UnsupportedMediaTypeException {
		RefIdObject obj = new RefIdObject(job.getId());
		obj.setObject(job);
		return obj;
	}

	private JobType unmarshal(RefIdObject obj) throws UnmarshalException, UnsupportedMediaTypeException {
		return (JobType) obj.getObject(JobType.class);
	}
}
