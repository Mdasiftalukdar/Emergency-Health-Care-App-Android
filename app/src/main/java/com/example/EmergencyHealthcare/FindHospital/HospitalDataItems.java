package com.example.EmergencyHealthcare.FindHospital;

public class HospitalDataItems {
    private String hospitalAdress;
    private  String hospitalContactNumber;
    private  String hospitalName;

    public HospitalDataItems(String hospitalName,String hospitalAdress, String hospitalContactNumber) {
        this.hospitalName = hospitalName;
        this.hospitalAdress = hospitalAdress;
        this.hospitalContactNumber = hospitalContactNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAdress() {
        return hospitalAdress;
    }

    public void setHospitalAdress(String hospitalAdress) {
        this.hospitalAdress = hospitalAdress;
    }

    public String getHospitalContactNumber() {
        return hospitalContactNumber;
    }

    public void setHospitalContactNumber(String hospitalContactNumber) {
        this.hospitalContactNumber = hospitalContactNumber;
    }
}
