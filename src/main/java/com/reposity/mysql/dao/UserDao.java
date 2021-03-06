package com.reposity.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    List<User> findByName(String name);

    List<User> findByNameAndPassword(String name,String password);

    List<User> findById(Long id);

    @Query("select count(1) from User where name=:name and password=:password")
    int countByNameAndPassword(@Param("name") String name,@Param("password") String password);

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity
     * @return the saved entity
     */
    @Override
    <S extends User> S save(S entity);
}
