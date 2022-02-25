package org.palestiner.keys.screen.project;

import io.jmix.core.EntityStates;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Form;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Project;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@UiController("Project.edit")
@UiDescriptor("project-edit.xml")
@EditedEntityContainer("projectDc")
public class ProjectEdit extends StandardEditor<Project> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProjectEdit.class);
    @Autowired
    private Form form;
    @Autowired
    private EntityStates entityStates;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        form.setEnabled(entityStates.isNew(getEditedEntity()));
    }


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

    @Subscribe("editEnabled")
    public void onEditEnabledValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        form.setEnabled(Boolean.TRUE.equals(event.getValue()));
    }
}
