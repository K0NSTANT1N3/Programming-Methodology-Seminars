package assignments.nameSurfer;/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

public class NameSurferDataBase implements NameSurferConstants {
    BufferedReader reader;

    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        FileReader fileReader;
        try {
            String src = "src/assignments.nameSurfer/" + filename; //modify based on hierarchy of your code
            fileReader = new FileReader(src);
            this.reader = new BufferedReader(fileReader);
            this.reader.mark(200000);
        } catch (IOException e) {
            System.out.println(" Fore some reason file is not found");

            throw new RuntimeException(e);
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        try {
            reader.reset();    // Reset the reader to the beginning of the file

            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line as needed
                StringTokenizer token = new StringTokenizer(line);
                if (token.hasMoreTokens() && Objects.equals(token.nextToken(), name)) {
                    return new NameSurferEntry(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

