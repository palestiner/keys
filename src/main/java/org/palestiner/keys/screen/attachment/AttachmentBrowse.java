package org.palestiner.keys.screen.attachment;

import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Attachment;

@UiController("Attachment.browse")
@UiDescriptor("attachment-browse.xml")
@LookupComponent("attachmentsTable")
public class AttachmentBrowse extends StandardLookup<Attachment> {
}
