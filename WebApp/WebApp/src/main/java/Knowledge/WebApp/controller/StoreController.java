package Knowledge.WebApp.controller;

import Knowledge.WebApp.dto.StoreDTO;
import Knowledge.WebApp.model.Store;
import Knowledge.WebApp.service.IStoreService;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
    private final IStoreService storeService;

    @Autowired
    public StoreController(IStoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<Store> insertStore(@RequestBody StoreDTO storeDTO) {
        try {
            Store store = storeService.insertStore(storeDTO);
            logger.info("Successfully inserted store with ID: {}", store.getId());
            return new ResponseEntity<>(store, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error inserting store: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        try {
            storeDTO.setId(id);
            Store store = storeService.updateStore(storeDTO);
            logger.info("Successfully updated store with ID: {}", store.getId());
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Error updating store: Store not found with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        try {
            storeService.deleteStore(id);
            logger.info("Successfully deleted store with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            logger.error("Error deleting store: Store not found with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        logger.info("Successfully retrieved all stores. Count: {}", stores.size());
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        try {
            Store store = storeService.getStoreById(id);
            logger.info("Successfully retrieved store with ID: {}", id);
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            logger.error("Error retrieving store: Store not found with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

