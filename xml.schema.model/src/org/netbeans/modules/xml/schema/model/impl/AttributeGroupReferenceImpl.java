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

package org.netbeans.modules.xml.schema.model.impl;

import org.netbeans.modules.xml.schema.model.*;
import org.netbeans.modules.xml.schema.model.visitor.SchemaVisitor;
import org.netbeans.modules.xml.xam.dom.NamedComponentReference;
import org.w3c.dom.Element;

/**
 * TODO implement
 * @author Chris Webster
 */
public class AttributeGroupReferenceImpl extends SchemaComponentImpl
implements AttributeGroupReference {
     public AttributeGroupReferenceImpl(SchemaModelImpl model) {
        this(model,createNewComponent(SchemaElements.ATTRIBUTE_GROUP,model));
    }
    
    public AttributeGroupReferenceImpl(SchemaModelImpl model, Element e) {
        super(model,e);
    }

	/**
	 *
	 *
	 */
	public Class<? extends SchemaComponent> getComponentType() {
		return AttributeGroupReference.class;
	}

    public void accept(SchemaVisitor v) {
        v.visit(this);
    }

    public void setGroup(NamedComponentReference<GlobalAttributeGroup> group) {
	setAttribute(GROUP_PROPERTY, SchemaAttributes.REF, group);
    }

    public NamedComponentReference<GlobalAttributeGroup> getGroup() {
        return resolveGlobalReference(GlobalAttributeGroup.class, SchemaAttributes.REF);
 
    }
}
