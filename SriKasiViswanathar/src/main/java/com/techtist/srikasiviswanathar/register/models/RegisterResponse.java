package com.techtist.srikasiviswanathar.register.models;

public class RegisterResponse {

    private String status;
    private String message;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegisterResponse [status = " + getStatus() + ", message = " + getMessage() + ", data = " + getData() + "]";
    }

    public static class Data {
        private String userId;
        private String name;
        private String mobileNumber;
        private String email;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

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

        @Override
        public String toString() {
            return "Data [userId = " + getUserId() + ", name = " + getName() + ", mobileNumber = " + getMobileNumber() + ", email = " + getEmail() + "]";
        }
    }
}
