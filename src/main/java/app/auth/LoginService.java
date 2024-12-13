//AuthenticationService.java
package app.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import app.entity.Cliente;
import app.entity.Funcionario;
import app.repository.ClienteRepository;
import app.repository.FuncionarioRepository;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    public String logar(LoginModel user) {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("client_id", user.getClientId());
        formData.add("username", user.getUsername());
        formData.add("password", user.getPassword());
        formData.add("grant_type", user.getGrantType());
        System.out.println("aqui");
        
        String url = "http://192.168.56.10:8080/realms/barberscom-realm/protocol/openid-connect/token";
        
        HttpEntity<MultiValueMap<String, String>> entity
		= new HttpEntity<>(formData, headers);
        
        String result = rt.postForEntity(url, entity, String.class).getBody();
        return result;
    }
}