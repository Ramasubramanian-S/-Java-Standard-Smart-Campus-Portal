import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Department cse = new CSE();
        Department aids = new AIDS();
        Department ece = new ECE();


        Student student1 = new Student(
                "23CSE101",
                "Rahul Sharma",
                "rahulSharma@gmail.com",
                LocalDate.of(2001, 3, 12),
                cse,
                2
        );

        Student student2 = new Student(
                "24AIDS002",
                "Priya Patel",
                "priyapatel@yahoo.com",
                LocalDate.of(2002, 7, 25),
                aids,
                1
        );


        System.out.println("=== Engineering Students ===");
        printStudentDetails(student1);
        printStudentDetails(student2);


        Student student3 = new Student(
                "22ECE033",
                "Amit Kumar",
                "amit@outlook.com",
                LocalDate.of(2000, 11, 5),
                ece,
                3
        );
        printStudentDetails(student3);
    }

    private static void printStudentDetails(Student student) {
        System.out.println("\nID: " + student.studentId());
        System.out.println("Name: " + student.fullName());
        System.out.println("DOB: " + student.dob());
        System.out.println("email: " + student.email());
        System.out.println("Department: " + student.department().name() + " (" + student.department().code() + ")");
        System.out.println("Year: " + student.yearOfStudy());
        System.out.println("Admission: " + student.getAdmissionYear());
    }
}
