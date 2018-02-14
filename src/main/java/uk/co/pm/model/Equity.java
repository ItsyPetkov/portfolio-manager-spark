package uk.co.pm.model;

import com.google.gson.annotations.SerializedName;

public class Equity {

	private String EPIC,Sector, Currency;
	 @SerializedName("Asset Type") private String AssetType;
	@SerializedName("Company Name") String  CompanyName;
	
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

	public String getCompanyName() { return CompanyName; }

	public void setCompanyName(String companyName) {
		this.CompanyName = companyName;
	}

	public String getAssetType() {		
		return AssetType;
	}

	public void setAssetType(String assetType) {
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
	 @Override
	    public String toString() {
	        return "uk.co.pm.model.Equity{" +
	                "Company Name='" + CompanyName + '\'' +
	                ", EPIC=" + EPIC + '\'' +
	                ", Currency=" + Currency + '\'' +
	                ", Sector=" + Sector +
	                '}';
	    }
}
