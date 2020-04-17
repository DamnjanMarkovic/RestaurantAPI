package restaurantIOS.models.dto;



import java.io.Serializable;
import java.util.Set;

public class UserResponse implements Serializable {

    private int id_user;
    private String userFirstName;
    private String imageLink;
    private Set<String> role;
    private int id_restaurant;


    public UserResponse(int id_user, String userFirstName, String imageLink, Set<String> role, int id_restaurant) {
        this.id_user = id_user;
        this.userFirstName = userFirstName;
        this.imageLink = imageLink;
        this.role = role;
        this.id_restaurant = id_restaurant;

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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }
}
