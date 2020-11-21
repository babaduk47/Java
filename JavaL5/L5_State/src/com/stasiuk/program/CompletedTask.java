package com.stasiuk.program;

public class CompletedTask implements ITaskState {
    private final String StatusTask = "выполнено";
    private final String RED_BOLD = "\033[1;31m";    // RED

    @Override
    public void next(Task t) {
        System.out.println("Ошибка смены состояния");
    }

    @Override
    public void prev(Task t) {
        t.setState(new RetakeTask(),RED_BOLD);
    }

    @Override
    public String getStatus() {
        return StatusTask;
    }
}
