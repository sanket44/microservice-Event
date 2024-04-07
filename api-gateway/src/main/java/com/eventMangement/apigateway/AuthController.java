package com.eventMangement.apigateway;

import com.eventMangement.apigateway.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @Value("${tokenEndpoint}")
    private String tokenEndpoint;

    @Value("${okta:clientId}")
    private String clientId;
    //
    @Value("${okta:clientSecret}")
    private String clientSecret;

    @Value("${spring:security:oauth2:client:registration:okta:redirect-uri}")
    private String redirectUri;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("login")
    public ResponseEntity<AuthenticationResponse> login(
            @AuthenticationPrincipal OidcUser oidcUser,
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setId(oidcUser.getEmail());
        authenticationResponse.setAccessToken(client.getAccessToken().getTokenValue());
        authenticationResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
        authenticationResponse.setExpiresAt(client.getAccessToken().getExpiresAt().getEpochSecond());
        authenticationResponse.setAuthorityList(oidcUser.getAuthorities().stream().map(
                GrantedAuthority::getAuthority
        ).collect(Collectors.toList()));
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<String> handleCallback(@RequestParam("code") String code) {
        // Exchange authorization code for access token
        String accessToken = exchangeCodeForToken(code);
        String jsonAccessToken = "{\"access_token\": \"" + accessToken + "\"}";
        // Store access token securely, e.g., in session or database
        return ResponseEntity.ok().body(jsonAccessToken);
    }

    private String exchangeCodeForToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("0oafxabpjx17KyfqO5d7", "eqPcXbs7zqQAGk6pEW4oX5xDTvQBV6XdcddYK5gYutSd3g9m_qypgwq9gUZCRsMp");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", "http://localhost:4200/login");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
            Map<String, Object> tokenResponse = response.getBody();
            System.err.println(tokenResponse.toString());
            System.err.println(tokenResponse.keySet());
            String accessToken = (String) tokenResponse.get("access_token");
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/sd")
    public String sd() {
        return "HI SD";
    }
}
