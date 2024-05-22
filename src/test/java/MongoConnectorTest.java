import com.mehmetyurekli.Mongo.MongoConnector;
import com.mongodb.client.MongoClient;
import org.junit.Test;

import static org.junit.Assert.*;

public class MongoConnectorTest {

    @Test
    public void isDbConnected(){
        MongoClient client = MongoConnector.getInstance().getMongo();
        assertNotNull(client);
    }
}
