package org.example.acsrecomapi.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.acsrecomapi.Common.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = Constant.HOST, allowCredentials = "true")
@SecurityRequirement(name = "Authorization")
public class SwaggerController {

    @RequestMapping("/ACSR-ECom-API")
    public String getRedirectUrl() {
        return "redirect:swagger-ui/";
    }
}
