package com.techtist.srikasiviswanathar.news.activities.models;

public class NewsItem {

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

        private String title;        // Title field
        private String description;  // Description field
        private String expiredate;   // expiredate field (updated to expiredate)

        // Getter and Setter for title
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        // Getter and Setter for description
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        // Getter and Setter for expiredate
        public String getExpiredate() {
            return expiredate;
        }

        public void setExpiredate(String expiredate) {
            this.expiredate = expiredate;
        }
    }

    @Override
    public String toString() {
        return "NewsRequest [appVersion = " + appVersion + ", data = " + data + ", apiCode = " + apiCode + ", appKey = " + appKey + "]";
    }
}
