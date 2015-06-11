package aec.replication;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parsing {

	private String xmlElement;
	private String attrName;
	private String attrValue;
	
	public Parsing(String xmlElement, String attrName, String attrValue) {
		this.xmlElement = xmlElement;
		this.attrName = attrName;
		this.attrValue = attrValue;
	}
	
	public Parsing() {
		// TODO Auto-generated constructor stub
	}

	public Document parseFile(File file)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		return doc;
	}
	
	public void printNote(NodeList nodeList) {
		
		for (int count = 0; count < nodeList.getLength(); count++) {
			
			Node tempNode = nodeList.item(count);
			
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// xmlElement can be path, link , qparticipant
				setXmlElement(tempNode.getNodeName());
				// get node name and value
				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent() + "value");
				
				if (tempNode.hasAttributes()) {
					
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					
					for (int i = 0; i < nodeMap.getLength(); i++) {
						
						Node node = nodeMap.item(i);
						attrName = node.getNodeName();
						attrValue = node.getNodeValue();
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());
						
					}
					
				}
				
				if (tempNode.hasChildNodes()) {
					printNote(tempNode.getChildNodes());
					System.out.println("has child nodes ");
				}
				
				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
				
			}
		}
	}
	
	public void ParseXML(File file)
			throws ParserConfigurationException, SAXException, IOException {
		
		Document doc = parseFile(file);

		if (doc.hasChildNodes()) {
			printNote(doc.getChildNodes());
			
		}
	}

	public String getXmlElement() {
		return xmlElement;
	}

	public void setXmlElement(String xmlElement) {
		this.xmlElement = xmlElement;
	}
	
	public String getAttrName() {
		return attrName;
	}
	
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	public String getAttrValue() {
		return attrValue;
	}
	
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	
}
