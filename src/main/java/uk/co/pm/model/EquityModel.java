package uk.co.pm.model;

public class EquityModel {

	private String message;
	
	public EquityModel(String message){
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
		 return " EqModel{" +" message ='" + message + '\'' +'}';
	 }
}
