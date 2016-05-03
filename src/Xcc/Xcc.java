/*
 * Copyright 2003-2016 MarkLogic Corporation
 */
package Xcc;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import marklogic.Config;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.marklogic.xcc.AdhocQuery;
import com.marklogic.xcc.ContentSource;
import com.marklogic.xcc.ContentSourceFactory;
import com.marklogic.xcc.RequestOptions;
import com.marklogic.xcc.ResultItem;
import com.marklogic.xcc.ResultSequence;
import com.marklogic.xcc.Session;
import com.marklogic.xcc.exceptions.RequestException;
import com.marklogic.xcc.exceptions.XccConfigException;

/**
 * <p>
 * The obligatory Hello World example.
 * </p>
 * <p>
 * Click here for the <a href="doc-files/HelloWorld.java.txt"> source code for
 * this class</a>.
 * </p>
 */
public class Xcc {

	public URI uri;
	public Session session;
	public AdhocQuery request;
	public ResultSequence rs;
	public ArrayList<String> res;
    public Config config;
    
	public Xcc() {
		super();
		try {
			init();
		} catch (XccConfigException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init() throws URISyntaxException, XccConfigException {
		// Create a URI object from the supplied argument
		//uri = new URI("xcc://hireme:hireme@10.68.219.105:8003/Hireme");
		config=new Config();
		uri = new URI("xcc://"+config.admin_user+":"+config.admin_password+"@"+config.host+":"+config.port+"/"+config.DbName);

 
		// Obtain a ContentSource object for the server at the URI.
		// ContentSources can create many Sessions. ContentSources are
		// tightly bound to a host/port, but user, password and contentbase,
		// if provided, are defaults and may be overridden for each Session.
		ContentSource contentSource = ContentSourceFactory
				.newContentSource(uri);

		// Create a Session, which encapsulates host, port, user and
		// password, and an optional contentbase id. If Contentbase is
		// not specified, the default configured on the server for the
		// host/port will be used.
		// Sessions represent a dialog with a contentbase and may hold
		// state related to that dialog. A Session is also the factory
		// for Request objects. Sessions are lightweight and relatively
		// cheap to create -- don't bother pooling them, they do not
		// represent connections.
		session = contentSource.newSession();

	}

	public void submit() {
		try {
			rs = session.submitRequest(request);

		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void request(String req) {
		request = session.newAdhocQuery(req);
		// Set the query language to JavaScript
		RequestOptions options = new RequestOptions();
		options.setQueryLanguage("json");

		// Submit the query
		request.setOptions(options);
		submit();
	}
	
	public String ElementToString(String xml){
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document document = null;
		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(new InputSource(new StringReader(xml)));
			
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document.getDocumentElement()
				.getTextContent();
		
	}

	public ArrayList<String> getResults() {

		res = new ArrayList<String>();

		
		while (rs.hasNext()) {
			ResultItem item = rs.next();
			res.add(ElementToString(item.asString()));

		}

		return res;
	}

	public void close() {
		// All done
		session.close();
	}

	public void print() {

		System.out.println("Display results start ---------------");
		for (String x : getResults()) {

			System.out.println("Value : "+x);
		}
		
		System.out.println("Display results end ---------------");
		

	}

	public static void main(String[] args) throws Exception {

		Xcc xcc = new Xcc();
		int id=3;
		xcc.request("doc()/Taxonomy/family[@id=\""+id+"\"]/skill/name");
		xcc.print();
		id=2;
		xcc.request("doc()/Taxonomy/family[@id=\""+id+"\"]/skill/name");
		xcc.print();
		id=1;
		xcc.request("doc()/Taxonomy/family[@id=\""+id+"\"]/skill/name");
		xcc.print();
		String category="cms";
		xcc.request("doc()/Taxonomy/family[name=\""+category+"\"]/skill/name");
		xcc.print();
		category="big data";
		xcc.request("doc()/Taxonomy/family[name=\""+category+"\"]/skill/name");
		xcc.print();
		category="NoSQL Databases";
		xcc.request("doc()/Taxonomy/family[name=\""+category+"\"]/skill/name");
		xcc.print();
		
		xcc.close();

	}
}
