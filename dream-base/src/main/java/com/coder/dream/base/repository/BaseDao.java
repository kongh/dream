package com.coder.dream.base.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.coder.dream.base.entity.BaseEntity;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T>{

}
