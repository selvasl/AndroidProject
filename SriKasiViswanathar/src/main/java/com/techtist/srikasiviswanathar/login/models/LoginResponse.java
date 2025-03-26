package com.techtist.srikasiviswanathar.login.models;

public class LoginResponse {
    private Result result;

    public Result getResult ()
    {
        return result;
    }

    public void setResult (Result result)
    {
        this.result = result;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+"]";
    }
    public class Result
    {
        private String appVersion;

        private Data data;

        private String apiCode;

        private String apiStatus;

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

        public String getApiStatus ()
        {
            return apiStatus;
        }

        public void setApiStatus (String apiStatus)
        {
            this.apiStatus = apiStatus;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [appVersion = "+appVersion+", data = "+data+", apiCode = "+apiCode+", apiStatus = "+apiStatus+"]";
        }
    }

    public class Data
    {
        private String mobileNumber;

        private String password;

        private String reason;

        private String message;

        public String getMobileNumber ()
        {
            return mobileNumber;
        }

        public void setMobileNumber (String mobile_number)
        {
            this.mobileNumber = mobile_number;
        }




        public String getpassword ()
        {
            return password;
        }

        public void setpassword (String password)
        {
            this.password = password;
        }


        public String getReason ()
        {
            return reason;
        }

        public void setReason (String reason)
        {
            this.reason = reason;
        }

        public String getMessage ()
        {
            return message;
        }

        public void setMessage (String message)
        {
            this.message = message;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [mobile_number = "+mobileNumber+", password = "+password+"]";
        }
    }



}
