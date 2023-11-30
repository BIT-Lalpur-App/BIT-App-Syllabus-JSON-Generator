import model.JsonFileModel;
import model.SubjectModel;
import utils.Utils;

import java.util.ArrayList;

/**
 * Code to generate bit app syllabus skeleton
 *
 * @author aiyu
 */

public class Main {
    public static void main(String[] args) {
        var jsonFileModel = new JsonFileModel("MCA",
                new ArrayList<>() {{
                    add(new SubjectModel("Computer Organization and Architecture", "MCA-101", Utils.getShortName("Computer Organization and Architecture"), 4.0f));
                    add(new SubjectModel("Computer Organization and Architecture", "MCA-101", Utils.getShortName("Computer Organization and Architecture"), 4.0f));
                }}
                , new ArrayList<>() {{
            add(new SubjectModel("Lab1", "MCA-101", Utils.getShortName("Lab1"), 1.5f));
            add(new SubjectModel("Lab2", "MCA-101", Utils.getShortName("Lab2"), 1.5f));
        }}
                , new ArrayList<>() {{
            add(new SubjectModel("Project1", "MCA-101", Utils.getShortName("Project1"), 4.0f));
            add(new SubjectModel("Project2", "MCA-101", Utils.getShortName("Project2"), 4.0f));
        }});
        System.out.println(jsonFileModel);
    }
}