//import java.io.InputStreamReader;
package ECM2414_CA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class containing methods to create a file and to write to it
 *
 * @author 660050748, 660049985
 * @version 18/11/2017
 */
public class WriteToFile
{
    private String filepath;
    
    /**
    * Constructor for objects of class WriteToFile
    *
    * @param filepath String that will have the path of the file on the system
    */
    public WriteToFile(String filepath) throws IOException {
        this.filepath = filepath;
        createFile(this.filepath);
    }
    //Helper method used to create the necessary files
    private void createFile(String filepath) throws IOException {
        
        File file = new File(filepath);
        //if the file  already exists with that path name, then we will delete 
        //it and create a new one with the same pathname. Essentially over writing.
        //this happens because as we keep on playing more games, the files being created
        //will have the same name as the ones already created.
        if (file.isFile()) {
            file.delete();
            file.createNewFile();
        }
        else {
            file.createNewFile();
        }
        
    }
    
    /**
    * Method writes the String argument to the path of the file that the 
    * WriteToFile Object contains
    *
    * @param textLine String that is the text you want to write to the file
    * @throws IOException 
    */
    public void writeLineIntoFile(String textLine) throws IOException {
        textLine += "\n";
        FileWriter write = new FileWriter(this.filepath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(write);
                
        bufferedWriter.write(textLine);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
    
    public static void main(String[] args) {
        String filepath = "C:/Users/Jonas/Desktop/Uni/Year 2/Computer Science/ECM2414 Software Development/CA/test2.txt";
        WriteToFile w;
        String line = "Hello there!";
        String line2 = "hi";
        try {
            w = new WriteToFile(filepath);
            w.writeLineIntoFile(line);
            w.writeLineIntoFile(line2);
        } catch (IOException e) {
            
        }
        

    } 
}
