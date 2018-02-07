package uk.co.pm.model;


public class Equity {

	private String EPIC, CompanyName, AssetType, Sector, Currency;
	
	public Equity(String epic, String companyName, String assettype, String sector, String currency){
		this.EPIC = epic;
		this.CompanyName = companyName;
		this.AssetType = assettype;
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

	public String getAssetType() {
		return AssetType;
	}

	public void setAssesType(String assetType) {
		this.AssetType = assetType;
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
