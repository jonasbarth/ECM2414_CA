/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test class to test the ReadFile class
 * @author 660050748, 6600499985
 */
public class ReadFileTest {
    private File txt, csv;
    private String messageTxt, messageCsv;
   
    public ReadFileTest() {
    }
    
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Testing class ReadFile");
    }
    
    @Before
    public void setUp() throws IOException {
        //create text for txt and csv file
        this.messageTxt = "This is a txt file";
        this.messageCsv = "This is a csv file";
        
        //create new file object for txt and csv
        this.txt = new File("txt.txt");
        this.csv = new File("csv.csv");
        
        //create the actual file
        this.txt.createNewFile();
        this.csv.createNewFile();
        
        
        FileWriter writerTxt = new FileWriter(this.txt);
        FileWriter writerCsv = new FileWriter(this.csv);
        
        //write the message into each file
        writerTxt.write(messageTxt);
        writerCsv.write(messageCsv);
        
        writerTxt.close();
        writerCsv.close();
    }
    
    @After
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
     * Opens a txt file and asserts the result from openFile
     * method is equal to the String written into the file.
     */
    @Test
    public void testOpenFileTxt() {
        
        ReadFile rf = new ReadFile(this.txt.getPath());
        try {
            String result = rf.openFile();
            assertEquals(this.messageTxt, result);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        
    }
    
    /**
     * Test of openFile method, of class ReadFile.
     * Opens a csv file and asserts the result from openFile
     * method is equal to the String written into the file.
     */
    @Test
    public void testOpenFileCsv() {
        
        
        ReadFile rf = new ReadFile(this.csv.getPath());
        try {
            String result = rf.openFile();
            assertEquals(this.messageCsv, result);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
       
    }

    

    
}
