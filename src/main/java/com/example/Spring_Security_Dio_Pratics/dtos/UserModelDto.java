package com.example.Spring_Security_Dio_Pratics.dtos;

import com.example.Spring_Security_Dio_Pratics.models.UserModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModelDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UserModel transform(){
        return new UserModel(username, password);
    }
}
