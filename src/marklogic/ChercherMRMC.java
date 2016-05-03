//pour utliser la methoder ChercherMRCM
//il faut cree un objer ChercherMRMC puis ajouter le mot clé avec le setter
//puis appliquer la methode ChercherMCRM a cet objet
// cette methode return un arraylist avec le mot clé en question dans le rang0 et les mots relatif dans les autres rang




package marklogic;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.*;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.query.QueryManager;
import com.marklogic.xcc.ContentSource;
import com.marklogic.xcc.ContentSourceFactory;
import com.marklogic.xcc.Request;
import com.marklogic.xcc.ResultItem;
import com.marklogic.xcc.ResultSequence;
import com.marklogic.xcc.Session;
import com.marklogic.xcc.exceptions.RequestException;
import com.marklogic.xcc.exceptions.XccConfigException;

public class ChercherMRMC {
	
	
 private String MC;
	
	

	
	public String getMC() {
	return MC;
}



public void setMC(String mC) {
	MC = mC;
}



	public ChercherMRMC() {
		super();
		// TODO Auto-generated constructor stub
	}



	public static void main(String[] args) throws XccConfigException, RequestException, URISyntaxException{
		
		        ChercherMRMC chercher =new ChercherMRMC();
		        chercher.setMC("big data");
		        ArrayList<String> res = new ArrayList<String>();

		      //  Config config;
		
		       // String[] toppings = new String[20];
				//String[] topping =
      res=chercher.ChercherMRMC();
      System.out.print(res.size());
      System.out.print(res.get(0));
      System.out.print(res.get(1));
      System.out.print(res.get(2));
      System.out.print(res.get(3));




	}

	
	public ArrayList<String> ChercherMRMC() throws XccConfigException, URISyntaxException, RequestException{
		String MC=this.MC;
		//connector.init();
		//queryMgr=connector.client.newQueryManager();
		//"doc()/taxonomy/family[name="+MC+"]/skill/value"
		Config config=new Config();
        ArrayList<String> res = new ArrayList<String>();

        
		URI uri = new URI("xcc://"+config.admin_user+":"+config.admin_password+"@"+config.host+":"+config.port+"/"+config.DbName);
       // URI uri= new URI("xcc://hireme:hireme@10.68.216.78:8003/Hireme");
        ContentSource contentSource = ContentSourceFactory.newContentSource(uri);

        Session session = contentSource.newSession();
     Request request = session.newAdhocQuery("doc(\"/taxonomy-final.xml\")/Taxonomy/family[name=\""+MC+"\"]/skill/name");
        //Request request = session.newAdhocQuery("doc()/taxonomy/family[name=\""+MC+"\"]/skill/value");
    	//xcc.request("cts:search(fn:doc(),\""+s+"\")")
        ResultSequence rs = session.submitRequest(request);
       System.out.println(rs.asString());
       String resultat=rs.asString();
      /* System.out.print(rs.current().asString());
       System.out.print(rs.current().asString());
       System.out.print(rs.current().asString());
       */
      res=getResults(rs);
      
        rs.close();
        session.close();
		//return rs;
        return res;
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

  public ArrayList<String> getResults(ResultSequence rs) {

        ArrayList<String> res = new ArrayList<String>();
         res.add(this.MC);
        
        while (rs.hasNext()) {
               ResultItem item = rs.next();
               res.add(ElementToString(item.asString()));

        }

        return res;
  }


}
