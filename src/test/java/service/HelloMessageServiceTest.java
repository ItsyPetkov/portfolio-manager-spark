package service;

import model.HelloReference;
import model.Person;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HelloMessageServiceTest {

    @Test
    public void getMessageReturnsMessageWithCorrectName() {
        Person john = new Person("John", 28);
        HelloReference helloMessage = HelloMessageService.getHelloMessage(john);
        assertThat(helloMessage.getMessage()).isEqualTo("Hello, John");
    }

    @Test
    public void getMessageForNullPersonThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> HelloMessageService.getHelloMessage(null))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("Person cannot be null");
    }

    @Test
    public void getMessageForNullNameThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> HelloMessageService.getHelloMessage(new Person(null, 12)))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("Person must have a name");
    }

    @Test
    public void getMessageForEmptyNameThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> HelloMessageService.getHelloMessage(new Person("", 12)))
                .isInstanceOf(IllegalArgumentException.class)
                .withFailMessage("Person must have a name");
    }
}