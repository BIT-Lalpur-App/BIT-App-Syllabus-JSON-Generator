import export.JsonFile;
import model.JsonFileModel;
import model.SubjectModel;
import utils.Utils;

import java.util.*;

import static utils.Utils.findSubject;

/**
 * Code to generate bit app syllabus skeleton
 *
 * @author aiyu
 */

public class Main {
    private static Scanner scanner;
    private static JsonFile jsonFile;

    private static String course;
    private static int sem;
    private static List<SubjectModel> theory;
    private static List<SubjectModel> lab;
    private static List<SubjectModel> pe;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.print("Enter the course name : ");
        course = scanner.nextLine();
        System.out.print("Enter the semester : ");
        sem = scanner.nextInt();
        jsonFile = JsonFile.getInstance(course, sem);
        initializingList();
        menu();
    }

    private static void initializingList() {
        theory = new ArrayList<>();
        lab = new ArrayList<>();
        pe = new ArrayList<>();
    }

    private static void menu() {
        while (true) {
            System.out.println("""
                    1. Theory Subject
                    2. Lab Subject
                    3. PE Subject
                    4. Export Json File
                    5. Exit
                                    
                    Enter your choice :\s
                    """.trim());
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice !!");
            }
            switch (choice) {
                case 1 -> handleIUR(Utils.SubjectType.THEORY);
                case 2 -> handleIUR(Utils.SubjectType.LAB);
                case 3 -> handleIUR(Utils.SubjectType.PE);
                case 4 -> exportJsonFile();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice !!");
            }
        }
    }

    private static void handleIUR(Utils.SubjectType type) {
        boolean isContinue = true;
        while (isContinue) {
            System.out.println(type.name());
            System.out.println("""
                    1. Insert
                    2. Update
                    3. Remove
                    4. Display
                    5. Back
                    """);
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice !!");
            }
            switch (choice) {
                case 1 -> insertSubject(type);
                case 2 -> updateSubject(type);
                case 3 -> removeSubject(type);
                case 4 -> displaySubject(type);
                case 5 -> isContinue = false;
                default -> System.out.println("Invalid choice !!");
            }
        }
    }

    private static void displaySubject(Utils.SubjectType type) {
        if (type == Utils.SubjectType.THEORY) {
            theory.forEach(System.out::println);
        } else if (type == Utils.SubjectType.LAB) {
            theory.forEach(System.out::println);
        } else {
            theory.forEach(System.out::println);
        }
    }

    private static void insertSubject(Utils.SubjectType type) {
        System.out.print("Enter the subject name : ");
        var name = new Scanner(System.in).nextLine().trim();
        // Check for a special condition and return if necessary
        if ("//".equals(name)) {
            return;
        }
        System.out.print("Enter the subject code : ");
        var code = new Scanner(System.in).next().toUpperCase(Locale.ROOT);
        var credit = getCredit("Enter the subject credit : ");
        var shortName = Utils.getShortName(name);
        var subjectModel = new SubjectModel(name, code, shortName, credit);
        if (type == Utils.SubjectType.THEORY) {
            theory.add(subjectModel);
        } else if (type == Utils.SubjectType.LAB) {
            lab.add(subjectModel);
        } else {
            pe.add(subjectModel);
        }
    }

    private static float getCredit(String s) {
        try {
            System.out.print(s);
            var cr = scanner.nextFloat();
            return cr;
        } catch (InputMismatchException exception) {
            System.out.println("Invalid credit !!");
            return 1.0f;
        }
    }

    private static void updateSubject(Utils.SubjectType type) {
        System.out.println("Enter the subject code : ");
        var code = scanner.next();
        if (code.equals("//"))
            return;
        if (type == Utils.SubjectType.THEORY) {
            var subject = findSubject(theory, code);
            if (subject == null) System.out.println("Subject not found !!");
            else askUpdatePart(theory, subject);
        } else if (type == Utils.SubjectType.LAB) {
            var subject = findSubject(lab, code);
            if (subject == null) System.out.println("Subject not found !!");
            else askUpdatePart(lab, subject);
        } else {
            var subject = findSubject(pe, code);
            if (subject == null) System.out.println("Subject not found !!");
            else askUpdatePart(pe, subject);
        }

    }

    private static void askUpdatePart(List<SubjectModel> list, Utils.Pair<SubjectModel, Integer> pair) {
        System.out.println("""
                1.Update subject name
                2.Update subject code
                3.Update subject credit
                """);
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid choice !!");
        }
        switch (choice) {
            case 1 -> {
                System.out.print("Enter the new subject name : ");
                var name = scanner.nextLine();
                Utils.updateList(list,
                        pair.getSecond(),
                        new SubjectModel(name, pair.getFirst().code(),
                                pair.getFirst().shortName(),
                                pair.getFirst().credit()));
            }
            case 2 -> {
                System.out.print("Enter the new subject code : ");
                var code = scanner.next().toUpperCase(Locale.ROOT);
                Utils.updateList(list,
                        pair.getSecond(),
                        new SubjectModel(pair.getFirst().subjectName(),
                                code, pair.getFirst().shortName(),
                                pair.getFirst().credit()));
            }
            case 3 -> {
                var credit = getCredit("Enter the new subject credit : ");
                Utils.updateList(list,
                        pair.getSecond(),
                        new SubjectModel(pair.getFirst().subjectName(),
                                pair.getFirst().code(),
                                pair.getFirst().shortName(), credit));
            }
            default -> System.out.println("Invalid choice !!");
        }
    }


    private static void removeSubject(Utils.SubjectType type) {
        System.out.println("Enter the subject code : ");
        var code = scanner.next();
        if (type == Utils.SubjectType.THEORY) {
            var subject = findSubject(theory, code);
            if (subject == null) System.out.println("Subject not found !!");
            else theory.remove(subject.getSecond().intValue());
        } else if (type == Utils.SubjectType.LAB) {
            var subject = findSubject(lab, code);
            if (subject == null) System.out.println("Subject not found !!");
            else lab.remove(subject.getSecond().intValue());
        } else {
            var subject = findSubject(pe, code);
            if (subject == null) System.out.println("Subject not found !!");
            else pe.remove(subject.getSecond().intValue());
        }
    }

    private static void exportJsonFile() {
        var jsonFileModel = new JsonFileModel(
                (course + sem).toLowerCase(Locale.ROOT),
                theory,
                lab,
                pe
        );
        var result = jsonFile.exportJson(jsonFileModel);
        if (result < 0) {
            System.out.println("Exported !!!!");
            jsonFile.destroy();
        } else {
            System.out.println("Error happen !!");
        }
    }


}