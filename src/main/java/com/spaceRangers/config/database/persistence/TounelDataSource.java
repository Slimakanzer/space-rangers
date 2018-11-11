package com.spaceRangers.config.database.persistence;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

public class TounelDataSource extends DriverManagerDataSource implements InitializingBean {

    Logger logger = Logger.getLogger(TounelDataSource.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        JSch jSch = new JSch();
        Session session = jSch.getSession("s242552", "helios.cs.ifmo.ru", 2222);
        session.setPassword("aif217");

        Properties config = new Properties();
        config.put( "StrictHostKeyChecking", "no" );
        session.setConfig( config );

        session.connect();

        logger.info("connected successfull");
        session.setPortForwardingL(63334, "pg", 5432);

    }
}
