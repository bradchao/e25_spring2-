package tw.brad.spring3.apis;

import lombok.Data;

@Data
public class MemberForm {
    private String account;
    private String passwd;
    private String name;
}
