package disks2.form;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Created by adminvl on 21.10.2015.
 */
public class LoginForm {
    @NotEmpty
    @Size(min = 1, max = 30)
    private String login;
    @NotEmpty
    @Size(min = 1, max = 30)
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}