package com.stasiuk.program;

public class IssuedTask implements ITaskState{

    private final String StatusTask = "выдано";
    private final String BLUE_BOLD = "\033[1;34m";   // BLUE

    @Override
    public void next(Task t) {
        t.setState(new UncompletedTask(),BLUE_BOLD);
    }

    @Override
    public void prev(Task t) {
        System.out.println("Ошибка смены состояния");
    }

    @Override
    public String getStatus() {
        return StatusTask;
    }
}
