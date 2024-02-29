package exams.finaleExamProblem.version1.gallery;

import acm.program.ConsoleProgram;

public class DecodeText extends ConsoleProgram {

    public void run(){
        String encodedText = readLine();
        String chiper = readLine();
        String text = decode(encodedText, chiper);
        print(text);
    }

    private String decode(String encodedText, String cipher) {
        String decodedText = "";
        for (int i = 0; i < encodedText.length(); i++) {
            char c = encodedText.charAt(0);
            int numC = cipher.indexOf(c);
            char decodeC = (char) (numC + 'a');
            decodedText += decodeC;
        }
        return decodedText;
    }
}
