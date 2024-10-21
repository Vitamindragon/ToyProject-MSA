package org.prj.user.web.v1.dto;


import lombok.Data;
import org.prj.user.web.v1.vo.ResponseOrder;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    private String decryptedPwd;

    private String encryptedPwd;

    private List<ResponseOrder> orders;
}