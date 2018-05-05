package az.hackathon.service;

import az.hackathon.model.Action;

import java.util.List;

public interface SecurityService {
    List<Action> getActionListByRoleId(int idRole);
}
