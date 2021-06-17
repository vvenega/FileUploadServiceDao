package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraAdapter {
	
private static CassandraAdapter instance;
	
	static {
		instance = new CassandraAdapter();
	}
	
	private CassandraAdapter() {
		
	}


	
	public static void  setFileLoadRecord(String owner, String filename,
			String outputfile,String status,String path) {
		
		CassandraConnector client = new CassandraConnector();
		  client.connect(TypeConstants.CASSANDRA_SERVER, TypeConstants.CASSANDRA_PORT);
		  Session session = client.getSession();
		  
		try {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
			String query="Insert into "+TypeConstants.NAMESPACE+"file_load("+
					"owner,filename,outputfile,status,path,date)values(?,?,?,?,?,?)";
					  
					  PreparedStatement prepared = session.prepare(query);
			    	  BoundStatement bound = prepared.bind(owner,filename,
			    			  outputfile,status,path,dtf.format(now));
		    			session.execute(bound);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		      client.close();
		}
	}
	
	public static void updateFileLoadRecord(String filename,String username,String status,String outputfile) {
		CassandraConnector client = new CassandraConnector();
		  client.connect(TypeConstants.CASSANDRA_SERVER, TypeConstants.CASSANDRA_PORT);
		  Session session = client.getSession();
		  
		try {
			
			String query="update  "+TypeConstants.NAMESPACE+"file_load set "+
					"status=?,outputfile=? where owner=? and filename=?";
					  
					  PreparedStatement prepared = session.prepare(query);
			    	  BoundStatement bound = prepared.bind(status,outputfile,username,filename);
		    			session.execute(bound);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		      client.close();
		}
	}
	
	public static List<FileLoadDao> getFileLoads(String username){
		
		List<FileLoadDao> lst = new ArrayList<FileLoadDao>();
		
		CassandraConnector client = new CassandraConnector();
		  client.connect(TypeConstants.CASSANDRA_SERVER, TypeConstants.CASSANDRA_PORT);
		  Session session = client.getSession();
		
		try {
			
			PreparedStatement prepared = session.prepare(
	    			  "select owner,filename,outputfile,status,path,date from "
	    					  +TypeConstants.NAMESPACE+"file_load where owner=?");

	    			//BoundStatement bound = prepared.bind(airport);
			
			  BoundStatement bound = prepared.bind(username);
	    			ResultSet rset = session.execute(bound);
	    			if(rset!=null) {
	    				Iterator<Row> itr =rset.iterator();
	    				
	    				//System.err.println("rset different than null");
	    				
	    				while(itr.hasNext()) {
	    					Row row = itr.next();
	    					FileLoadDao dao = new FileLoadDao();
	    					dao.setOwner(row.getString(0));
	    					dao.setFilename(row.getString(1));
	    					dao.setOutputfile(row.getString(2));
	    					dao.setStatus(row.getString(3));
	    					dao.setPath(row.getString(4));
	    					dao.setDate(row.getString(5));
	    					
	    					lst.add(new FileLoadDao(dao));
	    					
	    				}
	    			}
			
		}catch(Exception e) {
			e.printStackTrace();
			lst = new ArrayList<FileLoadDao>();
		}finally {
			session.close();
		      client.close();
		}
		
		return lst;
		
	}

}
