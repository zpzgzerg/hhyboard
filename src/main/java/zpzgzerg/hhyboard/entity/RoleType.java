package zpzgzerg.hhyboard.entity;

import lombok.Getter;

@Getter
public enum RoleType {

    ROLE_ADMIN("관리자"),
    ROLE_USER("사용자");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

}
