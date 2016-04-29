package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Band;
import com.itibo.grob.dataaccess.model.Member;
import com.itibo.grob.services.BandService;
import com.itibo.grob.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "memberBean")
@SessionScoped
@Component
public class MemberBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BandService bandService;

    private String firstName;
    private String lastName;
    private String biography;
    private Band currentBand;
    private List<Member> memberList;
    private Member currentMember;


    public MemberBean() {
    }

    public void addMember() throws IOException {
        Member member = new Member("unknown", "unknown");
        List<Member> memberList = currentBand.getMembers();
        memberList.add(member);
        currentBand.setMembers(memberList);
        bandService.save(currentBand);
        FacesContext.getCurrentInstance().getExternalContext().redirect("members.xhtml");
    }

    public void editMember(Member member) {
        member.setEdit(true);
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        memberService.save(member);
    }

    public void saveMember(Member member) {
        member.setEdit(false);
        member.setFirstName(this.firstName);
        member.setLastName(this.lastName);
        memberService.save(member);
    }

    public void editDescription() {
        currentMember.setEdit(true);
        this.biography = currentMember.getBiography();
        memberService.save(currentMember);
    }

    public void saveDescription() {
        currentMember.setEdit(false);
        currentMember.setBiography(this.biography);
        memberService.save(currentMember);
    }

    public void deleteMember(Member member) {
        memberService.delete(member);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Band getCurrentBand() {
        return currentBand;
    }

    public void setCurrentBand(Band currentBand) {
        this.currentBand = currentBand;
    }

    public List<Member> getMemberList() {
        this.memberList = currentBand.getMembers();
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }


}
