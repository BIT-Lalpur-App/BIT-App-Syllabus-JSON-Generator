package model;

import java.util.List;

public record JsonFileModel(
        String id,
        List<SubjectModel> theory,
        List<SubjectModel> lab,
        List<SubjectModel> pe
) {
    @Override
    public String toString() {
        return "{\n" +
                "\"semester\" : {\n" +
                "\"id\" : \"" + id + "\",\n" +
                "\"subjects\" : {\n" +

                "\"theory\" : " + utils.Utils.convertListToArray(theory) + ",\n" +
                "\"lab\" : " + utils.Utils.convertListToArray(lab) + ",\n" +
                "\"pe\" : " + utils.Utils.convertListToArray(pe) + "\n" +

                "}\n}\n}";


    }
}
