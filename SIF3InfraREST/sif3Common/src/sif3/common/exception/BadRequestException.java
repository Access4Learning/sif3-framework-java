package sif3.common.exception;

import javax.ws.rs.core.Response.Status;

public class BadRequestException extends SIF3Exception
{
  private static final long serialVersionUID = 807165545454L;
  
	public BadRequestException()
	{
		super();
	}

	public BadRequestException(String msg)
	{
		super(msg);
	}

	public BadRequestException(String msg, Throwable ex)
	{
		super(msg, ex);
	}

	public BadRequestException(Throwable ex)
	{
		super(ex);
	}
	
	@Override
	public Status getStatus() {
		return Status.BAD_REQUEST;
	}
}
