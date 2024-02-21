package exams.finaleExamProblem.exam2022_2023.undo_redo;

public interface Undoer {
    public String get();
    public void save(String text);
    public void undo();
    public void redo();
    public void clear();
}
