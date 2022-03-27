CXF generated client + server with password interceptor implementations
===

The solution consists of 2 Dynamic Web Projects:
- Client - contains CXF generated client using Server's provided WDSL
- Server - a Server implementation which includes a `PasswordCallback`, requiring valid credentials passed within request's SOAP's header.

## CXF Client

- Auto-generated client contains an altered invocation test in main
    - it first asks for freelancers capable of "knitting"
    - then takes first of their IDs and requests the freelancer's info

### How to run

1. Run server
    1. The current implementation of server needs to be altered as it expects credentials in SOAP header.

        This can be fixed by temporarily removing the `<jaxws:inInterceptors>` snippet from `src/java/main/webapp/WEB-INF/cxf-beans.xml`, and rerunning the server.

### Issues

Current version fails on second invocation, where serialized `FreelancerInfo` object is returned. The encountered exception is:
```java
org.apache.cxf.interceptor.Fault: Unmarshalling Error: unexpected element (uri:"http://webinterface/", local:"return"). Expected elements are <{}return> 
```

which I understand as inconsistency of namespaces between generated WSDL and actual SOAP response created by the server.
I don't understand why this happens, and after some searching it seems creating XMLAdapters could be a solution (although I don't see how that would
bypass seemingly incorrect XSD generation).

I would like to note though that the server does work, and behaves well in e.g., SoapUI (sent to `http://localhost:8080/Server/services/FreelancerAccessPointPort`):

```xml
<soapenv:Envelope
    xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
    xmlns:web="http://webinterface/">
   <soapenv:Header />
   <soapenv:Body>
      <web:GetByJobType>
         <!--Optional:-->
         <arg0>knitting</arg0>
      </web:GetByJobType>
   </soapenv:Body>
</soapenv:Envelope>
```

yields 

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:GetByJobTypeResponse xmlns:ns2="http://webinterface/">
         <return>5547f3a3-d160-4589-8363-108d5669ae4d</return>
         <return>673dd0ef-7571-4f7a-8975-9f906bd21853</return>
         <return>e107a2ac-7554-4d4f-b37a-547f3e954834</return>
      </ns2:GetByJobTypeResponse>
   </soap:Body>
</soap:Envelope>
```

and 

```xml
<soapenv:Envelope 
xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://webinterface/">
   <soapenv:Header />
   <soapenv:Body>
      <web:GetFreelancerInfo>
         <!--Optional:-->
         <arg0>5547f3a3-d160-4589-8363-108d5669ae4d</arg0>
      </web:GetFreelancerInfo>
   </soapenv:Body>
</soapenv:Envelope>
```

yields

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:GetFreelancerInfoResponse xmlns:ns2="http://webinterface/">
         <ns2:return>
            <id>5547f3a3-d160-4589-8363-108d5669ae4d</id>
            <jobTypes>knitting</jobTypes>
            <name>John Doe</name>
         </ns2:return>
      </ns2:GetFreelancerInfoResponse>
   </soap:Body>
</soap:Envelope>
```

(similarly to before, UUIDs are random for each server rerun)

## Interceptor-secured Server

- Requires messages to contain credentials within header, which correspond to name and password combination in existing mockup database

### How to use

- Run server
- Send messages mentioned above with added header as follows:

```xml
<soapenv:Envelope 
xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://webinterface/">
   <soapenv:Header>
    <wsse:Security>
      <wsse:UsernameToken>
        <wsse:Username>Jack Luck</wsse:Username>
        <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">pass123</wsse:Password>
      </wsse:UsernameToken>
     </wsse:Security>
  </soapenv:Header>
   <soapenv:Body>
      <web:GetByJobType>
         <!--Optional:-->
         <arg0>knitting</arg0>
      </web:GetByJobType>
   </soapenv:Body>
</soapenv:Envelope>
```

yields

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:GetByJobTypeResponse xmlns:ns2="http://webinterface/">
         <return>6fea67c4-c88d-4936-8218-151c2c3e5b88</return>
         <return>1fdfb8cc-2491-4529-958f-e6acc926bfac</return>
         <return>16646cbe-b886-46e3-80a1-f98e2ce144bf</return>
      </ns2:GetByJobTypeResponse>
   </soap:Body>
</soap:Envelope>
```

Changing password to invalid one, e.g. `pass321`, server sends back:

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <soap:Fault>
         <faultcode xmlns:ns1="http://ws.apache.org/wss4j">ns1:SecurityError</faultcode>
         <faultstring>A security error was encountered when verifying the message</faultstring>
      </soap:Fault>
   </soap:Body>
</soap:Envelope>
```


Notes to self
===

```
org.springframework.beans.factory.BeanCreationException: ... No default constructor found; ...
```

Fix: Webservices need default parameterless constructors

---

```
org.springframework.beans.factory.BeanCreationException: ... Could not find definition for service {http://interface/}FreelancerAccessPointService.
```

Fix: `cxf-beans.xml` change `jaxws:endpoint xmlns:ts` from `<interface_package>` to `<implementation_package>`

I have no idea why generator plugs in interface package, but after manually fixing it, all works like a charm.

---

SoapUI/Client receives this as server response:

```html
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> ...<title>CXF - Service list</title> ... </html>
```

Fix: inside generated .wsdl change from "<soap:address location=".../services"/>" to "<soap:address location=".../services/MyServicePort"/>"

Again, no idea why this happens, but it happened every time with JDK 17. JDK 1.8 seems to generate the correct address.

Additionally, the URL is correct when there's only a single WebMethod.

---

Serialized objects are received "empty", e.g.:
```xml
<ns2:GetFreelancerInfoResponse xmlns:ns2="http://webinterface/">
    <ns2:return />
</ns2:GetFreelancerInfoResponse>
``` 
- corresponding complexType in XSD is empty or only partial

Fix: Serializable works out of the box iff:
- parameterless constructor exists, and
- all to-be-exposed properties have defined setters and getters