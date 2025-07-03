package aibook.infra;

import aibook.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
@Transactional
public class UserController {

    @Autowired
    UserRepository userRepository;

    // ✅ 구독 구매
    @PutMapping(
        value = "/users/{id}/buysubscription",
        produces = "application/json;charset=UTF-8"
    )
    public User buySubscription(
        @PathVariable(value = "id") Long id,
        @RequestBody BuySubscriptionCommand buySubscriptionCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /user/buySubscription called #####");
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.orElseThrow(() -> new Exception("No Entity Found"));
        User user = optionalUser.get();
        user.buySubscription(buySubscriptionCommand);
        userRepository.save(user);
        return user;
    }

    // ✅ 로그인
    @PostMapping("/users/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new Exception("존재하지 않는 이메일입니다.");
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공
        Boolean isAuthor = user.getIsAuthor() != null ? user.getIsAuthor() : false;

        return new LoginResponse(
            user.getId(),
            user.getUserName(),
            "로그인 성공",
            isAuthor
        );
    }
}
