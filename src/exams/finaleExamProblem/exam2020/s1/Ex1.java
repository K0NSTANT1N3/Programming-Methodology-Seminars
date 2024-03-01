/*-
 * თქვენი ამოცანაა დაწეროთ rearrange მეთოდი,
 * რომელსაც გადაეცემა წინადადება და რომელიც აბრუნებს გადალაგებულ ტექსტს.
 *
 * ჩათვალეთ, რომ წინადადება არის პატარა ლათინური ანბანით და სფეისებით შედგენილი სტრინგი.
 * თქვენი ამოცანაა გადაცემულ წინადადებაში გადაალაგოთ სიტყვები მათი სიგრძის ზრდის მიხედვით.
 * პირველი სიტყვა უნდა იყოს ყველაზე მოკლე, მეორე სიგრძით შემდეგი და ა.შ. ბოლო სიტყვა ყველაზე გრძელი.
 * თუკი ორი სიტყვა ერთიდაიგივე სიგრძისაა, მაშინ მათი მიმდერობა არ უნდა შეიცვალოს
 * (საწყის წინადადებაში უფრო ადრე რომელიც გვხვდებოდა ისევ ის უნდა შეგვხვდეს შედეგშიც).
 * მეთოდმა უნდა დააბრუნოს გადალაგებული წინადადება. მაგალითები:
 * 	“keep calm and code on” -> “on and keep calm code”
 * 	“to be or not to be” -> “to be or to be not”
 * 	“exams rule” -> “rule exams”
 */

package exams.finaleExamProblem.exam2020.s1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Ex1 {
    public String rearrange(String s) {
        StringTokenizer tkn = new StringTokenizer(s, " ");
        ArrayList<String> lst = new ArrayList<>();
        while (tkn.hasMoreTokens()) {
            lst.add(tkn.nextToken());
        }

        lst.sort(Comparator.comparingInt(String::length));

        String ans = "";
        for(String str : lst){
            ans += str;
            ans += " ";
        }
        return ans;
    }
}
