package mx.com.accounts.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class AccountTest {

	@Test
    void testAllArgsConstructorAndGetters() {
        Account account = new Account(
                1L,
                "CLI-123",
                "12345678901",
                2L,
                "Corriente",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(1000),
                true
        );

        assertThat(account.getAccountId()).isEqualTo(1L);
        assertThat(account.getClientId()).isEqualTo("CLI-123");
        assertThat(account.getAccountNumber()).isEqualTo("12345678901");
        assertThat(account.getAccountTypeId()).isEqualTo(2L);
        assertThat(account.getDescAccountType()).isEqualTo("Corriente");
        assertThat(account.getInitialBalance()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(account.getCurrentBalance()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(account.getActive()).isTrue();
    }

    @Test
    void testCustomConstructor() {
        Account account = new Account(
                "CLI-456",
                "012345678901",
                3L,
                BigDecimal.valueOf(500),
                BigDecimal.valueOf(500),
                false
        );

        assertThat(account.getClientId()).isEqualTo("CLI-456");
        assertThat(account.getAccountNumber()).isEqualTo("012345678901");
        assertThat(account.getAccountTypeId()).isEqualTo(3L);
        assertThat(account.getInitialBalance()).isEqualTo(BigDecimal.valueOf(500));
        assertThat(account.getCurrentBalance()).isEqualTo(BigDecimal.valueOf(500));
        assertThat(account.getActive()).isFalse();
    }

    @Test
    void testSetters() {
        Account account = new Account();
        account.setAccountId(10L);
        account.setClientId("CLI-789");
        account.setAccountNumber("23424567865");
        account.setAccountTypeId(4L);
        account.setDescAccountType("Ahorro");
        account.setInitialBalance(BigDecimal.valueOf(200));
        account.setCurrentBalance(BigDecimal.valueOf(300));
        account.setActive(true);

        assertThat(account.getAccountId()).isEqualTo(10L);
        assertThat(account.getDescAccountType()).isEqualTo("Ahorro");
        assertThat(account.getCurrentBalance()).isEqualTo(BigDecimal.valueOf(300));
        assertThat(account.getActive()).isTrue();
    }

}
