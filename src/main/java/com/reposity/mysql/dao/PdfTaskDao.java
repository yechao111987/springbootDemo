package com.reposity.mysql.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfTaskDao extends JpaRepository<PdfTask, Long> {

    List<PdfTask> findAllByStatusOrderById(Integer status);

    List<PdfTask> findAllByState(Integer flag);

    PdfTask findByTaskId(String taskId);

    <S extends PdfTask> S save(S entity);
}
