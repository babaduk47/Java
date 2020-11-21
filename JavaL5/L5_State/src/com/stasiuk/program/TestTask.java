package com.stasiuk.program;

public class TestTask implements ITaskState {
    private final String StatusTask = "сдано на проверку";
    private final String BLUE_BOLD = "\033[1;34m";   // BLUE
    private final String CYAN_BOLD = "\033[1;36m";

    @Override
    public void next(Task t) {
        t.setState(new VerificationTask(),CYAN_BOLD);
    }

    @Override
    public void prev(Task t) {
        t.setState(new UncompletedTask(),BLUE_BOLD);
    }

    @Override
    public String getStatus() {
        return StatusTask;
    }
}
