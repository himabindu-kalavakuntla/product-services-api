package com.product.api.service;

import com.product.api.domain.ProductRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductSolrService {

    public static final String SOLR_URL = "http://localhost:8983/solr/product";
    public static final String TAB = "\\t";

    public String processData(ProductRequest request) {
        try {
            List<String> list = Files.readAllLines(Paths.get(request.getFileName()));
            if(!list.isEmpty())
            {
                String headerColumnInfo = list.get(0);
                String[] columnNames = headerColumnInfo.split(TAB);
                HttpSolrClient solr = new HttpSolrClient.Builder(SOLR_URL).build();
                solr.setParser(new XMLResponseParser());
                uploadProductToSolr(list, columnNames, solr);
            }
        }  catch (IOException e) {
            System.out.println("IO Exception occured::"+e.getMessage());
        }
        return "Data saved in Solr Successfully";
    }

    private static void uploadProductToSolr(List<String> list, String[] columnNames, HttpSolrClient solr)
                                                        throws IOException {
        int productUniqueKey = 1;
        for (int i = 1; i < list.size(); i++) {
            String[] subProducts = list.get(i).split(TAB);
            SolrInputDocument document = new SolrInputDocument();
            try{
                document.addField("id", productUniqueKey++);
                for (int j = 0; j < columnNames.length; j++) {
                    document.addField(columnNames[j], subProducts[j]);
                }
                solr.add(document);
                solr.commit();
            } catch (SolrServerException e) {
                System.out.println("SolrServerException occured::"+e.getMessage());
            }
        }
    }
}
