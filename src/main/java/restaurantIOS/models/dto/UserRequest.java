package restaurantIOS.models.dto;

import restaurantIOS.models.User;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

public class UserRequest implements Serializable{

    private String userName;
    private String password;
    private String userFirstName;
    private String imageLink;
    private int id_restaurant;
    private String role;

    public UserRequest(String userName, String password, String userFirstName, String imageLink, int id_restaurant, String role) {
        this.userName = userName;
        this.password = password;
        this.userFirstName = userFirstName;
        this.imageLink = imageLink;
        this.id_restaurant = id_restaurant;
        this.role = role;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
