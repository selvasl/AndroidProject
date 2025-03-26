package com.techtist.srikasiviswanathar.register.models;

public class RegisterRequest {

    private String appVersion;
    private Data data;
    private String apiCode;
    private String appKey;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public String toString() {
        return "RegisterRequest [appVersion = " + getAppVersion() + ", data = " + getData() + ", apiCode = " + getApiCode() + ", appKey = " + getAppKey() + "]";
    }

    public static class Data {
        private String name;
        private String mobileNumber;
        private String email;
        private String password;
        private String blockName;
        private String doorNo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getBlockName() {
            return blockName;
        }

        public void setBlockName(String blockName) {
            this.blockName = blockName;
        }

        public String getDoorNo() {
            return doorNo;
        }

        public void setDoorNo(String doorNo) {
            this.doorNo = doorNo;
        }

        @Override
        public String toString() {
            return "Data [name = " + getName() + ", mobileNumber = " + getMobileNumber() + ", email = " + getEmail() + ", password = " + getPassword() + ", blockName = " + getBlockName() + ", doorNo = " + getDoorNo() + "]";
        }
    }
}