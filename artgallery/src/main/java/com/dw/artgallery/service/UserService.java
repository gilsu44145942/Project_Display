package com.dw.artgallery.service;

import com.dw.artgallery.DTO.UserDTO;
import com.dw.artgallery.DTO.LoginDTO;
import com.dw.artgallery.jwt.TokenProvider;
import com.dw.artgallery.model.Authority;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.AuthorityRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.exception.InvalidRequestException;
import com.dw.exception.ResourceNotFoundException;
import com.dw.exception.UnauthorizedUserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    // 🔹 회원가입
    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {
        // 1. 중복 아이디 확인
        if (userRepository.existsById(userDTO.getUserId())) {
            throw new InvalidRequestException("이미 존재하는 사용자 ID입니다.");
        }

        // 2. 기본 권한 (USER) 가져오기
        Authority authority = authorityRepository.findByAuthorityName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("권한을 찾을 수 없습니다."));

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
        );

        // 5. 저장 후 DTO 변환
        return userRepository.save(user).toDTO();
    }

    //  JWT 로그인 (토큰 반환)
    public String loginUser(LoginDTO loginDTO) {
        User user = userRepository.findById(loginDTO.getUserId())
                .orElseThrow(() -> new InvalidRequestException("사용자 ID가 존재하지 않습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new UnauthorizedUserException("비밀번호가 틀렸습니다.");
        }

        // 🔥 JWT 토큰 생성 후 반환
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserId(), null);
        return tokenProvider.createToken(authentication);
    }

    //  로그아웃
    public void logoutUser(HttpSession session) {
        session.invalidate(); // 세션 무효화
    }

    // 모든 회원 조회 (관리자만 가능)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    //  realname으로 회원 조회 (관리자만 가능)
    public User getRealNameUser(String realname) {
        return userRepository.findByRealName(realname)  // 여기서도 변수명 일관되게 유지
                .orElseThrow(() -> new ResourceNotFoundException("입력하신 회원이 존재하지 않습니다."));
    }

    // 최근 가입한 유저순으로 조회 (관리지만 가능)
    public List<UserDTO> getRecentUsers() {
        List<User> users = userRepository.findAllByOrderByEnrolmentDateDesc();
        return users.stream().map(User::toDTO).toList();
    }

    // 포인트가 많은 유저순으로 조회 (관리자만 가능)
    public List<UserDTO> getTopUsersByPoints() {
        List<User> users = userRepository.findAllByOrderByPointDesc();
        return users.stream().map(User::toDTO).toList();  
    }














    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new UnauthorizedUserException("권한이 없습니다.");
        }

        String userName = (String) session.getAttribute("username");
        return userRepository.findById(userName)
                .orElseThrow(() -> new InvalidRequestException("유저명을 찾을 수 없습니다."));
    }
}
