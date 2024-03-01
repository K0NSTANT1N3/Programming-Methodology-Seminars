package exams.finaleExamProblem;

import acm.program.ConsoleProgram;
import exams.finaleExamProblem.exam2020.s1.Ex1;
import exams.finaleExamProblem.exam2022_2023.undo_redo.RedoUndo;
import exams.finaleExamProblem.version1.domino.Domino;
import exams.finaleExamProblem.version1.domino.Title;
import exams.finaleExamProblem.version1.final_exam.Transition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class tester extends ConsoleProgram {
    @Override
    public void run() {
        testSorting();

    }

    private void testUndoRedo() {
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

    private void testTitle() {
        Title ttl = new Title();
        String str = "heLLo NiggGeri frienD";
        println(ttl.translateToTitle(str));
    }

    private void testDomino() {
        Domino domino = new Domino();
        print(domino.simulateDomino(1000000));
    }

    private void testTransition() {
        Transition transition = new Transition();
        System.out.println(transition.blowup("a3t0t0x45n1k09"));
    }

    private void testEx1() {
        Ex1 obj = new Ex1();
        System.out.println(obj.rearrange("to be or not to be"));
    }

    private void testSorting() {
        ArrayList<String> lst = new ArrayList<>();
        lst.add("cbdc");
        lst.add("efgh");
        lst.add("aaaaaa");
        lst.add("tyyyt");


        System.out.println(lst);
        lst.sort((a,b) -> {
            Set<Character> aset = new HashSet<>();
            Set<Character> bset = new HashSet<>();

            for(int i = 0; i < a.length(); i++)aset.add(a.charAt(i));
            for(int i = 0; i < b.length(); i++)bset.add(b.charAt(i));

            if(aset.size() > bset.size())return 1;
            if(aset.size() < bset.size())return -1;
            else return 0;
        });
        System.out.println(lst);

    }
}
