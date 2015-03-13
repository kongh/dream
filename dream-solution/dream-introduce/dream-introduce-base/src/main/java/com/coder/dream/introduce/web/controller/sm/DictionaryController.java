package com.coder.dream.introduce.web.controller.sm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseDirectController;
import com.coder.dream.introduce.entity.sm.Dictionary;
import com.coder.dream.introduce.repository.sm.DictionaryDao;
import com.coder.dream.introduce.service.sm.DictionaryService;

@RestController
@RequestMapping(value="/sm/dictionary")
public class DictionaryController extends BaseDirectController<Dictionary, DictionaryService, DictionaryDao>{

}
