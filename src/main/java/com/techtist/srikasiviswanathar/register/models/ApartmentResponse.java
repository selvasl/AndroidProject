package com.techtist.srikasiviswanathar.register.models;

import com.google.gson.annotations.SerializedName;

public class ApartmentResponse {
    @SerializedName("BlockName")
    private String BlockName;

    @SerializedName("ApartmentID")
    private int ApartmentID;

    @SerializedName("DoorNo")
    private String DoorNo;

    // Getter for BlockName
    public String getBlockName() {
        return BlockName;
    }

    // Setter for BlockName
    public void setBlockName(String blockName) {
        this.BlockName = blockName;
    }

    // Getter for ApartmentID
    public int getApartmentID() {
        return ApartmentID;
    }

    // Setter for ApartmentID
    public void setApartmentID(int ApartmentID) {
        this.ApartmentID = ApartmentID;
    }

    // Getter for DoorNo
    public String getDoorNo() {
        return DoorNo;
    }

    // Setter for DoorNo
    public void setDoorNo(String doorNo) {
        this.DoorNo = doorNo;
    }
}
