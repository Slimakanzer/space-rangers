package com.spaceRangers.impl;

import com.spaceRangers.entities.ComplainEntity;
import com.spaceRangers.repository.ComplainRepository;
import com.spaceRangers.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("complainService")
public class ComplainServiceImpl implements ComplainService {

    private final ComplainRepository complainRepository;

    @Autowired
    public ComplainServiceImpl(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
    }

    @Override
    public ComplainEntity createComplain(ComplainEntity complainEntity) {
        complainRepository.save(complainEntity);
        return complainEntity;
    }

    @Override
    public ComplainEntity updateComplain(ComplainEntity complainEntity) {
        complainRepository.save(complainEntity);
        return complainEntity;
    }
}
