package com.test;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

public class SolarTest {
    public static void main(String[] args) {
        try {
            String urlString = "http://localhost:8983/solr/test";
            HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
            solr.setParser(new XMLResponseParser());
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", "123456");
            document.addField("name", "Kenmore Dishwasher");
            document.addField("price", "599.99");
            solr.add(document);
            solr.commit();
        }
        catch (Exception e)
        {
            int a=10;
        }
    }
}
