package com.myApp.springCRUD.repository;

import com.myApp.springCRUD.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer>, JpaRepository<Address, Integer> {

}
