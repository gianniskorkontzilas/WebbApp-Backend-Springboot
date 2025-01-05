package Knowledge.WebApp.controller;

import Knowledge.WebApp.dto.StoreDTO;
import Knowledge.WebApp.model.Store;
import Knowledge.WebApp.service.IStoreService;
import Knowledge.WebApp.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final IStoreService storeService;

    @Autowired
    public StoreController(IStoreService storeService) {
        this.storeService = storeService;
    }
    @PostMapping
    public ResponseEntity<Store> insertStore(@RequestBody StoreDTO storeDTO) {
        try {
            Store store = storeService.insertStore(storeDTO);
            return new ResponseEntity<>(store, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error inserting store: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Store> updateStore(@RequestBody StoreDTO storeDTO) {
        try {
            Store store = storeService.updateStore(storeDTO);
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println("Error updating store: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Store> deleteStore(@PathVariable Long id) {
        try {
            Store store = storeService.deleteStore(id);
            return new ResponseEntity<>(store, HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            System.out.println("Error deleting store: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        try {
            Store store = storeService.getStoreById(id);
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.println("Store with ID {} not found" + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
