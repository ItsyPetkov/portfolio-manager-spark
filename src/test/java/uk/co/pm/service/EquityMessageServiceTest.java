package uk.co.pm.service;

import org.junit.jupiter.api.Test;
import uk.co.pm.model.Equity;
import uk.co.pm.model.EquityModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EquityMessageServiceTest {

    @Test
    public void getMessageReturnsMessageWithCorrectName() {
        Equity III = new Equity("III", "3I GROUP PLC","Equity","Financials","GBP");
        EquityModel EquityMessage = EqService.getEq(III);
        assertThat(EquityMessage.getMessage()).isEqualTo("Name is: 3I GROUP PLC Sector is: Financials EPIC is:  III Asset Type is: Equity Currency is: GBP");
    }

    @Test
    public void getMessageForNullEquityThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> EqService.getEq(null))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("Equity can't be null");
    }

    @Test
    public void getMessageForComapanyNameThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> EqService.getEq(new Equity("Dab", null, "Equity", "Financials", "DAB")))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("Equity Must have a NAME");
    }

   
}