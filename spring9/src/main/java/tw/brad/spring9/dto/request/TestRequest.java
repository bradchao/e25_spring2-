package tw.brad.spring9.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TestRequest(@NotBlank @Size(max = 100) String text) {}
