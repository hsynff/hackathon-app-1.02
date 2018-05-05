package az.hackathon.model;

import javax.persistence.*;
import java.util.Date;


public class Progress {


    private int idProgress;

    private int percent;

    private String comment;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_repair")
    private Repair repair;


    public int getIdProgress() {
        return idProgress;
    }

    public void setIdProgress(int idProgress) {
        this.idProgress = idProgress;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }
}
