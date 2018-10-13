package com.spaceRangers.impl;

import com.spaceRangers.entities.ComplainEntity;
import com.spaceRangers.repository.ComplainRepository;
import com.spaceRangers.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("complainService")
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    private ComplainRepository complainRepository;

    /**
     * Создание жалобы
     *
     * @param complainEntity
     * @return
     */
    @Override
    public boolean createComplain(ComplainEntity complainEntity) {
        complainRepository.save(complainEntity);
        return true;
    }

    /**
     * Изменение жалобы (если исправлена или т.д.)
     *
     * @param complainEntity
     * @return
     */
    @Override
    public boolean updateComplain(ComplainEntity complainEntity) {
        complainRepository.save(complainEntity);
        return true;
    }
}
