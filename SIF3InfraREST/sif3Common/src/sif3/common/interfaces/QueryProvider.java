package sif3.common.interfaces;

import sif3.common.exception.PersistenceException;
import sif3.common.exception.UnsupportedQueryException;
import sif3.common.model.PagingInfo;
import sif3.common.model.QueryCriteria;
import sif3.common.model.RequestMetadata;
import sif3.common.model.SIFContext;
import sif3.common.model.SIFZone;

/**
 * This interface is an extension to the Provider interface to also allow object queries.
 */
public interface QueryProvider extends Provider {
  
  /**
   * This method is used to retrieve any number of objects. Filtered by the service path.
   * Each predicate within the criteria holds a parent object and it's key.
   * @param queryCriteria
   * @param zone
   * @param context
   * @param pagingInfo
   * @param metadata
   * @return
   * @throws PersistenceException
   * @throws UnsupportedQueryException
   */
  public Object retrieveByServicePath(QueryCriteria queryCriteria, SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException;
  
  /**
   * This method is used to retrieve any number of objects. Filtered by the dynamic query parameter.
   * Each predicate within the criteria holds a property of the object an operator and a value.
   * @param queryCriteria
   * @param zone
   * @param context
   * @param pagingInfo
   * @param metadata
   * @return
   * @throws PersistenceException
   * @throws UnsupportedQueryException
   */
  public Object retrieveByDynamicQuery(QueryCriteria queryCriteria, SIFZone zone, SIFContext context, PagingInfo pagingInfo, RequestMetadata metadata) throws PersistenceException, UnsupportedQueryException;
}
