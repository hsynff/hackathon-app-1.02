package az.hackathon.service;

import az.hackathon.dao.SecurityDao;
import az.hackathon.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private SecurityDao securityDao;

    @Override
    public List<Action> getActionListByRoleId(int idRole) {
        return securityDao.getActionListByRoleId(idRole);
    }
}
