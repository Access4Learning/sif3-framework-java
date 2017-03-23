/*
 * X509KeystoreManager.java Created: 09/12/2013
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
import java.net.Socket;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is an utility class to load the Keystore from a specific location. The code is inspired from the following
 * site: http://java.sun.com/javase/6/docs/technotes/guides/security/jsse/JSSERefGuide.html
 * 
 * @author Joerg Huber
 * 
 */
public class X509KeystoreManager implements X509KeyManager 
{
  private final Logger logger = LoggerFactory.getLogger(getClass());
  
  //The default PKIX X509KeyManager.  We'll delegate decisions to it, and fall back to the logic in this class if 
  //the default X509KeyManager doesn't trust it.
  private X509KeyManager pkixKeyManager;

  public X509KeystoreManager(String keyStore, char[] password) throws Exception 
  {
      this(new File(keyStore), password);
  }

  public X509KeystoreManager(File keyStore, char[] password) throws Exception 
  {
      // create a "default" JSSE X509KeyManager
      KeyStore keystore = KeyStore.getInstance("JKS");
      keystore.load(new FileInputStream(keyStore), password);

      KeyManagerFactory factory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
      factory.init(keystore, password);

      KeyManager managers[] = factory.getKeyManagers();

      //Iterate over the returned keymanagers, look for an instance of X509KeyManager.  If found,
      //use that as our "default" key manager.
      for (int i = 0; i < managers.length; i++) 
      {
          if (managers[i] instanceof X509KeyManager) 
          {
              pkixKeyManager = (X509KeyManager) managers[i];
              return;
          }
      }

      //find some other way to initialize, or else we have to fail the constructor.
      logger.error("Failed fo initialise find the standard keystore managers.");
      throw new Exception("Failed fo initialise find the standard keystore managers.");
  }

  public PrivateKey getPrivateKey(String alias) 
  {
      return pkixKeyManager.getPrivateKey(alias);
  }

  public X509Certificate[] getCertificateChain(String alias) 
  {
      return pkixKeyManager.getCertificateChain(alias);
  }

  public String[] getClientAliases(String keyType, Principal[] issuers) 
  {
      return pkixKeyManager.getClientAliases(keyType, issuers);
  }

  public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) 
  {
      return pkixKeyManager.chooseClientAlias(keyType, issuers, socket);
  }

  public String[] getServerAliases(String keyType, Principal[] issuers) 
  {
      return pkixKeyManager.getServerAliases(keyType, issuers);
  }

  public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) 
  {
      return pkixKeyManager.chooseServerAlias(keyType, issuers, socket);
  }
}
