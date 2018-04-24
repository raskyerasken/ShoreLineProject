/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TEST;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;
import static java.time.Clock.system;
import static oracle.jrockit.jfr.tools.ConCatRepository.usage;


/**
 *
 * @author kasper
 */
public class XmlToJava extends DefaultHandler {
    
    private Hashtable tags;
    String filename = null;

    public void main(String[] arghs) throws ParserConfigurationException, SAXException, IOException {
        
        //sets up the parser that analyses the text
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser(); 
        
        //sets up the XMLReader
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new XmlToJava());
        xmlReader.parse(convertToFileURL(filename));
        
       //sets up error handling
       xmlReader.setErrorHandler((ErrorHandler) new MyErrorHandler(System.err));
       
       
        
        
        //sets up the command-line arguments, gets the name of the file to process
        for (int i = 0; i < arghs.length; i++) {
            filename = arghs[i];
            
            if (i != arghs.length - 1)
            {
                usage();
           
            }
    }
        if (filename == null) {
            usage();
        }
    }
    
    
    //defines the command-line options, tells the application the name of the XML file to be processed
    private static String convertToFileURL(String filename) 
    {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        
        return "file:" + path;
    }
    
    //used if the incorrect command-line arguments are specificed
    private static void usage() 
    {
     System.err.println("Usage: SAXLocalNameCount <file.xml>");
     System.err.println("          -usage or -help this message");
     System.exit(1);
    }
    
    //Defines what to do when the parser finds the start point of the document
    //it sets up a hashtable where the information will be stored
    public void startDocument() throws SAXException
    {
        tags = new Hashtable();
    }
    
    //Defines what to do when the parser finds the end point of the document
    //checks to see whether or not there are any more keys in the document
    //it also gets the names and counts of the elements contained in the hashtable
    //prints out a message onscreen teo tell the user how many incidences of each element were found
    public void endDocument() throws SAXException 
    {
        Enumeration e = tags.keys();
        while (e.hasMoreElements()) 
        {
            String tag = (String)e.nextElement();
            int count = ((Integer)tags.get(tag)).intValue();
            System.out.println("Localname \"" + tag + "\" occurs "
            + count + " times");
        }
    }
    
    
    //populates the hashtable created by the startDocument()
    //
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException
    {
        String key = localName;
        Object value = tags.get(key);
        
        if (value == null) 
        {
            tags.put(key, new Integer(1));
        }
        
        else 
        {
            int count = ((Integer)value).intValue();
            count++;
            tags.put(key, new Integer(count));
        }
    }
    
    
    
}

