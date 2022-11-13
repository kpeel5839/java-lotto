package lotto;

import java.util.HashMap;
import java.util.Map;

import static lotto.Const.SIZE;

public class Result {
    private Map<Float, Integer> winBoard;

    public Result() {
        initWinBoard();
    }

    public void compare(Customer customer, Lotto lotto) {
        float matchingNumber = numberOfMatchingNumbers(customer.getWinningNumber(), lotto); // lotto 번호만 넘김

        if (matchingNumber == 5f) {
            if (match(lotto, customer.getBonusNumber())) {
                matchingNumber += 0.5;
            }
        }

        updateWinBoard(matchingNumber);
    }

    private void updateWinBoard(float matchingNumber) {
        winBoard.put(matchingNumber, winBoard.get(matchingNumber) + 1);
    }

    private int numberOfMatchingNumbers(Lotto winningNumber, Lotto lotto) {
        int matchingCount = 0;

        for (int i = 0; i < SIZE.getNumber(); i++) {
            if (match(winningNumber, lotto.getNumbers().get(i))) {
                matchingCount++;
            }
        }

        return matchingCount;
    }

    private boolean match(Lotto lotto, int findNumber) {
        return lotto.getNumbers().contains(findNumber);
    }

    private void initWinBoard() {
        winBoard = new HashMap<>();

        for (float i = 3; i <= 6; i++) {
            winBoard.put(i, 0);
        }

        winBoard.put(5.5f, 0);
    }

    public Map<Float, Integer> getWinBoard() {
        return this.winBoard;
    }
}