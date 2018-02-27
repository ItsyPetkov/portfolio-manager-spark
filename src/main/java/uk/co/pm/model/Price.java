package uk.co.pm.model;

import com.google.gson.annotations.SerializedName;

public class Price {
//[{"EPIC":"III","Date Time":"20015-Q1","Mid Price":4.89,"Currency":"GBP"}

    private String EPIC,Currency;
    @SerializedName("Date Time") private String Date;
    @SerializedName("Mid Price") private String  Mid;

        public Price(String e, String d, String m, String c){
            this.EPIC = e;
            this.Date =d;
            this.Mid = m;
            this.Currency = c;
        }


    public String getEPIC() {
        return EPIC;
    }

    public void setEPIC(String EPIC) {
        this.EPIC = EPIC;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMid() {
        return Mid;
    }

    public void setMid(String mid) {
        Mid = mid;
    }

    @Override
    public String toString() {
        return "uk.co.pm.model.Price{" +
                "Currency='" + Currency + '\'' +
                ", EPIC=" + EPIC + '\'' +
                ", Mid Price=" + Mid + '\'' +
                ", Date Time=" + Date +
                '}';
    }


}
