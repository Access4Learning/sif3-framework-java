
package sif3.infra.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sif3.common.model.ServiceRights.AccessRight;
import sif3.common.model.ServiceRights.AccessType;
import sif3.infra.common.model.ObjectFactory;
import sif3.infra.common.model.RightType;
import sif3.infra.common.model.RightValueType;
import sif3.infra.common.model.RightsType;

public class SIFRights {

	private Map<AccessRight, AccessType> rights = new HashMap<AccessRight, AccessType>();
	private ObjectFactory objectFactory = new ObjectFactory();

	public SIFRights() {
		for (AccessRight right : AccessRight.values()) {
			rights.put(right, AccessType.REJECTED);
		}
	}

	public List<RightType> getRights() {
		List<RightType> list = new ArrayList<RightType>();
		for (AccessRight right : rights.keySet()) {
			RightType r = objectFactory.createRightType();
			r.setType(right.name());
			r.setValue(RightValueType.valueOf(rights.get(right).name()));
			list.add(r);
		}
		return list;
	}

	public SIFRights setRights(RightsType element) {
		if(element == null) {
			return this;
		}
		return setRights(element.getRight());
	}
	
	public SIFRights setRights(List<RightType> list) {
		if (list != null) {
			for (RightType r : list) {
				rights.put(AccessRight.valueOf(r.getType()), AccessType.valueOf(r.getValue().name()));
			}
		}
		return this;
	}
	
	public AccessType getRight(AccessRight right) {
		return rights.get(right);
	}
	
	public boolean hasRight(AccessRight right, AccessType type) {
		return getRight(right).equals(type);
	}

	public SIFRights admin() {
		this.admin(AccessType.APPROVED);
		return this;
	}

	public SIFRights admin(AccessType type) {
		rights.put(AccessRight.ADMIN, type);
		return this;
	}

	public SIFRights create() {
		this.create(AccessType.APPROVED);
		return this;
	}

	public SIFRights create(AccessType type) {
		rights.put(AccessRight.CREATE, type);
		return this;
	}

	public SIFRights delete() {
		this.delete(AccessType.APPROVED);
		return this;
	}

	public SIFRights delete(AccessType type) {
		rights.put(AccessRight.DELETE, type);
		return this;
	}

	public SIFRights provide() {
		this.provide(AccessType.APPROVED);
		return this;
	}

	public SIFRights provide(AccessType type) {
		rights.put(AccessRight.PROVIDE, type);
		return this;
	}

	public SIFRights query() {
		this.query(AccessType.APPROVED);
		return this;
	}

	public SIFRights query(AccessType type) {
		rights.put(AccessRight.QUERY, type);
		return this;
	}

	public SIFRights subscribe() {
		this.subscribe(AccessType.APPROVED);
		return this;
	}

	public SIFRights subscribe(AccessType type) {
		rights.put(AccessRight.SUBSCRIBE, type);
		return this;
	}

	public SIFRights update() {
		this.update(AccessType.APPROVED);
		return this;
	}

	public SIFRights update(AccessType type) {
		rights.put(AccessRight.UPDATE, type);
		return this;
	}
}
