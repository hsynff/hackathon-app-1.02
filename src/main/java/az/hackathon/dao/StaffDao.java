package az.hackathon.dao;

import az.hackathon.model.Staff;

import java.util.List;

public interface StaffDao {
    Staff getStaffByUsernameAndPassword(Staff staff);

    boolean updateStaffPassword(String password,int staffId);

    List<Staff> getAllStaff();

    boolean addNewRepairer(Staff staff);
}
