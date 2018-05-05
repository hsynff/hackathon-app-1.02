package az.hackathon.service;


import az.hackathon.model.Progress;
import az.hackathon.model.Repair;

import java.util.Date;
import java.util.List;

public interface RepairService {
    Repair getRepairById(int id);

    // update repair status by id method
    boolean updateRepairStatusById(int id, Progress progress);

    // cancel repair by id method
    boolean updateRepairActiveById(int id, int active, Date endDate);

    //get repairlist by staff id
    List<Repair> getRepairListByStaffId(int id);
}
