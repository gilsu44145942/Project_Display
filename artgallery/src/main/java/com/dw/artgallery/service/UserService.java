package com.dw.artgallery.service;

import com.dw.artgallery.DTO.UserDTO;
import com.dw.artgallery.model.Authority;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.AuthorityRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.exception.InvalidRequestException;
import com.dw.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

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

    // 로그인
    public boolean validateUser(String userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new InvalidRequestException("Invalid Username"));
        return passwordEncoder.matches(password,user.getPassword());
    }
    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate(); // 세션 종료
        return new ResponseEntity<>(
                "You have been logged out.",
                HttpStatus.OK);
    }
}
