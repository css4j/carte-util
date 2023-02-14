/*

 Copyright (c) 2005-2021, Carlos Amengual.

 SPDX-License-Identifier: BSD-3-Clause

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */

package io.sf.carte.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SingleElementIteratorTest {

	@Test
	public void testSingleElementIterator() {
		String s = "foo";
		SingleElementIterator<String> it = new SingleElementIterator<String>(s);
		assertTrue(it.hasNext());
		assertEquals("foo", it.next());
		assertFalse(it.hasNext());
	}

	@Test
	public void testSingleElementIteratorError() {
		try {
			new SingleElementIterator<String>(null);
			fail("Must throw exception.");
		} catch (NullPointerException e) {
		}
	}

}
