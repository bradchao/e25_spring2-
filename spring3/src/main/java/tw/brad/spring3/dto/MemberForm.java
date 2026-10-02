package tw.brad.spring3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberForm {
    private String account;
    private String passwd;
    private String name;
    private MultipartFile iconFile;
}
