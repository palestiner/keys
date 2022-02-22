package org.palestiner.keys.screen.project;

import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@UiController("Project.edit")
@UiDescriptor("project-edit.xml")
@EditedEntityContainer("projectDc")
public class ProjectEdit extends StandardEditor<Project> {

    @Subscribe("genQas")
    public void onGenQasClick(Button.ClickEvent event) {
        getEditedEntity().setQas(generateKey());
    }

    @Subscribe("genDev")
    public void onGenDevClick(Button.ClickEvent event) {
        getEditedEntity().setDev(generateKey());
    }

    @Subscribe("genPrd")
    public void onGenPrdClick(Button.ClickEvent event) {
        getEditedEntity().setPrd(generateKey());
    }

    private String generateKey() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 32;
        return new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
