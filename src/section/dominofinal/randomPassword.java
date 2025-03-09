package section.dominofinal;

//უნდა დააგენერირო 10 დან 15 მდე შემთხვევითი ზომის სტრინგი სადაც იქნება რიცხვები დიდი და პატარა ასოები და სპეციალური სიმბოლოები
//აუცილებლად უნდა იყოს სტრინგში ერთი დიდი ასო და ერთი პატარა ასო და ერთი რიცხვი და ორი სიმბოლო


import acm.program.ConsoleProgram;

import java.util.random.RandomGenerator;

public class randomPassword extends ConsoleProgram {
    RandomGenerator rgen = RandomGenerator.getInstance();

    private String getPassword() {
        String str = "";
        int length = rgen.nextInt(10, 16);
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz1234567890!@#$%";
        for(int i = 0; i < length; i++){
            int curIndex = rgen.nextInt(0, chars.length());
            String ch = chars.substring(curIndex, curIndex + 1);
            char curc = ch.charAt(0);
            str += curc;
        }

        int index1 = rgen.nextInt(0, str.length());
        int index2, index3, index4, index5;
        index2 = index3 = index4 = index5 = index1;

        while(index2 == index1){
            index2 = rgen.nextInt(0, str.length());
        }
        while (index3 == index2 || index3 == index1){
            index3 = rgen.nextInt(0, str.length());
        }
        while(index4 == index1 || index4 == index2 || index4 == index3){
            index4 = rgen.nextInt(0, str.length());
        }
        while(index5 == index1 || index5 == index2 || index5 == index3 || index5 == index4){
            index5 = rgen.nextInt(0, str.length());
        }

        int bigChIndex = rgen.nextInt(0, 26);
        char bigCh = (char) ('A' + bigChIndex);

        int cmallChIndex = rgen.nextInt(0, 26);
        char smallCh = (char)('a' + cmallChIndex);
        int num = rgen.nextInt(0, 10);
        char numC = (char)('0' + num);
        String symbols = "!@#$%";
        int symbIndex = rgen.nextInt(0, symbols.length());
        char symb = symbols.charAt(symbIndex);
        int symbIndex2 = rgen.nextInt(0, symbols.length());
        char symb2 = symbols.charAt(symbIndex2);


        str = str.substring(0, index1) + bigCh + str.substring(index1 + 1);
        str = str.substring(0, index2) + smallCh + str.substring(index2 + 1);
        str = str.substring(0, index3) + numC + str.substring(index4 + 1);
        str = str.substring(0, index4) + symb + str.substring(index4 + 1);
        str = str.substring(0, index5) + symb2 + str.substring(index5 + 1);

        return str;
    }
}