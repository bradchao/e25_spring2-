package tw.brad.spring4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    private Long id;
    private String account, passwd, name;
}
