package com.example.trafficsignproject.repository;

import com.example.trafficsignproject.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT email from users WHERE email = :email", nativeQuery = true)
    List<String> checkUserEmail(@Param("email") String email);

    @Query(value = "SELECT password from users WHERE email = :email", nativeQuery = true)
    String checkUserPasswordByEmail(@Param("email") String email);

    @Query(value = "SELECT * from users WHERE email = :email", nativeQuery = true)
    User getUserDetailsByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO USERS(nom, email, password, tel) VALUES(:nom, :email, :password, :tel)", nativeQuery = true)
    int registerNewUser(@Param("nom") String nom,
                        @Param("email") String email,
                        @Param("password") String password,
                        @Param("tel") String tel);

}
