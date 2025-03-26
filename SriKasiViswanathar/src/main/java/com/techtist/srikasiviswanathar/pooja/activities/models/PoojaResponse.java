package com.techtist.srikasiviswanathar.pooja.activities.models;

import java.util.List;

public class PoojaResponse {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        private String apiStatus;
        private List<PoojaData> data;  // Change from single object to List

        public String getApiStatus() {
            return apiStatus;
        }

        public void setApiStatus(String apiStatus) {
            this.apiStatus = apiStatus;
        }

        public List<PoojaData> getData() {  // Change return type to List
            return data;
        }

        public void setData(List<PoojaData> data) {  // Change parameter to List
            this.data = data;
        }
    }

    public class PoojaData {
        private int id;
        private String pooja_title;
        private String pooja_date;
        private String start_time;
        private String end_time;
        private String sponsors;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPoojaTitle() {
            return pooja_title;
        }

        public void setPoojaTitle(String poojaTitle) {
            this.pooja_title = poojaTitle;
        }

        public String getPoojaDate() {
            return pooja_date;
        }

        public void setPoojaDate(String poojaDate) {
            this.pooja_date = poojaDate;
        }

        public String getStartTime() {
            return start_time;
        }

        public void setStartTime(String startTime) {
            this.start_time = startTime;
        }

        public String getEndTime() {
            return end_time;
        }

        public void setEndTime(String endTime) {
            this.end_time = endTime;
        }

        public String getSponsors() {
            return sponsors;
        }

        public void setSponsors(String sponsors) {
            this.sponsors = sponsors;
        }

        @Override
        public String toString() {
            return "PoojaData {" +
                    "id=" + id +
                    ", poojaTitle='" + pooja_title + '\'' +
                    ", poojaDate='" + pooja_date + '\'' +
                    ", startTime='" + start_time + '\'' +
                    ", endTime='" + end_time + '\'' +
                    ", sponsors='" + sponsors + '\'' +
                    '}';
        }
    }
}
