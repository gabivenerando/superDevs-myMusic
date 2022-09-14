package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.User;
import com.ciandt.summit.bootcamp2022.domain.UserType;
import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Test
    void getUSerByIdSuccess() {
        Playlist playlist = new Playlist("986490556834gfg");
        UserType userType = new UserType("490684309534", "type");
        User user = new User("6509654906845fdd", "Lalaland", playlist, userType);

        Mockito.when(userRepositoryPort.getUserById("87347985jdrter")).thenReturn(user);
        UserDTO userDTO = userService.getUserById("87347985jdrter");
        Assertions.assertNotNull(userDTO);
    }

    @Test
    void getUserByIdFailure_UserNotFound() {
        Mockito.when(userRepositoryPort.getUserById("87347985jdrter")).thenThrow(new InvalidParameterException("Usuário inexistente"));
        Assertions.assertThrows(InvalidParameterException.class, () -> userService.getUserById("87347985jdrter"));

    }
}
