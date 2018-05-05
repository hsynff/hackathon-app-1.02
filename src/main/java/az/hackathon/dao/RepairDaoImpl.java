package az.hackathon.dao;


import az.hackathon.model.Progress;
import az.hackathon.model.Repair;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepairDaoImpl implements RepairDao {


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
}
