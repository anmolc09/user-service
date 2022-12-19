package com.learning.io.repositories;

import com.learning.io.Entity.AddressEntity;
import com.learning.io.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    List<AddressEntity> findAllByUserDetails(UserEntity userEntity);

    AddressEntity findByAddressId(String addressId);
}
