package feiticeiros.example.fmbackend.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {


    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message= "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String password;

    private String image;

    @NotBlank(message = "Nome de usuário é obrigatório")
    @Size(min = 2, max = 100, message = "Nome de usuário deve ter entre 2 e 100 caracteres")
    private String username;




}
