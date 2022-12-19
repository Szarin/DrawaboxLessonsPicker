package src.java.com.lessonPicker;
public class Parameters {
    public int[] selectionNumber;
    public int[] numbersThreshold;
    public boolean[] isActiveExercices;

    public Parameters(int exercicesCount) {
        this.selectionNumber = new int[exercicesCount];
        this.numbersThreshold = new int[exercicesCount];
        this.isActiveExercices = new boolean[exercicesCount];
        for (int i = 0; i < isActiveExercices.length; i++) isActiveExercices[i] = true;
        setAllSelectionNumberThreshold(1);
    }

    public Parameters(int[] selectionNumber, int[] numbersThreshold, boolean[] isActiveExercices) {
        this.selectionNumber = selectionNumber;
        this.numbersThreshold = numbersThreshold;
        this.isActiveExercices = isActiveExercices;
    }

    public void setSelectionNumberThreshold(int numberExercice, int numberThreshold) throws IllegalArgumentException {
        if (numberThreshold <= 0) throw new IllegalArgumentException("Selector class: setSelectionNumberThreshold method: numberThreshold can't be below 0.");
        numbersThreshold[numberExercice] = numberThreshold;
    }

    public void setAllSelectionNumberThreshold(int numberThreshold) throws IllegalArgumentException { 
        for (int i = 0; i < numbersThreshold.length; i++) setSelectionNumberThreshold(i, numberThreshold);
    }

    public boolean[] getRemoved() { return this.isActiveExercices; }
    public void desactiveExercice(int number) { isActiveExercices[number] = false; }
    public void activeExercice(int number) { isActiveExercices[number] = true; }

    public boolean isActiveExercice(int number) { return isActiveExercices[number]; }

    public boolean isAllThresholdReached() {
        int isThresholdReachedCount = 0;
        for (int i = 0; i < numbersThreshold.length; i++) {
            if (isThresholdReached(i)) {
                isThresholdReachedCount++;
            }
        }
        return (numbersThreshold.length == isThresholdReachedCount) ? true : false;
    }

    public boolean isThresholdReached(int number) {
        if (selectionNumber[number] >= numbersThreshold[number]) return true;
        return false;
    }

    public void resetSelectionNumber() {
        for (int i = 0; i < selectionNumber.length; i++) selectionNumber[i] = 0;
    }

    public void loadParameters(Parameters parameters) throws IllegalAccessException {
        loadParameters(parameters.selectionNumber, parameters.numbersThreshold, parameters.isActiveExercices);
    }

    public void loadParameters(int[] selectionNumber, int[] numbersThreshold, boolean[] isActiveExercices) throws IllegalAccessException {
        if (selectionNumber == null || numbersThreshold == null || isActiveExercices == null)
            throw new IllegalAccessException("Picker class: loadParameters method: arguments cannot be null.");
        this.selectionNumber = selectionNumber;
        this.numbersThreshold = numbersThreshold;
        for (int i = 0; i < isActiveExercices.length; i++) {
            if (!isActiveExercices[i]) {
                desactiveExercice(i);
            }
        }
    }
}
