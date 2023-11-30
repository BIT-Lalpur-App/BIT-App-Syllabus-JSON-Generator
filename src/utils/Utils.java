package utils;

import model.SubjectModel;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public final class Utils {
    public static Number removeDecimalIfExist(float number) {
        // check number has value after . or not
        if (number % 1 == 0) {
            return (int) number;
        } else {
            return number;
        }
    }

    public static String convertListToArray(List<SubjectModel> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i).toString());
            if (i != list.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String getShortName(String subjectName) {
        String[] words = subjectName.split(" ");
        StringBuilder shortName = new StringBuilder();
        try {
            for (String word : words) {
                shortName.append(word.charAt(0));
            }
        } catch (Exception e) {
            System.out.println("Invalid subject name !!");
        }
        return shortName.toString();
    }

    public enum SubjectType {
        THEORY,
        LAB,
        PE
    }

    public static class Pair<F, S> {
        private final F f;
        private final S s;

        public Pair(F f, S s) {
            this.f = f;
            this.s = s;
        }

        public F getFirst() {
            return f;
        }

        public S getSecond() {
            return s;
        }
    }

    public static Pair<SubjectModel, Integer> findSubject(List<SubjectModel> list, String code) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).code().equals(code)) {
                return new Pair<>(list.get(i), i);
            }
        }
        return null;
    }

    public static void updateList(List<SubjectModel> list, int index, SubjectModel subjectModel) {
        list.set(index, subjectModel);
    }
}
