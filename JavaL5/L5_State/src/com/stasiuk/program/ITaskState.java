package com.stasiuk.program;

public interface ITaskState {
    void next(Task t);
    void prev(Task t);
    String getStatus();
}
