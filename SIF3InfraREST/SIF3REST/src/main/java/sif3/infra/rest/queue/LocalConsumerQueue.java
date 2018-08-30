/*
 * LocalConsumerQueue.java
 * Created: 06/05/2014
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
package sif3.infra.rest.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.systemic.framework.utils.StringUtils;
import sif3.common.CommonConstants;
import sif3.infra.rest.queue.types.QueueMessage;


/**
 * This class encapsulates a standard BlockingQueue from the java.util.concurrent package. It only gives
 * access to the blocking put() and take() method. This is the desired behaviour for consumer with concurrent
 * connections to a SIF queue. It follows the standard multi-threaded producer-consumer design pattern. A further
 * advantage of encapsulating the lower level blocking queue is that further functionality can be
 * provided to this LocalConsumerQueue queue such a 'persistence', notification etc. without the need of
 * changes in the classes that use this LocalConsumerQueue class.<p>
 * 
 * <b>Note:</b><br />
 * At this point in time the persistence functionality is not yet implemented. This might be a future 
 * feature. This means if the system should go down, the messages currently held in the queue are lost. 
 * One needs to carefully analyse what capacity of the subscriber queue shall be as this is the maximum 
 * number of lost messages in case of a system failure.<p>
 * 
 * @author Joerg Huber
 *
 */
public class LocalConsumerQueue
{	
    protected final Logger logger = LoggerFactory.getLogger(getClass());

	private BlockingQueue<QueueMessage> queue = null;
	
	/* Properties used in future development once persistence will be implemented. */
    private String localQueueID;
    
    private boolean shutdownFlag = false;
    
	@SuppressWarnings("unused")
    private String workingDir;
	
	/**
	 * This initialises the Local Queue for use in multi-threaded environment.<p>
	 * 
	 * @param capacity The max capacity of elements that can be held by this queue. Generally that should be
	 *                 a low number but theoretically can be any number.
	 * @param localQueueID A unique name representing this queue. This ID should not contain any white spaces. In
	 *                fact all white spaces will be removed from this value. Once persistence is implemented
	 *                this name will be used as part of the persistence store identification.
	 * @param workingDir This is a directory that might be used for temporary or permanent storage once persistence
	 *                   is implemented. This should point to a valid directory that will be created if it
	 *                   doesn't exist.
	 */
	public  LocalConsumerQueue(int capacity, String localQueueID, String workingDir)
	{
		this.localQueueID = StringUtils.isEmpty(localQueueID) ? "LocalConsumerQueue" : localQueueID.replaceAll("\\s+","");
		this.workingDir = StringUtils.isEmpty(workingDir) ? "" : workingDir.replaceAll("\\s+","");;
		this.queue = new ArrayBlockingQueue<QueueMessage>(capacity);
	}
	
	public String getLocalQueueID()
    {
    	return this.localQueueID;
    }

	public void setLocalQueueID(String localQueueID)
    {
    	this.localQueueID = localQueueID;
    }

	/**
	 * This method attempts to put an message from a SIF Message Queue on to the LocalConsumerQueue. If the capacity of 
	 * the queue is below the threshold defined in the constructor then the messageResponse is put on the queue 
	 * immediately. If the queue is full this method blocks indefinitely until a 'slot' becomes available 
	 * (ie. the size of the queue falls below the capacity defined in the constructor). This means a 
	 * consumer has taken a element off the queue.<p>
	 * 
	 * @param message The element to be put on the queue.
	 */
	public void blockingPush(QueueMessage message)
	{
		try
		{
			queue.put(message);
		}
		catch (Exception ex)
		{
			logger.error("Failed to push the 'message' on to the "+getConsumerName()+": "+ex.getMessage(),ex);
		}
	}
	
	/**
	 * This method returns the next available message from the local queue. If a message is available this method
	 * returns immediately with the message. If no message is available then this method will block until
	 * a message is available (blockingPush() has been called by some thread).
	 * 
	 * @return A message of the defined type.
	 */
	public QueueMessage blockingPull()
	{
		try
		{
			return blockingRead();
		}
		catch (Exception ex)
		{
			logger.error("Failed to pull a message off the "+getConsumerName()+": "+ex.getMessage(),ex);
			return null;
		}
	}
	
	public void shutdown()
	{
	    logger.debug("Shutdown message received for "+getConsumerName());
	    shutdownFlag = true;
	}
	
	/* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "LocalConsumerQueue [localQueueID=" + localQueueID + "]";
    }
    
    /*---------------------*/
    /*-- Private Methods --*/
    /*---------------------*/
    
    /*
     * This method will read from the queue for a max of CommonConstants.MAX_SLEEP_MILLISEC before it will check if a shutdown
     * was requested. If it is it will return null otherwise it will continue to read until a proper message is available form the
     * queue. If a message is available it will be returned.
     * This method helps in the proper shutdown of consumer and local queues during the shutdown process. Without that approach
     * this class which runs in its own thread never shuts down properly.
     */
    private QueueMessage blockingRead()
    {
        while (!shutdownFlag)
        {
            try
            {
                QueueMessage message = queue.poll(CommonConstants.MAX_SLEEP_MILLISEC, TimeUnit.MILLISECONDS);
                
                if (message != null) // we have received a message!
                {
                    return message;
                }
                
                if (!shutdownFlag)
                {
                    logger.debug("No message received for "+getConsumerName()+". Go back to read again ...");
                }
            }
            catch (Exception ex)
            {
                logger.error("Failed to pull a message off the "+getConsumerName()+": "+ex.getMessage(),ex);
                return null;
            }  
        }
        
        // If we get here then the shutdown has been called!
        logger.debug("Stop reading from "+getConsumerName()+". Shutdown Flag = "+shutdownFlag);
        return null;
    }
    
    private String getConsumerName()
    {
        return "LocalConsumerQueue: "+getLocalQueueID() + "("+Thread.currentThread().getId()+")";
    }
}
