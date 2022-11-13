package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @DisplayName("3개 일치하는 경우")
    @Test
    void threeNumberMatch() {
        // given
        Result result = new Result();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Customer customer = new Customer();

        // when
        customer.inputWinningNumber("1,2,3,7,8,9");
        customer.inputBonusNumber("10");
        result.compare(customer, lotto);

        // then
        assertThat(result.getWinBoard().get(3f)).isEqualTo(1);
        assertThat(result.getWinBoard().get(4f)).isEqualTo(0);
    }

    @DisplayName("4개 일치하는 경우")
    @Test
    void fourNumberMatch() {
        // given
        Result result = new Result();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Customer customer = new Customer();

        // when
        customer.inputWinningNumber("1,2,3,4,8,9");
        customer.inputBonusNumber("10");
        result.compare(customer, lotto);

        // then
        assertThat(result.getWinBoard().get(4f)).isEqualTo(1);
    }

    @DisplayName("4개 일치에 보너스가 일치하는 의미없는 경우")
    @Test
    void fourNumberAndBonusNumberMatch() {
        // given
        Result result = new Result();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Customer customer = new Customer();

        // when
        customer.inputWinningNumber("1,2,3,4,8,9");
        customer.inputBonusNumber("5");
        result.compare(customer, lotto);

        // then
        assertThat(result.getWinBoard().get(4f)).isEqualTo(1);
    }

    @DisplayName("5개 일치하는 경우")
    @Test
    void fiveNumberMatch() {
        // given
        Result result = new Result();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Customer customer = new Customer();

        // when
        customer.inputWinningNumber("1,2,3,4,5,9");
        customer.inputBonusNumber("10");
        result.compare(customer, lotto);

        // then
        assertThat(result.getWinBoard().get(5f)).isEqualTo(1);
    }

    @DisplayName("5개와 보너스가 일치하는 경우")
    @Test
    void fiveNumberAndBounsNumberMatch() {
        // given
        Result result = new Result();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Customer customer = new Customer();

        // when
        customer.inputWinningNumber("1,2,3,4,5,9");
        customer.inputBonusNumber("6");
        result.compare(customer, lotto);

        // then
        assertThat(result.getWinBoard().get(5.5f)).isEqualTo(1);
    }

    @DisplayName("6개 일치하는 경우")
    @Test
    void allNumberMatch() {
        // given
        Result result = new Result();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Customer customer = new Customer();

        // when
        customer.inputWinningNumber("1,2,3,4,5,6");
        customer.inputBonusNumber("10");
        result.compare(customer, lotto);

        // then
        assertThat(result.getWinBoard().get(6f)).isEqualTo(1);
    }
}
