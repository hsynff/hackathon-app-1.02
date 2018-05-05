package az.hackathon.controller;

import az.hackathon.model.Progress;
import az.hackathon.model.Repair;
import az.hackathon.model.Role;
import az.hackathon.model.Staff;
import az.hackathon.service.RepairService;
import az.hackathon.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @Autowired
    StaffService staffService;
    @Autowired
    RepairService repairService;

    @RequestMapping(value = "/getStaff",method = RequestMethod.GET)
    public String testGetStaffByUsernameandPassword(){
        Staff staff=new Staff();
        staff.setUsername("cosqun");
        staff.setPassword("123");
        Role role=new Role();
        role.setIdRole(1);
        staff.setRole(role);
       Staff staff1= staffService.getStaffByUsernameAndPassword(staff);

        return null;
    }

    @RequestMapping(value="/updateProgress",method = RequestMethod.GET)
    public String testUpdateProgressByRepair(){
        Progress progress=new Progress();
        progress.setPercent(1000);
        boolean result=repairService.updateRepairStatusById(1,progress);
        return null;
    }
}
