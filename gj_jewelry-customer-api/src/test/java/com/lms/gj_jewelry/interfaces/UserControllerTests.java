package com.lms.gj_jewelry.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lms.gj_jewelry.GjJewelryCustomerApiApplication;
import com.lms.gj_jewelry.application.UserService;
import com.lms.gj_jewelry.domain.User;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.lms.gj_jewelry.test.data_check.JsonObjectConverter.convertObjectToJson;
import static com.lms.gj_jewelry.test.data_check.MvcResultChecker.isMvcResultEqualTo;
import static com.lms.gj_jewelry.test.random.RandomUserGenerator.generateRandomUser;
import static com.lms.gj_jewelry.test.random.RandomUserGenerator.generateRandomUserList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GjJewelryCustomerApiApplication.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    private User testUser;

    @Before
    public void setUp() {
        testUser = generateRandomUser();
    }

    @Test
    public void testCreateUser() throws Exception {
        given(userService.createUser(any())).willReturn(testUser);

        MvcResult result = mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(testUser)))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(isMvcResultEqualTo(testUser, result, User.class), is(true));
    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> users = generateRandomUserList(5);

        given(userService.getUsers()).willReturn(users);

        MvcResult result = mvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(users, result, new TypeReference<List<User>>(){}), is(true));
    }

    @Test
    public void testGetUserById() throws Exception {
        given(userService.getUserById(any())).willReturn(testUser);

        MvcResult result = mvc.perform(get("/user/id/" + testUser.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testUser, result, User.class), is(true));
    }

    @Test
    public void testGetUserByAccount() throws Exception {
        given(userService.getUserByAccount(any())).willReturn(testUser);

        MvcResult result = mvc.perform(get("/user/account/" + testUser.getEmail()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testUser, result, User.class), is(true));
    }

    @Test
    public void testGetUserByPhoneNumber() throws Exception {
        given(userService.getUserByPhoneNumber(any())).willReturn(testUser);

        MvcResult result = mvc.perform(get("/user/phoneNumber/" + testUser.getEmail()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testUser, result, User.class), is(true));
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        given(userService.getUserByEmail(any())).willReturn(testUser);

        MvcResult result = mvc.perform(get("/user/email/" + testUser.getEmail()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testUser, result, User.class), is(true));
    }

    @Test
    public void testUpdateUser() throws Exception {
        given(userService.updateUser(any())).willReturn(testUser);

        MvcResult result = mvc.perform(patch("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(testUser)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testUser, result, User.class), is(true));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mvc.perform(delete("/user/id/" + testUser.getId()))
                .andExpect(status().isOk());

        verify(userService).deleteUser(testUser.getId());
    }
}