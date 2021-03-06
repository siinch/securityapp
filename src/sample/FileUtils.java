package sample;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;

public class FileUtils {

    // readAllBytes:
    // The method merely hides the use of Paths.

    public static byte[] readAllBytes(String inputFile) {
        byte[] bytesRead = {};
        try {
            bytesRead = Files.readAllBytes(Paths.get(inputFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesRead;
    }

    // write:
    // The method merely "hides" the use of Paths.
    // Overwrites if the file exists already; otherwise creates the file
    // This behavior is due to java.nio.file.Files.write

    public static void write(String outputFile, byte[] output) {
        try {
            Files.write(Paths.get(outputFile), output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isWriteSuccessful(String outputFile, byte[] output) {
        try {
            Files.write(Paths.get(outputFile), output);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void delete(String filePath) {
        new File(filePath).delete();
    }

    // getAllFileNamesWithExt:
    // returns a list of all filenames (strings) in a directory
    // that have the specified extension

    // getAllFileNamesWithExt/3: file names must have both a certain filename and a certain extension

    public static String[] getAllFileNames(String dir, String name, String ext) {
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String fname = file.getName();
                String[] parts = fname.split("[.]");
                if (parts[0].equals(name) && parts[parts.length-1].equals(ext)) results.add(fname);
            }
        }
        String[] names = new String[results.size()];
        for (int i=0; i<names.length; i++) {
            names[i] = results.get(i);
        }
        return names;
    }

    // getAllFileNamesWithExt/2: file names need only have a certain extension

    public static String[] getAllFileNames(String dir, String ext) {
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String fname = file.getName();
                String[] parts = fname.split("[.]");
                if (parts[parts.length-1].equals(ext)) results.add(dir + fname);
            }
        }
        String[] names = new String[results.size()];
        for (int i=0; i<names.length; i++) {
            names[i] = results.get(i);
        }
        return names;
    }

    public static String[] getAllFileNamesRecursive(String dir, String ext) {
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String fname = file.getName();
                String[] parts = fname.split("[.]");
                if (parts[parts.length-1].equals(ext)) results.add(dir + fname);
            }
            else if(file.isDirectory() && !file.isHidden()) {
                for (String name : getAllFileNamesRecursive(file.getAbsolutePath() + "/", ext))
                    results.add(name);
            }
        }

        String[] names = new String[results.size()];
        for (int i=0; i<names.length; i++) {
            names[i] = results.get(i);
        }
        return names;
    }

    public static String[] getAllFileNamesWOExt(String dir, String ext) {
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile() && !file.isHidden()) {
                String fname = file.getName();
                String[] parts = fname.split("[.]");
                if (!parts[parts.length-1].equals(ext)) results.add(dir + fname);
            }
        }
        String[] names = new String[results.size()];
        for (int i=0; i<names.length; i++) {
            names[i] = results.get(i);
        }
        return names;
    }

    public static String[] getAllFileNamesWOExtRecursive(String dir, String ext) {
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile() && !file.isHidden()) {
                String fname = file.getName();
                String[] parts = fname.split("[.]");
                if (!parts[parts.length-1].equals(ext) && !(fname.charAt(0) == '.')) results.add(dir + fname);
            }
            else if(file.isDirectory() && !file.isHidden()) {
                for (String name : getAllFileNamesWOExtRecursive(file.getAbsolutePath() + "/", ext))
                    results.add(name);
            }
        }
        String[] names = new String[results.size()];
        for (int i=0; i<names.length; i++) {
            names[i] = results.get(i);
        }
        return names;
    }

    //returns all file paths + names from the given directory
    public static String[] getAllFileNames(String dir) {
        ArrayList<String> results = new ArrayList<String>();
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String fname = file.getName();
                results.add(dir+fname);
            }
        }
        String[] names = new String[results.size()];
        for (int i=0; i<names.length; i++) {
            names[i] = results.get(i);
        }
        return names;
    }

    public static void makeDir (String dirName) {
        File dir = new File(dirName);
        dir.mkdir();
    }

    public static boolean dirContainsFileName(String dir, String fileName) {
        File[] files = new File(dir).listFiles();
        for (File file : files) {
            if (file.isFile() && (file.getName().equals(fileName))) {
                return true;
            }
        }

        return false;
    }
}
