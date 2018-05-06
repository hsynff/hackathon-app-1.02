package az.hackathon.controller;


import az.hackathon.model.*;
import az.hackathon.service.RepairService;
import az.hackathon.service.UserService;
import az.hackathon.util.Constants;
import az.hackathon.util.Crypto;
import az.hackathon.util.MailService;
import az.hackathon.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Controller
public class RepairController {

    @Autowired
    RepairService repairService;
    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    @RequestMapping("/updateStatus")
    public String updateRepairStatus(@RequestParam("id") int repairId,
                                     @RequestParam("percent") int percent,
                                     @RequestParam("comment") String comment,
                                     @RequestParam("userEmail") String userEmail,
                                     @RequestParam("userPhone") String userPhone,
                                     HttpSession session){
        if (!Validator.validate(repairId, percent, comment)){
            session.setAttribute("message", Constants.ERROR_EMPTY_INPUTS);
            return "redirect:/staff/repair/"+repairId;
        }


        Progress progress = new Progress();
        progress.setPercent(percent);
        progress.setComment(comment);
        progress.setDate(new Date());

        boolean result = repairService.updateRepairStatusById(repairId, progress);

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
            return "redirect:/staff/repair/"+repairId;
        }

        if (Validator.validate(userEmail)){
            // TODO: send email to user
            //
            ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,1, TimeUnit.SECONDS,  new LinkedBlockingQueue<Runnable>());
            executor.execute(() -> mailService.sendEmail("Azercell",userEmail, "Temir statusunuz yenilendi",
                    "Status faizi: "+percent+"%\n"+"Temircinin sherhi: "+comment+"\n"+"Yenilenme tarixi: "+new Date()));
        }

        if (Validator.validate(userPhone)){
            // TODO: send SMS notification to user
            //
        }




        return "redirect:/staff/repair/"+repairId;

    }

    // Sets repair active=0 and adds end_date timestamp
    @RequestMapping("/cancelRepair")
    public String cancelRepair(@RequestParam("id") int repairId, HttpSession session){
        int active = 2;
        Date endDate = new Date();

        boolean result = repairService.updateRepairActiveById(repairId, active, endDate);

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
        }

        return "redirect:/staff/main";

    }

    @RequestMapping("/createRepair")
    public String createRepair(@RequestParam("fin") String fin,
                               @RequestParam("fullName") String fullName,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address,
                               @RequestParam("idUser") int idUser,
                               @RequestParam("brand") int idDevice,
                               @RequestParam("model") int idModel,
                               @RequestParam("title") String title,
                               @RequestParam("price") int price,
                               @RequestParam("idRepairer") int idRepairer,
                               @RequestParam("isReturning") int isReturningUser,
                               @RequestParam("trackingNumber") String trackingNumber,
                               HttpSession session){

        System.out.println("idRepairer = " + idRepairer);
        System.out.println("trackingNumber = " + trackingNumber);


        if (isReturningUser==0){
            User user = new User();
            user.setEmail(email);
            user.setContactNumber(phone);
            user.setAddress(address);
            user.setFullName(fullName);
            user.setFin(fin);

            Role role = new Role();
            role.setIdRole(Constants.ROLE_ID_USER);
            user.setRole(role);
           idUser = userService.createNewUser(user);
        }

        Repair repair = new Repair();
        User user = new User();
        user.setIdUser(idUser);
        repair.setUser(user);
        Device device = new Device();
        device.setIdDevice(idDevice);
        repair.setDevice(device);
        Staff staff = new Staff();
        staff.setIdStaff(idRepairer);
        repair.setStaff(staff);
        repair.setPrice(price);
        repair.setTitle(title);
        repair.setStartDate(new Date());
        repair.setActive(1);
        repair.setTrackingNumber(trackingNumber);

        boolean result = repairService.createNewRepair(repair);

        if (!result){
            session.setAttribute("message", Constants.ERROR_INTERNAL);
            return "redirect:/staffManager/new-repair";
        }






        return "redirect:/staffManager/main";



    }

    @RequestMapping("/getAllModel")
    public String getAllModel(org.springframework.ui.Model model){
        List<Model> modelList = repairService.getAllModel();

        List<Device> deviceList = new ArrayList<>();
        for (Model m : modelList){

        }

        model.addAttribute("modelList",modelList);

        return "/view_elements/device_details";

    }

    @RequestMapping("/getDeviceByModelId")
    public String getDeviceByModelId(org.springframework.ui.Model model,
                                     @RequestParam("id") int id){

        List<Device> deviceList = repairService.getDeviceByModelId(id);

        model.addAttribute("deviceList",deviceList);

        return "/view_elements/device_model_select";

    }

    @RequestMapping("/generateQR")
    @ResponseBody
    public String generateQR(){

        String trackingNumber = Crypto.generateTrackingNumber();

        while (repairService.getTrackingNumberCount(trackingNumber)>0){
            trackingNumber = Crypto.generateTrackingNumber();
            System.out.println("sss");
        }

        System.out.println("trackingNumber = " + trackingNumber);

        return trackingNumber;
    }




}
