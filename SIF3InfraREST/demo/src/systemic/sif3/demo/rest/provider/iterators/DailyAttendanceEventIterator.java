/*
 * DailyAttendanceEventIterator.java
 * Created: 27/05/2014
 *
 * Copyright 2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package systemic.sif3.demo.rest.provider.iterators;

import java.util.Calendar;
import java.util.Random;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import sif.dd.au30.model.AUCodeSetsDayValueCodeType;
import sif.dd.au30.model.AttendanceCodeType;
import sif.dd.au30.model.ObjectFactory;
import sif.dd.au30.model.StudentDailyAttendanceCollectionType;
import sif.dd.au30.model.StudentDailyAttendanceType;
import sif3.common.header.HeaderValues.EventAction;
import sif3.common.interfaces.SIFEventIterator;
import sif3.common.model.SIFEvent;
import sif3.common.utils.UUIDGenerator;
import au.com.systemic.framework.utils.AdvancedProperties;

/**
 * @author Joerg Huber
 *
 */
public class DailyAttendanceEventIterator implements SIFEventIterator<StudentDailyAttendanceCollectionType>
{
	private ObjectFactory objFactor = new ObjectFactory();
	private Random randomGenerator = new Random();
	private AttendanceCodeType present = null;
	private String absentCodes[] = {"101", "111", "201", "204", "500", "604", "801", "607", "904", "999"};

	private int totalRecords = 0;
	private int recPerSchool = 0;
	private int currentRecord = 0;
	private boolean isAM = true;
	private String studentRefID = null;
	private String schoolRefID = null;
//	private String providerID = null;
//	private AdvancedProperties serviceProperties = null;
	private XMLGregorianCalendar today = null;
	private XMLGregorianCalendar currentYear = null;
	

	public DailyAttendanceEventIterator(String providerID, AdvancedProperties serviceProperties, int numSchools, int numStudents)
	{
//		this.serviceProperties = serviceProperties;
//		this.providerID = providerID;
		this.totalRecords = numSchools * numStudents * 2; // One AM and one PM record
		this.recPerSchool = numStudents * 2;
		this.currentRecord = 0;
		setToday();
		present = new AttendanceCodeType();
		present.setCode("100");
	}
	
	/* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#getNextEvents(int)
     */
    @Override
    public SIFEvent<StudentDailyAttendanceCollectionType> getNextEvents(int maxListSize)
    {
		SIFEvent<StudentDailyAttendanceCollectionType> events = null;
    	if (hasNext())
    	{
			events = new SIFEvent<StudentDailyAttendanceCollectionType>(new StudentDailyAttendanceCollectionType(), EventAction.CREATE, null, 0);
			while ((events.getListSize() < maxListSize) && hasNext())
			{
				events.getSIFObjectList().getStudentDailyAttendance().add(getAttendanceRecord());
				events.setListSize(events.getListSize()+1);
				currentRecord++;
			}
    	}
	    return events;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#hasNext()
     */
    @Override
    public boolean hasNext()
    {
	    return currentRecord < totalRecords;
    }

	/* (non-Javadoc)
     * @see sif3.common.interfaces.SIFEventIterator#releaseResources()
     */
    @Override
    public void releaseResources()
    {
    }

    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    private StudentDailyAttendanceType getAttendanceRecord()
    {
		StudentDailyAttendanceType attRec = new StudentDailyAttendanceType();
		attRec.setRefId(UUIDGenerator.getSIF2GUIDUpperCase());
		if ((currentRecord % recPerSchool) == 0)
		{
			schoolRefID = UUIDGenerator.getSIF2GUIDUpperCase();
		}
		if (isAM)
		{
			studentRefID = UUIDGenerator.getSIF2GUIDUpperCase();
		}
		attRec.setSchoolInfoRefId(schoolRefID);
		attRec.setStudentPersonalRefId(studentRefID);
		
		if (isAM)
		{
			attRec.setDayValue(objFactor.createStudentDailyAttendanceTypeDayValue(AUCodeSetsDayValueCodeType.AM));
		}
		else
		{
			attRec.setDayValue(objFactor.createStudentDailyAttendanceTypeDayValue(AUCodeSetsDayValueCodeType.PM));
		}
		if (today != null)
		{
			attRec.setDate(today);
		}
		if (currentYear != null)
		{
			attRec.setSchoolYear(currentYear);
		}
		
		// Every 100 student or so may get an absent record
		if ((randomGenerator.nextInt(100) % 100) == 0)
		{
			AttendanceCodeType absent = new AttendanceCodeType();
			absent.setCode(absentCodes[randomGenerator.nextInt(10)]);
			attRec.setAttendanceCode(absent);
		}
		else
		{
			attRec.setAttendanceCode(present);
		}
		
		isAM = !isAM;
		
		return attRec;	
    }
    
    private void setToday()
    {
    	try
    	{
	    	Calendar now = Calendar.getInstance();
	    	currentYear = DatatypeFactory.newInstance().newXMLGregorianCalendar();
	    	currentYear.setYear(now.get(Calendar.YEAR));
	    	
	    	today = DatatypeFactory.newInstance().newXMLGregorianCalendar();
	    	today.setDay(now.get(Calendar.DAY_OF_MONTH));
	    	today.setMonth(now.get(Calendar.MONTH)+1);
	    	today.setYear(now.get(Calendar.YEAR));
    	}
    	catch (Exception ex)
    	{
    		System.out.println("Error genereating dates: "+ex.getMessage());
    		today = null;
    		currentYear = null;
    	}
    }
    
}
