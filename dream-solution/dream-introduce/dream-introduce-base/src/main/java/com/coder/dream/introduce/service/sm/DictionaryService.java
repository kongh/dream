package com.coder.dream.introduce.service.sm;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseService;
import com.coder.dream.introduce.entity.sm.Dictionary;
import com.coder.dream.introduce.repository.sm.DictionaryDao;

@Service
@Transactional
public class DictionaryService extends BaseService<Dictionary, DictionaryDao>{

}
