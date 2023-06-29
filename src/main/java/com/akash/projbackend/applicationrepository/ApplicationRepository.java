package com.akash.projbackend.applicationrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akash.projbackend.entity.User;

@Repository
public interface ApplicationRepository extends JpaRepository<User, Long> {

	@Modifying
	@Query(value = "INSERT INTO user (username, password) VALUES (:username, :password)", nativeQuery = true)
	void registerUser(@Param("username") String username, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
	User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findByUsername(String username);
}
