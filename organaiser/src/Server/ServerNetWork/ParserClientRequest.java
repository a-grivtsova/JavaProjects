package Server.ServerNetWork;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserClientRequest {
	// узнаем название запроса :login, logout...
	static String Parser_Request_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем
	{
		String Request_Name = "no request name";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml_message));
	    is.setEncoding("UTF-8");
	    Document doc = dBuilder.parse(is);
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           Request_Name = eElement.getAttribute("name");
	       }
	    }
		return Request_Name;	
	}
	
	static String Parser_Client_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Client_Name = "no name client";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Client_Name = eElement.getElementsByTagName("user_name").item(0).getTextContent();
	       }
	    }
		return Client_Name;	
	}
	
	static String Parser_Desk_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Client_Name = "no name desk";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Client_Name = eElement.getElementsByTagName("desk_name").item(0).getTextContent();
	       }
	    }
		return Client_Name;	
	}
	
	static String Parser_Client_Password(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Client_Password = "no password client";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Client_Password = eElement.getElementsByTagName("user_password").item(0).getTextContent();
	       }
	    }
		return Client_Password;	
	}

	static String Parser_Client_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Client_Id = "no id client";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Client_Id = eElement.getElementsByTagName("id_user").item(0).getTextContent();
	       }
	    }
		return Client_Id;	
	}
	
	static String Parser_TaskList_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String TaskList_Name = "no name tasklist";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           TaskList_Name = eElement.getElementsByTagName("tasklist_name").item(0).getTextContent();
	       }
	    }
		return TaskList_Name;	
	}

	public String Parser_Desk_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException 
	{
		String Desk_Id = "no id desk";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Desk_Id = eElement.getElementsByTagName("id_desk").item(0).getTextContent();
	       }
	    }
		return Desk_Id;
	}
	
	public String Parser_TaskList_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException 
	{
		String TaskList_Id = "no id tasklist";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           TaskList_Id = eElement.getElementsByTagName("id_tasklist").item(0).getTextContent();
	       }
	    }
		return TaskList_Id;
	}
	

	public String Parser_Card_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException
	{
		String Card_Name = "no card name";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Card_Name = eElement.getElementsByTagName("card_name").item(0).getTextContent();
	       }
	    }
		return Card_Name;
	}

	public String Parser_Card_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException
	{
		String Card_Id = "no card id";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Card_Id = eElement.getElementsByTagName("id_card").item(0).getTextContent();
	       }
	    }
		return Card_Id;
	}

	public String Parser_Note(String xml_message) throws ParserConfigurationException, SAXException, IOException
	{
		String note = "no note";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("command");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           note = eElement.getElementsByTagName("note").item(0).getTextContent();
	       }
	    }
		return note;
	}


}
