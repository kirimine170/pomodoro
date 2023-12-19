package pomodoro.timer;

import javafx.animation.AnimationTimer;

public class CustomableAnimationTimer extends AnimationTimer
{
    private long startTime;
    private double period;
    private TimerListener timerListener;
    private PomodoroTimer pomodoroTimer;

    public CustomableAnimationTimer(PomodoroTimer pomodoroTimer)
    {
        this.pomodoroTimer = pomodoroTimer;
        this.period = pomodoroTimer.getCurrentSessionType().getSessionPeriod(pomodoroTimer);
    }

    @Override
    public void start()
    {
        this.period = pomodoroTimer.getCurrentSessionType().getSessionPeriod(pomodoroTimer);
        super.start();
        startTime = System.nanoTime();
    }

    @Override
    public void stop()
    {
        super.stop();
    }

    @Override
    public void handle(long now)
    {
        double elapsedSeconds = (now - startTime) / 1_000_000_000.0;
        double angle = (elapsedSeconds % period) / period * 360;

        timerListener.onTimerUpdate(angle);// HACK 本当はこの実装良くないかも
    }

    public void setTimerListener(TimerListener timerListener)
    {
        this.timerListener = timerListener;
    }
}
