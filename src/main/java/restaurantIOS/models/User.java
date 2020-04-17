package restaurantIOS.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
@NamedQuery(name = "Integer.getSpecificUser",
        query = "SELECT u.id from User u where u.userFirstName = ?1")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private int id;
    @Column(name = "username")
    private String userName;
    private String password;
    @Column(name = "status")
    private boolean active;
    @Column(name = "userFirstName")
    private String userFirstName;
    private String imageLink;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_restaurant", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_restaurant"))
    private Restaurant restaurant;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;


    public User(String userName, String password, boolean active, String userFirstName, String imageLink) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.imageLink = imageLink;
    }

    public User(String userName, String password, boolean active, String userFirstName, String imageLink, Restaurant restaurant, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.imageLink = imageLink;
        this.restaurant = restaurant;
        this.roles = roles;
    }

    public User() {
    }

    public User(int id, String userName, String password, boolean active, String userFirstName, String imageLink) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.userFirstName = userFirstName;
        this.imageLink = imageLink;


    }





    public User(User user) {


        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.userFirstName = user.getUserFirstName();
        this.imageLink = user.getImageLink();
        this.roles = user.getRoles();
        this.restaurant = user.getRestaurant();
    }



    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
