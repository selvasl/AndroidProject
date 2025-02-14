package com.techtist.srikasiviswanathar.news.activities.models;

import java.util.List;

//public class NewsResponse {
//
//    private String status; // Assuming the response might contain a status field
//    private List<NewsData> newsList; // List of news items
//
//    // Getter and Setter for status
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    // Getter and Setter for newsList
//    public List<NewsData> getNewsList() {
//        return newsList;
//    }
//
//    public void setNewsList(List<NewsData> newsList) {
//        this.newsList = newsList;
//    }
//
//    // NewsData class to represent individual news items
//    public static class NewsData {
//
//        private String title;        // Title field
//        private String description;  // Description field
//        private String expiredate;   // expiredate field (Date string)
//
//        // Getter and Setter for title
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        // Getter and Setter for description
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        // Getter and Setter for expiredate
//        public String getExpiredate() {
//            return expiredate;
//        }
//
//        public void setExpiredate(String expiredate) {
//            this.expiredate = expiredate;
//        }
//
//        @Override
//        public String toString() {
//            return "NewsData [title = " + title + ", description = " + description + ", expiredate = " + expiredate + "]";
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "NewsResponse [status = " + status + ", newsList = " + newsList + "]";
//    }
//}

public class NewsResponse {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private String apiStatus;
        private List<NewsData> data;

        public String getApiStatus() {
            return apiStatus;
        }

        public void setApiStatus(String apiStatus) {
            this.apiStatus = apiStatus;
        }

        public List<NewsData> getData() {
            return data;
        }

        public void setData(List<NewsData> data) {
            this.data = data;
        }
    }

    public class NewsData {

        private String title;
        private String description;
        private String expiredate;
        private String status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description; 
        }

        public String getExpiredate() {
            return expiredate;
        }

        public void setExpiredate(String expiredate) {
            this.expiredate = expiredate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

