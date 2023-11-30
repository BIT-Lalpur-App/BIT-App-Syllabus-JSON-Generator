package model;

import utils.Utils;

public record SubjectModel(
        String subjectName,
        String code,
        String shortName,
        Float credit
) {

    @Override
    public String toString() {
        return "{ \n" +
                "\"subjectName\" : \"" + subjectName + "\"" + "," +
                "\n\"code\" : \"" + code + "\"" + "," +
                "\n\"shortName\" : \"" + shortName + "\"" + "," +
                "\n\"credit\" : \"" + Utils.removeDecimalIfExist(credit) + "\"" +
                "\n}\n";

    }
}
