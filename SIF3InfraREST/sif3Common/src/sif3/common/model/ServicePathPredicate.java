package sif3.common.model;


public class ServicePathPredicate extends QueryPredicate {

  private static final long serialVersionUID = -5647502092983681930L;

  public ServicePathPredicate(String subject, String value) {
    super(subject, QueryOperator.EQUAL, value);
  }

}
