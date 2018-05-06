package az.hackathon.service;


import az.hackathon.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffService {

    Staff getStaffByUsernameAndPassword(Staff staff);

    boolean updateStaffPassword(String password,int staffId);

    List<Staff> getAllStaff();

    boolean addNewRepairer(Staff staff);
}
