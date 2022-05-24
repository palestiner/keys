package org.palestiner.keys.screen.attachment;

import io.jmix.ui.UiComponents;
import io.jmix.ui.action.list.EditAction;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.LinkButton;
import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@UiController("Attachment.browse")
@UiDescriptor("attachment-browse.xml")
@LookupComponent("attachmentsTable")
public class AttachmentBrowse extends StandardLookup<Attachment> {
    @Autowired
    private UiComponents uiComponents;
    @Named("attachmentsTable.edit")
    private EditAction<Attachment> attachmentsTableEdit;

    @Install(to = "attachmentsTable.file", subject = "columnGenerator")
    private Component attachmentsTableFileColumnGenerator(Attachment attachment) {
        LinkButton linkButton = uiComponents.create(LinkButton.class);
        linkButton.setCaption(attachment.getFile().getFileName());
        linkButton.addClickListener(clickEvent -> attachmentsTableEdit.execute());
        return linkButton;
    }
}
