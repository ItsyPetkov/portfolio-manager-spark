package uk.co.pm.utils;

import java.io.FileWriter;
import java.util.Arrays;

import uk.co.pm.externalapi.EquityExternalApiService;
import uk.co.pm.model.Equity;


public class CSVUtilFile {
	
	EquityExternalApiService eqserviceapi;
	
	public CSVUtilFile(String remoteApiBaseUrl) {
		eqserviceapi = new EquityExternalApiService(remoteApiBaseUrl);
		try {
			writeToFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeToFile() throws Exception{
		
		

        String csvFile = "E:/Games/New folder/cs217/Equities.csv"; //please change this to correct directory otherwise this will only work for me!
        FileWriter writer = new FileWriter(csvFile);

        EquityCSVUtil.writeLine(writer, Arrays.asList("EPIC", "CompanyName", "AssetType", "Sector", "Currency"));
        
        for(Equity equities : eqserviceapi.getEquities()){
        	//custom separator + quote
            EquityCSVUtil.writeLine(writer, Arrays.asList(equities.getEPIC(), equities.getCompanyName(), equities.getAssetType(), equities.getSector(), equities.getCurrency()), ',', '"');
        }

        writer.flush();
        writer.close();
	}
}
