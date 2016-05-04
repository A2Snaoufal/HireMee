package marklogic;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.FailedRequestException;
import com.marklogic.client.ForbiddenUserException;
import com.marklogic.client.ResourceNotFoundException;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;

public class MarklogicConnector implements Observer {
	Config config;
	DatabaseClient client;
	JSONDocumentManager docMgr;

	public MarklogicConnector() {
		super();
		init();
	}

	public void init() {
		config = new Config();
		// recuperer les identifiant de connection
		client = DatabaseClientFactory.newClient(config.host, config.port,
				config.user, config.password, config.authType);
	}

	public void quit() {
		client.release();
	}

	public void createJSONFromString(String name, String content,
			String collection) {

		try {
			// acquire the content
			InputStream docStream = new ByteArrayInputStream(
					content.getBytes("UTF8"));

			// create a manager for JSON documents
			docMgr = client.newJSONDocumentManager();

			// add a collection tag
			DocumentMetadataHandle metadata = new DocumentMetadataHandle();
			metadata.getCollections().addAll(collection);

			// create a handle on the content
			InputStreamHandle handle = new InputStreamHandle(docStream);

			// write the document content
			docMgr.write(name, metadata, handle);

		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ForbiddenUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailedRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void createJSONFromInputStream(String name, InputStream docStream,
			String collection) {

		try {
			
			// create a manager for JSON documents
			docMgr = client.newJSONDocumentManager();

			// add a collection tag
			DocumentMetadataHandle metadata = new DocumentMetadataHandle();
			metadata.getCollections().addAll(collection);

			// create a handle on the content
			InputStreamHandle handle = new InputStreamHandle(docStream);

			// write the document content
			docMgr.write(name, metadata, handle);

		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ForbiddenUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FailedRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
