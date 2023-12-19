package pomodoro.timer;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer
{
    private TimerListener timerListener;

    private long ONE_SECOND_IN_MILLIS = 1000;
    private long pomodoroTime = 25;//* 60;
    private long shortBreakTime = 5;// * 60;
    private long longBreakTime = 30;// * 60;
    private int pomodorosUntilLongBreak = 4;
    private int pomodorosCompleted = 0;
    private boolean isTimerRunning = false;

    public enum SessionType
    {
        POMODORO
        {
            @Override
            public SessionType getNextSessionType(PomodoroTimer pomodoroTimer)
            {
                if (pomodoroTimer.pomodorosCompleted % pomodoroTimer.pomodorosUntilLongBreak == 0) return LONG_BREAK;
                else return SHORT_BREAK;
            }

            @Override
            public String getSessionName()
            {
                return "Pomodoro";
            }

            @Override
            public double getSessionPeriod(PomodoroTimer pomodoroTimer)
            {
                return pomodoroTimer.pomodoroTime;
            }
        },
        SHORT_BREAK
        {
            @Override
            public SessionType getNextSessionType(PomodoroTimer pomodoroTimer)
            {
                return POMODORO;
            }

            @Override
            public String getSessionName()
            {
                return "Short Break";
            }

            @Override
            public double getSessionPeriod(PomodoroTimer pomodoroTimer)
            {
                return pomodoroTimer.shortBreakTime;
            }
        },
        LONG_BREAK
        {
            @Override
            public SessionType getNextSessionType(PomodoroTimer pomodoroTimer)
            {
                return POMODORO;
            }

            @Override
            public String getSessionName()
            {
                return "Long Break";
            }

            @Override
            public double getSessionPeriod(PomodoroTimer pomodoroTimer)
            {
                return pomodoroTimer.longBreakTime;
            }
        };

        public abstract SessionType getNextSessionType(PomodoroTimer pomodoroTimer);

        public abstract String getSessionName();

        public abstract double getSessionPeriod(PomodoroTimer pomodoroTimer);
    }

    private SessionType currentSessionType;

    private Timer timer;

    public PomodoroTimer()
    {
        this.timer = new Timer();
        this.currentSessionType = SessionType.POMODORO;
    }

    public PomodoroTimer(long pomodoroTime, long shortBreakTime, long longBreakTime, int pomodorosUntilLongBreak)
    {
        this.pomodoroTime = pomodoroTime;
        this.shortBreakTime = shortBreakTime;
        this.longBreakTime = longBreakTime;
        this.pomodorosUntilLongBreak = pomodorosUntilLongBreak;
    }

    public void setTimerListener(TimerListener timerListener)
    {
        this.timerListener = timerListener;
    }

    public void startTimer()
    {
        this.timer.schedule(new TimerTask()
        {
            public void run()
            {
                timerListener.onTimerComplete();
            }
        }, (long) getCurrentSessionType().getSessionPeriod(this) * ONE_SECOND_IN_MILLIS);
        isTimerRunning = true;
    }

    public void stopTimer()
    {
        this.timer.cancel();
        isTimerRunning = false;
    }

    public SessionType getCurrentSessionType()
    {
        return this.currentSessionType;
    }

    public void nextSession()
    {
        this.timer = new Timer();
        if (this.currentSessionType == SessionType.POMODORO) this.pomodorosCompleted++;
        System.out.println(this.currentSessionType.getSessionName() + " " + this.pomodorosCompleted);
        this.currentSessionType = this.currentSessionType.getNextSessionType(this);
    }

    public boolean isTimerRunning()
    {
        return this.isTimerRunning;
    }
}
