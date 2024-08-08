package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

public class SAXExample {
    public static void main(String[] args) {
        try {
            // SAXParserFactory 생성
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // 사용자 정의 핸들러 설정
            DefaultHandler handler = new DefaultHandler() {
                boolean bFirstName = false;
                boolean bLastName = false;
                boolean bDepartment = false;

                // startElement() 메소드 재정의
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    System.out.println("Start Element: " + qName);
                    if (qName.equalsIgnoreCase("employee")) {
                        String id = attributes.getValue("id");
                        System.out.println("Employee ID: " + id);
                    } else if (qName.equalsIgnoreCase("firstname")) {
                        bFirstName = true;
                    } else if (qName.equalsIgnoreCase("lastname")) {
                        bLastName = true;
                    } else if (qName.equalsIgnoreCase("department")) {
                        bDepartment = true;
                    }
                }

                // endElement() 메소드 재정의
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.println("End Element: " + qName);
                }

                // characters() 메소드 재정의
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bFirstName) {
                        System.out.println("First Name: " + new String(ch, start, length));
                        bFirstName = false;
                    } else if (bLastName) {
                        System.out.println("Last Name: " + new String(ch, start, length));
                        bLastName = false;
                    } else if (bDepartment) {
                        System.out.println("Department: " + new String(ch, start, length));
                        bDepartment = false;
                    }
                }
            };

            // XML 파일 파싱
            saxParser.parse(new File("input.xml"), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
