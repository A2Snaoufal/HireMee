package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CvParser {

	int id;
	String auteur;
	String date;
	String texte;
	
	public CvParser() {
		// TODO Auto-generated constructor stub
	}
	
	public CvParser(int id,String auteur,String date,String texte) {
		this.id=id;
		this.auteur=auteur;
		this.date=date;
		this.texte=texte;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
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
	

	

	
	String toJSON(String uri) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		
		//Object to JSON in file
	/*	try {
			mapper.writeValue(new File(uri), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		//Object to JSON in String
		return  mapper.writeValueAsString(this);
		
		
	}

	public static void main(String[] args)  {
		
		CvParser cv=new CvParser();
		try {
			String text=cv.parseToString("cv.doc");
			Metadata metadata=cv.parseToMetadata("cv.doc");
			
			System.out.println(metadata.get("Last-Author"));
			System.out.println(metadata.get("Last-Save-Date"));
			System.out.println(text);
			////
			CvParser cv2=new CvParser(0, metadata.get("Last-Author"),metadata.get("Last-Save-Date"), text);
			cv2.toJSON("cv.json");
		} catch (IOException | SAXException | TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
