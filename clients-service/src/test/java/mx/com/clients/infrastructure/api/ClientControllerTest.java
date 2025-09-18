package mx.com.clients.infrastructure.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import mx.com.clients.application.port.in.ClientUseCase;
import mx.com.clients.infrastructure.api.dto.ClientRequestDTO;
import mx.com.clients.infrastructure.api.dto.ClientResponseDTO;

class ClientControllerTest {

	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}
	
	@Mock
    private ClientUseCase clientUseCase;

    @InjectMocks
    private ClientController clientController;
    
	@Test	
	public void testCreateClient() {
		ClientRequestDTO requestDTO = new ClientRequestDTO("Abraham Escalante", "M", 30, "EFAA921202TH0", "Jade 206 Gema", "7714035505", "$2a$10$l88Kb290FeQtdgT20MoZi.PpsGA7FDnt7d.LhZh/vqA7hs5hNowwC", true);
        ClientResponseDTO responseDTO = new ClientResponseDTO(1L, "CLI-20250917-E6E570", "Abraham Escalante", true);
        
		when(clientUseCase.createClient(any())).thenReturn(responseDTO);
		
		HttpServletRequest request = new MockHttpServletRequest();
		ResponseEntity<ClientResponseDTO> resp = clientController.createClient(request, requestDTO);
		assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}
	
    @Test	
	public void testGetClientById() {
    	ClientResponseDTO responseDTO = new ClientResponseDTO(1L, "CLI-20250917-E6E570", "Abraham Escalante", true);
		
		when(clientUseCase.getClientById(anyString())).thenReturn(responseDTO);
		
		HttpServletRequest request = new MockHttpServletRequest();
		ResponseEntity<ClientResponseDTO> resp = clientController.getClientById(request, "CLI-20250917-E6E570");
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test	
	public void testUpdateClient() {
		ClientRequestDTO requestDTO = new ClientRequestDTO("Abraham Escalante", "M", 30, "EFAA921202TH0", "Jade 206 Gema", "7714035505", "$2a$10$l88Kb290FeQtdgT20MoZi.PpsGA7FDnt7d.LhZh/vqA7hs5hNowwC", true);
        ClientResponseDTO responseDTO = new ClientResponseDTO(1L, "CLI-20250917-E6E570", "Abraham Escalante", true);
        
		when(clientUseCase.updateClient(anyString(), any(ClientRequestDTO.class))).thenReturn(responseDTO);
		
		HttpServletRequest request = new MockHttpServletRequest();
		ResponseEntity<ClientResponseDTO> resp = clientController.updateClient(request, "CLI-20250917-E6E570", requestDTO);
		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
	
	@Test	
	public void testDeleteClient() {
		doNothing().when(clientUseCase).deleteClient(anyString());
		
		HttpServletRequest request = new MockHttpServletRequest();
		clientController.deleteClient(request, "CLI-20250917-E6E570");
		ResponseEntity<Void> resp = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
	}

}
