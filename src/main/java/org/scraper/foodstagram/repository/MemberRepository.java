package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, CrudRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
