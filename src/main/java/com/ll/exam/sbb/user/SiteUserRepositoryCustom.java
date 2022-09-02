package com.ll.exam.sbb.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SiteUserRepositoryCustom {

    Page<SiteUser> searchQsl(String username, Pageable pageable);
}
