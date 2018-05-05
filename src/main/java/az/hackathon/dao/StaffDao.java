package az.hackathon.dao;

import az.hackathon.model.Staff;

public interface StaffDao {
    Staff getStaffByUsernameAndPassword(Staff staff);
}
