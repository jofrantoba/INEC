package com.inec.server.control.servlet;


import java.io.IOException;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class TestGenerator extends HttpServlet {
	  private static final Logger log = Logger.getLogger(TestGenerator.class.getName());
	  public static final String JOB_PARENT = "notification_job_parent";
	  
	  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	      // set parent to ensure data consistency
	      Key jobKey = KeyFactory.createKey("NotificationJob", JOB_PARENT);
	      Entity job = new Entity("NotificationJob", jobKey);
	      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	      
	      job.setProperty("next_run_ts", System.currentTimeMillis() / 1000L + 10);
	      job.setProperty("frequency", 10);
	      job.setProperty("remaining", 5);
	      
	      datastore.put(job);
	      
	      log.severe("Created new job to run every 10 seconds 5 times");
	  }
	}

