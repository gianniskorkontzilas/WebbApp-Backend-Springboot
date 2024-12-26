package Knowledge.WebApp.service;

import Knowledge.WebApp.dto.StoreDTO;
import Knowledge.WebApp.model.Store;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;

import java.util.List;

public interface IStoreService {
    Store insertStore(StoreDTO storeDTO) throws Exception;
    Store updateStore(StoreDTO storeDTO) throws EntityNotFoundException;
    Store deleteStore(Long id) throws EntityNotFoundException;
    List<Store> getAllStores();
    Store getStoreById(Long id) throws EntityNotFoundException;
}
