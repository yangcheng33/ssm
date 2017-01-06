package org.sample.ssm.web.controller;

import java.time.Clock;

/**
 * desc
 *
 * @author Yang Cheng
 * @date 2016-09-26
 */
public class Test {

    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());
        clock =Clock.systemDefaultZone();
        System.out.println("Clock : " + clock.millis());
    }
}
