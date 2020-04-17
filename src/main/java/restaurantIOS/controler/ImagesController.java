package restaurantIOS.controler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import restaurantIOS.models.Images;
import restaurantIOS.service.ImagesService;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/images")
public class ImagesController {
    Logger log = LoggerFactory.getLogger(this.getClass());


    private ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }


    @RequestMapping(value = "getImageOnID/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
    Optional<Images> photoDTO = imagesService.getPhoto(id);
    RandomAccessFile f = new RandomAccessFile(photoDTO.get().getImageLocation(), "r");
    byte[] b = new byte[(int)f.length()];
    f.readFully(b);
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_PNG);
    return new ResponseEntity<>(b, headers, HttpStatus.CREATED);

    }


    @GetMapping("/{id}")
    public Optional<Images> getPhoto(@PathVariable Integer id){
        return imagesService.getPhoto(id);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Images>> getAll(){
        List<Images> photos;
        photos = imagesService.getAll();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }


    @RequestMapping(value ="/uploadImage", method = RequestMethod.POST)
//   public String uploadImage(@PathVariable MultipartFile imageFile) throws Exception {

        public String uploadImage(@RequestParam("imageFile") @PathVariable MultipartFile imageFile) throws Exception {
        String returnValue = "Image uploaded!";
        Images images = new Images();
        images.setImagename(imageFile.getOriginalFilename());

        try {
            imagesService.saveImage(imageFile, images);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error saving photo", e);
            returnValue = "error";
        }
        return returnValue;
    }
}

