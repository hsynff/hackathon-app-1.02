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
        return repairDao.updateRepairStatusById(id,progress);
    }

    @Override
    public boolean updateRepairActiveById(int id, int active, Date endDate) {
        return repairDao.updateRepairActiveById(id,active,endDate);
    }

    @Override
    public List<Repair> getRepairListByStaffId(int id) {

        return repairDao.getRepairListByStaffId(id);
    }

    @Override
    public int getTrackingNumberCount(String trackingNumber) {
        return repairDao.getTrackingNumberCount(trackingNumber);
    }

    @Override
    public List<Repair> getArchiveRepairListByStaffId(int id) {
        return null;
    }

    @Override
    public Repair getArchiveRepairById(int id) {
        return null;
    }
}
