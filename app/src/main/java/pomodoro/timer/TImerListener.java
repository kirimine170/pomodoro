package pomodoro.timer;

public interface TimerListener
{
    void onTimerUpdate(double angle);

    void onTimerComplete();
}
