package com.reposity.mysql.apitest;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

@Repository
//@NoRepositoryBean
public interface TestSystemDao extends PagingAndSortingRepository<TestSystem,Long>  {

    @Override
    <S extends TestSystem> S save(S s);

    boolean exists(long l);

    @Override
    List<TestSystem> findAll();

    @Override
    long count();

    void delete(long l);

    TestSystem getOne(long l);



    @Override
    <S extends TestSystem> List<S> save(Iterable<S> iterable);

    Page<TestSystem> findAll(Pageable pageable);

    @Query(value="insert into system (sysname,description,status) values(:sysname,:description,:status)",nativeQuery = true)
    @Modifying
    int saveTestSystem(@Param("sysname") String sysname,@Param("description") String description,@Param("status") Integer status);







}
