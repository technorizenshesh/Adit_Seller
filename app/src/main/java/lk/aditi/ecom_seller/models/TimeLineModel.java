package lk.aditi.ecom_seller.models;

public class TimeLineModel {
    String Title;
    String detail;
    int status;
     String date;
     String time;


    public int isStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TimeLineModel(String title, String detail, int status, String date, String time) {
        Title = title;
        this.detail = detail;
        this.status = status;
        this.date=date;
        this.time=time;

    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
