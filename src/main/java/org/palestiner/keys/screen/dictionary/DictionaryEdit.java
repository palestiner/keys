package org.palestiner.keys.screen.dictionary;

import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Dictionary;

@UiController("Dictionary.edit")
@UiDescriptor("dictionary-edit.xml")
@EditedEntityContainer("dictionaryDc")
public class DictionaryEdit extends StandardEditor<Dictionary> {
}
