package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Band;
import com.itibo.grob.services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    public List<Band> getBandList() {
        this.bandList = bandService.findAll();
        return bandList;
    }

    public void setBandList(List<Band> bandList) {
        this.bandList = bandList;
    }
}
