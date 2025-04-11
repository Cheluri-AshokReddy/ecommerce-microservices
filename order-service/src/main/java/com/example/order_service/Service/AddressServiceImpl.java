package com.example.order_service.Service;

import com.example.order_service.DTO.AddressDTO;
import com.example.order_service.Exceptions.ResourceNotFoundException;
import com.example.order_service.Model.Address;
import com.example.order_service.Repository.AddressRegistery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRegistery addressRegistery;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressDTO addAddress(Address address) {
        Address address1=addressRegistery.save(address);
        return modelMapper.map(address1, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAllAddress() {
        List<Address> addressList=addressRegistery.findAll();
        List<AddressDTO> addressDTOList=addressList
                .stream()
                .map(address->modelMapper.map(address,AddressDTO.class))
                .toList();
        return addressDTOList;
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address=addressRegistery.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address","addressId",addressId));
        return modelMapper.map(address,AddressDTO.class);
    }

    @Override
    public AddressDTO deleteAddressById(Long addressId) {
        Address address=addressRegistery.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address","addressId",addressId));
        addressRegistery.delete(address);
        return modelMapper.map(address,AddressDTO.class);
    }
}
