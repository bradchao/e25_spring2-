package tw.brad.springd.dto.response;

public record AskResponse(Decision decision, String reason) {
    public enum Decision {
        ACCEPT, REJECT, UNCERTAIN
    }
}
