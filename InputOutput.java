import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Input/Output of text files, parent class of Dictionary and ShakespeareGA
 */
public class InputOutput {
	
	public static String readFile(String filename) throws FileNotFoundException, IOException{
        System.out.println("getting file: " + filename);
        BufferedReader br = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()));
        String everything = "";
        
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                //sb.append(System.lineSeparator());
                sb.append(System.getProperty("line.separator"));
                //sb.append("\n");
                line = br.readLine();
            }
            
            everything = sb.toString();
        } finally {
            br.close();
        }
        
        return everything;
    }
	
	public static void writeFile(){
		
	}
}
