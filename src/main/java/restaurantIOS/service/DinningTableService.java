package restaurantIOS.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import restaurantIOS.models.DinningTable;
import restaurantIOS.repository.DinningTableRepository;
import java.util.Collections;
import java.util.List;

@Service
public class DinningTableService {

    private DinningTableRepository dinningTableRepository;

    public DinningTableService(DinningTableRepository dinningTableRepository) {
        this.dinningTableRepository = dinningTableRepository;
    }

    @Transactional
    public List<DinningTable> getAll(){
        return dinningTableRepository.findAll();
    }

    @Transactional
    public List<DinningTable> getDinningTable(Integer id){
        return dinningTableRepository.findAllById(Collections.singleton(id));
    }



    @Transactional
    public List<DinningTable>save(DinningTable dinningTable){
        dinningTableRepository.save(dinningTable);
        return dinningTableRepository.findAll();
    }

    @Transactional
    public DinningTable getSpecificDinningTable (int table_number, int id_restaurant) {
            return dinningTableRepository.getSpecificDinningTable(table_number, id_restaurant);
    }


}






