package 섹션2_스레딩기초_스레드생성;

public class Main3 {
    public static void main(String[] args) {


        Thread thread = new NewThread();

        thread.start();

    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            // 새로운 스레드에서 코드가 동작한다
            System.out.println("Hello from " + Thread.currentThread().getName());
            String name = this.getName();
            int priority = this.getPriority();
            System.out.println("name = " + name);
            System.out.println("priority = " + priority);
        }
    }


}
