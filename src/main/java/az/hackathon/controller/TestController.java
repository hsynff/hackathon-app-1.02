package az.hackathon.controller;

import az.hackathon.dao.StaffDao;
import az.hackathon.model.Staff;
import az.hackathon.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private MailService mailService;

    @Autowired
    private StaffDao staffDao;


    @RequestMapping(value = "/testMail",method = RequestMethod.GET)
    public String testEmail(){
       boolean r= mailService.sendEmail("Azercell","vusalrzayev0693@gmail.com","test","test");


        return "index";
    }


    @RequestMapping(value = "/testAllStaff",method = RequestMethod.GET)
    public String testGetAllStaff(){
        List<Staff> staffList=staffDao.getAllStaff();

        return "index";

    }


}
