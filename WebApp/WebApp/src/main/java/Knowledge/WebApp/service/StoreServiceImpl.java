package Knowledge.WebApp.service;

import Knowledge.WebApp.dto.StoreDTO;
import Knowledge.WebApp.mapper.StoreMapper;
import Knowledge.WebApp.model.Store;
import Knowledge.WebApp.repository.StoreRepository;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements IStoreService {

    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

@Transactional
@Override
    public Store insertStore(StoreDTO storeDTO) throws Exception {
        Store store;
        try {
            store = storeRepository.save(StoreMapper.StoreToEntity(storeDTO));
            if (store.getId() == null) {
                throw new Exception("Insert Error");
            }
            logger.info("Successfully inserted store with ID: {}", store.getId());
        } catch (Exception e) {
            logger.error("Error inserting store: {}", e.getMessage(), e);
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
            if (store == null) {
                throw new EntityNotFoundException(Store.class, storeDTO.getId());
            }
            store.getCustomers().size();
            store.setName(storeDTO.getName());
            store = storeRepository.save(store);
            logger.info("Successfully updated store with ID: {}", store.getId());
        } catch (EntityNotFoundException e) {
            logger.error("Error updating store with ID {}: {}", storeDTO.getId(), e.getMessage(), e);
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
            if (store == null) {
                throw new EntityNotFoundException(Store.class, id);
            }
            storeRepository.deleteById(id);
            logger.info("Successfully deleted store with ID: {}", id);
        } catch (EntityNotFoundException e) {
            logger.error("Error deleting store with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
        return store;
    }

    @Override
    public List<Store> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        logger.info("Successfully retrieved all stores. Count: {}", stores.size());
        return stores;
    }




    @Override
    public Store getStoreById(Long id) throws EntityNotFoundException {
        Store store;
        try {
            store = storeRepository.findStoreById(id);
            if (store == null) {
                throw new EntityNotFoundException(Store.class, id);
            }
            logger.info("Successfully retrieved store with ID: {}", id);
        } catch (EntityNotFoundException e) {
            logger.error("Error retrieving store with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
        return store;
    }
}
