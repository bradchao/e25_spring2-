package tw.brad.spring2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account,passwd;
    //-----------------------
    @OneToOne(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Info info;

    public void setInfo(Info info){
        this.info = info;
        if (info != null){
            info.setMember(this);
        }
    }


}
