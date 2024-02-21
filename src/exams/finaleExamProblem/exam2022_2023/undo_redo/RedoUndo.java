package exams.finaleExamProblem.exam2022_2023.undo_redo;

import acm.program.ConsoleProgram;

import java.util.ArrayList;

public class RedoUndo extends ConsoleProgram implements Undoer {
    private ArrayList<String> data;
    int curIndex;

    public RedoUndo() {
        data = new ArrayList<>();
        curIndex = -1;
    }


    @Override
    public String get() {
        return curIndex >= 0 ? data.get(curIndex) : "";
    }

    @Override
    public void save(String text) {
        curIndex++;
        while (data.size() > curIndex) {
            data.remove(curIndex);
        }
        data.add(text);
    }

    @Override
    public void undo() {
        curIndex = curIndex > 0 ? curIndex - 1 : curIndex;
    }

    @Override
    public void redo() {
        if (data.size() > curIndex + 1) {
            curIndex++;
        }
    }

    @Override
    public void clear() {
        data.clear();
        curIndex = -1;
    }
}
