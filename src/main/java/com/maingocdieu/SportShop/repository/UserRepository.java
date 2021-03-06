package com.maingocdieu.SportShop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maingocdieu.SportShop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<User> findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

	@Query("select u from User u where u.username like %?1")
	List<User> findAllByCriteria(String userName);


}