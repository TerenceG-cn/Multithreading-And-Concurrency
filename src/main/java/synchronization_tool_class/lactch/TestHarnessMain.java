package synchronization_tool_class.lactch;

import java.io.File;

public class TestHarnessMain {
    public static void main(String[] args){
        TestHarness testHarness = new TestHarness();
        Runnable task=()->{
            System.out.println(Thread.currentThread().getName());
        };
        try {
            long useTime=testHarness.timeTasks(10, task);
            System.out.println(useTime);
        }catch (InterruptedException ignored){

        }
    }
}
