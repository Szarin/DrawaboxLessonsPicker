package src.java.com.lessonPicker;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PickerParser {
    private String path;
    private List<Parameters> allParameters;

    private JSONParser jsonParser;
    private FileReader fileReader;

    private PickerParser() {}
    public PickerParser(String path) throws IOException, ParseException {
        this.path = path;
        jsonParser = new JSONParser();
        fileReader = new FileReader(path);
        allParameters = new ArrayList<Parameters>();
        parse();
    }

    public void setPath(String newPath) { this.path = newPath; }
    public void parse() throws IOException, ParseException {
        Object obj = jsonParser.parse(fileReader);

        JSONObject lessons = (JSONObject) obj;
        int lessonsSize = lessons.size();

        for (int i = 0; i < lessonsSize; i++) {
            JSONObject lesson = (JSONObject)lessons.get("Lesson " + (i + 1));
            JSONArray jsonActiveExercices = (JSONArray)lesson.get("activeExercices");
            JSONArray jsonSelectionsNumber = (JSONArray)lesson.get("selectionsNumber");
            JSONArray jsonNumbersThreshold = (JSONArray)lesson.get("numbersThreshold");
            
            allParameters.add(new Parameters(
                JSONArrayToIntArray(jsonSelectionsNumber),
                JSONArrayToIntArray(jsonNumbersThreshold),
                JSONArrayToBooleanArray(jsonActiveExercices)));
        }
    }

    public Parameters[] getParameters() { return allParameters.toArray(new Parameters[allParameters.size()]); }

    private boolean[] JSONArrayToBooleanArray(JSONArray array) {
        int arraySize = array.size();
        boolean[] result = new boolean[arraySize];
        for(int i = 0; i < arraySize; i++){
            result[i] = (array.get(i).toString() == "true") ? true : false;
        }
        return result;
    }

    private int[] JSONArrayToIntArray(JSONArray array) {
        int arraySize = array.size();
        int[] result = new int[arraySize];
        for(int i = 0; i < arraySize; i++){
            result[i] = Integer.parseInt(array.get(i).toString());
        }
        return result;
    }

}
