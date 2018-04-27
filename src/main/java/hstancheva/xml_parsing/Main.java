package hstancheva.xml_parsing;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author hstancheva
 *
 */
public class Main {
	/**
	 * @param args
	 *            xml file (path) attribute name attribute value
	 */
	public static void main(final String[] args) {
		String filename = args[0];
		String attribute = args[1];
		String value = args[2];

		try {
			File xmlFile = new File("src\\main\\resources\\" + filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();
			// System.out.println("Root element :" +
			// document.getDocumentElement().getNodeName());

			NodeList allNodes = document.getElementsByTagName("*");
			for (int i = 0; i < allNodes.getLength(); i++) {
				Node currentNode = allNodes.item(i);
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					NamedNodeMap attributes = currentNode.getAttributes();
					for (int a = 0; a < attributes.getLength(); a++) {
						Node currentAttribute = attributes.item(a);
						if (currentAttribute.getNodeName().equals(attribute)
								&& currentAttribute.getNodeValue().equals(value)) {
							System.out.println(currentAttribute.getNodeValue() + ": " + currentNode.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final static String PATH_TO_XML = "ABASData\\RecordSet\\Record\\Head\\Field";
	private final static String FILENAME = "TaskJunJava_201804.xml";

	// SAX is faster than DOM and use less memory.
}
