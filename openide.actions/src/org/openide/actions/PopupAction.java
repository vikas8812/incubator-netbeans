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
package org.openide.actions;

import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallbackSystemAction;


/** Open a popup menu.
 * For example, may be bound to the context menu in the Explorer.
 */
public final class PopupAction extends CallbackSystemAction {
    protected void initialize() {
        super.initialize();

        // Cf. org.netbeans.core.windows.frames.NbFocusManager:
        putProperty("OpenIDE-Transmodal-Action", Boolean.TRUE); // NOI18N
    }

    public String getName() {
        return NbBundle.getMessage(PopupAction.class, "Popup");
    }

    public HelpCtx getHelpCtx() {
        return new HelpCtx(PopupAction.class);
    }

    protected boolean asynchronous() {
        return false;
    }
}
