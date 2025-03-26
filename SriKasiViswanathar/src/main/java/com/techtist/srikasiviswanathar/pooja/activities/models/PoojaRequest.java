package com.techtist.srikasiviswanathar.pooja.activities.models;

public class PoojaRequest {

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

    // ✅ Removed 'static' here
    public class Data {

        private int id;
        private String poojaTitle;
        private String poojaDate;
        private String startTime;
        private String endTime;
        private String sponsors;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPoojaTitle() {
            return poojaTitle;
        }

        public void setPoojaTitle(String poojaTitle) {
            this.poojaTitle = poojaTitle;
        }

        public String getPoojaDate() {
            return poojaDate;
        }

        public void setPoojaDate(String poojaDate) {
            this.poojaDate = poojaDate;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getSponsors() {
            return sponsors;
        }

        public void setSponsors(String sponsors) {
            this.sponsors = sponsors;
        }
    }

    @Override
    public String toString() {
        return "PoojaRequest [appVersion=" + appVersion + ", data=" + data + ", apiCode=" + apiCode + ", appKey=" + appKey + "]";
    }
}
