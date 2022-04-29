package org.palestiner.keys.screen.dictionary;

import io.jmix.core.common.util.ParamsMap;
import io.jmix.ui.Screens;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.PasswordField;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Dictionary;
import org.palestiner.keys.screen.clipboard.ClipboardScreen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.function.Consumer;

@UiController("Dictionary.browse")
@UiDescriptor("dictionary-browse.xml")
@LookupComponent("dictionariesTable")
public class DictionaryBrowse extends StandardLookup<Dictionary> {

    @Autowired
    private Screens screens;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Button getButton(String value, String screenCaption) {
        Button button = uiComponents.create(Button.class);
        button.addClickListener(clickListener(value, screenCaption));
        button.setCaption(messageBundle.getMessage("keyInfoBrowse.showBtn"));
        return button;
    }

    private Consumer<Button.ClickEvent> clickListener(String value, String caption) {
        return clickEvent -> {
            screens.create(ClipboardScreen.class,
                            OpenMode.DIALOG,
                            new MapScreenOptions(ParamsMap.of(
                                    "message", value,
                                    "caption", caption
                            )))
                    .show();
        };
    }

    @Install(to = "dictionariesTable.value", subject = "columnGenerator")
    private Component dictionariesTableValueColumnGenerator(Dictionary dictionary) {
        return getButton(dictionary.getValue(), dictionary.getKey());
    }
}
