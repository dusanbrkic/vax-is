package com.vax.rest.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import com.vax.rest.util.AuthenticationUtilities.ConnectionProperties;

public class XMLDatabase {
	
	public static boolean storeXML(String collectionId,String documentId,String contextPath,Object objectToStore) {
		
		Collection col = null;
        XMLResource res = null;
		try {
			ConnectionProperties conn=AuthenticationUtilities.loadProperties();
			Class<?> cl = Class.forName(conn.driver);
			Database database = (Database) cl.newInstance();
	        database.setProperty("create-database", "true");
	        DatabaseManager.registerDatabase(database);
	        
	        OutputStream os = new ByteArrayOutputStream();
	        
	        col = getOrCreateCollection(collectionId,0,conn);
	        res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE); 
			
			XMLParser.marshal(contextPath, "",objectToStore,os,false);
			
			res.setContent(os);
			col.storeResource(res);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException | IOException e) {
			return false;
		}finally {
            
        	//don't forget to cleanup
            if(res != null) {
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
            
            if(col != null) {
                try { 
                	col.close(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
		return true;
	}
	
	public static Object retriveXML(String collectionId,String documentId,String contextPath) {
		
		Collection col = null;
        XMLResource res = null;
		
		try {
			ConnectionProperties conn=AuthenticationUtilities.loadProperties();
			Class<?> cl = Class.forName(conn.driver);
			
			Database database = (Database) cl.newInstance();
	        database.setProperty("create-database", "true");
	        
	        DatabaseManager.registerDatabase(database);
	        
	        col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource)col.getResource(documentId);
            
            if(res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
            } else {
    			return XMLParser.unmarshal(contextPath, "","", false,true,res);	
            }
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | XMLDBException | IOException e) {
			e.printStackTrace();
		}finally {
            if(res != null) {
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
            
            if(col != null) {
                try { 
                	col.close(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
		}
		return null;
	}
	
	private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset,ConnectionProperties conn) throws XMLDBException {
        
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        
        // create the collection if it does not exist
         if(col == null) {
        
         	if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            
        	String pathSegments[] = collectionUri.split("/");
            
        	if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
            
                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }
                
                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);
                
                if (startCol == null) {
                	
                	// child collection does not exist
                    
                	String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);
                    
                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
                    
                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                    
                    col.close();
                    parentCol.close();
                    
                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset,conn);
        } else {
            return col;
        }
    }

}
