package sif3.infra.common.persist.model;

import java.beans.Transient;

import sif3.common.exception.MarshalException;
import sif3.common.exception.UnmarshalException;
import sif3.common.exception.UnsupportedMediaTypeException;
import sif3.infra.common.conversion.InfraMarshalFactory;
import sif3.infra.common.conversion.InfraUnmarshalFactory;

public class RefIdObject {
	private InfraUnmarshalFactory unmarshaller = new InfraUnmarshalFactory();
	private InfraMarshalFactory marshaller = new InfraMarshalFactory();
	private String id = null;
	private String xml = null;
	
	public RefIdObject() {
	}
	
	public RefIdObject(String id) {
		this.id = id;
	}
	
	public RefIdObject(String id, String xml) {
		this.id = id;
		this.xml = xml;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getXml() {
		return xml;
	}
	
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	@Transient
	public void setObject(Object obj) throws MarshalException, UnsupportedMediaTypeException {
		xml = marshaller.marshalToXML(obj);
	}
	
	@Transient
	public Object getObject(Class<?> clazz) throws UnmarshalException, UnsupportedMediaTypeException {
		return unmarshaller.unmarshalFromXML(xml, clazz);
	}
}
