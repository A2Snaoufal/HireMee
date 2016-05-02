package marklogic;

import java.util.Observable;
import java.util.Observer;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.JSONDocumentManager;

public class MarklogicConnector implements Observer {
	Config config;
    DatabaseClient client;
    JSONDocumentManager docMgr;



public MarklogicConnector() {
		super();
		// TODO Auto-generated constructor stub
	}

public void init(){
	 config = new Config();
	 //recuperer les identifiant de connection
	DatabaseClient client = DatabaseClientFactory.newClient(config.host, config.port, config.user, config.password, config.authType);
}

public void quit(){
    client.release();
}


@Override
public void update(Observable o, Object arg) {
	// TODO Auto-generated method stub
	
}
	
	
}
