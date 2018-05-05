package az.hackathon.dao;


import az.hackathon.model.Progress;
import az.hackathon.model.Repair;

public interface RepairDao {
    Repair getRepairById(int id);

    // update repair status by id method
    boolean updateRepairStatusById(int id, Progress progress);

    // cancel repair by id method
}
