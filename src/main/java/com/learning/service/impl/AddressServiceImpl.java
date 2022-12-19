package com.learning.service.impl;

import com.learning.io.Entity.AddressEntity;
import com.learning.io.Entity.UserEntity;
import com.learning.io.repositories.AddressRepository;
import com.learning.io.repositories.UserRepository;
import com.learning.service.AddressService;
import com.learning.shared.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDTO> getAddresses(String userId) {
        List<AddressDTO> returnValue =new ArrayList<>();
        UserEntity userEntity = null;

        if (userRepository.findByUserId(userId).isPresent()) {
            userEntity = userRepository.findByUserId(userId).get();
        } else { return returnValue; }

        List<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);

        addresses.stream().forEach(addressEntity ->  returnValue.add(new ModelMapper().map(addressEntity,AddressDTO.class)));

        return returnValue;
    }

    @Override
    public AddressDTO getAddress(String addressId) {
        AddressDTO returnValue = null;
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        if(addressEntity!=null){
            returnValue=new ModelMapper().map(addressEntity,AddressDTO.class);
        }
        return returnValue;
    }
}
