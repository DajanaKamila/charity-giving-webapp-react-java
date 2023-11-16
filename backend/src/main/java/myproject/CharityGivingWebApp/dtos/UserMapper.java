package myproject.CharityGivingWebApp.dtos;

import org.springframework.stereotype.Component;

import myproject.CharityGivingWebApp.model.User;

@Component
public class UserMapper {

    public UserDTO mapToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRoles(user.getRoles());

        return dto;
    }

}
