package tw.brad.spring2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "memberinfo")
public class Info {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, tel;

    @Column(name = "gender")
    private Boolean isMale;
    //----------------------
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Member member;
}
