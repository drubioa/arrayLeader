package es.solution;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SolutionTest {

    @Test
    public void test1() {

        final int[] A = {2, 1, 3 , 1 , 2 , 2 ,3};
        final int[] solutionExpected = {2, 3};
        int[] result = Solution.solution(3, 5, A);
        assertArrayEquals(solutionExpected, result);
    }

    @Test
    public void test2() {

        final int[] A = {1, 2, 2 , 1 , 2 };
        final int[] solutionExpected = {2, 3};
        int[] result = Solution.solution(4, 2, A);
        assertArrayEquals(solutionExpected, result);
    }

    @Test
    public void testSingleElement() {

        final int[] A = {1};
        final int[] solutionExpected = {1};
        int[] result = Solution.solution(2, 1, A);
        assertArrayEquals(solutionExpected, result);
    }

    @Test
    public void testEmptyElement() {

        final int[] A = {};
        final int[] solutionExpected = {};
        int[] result = Solution.solution(2, 1, A);
        assertArrayEquals(solutionExpected, result);
    }

    @Test(expected = InvalidArrayException.class)
    public void testArrayValueGreaterThanM() {
        final int[] A = {1, 2, 2 , 1 , 3 };
        Solution.solution(4, 2, A);
    }

    @Test(expected = InvalidArrayException.class)
    public void testArrayValueGreaterThanMaxValue() {
        final int MAX_VALUE = 100_000;
        final int[] A = {1, 2, 2 , MAX_VALUE + 1 , 3 };
        Solution.solution(4, 2, A);
    }

}
