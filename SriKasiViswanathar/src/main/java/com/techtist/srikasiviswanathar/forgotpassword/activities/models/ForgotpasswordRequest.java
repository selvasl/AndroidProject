package com.techtist.srikasiviswanathar.forgotpassword.activities.models;

public class ForgotpasswordRequest {

    private String appVersion;
    private Data data;
    private String apiCode;
    private String appKey;

    // Getter and Setter for appVersion
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    // Getter and Setter for data
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // Getter and Setter for apiCode
    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    // Getter and Setter for appKey
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    // Nested Data class for encapsulating request-specific parameters
    public class Data {

        private String mobile_number;  // Mobile number field
        private String password;   // Changed from newPassword to password

        // Getter and Setter for mobile_number
        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        // Getter and Setter for password
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @Override
    public String toString() {
        return "ForgotPasswordRequest [appVersion = " + appVersion + ", data = " + data + ", apiCode = " + apiCode + ", appKey = " + appKey + "]";
    }
}
