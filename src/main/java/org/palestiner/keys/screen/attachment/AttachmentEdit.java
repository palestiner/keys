package org.palestiner.keys.screen.attachment;

import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Attachment;

@UiController("Attachment.edit")
@UiDescriptor("attachment-edit.xml")
@EditedEntityContainer("attachmentDc")
public class AttachmentEdit extends StandardEditor<Attachment> {
}
