package com.paresh.practice.tools;
import java.io.InputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SSLPoke {
    public SSLPoke() {
    }

    public static void main(String[] var0) {
        if (var0.length != 2) {
            System.err.println("Utility to debug Java connections to SSL servers");
            System.err.println("Usage: ");
            System.err.println("  java " + SSLPoke.class.getName() + " <host> <port>");
            System.err.println("or for more debugging:");
            System.err.println("  java -Djavax.net.debug=ssl " + SSLPoke.class.getName() + " <host> <port>");
            System.err.println();
            System.err.println("Eg. to test the SSL certificate at https://localhost, use");
            System.err.println("  java " + SSLPoke.class.getName() + " localhost 443");
            System.exit(1);
        }

        try {
            SSLSocketFactory var1 = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket var2 = (SSLSocket) var1.createSocket(var0[0], Integer.parseInt(var0[1]));
            InputStream var3 = var2.getInputStream();
            OutputStream var4 = var2.getOutputStream();
            var4.write(1);

            while (var3.available() > 0) {
                System.out.print(var3.read());
            }

            System.out.println("Successfully connected");
            System.exit(0);
        } catch (SSLHandshakeException var5) {
            if (var5.getCause() != null) {
                var5.getCause().printStackTrace();
            } else {
                var5.printStackTrace();
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        System.exit(1);
    }
}
