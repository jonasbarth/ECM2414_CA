/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 660050748, 6600499985
 */
public class ReadFileTest {
    private File txt, csv;
    private String messageTxt, messageCsv;
   
    public ReadFileTest() {
    }
    
    
    
    public void setUp() throws IOException {
        //create a text and csv file
        this.messageTxt = "This is a txt file";
        this.messageCsv = "This is a csv file";
        
        this.txt = new File("txt.txt");
        this.csv = new File("csv.csv");
        
        this.txt.createNewFile();
        this.csv.createNewFile();
        
        FileWriter writerTxt = new FileWriter(this.txt);
        FileWriter writerCsv = new FileWriter(this.csv);
        writerTxt.write(messageTxt);
        writerCsv.write(messageCsv);
        
        writerTxt.close();
        writerCsv.close();
    }
    
    
    public void tearDown() {
        this.txt.delete();
        this.csv.delete();
        this.txt = null;
        this.csv = null;
        this.messageCsv = null;
        this.messageTxt = null;
    }

    /**
     * Test of openFile method, of class ReadFile.
     */
    @Test
    public void testOpenFileTxt() {
        try {
            setUp();
        } catch (IOException ex) {
            fail("Setup could not be performed due to IOException");
        }
        
        ReadFile rf = new ReadFile(this.txt.getPath());
        try {
            String result = rf.openFile();
            assertEquals(this.messageTxt, result);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        tearDown();
    }
    
    @Test
    public void testOpenFileCsv() {
        try {
            setUp();
        } catch (IOException ex) {
            fail("Setup could not be performed due to IOException");
        }
        
        ReadFile rf = new ReadFile(this.csv.getPath());
        try {
            String result = rf.openFile();
            assertEquals(this.messageCsv, result);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        tearDown();
    }

    

    
}
