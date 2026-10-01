package tw.brad.spring3.apis;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private Boolean gender;
    private Integer age;
}
