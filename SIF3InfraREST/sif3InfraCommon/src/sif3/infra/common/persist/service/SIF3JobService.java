
package sif3.infra.common.persist.service;

import java.util.List;

import sif3.common.exception.PersistenceException;
import sif3.common.persist.common.BasicTransaction;
import sif3.common.persist.dao.BaseDAO;
import sif3.common.persist.service.DBService;
import sif3.infra.common.model.JobType;
import sif3.infra.common.persist.dao.SIF3JobDAO;

public class SIF3JobService extends DBService {

	private SIF3JobDAO sif3JobDAO = new SIF3JobDAO();

	@Override
	public BaseDAO getDAO() {
		return sif3JobDAO;
	}

	public List<JobType> getJobs() throws IllegalArgumentException, PersistenceException {
		List<JobType> jobs = null;
		BasicTransaction tx = null;

		try {
			tx = startTransaction();
			jobs = sif3JobDAO.getJobs(tx);
			tx.commit();
		} catch (Exception ex) {
			rollback(tx);
			exceptionMapper(ex, "Failed to retrieve jobs.", true, false);
		}
		return jobs;
	}
	
	public JobType getJobById(String id) throws IllegalArgumentException, PersistenceException {
		JobType job = null;
		BasicTransaction tx = null;

		try {
			tx = startTransaction();
			job = sif3JobDAO.getJobByRefId(tx, id);
			tx.commit();
		} catch (Exception ex) {
			rollback(tx);
			exceptionMapper(ex, "Failed to retrieve job for ID = '" + id + "'.", true, false);
		}
		return job;
	}

	public JobType save(JobType job) throws IllegalArgumentException, PersistenceException {
		BasicTransaction tx = null;
		try {
			tx = startTransaction();
			String id = sif3JobDAO.save(tx, job);
			tx.commit();
			return getJobById(id);
		} catch (Exception ex) {
			rollback(tx);
			exceptionMapper(ex, "Failed to save Job with ID='" + job.getId() + "'.", true, false);
			return null;
		}
	}

	public void removeJobById(String id) throws IllegalArgumentException, PersistenceException {
		BasicTransaction tx = null;
		try {
			tx = startTransaction();
			sif3JobDAO.removeById(tx, id);
			tx.commit();
		} catch (Exception ex) {
			rollback(tx);
			exceptionMapper(ex, "Failed to remove Job for ID = '" + id + "'.", true, true);
		}
	}
}
