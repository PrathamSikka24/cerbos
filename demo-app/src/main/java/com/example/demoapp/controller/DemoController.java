package com.example.demoapp.controller;

import dev.cerbos.api.v1.svc.CerbosBlockingClient;
import dev.cerbos.api.v1.svc.CerbosClientBuilder;
import dev.cerbos.sdk.model.CheckResult;
import dev.cerbos.sdk.model.Principal;
import dev.cerbos.sdk.model.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String helloWorld() {
        try {
            CerbosBlockingClient client = new CerbosClientBuilder("localhost:3593").withPlaintext().buildBlockingClient();

            Principal principal = Principal.newInstance("john", "employee")
                    .withPolicyVersion("20210210")
                    .withAttribute("department", "marketing")
                    .withAttribute("geography", "GB");

            Resource resource = Resource.newInstance("leave_request", "xx125")
                    .withPolicyVersion("20210210")
                    .withAttribute("department", "marketing")
                    .withAttribute("geography", "GB")
                    .withAttribute("owner", "john");

            CheckResult result = client.check(principal, resource, "view:public", "approve");

            if (result.isAllowed("approve")) {
                return "Hello, World!";
            } else {
                return "Not Authorized!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
