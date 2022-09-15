package com.ciandt.summit.bootcamp2022.application.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.PlaylistDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.UserTypeDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServicePort userServicePort;

    public static String parseJson(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    UserDTO userDTO;
    PlaylistDTO playlistDTO;
    UserTypeDTO userTypeDTO;

    @BeforeEach
    void setup(){
        playlistDTO = new PlaylistDTO("9kfioefke9r0");
        userTypeDTO = new UserTypeDTO("9039483920jfef", "Comum");
        userDTO = new UserDTO("ifjse9re9r9898", "lalaland", playlistDTO, userTypeDTO);
    }

    @Test
    void getUserSuccess() throws Exception {
        Mockito.when(userServicePort.getUserById("98")).thenReturn(userDTO);
        mvc.perform(get("/api/users/" + userDTO.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getUserFailure() throws Exception {
        Mockito.when(userServicePort.getUserById(" ")).thenThrow(new InvalidParameterException("Um id deve ser informado"));
        mvc.perform(get("/api/users/" + " "))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
