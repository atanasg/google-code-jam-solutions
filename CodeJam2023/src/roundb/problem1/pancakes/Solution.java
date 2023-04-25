package roundb.problem1.pancakes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author atanasg
 * @see https://codingcompetitions.withgoogle.com/codejam/round/0000000000c9607c
 */
public class Solution {
    private static Map<Integer, Long> LEFT_SUMS_FROM_INDEX = new HashMap<>();
    private static Map<Integer, Long> RIGHT_SUMS_FROM_INDEX = new HashMap<>();

    private static long calculateMaxPancakesForAlice(int[] pancakeStacks, int la, int ra, int lb, int rb) {

        LEFT_SUMS_FROM_INDEX.put(0, (long)pancakeStacks[0]);
        for (int i = 1; i < pancakeStacks.length; i++) {
            LEFT_SUMS_FROM_INDEX.put(i, LEFT_SUMS_FROM_INDEX.get(i-1) + pancakeStacks[i]);
        }

        RIGHT_SUMS_FROM_INDEX.put(0, Arrays.stream(pancakeStacks).asLongStream().sum());
        for (int i = 1; i < pancakeStacks.length; i++) {
            RIGHT_SUMS_FROM_INDEX.put(i, RIGHT_SUMS_FROM_INDEX.get(i-1) - pancakeStacks[i-1]);
        }

        long result = 0L;

        for (int aliceFirstIndex = la; aliceFirstIndex <= ra; aliceFirstIndex++) {
            int bobOptimalFirstIndex;

            if (lb == rb) {
                // no choice for Bob
                bobOptimalFirstIndex = lb;
                if (aliceFirstIndex < bobOptimalFirstIndex) {
                    long res = maxLeftSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                    if (res > result) {
                        result = res;
                    }
                } else {
                    long res = maxRightSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                    if (res > result) {
                        result = res;
                    }
                }
            } else if (aliceFirstIndex < lb) {
                bobOptimalFirstIndex = lb;
                long res = maxLeftSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                if (res > result) {
                    result = res;
                }
            } else if (aliceFirstIndex > rb) {
                bobOptimalFirstIndex = rb;
                long res = maxRightSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                if (res > result) {
                    result = res;
                }
            } else {
                if (aliceFirstIndex == lb) {
                    bobOptimalFirstIndex = aliceFirstIndex + 1;
                    long res = maxLeftSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                    if (res > result) {
                        result = res;
                    }
                } else if (aliceFirstIndex == rb) {
                    bobOptimalFirstIndex = aliceFirstIndex - 1;
                    long res = maxRightSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                    if (res > result) {
                        result = res;
                    }
                } else {
                    bobOptimalFirstIndex = aliceFirstIndex + 1;
                    long res1 = maxLeftSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                    bobOptimalFirstIndex = aliceFirstIndex - 1;
                    long res2 = maxRightSumForAlice(pancakeStacks, aliceFirstIndex, bobOptimalFirstIndex);
                    if (Math.min(res1, res2) > result) {
                        result = Math.min(res1, res2);
                    }
                }
            }
        }
        return result;
    }

    private static long maxLeftSumForAlice(int[] pancakeStacks, int aliceFirstIndex, int bobFirstIndex) {
        int splitPoint =  aliceFirstIndex + (int)(Math.ceil((bobFirstIndex - aliceFirstIndex -1)/2.0));
        return LEFT_SUMS_FROM_INDEX.get(splitPoint);
    }

    private static long maxRightSumForAlice(int[] pancakeStacks, int aliceFirstIndex, int bobFirstIndex) {
        int splitPoint =  aliceFirstIndex - (int)(Math.ceil((aliceFirstIndex - bobFirstIndex-1)/2.0));
        return RIGHT_SUMS_FROM_INDEX.get(splitPoint);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int [] pancakeStacks = new int[n];
            for (int j = 0; j < pancakeStacks.length; j++) {
                pancakeStacks[j] = in.nextInt();
            }
            int la = in.nextInt();
            int ra = in.nextInt();
            int lb = in.nextInt();
            int rb = in.nextInt();
            System.out.println("Case #" + i + ": " + calculateMaxPancakesForAlice(pancakeStacks, la-1, ra-1, lb-1, rb-1));
        }
    }
}
