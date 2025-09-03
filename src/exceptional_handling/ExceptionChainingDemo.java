package exceptional_handling;

import java.io.*;

public class ExceptionChainingDemo {

    public static void readFile(String fileName) throws Exception {
        try {

            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            System.out.println("File Content: " + line);

            br.close();
            fr.close();

        } catch (IOException ioEx) {

            throw new Exception("Error occurred while reading file", ioEx);
        }
    }

    public static void main(String[] args) {
        try {
            readFile("non_existing_file.txt"); // This file does not exist
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e);

            Throwable cause = e.getCause();
            if (cause != null) {
                System.out.println("Cause of Exception: " + cause);
            }
        }
    }
}

