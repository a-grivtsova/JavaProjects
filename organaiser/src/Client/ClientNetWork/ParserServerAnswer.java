package Client.ClientNetWork;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserServerAnswer 
{
	// узнаем тип сообщения: error/success/event
	String type_answer(String xml_message) throws ParserConfigurationException, SAXException, IOException 
    {
    	String msg_client = xml_message;
    	String id = null;
    	
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(msg_client) );
        is.setEncoding("UTF-8");
        Document doc = dBuilder.parse(is);

        doc.getDocumentElement().normalize();

    	msg_client = doc.getDocumentElement().getNodeName();
 	
    	return msg_client;	
    }

	 // узнаем тип события: registration/signin/...
	String type_event_answer(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем
    {
    	String msg_client = xml_message;
    	
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(msg_client) );
        is.setEncoding("UTF-8");
        Document doc = dBuilder.parse(is);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("event"); // получим список узлов всех элементов с указанным именем.
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               
               msg_client = eElement.getAttribute("name");
            }
        }

    	return msg_client;	
    }
	
	ArrayList parser_list_desks(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем
    {
    	System.out.println(xml_message);
    	String msg_client = "no6";
    	ArrayList list = new ArrayList();
    	
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml_message));
        is.setEncoding("UTF-8");
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
    	NodeList nList = doc.getElementsByTagName("event");
    	
        for (int temp = 0; temp < nList.getLength(); temp++) {
           Node nNode = nList.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
        	   NodeList carNameList = eElement.getElementsByTagName("desk_name");
        	   
        	   for (int count = 0; count < carNameList.getLength(); count++) {
                   Node node1 = carNameList.item(count);
                   
                   if (node1.getNodeType() == node1.ELEMENT_NODE) {
                      Element car = (Element) node1;

                      list.add(car.getTextContent());
                   }
           }
        }
        }
    	return list;	
    }
	
	//узнаем id пользователя после регистрации в бд
	static String Parser_User_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Client_Password = "no User Id";
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
	    doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
		
	    for (int temp = 0; temp < nList.getLength(); temp++) {
	       Node nNode = nList.item(temp);
	       
	       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	           Element eElement = (Element) nNode;
	           
	           Client_Password = eElement.getElementsByTagName("id_user").item(0).getTextContent();
	       }
	    }
		return Client_Password;	
	}
	

	static String Parser_Desk_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Desk_Name = "no Desk Name";
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
			
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		       
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
		           
			Desk_Name = eElement.getElementsByTagName("desk_name").item(0).getTextContent();
			}
		}
		return Desk_Name;	
	}	
	
	static String Parser_TaskList_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String TaskList_Name = "no TaskList Name";
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
			
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		       
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
		           
			TaskList_Name = eElement.getElementsByTagName("tasklist_name").item(0).getTextContent();
			}
		}
		return TaskList_Name;	
	}	
	
	static String Parser_Desk_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Desk_Id = "no Desk Id";
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
			
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		       
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
		           
			Desk_Id = eElement.getElementsByTagName("id_desk").item(0).getTextContent();
			}
		}
		return Desk_Id;	
	}

	ArrayList parser_list_tasklists(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем
    {
    	System.out.println(xml_message);
    	String msg_client = "no tasklist";
    	ArrayList list = new ArrayList();
    	
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml_message));
        is.setEncoding("UTF-8");
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
    	NodeList nList = doc.getElementsByTagName("event");
    	
        for (int temp = 0; temp < nList.getLength(); temp++) {
           Node nNode = nList.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
        	   NodeList carNameList = eElement.getElementsByTagName("tasklist_name");
        	   
        	   for (int count = 0; count < carNameList.getLength(); count++) {
                   Node node1 = carNameList.item(count);
                   
                   if (node1.getNodeType() == node1.ELEMENT_NODE) {
                      Element car = (Element) node1;

                      list.add(car.getTextContent());
                   }
           }
        }
        }
    	return list;	
    }

	static String Parser_Card_Name(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Card_Name = "no Card Name";
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
			
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		       
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
		           
			Card_Name = eElement.getElementsByTagName("card_name").item(0).getTextContent();
			}
		}
		return Card_Name;	
	}
	
	static String Parser_Card_Note(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Card_Note = "no Card Note";
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
			
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		       
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
		           
			Card_Note = eElement.getElementsByTagName("note").item(0).getTextContent();
			}
		}
		return Card_Note;	
	}
	
	static String Parser_Card_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String Card_Id = "no Card Id";
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
			
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		       
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
		           
			Card_Id  = eElement.getElementsByTagName("id_card").item(0).getTextContent();
			}
		}
		return Card_Id;	
	}


	static String Parser_TaskList_Id(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем логин
	{
		String TaskList_Id = "no TaskList Id";
			
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new InputSource(new StringReader(xml_message)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("event");
			
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
		       
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
		           
			TaskList_Id = eElement.getElementsByTagName("id_tasklist").item(0).getTextContent();
			}
		}
		return TaskList_Id;	
	}

	ArrayList parser_list_cards(String xml_message) throws ParserConfigurationException, SAXException, IOException // узнаем
    {
    	System.out.println(xml_message);
    	String msg_client = "no card";
    	ArrayList list = new ArrayList();
    	
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml_message));
        is.setEncoding("UTF-8");
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
    	NodeList nList = doc.getElementsByTagName("event");
    	
        for (int temp = 0; temp < nList.getLength(); temp++) {
           Node nNode = nList.item(temp);
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
        	   NodeList carNameList = eElement.getElementsByTagName("card_name");
        	   
        	   for (int count = 0; count < carNameList.getLength(); count++) {
                   Node node1 = carNameList.item(count);
                   
                   if (node1.getNodeType() == node1.ELEMENT_NODE) {
                      Element car = (Element) node1;

                      list.add(car.getTextContent());
                   }
           }
        }
        }
    	return list;	
    }
	
}
