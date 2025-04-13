package com.mobile.pomodoro.repositories;

import java.util.List;
import java.util.Optional;

import com.mobile.pomodoro.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // This class will handle the database operations for the User entity
    // You can use Spring Data JPA to create a repository interface for User
    // and extend JpaRepository or CrudRepository to get basic CRUD operations
    // without writing any implementation code.
    // Example:
    // public interface UserRepository extends JpaRepository<User, Long> {
    //     Optional<User> findByUsername(String username);
    //     Optional<User> findById(Long id);
    //     void deleteById(Long id);
    //     List<User> findAll();
    //     User save(User user);
    //     User update(User user);
    @Query("SELECT u FROM user u WHERE u.username = :username")
    Optional<User> findUsersByUsername(@Param("username") String username);

    @Query("SELECT u FROM user u WHERE u.userId = :id")
    Optional<User> findUsersByUserId(@Param("id") long id);
}
