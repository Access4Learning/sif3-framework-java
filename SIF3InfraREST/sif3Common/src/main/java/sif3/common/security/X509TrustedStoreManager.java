/*
 * X509TrustedStoreManager.java Created: 09/12/2013
 * 
 * Copyright 2013 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package sif3.common.security;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is an utility class to load the Trusted Keystore from a specific location. The code is inspired from the following
 * site: http://java.sun.com/javase/6/docs/technotes/guides/security/jsse/JSSERefGuide.html
 * 
 * @author Joerg Huber
 * 
 */
public class X509TrustedStoreManager implements X509TrustManager 
{
  private final Logger logger = LoggerFactory.getLogger(getClass());

  /*
   * The default PKIX X509TrustManager.  We'll delegate decisions to it, and fall back to the logic in this 
   * class if the default X509TrustManager doesn't trust it.
   */
  private X509TrustManager pkixTrustManager = null;

  public X509TrustedStoreManager(String trustStore, char[] password) throws Exception 
  {
      this(new File(trustStore), password);
  }

  public X509TrustedStoreManager(File trustStore, char[] password) throws Exception 
  {
      // create a "default" JSSE X509TrustManager.
      KeyStore keystore = KeyStore.getInstance("JKS");
      keystore.load(new FileInputStream(trustStore), password);

      TrustManagerFactory factory = TrustManagerFactory.getInstance("PKIX");
      factory.init(keystore);

      TrustManager managers[] = factory.getTrustManagers();

       //Iterate over the returned trustmanagers, look for an instance of X509TrustManager. If found,
       //use that as our "default" trust manager.
      for (int i = 0; i < managers.length; i++) 
      {
          if (managers[i] instanceof X509TrustManager) 
          {
              pkixTrustManager = (X509TrustManager) managers[i];
              return;
          }
      }

      //find some other way to initialize, or else we have to fail the constructor.
      logger.error("Failed fo initialise find the trusted store managers.");
      throw new Exception("Failed fo initialise find the trusted store managers.");
  }

  /*
   * Delegate to the default trust manager.
   */
  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException 
  {
      try 
      {
          pkixTrustManager.checkClientTrusted(chain, authType);
      }
      catch (CertificateException ex) 
      {
          logger.error("Problem with Trusted Store and Client certififcate check: "+ex.getMessage(), ex);
          
          //re-throw the exception to stop client if there is a certificate issue.
          throw ex;
      }
  }

  /*
   * Delegate to the default trust manager.
   */
  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException 
  {
      try 
      {
          pkixTrustManager.checkServerTrusted(chain, authType);
      } 
      catch (CertificateException ex) 
      {
          logger.error("Problem with Trusted Store and Sever certififcate check: "+ex.getMessage(), ex);
          
          //re-throw the exception to stop server if there is a certificate issue.
          throw ex;
      }
  }

  /*
   * Merely pass this through.
   */
  public X509Certificate[] getAcceptedIssuers() 
  {
      return pkixTrustManager.getAcceptedIssuers();
  }
}
