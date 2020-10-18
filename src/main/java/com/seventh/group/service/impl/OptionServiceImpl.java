package com.seventh.group.service.impl;

import com.seventh.group.Entity.Option;
import com.seventh.group.repository.OptionRepository;
import com.seventh.group.service.optionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author EdiMen
 * @Data 2020/10/17--11:55
 * @Version 1.0
 */
@Service
@Transactional
public class OptionServiceImpl implements optionService {

    @Resource
    private OptionRepository optionRepository;


    ////更新选项被选中的次数
    @Override
    public void updateOptionNum(Integer contentId) {
        Option option = optionRepository.findById(contentId).get();
        option.setNum(option.getNum()+1);
        optionRepository.save(option);
    }


}
