package com.lms.gj_jewelry.domain;

import com.lms.gj_jewelry.interfaces.User;
import com.lms.gj_jewelry.random.RandomUserInstanceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class UserRepositoryTests {

    final int INSERTED_USERS = 5;

    @Autowired
    UserRepository userRepository;

    List<User> dummyUserList;

    @Before
    public void setUp() {
        dummyUserList = new ArrayList<>();
        dummyUserList = RandomUserInstanceGenerator.generateRandomUserList(INSERTED_USERS);
    }

    @Test
    public void testFindAll() {
        userRepository.saveAll(dummyUserList);

        List<User> newUserList = userRepository.findAll();

        assertThat(newUserList.size(), is(INSERTED_USERS));
        for (int i = 0; i < newUserList.size(); ++i) {
//            log.info("{}", newUserList.get(i).toString());
            assertThat(newUserList.get(i).equals(dummyUserList.get(i)), is(true));
        }
    }

    @Test
    public void testFindByEmail() {
        userRepository.saveAll(dummyUserList);

        // Query about emails that exist. should be true
        for (User insertedUser : dummyUserList) {
            assertThat(userRepository.findByEmail(insertedUser.getEmail()).isPresent(), is(true));
        }

        // Query about emails that doesn't exist. should be false
        assertThat(userRepository.findByEmail("wrongData!!@#$%^&").isPresent(), is(false));
    }

    @Test
    public void testFindByPhoneNumber() {
        userRepository.saveAll(dummyUserList);

        // Query about phone numbers that exist. should be true
        for (User insertedUser : dummyUserList) {
            assertThat(userRepository.findByPhoneNumber(insertedUser.getPhoneNumber()).isPresent(), is(true));
        }

        // Query about phone numbers that doesn't exist. should be false
        assertThat(userRepository.findByPhoneNumber("!@#!@$!@").isPresent(), is(false));
    }

}