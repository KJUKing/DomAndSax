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

            // SAXParser 생성
            SAXParser saxParser = factory.newSAXParser();

            // 핸들러 설정
            DefaultHandler handler = new DefaultHandler() {
                boolean bFirstName = false;
                boolean bLastName = false;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    System.out.println("Start Element :" + qName);
                    if (qName.equalsIgnoreCase("FIRSTNAME")) {
                        bFirstName = true;
                    }
                    if (qName.equalsIgnoreCase("LASTNAME")) {
                        bLastName = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.println("End Element :" + qName);
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bFirstName) {
                        System.out.println("First Name : " + new String(ch, start, length));
                        bFirstName = false;
                    }
                    if (bLastName) {
                        System.out.println("Last Name : " + new String(ch, start, length));
                        bLastName = false;
                    }
                }
            };

            // XML 파일 파싱
            saxParser.parse(new File("C:/input.xml"), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
