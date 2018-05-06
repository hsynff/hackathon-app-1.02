package az.hackathon.model;


import javax.persistence.*;
import java.util.List;


public class Staff {


    private int idStaff;


    private String fullName;

    private String contactNumber;

    private String username;

    private String password;


    private Role role;

    private Repair repair;

    private int activeTasks;

    private int deactiveTasks;

    public int getActiveTasks() {
        return activeTasks;
    }

    public void setActiveTasks(int activeTasks) {
        this.activeTasks = activeTasks;
    }

    public int getDeactiveTasks() {
        return deactiveTasks;
    }

    public void setDeactiveTasks(int deactiveTasks) {
        this.deactiveTasks = deactiveTasks;
    }

    private List<Repair> repairs;

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Staff staff = (Staff) o;

        return idStaff == staff.idStaff;
    }

    @Override
    public int hashCode() {
        return idStaff;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "idStaff=" + idStaff +
                ", fullName='" + fullName + '\'' +
                ", repairs=" + repairs +
                '}';
    }
}
