package ru.academy.myapp.schedules;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@SpringBootApplication
//@EnableScheduling
public class MyScheduling {
    List<String> name = new ArrayList<>();


    @Scheduled(fixedRate = 30000)
    public void scheduled() {
        System.out.println("My scheduled task");

    }


    //  * любое значение
    //  1 первое значение
    //  L последнее значение (день, месяц, неделя)
    //  W ближайший рабочий день
    //  ,  несколько значений
    //
    //  @Scheduled(cron = "0 */5 * * * *")
    //  @Scheduled(cron = "0 */5 * * * *")    каждая 5 минута
    //  @Scheduled(cron = "0 * /2  * * *")    каждая 5 минута
    //  ,  несколько значений

    @Scheduled(cron = "0 */5 * * * *")
    public void cron() {
        System.out.println("My cron task");
    }

    @Scheduled(fixedRate = 5000)
    public void taskOne() {
        System.out.println("Привет!");
    }

    @Scheduled(fixedDelay = 15000, initialDelay = 1000)
    public void taskTwo() {
        System.out.println("Привет!" + " задача 2 ");
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void taskThree() {
        LocalDate today = LocalDate.now();
        System.out.println(today);
    }

    @Scheduled(cron = "0 30 9-18 * * 1-5")
    public void taskFour() {
        System.out.println("Напоминание: рабочее время!");
    }

    @Scheduled(cron = "0 0 * * * 1-5")
    public void taskFive() {
        System.out.println("Сегодня рабочий день");
    }

    @Scheduled(fixedDelay = 180000)
    public void taskSix() {
        LocalTime time = LocalTime.now();
        System.out.println("начало работы " + time);
        int count = 1;
        for (int i = 0; i < 1000; i++) {
            count++;

        }
        LocalTime timeLast = LocalTime.now();
        System.out.println(timeLast);

    }

    @Scheduled(cron = "${my.task.cron}")
    public void taskSeven() {
        System.out.println("\"Cron задача активирована\"");
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void taskEight() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Ben");
        list.add("Steven");
        list.add("Jack");
        list.add("Alex");
        for (String name : list) {
            System.out.println("доброе утро " + name);
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void taskNine() {
        long startTime = System.nanoTime();
        int count = 1;

        for (int i = 1; i < 1_000_000; i++) {
            count = count + i;
        }
        long endTime = System.nanoTime();

        System.out.println(count);
        System.out.println((endTime - startTime) / 1_000_000 + " время работы");
    }

    @Scheduled(zone = "Europe/Moscow",fixedDelay = 10000)
    public void taskTen() {
        System.out.println("\"Europe/Moscow\"");
    }
    @Scheduled(fixedRate = 10000)
    public void taskEleven() {
        int random = new Random().nextInt(100)+1;
        System.out.println(random);
    }
}
