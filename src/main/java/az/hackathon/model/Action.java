package az.hackathon.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAction;

    @Column(name="action_name")
    private String actionName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_action", joinColumns = @JoinColumn(name = "id_action"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;


    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
