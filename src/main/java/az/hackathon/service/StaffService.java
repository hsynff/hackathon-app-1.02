package az.hackathon.service;


import az.hackathon.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffService {

    Staff getStaffByUsernameAndPassword(Staff staff);
}
