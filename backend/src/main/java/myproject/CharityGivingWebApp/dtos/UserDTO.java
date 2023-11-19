package myproject.CharityGivingWebApp.dtos;

import java.util.Set;

import myproject.CharityGivingWebApp.model.Role;

public class UserDTO {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

    UserDTO() {
    }

    public UserDTO(Long id, String email, String firstName, String lastName, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
