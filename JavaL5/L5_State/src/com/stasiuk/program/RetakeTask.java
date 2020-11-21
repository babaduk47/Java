package com.stasiuk.program;

public class RetakeTask implements ITaskState {
    private final String StatusTask = "перездать на проверку";
    private final String CYAN_BOLD = "\033[1;36m";
    private final String GREEN_BOLD = "\033[1;32m";  // GREEN

    @Override
    public void next(Task t) {
        t.setState(new CompletedTask(),GREEN_BOLD);
    }

    @Override
    public void prev(Task t) {
        t.setState(new VerificationTask(),CYAN_BOLD);
    }

    @Override
    public String getStatus() {
        return StatusTask;
    }
}
