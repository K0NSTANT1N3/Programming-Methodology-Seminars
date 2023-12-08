import acm.program.ConsoleProgram;

import java.util.ArrayList;

public class Problem60 extends ConsoleProgram {
    public void run(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int a = readInt();
        while(a != -1){
            list.add(a);
            a = readInt();
        }

        for (int i = list.size() - 1; i >= 0; i--){
            if (list.get(i) == 1){
                list.remove(i);
            }
        }
        println(list);
    }

}
