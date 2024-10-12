package org.scraper.foodstagram.repository;

import org.scraper.foodstagram.repository.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long>, CrudRepository<Member,Long> {

}
