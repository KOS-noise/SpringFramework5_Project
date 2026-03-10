package mylab.user.annot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        // 1. UserService 주입 검증
        assertNotNull(userService, "UserService가 null입니다.");

        // 2. UserRepository 주입 검증
        assertNotNull(userService.getUserRepository(), "UserRepository가 null입니다.");

        // 3. dbType 값 검증 (기대값, 실제값, 메시지 순서)
        assertEquals("MySQL", userService.getUserRepository().getDbType(), "DB 타입이 일치하지 않습니다.");

        // 4. SecurityService 주입 검증
        assertNotNull(userService.getSecurityService(), "SecurityService가 null입니다.");

        // 5. registerUser() 메서드 검증
        boolean isRegistered = userService.registerUser("user01", "홍길동", "pass1234");
        assertTrue(isRegistered, "사용자 등록에 실패했습니다.");
    }
}