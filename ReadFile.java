
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class ReadFile {
    
    private String path;
    
    public ReadFile(String filePath) {
        this.path = filePath;
    }
    
    
    /**
     * opens and reads the first line of the file
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String openFile() throws FileNotFoundException, IOException  {
        
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textData = bufferedReader.readLine();
        bufferedReader.close();
        return textData;
    }
    
    public int readLine() throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String line = "";
        int numberOfLines = 0;
        
        while ((line = bufferedReader.readLine()) != null) {
            numberOfLines++;
        }
        
        bufferedReader.close();
        return numberOfLines;
    }
    
    public static void main(String[] args){
        String filepath = "D:/test.txt";
        ReadFile rf = new ReadFile(filepath);
        
        try {
            System.out.println(rf.openFile());
        } catch (IOException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
