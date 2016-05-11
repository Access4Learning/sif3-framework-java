package sif3.infra.rest.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sif3.common.model.SIFEvent;
import sif3.infra.common.model.JobCollectionType;


public class JobEvents {
	 private List<JobEvent> sifevents = Collections.synchronizedList(new ArrayList<JobEvent>());
	 
	 public JobEvent get(SIFEvent<JobCollectionType> sifevent) {
		 for(JobEvent event : sifevents) {
			 if(event.getEvent().equals(sifevent)) {
				 return event;
			 }
		 }
		 return null;
	 }
	 
	 public List<SIFEvent<JobCollectionType>> getAllEvents() {
		 List<SIFEvent<JobCollectionType>> events = new ArrayList<SIFEvent<JobCollectionType>>();
		 for(JobEvent event : sifevents) {
			 events.add(event.getEvent());
		 }
		 return events;
	 }
	 
	 public boolean add(JobEvent event) {
		 return sifevents.add(event);
	 }
	 
	 public boolean remove(JobEvent event) {
		 return sifevents.remove(event);
	 }
	 
	 public boolean isEmpty() {
		 return sifevents.isEmpty();
	 }
}
