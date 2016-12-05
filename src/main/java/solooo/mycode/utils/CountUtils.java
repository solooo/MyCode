package solooo.mycode.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class CountUtils {

    private AtomicInteger count = new AtomicInteger();
    
    
    public Integer getCount() {
        return count.get();
    }
    
    public String getNextCode() {
        return String.format("D%04d", getCount());
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            System.out.println(new CountUtils().getNextCode());
        }
    }
}
