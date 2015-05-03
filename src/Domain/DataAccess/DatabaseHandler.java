/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.DataAccess;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Admin
 */
public class DatabaseHandler {
    
    private static DatabaseHandler instance;
    
    protected DatabaseHandler(){
        
    }
    
    public static DatabaseHandler getInstance(){
        
        if(instance == null){
            instance = new DatabaseHandler();
        }
        
        return instance;
    }
    
    public boolean bookExists(String pBookId){
        
        boolean result = false;
        
        try {
 
            File fXmlFile = new File("./src/Books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = (org.w3c.dom.Document) dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("book");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;
                            
                            String bookId = eElement.getElementsByTagName("bookId").item(0).getTextContent();
                            
                            if(pBookId.equals(bookId)){
                                result = true;
                            }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
        
    public boolean bookAvailable(String pBookId){
        
        boolean result = false;
        
        
        org.jdom2.Element book;
 
        SAXBuilder builder = new SAXBuilder();
        org.jdom2.Document readDoc = null;
        try {
            readDoc = builder.build(new File("./src/Books.xml"));
            org.jdom2.Element root = readDoc.getRootElement();
            List children = root.getChildren();

            for(int i=0;i<children.size();i++){
                
                
                book = (org.jdom2.Element) children.get(i);
                
                if(book.getChildText("bookId").equals(pBookId)){
                    
                    String childText = book.getChildText("available");
                    
                    if(childText.equals("1")){
                        result = true;
                    }
                    break;
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
            xmlOutput.output(readDoc, new FileOutputStream(new File("./src/Books.xml")));

        } catch (JDOMException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(result);
        return result;
        
    }    
    
    public boolean studentExists(String pStudentId){
        
        boolean result = false;
        
        try {
 
            File fXmlFile = new File("./src/Students.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = (org.w3c.dom.Document) dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("student");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;
                            
                            String bookId = eElement.getElementsByTagName("studentId").item(0).getTextContent();
                            
                            if(pStudentId.equals(bookId)){
                                result = true;
                            }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
     
    public void disableBook (String pBookId){
        
        org.jdom2.Element book;
 
        SAXBuilder builder = new SAXBuilder();
        org.jdom2.Document readDoc = null;
        try {
            readDoc = builder.build(new File("./src/Books.xml"));
            org.jdom2.Element root = readDoc.getRootElement();
            List children = root.getChildren();

            for(int i=0;i<children.size();i++){
                
                
                book = (org.jdom2.Element) children.get(i);
                
                if(book.getChildText("bookId").equals(pBookId)){
                    
                    book.getChild("available").setText("0");
                    break;
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
            xmlOutput.output(readDoc, new FileOutputStream(new File("./src/Books.xml")));

        } catch (JDOMException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void enableBook (String pBookId){
        
        org.jdom2.Element book;
 
        SAXBuilder builder = new SAXBuilder();
        org.jdom2.Document readDoc = null;
        try {
            readDoc = builder.build(new File("./src/Books.xml"));
            org.jdom2.Element root = readDoc.getRootElement();
            List children = root.getChildren();

            for(int i=0;i<children.size();i++){
                
                
                book = (org.jdom2.Element) children.get(i);
                
                if(book.getChildText("bookId").equals(pBookId)){
                    
                    book.getChild("available").setText("1");
                    break;
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
            xmlOutput.output(readDoc, new FileOutputStream(new File("./src/Books.xml")));

        } catch (JDOMException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean insertBookLoan(String pBookId, String pStudentId){
        
        boolean result = false;
        
        try {
 
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            org.w3c.dom.Document document = documentBuilder.parse("./src/Loans.xml");
            Element root = document.getDocumentElement();


            Element newServer = document.createElement("loan");
            root.appendChild(newServer);

            Element name = document.createElement("studentId");
            name.appendChild(document.createTextNode(pStudentId));
            newServer.appendChild(name);

            Element port = document.createElement("bookId");
            port.appendChild(document.createTextNode(pBookId));
            newServer.appendChild(port);

            root.appendChild(newServer);
            
            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            StreamResult res = new StreamResult("./src/Loans.xml");
            transformer.transform(source, res);
            
            
            SAXBuilder builder = new SAXBuilder();
            org.jdom2.Document readDoc = null;
            readDoc = builder.build(new File("./src/Loans.xml"));
            
            XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());

            xmlOutput.output(readDoc, new FileOutputStream(new File("./src/Loans.xml")));
                        
            disableBook(pBookId);
            
            result = true;

            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        
        return result;
    }
    
    public boolean removeBookLoan(String pBookId){
        
        boolean result = false;
        
        org.jdom2.Element loan;
 
        SAXBuilder builder = new SAXBuilder();
        org.jdom2.Document readDoc = null;
        try {
            readDoc = builder.build(new File("./src/Loans.xml"));
            org.jdom2.Element root = readDoc.getRootElement();
            List children = root.getChildren();

            for(int i=0;i<children.size();i++){
                loan = (org.jdom2.Element) children.get(i);
                
                if(loan.getChildText("bookId").equals(pBookId)){
                    
                    System.out.println("bookId: " + loan.getChildText("bookId") + "--- param: " + pBookId);
                    children.remove(i);
                    break;
                }
            }

            XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
            xmlOutput.output(readDoc, new FileOutputStream(new File("./src/Loans.xml")));
            
            enableBook(pBookId);

            result = true;

        } catch (JDOMException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return result;
    } 
}
