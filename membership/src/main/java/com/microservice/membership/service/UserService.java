package com.microservice.membership.service;

import com.microservice.membership.dto.*;
import com.microservice.membership.model.CreditCards;
import com.microservice.membership.model.OneTimePasswords;
import com.microservice.membership.model.Users;
import com.microservice.membership.repository.CreditCardsRepository;
import com.microservice.membership.repository.OneTimePasswordRepository;
import com.microservice.membership.repository.UsersRepository;
import com.microservice.membership.utils.EncryptionCreditCard;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Component
@Slf4j
public class UserService {
    private final UsersRepository usersRepository;
    private final CreditCardsRepository creditCardsRepository;
    private final OneTimePasswordRepository oneTimePasswordRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EncryptionCreditCard encrypt;
    private final JwtService jwtService;

    private final String STATUS_BELUM_TERVALIDASI = "BELUM TERVALIDASI";
    private final String STATUS_TERDAFTAR = "TERDAFTAR";
    private final String STATUS_TIDAK_TERDAFTAR = "TIDAK TERDAFTAR";
    private final String TYPE_USER_REGISTRATION = "USER_REGISTRATION";
    private final String TYPE_USER_FORGOT_PASSWORD = "USER_FORGOT_PASSWORD";


    @Autowired
    public UserService(UsersRepository usersRepository,
                       CreditCardsRepository creditCardsRepository,
                       OneTimePasswordRepository oneTimePasswordRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       EncryptionCreditCard encrypt,
                       JwtService jwtService) {
        this.usersRepository = usersRepository;
        this.creditCardsRepository = creditCardsRepository;
        this.oneTimePasswordRepository = oneTimePasswordRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.encrypt = encrypt;
        this.jwtService = jwtService;
    }

    // User Registration
    @Transactional
    public ResponseTemplate RegisterUser(RegisterUserRequestDto request) {
        log.info("Start RegisterUserRequestDto");
        log.info("RegisterUserRequestDto Request: {}", request);

        if (isUserExist(request.getEmail())) {
            return ResponseTemplate.builder()
                    .responseCode(6000)
                    .isSuccess(false)
                    .message("Email already exist. Use other active email")
                    .payload(null)
                    .build();
        }

        CreditCardDto ccardRequestData = request.getCreditCardInfo();
        // save user
        Users userData = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .status(STATUS_BELUM_TERVALIDASI)
                .validationCode(null)
                .build();

        // save CC info
        CreditCards creditCardsData = CreditCards.builder()
                    .cardNumber(encrypt.encryptCreditCard(ccardRequestData.getCardNumber()))
                    .cvvNumber(encrypt.encryptCreditCard(ccardRequestData.getCvvNumber()))
                    .cardHolderName(encrypt.encryptCreditCard(ccardRequestData.getCardHolderName()))
                    .expiredDate(encrypt.encryptCreditCard(ccardRequestData.getExpiredDate()))
                    .build();

        // save OTP
        OneTimePasswords oneTimePasswordsData = OneTimePasswords.builder()
                .user(userData)
                .otp(generateOneTimePassword())
                .expiredDate(Timestamp.valueOf(LocalDateTime.now().plusHours(2)))
                .type(TYPE_USER_REGISTRATION)
                .build();

        Users savedUser = usersRepository.save(userData);
        creditCardsRepository.save(creditCardsData);
        oneTimePasswordRepository.save(oneTimePasswordsData);

        return ResponseTemplate.builder()
                .responseCode(9000)
                .isSuccess(true)
                .message("Registered")
                .payload(savedUser)
                .build();
    }

    // confirm email OTP
    @Transactional
    public ResponseTemplate ConfirmRegistrationOtp(ConfirmEmailOtpRequestDto request) {
        log.info("Start ConfirmRegistrationOtp");
        log.info("ConfirmRegistrationOtp Request: {}", request);

        if (!isUserExist(request.getEmail())) {
            return ResponseTemplate.builder()
                    .responseCode(4040)
                    .isSuccess(false)
                    .message("User Not Found.")
                    .payload(null)
                    .build();
        }

        Optional<Users> optionalUser = usersRepository.findByEmail(request.getEmail());
        Users users = optionalUser.get();
        Optional<OneTimePasswords> optionalOneTimePasswords = oneTimePasswordRepository.findByUserIdAndOtpAndType(users.getId(),
                                                                                                                  request.getOtp(),
                                                                                                                  TYPE_USER_REGISTRATION);
        if (!optionalOneTimePasswords.isPresent()) {
            return ResponseTemplate.builder()
                    .responseCode(4040)
                    .isSuccess(false)
                    .message("OTP Not Found.")
                    .payload(null)
                    .build();
        }

        OneTimePasswords oneTimePasswords = optionalOneTimePasswords.get();
        users.setStatus(STATUS_TIDAK_TERDAFTAR);
        usersRepository.save(users);
        oneTimePasswordRepository.delete(oneTimePasswords);

        return ResponseTemplate.builder()
                .responseCode(9000)
                .isSuccess(true)
                .message("Success confirm token. You can login now.")
                .payload(users)
                .build();
    }

    // login
    public ResponseTemplate LoginUser(LoginUserRequestDto request) {
        log.info("Start LoginUser");
        log.info("LoginUser Request: {}", request);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        Users userData = usersRepository.findByEmail(request.getEmail()).orElse(null);
        if (userData == null) {
            return ResponseTemplate.builder()
                    .responseCode(4040)
                    .isSuccess(false)
                    .message("Credentials not found.")
                    .payload(null)
                    .build();
        }

        String jwtToken = jwtService.generateToken(userData);

        return ResponseTemplate.builder()
                .responseCode(9000)
                .isSuccess(true)
                .message("Login Success.")
                .payload(userData)
                .token(jwtToken)
                .build();
    }

    private Boolean isUserExist(String email) {
        Optional<Users> user = this.usersRepository.findByEmail(email);

        return user.isPresent() ? true : false;
    }

    private Integer generateOneTimePassword() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }
}
