package az.hackathon.dao;

import az.hackathon.model.Action;

import java.util.List;

public interface SecurityDao {
    List<Action> getActionListByRoleId(int idRole);
}
