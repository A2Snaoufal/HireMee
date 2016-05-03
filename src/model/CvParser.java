package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.SAXException;

public class CvParser {

	public CvParser() {
		// TODO Auto-generated constructor stub
	}
	
	public String parseToString(String uri) throws IOException, SAXException, TikaException {
	    Tika tika = new Tika();
	    try (InputStream stream = CvParser.class.getResourceAsStream(uri)) {
	        return tika.parseToString(stream);
	    }
	}
	
	public Metadata parseToMetadata(String uri) throws IOException, SAXException, TikaException {
	    ToXMLContentHandler handler = new ToXMLContentHandler();
	    AutoDetectParser parser = new AutoDetectParser();
	    Metadata metadata = new Metadata();
	    try (InputStream stream = CvParser.class.getResourceAsStream(uri)) {
	    	parser.parse(stream, handler, metadata);
	        return metadata;
	    }
	}

	public static void main(String[] args)  {
		
		CvParser cv=new CvParser();
		try {
			String text=cv.parseToString("cv.doc");
			Metadata metadata=cv.parseToMetadata("cv.doc");
			
			System.out.println(metadata.get("Last-Author"));
			System.out.println(metadata.get("Last-Save-Date"));
			System.out.println(text);
		} catch (IOException | SAXException | TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
