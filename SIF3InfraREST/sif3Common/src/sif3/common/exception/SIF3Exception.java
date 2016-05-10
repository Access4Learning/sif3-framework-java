package sif3.common.exception;

public class SIF3Exception extends Exception
{
  private static final long serialVersionUID = 923365345457L;

  public SIF3Exception()
  {
      super();
  }

  public SIF3Exception(String msg)
  {
      super(msg);
  }

  public SIF3Exception(String msg, Throwable ex)
  {
      super(msg, ex);
  }

  public SIF3Exception(Throwable ex)
  {
      super(ex);
  }
}
