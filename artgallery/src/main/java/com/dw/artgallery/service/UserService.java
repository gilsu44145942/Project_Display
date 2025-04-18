package com.dw.artgallery.service;

import com.dw.artgallery.DTO.UserDTO;
import com.dw.artgallery.DTO.LoginDTO;
import com.dw.artgallery.DTO.UserGetDTO;
import com.dw.artgallery.jwt.TokenProvider;
import com.dw.artgallery.model.Authority;
import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.ArtRepository;
import com.dw.artgallery.repository.AuthorityRepository;
import com.dw.artgallery.repository.UserRepository;
import com.dw.artgallery.exception.InvalidRequestException;
import com.dw.artgallery.exception.ResourceNotFoundException;
import com.dw.artgallery.exception.UnauthorizedUserException;
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
import java.util.stream.Collectors;

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

    @Autowired
    private ArtRepository artRepository;

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

    // 모든 유저 조회
    public List<UserGetDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    //  realname으로 회원 조회 (관리자만 가능)
    public UserGetDTO getRealNameUser(String realname) {
        User user = userRepository.findByRealName(realname).orElseThrow(()-> new ResourceNotFoundException("일치하는 회원이 없습니다."));
        return convertToDTO(user);
    }

    // 최근 가입한 유저순으로 조회 (관리지만 가능)
    public List<UserGetDTO> getRecentUsers() {
        return userRepository.findAllByOrderByEnrolmentDateDesc()
                .stream()
                .map(this::convertToDTO)
                .toList();

    }

    // 포인트가 많은 유저순으로 조회 (관리자만 가능)
    public List<UserGetDTO> getTopUsersByPoints() {
        return  userRepository.findAllByOrderByPointDesc()
         .stream().map(this::convertToDTO).toList();
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

    //  UserID로 회원 조회
    public UserGetDTO getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 ID의 사용자를 찾을 수 없습니다: " + userId));
        return convertToDTO(user);
    }

    //  Nickname으로 회원 조회
    public List<UserGetDTO> getUsersByNickname(String nickname) {
        return userRepository.findByNickName(nickname)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //  Email로 회원 조회
    public UserGetDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("해당 이메일의 사용자를 찾을 수 없습니다: " + email));
        return convertToDTO(user);
    }

    //  Address로 회원 조회
    public List<UserGetDTO> getUsersByAddress(String address) {
        return userRepository.findByAddress(address)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserGetDTO convertToDTO(User user) {
        return new UserGetDTO(
                user.getUserId(),
                user.getNickName(),
                user.getRealName(),
                user.getEmail(),
                user.getBirthday(),
                user.getAddress(),
                user.getEnrolmentDate(),
                user.getPoint(),
                user.getGender()
        );
    }
}

