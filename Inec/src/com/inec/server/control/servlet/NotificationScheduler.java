package com.inec.server.control.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import static com.google.appengine.api.taskqueue.TaskOptions.Builder.*;


@SuppressWarnings("serial")
public class NotificationScheduler extends HttpServlet {
    private static final Logger log = Logger.getLogger(NotificationScheduler.class
            .getName());

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        log.severe("Running cron loop");
        long currentTime = System.currentTimeMillis() / 1000L;
        log.severe("Current time: " + currentTime);

        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
        Key jobKey = KeyFactory.createKey("NotificationJob",
                TestGenerator.JOB_PARENT);
        Query query = new Query("NotificationJob", jobKey)
                .setAncestor(jobKey)
                .setFilter(
                        new FilterPredicate("next_run_ts",
                                FilterOperator.LESS_THAN_OR_EQUAL, currentTime));
        PreparedQuery pq = datastore.prepare(query);

        for (Entity job : pq.asIterable()) {
            processJob(job);
        }
    }

    private void processJob(Entity job) {
        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(withUrl("/sender").param("job_id", "job_id"));
        log.severe("HOLAAA: " + "Hola a todos");
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
      job.setProperty("remaining", ((Long) job.getProperty("remaining")) - 1);
      
      if ((Long)job.getProperty("remaining") == 0) {
          datastore.delete(job.getKey());
      } else {
          job.setProperty("next_run_ts",
              ((Long)job.getProperty("next_run_ts")) + ((Long)job.getProperty("frequency")));
          datastore.put(job);
      }
  }
}
