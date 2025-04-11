package com.example.order_service.Repository;

import com.example.order_service.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRegistery extends JpaRepository<Address, Long> {
}
