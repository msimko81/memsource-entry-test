package cz.memsource.entrytest.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RequestController {

    @Autowired
    private RequestService service;

    @ModelAttribute("requestKinds")
    public List<RequestKind> getMultiCheckboxAllValues() {
        return service.listRequestKinds();
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/request")
    public String greeting(Model model) {
        model.addAttribute("request", new Request());
        return "request";
    }

    @PostMapping("/request")
    public String greetingSubmit(@Valid Request request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "request";
        }

        service.saveRequest(request);
        model.addAttribute("requestSaved", true);
        return "index";
    }
}
