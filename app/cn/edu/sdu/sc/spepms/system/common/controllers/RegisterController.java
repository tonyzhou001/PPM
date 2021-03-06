package cn.edu.sdu.sc.spepms.system.common.controllers;

import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import cn.edu.sdu.sc.spepms.framework.wireframe.Wireframe;
import cn.edu.sdu.sc.spepms.system.common.forms.RegisterForm;
import cn.edu.sdu.sc.spepms.system.common.models.User;
import cn.edu.sdu.sc.spepms.system.common.views.html.register;
import cn.edu.sdu.sc.spepms.system.common.views.html.profile;
public class RegisterController extends Controller {

    public static Result register() {
        Wireframe.current().setShowBusinessMenu(true);
        return ok(register.render());
    }

    @Transactional
    public static Result registerSave() {
        Form<RegisterForm> form = Form.form(RegisterForm.class).bindFromRequest();
        RegisterForm data = form.get();

        User user = new User();
        user.setName(data.getName());
        System.out.println(data.getName());
        user.setGender(data.getGender());
        user.setPersonalId(data.getPersonalId());
        user.setBirthday(data.getBirthday());
        user.setHometown(data.getHometown());
        user.setPassword(data.getPassword());

        JPA.em().persist(user);

        return ok(profile.render(user));
    }

}
