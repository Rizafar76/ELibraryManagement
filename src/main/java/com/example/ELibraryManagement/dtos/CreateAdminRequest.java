package com.example.ELibraryManagement.dtos;

import com.example.ELibraryManagement.models.Admin;
import com.example.ELibraryManagement.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String name;

    public Admin toAdmin() {

        return Admin.builder()
                .name(this.name)
                .user(User.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }
}
