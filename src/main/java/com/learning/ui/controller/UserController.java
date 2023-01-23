package com.learning.ui.controller;

import com.learning.exceptions.UserServiceException;
import com.learning.service.AddressService;
import com.learning.service.UserService;
import com.learning.shared.dto.AddressDTO;
import com.learning.shared.dto.UserDto;
import com.learning.ui.model.request.UserDetailsRequestModel;
import com.learning.ui.model.response.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id) {
        return modelMapper.map(userService.getUserByUserId(id), UserRest.class);
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

        if (userDetails.getFirstName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    @PutMapping("/{id}")
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {

        UserDto userDto = new UserDto();
        modelMapper.map(userDetails,UserDto.class);
        UserDto updateUser = userService.updateUser(id, userDto);
        return modelMapper.map(updateUser,UserRest.class);
    }

    @DeleteMapping("/{id}")
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperatonName(RequestOperationName.DELETE.name());
        userService.deleteUser(id);
        returnValue.setOperatonResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }

    @GetMapping
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserRest> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page, limit);
        for (UserDto userDto : users) {
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(userDto, userModel);
            returnValue.add(userModel);
        }
        return returnValue;
    }

    @GetMapping("/{id}/addresses")
    public List<AddressesRest> getUserAddresses(@PathVariable String id) {

        List<AddressesRest> returnValue = new ArrayList<>();
        List<AddressDTO> addressesDTO = addressService.getAddresses(id);

        if (addressesDTO != null && !addressesDTO.isEmpty()) {
            Type listType = new TypeToken<List<AddressesRest>>() {}.getType();
            returnValue = modelMapper.map(addressesDTO, listType);
        }
        return returnValue;
    }

    @GetMapping("/{userId}/addresses/{addressId}")
    public AddressesRest getUserAddress(@PathVariable String addressId) {
        AddressDTO addressesDto = addressService.getAddress(addressId);
        return modelMapper.map(addressesDto,AddressesRest.class);
    }
}
