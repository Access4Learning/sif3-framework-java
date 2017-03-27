/*
 * X509TrustedStoreManagerNoCheck.java 
 * Created: 11/01/2016
 * 
 * Copyright 2016 Systemic Pty Ltd
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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a "dummy" trusted store implementation that accepts all certificates. There are situations during development
 * and maybe during testing where self-signed certificates are used. In these cases it can be cumbersome to deal with these
 * self-signed certificates. This framework allows this Trusted Store Manager to be used where all certificates are accepted.
 * To enable this store manager to be used the adapter.noCertificateCheck property must be set to true in the adapter's
 * property file. 
 * 
 * @author Joerg Huber
 * 
 */
public class X509TrustedStoreManagerNoCheck implements X509TrustManager 
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public X509TrustedStoreManagerNoCheck() throws Exception
    {
        logger.warn("This Trusted Store Manager does not chack certificates. To enable certificate check, please set adapter.noCertificateCheck=false in this adapter's property file.");
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
    {}

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
    {}

    public X509Certificate[] getAcceptedIssuers()
    {
        return new X509Certificate[0];
    }
}
