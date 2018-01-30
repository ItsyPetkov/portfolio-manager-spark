package model;

public class HelloReference {
    public HelloReference(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "model.HelloReference{" +
                "message='" + message + '\'' +
                '}';
    }
}
