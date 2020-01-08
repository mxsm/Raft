package com.github.mxsm.raft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description:
 *
 * @author mxsm
 * @Date 2020/1/8 22:26
 */
@SpringBootApplication
public class RaftApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(RaftApplicationBootstrap.class,args);
    }

}
