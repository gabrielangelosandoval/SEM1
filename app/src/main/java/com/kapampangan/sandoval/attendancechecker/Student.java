package com.kapampangan.sandoval.attendancechecker;

import java.io.Serializable;
import java.util.Scanner;

public class Student implements Serializable{
    private final int ALLOWABLE=11;
    private String stuFullName;
    private String stuLastName;
    private int seatNo;
    private String section;
    private String status;
    private int numLates;
    private int numAbsences;
    private int totalNumAbsences;


    public Student(String name, int seatnum, String stuSection){
        stuFullName = name;
        Scanner s = new Scanner(stuFullName).useDelimiter(",");
        stuLastName = s.next();
        seatNo = seatnum;
        section = stuSection;
        status = "Good";
        numLates = 0;
        numAbsences = 0;
        totalNumAbsences = 0;
    }

    public Student(){
        stuFullName = "";
        stuLastName = "";
        seatNo = 0;
        section = "";
        status = "Good";
        numLates = 0;
        numAbsences = 0;
        totalNumAbsences = 0;
    }

    // SETTERS
    public void setStuName(String studName) {
        this.stuFullName = studName;
        Scanner s = new Scanner(this.stuFullName).useDelimiter(",");
        stuLastName = s.next();
    }

    public void setSeatNo(int seatNum) {
        this.seatNo = seatNum;
    }

    public void setSection(String strSection) {
        this.section = strSection;
    }

    public void setNumLates(int noLates) {
        this.numLates = noLates;
    }

    public void setNumAbsences(int noAbsences) {
        this.numAbsences = noAbsences;
    }

    public void setTotalNumAbsences(int itotalNumAbsences) {
        this.totalNumAbsences = itotalNumAbsences;
    }

    public void setStatus(String strStatus) {
        this.status = strStatus;
    }

    // GETTERS
    public String getFullName() {
        return stuFullName;
    }
    public String getLastName() {
        return stuLastName;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public String getSection() {
        return section;
    }

    public int getNumLates() {
        return numLates;
    }

    public int getNumAbsences() {
        return numAbsences;
    }

    public int getTotalNumAbsences() {
        return totalNumAbsences;
    }

    public String getStatus() {
        return status;
    }

    // OPERATIONS

    public void computeTotalNumAbsences(){
        totalNumAbsences = (numLates/3) + numAbsences;
    }

    public void computeStatus(){
        int gauge = (totalNumAbsences*100)/ALLOWABLE;

        if(gauge<50){ status = "Good";}
        else if(gauge>=50 && gauge<75) {status="Caution";}
        else if(gauge >=75 && gauge < 100){status="Warning";}
        else{
            status = "FA";
        }
    }

    public void addLate(){
        numLates = numLates+1;
        computeTotalNumAbsences();
        computeStatus();
    }

    public void addAbsence(){
        numAbsences = numAbsences+1;
        computeTotalNumAbsences();
        computeStatus();
    }

    // DETAILS
    public String getDetails(){
        return "\nSeat Number: " + seatNo + "\nFull Name: " + stuFullName + "\nSection: " + section + "\nNumber of Lates: " + numLates + "\nNumber of Absences: " + numAbsences + "\nTotal Number of Absences: " + totalNumAbsences + "\nStatus: " + status;
    }

    public String compileRecord(){
        return seatNo + ";" + stuFullName + ";" + section + ";" + numLates + ";" + numAbsences + ";" + totalNumAbsences + ";" + status + ";\n";
    }

}
