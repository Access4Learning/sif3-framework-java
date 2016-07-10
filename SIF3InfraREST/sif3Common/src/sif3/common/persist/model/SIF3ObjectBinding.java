package sif3.common.persist.model;

import java.io.Serializable;

public class SIF3ObjectBinding implements Serializable
{
    private static final long serialVersionUID = -5791623824653436683L;
    private String            objectId;
    private String            sessionToken;
    // FIXME JN Extend this class with queueID (fix hmb.xml, extend the DAO, etc.) if that is the
    // appropriate update for direct eventing.

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    public void setSessionToken(String sessionToken)
    {
        this.sessionToken = sessionToken;
    }

    public String getSessionToken()
    {
        return sessionToken;
    }

    public String getObjectId()
    {
        return objectId;
    }
}
