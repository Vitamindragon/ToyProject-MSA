package org.prj.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@JsonInclude(NON_NULL)
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or grater than 8 characters")
    private String password;

    private List<OrderDTO> orders;

    public UserDTO(String email, String name, List<OrderDTO> orders) {
        this.email = email;
        this.name = name;
        this.orders = orders;
    }

    public static UserDTO createUserDTO(String email, String name, List<OrderDTO> orders) {
        return new UserDTO(name, email, orders);
    }


}
