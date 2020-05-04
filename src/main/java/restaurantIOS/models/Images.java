package restaurantIOS.models;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="images")

public class Images implements Serializable {
	@Id
	@GeneratedValue
	private int id_image;
	private String imageLocation;
	private String imagename;


	public int getId_image() {
		return id_image;
	}

	public void setId_image(int id_image) {
		this.id_image = id_image;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

}
