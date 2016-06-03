package sif3.infra.common;

public class ServiceStatus
{
	public enum JobState {NOTSTARTED, INPROGRESS, COMPLETED, FAILED};

	public enum PhaseState {NOTAPPLICABLE, NOTSTARTED, PENDING, SKIPPED, INPROGRESS, COMPLETED, FAILED};
}
