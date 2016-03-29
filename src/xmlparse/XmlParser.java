package xmlparse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
	
	private static Document document;
	
	public static void init() {
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		}
	}
	
	public static Map<String,String> parserXmltoMap(String fileName) {
		Map<String,String> map = new HashMap<>();
		StringBuffer sb = new StringBuffer("/Users/bym/xmldoc/");
		sb.append(fileName).append(".xml");
		init();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(sb.toString());
             
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                NodeList employeeInfo = employee.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node = employeeInfo.item(j);
                    NodeList employeeMeta = node.getChildNodes();
                    for (int k = 0; k < employeeMeta.getLength(); k++) {
                        System.out.println(employeeMeta.item(k).getNodeName()
                                + ":" + employeeMeta.item(k).getTextContent());
                        map.put(employeeMeta.item(k).getNodeName(), employeeMeta.item(k).getTextContent());
                    }
                }
            }
            System.out.println("解析完毕");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return map;
    }
	
	public static void createXmlFromList(String fileName, List<org.jsoup.nodes.Element> list) {
		init();
		StringBuffer sb = new StringBuffer("/Users/bym/xmldoc/");
		sb.append(fileName).append(".xml");
		Element root = document.createElement("scores"); 
		document.appendChild(root); 
		Element content = document.createElement("content");
		for(org.jsoup.nodes.Element e : list) {
			if(e.nodeName().equals("p")) {
				Element p = document.createElement("p"); 
		        p.appendChild(document.createTextNode(e.text())); 
		        content.appendChild(p);
			} else if(e.nodeName().equals("img")) {
				Element p = document.createElement("img"); 
		        p.appendChild(document.createTextNode(e.attr("src"))); 
		        content.appendChild(p);
			}
		}
		root.appendChild(content);
		TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(sb.toString()));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("生成XML文件成功!");
            
            /**
             * 保存成功后返回一个map 键：fileName 值：path
             */
            
            
            
        } catch (TransformerConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
//	public static void main(String[] args) {
//		String str = "/Users/bym/xmldoc/filename.xml";
////		createXml(str);
//		parserXml(str);
//	}

}
