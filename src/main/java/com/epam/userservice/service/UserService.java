package com.epam.userservice.service;

import com.epam.userservice.entity.UserDao;
import com.epam.userservice.model.UserDto;
import com.epam.userservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public UserDto getUserDetails(String userEmailId) {
        UserDao userDao = userRepository.findByEmailId(userEmailId).orElse(null);
        if (userDao == null) return new UserDto();
        return objectMapper.convertValue(userDao, UserDto.class);
    }

    public boolean registerNewUser(UserDto userDto) {
        if(userRepository.findByEmailId(userDto.getEmailId()).isEmpty()) {
            UserDao userDao = objectMapper.convertValue(userDto, UserDao.class);
            userDao.setPassword(get_SHA_512_SecurePassword(userDto.getPassword(), "SALT512"));
            userRepository.save(userDao);
            return true;
        } else return false;
    }

    public boolean removeUserByEmailId(String userEmailId) {
        UserDao userDao = userRepository.findByEmailId(userEmailId).orElse(null);
        if(userDao == null) {
            return false;
        } else {
            userRepository.delete(userDao);
            return true;
        }
    }

    public UserDto updateUserDetails(UserDto userDto) {
        UserDao userDao = userRepository.findByEmailId(userDto.getEmailId()).orElse(null);
        if(userDao == null) return null;
        userDao.setFirstName(userDto.getFirstName());
        userDao.setLastName(userDto.getLastName());
        userDao.setPanNumber(userDto.getPanNumber());
        userDao.setBankAccountNumber(userDto.getBankAccountNumber());
        userRepository.save(userDao);
        return userDto;
    }

    public UserDto loginUser(String userEmailId, String userPassword) {
        UserDao userDao = userRepository.findByEmailId(userEmailId).orElse(null);
        String securePassword = get_SHA_512_SecurePassword(userPassword, "SALT512");
        if(userDao != null || userDao.getPassword().equals(securePassword)) {
            return objectMapper.convertValue(userDao, UserDto.class);
        }
        return null;
    }

    private String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
