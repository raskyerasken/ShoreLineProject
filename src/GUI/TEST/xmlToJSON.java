/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TEST;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;



/**
 *
 * @author kasper
 */
public class xmlToJSON {
    
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static String TEST_XML_STRING = 
  "<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";

    
    public static void main(String[] args) throws JSONException
    {
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString);
        }
        catch (JSONException je)
        {
            System.out.println(je.toString());
        }
        }
    }

