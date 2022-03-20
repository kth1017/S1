package pracs1.springboot.config.auth.dto;

import lombok.Getter;
import pracs1.springboot.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // 세션에는 인증 사용자 정보만 필요하기에 간단하게
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPictures();
    }
}
