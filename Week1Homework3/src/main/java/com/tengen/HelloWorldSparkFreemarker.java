package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkFreemarker {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreemarker.class, "/");
		
		Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try{
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					
					helloMap.put("name", "Wooorllddd Freemarker");
					
					helloTemplate.process(helloMap, writer);
				}catch (Exception e)
				{
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});

	}
}
