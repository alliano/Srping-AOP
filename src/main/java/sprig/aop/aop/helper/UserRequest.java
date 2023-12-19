package sprig.aop.aop.helper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class UserRequest {
    
    private String firstName;

    private String lastName;

    private String email;
}
