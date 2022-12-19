package src.java;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

public class Main {

    public static String RESOURCES_PATH = "..\\ressources";
    public static String PARAMETERS_PATH = RESOURCES_PATH + "\\Parameters.json";
    public static String EXERCICES_PATH = RESOURCES_PATH + "\\Exercices.json";
    
    public static void main(String[] args) throws IOException, ParseException, IllegalAccessException {

        createDataDirectory();
        Parameters[] allParameters = parseParameters();

        //Exercices data init
        String[] name1 = new String[] {
            "Surimposed Lines", "Ghosted Lines", "Ghosted Planes",
            "Tables of Ellipses", "Ellipses in Planes", "Funnels", "Rotated Boxes", 
            "Organic Perspective"
        };

        String[] name2 = new String[] {
            "Organic Arrows", "Organic Forms with Contour Lines",
            "Texture Analysis", "Dissections", "Forms Intersections", 
            "Organic Intersections"
        };

        String[] name3 = new String[] {
            "Organic Arrows", "Leaves", "Branches", "Plant Construction"
        };

        String[] link1 = new String[] { 
            "https://drawabox.com/lesson/1/superimposedlines", "https://drawabox.com/lesson/1/ghostedlines",
            "https://drawabox.com/lesson/1/ghostedplanes", "https://drawabox.com/lesson/1/tablesofellipses",
            "https://drawabox.com/lesson/1/ellipsesinplanes", "https://drawabox.com/lesson/1/funnels",
            "https://drawabox.com/lesson/1/plottedperspective", "https://drawabox.com/lesson/1/roughperspective",
            "https://drawabox.com/lesson/1/rotatedboxes", "https://drawabox.com/lesson/1/organicperspective" };

        String[] link2 = new String[] {
            "https://drawabox.com/lesson/2/organicarrows", "https://drawabox.com/lesson/2/contourlines",
            "https://drawabox.com/lesson/2/textureanalysis", "https://drawabox.com/lesson/2/dissections",
            "https://drawabox.com/lesson/2/formintersections", "https://drawabox.com/lesson/2/organicintersections"
        };

        String[] link3 = new String[] {
            "https://drawabox.com/lesson/2/organicarrows", "https://drawabox.com/lesson/3/2/exerciseleaves",
            "https://drawabox.com/lesson/3/2/exercisebranches", "See Goole image, Pinterest,... otherwise https://drawabox.com/lesson/3"
        };

        // Exercice init
        List<Exercice[]> allExercices = new ArrayList<Exercice[]>();
        allExercices.add(new Exercice[name1.length]);
        for (int i = 0; i < name1.length; i++) {
            allExercices.get(0)[i] = new Exercice(name1[i], link1[i]);
        }
        allExercices.add(new Exercice[name2.length]);
        for (int i = 0; i < name2.length; i++) {
            allExercices.get(1)[i] = new Exercice(name2[i], link2[i]);
        }
        allExercices.add(new Exercice[name3.length]);
        for (int i = 0; i < name3.length; i++) {
            allExercices.get(2)[i] = new Exercice(name3[i], link3[i]);
        }

        // Lesson init
        List<Lesson> lessons = new ArrayList<>();
        for (Exercice[] exercices : allExercices)
            lessons.add(new Lesson(exercices));

        // Picker init and select
        List<Picker> pickers = new ArrayList<>();
        for (int i = 0; i < lessons.size(); i++) {
            Picker picker = new Picker(lessons.get(i));
            if (allParameters != null) {
                picker.loadParameters(allParameters[i]);
            }
            System.out.println("Lesson " + (i + 1) + ": " + picker.selectExercice());
            picker.printParameters(true, false, false);
            System.out.println();
            pickers.add(picker);
        };
        
        scanData(pickers);
        // waitEnterKey();
    }

    private static void createDataDirectory() {
        File dataDir = new File(RESOURCES_PATH);
        if (!dataDir.exists()) dataDir.mkdirs();
    }

    private static Parameters[] parseParameters() throws IOException, ParseException {
        File f = new File(PARAMETERS_PATH);
        if(f.exists() && !f.isDirectory()) { 
            PickerParser parser = new PickerParser(PARAMETERS_PATH);
            return parser.getParameters();
        }
        return null;
    }

    private static void scanData(List<Picker> pickers) throws IOException {
        PickerScanner scan = new PickerScanner(ScanType.JSON, pickers);
        scan.scan(RESOURCES_PATH);
    }

    private static void waitEnterKey() {
        System.out.print("Press Enter to exit");
        Scanner readKey = new Scanner(System.in);
        while (readKey.nextLine() != "") { continue; }
        readKey.close();
    }
}