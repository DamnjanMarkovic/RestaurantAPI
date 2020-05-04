package restaurantIOS.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import restaurantIOS.models.Images;
import restaurantIOS.repository.ImagesRepository;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagesService {

    private final ImagesRepository imagesRepository;

    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Transactional
    public void saveImage(MultipartFile imageFile, Images images) throws Exception {
        savePhotoImage(imageFile, images);
        save(images);


    }
    @Transactional
    public Integer saveSpecificImage(MultipartFile imageFile, Images images) throws Exception {
        savePhotoImage(imageFile, images);
        Integer id_image =0;
         if (imagesRepository.checkImagetexistance(images.getImagename()) > 0) {
            id_image = imagesRepository.returnImageIDBasedOnImageName(images.getImagename());
        } else {
            id_image = imagesRepository.save(images).getId_image();
        }
        return id_image;
    }



    public void savePhotoImage(MultipartFile imageFile, Images images) throws Exception {
        images.setImageLocation("src/main/java/restaurantIOS/images/" + images.getImagename());
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(images.getImageLocation());
        Files.write(path, bytes);
    }


    public void save(Images images) {

        imagesRepository.save(images);
    }

    @Transactional
    public Optional<Images> getPhoto(Integer id) {
        return imagesRepository.findById(id);
    }



    @Transactional
    public Optional<Images> getPhotoOnName(String imagename) {

        Images imagesArrived = new Images();
        imagesArrived.setImagename(imagename);
        Example<Images> photoDTO = Example.of(imagesArrived);
        return imagesRepository.findOne(photoDTO);

    }

    @Transactional
    public List<Images> getAll(){
        return imagesRepository.findAll();
    }
}