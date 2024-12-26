package Knowledge.WebApp.service;

import Knowledge.WebApp.dto.StoreDTO;
import Knowledge.WebApp.mapper.StoreMapper;
import Knowledge.WebApp.model.Store;
import Knowledge.WebApp.repository.StoreRepository;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements IStoreService {

    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public Store insertStore(StoreDTO storeDTO) throws Exception {
        Store store;

        try {
            store = storeRepository.save(StoreMapper.StoreToEntity(storeDTO));
            if (store.getId() == null) {
                throw new Exception("Insert Error");
            }
            System.out.println("Successfully inserted store with ID: " + store.getId());
        } catch (Exception e) {
            System.out.println("Error inserting store: " + e.getMessage());
            throw e;
        }
        return store;
    }

    @Transactional
    @Override
    public Store updateStore(StoreDTO storeDTO) throws EntityNotFoundException {
        Store store;
        try {
            store = storeRepository.findStoreById(storeDTO.getId());
            if (store == null) throw new EntityNotFoundException(Store.class, storeDTO.getId());
            store.setName(storeDTO.getName());
            store = storeRepository.save(store);
            System.out.println("Successfully updated store with ID: " + store.getId());
        } catch (EntityNotFoundException e) {
            System.out.println("Error updating store: " + e.getMessage());
            throw e;
        }
        return store;
    }


    @Transactional
    @Override
    public Store deleteStore(Long id) throws EntityNotFoundException {
        Store store;
        try {
            store = storeRepository.findStoreById(id);
            if (store == null) throw new EntityNotFoundException(Store.class, id);
            storeRepository.deleteById(id);
            System.out.println("Successfully deleted store with ID: " + id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error deleting store: " + e.getMessage());
            throw e;
        }
        return store;
    }

    @Override
    public List<Store> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        System.out.println("Retrieved all stores. Count: " + stores.size());
        return stores;
    }

    @Override
    public Store getStoreById(Long id) throws EntityNotFoundException {
        Store store;
        try {
            store = storeRepository.findStoreById(id) ;
            if (store == null) throw new EntityNotFoundException(Store.class, id);
            System.out.println("Successfully retrieved store with ID: " + id);
        } catch (EntityNotFoundException e) {
            System.out.println("Error retrieving store: " + e.getMessage());
            throw e;
        }
        return store;
    }
}
