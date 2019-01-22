package systemic.sif3.demo.rest.consumer.namedquery;

import sif3.common.model.PagingInfo;
import sif3.common.model.StringPayload;
import sif3.common.model.delayed.DelayedResponseReceipt;
import sif3.common.ws.ErrorDetails;
import sif3.infra.rest.consumer.AbstractNamedQueryConsumer;

public class StudentsInYearConsumer extends AbstractNamedQueryConsumer
{
    public StudentsInYearConsumer()
    {
        super();
    }
    
    @Override
    public String getNamedQueryName()
    {
        return "StudentsInYear"; // Name of named query as it appears in the environment XML.
    }

    @Override
    public void processDelayedNamedQuery(StringPayload responseData, PagingInfo pagingInfo, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED NAMED QUERY Response:\nPagingInfo:\n"+pagingInfo+"\nDelayed Receipt Details:\n"+receipt+"\nResponse Data:\n"+responseData);
    }

    @Override
    public void processDelayedError(ErrorDetails error, DelayedResponseReceipt receipt)
    {
        logger.debug("Received DELAYED ERROR Response:\n"+error+"\nDelayed Receipt Details:\n"+receipt);
    }

    @Override
    public void shutdown()
    {
        logger.info("Shutting down Named Query Service Consumer '"+getNamedQueryName()+"'");
    }

}
