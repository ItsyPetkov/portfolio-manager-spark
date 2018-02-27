package uk.co.pm.model;

public class PriceReference {


    private String message;

    public PriceReference(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString(){
        return "PriceReference{" +" message ='" + message + '\'' +'}';
    }
}
