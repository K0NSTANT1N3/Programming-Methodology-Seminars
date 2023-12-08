import acm.program.ConsoleProgram;

public class SubStringContainer extends ConsoleProgram {
    public void run(){
        String father = readLine();
        String son = readLine();

        println(isFather(father, son));
    }

    private boolean isFather(String father, String son){
        for(int i = 1; i < father.length() - 1; i++){
            String left = father.substring(0, i);
            String right = father.substring(i);

            for (int j = 0; j < left.length(); j++){
                String newLeft = left.replaceAll("" + left.charAt(j), "");

                for(int k = 0; k < right.length(); k++){
                    String newRight = right.replaceAll("" + right.charAt(k), "");

                    String newFather = newLeft + newRight;

                    if (newFather.equals(son))return true;
                }
            }
        }
        return false;
    }
}
