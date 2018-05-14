/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Converter;

import java.io.PrintStream;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author kasper
 */
public class MyErrorHandler implements ErrorHandler 
{
    private PrintStream out;
    
    MyErrorHandler(PrintStream out) 
    {
        this.out = out;
    }

    private String getParseExceptionInfo(SAXParseException spe) 
    {
        String systemId = spe.getSystemId();
        
        if (systemId == null)
        {
            systemId = "null";   
        }
        
        String info = "URI=" + systemId + " Line="
                + spe.getLineNumber() + ": " + spe.getMessage();
        
        return info;
    }

    @Override
    public void warning(SAXParseException spe) throws SAXException
    {
        out.println("Warning: " + getParseExceptionInfo(spe));
    }

    @Override
    public void error(SAXParseException spe) throws SAXException 
    {
        String message =" Error: " + getParseExceptionInfo(spe);
    }

    @Override
    public void fatalError(SAXParseException spe) throws SAXException 
    {
        String message = "Fatal Error: " + getParseExceptionInfo(spe);
        throw new SAXException(message); 
    }
    
}
