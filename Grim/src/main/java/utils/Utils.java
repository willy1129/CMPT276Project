package utils;
import java.io.*;

/**
 * Utilities to use in the project
 *
 * @author Sarah Li
 * @version 1.0
 * @since 1.0
 */
public class Utils {

    /**
     * Converts a text file to a string
     *
     * @param path of text file
     * @return text file converted to a string
     */
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            String editedPath = editPath(path);
            InputStream in = Utils.class.getResourceAsStream(editedPath);
            if (in == null) in = Utils.class.getResourceAsStream("/"+editedPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * Converts integers contained in strings to an int type
     *
     * @param num number as a string
     * @return number as an integer
     */
    public static int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Edits the supplied path to account for current working directory
     *
     * @param path the supplied path
     * @return the correct path
     */
    public static String editPath(String path) {
        String fullWorkingDirPath = System.getProperty("user.dir");
        String[] pathArr = path.replaceAll("\\/", "\\\\").split("\\\\");
        int i = pathArr.length - 1;
        for (; i >= 0 ; i--) {
            if (fullWorkingDirPath.contains(pathArr[i])) break;
        }

        String editedPath = "";

        if (i >= 0) {
            for (i = i + 1; i < pathArr.length - 1; i++) editedPath += pathArr[i] + "\\";
            editedPath += pathArr[pathArr.length - 1];
        } else editedPath = path;

        return editedPath;
    }
}
