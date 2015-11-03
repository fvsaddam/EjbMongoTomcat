/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejbmongoembeddedtomcat.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.mongodb.MongoClient;
/**
 *
 * @author saddamtbg
 */
@WebListener
public class MongoDBContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoClient mongo = (MongoClient) sce.getServletContext()
                .getAttribute("MONGO_CLIENT");
        mongo.close();
        System.out.println("MongoClient closed successfully");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext ctx = sce.getServletContext();
        MongoClient mongo = new MongoClient(
                ctx.getInitParameter("MONGODB_HOST"),
                Integer.parseInt(ctx.getInitParameter("MONGODB_PORT")));
        System.out.println("MongoClient initialized successfully");
        sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
    }

}
