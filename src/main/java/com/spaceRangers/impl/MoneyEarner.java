package com.spaceRangers.impl;


import com.spaceRangers.entities.PlanetEntity;
import com.spaceRangers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Collection;

@Component
public class MoneyEarner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SimpMessagingTemplate template;


    public void startEarnMoney(){
        new Thread(() -> {
            while (true){
                    userRepository
                            .findAll()
                            .forEach(user -> {
                                final int[] countMoney = {0};
                                Collection<PlanetEntity> planets = user.getPlanets();
                                planets.forEach(planetEntity -> {
                                    planetEntity.getResources().forEach(resourceEntity -> {
                                        countMoney[0] += resourceEntity.getCount() * resourceEntity.getTypeResources().getCost();
                                    });
                                });

                                user.setCoins(user.getCoins() + countMoney[0]);
                                userRepository.save(user);
                                System.out.println(user.getLogin() +"  earned " + countMoney[0]);
                            });

                    template.convertAndSend("/topic/user", new Boolean(true));

                    try {
                        Thread.sleep(120000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }).start();
    }
}
