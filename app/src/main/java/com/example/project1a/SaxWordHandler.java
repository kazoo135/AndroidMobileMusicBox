package com.example.project1a;





import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class SaxWordHandler extends DefaultHandler{
    private List<MusicDictionary> data;
    private MusicDictionary dict;
    private String currentElement = "";
    private StringBuilder currentText;


    List<MusicDictionary> readDataFromXML() throws ParserConfigurationException{

        return data;
    }


    //	the parser obj calls all the below event methods below.
    @Override
    public void startDocument() throws SAXException {
//		System.out.println("Start of Document");
        data = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
//        How to log this in android?
        System.out.println("End of Document");
    }

    @Override
    public void startElement(String uri, String localname, String qname,
                             Attributes attributes) throws SAXException {
//		System.out.println("Start Element:" + qname);
        currentElement = qname;

        if(currentElement.equalsIgnoreCase("item")){
            dict = new MusicDictionary();
            String idAsString = attributes.getValue(MusicDictionary.ID);
            dict.setId(Integer.parseInt(idAsString));
            data.add(dict);
        }else if(!currentElement.equalsIgnoreCase("dictionary") &&
                !currentElement.equalsIgnoreCase("item")){
            currentText = new StringBuilder();
        }

    }

    @Override
    public void endElement(String uri, String localname, String qname) throws SAXException {
//		System.out.println("End Element: " + qname);
        if(currentElement.equalsIgnoreCase("dictionary") || currentElement.equalsIgnoreCase("item")){
            return;
        }
        String content = currentText.toString();

        if(currentElement.equals(MusicDictionary.WORD)){
            dict.setWord(content);
        }else if(currentElement.equals(MusicDictionary.DEF)){
            dict.setDef(content);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//		System.out.println("Characters Event Happened");
        if (currentText != null) {
            currentText.append(ch, start, length);
        }

    }
    // Handle XML Erros HERE
    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Warning!");
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("Error parsing XML");

    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Fatal Parsing Error!");
    }

}

