package time;

import java.util.Date;

/**
 * Created by wangyue66 on 2017/12/5.
 */
public class UnixTime {
    private final int value;

    public UnixTime() {
        this((int) (System.currentTimeMillis() / 1000L + 2208988800L));
    }

    public UnixTime(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
