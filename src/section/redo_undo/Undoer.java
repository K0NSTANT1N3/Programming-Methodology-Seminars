package section.redo_undo;

public interface Undoer {
    public String get();
    public void save(String text);
    public void undo();
    public void redo();
    public void clear();
}
