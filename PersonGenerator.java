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
public class PersonGenerator {
    public static void main(String[] args){
        //Declarations
        ArrayList<String> personRecords = new ArrayList<>();
        Boolean shouldContinue;
        String personID;
        String personFirstName;
        String personLastName;
        String personTitle;
        String yearOfBirth;
        String personCompleteRecord;
        Scanner in = new Scanner(System.in);
       // int recordPrintCounter=0; //Left over from a test printing step

        do{
            personID= SafeInput.getNonZeroLenString(in,"Enter an ID for the record");
            personFirstName=SafeInput.getNonZeroLenString(in, "Enter a first name for the record");
            personLastName=SafeInput.getNonZeroLenString(in,"Enter a last name for the record");
            personTitle=SafeInput.getNonZeroLenString(in,"Enter a title for the record");
            yearOfBirth=String.valueOf(SafeInput.getInt(in,"Enter the birth year for the record"));
            personCompleteRecord=(personID+", "+personFirstName+", "+personLastName+", "+personTitle+", "+yearOfBirth);
            personRecords.add(personCompleteRecord);
            shouldContinue=SafeInput.getYNConfirm(in,"Do you wish to continue adding records? Type Y or N to proceed.");
        }while(shouldContinue==true);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory+"\\src\\PersonTestData.txt");
        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : personRecords)
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
