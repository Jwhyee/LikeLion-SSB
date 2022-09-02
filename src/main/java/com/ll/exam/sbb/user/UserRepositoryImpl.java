package com.ll.exam.sbb.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.ll.exam.sbb.user.QSiteUser.siteUser;

@RequiredArgsConstructor
public class UserRepositoryImpl implements SiteUserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<SiteUser> searchQsl(String username, Pageable pageable) {
        return null;
    }
}
