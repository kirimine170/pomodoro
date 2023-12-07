package pomodoro.timer;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer
{
    private TImerListener timerListener;

    private long ONE_SECOND_IN_MILLIS = 1000;
    private long pomodoroTime = 25;//* 60;
    private long shortBreakTime = 5 * 60;
    private long longBreakTime = 30 * 60;
    private int pomodorosUntilLongBreak = 4;

    private enum SessionType
    {
        POMODORO, SHORT_BREAK, LONG_BREAK
    }

    private Timer timer;

    public PomodoroTimer()
    {
        this.timer = new Timer();
    }

    public PomodoroTimer(long pomodoroTime, long shortBreakTime, long longBreakTime, int pomodorosUntilLongBreak)
    {
        this.pomodoroTime = pomodoroTime;
        this.shortBreakTime = shortBreakTime;
        this.longBreakTime = longBreakTime;
        this.pomodorosUntilLongBreak = pomodorosUntilLongBreak;
    }

    public void setTimerListener(TImerListener timerListener)
    {
        this.timerListener = timerListener;
    }

    public void startTimer()
    {
        this.timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                timerListener.onTimerUpdate();
            }
        }, 0, pomodoroTime * ONE_SECOND_IN_MILLIS / 360);
    }

    public void stopTimer()
    {
        this.timer.cancel();
    }
}
