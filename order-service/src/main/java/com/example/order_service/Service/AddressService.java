package com.example.order_service.Service;

import com.example.order_service.DTO.AddressDTO;
import com.example.order_service.Model.Address;

import java.util.List;


public interface AddressService {

    AddressDTO addAddress(Address address);
    List<AddressDTO> getAllAddress();
    AddressDTO getAddressById(Long addressId);
    AddressDTO deleteAddressById(Long addressId);

}
