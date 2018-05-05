package az.hackathon.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModel;

    @Column(name="model")
    private String model;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Device> devices;

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
