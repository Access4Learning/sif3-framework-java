package sif3.common.exception;

public class ServiceInvokationException extends SIF3Exception
{
  private static final long serialVersionUID = -7309084634964371L;

  /**
   * @see java.lang.Exception#Exception()
   */
  public ServiceInvokationException()
  {
      super();
  }

  /**
   * @see java.lang.Exception#Exception(String)
   */
  public ServiceInvokationException(String message)
  {
      super(message);
  }

  /**
   * @see java.lang.Exception#Exception(String, Throwable)
   */
  public ServiceInvokationException(String message, Throwable cause)
  {
      super(message, cause);
  }

  /**
   * @see java.lang.Exception#Exception(Throwable)
   */
  public ServiceInvokationException(Throwable cause)
  {
      super(cause);
  }

}
