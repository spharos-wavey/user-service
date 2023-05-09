package xyz.wavey.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String email;
    private String profileImageUrl;
    private String phoneNum;
    private Boolean blockList;
    private String UUID;
    private String nickName;
    private String secession;
    private Integer reward;
}
