package com.reposity.mysql.apitest;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@NoRepositoryBean
public interface TestSystemDao extends PagingAndSortingRepository<TestSystem,Long> {

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





}
