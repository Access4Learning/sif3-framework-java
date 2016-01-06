/*
 * ClientConfigMgr.java Created: 05/12/2013
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

package sif3.infra.rest.client;

import au.com.systemic.framework.utils.AdvancedProperties;
import au.com.systemic.framework.utils.PropertyManager;
import au.com.systemic.framework.utils.Timer;

//TODO: JH - Here we do have some jersey specific classes => Investigate how to replace them with generic classes!
//Note: These are "client" classes only and the only jar that might be required could be jersey-client-1.17.1.jar which
//would not conflict with any other JAX-RS implementation.
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import org.apache.log4j.Logger;

import sif3.common.CommonConstants;
import sif3.common.security.X509KeystoreManager;
import sif3.common.security.X509TrustedStoreManager;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.GeneralSecurityException;


/**
 * There are many places where client contexts are required for REST service invocation. The client context can either be a secure 
 * connection (HTTPS/SSL) or unsecured (HTTP) and in future may even include client certificate exchanges etc. This class is a helper 
 * class to manage the different client contexts, so that they are dealt consistently in the same way. When it comes to any secured 
 * style of connections then it is assumed that the required information about the  connection is stored in the environment.properties
 * file. Typical information stored in there are details about location and credentials for the keystore, trusted store etc.
 * 
 * @author Joerg Huber
 * 
 */
public class ClientConfigMgr
{
  private final Logger logger = Logger.getLogger(getClass());

	private static AdvancedProperties props = null;
	private static String keystoreName = null;
	private static String keystorePWD = null;
	private static String truststoreName = null;
	private static String truststorePWD = null;
	
	private static SSLContext sslCtx = null;

	public ClientConfigMgr()
	{
		if (props == null)
		{
			PropertyManager propMgr = PropertyManager.getInstance();
			propMgr.loadPropertyFile(CommonConstants.ENV_PROP_FILE_NAME);
			if (propMgr.isLoaded(CommonConstants.ENV_PROP_FILE_NAME))
			{
				props = propMgr.getProperties(CommonConstants.ENV_PROP_FILE_NAME);
				
				// Get the values
				keystoreName = props.getPropertyAsString("key.store", null);
				keystorePWD = props.getPropertyAsString("key.store.password", null);
				truststoreName = props.getPropertyAsString("trust.store", null);
				truststorePWD = props.getPropertyAsString("trust.store.password", null);
				
				logger.debug("Loaded keystore name, trusted store name and passwords for keystores.");
				logger.debug("Keystore Location to use: "+keystoreName);
				logger.debug("Trusted Store Location to use: "+truststoreName);
			}
			else
			{
				logger.error("Property file "+CommonConstants.ENV_PROP_FILE_NAME+".properies could not be loaded. Ensure it is on the classpath.");
				props = null;
			}
		}
	}
	
	/**
	 * This method returns a client configuration that can be used by the client interface to invoke a REST webservice.
	 * 
	 * @param secureConnection TRUE: A secure (HTTPS) client config is returned. FALSE: A unsecure (HTTP) client config is returned.
	 * 
	 * @return See desc.
	 */
	public ClientConfig getClientConfig(Boolean secureConnection)
	{
		return (secureConnection) ? getHTTPSClientConfig() : getHTTPClientConfig(); 
	}

	/**
	 * This method will return an unsecured HTTP client configuration.
	 * 
	 * @return See desc.
	 */
	public ClientConfig getHTTPClientConfig()
	{
		logger.debug("Use HTTP");
		return new DefaultClientConfig();
	}

  /**
   * This method will return an secured HTTPS client configuration using SSL.
   * 
   * @return See desc.
   */
	public ClientConfig getHTTPSClientConfig()
	{
		Timer timer = null;
		if (logger.isDebugEnabled())
		{
			timer = new Timer();
			timer.start();		
			logger.debug("Use HTTPS");
		}
		try
		{
			DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
			defaultClientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
			
			return defaultClientConfig;
		}
		catch (Exception ex)
		{
			logger.error("Cannot create configuration for HTTPS (SSL) connection.", ex);
			return null;
		}
		finally
		{
			if (logger.isDebugEnabled())
			{
				timer.finish();
				logger.debug("Time taken retrieve SSLContext: "+timer.timeTaken()+"ms");				
			}
		}
	}
	
	/*---------------------*/
	/*-- Private methods --*/
	/*---------------------*/
	private HostnameVerifier getHostnameVerifier() 
	{
		return new HostnameVerifier() 
		{
			@Override
			public boolean verify(String hostname, SSLSession sslSession) 
			{
				return true;
			}
		};
	}
	
	/*
	 * This method is 'synchronised' to ensure that it is thread safe during SSLContext creation. Once the SSLContext is created it is
	 * stored as a singleton and can be accessed very efficiently.
	 */
	private synchronized SSLContext getSSLContext()
	{
		if (sslCtx == null)
		{
			TrustManager truststoreMgr[] = null;
			KeyManager keystoreMgr[] = null;

			try
			{
				if (truststoreName != null)
				{
					truststoreMgr = new TrustManager[]{new X509TrustedStoreManager(truststoreName, truststorePWD != null ? truststorePWD.toCharArray() : null)};
				}
				else
				{
					// create a X509TrustManager that trusts every certificate
					logger.warn("No Trust Store configured, server certificates will NOT be verified");
					truststoreMgr = new TrustManager[]{new X509TrustManager()
					{
						@Override
						public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException
						{

						}

						@Override
						public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException
						{

						}

						@Override
						public X509Certificate[] getAcceptedIssuers()
						{
							return new X509Certificate[0];
						}
					}};
				}
				if (keystoreName != null)
				{
					keystoreMgr = new KeyManager[]{new X509KeystoreManager(keystoreName, keystorePWD != null ? keystorePWD.toCharArray() : null)};
				}
			}
			catch (Exception ex)
			{
				logger.error("Failed to create Trust Store or Key Store Manager: " + ex.getMessage(), ex);
				return null;
			}

			try
			{
				sslCtx = SSLContext.getInstance("SSL");
				sslCtx.init(keystoreMgr, truststoreMgr, null);
			}
			catch (GeneralSecurityException ex)
			{
				logger.error("Failed to create SSL Context: " + ex.getMessage(), ex);
				return null;
			}
		}
		return sslCtx;
	}	
}
