package cz.memsource.entrytest.setup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/setup")
    public String getAccount(Model model) {
        model.addAttribute("account", accountService.getAccount());
        return "setup";
    }

    @PostMapping("/setup")
    public String postAccount(@Valid Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "setup";
        }

        accountService.saveAccount(account);
        return "redirect:/";
    }
}
