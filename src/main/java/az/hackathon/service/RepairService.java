package az.hackathon.service;


import az.hackathon.model.Progress;
import az.hackathon.model.Repair;

import java.util.Date;

public interface RepairService {
    Repair getRepairById(int id);

    boolean updateRepairStatusById(int id, Progress progress);
    boolean updateRepairActiveById(int id, int active, Date endDate);
}
