package seminarProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class School {

    private HashMap<String, ArrayList<String>> teacherToSubject;
    private HashMap<String, ArrayList<String>> subjectToTeacher;
    private HashMap<String, ArrayList<String>> subjectToStudent;
    private HashMap<String, ArrayList<String>> studentToSubject;


    public School() {
        teacherToSubject = new HashMap<>();
        subjectToTeacher = new HashMap<>();
    }

    public void addTeacher(String teacher) {
        teacherToSubject.put(teacher, new ArrayList<>());
    }

    public void addSubject(String teacher, String subject) {
        boolean exists = false;
        for (String str : teacherToSubject.keySet()) {
            if (str.equals(teacher)) {
                teacherToSubject.get(str).add(subject);
                exists = true;
            }
        }
        if (!exists) return;
        exists = false;

        for (String sbj : subjectToTeacher.keySet()) {
            if (sbj.equals(subject)) {
                subjectToTeacher.get(sbj).add(teacher);
                exists = true;
            }
        }
        if (!exists) {
            ArrayList<String> teachers = new ArrayList<>();
            teachers.add(teacher);

            subjectToTeacher.put(subject, teachers);
        }
    }

    public void addPupil(String pupil, String subject) {
        if (!subjectToStudent.containsKey(subject)) {
            ArrayList<String> students = new ArrayList<>();
            students.add(pupil);

            subjectToStudent.put(pupil, students);
        } else {
            subjectToStudent.get(subject).add(pupil);
        }

        if (!studentToSubject.containsKey(pupil)) {
            ArrayList<String> subjects = new ArrayList<>();
            subjects.add(subject);

            studentToSubject.put(pupil, subjects);
        } else {
            studentToSubject.get(pupil).add(subject);
        }
    }

    public Iterator<String> getTeachers(String pupil) {
        ArrayList<String> subjects = studentToSubject.get(pupil);
        if (subjects == null) {
            return Collections.emptyIterator();
        }
        ArrayList<String> teachers = new ArrayList<>();
        for (String sbj : subjects) {
            for (String tch : subjectToTeacher.get(sbj)) {
                teachers.add(tch);
            }
        }
        return teachers.iterator();
    }

    public Iterator<String> getPupils(String teacher) {
        ArrayList<String> subjects = teacherToSubject.get(teacher);
        if (subjects == null) {
            return Collections.emptyIterator();
        }
        ArrayList<String> pupils = new ArrayList<>();
        for (String sbj : subjects) {
            for (String pup : subjectToStudent.get(sbj)){
                pupils.add(pup);
            }
        }
        return pupils.iterator();
    }

    public void removeTeacher(String teacher){
        teacherToSubject.remove(teacher);
        for (String key : subjectToTeacher.keySet()){
            for (String tch : subjectToTeacher.get(key)){
                if (tch.equals(teacher)){
                    subjectToTeacher.get(key).remove(tch);
                }
            }
        }
    }


}
