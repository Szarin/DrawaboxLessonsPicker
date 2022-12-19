package src.java;
import java.util.ArrayList;
import java.util.List;

/**
 * Number param start at 0
 */
public class Lesson {

    private List<Exercice> exercices;

    private Lesson() {}

    public Lesson(Exercice... exercices) {
        this.exercices = new ArrayList<>();
        for (Exercice exercice : exercices)
            this.exercices.add(exercice);
    }

    public void addExercice(Exercice exercice) { exercices.add(exercice); }
    public void setExercice(int number, Exercice exercice) { exercices.set(number, exercice); }
    public Exercice getExercice(int number) { return exercices.get(number); }

    public Exercice[] getExercices() { return (Exercice[])this.exercices.toArray(); }

    /**
     *
     * @param description
     * @return -1 if desc doesn't exists
     */
    public int getNumberByName(String name) {
        for (int i = 0; i < exercices.size(); i++) {
            if (exercices.get(i).name.equals(name)) {
                return i;
            }
        }

        return -1;
    }

    public int getExercicesCount() { return exercices.size(); }

    @Override
    public Lesson clone() { return this.clone(); }
}