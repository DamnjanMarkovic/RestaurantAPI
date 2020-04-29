package restaurantIOS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restaurantIOS.models.Images;

import java.util.List;


public interface ImagesRepository extends JpaRepository<Images, Integer> {
    @Query(value = "SELECT images.id_image FROM images WHERE images.imagename = ?",
            nativeQuery = true)
    List<Integer> getSpecificPhoto(String role);


    @Query(value = "SELECT * FROM images WHERE images.id_image = ?",
            nativeQuery = true)
    Images getSpecificPhotos(Integer id_image);

    @Query(value = "select count(*) from images WHERE imagename = ?",
            nativeQuery = true)
    Integer checkImagetexistance(String imagename);

    @Query(value = "SELECT id_image FROM images WHERE imagename = ?",
            nativeQuery = true)
    Integer returnImageIDBasedOnImageName(String imagename);


    /*
    @Query("SELECT p.id_image from PhotoDTO p where p.imagename =(:stiglo)")
    Integer getSpecificPhoto(String stiglo);
*/
}
