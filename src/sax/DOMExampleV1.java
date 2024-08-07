package sax;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DOMExampleV1 {
    public static void main(String[] args) {
        try {
            // XML 파일 로드
            File inputFile = new File("C:/input.xml");

            // DocumentBuilderFactory 생성
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Document 생성
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // 특정 노드 리스트 가져오기
            NodeList nList = doc.getElementsByTagName("employee");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Employee id: " + eElement.getAttribute("id"));
                    System.out.println("First Name: " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name: " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

