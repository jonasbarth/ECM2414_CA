/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.File;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jb901
 */
public class WriteToFileTest {
    private WriteToFile write;
    private String path;
    
    public WriteToFileTest() {
    }
    
    

    public void setUp() throws IOException {
        this.path = "output.txt";
        this.write = new WriteToFile(this.path);
        
    }
    
    public void tearDown() {
        this.write = null;
        this.path = null;
    }
    /**
     * Test of writeLineIntoFile method, of class WriteToFile.
     */
    @Test
    public void testWriteLineIntoFile() throws Exception {
       setUp();
       String message = "This is a writing to a file test";
       this.write.writeLineIntoFile(message);
       
       ReadFile rf = new ReadFile(this.path);
       String result = rf.openFile();
       assertEquals(message, result);
       File file = new File(this.path);
       file.delete();
       tearDown();
    }

    
}
