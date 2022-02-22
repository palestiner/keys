package org.palestiner.keys.screen.project;

import io.jmix.ui.screen.*;
import org.palestiner.keys.entity.Project;

@UiController("Project.browse")
@UiDescriptor("project-browse.xml")
@LookupComponent("projectsTable")
public class ProjectBrowse extends StandardLookup<Project> {
}
