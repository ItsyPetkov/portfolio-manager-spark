package service;

import model.HelloReference;
import model.Person;

public class HelloMessageService {

    public static HelloReference getHelloMessage(final Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        if(person.getName() == null || "".equals(person.getName())){
            throw new IllegalArgumentException("Person must have a name");
        }
        return new HelloReference("Hello, " + person.getName());
    }
}
