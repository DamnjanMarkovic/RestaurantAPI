package restaurantIOS.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import restaurantIOS.models.Images;
import restaurantIOS.models.dto.UserRequest;
import restaurantIOS.models.dto.UserResponse;
import restaurantIOS.service.ImagesService;
import restaurantIOS.service.UserService;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")

public class UserController {

	@Autowired
	private final UserService userService;

	@Autowired
	private ImagesService imagesService;

	public UserController( UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/all")
	public List<UserResponse> getAll() {
		return userService.getAll();
	}

	@GetMapping("/{id}")
	public List<UserResponse> getUser(@PathVariable Integer id) throws EntityNotFoundException {

		return userService.getUser(id);
	}


	@PostMapping(value = "/load")
	public String loadUser (@Valid @RequestBody UserRequest userRequest){
		String result = null;
		String response = null;
		try {
			response = userService.save(userRequest);
				result = response;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	@PostMapping(value = "/loadUser", consumes = {"multipart/form-data"})
	public String saveUser (@RequestParam("imageFile") @PathVariable MultipartFile imageFile,
							UserRequest userRequest
							){
		String result = null;
		String response = null;
		Images images = new Images();
		images.setImagename(imageFile.getOriginalFilename());
		try {
			Integer id_image = imagesService.saveSpecificImage(imageFile, images);
			userRequest.setImageLink((id_image).toString());
			response = userService.save(userRequest);
			result = response;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}




}
