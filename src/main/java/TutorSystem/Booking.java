package TutorSystem;


import java.io.Serializable;

public class Booking implements Serializable {
    private String bookingId;
    private String studentName;
    private String tutorName;
    private String subject;
    private String date;

    public Booking(String bookingId, String studentName, String tutorName, String subject, String date) {
        this.bookingId = bookingId;
        this.studentName = studentName;
        this.tutorName = tutorName;
        this.subject = subject;
        this.date = date;
    }

    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getTutorName() { return tutorName; }
    public void setTutorName(String tutorName) { this.tutorName = tutorName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
