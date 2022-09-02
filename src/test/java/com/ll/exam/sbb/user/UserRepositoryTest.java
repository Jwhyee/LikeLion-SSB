package com.ll.exam.sbb.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("검색, Page 리턴")
    void t8() {
        int itemsInAPage = 1; // 한 페이지에 보여줄 아이템 개수
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.asc("id"));
        Pageable pageable = PageRequest.of(1, itemsInAPage, Sort.by(sorts)); // 한 페이지에 10까지 가능
        Page<SiteUser> usersPage = userRepository.searchQsl("user", pageable);

        List<SiteUser> users = usersPage.get().toList();

        SiteUser u = users.get(0);

        assertThat(u.getId()).isEqualTo(2L);
        assertThat(u.getUsername()).isEqualTo("user2");
        assertThat(u.getEmail()).isEqualTo("user2@test.com");
        assertThat(u.getPassword()).isEqualTo("{noop}1234");

        assertThat(usersPage.getNumber()).isEqualTo(1);
        assertThat(usersPage.getTotalPages()).isEqualTo(2);
        assertThat(usersPage.getTotalElements()).isEqualTo(2);

        // 검색어 : user1
        // 한 페이지에 나올 수 있는 아이템 수 : 1개
        // 현재 페이지 : 1
        // 정렬 : id 역순

        // 내용 가져오는 SQL
        /*
        SELECT site_user.*
        FROM site_user
        WHERE site_user.username LIKE '%user%'
        OR site_user.email LIKE '%user%'
        ORDER BY site_user.id ASC
        LIMIT 1, 1
         */

        // 전체 개수 계산하는 SQL
        /*
        SELECT COUNT(*)
        FROM site_user
        WHERE site_user.username LIKE '%user%'
        OR site_user.email LIKE '%user%'
         */
    }

}