package exams.finaleExamProblem;

import acm.program.ConsoleProgram;
import exams.finaleExamProblem.exam2020.s1.Ex1;
import exams.finaleExamProblem.exam2022_2023.undo_redo.RedoUndo;
import exams.finaleExamProblem.version1.domino.Domino;
import exams.finaleExamProblem.version1.domino.Title;
import exams.finaleExamProblem.version1.final_exam.Transition;

public class tester extends ConsoleProgram {
    @Override
    public void run(){

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

    private void testDomino(){
        Domino domino = new Domino();
        print(domino.simulateDomino(1000000));
    }

    private void testTransition(){
        Transition transition = new Transition();
        System.out.println(transition.blowup("a3t0t0x45n1k09"));
    }

    private void testEx1(){
        Ex1 obj = new Ex1();
        System.out.println(obj.rearrange("to be or not to be"));
    }
}
