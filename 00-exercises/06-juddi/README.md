Talking with jUDDI
===

## Notes to self

- hardcode "-Djavax.xml.accessExternalDTD=all" in catalina.bat (inside the JAVA_OPTS=%JAVA_OPTS% ...)

### Using SoapUI, import the UDDI_Inquiry_PortType WSDL

- needs to be v3

### Using SoapUI, search for the Calculator web service by name

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:urn="urn:uddi-org:api_v3">
   <soapenv:Header/>
   <soapenv:Body>
      <urn:find_service>
         <urn:name xml:lang="en">webcalc</urn:name>
      </urn:find_service>
   </soapenv:Body>
</soapenv:Envelope>
```

yields 

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:serviceList truncated="false" xmlns:ns11="urn:uddi-org:policy_v3_instanceParms" xmlns:ns10="urn:uddi-org:vs_v3" xmlns:ns9="urn:uddi-org:policy_v3" xmlns:ns8="urn:uddi-org:vscache_v3" xmlns:ns7="urn:uddi-org:custody_v3" xmlns:ns6="urn:uddi-org:repl_v3" xmlns:ns5="urn:uddi-org:subr_v3" xmlns:ns4="urn:uddi-org:sub_v3" xmlns:ns3="http://www.w3.org/2000/09/xmldsig#" xmlns:ns2="urn:uddi-org:api_v3">
         <ns2:listDescription>
            <ns2:includeCount>1</ns2:includeCount>
            <ns2:actualCount>1</ns2:actualCount>
            <ns2:listHead>1</ns2:listHead>
         </ns2:listDescription>
         <ns2:serviceInfos>
            <ns2:serviceInfo serviceKey="uddi:www.contractor.com:b7e64595-e002-475a-89e9-05a5da1a5e51" businessKey="uddi:www.contractor.com:b81f70f1-b96e-4e66-8f95-9cbd9af93353">
               <ns2:name xml:lang="en">webcalc</ns2:name>
            </ns2:serviceInfo>
         </ns2:serviceInfos>
      </ns2:serviceList>
   </soap:Body>
</soap:Envelope>
```

### Using SoapUI, search for all services implementing SOAP