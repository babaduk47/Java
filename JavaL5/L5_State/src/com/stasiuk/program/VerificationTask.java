package com.stasiuk.program;

public class VerificationTask implements ITaskState {
    private final String StatusTask = "проверено";
    private final String YELLOW_BOLD = "\033[1;33m";
    private final String RED_BOLD = "\033[1;31m";    // RED

    @Override
    public void next(Task t) {
        t.setState(new RetakeTask(),RED_BOLD);
    }

    @Override
    public void prev(Task t) {
        t.setState(new TestTask(),YELLOW_BOLD);
    }

    @Override
    public String getStatus() {
        return StatusTask;
    }
}
