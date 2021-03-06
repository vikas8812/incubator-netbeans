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
package org.netbeans.modules.jshell.navigation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import org.netbeans.modules.jshell.env.JShellEnvironment;
import org.netbeans.modules.jshell.model.SnippetHandle;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author sdedic
 */
final class SnippetPanelUI extends javax.swing.JPanel implements Lookup.Provider, ExplorerManager.Provider, PropertyChangeListener {
    private BeanTreeView    treeView;
    private ExplorerManager manager = new ExplorerManager();
    private JShellEnvironment   env;
    private SnippetNodes    nodesFactory;
    private final InstanceContent selectedObjects = new InstanceContent();
    private final Lookup lkp =  new AbstractLookup(selectedObjects);
    
    /**
     * Creates new form SnippetPanelUI
     */
    public SnippetPanelUI() {
        treeView = new BeanTreeView();

        initComponents();
        treeView.setRootVisible(false);
        
        manager.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!ExplorerManager.PROP_SELECTED_NODES.equals(evt.getPropertyName())) {
            return;
        }
        for (Node n : (Node[])evt.getOldValue()) {
            selectedObjects.remove(n);
        }
        for (Node n : (Node[])evt.getNewValue()) {
            selectedObjects.add(n);
        }
    }

    @Override
    public Lookup getLookup() {
        return lkp;
    }
    
    void unselectAll() {
        for (Node n : manager.getSelectedNodes()) {
            selectedObjects.remove(n);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        treeScrollPane = treeView;

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(treeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(treeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public ExplorerManager getExplorerManager() {
        return manager;
    }
    
    public void navigateToHandle(SnippetHandle handle) {
        Node n = nodesFactory.getNodeFor(handle);
        if (n != null) {
            try {
                manager.setSelectedNodes(new Node[] { n });
            } catch (PropertyVetoException ex) {
                // ignore.
            }
        }
    }

    public void navigate(JShellEnvironment env) {
        if (this.env == env) {
            return;
        }
        if (this.env != null) {
            this.selectedObjects.remove(this.env);
        }
        this.selectedObjects.add(env);
        this.env = env;
        this.nodesFactory = new SnippetNodes(env);
        manager.setRootContext(new AbstractNode(nodesFactory));
        try {
            manager.setSelectedNodes(new Node[0]);
        } catch (PropertyVetoException ex) {
        }
    }

}
