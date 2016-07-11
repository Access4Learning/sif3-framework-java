package sif3.common.persist.model;

import java.io.Serializable;

public class SIF3ObjectBinding implements Serializable
{
    private static final long serialVersionUID = -5791623824653436683L;
    private String            objectId;
    private String            ownerId;

    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    public void setOwnerId(String sessionToken)
    {
        this.ownerId = sessionToken;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public String getObjectId()
    {
        return objectId;
    }
}
