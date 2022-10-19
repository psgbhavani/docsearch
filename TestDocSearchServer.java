import static org.junit.Assert.*;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

public class TestDocSearchServer {
    @Test
    public void testWrongHandleRequest() throws URISyntaxException, IOException {
        Handler h = new Handler("./technical/");
        
        URI wrong = new URI("http://localhost/boo");
        
        assertEquals(h.handleRequest(wrong), "Don't know how to handle that path!");
    }

    @Test
    public void testHandleRequest1() throws URISyntaxException, IOException {
        String directory = "./technical/";

        Handler h = new Handler(directory);
        
        URI query = new URI("http://localhost/");
    
        List<File> f = FileHelpers.getFiles(Paths.get(directory));
        String expected = String.format("There are %d files to search", f.size());

        assertEquals(h.handleRequest(query), expected);
    }
}