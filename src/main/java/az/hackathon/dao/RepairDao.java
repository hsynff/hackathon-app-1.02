package az.hackathon.dao;


import az.hackathon.model.Progress;
import az.hackathon.model.Repair;

import java.util.Date;
import java.util.List;

public interface RepairDao {
    Repair getRepairById(int id);

    // update repair status by id method
    boolean updateRepairStatusById(int id, Progress progress);

    // cancel repair by id method
    boolean updateRepairActiveById(int id, int active, Date endDate);

    //get repairlist by staff id
    List<Repair> getRepairListByStaffId(int id);

    int getTrackingNumberCount(String trackingNumber);

    List<Repair> getArchiveRepairListByStaffId(int id);

    Repair getArchiveRepairById(int id);
}
