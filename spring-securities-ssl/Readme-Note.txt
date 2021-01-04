securing lab 30 using ssl
Ref: https://howtodoinjava.com/spring-boot/spring-boot-ssl-https-example/
and
ref: https://www.thomasvitale.com/https-spring-boot-ssl-certificate/

steps 1: get the ssl certificate e.g below self-signed certificate
    keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -storepass password



