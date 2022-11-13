package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.ErrorPhrase.DUPLICATE;
import static lotto.ErrorPhrase.WRONG_INPUT;
import static lotto.ErrorPhrase.WRONG_LENGTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static lotto.Const.SIZE;
import static lotto.ErrorPhrase.WRONG_RANGE;

public class CustomerTest {

    @DisplayName("사용자가 로또 구매 금액을 입력하면 매수를 반환")
    @Test
    void calculateHowManySheet() {
        // given
        Customer customer = new Customer();

        // when
        int sheet = customer.buy("8000");

        // then
        assertThat(sheet).isEqualTo(8);
    }

    @DisplayName("사용자가 잘못된 금액을 입력하면 예외 반환")
    @Test
    void customerPayWrong() {
        // given
        Customer customer = new Customer();

        // then
        assertThatThrownBy(() -> customer.buy("8100"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorPhrase.WRONG_PAY.toString());
    }

    @DisplayName("사용자가 당첨 번호를 입력하는 기능")
    @Test
    void customerEntersWinningNumber() {
        // given
        Customer customer = new Customer();

        // when
        customer.inputWinningNumber("1,2,3,4,5,6");
        boolean wellEntered = true;
        for (int i = 1; i <= 6; i++) {
            if (customer.getWinningNumber().get(i - 1) != i) {
                wellEntered = false;
            }
        }

        // then
        assertThat(SIZE.equals(customer.getWinningNumber().size())).isTrue();
        assertThat(wellEntered).isTrue();
    }

    @DisplayName("사용자가 당첨 번호를 잘못 입력하여 예외 발생")
    @Test
    void customerEntersWrongWinningNumber() {
        // given
        Customer customer = new Customer();

        // then
        assertThatThrownBy(() -> customer.inputWinningNumber("0,1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_RANGE.toString());
        assertThatThrownBy(() -> customer.inputWinningNumber("a,1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_INPUT.toString());
        assertThatThrownBy(() -> customer.inputWinningNumber("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(WRONG_LENGTH.toString());
        assertThatThrownBy(() -> customer.inputWinningNumber("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATE.toString());
    }
}
