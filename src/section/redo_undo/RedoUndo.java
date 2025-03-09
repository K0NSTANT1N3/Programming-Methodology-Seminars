package section.redo_undo;

import acm.program.ConsoleProgram;

import java.util.ArrayList;

public class RedoUndo extends ConsoleProgram implements Undoer {

    ArrayList<String> texts;
    int curInt;

    public RedoUndo() {
       texts = new ArrayList<>();
       curInt = -1;
    }


    @Override
    public String get() {
        if(curInt == -1){
            return "";
        }
        return texts.get(curInt);
    }

    @Override
    public void save(String text) {
       if(curInt < texts.size()-1){
           while(curInt != texts.size()-1){
               texts.remove(texts.size()-1);
           }
       }
       curInt += 1;
       texts.add(text);
    }

    @Override
    public void undo() {
        if (curInt > 0) {
            curInt -= 1;
        }
    }
    @Override
    public void redo() {
      if(curInt != -1 && curInt <= texts.size()-1 ){
          curInt += 1;
      }
    }

    @Override
    public void clear() {
       texts.clear();
       curInt = -1;
    }
}
