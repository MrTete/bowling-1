import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingGameTest {

    BowlingGame bowlingGame;

    @Before
    public void setUp() {
        bowlingGame = new BowlingGame(10);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testEntreeNull() {
        bowlingGame = new BowlingGame(null, 10);
        bowlingGame.getSum();
    }

    /**
     * Check if each pair is positive or null
     */
    @Test
    public void testAddScoreNegative() {
        assertFalse(bowlingGame.addScore(new Pair<>(-1, 0)));
    }

    /**
     * Check if each pair is positive or null
     */
    @Test
    public void testAddScorePositive() {
        assertTrue(bowlingGame.addScore(new Pair<>(1, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(0, 0)));
    }

    @Test
    public void testNbMaxSets() {
        for (int i = 0; i < 10; ++i) {
            assertTrue(bowlingGame.addScore(new Pair<>(1, 2)));
        }
        assertFalse(bowlingGame.addScore(new Pair<>(1, 3)));
    }

    /**
     * Check if each pair is inferior or equals 10
     */
    @Test
    public void testSumPair() {
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertFalse(bowlingGame.addScore(new Pair<>(11, 3)));
    }

    @Test
    public void testGetSum1() {
        bowlingGame.addScore(new Pair<>(2, 3));
        bowlingGame.addScore(new Pair<>(5, 5));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        assertEquals(24, bowlingGame.getSum()); //5+11+8

    }

    /// <summary>
    /// Check the getSum when a tuple equals 10 (item1 + item2 = 10, item1=0 XOR item2 = 0)
    /// </summary>

    @Test
    public void testGetSum2() {
        bowlingGame.addScore(new Pair<>(2, 3));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        bowlingGame.addScore(new Pair<>(1, 0));
        assertEquals(24, bowlingGame.getSum());//5+11+8
    }

    /// <summary>
    /// Test enchainement stike et spare
    /// </summary>

    @Test
    public void testGetSum3() {
        bowlingGame.addScore(new Pair<>(2, 3));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(5, 5));
        bowlingGame.addScore(new Pair<>(7, 2));
        bowlingGame.addScore(new Pair<>(2, 3));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(4, 5));
        bowlingGame.addScore(new Pair<>(7, 2));
        assertEquals(5 + 25 + 20 + 17 + 9 + 5 + 24 + 19 + 9 + 9, bowlingGame.getSum()); // 5+25+20+17+9+5+24+19+9+9
    }

    @Test
    public void testAddScoreSetEnd1() //Pas de coup supplémentaires
    {
        bowlingGame.addScore(new Pair<>(2, 5));
        bowlingGame.addScore(new Pair<>(3, 1));
        bowlingGame.addScore(new Pair<>(4, 3));
        bowlingGame.addScore(new Pair<>(5, 2));
        bowlingGame.addScore(new Pair<>(6, 2));
        bowlingGame.addScore(new Pair<>(7, 0));
        bowlingGame.addScore(new Pair<>(8, 1));
        bowlingGame.addScore(new Pair<>(9, 1));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(6, 3));

        assertFalse(bowlingGame.addScore(new Pair<>(2, 3)));
    }

    @Test
    public void testAddScoreMancheEnd2()//spare => un coup supplémentaire
    {
        bowlingGame.addScore(new Pair<>(2, 5));
        bowlingGame.addScore(new Pair<>(3, 1));
        bowlingGame.addScore(new Pair<>(4, 3));
        bowlingGame.addScore(new Pair<>(5, 2));
        bowlingGame.addScore(new Pair<>(6, 2));
        bowlingGame.addScore(new Pair<>(7, 0));
        bowlingGame.addScore(new Pair<>(8, 1));
        bowlingGame.addScore(new Pair<>(9, 1));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(5, 5));

        assertFalse(bowlingGame.addScore(new Pair<>(2, 3)));
        assertTrue(bowlingGame.addScore(new Pair<>(2, 0)));
    }

    @Test
    public void testAddScoreMancheEnd3()//strike => 2 coups supplémentaires
    {
        bowlingGame.addScore(new Pair<>(2, 5));
        bowlingGame.addScore(new Pair<>(3, 1));
        bowlingGame.addScore(new Pair<>(4, 3));
        bowlingGame.addScore(new Pair<>(5, 2));
        bowlingGame.addScore(new Pair<>(6, 2));
        bowlingGame.addScore(new Pair<>(7, 0));
        bowlingGame.addScore(new Pair<>(8, 1));
        bowlingGame.addScore(new Pair<>(9, 1));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(10, 0));

        assertTrue(bowlingGame.addScore(new Pair<>(2, 3)));
    }

    @Test
    public void testAddScoreMancheEnd4()//test ajout d'un coup après le coup supplémentaire
    {
        bowlingGame.addScore(new Pair<>(2, 5));
        bowlingGame.addScore(new Pair<>(3, 1));
        bowlingGame.addScore(new Pair<>(4, 3));
        bowlingGame.addScore(new Pair<>(5, 2));
        bowlingGame.addScore(new Pair<>(6, 2));
        bowlingGame.addScore(new Pair<>(7, 0));
        bowlingGame.addScore(new Pair<>(8, 1));
        bowlingGame.addScore(new Pair<>(9, 1));
        bowlingGame.addScore(new Pair<>(10, 0));
        bowlingGame.addScore(new Pair<>(10, 0));

        assertTrue(bowlingGame.addScore(new Pair<>(2, 3)));

        assertFalse(bowlingGame.addScore(new Pair<>(1, 1)));
    }

    @Test
    public void test300points() {
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertTrue(bowlingGame.addScore(new Pair<>(10, 0)));
        assertFalse(bowlingGame.addScore(new Pair<>(10, 0)));
        assertEquals(300, bowlingGame.getSum());
    }

}
