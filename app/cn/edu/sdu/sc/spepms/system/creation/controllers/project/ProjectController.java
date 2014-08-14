package cn.edu.sdu.sc.spepms.system.creation.controllers.project;

import java.util.List;

import cn.edu.sdu.sc.spepms.framework.wireframe.Wireframe;
import cn.edu.sdu.sc.spepms.system.common.models.User;
import cn.edu.sdu.sc.spepms.system.common.views.html.*;
import cn.edu.sdu.sc.spepms.system.creation.forms.ProjectForm;
import cn.edu.sdu.sc.spepms.system.creation.models.CreationProject;
import cn.edu.sdu.sc.spepms.system.creation.views.html.project.*;
import play.*;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.userList;
public class ProjectController extends Controller {
    //创新项目发布
    public static Result addProject() {
        Wireframe.current().setShowBusinessMenu(true);
        return ok(add.render());
    }

    // 创新审核报名,展示数据库相关信息
    public static Result showProject() {
        Wireframe.current().setShowBusinessMenu(true);
        CreationProject creationProject = JPA.em().createQuery("from CreationProject", CreationProject.class).getSingleResult();
        return ok(details.render(creationProject));
    }

    //信息录入数据库
    @Transactional
    public static Result projectSave() {
        Form<ProjectForm> form = Form.form(ProjectForm.class).bindFromRequest();
        ProjectForm data = form.get();

        CreationProject creatonProject=new CreationProject();
        creatonProject.setName(data.getName());
        creatonProject.setCategory(data.getCategory());
        creatonProject.setBillable(data.getBillable());
        creatonProject.setRewardMethod(data.getRewardMethod());
        creatonProject.setRewardAmount(data.getRewardAmount());
        creatonProject.setDescription(data.getDescription());
        creatonProject.setApplicableFrom(data.getApplicableFrom());
        creatonProject.setApplicableTo(data.getApplicableTo());
        creatonProject.setContactInfo(data.getContactInfo());
        creatonProject.setNumber(data.getNumber());

        JPA.em().persist(creatonProject);

        List<CreationProject> creationProject = JPA.em().createQuery("from CreationProject", CreationProject.class).getResultList();
        return ok(studentHome.render(creationProject));
        //return ok();
    }
}