package es.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private static final int MAX_VALUE = 100_000;

    /**
     * that, given integers K and M and an array A consisting of N integers, returns an array of all numbers that can become a leader,
     * after increasing by 1 every element of exactly one segment of A of length K. The returned array should be sorted in ascending order,
     * and if there is no number that can become a leader, you should return an empty array. Moreover,
     * if there are multiple ways of choosing a segment to turn some number into a leader, then this particular number should appear in an output array only once.
     *
     * @param K segment length
     * @param M A values not bigger than M
     * @param A
     * @return
     */
    public static int[] solution(final int K,final int M, int[] A) {

        validateArrayValues(M, A);
        List<Integer> leadersList = new ArrayList<>();
        final Integer leader = findLeader(A);
        if(leader != null) {
            leadersList.add(leader);
            for (int i = 0; i < A.length; i++) {
                if (A[i] == leader) {
                    int[] copyOfA = Arrays.copyOf(A, A.length);
                    incressingSegmentBy1(copyOfA, i, K);
                    Integer newLeader = findLeader(copyOfA);
                    if (newLeader != null && !leadersList.contains(newLeader)) {
                        leadersList.add(newLeader);
                    }
                }
            }
        }
        return leadersList.stream().mapToInt(i -> i).toArray();
    }

    private static void validateArrayValues(final int M, final int[] A) {

        for(int i = 0; i < A.length; i++) {
            if(A[i] > M || A[i] > MAX_VALUE) {
                throw new InvalidArrayException("array values not bigger than " + (A[i] > MAX_VALUE ? MAX_VALUE : M));
            }
        }

    }

    /**
     * The segment of the array is a sequence of consecutive elements of the array.
     * @param A
     * @param leader is a value that occurs in more than half of the elements of the array
     * @param K segment length
     */
    public static void incressingSegmentBy1(int[] A, int leader, int K) {

        int cont = 0;
        for(int i = leader + 1; cont < K && i < A.length; i++) {
            A[i]++;
            cont++;
        }
    }

    /**
     * The leader of the array is a value that occurs in more than half of the elements of the array
     * @param A
     * @return leader of array
     */
    public static Integer findLeader(final int[] A) {

        if(A.length == 0) {
            return null;
        }
        if(A.length == 1) {
            return A[0];
        }
        int[] copy = Arrays.copyOf(A, A.length);
        Arrays.sort(copy);
        int leader = A[0];
        int leaderCont = 1;
        int aux = copy[0];
        int cont = 1;

        for(int i = 1; i < copy.length; i++) {
            if(aux == copy[i]) {
                cont++;
                if(cont >= leaderCont && leader != aux) {
                    leader = aux;
                }
                if(leaderCont > A.length / 2 - 1) {
                    return leader;
                }
            } else {
                leaderCont = cont;
                cont = 1;
                aux = copy[i];
            }
        }
        if(leaderCont >= A.length / 2 - 1) {
            return leader;
        } else {
            return null;
        }
    }

}
