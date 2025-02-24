<H1>To Check SSL connectivity:</H1>
1. Download the SSLPoke.jar file and copy to machine from where you want to run test.
2. get certificate from server.
3. Import certificate to keystore.
4. Run below command to check SSL connectivity.
```java
java -Djavax.net.ssl.trustStore=truststore.jks -Djavax.net.ssl.trustStorePassword=changeit -Djavax.net.ssl.trustStoreType=JKS -jar SSLPoke.jar <hostname> <port>
```

<H1>KeyTool Help:</H1>
```java
keytool -list -keystore bil-my.jceks -storetype jceks -storepass my

keytool -v -list -keystore truststore.jks

keytool -delete -alias my-blf-server -keystore truststore.jks  
keytool -delete -alias my-blf-server -keystore truststore.jks -storepass secret

keytool -importcert -file my-blf-server.crt -alias my-blf-server -keystore truststore.jks -storepass secret -keypass secret -noprompt

java -Djavax.net.ssl.trustStore=truststore.jks SSLPoke my-blf-server 8443
```


