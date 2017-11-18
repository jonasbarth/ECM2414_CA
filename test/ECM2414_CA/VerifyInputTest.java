/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jb901
 */
public class VerifyInputTest {
    private int[][] values;
    private VerifyInput vf;
    
    
    public VerifyInputTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    public void setUp() {
        this.values = new int[3][];
        this.vf = new VerifyInput();
        this.vf.setPlayerNumber(2);
        
        
        
    }
    
    public void tearDown() {
        this.values = null;
        this.vf = null;
    }

    /**
     * Test of getValues method, of class VerifyInput.
     */
    @Test
    public void testGetValues() {
        setUp();
        System.out.println("getValues");
        Assert.assertArrayEquals(this.values, this.vf.getValues());
        tearDown();
    }


    /**
     * Test of verifyFileMeta method, of class VerifyInput.
     */
    @Test
    public void testVerifyFileMetaFileNotExist() {
        setUp();
        System.out.println("verifyFileMetaFileNotExist");
        String fileName = "nonExistent.txt";
        try { 
            this.vf.verifyFileMeta(fileName);
            fail("Should have thrown FileNotFoundException");
        } catch (FileNotFoundException ex) {
            assertTrue(true);
        } catch (NoFileExtentionException ex) {
            fail("Should have thrown FileNotFoundException");
        } catch (InvalidFileExtentionException ex) {
            fail("Should have thrown FileNotFoundException");
        }
        tearDown();
    }
    
    @Test
    public void testVerifyFileMetaWrongExtension(){
        setUp();
        System.out.println("VerifyFileMetaWrongExtension");
        String fileName = "test.pdf";
        File file = new File(fileName);
        
        try {
            file.createNewFile();
            this.vf.verifyFileMeta(fileName);
            fail("Should have thrown InvalidFileExtentionException");
        } catch (FileNotFoundException ex) {
            fail("Should have thrown InvalidFileExtentionException");
            
        } catch (NoFileExtentionException ex) {
            fail("Should have thrown InvalidFileExtentionException");
        } catch (InvalidFileExtentionException ex) {
            assertTrue(true);
        } catch (IOException ex) {
            fail("File could not be created");
        }
        file.delete();
        tearDown();
    }
    
    @Test
    public void testVerifyFileMetaNoExtension() {
        setUp();
        System.out.println("VerifyFileMetaNoExtension");
        String fileName = "noExtension";
        File file = new File(fileName);
        
        try {
            file.createNewFile();
            this.vf.verifyFileMeta(fileName);
            fail("Should have thrown NoFileExtentionException");
        } catch (FileNotFoundException ex) {
            fail("Should have thrown NoFileExtentionException");
            
        } catch (NoFileExtentionException ex) {
            assertTrue(true);
        } catch (InvalidFileExtentionException ex) {
            fail("Should have thrown NoFileExtentionException");
        } catch (IOException ex) {
            fail("File could not be created");
        }
        file.delete();
        tearDown();
    }
    
    @Test
    public void testVerifyFileMetaValidExtensionTxt() {
        setUp();
        System.out.println("VerifyFileMetaNoExtension");
        String fileName = "valid.txt";
        File file = new File(fileName);
        
        try {
            file.createNewFile();
            boolean result = this.vf.verifyFileMeta(fileName);
            assertTrue(result);
        } catch (FileNotFoundException ex) {
            fail("Should not have thrown Exception");
            
        } catch (NoFileExtentionException ex) {
            fail("Should not have thrown Exception");
        } catch (InvalidFileExtentionException ex) {
            fail("Should not have thrown Exception");
        } catch (IOException ex) {
            fail("File could not be created");
        }
        file.delete();
        tearDown();
    }
    
    @Test
    public void testVerifyFileMetaValidExtensionCsv() {
        setUp();
        System.out.println("VerifyFileMetaNoExtension");
        String fileName = "valid.csv";
        File file = new File(fileName);
        
        try {
            file.createNewFile();
            boolean result = this.vf.verifyFileMeta(fileName);
            assertTrue(result);
        } catch (FileNotFoundException ex) {
            fail("Should not have thrown Exception");
            
        } catch (NoFileExtentionException ex) {
            fail("Should not have thrown Exception");
        } catch (InvalidFileExtentionException ex) {
            fail("Should not have thrown Exception");
        } catch (IOException ex) {
            fail("File could not be created");
        }
        file.delete();
        tearDown();
    }

    /**
     * Test of verifyFileContent method, of class VerifyInput.
     */
    @Test
    public void testVerifyFileContentNoContentTxt() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "noContent.txt";
        File file = new File(fileName);
        
        try {
            file.createNewFile();
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) {
            assertTrue(true);
        }
        
        file.delete();
        tearDown();
        
    }
    
    
    @Test
    public void testVerifyFileContentNoContentCsv() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "noContent.csv";
        File file = new File(fileName);
        
        try {
            file.createNewFile();
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) {
            assertTrue(true);
        }
        
        file.delete();
        tearDown();
        
    }
    
    @Test
    public void testVerifyFileContentNoCommasTxt() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "noCommas.txt";
        File file = new File(fileName);
        String content = "1 2 3 4 5 6 7 8 9 10";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            write.writeLineIntoFile(content);
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) {
            assertTrue(true);
        }
        
        file.delete();
        tearDown();
        
    }
    
    
    @Test
    public void testVerifyFileContentNoCommasCsv() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "noCommas.csv";
        File file = new File(fileName);
        String content = "1 2 3 4 5 6 7 8 9 10";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            write.writeLineIntoFile(content);
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) {
            assertTrue(true);
        }
        
        file.delete();
        tearDown();
        
    }
    
    
    @Test
    public void testVerifyFileContentNegativeTxt() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "negative.txt";
        File file = new File(fileName);
        String content = "-1, 2, 3, 4, -5, 6, 7, 8, 9, 10";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            write.writeLineIntoFile(content);
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) {
            assertTrue(true);
        }
        
        file.delete();
        tearDown();
        
    }
    
    
    @Test
    public void testVerifyFileContentNegativeCsv() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "negative.csv";
        File file = new File(fileName);
        String content = "-1, 2, 3, 4, -5, 6, 7, 8, 9, 10";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            write.writeLineIntoFile(content);
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) {
            assertTrue(true);
        }
        
        file.delete();
        tearDown();
        
    }
    
    @Test
    public void testVerifyFileContentValidTxt() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "valid.txt";
        File file = new File(fileName);
        String content = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            
            write.writeLineIntoFile(content);
            boolean result = this.vf.verifyFileContent(fileName);
            assertTrue(result);
            
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) { 
            ex.printStackTrace();
            fail("Should not have thrown IllegalFileContentException");
        }
        
        file.delete();
        tearDown();
        
    }
    
    
    @Test
    public void testVerifyFileContentValidCsv() {
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "valid.csv";
        File file = new File(fileName);
        String content = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            
            write.writeLineIntoFile(content);
            boolean result = this.vf.verifyFileContent(fileName);
            assertTrue(result);
            
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) { 
            ex.printStackTrace();
            fail("Should not have thrown IllegalFileContentException");
        }
        
        file.delete();
        tearDown();
        
    }
    
    
    @Test
    public void testVerifyContentCharactersTxt() {
        
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "valid.txt";
        File file = new File(fileName);
        String content = "1, 2, 3, 4, 5, 6, 7, a, 9, 10, 11, 1, 2, 3, 4, 5, b, 7, 8, 9, 10, 11";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            
            write.writeLineIntoFile(content);
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
            
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) { 
            assertTrue(true);
            
        }
        
        file.delete();
        tearDown();
    }
    
    
    
    @Test
    public void testVerifyContentCharactersCsv() {
        
        setUp();
        System.out.println("verifyFileContent");
        String fileName = "valid.csv";
        File file = new File(fileName);
        String content = "1, 2, 3, 4, 5, 6, 7, a, 9, 10, 11, 1, 2, 3, 4, 5, b, 7, 8, 9, 10, 11";
        
        
        try {
            WriteToFile write = new WriteToFile(fileName);
            file.createNewFile();
            
            write.writeLineIntoFile(content);
            this.vf.verifyFileContent(fileName);
            fail("Should have thrown IllegalFileContentException");
            
        } catch (IOException ex) {
            fail("Test file could not be created");
        } catch (IllegalFileContentException ex) { 
            assertTrue(true);
            
        }
        
        file.delete();
        tearDown();
    }
    
}
