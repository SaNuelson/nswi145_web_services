SAAJ Client implementation
===

`Client` project has an interface `ClientProxy`.

That has 2 implementations:
- `AutoContractorClient` which uses JAX-WS Service
- `SaajContractorClient` which manually constructs and deconstructs SOAP messages.

`Main.main()` method runs both on a simple example.

### Usage

1. Run `Server` via `Runner.main()`
2. Run `Client` via `Main.main()`

An example output:
```
Client proxy test - Service client
.. for knitting found IDs: [2e732c60-c5ff-4eae-a955-d04cd45bf99d, ef64b37d-0ed6-4690-bd24-8b13006345fe, b12697bc-4089-44f4-b79b-edb2a63faec1]
.. info for ID: 2e732c60-c5ff-4eae-a955-d04cd45bf99d
Freelancer Info
ID:       2e732c60-c5ff-4eae-a955-d04cd45bf99d
Name:     John Doe
JobTypes: [knitting]
.. info for ID: ef64b37d-0ed6-4690-bd24-8b13006345fe
Freelancer Info
ID:       ef64b37d-0ed6-4690-bd24-8b13006345fe
Name:     Jack Dove
JobTypes: [knitting]
.. info for ID: b12697bc-4089-44f4-b79b-edb2a63faec1
Freelancer Info
ID:       b12697bc-4089-44f4-b79b-edb2a63faec1
Name:     Littlefinger
JobTypes: [knitting, smithing, other]
Client proxy test - SAAJ client
.. for knitting found IDs: [2e732c60-c5ff-4eae-a955-d04cd45bf99d, ef64b37d-0ed6-4690-bd24-8b13006345fe, b12697bc-4089-44f4-b79b-edb2a63faec1]
.. info for ID: 2e732c60-c5ff-4eae-a955-d04cd45bf99d
Freelancer Info
ID:       2e732c60-c5ff-4eae-a955-d04cd45bf99d
Name:     John Doe
JobTypes: [knitting]
.. info for ID: ef64b37d-0ed6-4690-bd24-8b13006345fe
Freelancer Info
ID:       ef64b37d-0ed6-4690-bd24-8b13006345fe
Name:     Jack Dove
JobTypes: [knitting]
.. info for ID: b12697bc-4089-44f4-b79b-edb2a63faec1
Freelancer Info
ID:       b12697bc-4089-44f4-b79b-edb2a63faec1
Name:     Littlefinger
JobTypes: [knitting, smithing, other]

Process finished with exit code 0

```

Servlet implementation
===

Servlet currently only handles requests for Freelancer service,
namely, `GetFreelancerInfo`, where a privacy tag can be added in form:
```xml
<privacy xmlns="http://servlet.privacy/" level="partial" />
```

with `level` being one of 
- "none" - provides no privacy and is equivalent to the privacy tag being ommited entirely
- "partial" - provides partial privacy, replacing all but first name with initials (e.g., "John van Dongle Jr." translates to "John D. J.")
- "full" - full name gets hidden ("John van Dongle Jr." becomes "\*\*\*\* \*\*\* \*\*\*\*\*\* \*\*\*")

### Usage

1. Start `Server` via `Runner.main`
2. Start `Servlet` via Tomcat
3. Use e.g., SoapUI and send `POST` to `http://localhost:8080/Servlet_war_exploded/freelancer-servlet` with body:
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:int="http://interfaces/">
    <soapenv:Header/>
    <soapenv:Body>
        <privacy xmlns="http://servlet.privacy/" level="partial" />
        <int:GetFreelancerInfo>
            <!--Optional:-->
            <freelancerId>2e732c60-c5ff-4eae-a955-d04cd45bf99d</freelancerId>
        </int:GetFreelancerInfo>
    </soapenv:Body>
</soapenv:Envelope>
```

In which case the example output is:
```xml
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <ns2:GetFreelancerInfoResponse xmlns:ns2="http://interfaces/">
            <freelancerInfo>
                <id>2e732c60-c5ff-4eae-a955-d04cd45bf99d</id>
                <name>John D.</name>
                <jobType>knitting</jobType>
            </freelancerInfo>
        </ns2:GetFreelancerInfoResponse>
        <privacy xmlns="http://servlet.privacy/">Partial privacy provided.</privacy>
    </S:Body>
</S:Envelope>
```

Note: The UUIDs of freelancers are randomly generated and therefore you
should first request freelancer IDs for a job type, e.g., `POST` to `http://127.0.0.1:8000/freelancers` with:
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:int="http://interfaces/">
   <soapenv:Header/>
   <soapenv:Body>
      <int:GetByJobType>
         <!--Optional:-->
         <jobType>knitting</jobType>
      </int:GetByJobType>
   </soapenv:Body>
</soapenv:Envelope>
```

yielding something similar to:
```xml
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <ns2:GetByJobTypeResponse xmlns:ns2="http://interfaces/">
         <freelancerId>2e732c60-c5ff-4eae-a955-d04cd45bf99d</freelancerId>
         <freelancerId>ef64b37d-0ed6-4690-bd24-8b13006345fe</freelancerId>
         <freelancerId>b12697bc-4089-44f4-b79b-edb2a63faec1</freelancerId>
      </ns2:GetByJobTypeResponse>
   </S:Body>
</S:Envelope>
```