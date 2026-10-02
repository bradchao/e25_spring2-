package tw.brad.spring3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "hotels")
public class Hotel {
    @Id
    private Long id;
    private String name, addr, tel;
}
