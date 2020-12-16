package ru.nsu.fit.oop.boryapatrushev.recordbook;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {
    @Test
    public void test1() {
        Record cr = new Record(8, 50);
        cr.addCredit("Mathematical Analysis", 5, false, 1);
        cr.addCredit("Discrete Math", 5, false, 1);
        cr.addCredit("History", 5, true, 1);
        cr.addCredit("Computing platforms", true, false, 1);
        cr.addCredit("Imperative programming", 5, false, 1);
        cr.addCredit("Declarative programming", 4, false, 1);
        cr.addCredit("Russian Language", 5, true, 1);
        cr.addCredit("English", true, false, 1);
        cr.addCredit("PE", true, false, 1);

        cr.addCredit("Discrete Math", 5, true, 2);
        cr.addCredit("Mathematical Analysis", 5, true, 2);
        cr.addCredit("Imperative programming", 5, true, 2);
        cr.addCredit("Computing platforms", 5, true, 2);
        cr.addCredit("Declarative programming", 5, true, 2);
        cr.addCredit("English", 5, true, 2);
        cr.addCredit("PE", true, false, 2);

        assertTrue(cr.canHaveSpecialDiploma());
        assertEquals(4.917, cr.getAverage(), 0.01);
        assertTrue(cr.hasAward(1));
        assertTrue(cr.hasAward(2));
    }

    @Test
    public void test2() {
        Record cr = new Record(4, 100);
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                cr.addCredit("Subject5", 5, false, i);
            }
            for (int j = 0; j < 5; j++) {
                cr.addCredit("Subject4", 4, false, i);
            }
        }
        assertEquals(4.8, cr.getAverage(), 0.01);
    }

    @Test
    public void test3() {
        Record cr = new Record(4, 100);
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                cr.addCredit("Subject3", 3, false, i);
            }
            for (int j = 0; j < 5; j++) {
                cr.addCredit("SubjectPassed", true, false, i);
            }
        }
        assertEquals(3, cr.getAverage(), 0.01);
    }

    @Test
    public void test4() {
        Record crBad = new Record(1, 10);
        Record crGood = new Record(1, 10);
        for (int i = 0; i < 9; i++) {
            crGood.addCredit("Placeholder", 5, true, 1);
            crBad.addCredit("Placeholder", 5, true, 1);
        }
        crGood.addCredit("Placeholder", 5, true, 1);
        crBad.addCredit("Placeholder", 3, true, 1);
        assertTrue(crGood.hasAward(1));
        assertFalse(crBad.hasAward(1));
    }

    @Test
    public void test5() {
        Record crHasSatisf = new Record(1, 10);
        Record crGood = new Record(1, 10);
        Record crLowAvg = new Record(1, 10);
        Record crBadFinal = new Record(1, 10);

        for (int i = 0; i < 9; i++) {
            crGood.addCredit("Placeholder", 5, true, 1);
            crHasSatisf.addCredit("Placeholder", 5, true, 1);
            crBadFinal.addCredit("Placeholder", 5, true, 1);
            crLowAvg.addCredit("Placeholder", 4, true, 1);
        }

        assertTrue(crHasSatisf.canHaveSpecialDiploma());
        assertTrue(crGood.canHaveSpecialDiploma());

        crGood.addCredit("Placeholder", 5, true, 1);
        crHasSatisf.addCredit("Placeholder", 3, true, 1);
        crBadFinal.addCredit("Placeholder", 5, true, 1);
        crLowAvg.addCredit("Placeholder", 5, true, 1);

        assertTrue(crGood.canHaveSpecialDiploma());
        assertFalse(crHasSatisf.canHaveSpecialDiploma());
        crGood.setFinalProject(5);
        crHasSatisf.setFinalProject(5);
        crLowAvg.setFinalProject(5);
        crBadFinal.setFinalProject(4);

        assertTrue(crGood.canHaveSpecialDiploma());
        assertFalse(crHasSatisf.canHaveSpecialDiploma());
        assertFalse(crLowAvg.canHaveSpecialDiploma());
        assertFalse(crBadFinal.canHaveSpecialDiploma());
    }

    @Test(expected = IllegalStateException.class)
    public void test6() {
        Record cr = new Record(1, 10);
        for (int i = 0; i < 100; i++) {
            cr.addCredit("SubjectPassed", true, true, 1);
        }
        for (int i = 0; i < 10; i++) {
            cr.addCredit("Subject5Final", 5, true, 1);
        }
        for (int i = 0; i < 10; i++) {
            cr.addCredit("Subject5NotFinal", 5, false, 1);
        }
        cr.addCredit("Additional mark", 5, true, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test7() {
        Record cr = new Record(8, 100);
        cr.addCredit("WrongSemester", 5, true, 9);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test8() {
        Record cr = new Record(1, 100);
        for (int i = 0; i < 10; i++) {
            cr.addCredit("Not final", 5, false, 1);
        }
        cr.hasAward(1);
        cr.hasAward(2);
    }
}