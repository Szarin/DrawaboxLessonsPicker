package src.java;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;
import java.util.List;

public final class PickerScanner extends Picker {

    private ScanType type;
    private Picker[] selectors;

    private PickerScanner() { super(null); }
    public PickerScanner(ScanType type, Picker... selectors) {
        this();
        this.type = type;
        this.selectors = (Picker[]) selectors;
    } 

    public PickerScanner(ScanType type, List<Picker> selectors) {
        this();
        this.type = type;
        this.selectors = new Picker[selectors.size()];
        for (int i = 0; i < selectors.size(); i++) {
            this.selectors[i] = selectors.get(i);
        }
    }

    public void scan(String path) throws IOException {
        switch(this.type) {
            case JSON:
                scanJSON(path);
        }
    }

    private void scanJSON(String path) throws IOException {
        JSONObject json = new JSONObject();

        JSONObject selectorElements = new JSONObject();

        JSONArray selectionsNumber = new JSONArray();
        JSONArray numbersThreshold = new JSONArray();
        JSONArray activeExercices = new JSONArray();
        int lessonNumber = 1;
        for (Picker selector : this.selectors) {
            selectionsNumber = new JSONArray();
            for (Integer selectionNumber : selector.parameters.selectionNumber) {
                selectionsNumber.add(selectionNumber);
            }
            selectorElements.put("selectionsNumber", selectionsNumber);

            numbersThreshold = new JSONArray();
            for (Integer numberThreshold : selector.parameters.numbersThreshold) {
                numbersThreshold.add(numberThreshold);
            }
            selectorElements.put("numbersThreshold", numbersThreshold);

            activeExercices = new JSONArray();
            for (Boolean activeExercice : selector.parameters.isActiveExercices) {
                activeExercices.add(activeExercice);
            }
            selectorElements.put("activeExercices", activeExercices);

            json.put("Lesson " + lessonNumber, selectorElements);
            
            selectorElements = new JSONObject();    

            lessonNumber++;
        }   

        FileWriter file = new FileWriter(path + "\\Parameters.json");
        file.write(json.toJSONString());
        file.flush();
        file.close();
    }
}