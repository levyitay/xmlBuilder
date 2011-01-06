package com.Rest2Go.Utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.Buffer;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class xmlBuilder {
	
	public static Document buildXMLFromString (String inputString) throws Exception
	{
		DocumentBuilderFactory dbf =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(inputString));
        
        Document doc = db.parse(is);
        return doc;
	}
	
	
	public static Document buildXMLFromResultSet(ResultSet resSet) throws SQLException
	{
		
		Document document=null;
		
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		 try {
		 DocumentBuilder builder = dBF.newDocumentBuilder(); // java xml documentbuilder
		 document = builder.newDocument();
		 } 
		 catch (ParserConfigurationException parserException) {
			 parserException.printStackTrace();
		 }
		 Element root = document.createElement("RESTAURANTS");
		 document.appendChild(root);
		 
		 
			 int i=0;
				while (resSet.next())
				{
					Element User = document.createElement("Restaurant");
					root.appendChild(User);
					
					Element Name = document.createElement("NAME");
					
					Name.appendChild(document.createTextNode(resSet.getString("Name")));
					User.appendChild(Name);
					
					
					Element Address = document.createElement("ADDRESS");
					Address.appendChild(document.createTextNode(resSet.getString("Address")));
					User.appendChild(Address);
					
					Element Phone = document.createElement("PHONE");
					Phone.appendChild(document.createTextNode(resSet.getString("Phone")));
					User.appendChild(Phone);
					
					Element Phone2 = document.createElement("PHONE2");
					Phone2.appendChild(document.createTextNode(resSet.getString("SecondaryPhone")));
					User.appendChild(Phone2);
					
					
					
					i++;
				}
				document.normalize();
				return document;
	}
	
	public static String DocumentToString(Document doc)
	{

		DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
	    LSSerializer lsSerializer = domImplementation.createLSSerializer();
	    return lsSerializer.writeToString(doc);   
	}
	

}
