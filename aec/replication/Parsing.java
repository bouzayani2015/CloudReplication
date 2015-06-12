package aec.replication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

	private Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	private Set<String> attrSet = new HashSet<String>();;
	private String key;
	private String myID;
	
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
			String k;
			Node node = nodeList.item(count);
			
			// make sure it's element node.
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// xmlElement can be path, link , qparticipant
				setXmlElement(node.getNodeName());

				// get node name and value
				System.out.println("\nNode Name =" + node.getNodeName() + " [OPEN]");
				
				System.out.println("Node Value =" + node.getTextContent() + "value");
				
				if (node.hasAttributes()) {
					
					// get attributes names and values
					NamedNodeMap nodeMap = node.getAttributes();
					
					for (int i = 0; i < nodeMap.getLength(); i++) {
						
						Node nodes = nodeMap.item(i);
						attrName = nodes.getNodeName();
						attrValue = nodes.getNodeValue();
						/*
						 * if (node.getNodeName().equals("path")) {
						 * setKey(nodes.getNodeValue());
						 * System.out.println("the key is =" + getKey()); }
						 */
						
						if (node.getNodeName().equals("link")) {
							String src = nodes.getNodeValue();
							
							// TODO it should compare with my ID
							if ((nodes.getNodeValue().equals("nodeA"))) {

								setKey(node.getParentNode().getAttributes().item(0).getNodeValue());
								System.out.println("the key is = " + getKey());
							}

							if (nodes.getNodeName().equals("type")) {
								attrSet.add(nodes.getNodeValue());
								System.out.println("Type : " + nodes.getNodeValue());
							}
							if (nodes.getNodeName().equals("target")) {
								attrSet.add(nodes.getNodeValue());
								System.out.println("Target : " + nodes.getNodeValue());
							}

							// }
							System.out.println("attr name : " + nodes.getNodeName());
							System.out.println("attr value : " + nodes.getNodeValue());
							
						}
						
					}
				}
				
				if (node.hasChildNodes()) {
					printNote(node.getChildNodes());
					System.out.println("has child nodes ");
				}
				map.put(key, attrSet);
				System.out.println("Node Name =" + node.getNodeName() + " [CLOSE]");
				
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

	public Map<String, Set<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, Set<String>> map) {
		this.map = map;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Set<String> getAttrSet() {
		return attrSet;
	}

	public void setAttrSet(Set<String> attrSet) {
		this.attrSet = attrSet;
	}

	public String getMyID() {
		return myID;
	}

	public void setMyID(String myID) {
		this.myID = myID;
	}
	
}
