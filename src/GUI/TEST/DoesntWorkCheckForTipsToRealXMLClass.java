/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TEST;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author kasper
 */
public class DoesntWorkCheckForTipsToRealXMLClass {
    
    boolean bfname = false;
    boolean blname = false;
    boolean bnname = false;
    boolean bsalary = false;
       
    
    public static void main(String argv[]) throws ParserConfigurationException, SAXException
    {
        
      
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler(); 
            
    
    }    
    
    
    public void startElement(String uri, String localName, String qName, Attributes attributes)
    {
        System.out.println("Start Element :" + qName);
        
        if (qName.equalsIgnoreCase("FIRSTNAME")) 
        {
            bfname = true;
        }
        
        if (qName.equalsIgnoreCase("LASTNAME"))
        {
            blname = true;
        }
        
        if (qName.equalsIgnoreCase("NICKNAME"))
        {
            bnname = true;
        } 
        
        if (qName.equalsIgnoreCase("SALARY"))
        {
            bsalary = true;
        }
    }
    
    public void endElement(String uri, String localName, String qName) 
    {
        System.out.println("End Element :" + qName);
    }
    
    public void characters(char ch[], int start, int length)
    {
        if (bfname) 
        {
            System.out.println("First name :" + new String(ch, start, length));
            bfname = false;
        }
            
        
        if (blname) 
        {
            System.out.println("Last name :" + new String(ch, start, length));
            blname = false;
        }
        
        if (bnname) 
        {
            System.out.println("Nick name :" + new String(ch, start, length));
            bnname = false;
        }
        
        if (bsalary) 
        {
            System.out.println("Salary :" + new String(ch, start, length));
            bsalary = false;
        }
        
       
    }
    
}



