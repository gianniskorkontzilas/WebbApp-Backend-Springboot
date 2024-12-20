package Knowledge.WebApp.mapper;


import Knowledge.WebApp.dto.StoreDTO;
import Knowledge.WebApp.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public StoreDTO toDTO(Store store) {
        if (store == null) {
            return null;
        }
        return new StoreDTO(store.getId(), store.getName());
    }



    public Store toEntity(StoreDTO storeDTO) {
        if (storeDTO == null) {
            return null;
        }
        return new Store(storeDTO.getId(), storeDTO.getName(), null);
    }


}
