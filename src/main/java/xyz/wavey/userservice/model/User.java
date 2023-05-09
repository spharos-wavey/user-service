package xyz.wavey.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import xyz.wavey.userservice.base.BaseTimeEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false)
    private String email;
    private String profileImageUrl;
    private String phoneNum;
    @Builder.Default private Boolean blockList = false; //기본값은 false
    @Column(nullable = false)

    private String UUID;
    @Column(nullable = false)

    private String nickName;
    private String secession;
    private Integer reward;
}
