package az.hackathon.controller;


import az.hackathon.model.Progress;
import az.hackathon.service.RepairService;
import az.hackathon.util.Constants;
import az.hackathon.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
                                     @RequestParam("userPhone") String userPhone,
                                     HttpSession session){
        if (!Validator.validate(repairId, percent, comment)){
            session.setAttribute("message", Constants.ERROR_EMPTY_INPUTS);
            return "redirect: /staff/repair/"+repairId;
        }

        //TODO: call repairService.update() method here
        Progress progress = new Progress();
        progress.setPercent(percent);
        progress.setComment(comment);
        progress.setDate(new Date());

        boolean result = repairService.updateRepairStatusById(repairId, progress);

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
            return "redirect: /staff/repair/"+repairId;
        }

        if (Validator.validate(userEmail)){
            // TODO: send email to user
            //
        }

        if (Validator.validate(userPhone)){
            // TODO: send SMS notification to user
            //
        }




        return "redirect: /staff/repair/"+repairId;

    }

    // Sets repair active=0 and adds end_date timestamp
    @RequestMapping("/cancelRepair")
    public String cancelRepair(@RequestParam("id") int repairId, HttpSession session){
        int active = 0;
        Date endDate = new Date();

        boolean result = repairService.updateRepairActiveById(repairId, active, endDate);

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
        }

        return "redirect: /staff/main";

    }

    @RequestMapping("/createRepair")
    public String createRepair(@RequestParam("fin") String fin,
                               @RequestParam("fullName") String fullName,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address,
                               @RequestParam("brand") int idDevice,
                               @RequestParam("model") int idModel,
                               @RequestParam("title") String title,
                               @RequestParam("price") int price,
                               @RequestParam("idRepairer") int idRepairer,
                               @RequestParam("isReturningUser") int isReturningUser){








    }

    @RequestMapping("/getUserByFin")
    public String getUserByFin(@RequestParam("fin") String fin){
            return null;
    }

}
