


  
import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int roll;
    double score;

    public Student(String name, int roll, double score) {
        this.name = name;
        this.roll = roll;
        this.score = score;
    }

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.score, other.score); // Ascending by score
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll: " + roll + ", Score: " + score;
    }
}

public class StudentRecordManager {
    static Scanner sc = new Scanner(System.in);
    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Record Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Sort by Score");
            System.out.println("4. Search by Roll Number");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> sortStudents();
                case 4 -> searchStudent();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    static void addStudent() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        System.out.print("Enter Score: ");
        double score = sc.nextDouble();
        studentList.add(new Student(name, roll, score));
        System.out.println("✅ Student added.");
    }

    static void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }
        System.out.println("\n--- Student Records ---");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    static void sortStudents() {
        Collections.sort(studentList);
        System.out.println("✅ Sorted by score.");
    }

    static void searchStudent() {
        if (studentList.isEmpty()) {
            System.out.println("No records available.");
            return;
        }

        System.out.print("Enter roll number to search: ");
        int roll = sc.nextInt();

        // Sort first by roll number before binary search
        studentList.sort(Comparator.comparingInt(s -> s.roll));

        int index = binarySearchByRoll(roll);
        if (index == -1)
            System.out.println("❌ Student not found.");
        else
            System.out.println("✅ Found: " + studentList.get(index));
    }

    static int binarySearchByRoll(int roll) {
        int left = 0, right = studentList.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midRoll = studentList.get(mid).roll;

            if (midRoll == roll)
                return mid;
            else if (midRoll < roll)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }
}
