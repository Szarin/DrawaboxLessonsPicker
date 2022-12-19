package src.java;

import java.util.Random;

public class Picker {

    private Lesson lesson;
    private Random rand;
    protected Parameters parameters;

    public Picker(Lesson lesson) {
        this.lesson = lesson;
        rand = new Random();

        if (lesson == null) { parameters = new Parameters(0); } 
        else {
            int exercicesCount = lesson.getExercicesCount();
            parameters = new Parameters(exercicesCount);
        }
    }

    public void setLesson(Lesson lesson) { 
        this.lesson = lesson;
        int exercicesCount = lesson.getExercicesCount();
        parameters = new Parameters(exercicesCount);
    }
    public Lesson getLesson() { return this.lesson; }

    public String selectExercice() {
        if (parameters.isAllThresholdReached()) parameters.resetSelectionNumber();

        int selectNumber = 0;
        do {
            do {
                selectNumber = select();
            } while (!parameters.isActiveExercice(selectNumber));
        } while (parameters.isThresholdReached(selectNumber));
        parameters.selectionNumber[selectNumber]++;

        Exercice selectedExercice = lesson.getExercice(selectNumber);

        return (selectedExercice.isLinked()) ? (String.format("%s (%s)", selectedExercice.name, selectedExercice.getLink())) : selectedExercice.name;
    }

    private int select() {
        return rand.nextInt(lesson.getExercicesCount());
    }

    public void loadParameters(Parameters parameters) throws IllegalAccessException {
        this.parameters.loadParameters(parameters);
    }

    public void printParameters(boolean selectionNumber, boolean numbersThreshold, boolean isActiveExercice) {
        if (selectionNumber) printParameter(parameters.selectionNumber, "selectionNumber");
        if (numbersThreshold) printParameter(parameters.numbersThreshold, "numbersThreshold");
        if (isActiveExercice) printParameter(parameters.isActiveExercices, "isActiveExercices");
    }

    private void printParameter(int[] parameter, String name) {
        System.out.println(name + ":");
        for (int i = 0; i < parameter.length; i++) {
            System.out.println("\t" + lesson.getExercice(i).name + ": " + parameter[i]);
        }
    }

    private void printParameter(boolean[] parameter, String name) {
        System.out.println(name + ":");
        for (int i = 0; i < parameter.length; i++) {
            System.out.println("\t" + lesson.getExercice(i).name + ": " + parameter[i]);
        }
    }
}
