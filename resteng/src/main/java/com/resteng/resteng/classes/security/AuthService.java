package com.resteng.resteng.classes.security;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.resteng.resteng.classes.entity.RoleRepo;
import com.resteng.resteng.classes.mainUser.MainUser;
import com.resteng.resteng.classes.mainUser.MainUserRepo;
import com.resteng.resteng.classes.mainUser.MainUserServece;
import com.resteng.resteng.classes.tokenInfo.TokenInfo;
import com.resteng.resteng.classes.tokenInfo.TokenInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;

    private final HttpServletRequest httpRequest;

    private final TokenInfoService tokenInfoService;

    private final MainUserRepo mainUserRepo;

    private final JwtTokenUtils jwtTokenUtils;

    private final PasswordEncoder encoder;
    private final RoleRepo roleRepo;

    private final MainUserServece mainUserServece;

    public JWTResponseDto login(String login, String password) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password));

        log.debug("Valid userDetails credentials.");

        MainUserDetails userDetails = (MainUserDetails) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("SecurityContextHolder updated. [login={}]", login);

        TokenInfo tokenInfo = createLoginToken(login, userDetails.getId(), userDetails.getRole().getId());

        return JWTResponseDto.builder()
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
    }

    public TokenInfo createLoginToken(String userName, Long userId, long roleId) {
        String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String accessTokenId = UUID.randomUUID().toString();
        String accessToken = JwtTokenUtils.generateToken(userName, accessTokenId, false, roleId);
        log.info("Access token created. [tokenId={}]", accessTokenId);

        String refreshTokenId = UUID.randomUUID().toString();
        String refreshToken = JwtTokenUtils.generateToken(userName, refreshTokenId, true, roleId);
        log.info("Refresh token created. [tokenId={}]", accessTokenId);

        TokenInfo tokenInfo = new TokenInfo(accessToken, refreshToken);
        Optional<MainUser> mainUser = mainUserRepo.findUserByUsername(userName);
        tokenInfo.setUser(mainUser.get());
        tokenInfo.setUserAgentText(userAgent);
        tokenInfo.setLocalIpAddress(ip.getHostAddress());
        tokenInfo.setRemoteIpAddress(httpRequest.getRemoteAddr());
        // tokenInfo.setLoginInfo(createLoginInfoFromRequestUserAgent());
        return tokenInfoService.save(tokenInfo);
    }

    public AccessTokenDto refreshAccessToken(String refreshToken) {
        if (jwtTokenUtils.isTokenExpired(refreshToken)) {
            return null;
        }
        String userName = jwtTokenUtils.getUserNameFromToken(refreshToken);
        long roleId = Integer.parseInt(jwtTokenUtils.getRoleIdFromToken(refreshToken));
        Optional<TokenInfo> refresh = tokenInfoService.findByRefreshToken(refreshToken);
        if (!refresh.isPresent()) {
            return null;
        }

        return new AccessTokenDto(JwtTokenUtils.generateToken(userName, UUID.randomUUID().toString(), false, roleId));

    }

    public void logoutUser(String refreshToken) {
        Optional<TokenInfo> tokenInfo = tokenInfoService.findByRefreshToken(refreshToken);
        if (tokenInfo.isPresent()) {
            tokenInfoService.deleteById(tokenInfo.get().getId());
        }

    }

    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            if (mainUserRepo.findUserByUsername(signUpRequest.getUsername()) != null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }
    
            // Create new user's account
            MainUser user = new MainUser(signUpRequest.getPassword(),
                    signUpRequest.getUsername());
    
            MainUser savedUser = mainUserServece.newUser(user,signUpRequest.getRole());
            if (savedUser == null) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MessageResponse("Error: Failed to save user."));
            }
    
            mainUserRepo.save(savedUser);
    
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error: An unexpected error occurred."));
        }
    }

}
