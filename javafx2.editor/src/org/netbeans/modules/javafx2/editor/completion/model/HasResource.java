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
package org.netbeans.modules.javafx2.editor.completion.model;

import java.net.URL;
import org.netbeans.api.annotations.common.CheckForNull;

/**
 * Common interface for model elements, which point to an external resource.
 * 
 * @author sdedic
 */
public interface HasResource {
    /**
     * Path to the resource, as given in the soruce text
     * @return 
     */
    @CheckForNull
    public String getSourcePath();
    
    /**
     * Resolved reference to the resource.
     * @return 
     */
    @CheckForNull
    public URL  getResolvedURL();
}
