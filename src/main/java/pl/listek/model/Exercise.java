package pl.listek.model;

public class Exercise {
    private int exercise_id;
    private String title;
    private String description;

    public Exercise() {

    }

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
