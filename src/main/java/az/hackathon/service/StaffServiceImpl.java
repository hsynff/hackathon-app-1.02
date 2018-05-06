package az.hackathon.service;

import az.hackathon.dao.StaffDao;
import az.hackathon.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Override
    public Staff getStaffByUsernameAndPassword(Staff staff) {
        return staffDao.getStaffByUsernameAndPassword(staff);
    }

    @Override
    public boolean updateStaffPassword(String password) {
        return false;
    }

    @Override
    public List<Staff> getAllStaff() {
        return null;
    }

    @Override
    public boolean addNewRepairer(Staff staff) {
        return false;
    }
}
