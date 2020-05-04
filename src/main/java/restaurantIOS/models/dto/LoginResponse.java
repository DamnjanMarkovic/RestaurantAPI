package restaurantIOS.models.dto;


import java.io.Serializable;
import java.util.Set;

public class LoginResponse implements Serializable {

    private int id_user;
    private final String jwt;
    private String userName;
    private String userFirstName;
    private Integer id_image;
    private Set<String> role;
    private int id_restaurant;


    public LoginResponse(int id_user, String jwt, String userName,
                         String userFirstName, Integer id_image, Set<String> role, int id_restaurant) {
        this.id_user = id_user;
        this.jwt = jwt;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.id_image = id_image;
        this.role = role;
        this.id_restaurant = id_restaurant;

    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public Integer getId_image() {
        return id_image;
    }

    public void setId_image(Integer id_image) {
        this.id_image = id_image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJwt() {
        return jwt;
    }


}
