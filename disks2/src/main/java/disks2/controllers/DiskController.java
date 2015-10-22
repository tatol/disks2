package disks2.controllers;

import disks2.form.LoginForm;
import disks2.service.DiskService;
import disks2.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by adminvl on 21.10.2015.
 */
@Controller
public class DiskController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiskService diskService;

    private Integer currentUserId;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Map model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String processLoginForm(@Valid LoginForm loginForm, BindingResult result, Map model) {

        currentUserId = userService.getUserId(loginForm.getLogin(), loginForm.getPassword());

        return "redirect:/login/listOwnDisks";
    }

    @RequestMapping(value = "/login/listOwnDisks")
    public ModelAndView listOwnDisks() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("listOwnDisks", diskService.listOwnDisks(currentUserId));
        return new ModelAndView("listOwnDisks", model);
    }
}
