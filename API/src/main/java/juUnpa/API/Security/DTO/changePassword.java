package juUnpa.API.Security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class changePassword {

    String currentPassword;
    String newPassword;

}
