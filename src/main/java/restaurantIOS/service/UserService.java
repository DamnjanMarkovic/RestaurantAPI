package restaurantIOS.service;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import restaurantIOS.error.EntityNotFoundException;
import restaurantIOS.models.Images;
import restaurantIOS.models.Role;
import restaurantIOS.models.dto.MyLoginDetails;
import restaurantIOS.models.User;
import restaurantIOS.models.dto.UserRequest;
import restaurantIOS.models.dto.UserResponse;
import restaurantIOS.repository.ImagesRepository;
import restaurantIOS.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final ImagesRepository imagesRepository;

    public UserService(UserRepository userRepository, ImagesRepository imagesRepository) {
        this.userRepository = userRepository;
        this.imagesRepository = imagesRepository;
    }

    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyLoginDetails::new).get();
    }
    @Transactional
    public List<Images> getUsersPhotos() {
        List<Integer> listUsersIDs = userRepository.getUsersIDs();
        return getAllUsersPhotos(listUsersIDs);
    }



    private List<Images> getAllUsersPhotos(List<Integer> listUsersIDs) {
        Images imageUser = new Images();
        List<Images> listImageUsers = new ArrayList<>();
        for (Integer inter: listUsersIDs             ) {
            imageUser = imagesRepository.getSpecificPhotos(inter);
            if (imageUser!=null) {
                if (!listImageUsers.contains(imageUser)) {
                    listImageUsers.add(imageUser);
                }
            }
        }
        return listImageUsers;
    }


    @Transactional
    public List<UserResponse> getAll(){
        List<User> allUsers = userRepository.findAll();
        return returnUsersFormated(allUsers);
    }



    @Transactional
    public String save(UserRequest userRequest) throws SQLException {
    String result = null;
        User user = new User(userRequest.getUserName(), userRequest.getPassword(),
                true, userRequest.getUserFirstName(), userRequest.getImageLink(),
                null, null);
        userRepository.save(user);
        Integer idNewUser = userRepository.getSpecificUser(user.getUserFirstName());
        userRepository.connectUserAndRestaurant(userRequest.getId_restaurant(), idNewUser);
        Integer id_role = userRepository.getIDRoleBasedOnRole(userRequest.getRole());
        userRepository.connectUserAndRoles(idNewUser, id_role);
        result = "User inserted in the DB";
        return result;
    }


    public List<UserResponse> returnUsersFormated(List<User> allUsers){
        List<UserResponse> listUserResponse = new ArrayList<>();
        for (User us : allUsers) {
            UserResponse userResponse = new UserResponse(us.getId(), us.getUserFirstName(),
                    us.getImageLink(), us.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()),
                    us.getRestaurant().getId_restaurant());
            listUserResponse.add(userResponse);
        }
        return listUserResponse;
    }

    @Transactional
    public List<UserResponse> getUser(Integer id) {
        List<User> allUsers = userRepository.findAllById(Collections.singleton(id));
        List<UserResponse>listResponse = returnUsersFormated(allUsers);
        if (listResponse.isEmpty()) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }
        return listResponse;
    }
    @Transactional
    public Optional<User> getUserOnUsername(String userFirstName) {

        User userold = new User();
        userold.setUserFirstName(userFirstName);
        Example<User> user = Example.of(userold);
        return userRepository.findOne(user);
    }
}

