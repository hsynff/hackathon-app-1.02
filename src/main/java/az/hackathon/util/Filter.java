package az.hackathon.util;

import az.hackathon.model.Action;
import az.hackathon.model.Staff;
import az.hackathon.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class Filter {

    public static boolean check(HttpSession session, HttpServletRequest request, HttpServletResponse response, SecurityService securityService){

        String path=request.getServletPath();
        if (path.equals("/") || path.equals("/login")){
            return true;
        }

        if(session.getAttribute("staff")==null){
            List<Action> actions= securityService.getActionListByRoleId(Constants.DEFAULT_ROLE_ID);
            boolean validateAction=false;
            for(Action action :actions){
                if(path.contains(action.getActionName())){
                    validateAction=true;
                    break;
                }
            }
            if(validateAction){
               return true;
            }else{
              return false;

            }
        }else {
            Staff staff=(Staff)request.getSession().getAttribute("staff");
            int roleId=staff.getRole().getIdRole();
            List<Action> actions=securityService.getActionListByRoleId(roleId);

            boolean validateAction=true;

            for(Action action:actions){
                if(path.contains(action.getActionName())){
                    validateAction=true;
                }
            }

            if(validateAction){
               return true;
            }else{
                if(roleId==2){
                   return false;
                }

                if(roleId==1){
                   return false;
                }


            }
        }
        return false;
    }
}
