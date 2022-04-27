<?xml version="1.0" encoding="UTF-8" ?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="text" doctype-public="XSLT-compat" omit-xml-declaration="yes" encoding="UTF-8" indent="no" />

    <!--
        Although individual non-debug requests differ in outer wrappers, e.g.,
        contractInfos, customerInfos... the internal structure should be identical
    -->

    <xsl:template match="root">
@prefix pc: &lt;http://purl.org/procurement/public-contracts#&gt; .
@prefix rdf: &lt;http://www.w3.org/1999/02/22-rdf-syntax-ns#&gt; .
@prefix rdfs: &lt;http://www.w3.org/2000/01/rdf-schema#&gt; .
@prefix foaf: &lt;http://xmlns.com/foaf/0.1/&gt; .
@prefix ctors: &lt;http://www.contractor.novelins.com/vocab#&gt; .
@prefix ctor: &lt;http://www.contractor.novelins.com/&gt; .
@prefix kees: &lt;http://linkeddata.center/kees/v1#&gt; .
@prefix schema: &lt;http://schema.org/&gt; .
@prefix dc: &lt;http://purl.org/dc/terms/&gt; .
@prefix gr: &lt;http://purl.org/goodrelations/v1#&gt; .
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="contractInfos">
ctor:<xsl:value-of select="./id" /> a pc:Contract ;
    dc:title "<xsl:value-of select="./title" />"@en ;
    dc:description "<xsl:value-of select="./description" />"@en ;
    gr:category "<xsl:value-of select="./jobType" />"@en .
    </xsl:template>
    
    <xsl:template match="customerInfos">
ctor:<xsl:value-of select="./id" /> a ctors:customer ;
    foaf:givenName "<xsl:value-of select="tokenize(./name,' ')[1]" />"@en ;
    foaf:familyName "<xsl:value-of select="tokenize(./name,' ')[2]" />"@en ;
    kees:password "<xsl:value-of select="./pass" />" .
    </xsl:template>
    
    <xsl:template match="freelancerInfos">
    <xsl:variable name="flcid" select="./id" />
ctor:<xsl:value-of select="$flcid" /> a ctors:freelancer ;<xsl:for-each select="./jobTypes">
    gr:offers ctor:woff<xsl:value-of select="$flcid" /><xsl:value-of select="." /> ;</xsl:for-each>
    foaf:givenName "<xsl:value-of select="tokenize(./name,' ')[1]" />"@en ;
    foaf:familyName "<xsl:value-of select="tokenize(./name,' ')[2]" />"@en .
    
    <xsl:variable name="hourlyRate" select="./hourlyRate" />
    <xsl:for-each select="./jobTypes">
        <xsl:call-template name="wagedOffering">
            <xsl:with-param name="type" select="." />
            <xsl:with-param name="rate" select="$hourlyRate" />
            <xsl:with-param name="flcid" select="$flcid" />
        </xsl:call-template>
    </xsl:for-each>
    </xsl:template>
    
    <xsl:template name="wagedOffering">
        <xsl:param name="type" />
        <xsl:param name="rate" />
        <xsl:param name="flcid" />
ctor:woff<xsl:value-of select="$flcid" /><xsl:value-of select="$type" /> a ctors:wagedOffering ;
    gr:category "<xsl:value-of select="$type" />"@en ;
    ctors:costsHourly <xsl:value-of select="$rate" /> .
    </xsl:template>
    
</xsl:transform>
