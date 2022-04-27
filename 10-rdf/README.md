10 - RDF
===

## 1. Design a vocabulary for custom data in RDFS.

Custom definitions can be found in [contractor.ttl](./contractor.ttl)

## 2. Transform service output to RDF via XSLT

A debug output was fetched (since individual requests have fairly short responses).

The received JSON is saved in [debugSampleResponse.json](./debugSampleResponse.json).

It was auto-converted to XML via some online tool into [debugSamplReponseConverted.xml](./debugSampleResponseConverted.xml)

An XSLT was created based on it (although it does not 100% reflect the service responses, the structure is identical with the exception of the names of wrapper xml elements). The XSL is found in [xml2rdf.xsl](./xml2rdf.xsl)

Using that on the generated XML yields [generated.rdf.ttl](./generated.rdf.ttl)

## 3. Design a JSON-LD

Putting the generated RDF into EasyRdf yields JSON-LD saved in [jsonLdUgly.json](./jsonLdUgly.json).

I converted a part of it manually into contextualized JSON-LD saved in [jsonLdPretty.json](./jsonLdPretty.json).

## Notes

I'm not sure how to strip the base from the gr:offers property, since without adding the full URL, the convertor doesn't recognize it as a namespaced literal.