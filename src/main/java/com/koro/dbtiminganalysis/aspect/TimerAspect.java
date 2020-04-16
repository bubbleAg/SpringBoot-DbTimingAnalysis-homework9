package com.koro.dbtiminganalysis.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TimerAspect {

    private static long startTime;

    @Before("@annotation(com.koro.dbtiminganalysis.annotation.RunTimer)")
    private void startTimer() {
        startTime = System.currentTimeMillis();
        System.out.println("Timer started");
    }

    @After("@annotation(com.koro.dbtiminganalysis.annotation.RunTimer)")
    private void stopTimer() {
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Timer stopped");
        System.out.println(" ** Time of executing operation: "
                + duration
                + " ms **"
        );
    }
}
