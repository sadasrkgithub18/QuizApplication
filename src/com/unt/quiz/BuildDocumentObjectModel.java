package com.unt.quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class BuildDocumentObjectModel {
	
	
	public static Document getDOM(String test) throws SAXException,ParserConfigurationException,IOException, URISyntaxException
	{
		    Document dom=null;
		    File quizFile=null;
		    
	        quizFile=new File("D:/Elipse_Programs/DBMS_Ecommerce/QuizApplication/WebContent/resource/Quizzes/"+test+"-mcq.xml");
	        System.out.println("Quiz File Absolute Path "+quizFile.getAbsolutePath());
	         
	   DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	   DocumentBuilder db=dbf.newDocumentBuilder();
	   try{
	        dom=db.parse(quizFile);
	   }catch(FileNotFoundException fileNotFound){
		   System.out.println("Error : Quiz File Not Found "+fileNotFound);
	   }
	   dom.getDocumentElement().normalize();
	   return dom;
	}

}
