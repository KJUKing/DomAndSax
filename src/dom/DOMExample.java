package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
    현재 이 코드는 DOM의 주요 기능 특징중 3가지를 구현한 코드임
       1. 트리 구조 파싱:
            - DocumentBuilderFactory와 DocumentBuilder를
            사용하여 XML 문서를 파싱하고 DOM 트리로 변환
            - XML 문서의 루트 요소와 그 하위 요소들을 트리 구조로 메모리에 로드
       2. 문서 조작 및 수정:
            - 새로운 employee 요소를 생성하고 속성과 하위 요소를 추가
            - 기존 employee 요소의 department 값을 수정
       3. 문서 탐색:
            - getElementsByTagName 메소드를 사용하여
              특정 태그 이름을 가진 모든 요소를 찾고, 루프를 통해 각 요소에 접근했습니다
 */
public class DOMExample {
    public static void main(String[] args) {
        try {
            // XML 파일 로드 및 파싱
            File inputFile = new File("input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 루트 요소 출력
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // 새로운 employee 요소 생성 및 추가
            Element newEmployee = doc.createElement("employee");
            newEmployee.setAttribute("id", "3");

            Element firstName = doc.createElement("firstname");
            firstName.appendChild(doc.createTextNode("Michael"));
            newEmployee.appendChild(firstName);

            Element lastName = doc.createElement("lastname");
            lastName.appendChild(doc.createTextNode("Johnson"));
            newEmployee.appendChild(lastName);

            Element department = doc.createElement("department");
            department.appendChild(doc.createTextNode("HR"));
            newEmployee.appendChild(department);

            doc.getDocumentElement().appendChild(newEmployee);

            // 특정 요소 수정
            NodeList employees = doc.getElementsByTagName("employee");
            for (int i = 0; i < employees.getLength(); i++) {
                Element employee = (Element) employees.item(i);
                if (employee.getAttribute("id").equals("2")) {
                    employee.getElementsByTagName("department").item(0).setTextContent("Sales");
                }
            }

            // XML 파일 저장
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("output.xml"));
            transformer.transform(source, result);

            System.out.println("XML 파일이 성공적으로 수정되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
