package az.hackathon.model;

import javax.persistence.*;
import java.util.List;


public class User {

    private int idUser;


    private String fullName;

    private String email;

    private String address;

    private String contactNumber;


    private String fin;


    private List<Repair> repairs;

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

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    private Role role;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", fin='" + fin + '\'' +
                '}';
    }
}
