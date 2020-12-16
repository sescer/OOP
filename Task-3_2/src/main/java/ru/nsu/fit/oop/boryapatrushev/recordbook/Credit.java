package ru.nsu.fit.oop.boryapatrushev.recordbook;
class Credit {
    private final boolean isMark;
    private final boolean isFinal;
    private final int mark;

    Credit(boolean cred, boolean _final) {
        isMark = false;
        if (cred) {
            mark = 1;
        } else {
            mark = 0;
        }
        isFinal = _final;
    }

    Credit(int _mark, boolean _final) {
        isMark = true;
        mark = _mark;
        isFinal = _final;
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