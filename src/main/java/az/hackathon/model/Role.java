package az.hackathon.model;

import javax.persistence.*;
import java.util.List;

public class Role {


    private int idRole;

    private String roleName;


    private List<User> users;



    private List<Action> actions;


    private List<Staff> staffList;


    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }



    public void setUsers(List<User> users) {
        this.users = users;
    }
}
