package tw.brad.springd.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AskRequest(
        @NotBlank String type,
        @NotBlank @Size(max= 8000000) String base64) {
}
