package com.stasiuk.program;

public class UncompletedTask implements ITaskState {
    private final String StatusTask = "не выполнено";
    private final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    private final String YELLOW_BOLD = "\033[1;33m";

    @Override
    public void next(Task t) {
        t.setState(new TestTask(),YELLOW_BOLD);
    }

    @Override
    public void prev(Task t) {
        t.setState(new IssuedTask(),PURPLE_BOLD);
    }

    @Override
    public String getStatus() {
        return StatusTask;
    }
}
