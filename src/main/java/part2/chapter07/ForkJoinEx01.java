import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinEx01 {

    static class SumTask extends RecursiveTask<Long> {
        private final long start;
        private final long end;
        private static final long THRESHOLD = 10_000;

        public SumTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            String threadName = Thread.currentThread().getName();
            System.out.println("작업 범위: " + start + " ~ " + end + " | 실행 스레드: " + threadName);

            long range = end - start + 1;
            if (range <= THRESHOLD) {
                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                long mid = (start + end) / 2;
                SumTask leftTask = new SumTask(start, mid);
                SumTask rightTask = new SumTask(mid + 1, end);

                // 왼쪽 작업을 fork (비동기)
                leftTask.fork();

                // 오른쪽 작업을 현재 스레드(main)에서 실행
                long rightResult = rightTask.compute();

                // 왼쪽 작업 결과 기다림
                long leftResult = leftTask.join();

                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("메인 스레드 이름: " + Thread.currentThread().getName());

        long from = 1;
        long to = 100_000;

        SumTask task = new SumTask(from, to);
        long result = task.compute();  // 여기서 직접 실행하므로 main 스레드에서 진입

        System.out.println("최종 합계: " + result);
    }
}
