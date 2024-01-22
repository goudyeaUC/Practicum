import java.io.BufferedOutputStream;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.ArrayList;

public class ProductWriter {
    public static void main(String[] args){
        ArrayList<String> productRecords = new ArrayList<>();
        Boolean shouldContinue;
        String productID;
        String productName;
        String productDescription;
        double productCost;
        String productCompleteRecord;
        Scanner in = new Scanner(System.in);
        // int recordPrintCounter=0; //Left over from a test printing step

        do{
            productID= SafeInput.getNonZeroLenString(in,"Enter an ID for the record");
            productName=SafeInput.getNonZeroLenString(in, "Enter a name for the record");
            productDescription=SafeInput.getNonZeroLenString(in,"Enter a description for the record ");
            productCost=SafeInput.getDouble(in,"Enter a price for the record");
            productCompleteRecord= (productID+", "+productName+", "+productDescription+", "+String.valueOf((productCost)));
            productRecords.add(productCompleteRecord);
            shouldContinue=SafeInput.getYNConfirm(in,"Do you wish to continue adding records? Type Y or N to proceed.");}
        while(shouldContinue==true);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory+"\\src\\ProductTestData.txt");
        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : productRecords)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
