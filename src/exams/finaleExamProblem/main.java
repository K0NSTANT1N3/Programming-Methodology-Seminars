package exams.finaleExamProblem;

import acm.program.ConsoleProgram;
import exams.finaleExamProblem.domino.Title;
import exams.finaleExamProblem.exam2022_2023.undo_redo.RedoUndo;

public class main extends ConsoleProgram {
    public void run(){
        testTitle();

    }

    private void testUndoRedo(){
        RedoUndo a = new RedoUndo();
        a.save("1");
        a.save("2");
        a.save("3");
        a.undo();
        a.undo();
        println(a.get());
        a.redo();
        println(a.get());
        a.save("4");
        println(a.get());
        a.undo();
        println(a.get());
        a.clear();
        println(a.get());
    }

    private void testTitle(){
        Title ttl = new Title();
        String str = "heLLo NiggGeri frienD";
        println(ttl.translateToTitle(str));
    }
}
