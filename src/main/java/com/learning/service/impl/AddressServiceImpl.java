package com.learning.service.impl;

import com.learning.io.Entity.AddressEntity;
import com.learning.io.Entity.UserEntity;
import com.learning.io.repositories.AddressRepository;
import com.learning.io.repositories.UserRepository;
import com.learning.service.AddressService;
import com.learning.shared.dto.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private  final ModelMapper modelMapper;

    @Override
    public List<AddressDTO> getAddresses(String userId) {
        List<AddressDTO> addressDTOList =new ArrayList<>();
        UserEntity userEntity = null;

        if (userRepository.findByUserId(userId).isPresent()) {
            userEntity = userRepository.findByUserId(userId).get();
        } else { return addressDTOList; }

        List<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);

        addresses.stream().forEach(addressEntity ->  addressDTOList.add(modelMapper.map(addressEntity,AddressDTO.class)));

        return addressDTOList;
    }

    @Override
    public AddressDTO getAddress(String addressId) {
        AddressDTO addressDTO = null;
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        if(addressEntity!=null){
            addressDTO=modelMapper.map(addressEntity,AddressDTO.class);
        }
        return addressDTO;
    }
}
