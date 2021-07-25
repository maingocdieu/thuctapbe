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

//    @Query(value="SELECT  u"
//        + " FROM t_User u"
//        + "  JOIN u.roles role"
//        + " WHERE (:userName is null OR u.userName like :userName)"
//        + "  AND (:#{#roles[0]} = -1L OR role.id in :roles)"
//        + "  AND (:email is null OR u.email like :email )"
//        + "  AND (:address is null OR u.address like :address) ORDER BY id", countQuery = "SELECT count(*) FROM t_User", 
//        nativeQuery = true)
//    Page<User> findAllUserWithPagination(@Param("userName") String userName, @Param("roles") List<Long> roles, @Param("email") String email, @Param("address") String address, Pageable pageable);

	@Query(value = "SELECT distinct u" + " FROM User u" + " JOIN u.roles role"
			+ " WHERE (:userName is null OR u.username like :username)" + "  AND ( role.id in :roles)"
			+ "  AND (:email is null OR u.email like :email )" + "  AND (:address is null OR u.address like :address)")
	Page<User> findAllUserWithPagination(@Param("username") String username, @Param("roles") List<Long> roles,
			@Param("email") String email, @Param("address") String address, Pageable pageable);
}