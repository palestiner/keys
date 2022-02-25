package org.palestiner.keys.screen.keyinfo;

import io.jmix.core.common.util.ParamsMap;
import io.jmix.core.usersubstitution.CurrentUserSubstitution;
import io.jmix.ui.*;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Component;
import io.jmix.ui.screen.*;
import org.palestiner.keys.app.SecureTool;
import org.palestiner.keys.entity.KeyInfo;
import org.palestiner.keys.screen.clipboard.ClipboardScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Consumer;

@UiController("KeyInfo.browse")
@UiDescriptor("key-info-browse.xml")
@LookupComponent("keysInfoTable")
public class KeyInfoBrowse extends StandardLookup<KeyInfo> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private SecureTool secureTool;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private Notifications notifications;
    @Autowired
    private Fragments fragments;
    @Autowired
    private Screens screens;
    @Autowired
    private CurrentUserSubstitution currentUserSubstitution;

    @Install(to = "keysInfoTable.qas", subject = "columnGenerator")
    private Component keysInfoTableQasColumnGenerator(KeyInfo keyInfo) {
        Button button = uiComponents.create(Button.class);
        String key = keyInfo.getProject().getQas();
        button.addClickListener(clickListener(keyInfo.getInput(), key, "QAS"));
        button.setCaption(messageBundle.getMessage("keyInfoBrowse.showBtn"));
        return button;
    }


    @Install(to = "keysInfoTable.dev", subject = "columnGenerator")
    private Component keysInfoTableDevColumnGenerator(KeyInfo keyInfo) {
        Button button = uiComponents.create(Button.class);
        String key = keyInfo.getProject().getDev();
        button.addClickListener(clickListener(keyInfo.getInput(), key, "DEV"));
        button.setCaption(messageBundle.getMessage("keyInfoBrowse.showBtn"));

        return button;
    }

    @Install(to = "keysInfoTable.prd", subject = "columnGenerator")
    private Component keysInfoTablePrdColumnGenerator(KeyInfo keyInfo) {
        Button button = uiComponents.create(Button.class);
        String key = keyInfo.getProject().getPrd();
        button.addClickListener(clickListener(keyInfo.getInput(), key, "PRD"));
        button.setCaption(messageBundle.getMessage("keyInfoBrowse.showBtn"));
        return button;
    }

    private Consumer<Button.ClickEvent> clickListener(String value, String key, String caption) {
        return clickEvent -> {
            screens.create(ClipboardScreen.class,
                            OpenMode.DIALOG,
                            new MapScreenOptions(ParamsMap.of(
                                    "message", getMessage(key, value),
                                    "caption", caption
                            )))
                    .show();
        };
    }

    private String getMessage(String key, String value) {
        return secureTool.encrypt(key, value);
    }

    @Install(to = "keysInfoTable.remove", subject = "enabledRule")
    private boolean keysInfoTableRemoveEnabledRule() {
        return "admin".equals(currentUserSubstitution.getAuthenticatedUser().getUsername());
    }
}
