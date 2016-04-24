package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Band;
import com.itibo.grob.services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@ManagedBean(name = "bandBean")
@SessionScoped
@Component
public class BandBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    BandService bandService;

    private String name;
    private String year;
    private String description;
    private Band currentBand;
    private List<Band> bandList;

    public BandBean() {
    }

    @PostConstruct
    private void init() {
        this.bandList = bandService.findAll();
    }

    public void addBand() {
        Band band = new Band("unknown", "unknown");
        band.setEdit(true);
        bandService.save(band);
    }

    public void editBand(Band band) {
        band.setEdit(true);
        this.name = band.getName();
        this.year = band.getYear();
        bandService.save(band);
    }

    public void saveBand(Band band) {
        band.setEdit(false);
        band.setName(this.name);
        band.setYear(this.year);
        bandService.save(band);
    }

    public void editDescription() {
        currentBand.setEdit(true);
        this.description = currentBand.getDescription();
        bandService.save(currentBand);
    }

    public void saveDescription() {
        currentBand.setEdit(false);
        currentBand.setDescription(this.description);
        bandService.save(currentBand);
    }

    public void deleteBand(Band band) {
        bandService.delete(band);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Band> getBandList() {
        this.bandList = bandService.findAll();
        return bandList;
    }

    public void setBandList(List<Band> bandList) {
        this.bandList = bandList;
    }

    public Band getCurrentBand() {
        return currentBand;
    }

    public void setCurrentBand(Band currentBand) {
        this.currentBand = currentBand;
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("band-description.xhtml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
