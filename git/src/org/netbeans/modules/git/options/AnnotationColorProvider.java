/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.git.options;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.Collection;
import javax.swing.UIManager;
import org.netbeans.api.options.OptionsDisplayer;
import org.netbeans.modules.git.Annotator;
import org.netbeans.modules.git.Git;
import org.netbeans.modules.git.GitModuleConfig;
import org.netbeans.modules.git.GitVCS;
import org.netbeans.modules.versioning.util.OptionsPanelColorProvider;
import org.netbeans.modules.versioning.util.Utils;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author ondra
 */
@ServiceProviders({@ServiceProvider(service=OptionsPanelColorProvider.class), @ServiceProvider(service=AnnotationColorProvider.class)})
@OptionsPanelController.Keywords(keywords={"git", "#KW_Colors"}, location=OptionsDisplayer.FONTSANDCOLORS, tabTitle="#CTL_AnnotationColorsPanel.title")
@NbBundle.Messages("CTL_AnnotationColorsPanel.title=Versioning")
public class AnnotationColorProvider extends OptionsPanelColorProvider {

    private static String name;
    private static AnnotationColorProvider INSTANCE;
    private static final Color ADDED_COLOR;
    private static final Color MODIFIED_COLOR;
    private static final Color DELETED_COLOR;
    private static final Color CONFLICTED_COLOR;
    private static final Color IGNORED_COLOR;
    private static final Color TEXT_ANNOTATION_COLOR;
    static {
        Color c = UIManager.getColor("nb.versioning.added.color");
        ADDED_COLOR = c == null ? new Color(0, 0x80, 0) : c;
        c = UIManager.getColor("nb.versioning.modified.color");
        MODIFIED_COLOR = c == null ? new Color(0, 0, 0xff) : c;
        c = UIManager.getColor("nb.versioning.deleted.color");
        DELETED_COLOR = c == null ? new Color(0x99, 0x99, 0x99) : c;
        c = UIManager.getColor("nb.versioning.conflicted.color");
        CONFLICTED_COLOR = c == null ? new Color(0xff, 0, 0) : c;
        c = UIManager.getColor("nb.versioning.ignored.color");
        IGNORED_COLOR = c == null ? new Color(0x99, 0x99, 0x99) : c;
        c = UIManager.getColor("nb.versioning.textannotation.color"); //NOI18N
        TEXT_ANNOTATION_COLOR = c == null ? new Color(0x99, 0x99, 0x99) : c;
    }

    public final AnnotationFormat UP_TO_DATE_FILE = createAnnotationFormat("uptodate", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_UpToDate"), null, false); //NOI18N
    public final AnnotationFormat UP_TO_DATE_FILE_TOOLTIP = createAnnotationFormat("uptodateTT", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_UpToDate"), null, true); //NOI18N
    public final AnnotationFormat NEW_FILE = createAnnotationFormat("new", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_New"), ADDED_COLOR, false); //NOI18N
    public final AnnotationFormat NEW_FILE_TOOLTIP = createAnnotationFormat("newTT", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_New"), ADDED_COLOR, true); //NOI18N
    public final AnnotationFormat ADDED_FILE = createAnnotationFormat("added", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Added"), ADDED_COLOR, false); //NOI18N
    public final AnnotationFormat ADDED_FILE_TOOLTIP = createAnnotationFormat("addedTT", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Added"), ADDED_COLOR, true); //NOI18N
    public final AnnotationFormat MODIFIED_FILE = createAnnotationFormat("modified", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Modified"), MODIFIED_COLOR, false); //NOI18N
    public final AnnotationFormat MODIFIED_FILE_TOOLTIP = createAnnotationFormat("modifiedTT", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Modified"), MODIFIED_COLOR, true); //NOI18N
    public final AnnotationFormat REMOVED_FILE = createAnnotationFormat("removed", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Removed"), DELETED_COLOR, false); //NOI18N
    public final AnnotationFormat REMOVED_FILE_TOOLTIP = createAnnotationFormat("removedTT", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Removed"), DELETED_COLOR, true); //NOI18N
    public final AnnotationFormat EXCLUDED_FILE = createAnnotationFormat("excluded", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Excluded"), IGNORED_COLOR, false); //NOI18N
    public final AnnotationFormat EXCLUDED_FILE_TOOLTIP = createAnnotationFormat("excludedTT", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Excluded"), IGNORED_COLOR, true); //NOI18N
    public final AnnotationFormat CONFLICT_FILE = createAnnotationFormat("conflict", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Conflict"), CONFLICTED_COLOR, false); //NOI18N
    public final AnnotationFormat CONFLICT_FILE_TOOLTIP = createAnnotationFormat("conflictTT", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_Conflict"), CONFLICTED_COLOR, true); //NOI18N
    public final AnnotationFormat TEXT_ANNOTATION = createAnnotationFormat("textAnnotation", NbBundle.getMessage(Annotator.class, "CTL_FileInfoStatus_TextAnnotation"), TEXT_ANNOTATION_COLOR, true); //NOI18N

    public AnnotationColorProvider () {
        initColors();
    }
    
    public static synchronized AnnotationColorProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = Lookup.getDefault().lookup(AnnotationColorProvider.class);
            if (INSTANCE == null) {
                INSTANCE = new AnnotationColorProvider();
            }
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return GitVCS.getDisplayName();
        }

    @Override
    protected Color getSavedColor (String key, Color defaultColor) {
        return GitModuleConfig.getDefault().getColor(key, defaultColor);
    }

    @Override
    protected MessageFormat createFormat (Color color, boolean isTooltip) {
        StringBuilder annotationFormatString = new StringBuilder("{0}"); //NOI18N
        if (color != null) {
            annotationFormatString = new StringBuilder("<font color=\"#") //NOI18N
                    .append(to2Hex(color.getRed())).append(to2Hex(color.getGreen())).append(to2Hex(color.getBlue())).append("\"").append(">{0}</font>"); //NOI18N
        }
        if (!isTooltip) {
            annotationFormatString.append("{1}"); //NOI18N
        }
        return new MessageFormat(annotationFormatString.toString());
    }

    @Override
    protected void saveColors (Collection<AnnotationFormat> colors) {
        for (AnnotationFormat af : colors) {
            if (af != null) {
                GitModuleConfig.getDefault().setColor(getColorKey(af.getKey()), af.getActualColor());
            }
        }
        Utils.postParallel(new Runnable() {
            @Override
            public void run() {
                Git.getInstance().refreshAllAnnotations();
            }
        }, 0);
    }

    private void initColors() {
        putColor(ADDED_FILE);
        putColor(CONFLICT_FILE);
        putColor(EXCLUDED_FILE);
        putColor(MODIFIED_FILE);
        putColor(NEW_FILE);
        putColor(REMOVED_FILE);
        putColor(TEXT_ANNOTATION);
    }
}
