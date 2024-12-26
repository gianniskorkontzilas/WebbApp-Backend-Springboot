package Knowledge.WebApp.mapper;

import Knowledge.WebApp.dto.StoreDTO;
import Knowledge.WebApp.model.Store;

public class StoreMapper {

    private StoreMapper() {
    }

    public static StoreDTO StoreToDTO(Store store) {
        if (store == null) {
            return null;
        }
        return new StoreDTO(store.getId(), store.getName());
    }

    public static Store StoreToEntity(StoreDTO storeDTO) {
        if (storeDTO == null) {
            return null;
        }
        return new Store(storeDTO.getId(), storeDTO.getName(), null);
    }
}

