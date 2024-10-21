package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MemberRepository extends PagingAndSortingRepository<Member, String>, CrudRepository<Member, String> {
    Optional<Member> findByEmail(String email);
}
