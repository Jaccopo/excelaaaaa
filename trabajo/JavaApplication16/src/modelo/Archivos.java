package modelo;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Archivos {

    public static String[] getArchivo(String dir) {
        String data = "";
        String construir="";
        try {
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                char buscarComa[] =myReader.nextLine().toCharArray();
                for (int i = 0; i < buscarComa.length; i++) {
                    if (buscarComa[i]==',') {
                        construir += '.';
                    }else{
                        construir += buscarComa[i];
                    }
                }
                data += construir+";";
                construir="";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data.split(";");
    }
}
