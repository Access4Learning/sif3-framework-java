package sif3.common.exception;

import javax.ws.rs.core.Response.Status;

import sif3.common.ws.ErrorDetails;

public class SIF3Exception extends Exception {

	private static final long serialVersionUID = 924365325457L;

	public SIF3Exception() {
		super();
	}

	public SIF3Exception(String msg) {
		super(msg);
	}

	public SIF3Exception(String msg, Throwable ex) {
		super(msg, ex);
	}

	public SIF3Exception(Throwable ex) {
		super(ex);
	}
	
	/**
	 * Returns a Status object that should be used when reporting this exception. Should be overridden.
	 * @return The Status object appropriate for this exception.
	 */
	public Status getStatus() {
		return Status.INTERNAL_SERVER_ERROR;
	}

	/**
	 * Converts this exception into an ErrorDetails instance ready to be used in a REST Response
	 * @return ErrorDetails object with default details.
	 */
	public ErrorDetails getErrorDetails() {
		return new ErrorDetails(getStatus().getStatusCode(), "Error performing action.", getMessage());
	}

	public ErrorDetails getErrorDetails(String message) {
		return new ErrorDetails(getStatus().getStatusCode(), message, getMessage());
	}

	public ErrorDetails getErrorDetails(String message, String description) {
		return new ErrorDetails(getStatus().getStatusCode(), message, description);
	}

	public ErrorDetails getErrorDetails(String message, String description, String scope) {
		return new ErrorDetails(getStatus().getStatusCode(), message, description, scope);
	}
}
