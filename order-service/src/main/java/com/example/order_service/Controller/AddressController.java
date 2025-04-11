package com.example.order_service.Controller;

import com.example.order_service.DTO.AddressDTO;
import com.example.order_service.Model.Address;
import com.example.order_service.Service.AddressServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressServiceImpl;

    private static final Logger LOGGER
            = LoggerFactory.getLogger(AddressController.class);

    @PostMapping("/add")
    public ResponseEntity<AddressDTO> addAddress(@RequestBody Address address) {
        AddressDTO savedAddress = addressServiceImpl.addAddress(address);
        LOGGER.info("Address add: {}", address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressDTO>> getAllAddress() {
        List<AddressDTO> addresses = addressServiceImpl.getAllAddress();
        LOGGER.info("Address findAll");
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        AddressDTO address = addressServiceImpl.getAddressById(id);
        LOGGER.info("Address find: id={}", id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AddressDTO> deleteAddressById(@PathVariable Long id) {
        AddressDTO deletedAddress = addressServiceImpl.deleteAddressById(id);
        LOGGER.info("Address delete: {}", id);
        return new ResponseEntity<>(deletedAddress, HttpStatus.OK);
    }


}
