package com.product.api;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SolrTest {
    public static void main(String[] args) {
            String urlString = "http://localhost:8983/solr/product";
            try {
                List<String> list = Files.readAllLines(Paths.get("C:\\\\coding-test\\\\testdata.tsv"));
                String columnName = list.get(0);
                String[] columnNames = columnName.split("\\t");
                HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
                solr.setParser(new XMLResponseParser());
                int colCount = 1;

                for (int i = 1; i <=list.size(); i++) {
                    String[] subProducts = list.get(i).split("\\t");
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", colCount++);
                    for (int j = 0; j < columnNames.length; j++) {
                        document.addField(columnNames[j], subProducts[j]);
                    }
                    solr.add(document);
                    solr.commit();
                   // System.out.println(subProducts);
                }
            } catch (Exception e) {
                int a = 10;
            }
           /* String urlString = "http://localhost:8983/solr/testing";
            HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
            solr.setParser(new XMLResponseParser());
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", "123456");
            document.addField("gvenname", "Himabindu K");
            document.addField("value", "599.99");
            solr.add(document);
            solr.commit();*/


    }
}
