package uk.co.pm.model;


public class Equity {

	private String EPIC, CompanyName, AssesType, Sector, Currency;
	
	public Equity(String epic, String companyName, String assestype, String sector, String currency){
		this.EPIC = epic;
		this.CompanyName = companyName;
		this.AssesType = assestype;
		this.Sector = sector;
		this.Currency = currency;
	}

	public String getEPIC() {
		return EPIC;
	}

	public void setEPIC(String ePIC) {
		this.EPIC = ePIC;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		this.CompanyName = companyName;
	}

	public String getAssesType() {
		return AssesType;
	}

	public void setAssesType(String assesType) {
		this.AssesType = assesType;
	}

	public String getSector() {
		return Sector;
	}

	public void setSector(String sector) {
		this.Sector = sector;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		this.Currency = currency;
	}
}
