package feiticeiros.example.fmbackend.oauthpkg.controller;

import feiticeiros.example.fmbackend.oauthpkg.service.OAuth2Service;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.core.user.OAuth2User;


import java.util.Map;

@RestController
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    public OAuth2Controller(OAuth2Service oaUth2Service) {
        this.oAuth2Service = oaUth2Service;
    }

    @GetMapping("/")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Map.of("error", "User is not authenticated");
        }

        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");
        String googleId = principal.getAttribute("sub");

        return Map.of(
                "name", name,
                "email", email,
                "picture", picture,
                "googleId", googleId
        );
    }


//    String email, String googleId, String image, String name
    @GetMapping("/login/success")
    public String loginSuccessPage(@AuthenticationPrincipal OAuth2User principal) {
        oAuth2Service.createUser(
                principal.getAttribute("email"),
                principal.getAttribute("sub"),
                principal.getAttribute("picture"),
                principal.getAttribute("name")
        );


        return """
        <script>
        window.close(); // Close the OAuth login popup
        </script>
    """;
    }



    @GetMapping("/login/failure")
    public String loginFailurePage() {
        return """
           <html>
           <p>Não foi conseguimos fazer o login. Por favor, tente novamente.</p>
           </html>        
            """;
    }
}







