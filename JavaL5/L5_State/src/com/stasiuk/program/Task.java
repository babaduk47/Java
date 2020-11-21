package com.stasiuk.program;

public class Task {

        private ITaskState state = null;
        private final String RESET = "\033[0m";  // Text Reset
        private final String PURPLE_BOLD = "\033[1;35m"; // PURPLE

        public Task(){
            setState(new IssuedTask(),PURPLE_BOLD);
        }

        // getter, setter

        public void previousState() {
            state.prev(this);
        }

        public void nextState() {
            state.next(this);
        }

        public void printStatus() {
            state.getStatus();
        }

        public void setState(ITaskState _state,String Color) {
            System.out.println(Color+"Задание получает статус : " + _state.getStatus() + "..."+RESET);
            this.state = (ITaskState) _state;
        }
}
