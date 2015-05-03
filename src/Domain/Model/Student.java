/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Model;

/**
 *
 * @author Jerm423
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Student{

	private String studentId;
	private String studentName;
        private String studentLastname;


	public Student(String pStudentId, String pStudentName, String pStudentLastname){

		studentId = pStudentId;
		studentName = pStudentName;
                studentLastname = pStudentLastname;

	}


	public String getStudentId(){
		return studentId;
	}

	public String getStudentName(){
		return studentName;
	}
        
        public String getStudentLastname(){
		return studentLastname;
	}
        
        
        public boolean studentExists(String studentId){
            
            boolean result = false;
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File("./src/Students.xml");
            
            try {
 
		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();
		List list = rootNode.getChildren("student");
 
		for (int i = 0; i < list.size(); i++) {
 
		   Element node = (Element) list.get(i);
                   
                   
                   String id = node.getChildText("studentId");
                           
                   if(id.equals(studentId)){
                       result = true;
                       break;
                   }
 
		}
                
            } catch (IOException io) {
                System.out.println(io.getMessage());
	    } catch (JDOMException jdomex) {
		System.out.println(jdomex.getMessage());
	    }
            
            System.out.println(result);
            return result;
        }

        
        
        
        


}