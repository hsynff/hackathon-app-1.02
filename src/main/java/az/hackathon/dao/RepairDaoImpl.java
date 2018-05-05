package az.hackathon.dao;


import az.hackathon.model.Progress;
import az.hackathon.model.Repair;
import az.hackathon.model.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class RepairDaoImpl implements RepairDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Repair getRepairById(int id) {

       return null;
    }

    @Override
    public boolean updateRepairStatusById(int id, Progress progress) {

        return false;


    }

    @Override
    public List<Repair> getRepairListByStaffId(int id) {
        return null;
    }

    @Override
    public boolean updateRepairActiveById(int id, int active, Date endDate) {
        return false;
    }
}
