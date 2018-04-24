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
public class SAXLocalNameCount extends DefaultHandler {
    
    private Hashtable tags;
    String filename = null;

    public void main(String[] arghs) throws ParserConfigurationException, SAXException, IOException {
        
        //sets up the parser that analyses the text
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser(); 
        
        //sets up the XMLReader
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new SAXLocalNameCount());
        xmlReader.parse(convertToFileURL(filename));
        
       //sets up error handling
       xmlReader.setErrorHandler((ErrorHandler) new MyErrorHandler(System.err));
       
       
        
        
        
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
    
    private static void usage() 
    {
     System.err.println("Usage: SAXLocalNameCount <file.xml>");
     System.err.println("          -usage or -help this message");
     System.exit(1);
    }
    
    private void startdocument() throws SAXException
    {
        tags = new Hashtable();
    }
    
    
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
    
    public void startelement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException
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

