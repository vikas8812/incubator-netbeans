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
package org.netbeans.modules.css.prep.editor.refactoring;

import org.netbeans.modules.csl.api.OffsetRange;
import org.openide.filesystems.FileObject;

/**
 *
 * @author marekfukala
 */
public class RefactoringElement {
    
    private FileObject file;
    private OffsetRange range;
    private String name;

    public RefactoringElement(FileObject file, OffsetRange range, String name) {
        this.file = file;
        this.range = range;
        this.name = name;
    }

    public FileObject getFile() {
        return file;
    }

    public OffsetRange getRange() {
        return range;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(RefactoringElement.class.getSimpleName())
                .append(':')
                .append(file.getNameExt())
                .append(',')
                .append(name)
                .append(',')
                .append(range).toString();
    }
    
    
    
}
