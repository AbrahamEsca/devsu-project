package mx.com.clients.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClientTest {

	@Test
    void testConstructorAndGetters() {
        Client client = new Client("C123", "pass", "John Doe", "M", 30,
                "ID12345", "123 Main St", "5551234567");

        assertThat(client.getClientId()).isEqualTo("C123");
        assertThat(client.getPassword()).isEqualTo("pass");
        assertThat(client.getActive()).isTrue();
        assertThat(client.getName()).isEqualTo("John Doe");
        assertThat(client.getGender()).isEqualTo("M");
        assertThat(client.getAge()).isEqualTo(30);
        assertThat(client.getIdentification()).isEqualTo("ID12345");
        assertThat(client.getAddress()).isEqualTo("123 Main St");
        assertThat(client.getPhone()).isEqualTo("5551234567");
    }

    @Test
    void testSettersUpdateValues() {
        Client client = new Client("C123", "pass", "John Doe", "M", 30,
                "ID12345", "123 Main St", "5551234567");

        client.setClientId("C999");
        client.setPassword("cpass");
        client.setActive(false);

        assertThat(client.getClientId()).isEqualTo("C999");
        assertThat(client.getPassword()).isEqualTo("cpass");
        assertThat(client.getActive()).isFalse();
    }

}
