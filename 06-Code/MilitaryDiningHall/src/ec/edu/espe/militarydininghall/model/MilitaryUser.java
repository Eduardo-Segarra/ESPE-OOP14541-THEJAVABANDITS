package ec.edu.espe.militarydininghall.model;
/**
 *
 * @author THEJAVABANDITS, DCCO-ESPE
 */
public class MilitaryUser {
    private int id;
    private String name;
    private String email;
    private String grade;
    
    public MilitaryUser(int id, String name, String email, String grade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "MilitaryUser{" + "id=" + id + ", name=" + name + ", email=" + email + ", grade=" + grade + '}';
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
    
}
