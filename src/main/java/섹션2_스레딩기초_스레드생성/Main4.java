package 섹션2_스레딩기초_스레드생성;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main4 {

    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {

        Random random = new Random();

        int password = random.nextInt(MAX_PASSWORD);
        System.out.println("금고 패스워드 = " + password);

        Vault vault = new Vault(password);

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }

    }

    // * 금고
    private static class Vault {
       private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch(InterruptedException e) {
            }
            return this.password == guess;
        }
    }

    // * 해커 추상 클래스
    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("스레드가 시작되었어요 = " + this.getName());
            super.start();
        }
    }

    // * 오름차순을 이용하는 해커 스레드
    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess <= MAX_PASSWORD ; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + "이 해킹에 성공했다, 입력 패스워드 = " + guess);
                    System.exit(0);
                }
            }
        }
    }
    // * 내림차순 이용하는 해커 스레드
    private static class DescendingHackerThread extends HackerThread {
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 0 ; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + "이 해킹에 성공했다, 입력 패스워드 = " + guess);
                    System.exit(0);
                }
            }
        }
    }

    // * 경찰 스레드
    public static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0 ; i--) {
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                }
                System.out.println(i);
            }
            System.out.println("게임 오버, 도둑 잡았다!!!");
            System.exit(0);
        }
    }



}
