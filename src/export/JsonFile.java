package export;

import model.JsonFileModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class JsonFile {

    private static JsonFile instance = null;

    public static JsonFile getInstance(
            String course,
            int sem
    ) {
        synchronized (JsonFile.class) {
            if (instance == null) {
                instance = new JsonFile(course, sem);
                return instance;
            }
            return instance;
        }
    }


    private FileWriter jsonWriter;
    private FileWriter markDownWriter;

    private String course;
    private int sem;
    private String basePath;


    // making constructor private
    private JsonFile(String course, int sem) {
        this.course = course;
        this.sem = sem;
        this.basePath = "exportedData" + "/" + course + "/" + course + sem;
        try {
            createDirectories();
            jsonWriter = new FileWriter(this.basePath + "/" + this.course + this.sem + ".json");
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private void createDirectories() {
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (dir.mkdirs())
                System.out.println("Directory created successfully.");
            else
                System.out.println("Failed to create directory!");
        }
    }


    public int exportJson(JsonFileModel exportData) throws NullPointerException {
        if (instance == null) {
            throw new NullPointerException("Instance is destroyed !!");
        }
        try {
            jsonWriter.append(exportData.toString());
            createMarkDownFiles(exportData);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            return -1;
        }
        return 1;
    }

    private void createMarkDownFiles(JsonFileModel data) {
        var allFile = data.theory();
        allFile.addAll(data.lab());
        allFile.addAll(data.pe());
        var path = createMarkDownDir();
        allFile.forEach(ele -> {
            try {
                markDownWriter = new FileWriter(path + "/" + ele.subjectName() + ".md");
                markDownWriter.append("## ").append(ele.subjectName());
                markDownWriter.flush();
                markDownWriter.close();
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        });
    }

    private String createMarkDownDir() {
        var path = this.basePath + "/" + "subjects";
        var dir = new File(path);
        if (!dir.exists())
            if (dir.mkdirs()) {
                System.out.println("Picture folder created!!");
            } else
                System.out.println("Failed to create subject directory!");

        var picturePath = path + "/" + "picture";
        var pictureDir = new File(picturePath);
        if (!pictureDir.exists()) {
            if (pictureDir.mkdirs()) {
                System.out.println("Picture folder created!!");
            } else {
                System.out.println("Failed to create picture directory!");
            }
        }
        return path;
    }


    public void destroy() throws NullPointerException {
        if (instance == null) {
            throw new NullPointerException("Instance is destroyed !!");
        }
        try {
            jsonWriter.flush();
            jsonWriter.close();
            instance = null;
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
