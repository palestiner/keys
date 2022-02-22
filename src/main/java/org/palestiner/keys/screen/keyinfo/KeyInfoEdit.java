package org.palestiner.keys.screen.keyinfo;

import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.KeyInfo;

@UiController("KeyInfo.edit")
@UiDescriptor("key-info-edit.xml")
@EditedEntityContainer("keyInfoDc")
public class KeyInfoEdit extends StandardEditor<KeyInfo> {
}
