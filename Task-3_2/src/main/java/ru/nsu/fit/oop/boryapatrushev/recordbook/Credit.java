package ru.nsu.fit.oop.boryapatrushev.recordbook;
class Credit {
    private final boolean isMark;
    private final boolean isFinal;
    private final int mark;

    Credit(boolean cred, boolean isFinal) {
        isMark = false;
        if (cred) {
            mark = 1;
        } else {
            mark = 0;
        }
        this.isFinal = isFinal;
    }

    Credit(int mark, boolean isFinal) {
        isMark = true;
        this.mark = mark;
        this.isFinal = isFinal;
    }

    int getMark() {
        return mark;
    }

    boolean isMark() {
        return isMark;
    }

    boolean isFinal() {
        return isFinal;
    }
}