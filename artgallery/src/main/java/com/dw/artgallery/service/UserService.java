package com.dw.artgallery.service;

import com.dw.artgallery.DTO.UserDTO;
import com.dw.artgallery.model.Authority;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.AuthorityRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.exception.InvalidRequestException;
import com.dw.exception.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {
        // 1. 중복 아이디 확인
        if (userRepository.existsById(userDTO.getUserId())) {
            throw new InvalidRequestException("Username already exists");
        }

        // 2. 기본 권한 (USER) 가져오기
        Authority authority = authorityRepository.findByAuthorityName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("No role found"));

        // 3. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // 4. User 객체 생성
        User user = new User(
                userDTO.getUserId(),
                encodedPassword,
                userDTO.getNickName(),
                userDTO.getRealName(),
                userDTO.getEmail(),
                userDTO.getBirthday(),
                userDTO.getAddress(),
                LocalDate.now(),
                userDTO.getPoint(),
                userDTO.getGender(),
                authority
                // 가입 날짜 기본값 설정
        );

        // 5. 저장 후 DTO 변환
        return userRepository.save(user).toDTO();
    }
}
