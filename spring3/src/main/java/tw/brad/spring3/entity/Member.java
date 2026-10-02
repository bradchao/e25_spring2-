package tw.brad.spring3.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account,passwd, name;

    @Lob
    private byte[] icon;
}
