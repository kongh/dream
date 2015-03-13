package com.coder.dream.base.repository;
import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

import com.coder.dream.base.entity.BaseTree;

@NoRepositoryBean
public interface BaseTreeDao<T extends BaseTree,ID extends Serializable> extends BaseDao<T,ID>{

}
