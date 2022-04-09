08 - REST
===

REST-accessible version of the Contractor webservice.

# Usage

- copy `jaxb-ri` and `jaxrs-ri` in their respective folders in `extlibs/` (e.g., `.../08-rest/extlibs/jaxrs-ri/api/*.jar`)
- run project in Eclipse, start using Tomcat 10

- base path for server is `http://localhost:8080/RestContractorServer/webservice/`

Usage of the REST API (along with some comments) is demonstrated in a shared **Postman collection** [here](https://www.postman.com/collections/cc054aa85c83f1bb6126).

This can be imoprted via `Postman -> My Workspace -> Import -> Link`

Alternatively, import file [./RestContractorServerExamples.postman_collection.json](./RestContractorServerExamples.postman_collection.json)

## For example

- GET: `http://localhost:8080/RestContractorServer/webservice/contracts/info/{{contractId}}` (with `contractId = 201326592`)

yields `200` with body
```json
{
    "customerId": 167772167,
    "description": "Sample description for contract number 0",
    "freelancerId": 184549382,
    "id": 201326592,
    "jobType": "glassblowing",
    "title": "Sample contract #0"
}
```

- PUT: `http://localhost:8080/RestContractorServer/webservice/contracts/assign/{{contractId}}/{{freelancerId}}` (with `contractId = 201326592, freelancerId = 184549382`)

yields `200` with body
```json
{
    "customerId": 167772162,
    "description": "Sample description for contract number 0",
    "freelancerId": 184549382,
    "id": 201326592,
    "jobType": "knitting",
    "title": "Sample contract #0"
}
```

- calling same PUT again (so freelancer is assigned)

yields `400` with body
```
Contract is already assigned.
```

- POST: `http://localhost:8080/RestContractorServer/webservice/contracts/` with body
```json
{
    "title": "REST-inserted contract",
    "description": "REST-inserted contract description.",
    "jobType": "knitting",
    "customerId": {{customerId}}
}
```

yields `201` with body
```json
{
    "customerId": 167772167,
    "description": "REST-inserted contract description.",
    "freelancerId": 0,
    "id": 201326598,
    "jobType": "knitting",
    "title": "REST-inserted contract"
}
```