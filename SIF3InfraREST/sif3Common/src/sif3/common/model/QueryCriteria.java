package sif3.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryCriteria implements Serializable {

  private static final long serialVersionUID = 1803812315289373807L;

  private QueryJoinType joinType;
  private List<QueryPredicate> predicates = new ArrayList<QueryPredicate>();
  
  public QueryCriteria() {
    joinType = QueryJoinType.AND;
  }
  
  public QueryCriteria(QueryJoinType joinType) {
    this.joinType = joinType;
  }

  public QueryJoinType getJoinType() {
    return joinType;
  }

  public void setJoinType(QueryJoinType joinType) {
    this.joinType = joinType;
  }

  public List<QueryPredicate> getPredicates() {
    return predicates;
  }

  public void setPredicates(List<QueryPredicate> predicates) {
    this.predicates = predicates;
  }
  
  public void addPredicate(QueryPredicate predicate) {
    this.predicates.add(predicate);
  }
}
