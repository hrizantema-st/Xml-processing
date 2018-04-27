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
public class Main2 {
	/**
	 * @param args
	 * 
	 */
	public static void main(final String[] args) {
		String filename = args[0];
		String[] attributes = args[1].split(",");
		String[] values = args[2].split(",");

		try {
			File xmlFile = new File("src\\main\\resources\\" + filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(xmlFile);
			document.getDocumentElement().normalize();

			NodeList allNodes = document.getElementsByTagName("*");

			for (int i = 0; i < allNodes.getLength(); i++) {
				Node currentNode = allNodes.item(i);

				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					NamedNodeMap currentAttributes = currentNode.getAttributes();
					boolean isRelevant = false;
					String curVal = null;

					for (int a = 0; a < currentAttributes.getLength(); a++) {
						Node currentAttribute = currentAttributes.item(a);
						for (int j = 0; j < attributes.length; j++) {
							if (currentAttribute.getNodeName().equals(attributes[j])
									&& currentAttribute.getNodeValue().equals(values[j])) {
								isRelevant = true;
								curVal = values[j];
								break;
							}
						}
					}
					if (isRelevant == true) {
						System.out.println(curVal + ": " + currentNode.getTextContent());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
