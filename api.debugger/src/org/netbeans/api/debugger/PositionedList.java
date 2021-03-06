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
package org.netbeans.api.debugger;

import java.util.List;

/**
 * A list implementation with extra position information.
 * 
 * @author Martin Entlicher
 */
interface PositionedList<T> extends List<T> {
    
    /**
     * Test if this list contains positions.
     */
    boolean hasPositions();
    
    /**
     * Get the position of an element at the given index.
     * @param elementIndex the index of the requested element
     * @return The position, or {@link Integer#MAX_VALUE} for no position,
     *         or <code>0</code> when the position does not matter.
     * @see org.​openide.​filesystems.​annotations.​LayerBuilder.​File.position()
     */
    int getPosition(int elementIndex);
    
}
