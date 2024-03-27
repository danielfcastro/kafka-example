package pt.com.daniel.kafka.api;

public record MessageRequest(String message) {
    public String getMessage() {
        return message;
    }
}
