import java.time.LocalDate;
import java.util.Objects;

public record Student(
        String studentId,
        String fullName,
        String email,
        LocalDate dob,
        Department department,
        int yearOfStudy
) {
    public Student {
        Objects.requireNonNull(studentId, "Student ID cannot be null");
        Objects.requireNonNull(fullName, "Full name cannot be null");

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (yearOfStudy < 1 || yearOfStudy > 4) {
            throw new IllegalArgumentException("Year of study must be 1-4");
        }
    }

    public String getAdmissionYear() {
        return "20" + studentId.substring(0, 2);
    }
}