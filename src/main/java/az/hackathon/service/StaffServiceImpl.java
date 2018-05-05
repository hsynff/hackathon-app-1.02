package az.hackathon.service;

import az.hackathon.dao.StaffDao;
import az.hackathon.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Override
    public Staff getStaffByUsernameAndPassword(Staff staff) {
        return staffDao.getStaffByUsernameAndPassword(staff);
    }
}
