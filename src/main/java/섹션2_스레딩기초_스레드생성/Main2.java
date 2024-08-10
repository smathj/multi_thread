package 섹션2_스레딩기초_스레드생성;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 새로운 스레드에서 코드가 동작한다
                throw new RuntimeException("스레드 내에서 언체크 예외 발생");

            }
        });

        // 스레드 내에서 언체크된 예외를 잡지못하면 그 스레드를 다운시킨다
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + " 스레드에서 크리티컬 에러가 발생 했다, 메세지 = " + e.getMessage());
            }
        });

        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
}
