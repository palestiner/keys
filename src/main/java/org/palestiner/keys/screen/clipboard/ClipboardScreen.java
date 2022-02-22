package org.palestiner.keys.screen.clipboard;

import io.jmix.ui.Actions;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.valuepicker.ValueClearAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.ClipboardTrigger;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ClipboardScreen")
@UiDescriptor("clipboard-screen.xml")
public class ClipboardScreen extends Screen {
    private static final Logger log = LoggerFactory.getLogger(ClipboardScreen.class);
    @Autowired
    private TextField<String> value;
    @Autowired
    private ClipboardTrigger clipboardTrigger;
    @Autowired
    private Notifications notifications;
    @Subscribe
    public void onInit(InitEvent event) {
        MapScreenOptions options = (MapScreenOptions) event.getOptions();
        String message = (String) options.getParams().get("message");
        String caption = (String) options.getParams().get("caption");
        value.setValue(message);
        value.setEditable(false);
        value.setWidth((message.length() * 10) + "");
        getWindow().setCaption(caption);
    }

    @Subscribe("copy")
    public void onCopyClick(Button.ClickEvent event) {
        if (clipboardTrigger.isSupportedByWebBrowser()) {
            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption("Value was copied to clipboard")
                    .show();
        } else {
            notifications.create()
                    .withCaption("Value is not copied, because your " +
                            "browser does not support this functionality")
                    .show();
        }
        closeWithDefaultAction();
    }
}
