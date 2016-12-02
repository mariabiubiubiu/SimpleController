package resolution;

import java.io.IOException;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db=documentBuilderFactory.newDocumentBuilder();
				org.w3c.dom.Document document=db.parse("src/xml/MyXml.xml");
				NodeList nameList=document.getElementsByTagName("name");
				for(int i=0;i<nameList.getLength();i++){
					Node actionNode=nameList.item(i);
					NamedNodeMap namedNodeMap=actionNode.getAttributes();
					System.out.println(namedNodeMap.getLength());
				}
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
