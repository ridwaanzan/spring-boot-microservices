package com.microservice.membership.service;

import com.microservice.membership.dto.ConfirmResetPasswordOtpRequestDto;
import com.microservice.membership.dto.ResetPasswordRequestDto;
import com.microservice.membership.dto.ResponseTemplate;
import com.microservice.membership.model.OneTimePasswords;
import com.microservice.membership.model.Users;
import com.microservice.membership.repository.OneTimePasswordRepository;
import com.microservice.membership.repository.UsersRepository;
import com.microservice.membership.utils.EmailService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class ForgotPasswordService {

    private final UsersRepository usersRepository;
    private final OneTimePasswordRepository oneTimePasswordRepository;
    private final PasswordEncoder passwordEncoder;

    private final String STATUS_BELUM_TERVALIDASI = "BELUM TERVALIDASI";
    private final String STATUS_TERDAFTAR = "TERDAFTAR";
    private final String STATUS_TIDAK_TERDAFTAR = "TIDAK TERDAFTAR";
    private final String TYPE_USER_REGISTRATION = "USER_REGISTRATION";
    private final String TYPE_USER_FORGOT_PASSWORD = "USER_FORGOT_PASSWORD";

    @Autowired
    private EmailService emailService;

    @Autowired
    public ForgotPasswordService(UsersRepository usersRepository,
                                 OneTimePasswordRepository oneTimePasswordRepository,
                                 PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.oneTimePasswordRepository = oneTimePasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // reset password
    @Transactional
    public ResponseTemplate ResetPassword(ResetPasswordRequestDto request) {
        log.info("Start ResetPassword");
        log.info("ResetPassword Request: {}", request);

        if (!isUserExist(request.getEmail())) {
            return ResponseTemplate.builder()
                    .responseCode(4000)
                    .isSuccess(false)
                    .message("User Not Found.")
                    .payload(null)
                    .build();
        }
        Users userData  = usersRepository.findByEmail(request.getEmail()).get();
        Integer otp = generateOneTimePassword();
        OneTimePasswords oneTimePasswordsData = OneTimePasswords.builder()
                .user(userData)
                .otp(otp)
                .expiredDate(Timestamp.valueOf(LocalDateTime.now().plusHours(2)))
                .type(TYPE_USER_FORGOT_PASSWORD)
                .build();

        oneTimePasswordRepository.save(oneTimePasswordsData);

        // send mail
        String subject = "Your one time password - reset password";
        String body = "Your one time password (OTP) reset password is: " + otp;
        emailService.sendMail(userData.getEmail(), subject, body);

        return ResponseTemplate.builder()
                .responseCode(9000)
                .isSuccess(true)
                .message("Success requesting reset password. Please check your email.")
                .payload("")
                .build();
    }

    // confirm email reset OTP
    public ResponseTemplate ConfirmResetPasswordOtp(ConfirmResetPasswordOtpRequestDto request) {
        log.info("Start ConfirmResetPasswordOtp");
        log.info("ConfirmResetPasswordOtp Request: {}", request);

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return ResponseTemplate.builder()
                    .responseCode(4030)
                    .isSuccess(false)
                    .message("Validation error. Please confirm with the same password.")
                    .payload("")
                    .build();
        }

        if (!isUserExist(request.getEmail())) {
            return ResponseTemplate.builder()
                    .responseCode(4040)
                    .isSuccess(false)
                    .message("User Not Found.")
                    .payload("")
                    .build();
        }

        Optional<Users> optionalUser = usersRepository.findByEmail(request.getEmail());
        Users users = optionalUser.get();
        Optional<OneTimePasswords> optionalOneTimePasswords = oneTimePasswordRepository.findByUserIdAndOtpAndType(users.getId(),
                                                                                                                  request.getOtp(),
                                                                                                                  TYPE_USER_FORGOT_PASSWORD);

        if (!optionalOneTimePasswords.isPresent()) {
            return ResponseTemplate.builder()
                    .responseCode(4040)
                    .isSuccess(false)
                    .message("OTP Not Found.")
                    .payload(null)
                    .build();
        }

        OneTimePasswords oneTimePasswords = optionalOneTimePasswords.get();
        users.setPassword(passwordEncoder.encode(request.getPassword()));
        usersRepository.save(users);
        oneTimePasswordRepository.delete(oneTimePasswords);

        // send mail
        String subject = "Success reset password";
        String body = "You have successfully change your password.";
        emailService.sendMail(users.getEmail(), subject, body);

        return ResponseTemplate.builder()
                .responseCode(9000)
                .isSuccess(true)
                .message("Success confirm token. You can reset password now.")
                .payload(users)
                .build();
    }


    private Boolean isUserExist(String email) {
        Optional<Users> user = usersRepository.findByEmail(email);

        return user.isPresent() ? true : false;
    }

    private Integer generateOneTimePassword() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }
}
