//import java.io.InputStreamReader;
package ECM2414_CA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;

/**
 * Write a description of class Source here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WriteToFile
{
    private String filepath;
    
    public WriteToFile(String filepath) {
        this.filepath = filepath;
    }
    
    public void writeLineIntoFile(String textLine) throws IOException {
        textLine += "\n";
        FileWriter write = new FileWriter(this.filepath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(write);
        bufferedWriter.write(textLine );
        bufferedWriter.close();
    }
    
    public static void main(String[] args) {
        String filepath = "C:/Users/Jonas/Desktop/Uni/Year 2/Computer Science/ECM2414 Software Development/CA/test.txt";
        WriteToFile w = new WriteToFile(filepath);
        
        String line = "Hello there!";
        //System.out.println(line);
        
        try {
            w.writeLineIntoFile(line);
        }
        catch (IOException e) {
        
        }
        
    } 
}
