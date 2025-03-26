package com.techtist.srikasiviswanathar.register.models;

import com.google.gson.annotations.SerializedName;

public class ApartmentRequest {
    @SerializedName("BlockName")
    private String BlockName;

    public ApartmentRequest(String BlockName) {
        this.BlockName = BlockName;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        this.BlockName = BlockName;
    }
}
