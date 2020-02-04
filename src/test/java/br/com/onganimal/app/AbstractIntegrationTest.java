package br.com.onganimal.app;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.onganimal.app.service.UserService;
import br.com.onganimal.app.util.JwtTokenUtils;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
public class AbstractIntegrationTest {
	
	@Autowired
    protected MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private JwtTokenUtils jwtTokenUtils;
	
}
