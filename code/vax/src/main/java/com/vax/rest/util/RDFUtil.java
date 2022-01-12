package com.vax.rest.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.xml.sax.SAXException;

import com.vax.rest.util.FusekiAuthenticationUtilities.FusekiConnectionProperties;


public class RDFUtil {
	
	private static final String PREDICATE_NAMESPACE = "http://www.xml.proj/gradj/rdf/predicate/";
	
	public static void generateRDFFromXML(String xmlFilePath, String rdfFilePath) throws SAXException, IOException {
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		
		System.out.println("[INFO] Extracting metadata from RDFa attributes...");
		try {
			metadataExtractor.extractMetadata(
					new FileInputStream(new File(xmlFilePath)), 
					new FileOutputStream(new File(rdfFilePath)));
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateFuseki(String rdfFilePath, String dbString) throws IOException {
		
		FusekiConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

		System.out.println("[INFO] Loading UPDATE triples from an RDF/XML to a model...");
		
		// Creates a default model
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Loading the changes from an RDF/XML
		model.read(rdfFilePath);

		System.out.println("[INFO] Rendering the UPDATE model as RDF/XML...");
		model.write(System.out, SparqlUtil.RDF_XML);

		// Issuing the SPARQL update...
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, SparqlUtil.NTRIPLES);
		
        // Updating the named graph with the triples from RDF model
     	System.out.println("[INFO] Inserting the triples to a named graph \"" + dbString +"/metadata" + "\".");
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + "/" + dbString +"/metadata", new String(out.toByteArray()));
		System.out.println(sparqlUpdate);
		
		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		// UpdateProcessor sends update request to a remote SPARQL update service. 
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
		
		// Issuing a simple SPARQL query to make sure the changes were made...
		System.out.println("[INFO] Making sure the changes were made in the named graph \"" + dbString +"/metadata" + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + dbString +"/metadata", "?s ?p ?o");
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the collection, dump output response with the use of ResultSetFormatter
		ResultSet results = query.execSelect();
		ResultSetFormatter.out(System.out, results);
		
		query.close();

		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the collection, dump output response with the use of ResultSetFormatter
		results = query.execSelect();
		ResultSetFormatter.out(System.out, results);
		
		query.close();

		model.close();
		
		System.out.println("[INFO] End.");
	}
}
