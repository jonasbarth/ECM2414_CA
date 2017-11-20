
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


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
    
    
    
}
