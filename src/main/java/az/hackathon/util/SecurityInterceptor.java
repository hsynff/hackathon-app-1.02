package az.hackathon.util;

import az.hackathon.model.Action;
import az.hackathon.model.Staff;
import az.hackathon.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Staff staff = (Staff) request.getSession().getAttribute("staff");
        if (staff==null){
            response.sendRedirect("/login");
            return false;
        }else if (staff.getRole().getIdRole()==Constants.ROLE_ID_REPAIRER){

            List<Action> actionList = securityService.getActionListByRoleId(Constants.ROLE_ID_REPAIRER);
            for (Action a : actionList){
                if (request.getServletPath().contains(a.getActionName())){
                    return true;
                }
            }
            response.sendRedirect("/login");
            return false;

        }else if (staff.getRole().getIdRole() == Constants.ROLE_ID_MANAGER){
            System.out.println("request.getServletPath() = " + request.getServletPath());
            List<Action> actionList = securityService.getActionListByRoleId(Constants.ROLE_ID_MANAGER);
            for (Action a : actionList){
                if (request.getServletPath().contains(a.getActionName())){
                    return true;
                }
            }
            response.sendRedirect("/login");
            return false;
        }else{
            response.sendRedirect("/login");
            return false;
        }
    }
}
