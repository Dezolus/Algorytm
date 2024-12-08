package aston.lesson03.models;



import java.util.List;


public class Student {


    private int id;
    private String firstName;
    private String lastName;

    private List<Auditorium> auditorium;

    private Lecturer lecturer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Auditorium> getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(List<Auditorium> auditorium) {
        this.auditorium = auditorium;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}
