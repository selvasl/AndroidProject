package com.techtist.srikasiviswanathar.login.models;

public class LoginRequest {
    private String appVersion;

    private Data data;

    private String apiCode;

    private String appKey;

    public String getAppVersion ()
    {
        return appVersion;
    }

    public void setAppVersion (String appVersion)
    {
        this.appVersion = appVersion;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    public String getApiCode ()
    {
        return apiCode;
    }

    public void setApiCode (String apiCode)
    {
        this.apiCode = apiCode;
    }

    public String getAppKey ()
    {
        return appKey;
    }

    public void setAppKey (String appKey)
    {
        this.appKey = appKey;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [appVersion = "+ getAppVersion() +", data = "+ getData() +", apiCode = "+ getApiCode() +", appKey = "+ getAppKey() +"]";
    }

    public class Data
    {
        private String password;

        private String mobile_number;

        public String getPassword ()
        {
            return password;
        }

        public void setPassword (String password)
        {
            this.password = password;
        }

        public String getMobileNumber ()
        {
            return mobile_number;
        }

        public void setMobileNumber (String mobileNumber)
        {
            this.mobile_number = mobileNumber;
        }

    }


}
