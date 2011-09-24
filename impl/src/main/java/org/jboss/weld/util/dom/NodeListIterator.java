/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.util.dom;

import org.jboss.weld.exceptions.UnsupportedOperationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Iterator;

import static org.jboss.weld.logging.messages.UtilMessage.XML_DOM_READONLY;

public class NodeListIterator implements Iterator<Node> {

    private final NodeList nodeList;
    private int index;

    public NodeListIterator(NodeList nodeList) {
        this.nodeList = nodeList;
        index = 0;
    }

    public boolean hasNext() {
        return index < nodeList.getLength();
    }

    public Node next() {
        return nodeList.item(index++);
    }

    public void remove() {
        throw new UnsupportedOperationException(XML_DOM_READONLY);
    }

}
