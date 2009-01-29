/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections.iterators;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.collections.IterableMap;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.Unmodifiable;
import org.apache.commons.collections.bidimap.DualHashBidiMap;

/**
 * Tests the UnmodifiableMapIterator.
 *
 * @version $Revision$ $Date$
 *
 * @author Stephen Colebourne
 */
public class TestUnmodifiableMapIterator<K, V> extends AbstractTestMapIterator<K, V> {

    public static Test suite() {
        return new TestSuite(TestUnmodifiableMapIterator.class);
    }

    public TestUnmodifiableMapIterator(String testName) {
        super(testName);
    }

    public MapIterator<K, V> makeEmptyIterator() {
        return UnmodifiableMapIterator.decorate(new DualHashBidiMap<K, V>().mapIterator());
    }

    public MapIterator<K, V> makeObject() {
        return UnmodifiableMapIterator.decorate(getMap().mapIterator());
    }

    @SuppressWarnings("unchecked")
    public IterableMap<K, V> getMap() {
        IterableMap<K, V> testMap = new DualHashBidiMap<K, V>();
        testMap.put((K) "A", (V) "a");
        testMap.put((K) "B", (V)"b");
        testMap.put((K) "C", (V) "c");
        return testMap;
    }

    @SuppressWarnings("unchecked")
    public Map<K, V> getConfirmedMap() {
        Map<K, V> testMap = new HashMap<K, V>();
        testMap.put((K) "A", (V) "a");
        testMap.put((K) "B", (V)"b");
        testMap.put((K) "C", (V) "c");
        return testMap;
    }

    public boolean supportsRemove() {
        return false;
    }

    public boolean supportsSetValue() {
        return false;
    }

    //-----------------------------------------------------------------------
    public void testMapIterator() {
        assertTrue(makeEmptyIterator() instanceof Unmodifiable);
    }

    public void testDecorateFactory() {
        MapIterator<K, V> it = makeObject();
        assertSame(it, UnmodifiableMapIterator.decorate(it));

        it = getMap().mapIterator() ;
        assertTrue(it != UnmodifiableMapIterator.decorate(it));

        try {
            UnmodifiableMapIterator.decorate(null);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

}
