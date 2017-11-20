/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Class to test the PreGameSetup class
 * @author 660050748, 6600499985
 */
public class PreGameSetupTest {
    private File[] files;
    private String input;
    
    public PreGameSetupTest() {
    }
    
    @Before
    public void setUp() {
        this.files = new File[3];
        String[] filePaths = {"firstBag.txt", "secondBag.txt", "thirdBag.txt"};
        String content = "10,10,10,10,10,10,10,10,10,10,10";
        
        //Input to be given to system.in
        String input = "";
        
        for (int i = 0; i < filePaths.length; i++){
            input += filePaths[i] + "\n";
            
            //create file object
            File file = new File(filePaths[i]);
            this.files[i] = file;
            try {
                file.createNewFile();
                WriteToFile write = new WriteToFile(filePaths[i]);
                
                //Write the list of integers into the each file
                write.writeLineIntoFile(content);
            } catch (IOException ex) {
                fail(ex.getMessage());
            }
  
        }
        
        this.input = input;
    }
    
    @After
    public void tearDown(){
        for (int i = 0; i < this.files.length; i++){
            //this.files[i].delete();
            
        }
        this.files = null;
        
    }
    
    @Test
    public void testAskInputExit(){
        PreGameSetup pre = new PreGameSetup();
        String input = "E\n";
        
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        pre.askInput();
        
    }
    
    
    @Test
    public void testAskInputValidPlayerExit(){
        PreGameSetup pre = new PreGameSetup();
        String input = "2\nE\n";
        
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        pre.askInput();
        
    }
    
    
    @Test
    public void testAskInputValidEverything(){
        PreGameSetup pre = new PreGameSetup();
        
        String pass = "1\n" + this.input;
        //System.out.println(this.input);
        System.setIn(new ByteArrayInputStream(pass.getBytes()));
        
        pre.askInput();
        
    }
    
    
    
    
    
    /**
     * Test of getInfoOnNumOfPlayers method, of class PreGameSetup.
     */
    @Test
    public void testGetInfoOnNumOfPlayersValid() {
        System.out.println("getInfoOnNumOfPlayers");
        PreGameSetup pre = new PreGameSetup();
        String input = "3";
        
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Scanner scanner = new Scanner(System.in);
            String result = pre.getInfoOnNumOfPlayers(scanner);
            assertEquals(input, result);
            scanner.close();
        } 
        catch (NoSuchElementException e) {
            fail("Invalid input");
        }
        
    }
    
    
    @Test
    public void testGetInfoOnNumOfPlayersNegative() {
        System.out.println("getInfoOnNumOfPlayers");
        PreGameSetup pre = new PreGameSetup();
        String input = "-10";
        Scanner scanner = null;
        
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            scanner = new Scanner(System.in);
            String result = pre.getInfoOnNumOfPlayers(scanner);
            
            fail("Invalid input");
        } 
        catch (NoSuchElementException e) {
            assertTrue(true);
            scanner.close();
            
        }  
    }
    
    
    @Test
    public void testGetInfoOnNumOfPlayersZero() {
        System.out.println("getInfoOnNumOfPlayers");
        PreGameSetup pre = new PreGameSetup();
        String input = "0";
        Scanner scanner = null;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            scanner = new Scanner(System.in);
            String result = pre.getInfoOnNumOfPlayers(scanner);
            
            fail("Invalid input");
        } 
        catch (NoSuchElementException e) {
            assertTrue(true);
            scanner.close();
        }  
    }
    

    
    @Test
    public void testGetInfoOnPathForPebblesExitFirst() {
        PreGameSetup pre = new PreGameSetup();
        
        String input = "E";
        
        
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String result = pre.getInfoOnPathForPebbles(scanner);
        
        assertEquals("E", result);
        scanner.close();
 
    }
    
    @Test
    public void testGetInfoOnPathForPebblesExitSecond() {
        PreGameSetup pre = new PreGameSetup();
        
        String input = "noExit\nE";
        
        
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String result = pre.getInfoOnPathForPebbles(scanner);
        
        assertEquals("E", result);
        scanner.close();
    }
    
    
    @Test
    public void testGetInfoOnPathForPebblesThreeValidFiles() {
        PreGameSetup pre = new PreGameSetup();
        pre.validPlayerEntry("1");
        System.out.println(this.input);
        System.setIn(new ByteArrayInputStream(this.input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String result = pre.getInfoOnPathForPebbles(scanner);
        scanner.close();
 
    }
    
    
    @Test
    public void testGetInfoOnPathForPebblesInvalidFileExit() {
        PreGameSetup pre = new PreGameSetup();
        pre.validPlayerEntry("1");
        String path = "invalid.txt";
        File file = new File(path);
        try {
            file.createNewFile();
            WriteToFile write = new WriteToFile(path);
            write.writeLineIntoFile("1,2,3");
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
        String input = path + "\nE\n";
        System.setIn(new ByteArrayInputStream(this.input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        String result = pre.getInfoOnPathForPebbles(scanner);
        scanner.close();
 
    }
    
    
    
    /**
     * Test of validFile method, of class PreGameSetup.
     */
    @Test
    public void testValidFileNonExisting() throws Exception {
        String filePath = "nonExisting";
        PreGameSetup pre = new PreGameSetup();
        
        try {
            boolean result = pre.validFile(filePath);
        }
        catch (IOException e) {
            fail("Should have thrown FileNotFoundException");
        }
        catch (FileNotFoundException e){
            assertTrue(true);
        }
        catch (IllegalFileContentException e){
            fail("Should have thrown FileNotFoundException");
        }
        catch (NoFileExtentionException e){
            fail("Should have thrown FileNotFoundException");
        }
        catch (InvalidFileExtentionException e){
            fail("Should have thrown FileNotFoundException");
        }
        
        
    }
    
    
    /**
     * Test of validFile method, of class PreGameSetup.
     */
    @Test
    public void testValidFileNoExtension() throws Exception {
        String filePath = "noExtension";
        File file = new File(filePath);
        file.createNewFile();
        PreGameSetup pre = new PreGameSetup();
        
        try {
            boolean result = pre.validFile(filePath);
        }
        catch (IOException e) {
            fail("Should have thrown NoFileExtentionException");
        }
        catch (FileNotFoundException e){
            fail("Should have thrown NoFileExtentionException");
        }
        catch (IllegalFileContentException e){
            fail("Should have thrown NoFileExtentionException");
        }
        catch (NoFileExtentionException e){
            assertTrue(true);
        }
        catch (InvalidFileExtentionException e){
            fail("Should have thrown NoFileExtentionException");
        }
        file.delete();
        
        
    }
    
    
    /**
     * Test of validFile method, of class PreGameSetup.
     */
    @Test
    public void testValidFileWrongExtension() throws Exception {
        String filePath = "wrongExtension.wav";
        File file = new File(filePath);
        file.createNewFile();
        PreGameSetup pre = new PreGameSetup();
        
        try {
            boolean result = pre.validFile(filePath);
        }
        catch (IOException e) {
            fail("Should have thrown InvalidFileExtentionException");
        }
        catch (FileNotFoundException e){
            fail("Should have thrown InvalidFileExtentionException");
        }
        catch (IllegalFileContentException e){
            fail("Should have thrown InvalidFileExtentionException");
        }
        catch (NoFileExtentionException e){
            fail("Should have thrown InvalidFileExtentionException");
        }
        catch (InvalidFileExtentionException e){
            assertTrue(true);
        }
        file.delete();
        
        
    }
    
    
    /**
     * Test of validFile method, of class PreGameSetup.
     */
    @Test
    public void testValidFileIllegalContentTxt() throws Exception {
        String filePath = "illegalContent.txt";
        String content = "1,a,2,3,-4,5";
        File file = new File(filePath);
        file.createNewFile();
        PreGameSetup pre = new PreGameSetup();
        WriteToFile write = new WriteToFile(filePath);
        
        try {
            write.writeLineIntoFile(content);
            boolean result = pre.validFile(filePath);
        }
        catch (IOException e) {
            fail("Should have thrown IllegalFileContentException");
        }
        catch (FileNotFoundException e){
            fail("Should have thrown IllegalFileContentException");
        }
        catch (IllegalFileContentException e){
            assertTrue(true);
        }
        catch (NoFileExtentionException e){
            fail("Should have thrown IllegalFileContentException");
        }
        catch (InvalidFileExtentionException e){
            fail("Should have thrown IllegalFileContentException");
        }
        file.delete();
        
        
    }
    
    /**
     * Test of validFile method, of class PreGameSetup.
     */
    @Test
    public void testValidFileIllegalContentCsv() throws Exception {
        String filePath = "illegalContent.csv";
        String content = "1,a,2,3,-4,5";
        File file = new File(filePath);
        file.createNewFile();
        PreGameSetup pre = new PreGameSetup();
        WriteToFile write = new WriteToFile(filePath);
        
        try {
            write.writeLineIntoFile(content);
            boolean result = pre.validFile(filePath);
        }
        catch (IOException e) {
            fail("Should have thrown IllegalFileContentException");
        }
        catch (FileNotFoundException e){
            fail("Should have thrown IllegalFileContentException");
        }
        catch (IllegalFileContentException e){
            assertTrue(true);
        }
        catch (NoFileExtentionException e){
            fail("Should have thrown IllegalFileContentException");
        }
        catch (InvalidFileExtentionException e){
            fail("Should have thrown IllegalFileContentException");
        }
        file.delete(); 
    }
    
    
    /**
     * Test of validFile method, of class PreGameSetup.
     */
    @Test
    public void testValidFileLegalContentTxt() throws Exception {
        String filePath = "legalContent.txt";
        String content = "1,2,3,4,5,6,7,8,9,11,12";
        
        File file = new File(filePath);
        file.createNewFile();
        PreGameSetup pre = new PreGameSetup();
        pre.validPlayerEntry("1");
        WriteToFile write = new WriteToFile(filePath);
        
        try {
            write.writeLineIntoFile(content);
            boolean result = pre.validFile(filePath);
            assertTrue(result);
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
        catch (FileNotFoundException e){
            fail("Should not have thrown FileNotFoundException");
        }
        catch (IllegalFileContentException e){
            fail("Should not have thrown IllegalFileContentException");
        }
        catch (NoFileExtentionException e){
            fail("Should not have thrown NoFileExtentionException");
        }
        catch (InvalidFileExtentionException e){
            fail("Should not have thrown InvalidFileExtentionException");
        }
        file.delete(); 
    }
    
    
    /**
     * Test of validFile method, of class PreGameSetup.
     */
    @Test
    public void testValidFileLegalContentCsv() throws Exception {
        String filePath = "legalContent.csv";
        String content = "1,2,3,4,5,6,7,8,9,11,12";
        
        File file = new File(filePath);
        file.createNewFile();
        PreGameSetup pre = new PreGameSetup();
        pre.validPlayerEntry("1");
        WriteToFile write = new WriteToFile(filePath);
        
        try {
            write.writeLineIntoFile(content);
            boolean result = pre.validFile(filePath);
            assertTrue(result);
        }
        catch (IOException e) {
            fail(e.getMessage());
        }
        catch (FileNotFoundException e){
            fail("Should not have thrown FileNotFoundException");
        }
        catch (IllegalFileContentException e){
            fail("Should not have thrown IllegalFileContentException");
        }
        catch (NoFileExtentionException e){
            fail("Should not have thrown NoFileExtentionException");
        }
        catch (InvalidFileExtentionException e){
            fail("Should not have thrown InvalidFileExtentionException");
        }
        file.delete(); 
    }
    
    
    @Test
    public void testValidPlayerEntryNegative() {
        PreGameSetup pre = new PreGameSetup();
        boolean result = pre.validPlayerEntry("-3");
        assertFalse(result);
        
    }
    
    @Test
    public void testValidPlayerEntryNoNumber() {
        PreGameSetup pre = new PreGameSetup();
        boolean result = pre.validPlayerEntry("a");
        assertFalse(result); 
    }
    
    @Test
    public void testValidPlayerEntryZero() {
        PreGameSetup pre = new PreGameSetup();
        boolean result = pre.validPlayerEntry("0");
        assertFalse(result);
        
    }
    
    @Test
    public void testValidPlayerEntryMaxInt() {
        PreGameSetup pre = new PreGameSetup();
        boolean result = pre.validPlayerEntry(Integer.toString(Integer.MAX_VALUE));
        assertTrue(result);
        
    }
    
    @Test
    public void testValidPlayerEntryValid() {
        PreGameSetup pre = new PreGameSetup();
        boolean result = pre.validPlayerEntry("5");
        assertTrue(result);
    }
    
   
   
}
