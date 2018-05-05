package az.hackathon.controller;


import az.hackathon.service.RepairService;
import az.hackathon.util.Constants;
import az.hackathon.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class RepairController {

    @Autowired
    RepairService repairService;

    @RequestMapping("/updateStatus")
    public String updateRepairStatus(@RequestParam("id") int repairId,
                                     @RequestParam("percent") int percent,
                                     @RequestParam("comment") String comment,
                                     @RequestParam("userEmail") String userEmail,
                                     HttpSession session){
        if (!Validator.validate(repairId, percent, comment)){
            session.setAttribute("message", Constants.ERROR_EMPTY_INPUTS);
            return "redirect: /staff/repair/"+repairId;
        }

        //TODO: call repairService.update() method here
        boolean result = true;

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
            return "redirect: /staff/repair/"+repairId;
        }

        // TODO: send email to user
        //

        return "redirect: /staff/repair/"+repairId;

    }

    // Sets repair active=0 and adds end_date timestamp
    @RequestMapping("/cancelRepair")
    public String cancelRepair(@RequestParam("id") int repairId, HttpSession session){
        int active = 0;
        Date endDate = new Date();

        // TODO: call repairservice.cancelRepair method here
        boolean result = true;

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
        }

        return "redirect: /staff/main";

    }

}
