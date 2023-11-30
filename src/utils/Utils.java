package utils;

import model.SubjectModel;

import java.util.List;

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
        for (String word : words) {
            shortName.append(word.charAt(0));
        }
        return shortName.toString();
    }
}
