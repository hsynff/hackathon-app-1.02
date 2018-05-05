package az.hackathon.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStaff;

    @Column(name="full_name")
    private String fullName;
    @Column(name="contact_number")
    private String contactNumber;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Role role;

    @OneToMany
    private List<Repair> repairs;



    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
