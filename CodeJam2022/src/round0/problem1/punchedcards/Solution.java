package round0.problem1.punchedcards;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author atanasg
 * @see https://codingcompetitions.withgoogle.com/codejam/round/0000000000876ff1/0000000000a4621b
 */
public class Solution {
    private static final char DOT_CHAR = '.';
    private static final char PLUS_CHAR = '+';
    private static final char MINUS_CHAR = '-';
    private static final char VERTICAL_LINE_CHAR = '|';

    private static String printPunchedCard(int rows, int columns) {
        int card_rows = 2 * rows + 1;
        int card_columns = 2 * columns + 1;
        char[][] card = new char[card_rows][card_columns];

        // add all "."
        for (int i = 1; i < card_rows - 1; i = i + 2) {
            for (int j = 1; j < card_columns - 1; j = j + 2) {
                card[i][j] = DOT_CHAR;
            }
        }

        // add all "+"
        for (int i = 0; i <= card_rows - 1; i = i + 2) {
            for (int j = 0; j <= card_columns - 1; j = j + 2) {
                card[i][j] = PLUS_CHAR;
            }
        }

        // add all "-"
        for (int i = 0; i <= card_rows - 1; i = i + 2) {
            for (int j = 1; j < card_columns - 1; j = j + 2) {
                card[i][j] = MINUS_CHAR;
            }
        }

        // add all "|"
        for (int i = 1; i < card_rows - 1; i = i + 2) {
            for (int j = 0; j <= card_columns - 1; j = j + 2) {
                card[i][j] = VERTICAL_LINE_CHAR;
            }
        }

        // apply the special handling of the first cell
        card[0][0] = DOT_CHAR;
        card[0][1] = DOT_CHAR;
        card[1][0] = DOT_CHAR;

        // build the resulting string
        StringBuilder result = new StringBuilder("\n");
        for (int i = 0; i < card.length; i++) {
            result = result.append(card[i]);
            result = result.append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int r = in.nextInt();
            int c = in.nextInt();
            System.out.println("Case #" + i + ": " + printPunchedCard(r, c));
        }
    }
}
