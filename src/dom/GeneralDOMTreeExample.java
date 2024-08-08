package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class GeneralDOMTreeExample {
    public static void main(String[] args) {
        try {
            // XML 파일 로드
            File inputFile = new File("input.xml");

            // DocumentBuilderFactory 생성
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Document 생성
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 루트 노드 출력
            printNode(doc.getDocumentElement(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 재귀적으로 노드를 출력하는 함수
    private static void printNode(Node node, int depth) {
        // 들여쓰기
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }

        // 노드 이름 출력
        System.out.print("<" + node.getNodeName());

        // 속성 출력
        if (node.hasAttributes()) {
            org.w3c.dom.NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
            }
        }

        // 자식 노드가 있는 경우
        if (node.hasChildNodes()) {
            System.out.println(">");
            NodeList childNodes = node.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                // 텍스트 노드의 경우 내용 출력
                if (childNode.getNodeType() == Node.TEXT_NODE) {
                    String textContent = childNode.getTextContent().trim();
                    if (!textContent.isEmpty()) {
                        for (int j = 0; j <= depth; j++) {
                            System.out.print("  ");
                        }
                        System.out.println(textContent);
                    }
                } else {
                    printNode(childNode, depth + 1);
                }
            }
            // 닫는 태그 출력
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }
            System.out.println("</" + node.getNodeName() + ">");
        } else {
            // 자식 노드가 없는 경우 닫는 태그를 같은 줄에 출력
            System.out.println("/>");
        }
    }
}
