package az.hackathon.service;

import az.hackathon.dao.RepairDao;
import az.hackathon.model.Progress;
import az.hackathon.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RepairServiceImpl implements RepairService {

    @Autowired
    RepairDao repairDao;

    @Override
    public Repair getRepairById(int id) {
        return repairDao.getRepairById(id);
    }

    @Override
    public boolean updateRepairStatusById(int id, Progress progress) {
        return false;
    }

    @Override
    public boolean updateRepairActiveById(int id, int active, Date endDate) {
        return false;
    }

    @Override
    public List<Repair> getRepairListByStaffId(int id) {
        return null;
    }
}
