package service;

import model.HelloReference;
import model.Person;

public class HelloMessageService {

    public static HelloReference getHelloMessage(final Person person){
        return new HelloReference("Hello, " + person.getName());
    }
}
