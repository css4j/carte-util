/*

 Copyright (c) 2005-2025, Carlos Amengual.

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause
 */

package io.sf.carte.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class BufferSimpleWriterTest {

	@Test
	public void testBufferSimpleWriter() throws IOException {
		BufferSimpleWriter wri = new BufferSimpleWriter();
		wri.write('a');
		wri.write("bc");
		char[] cbuf = {'d', 'e', 'f'};
		wri.write(cbuf , 0, cbuf.length);
		wri.write(1);
		wri.write(0);
		assertEquals("abcdef10", wri.toString());
		wri.unwrite();
		assertEquals("abcdef1", wri.toString());
		wri.unwrite(2);
		assertEquals("abcde", wri.toString());
		wri.append('g');
		assertEquals("abcdeg", wri.toString());
		wri.append("hijk", 1, 4);
		assertEquals("abcdegijk", wri.toString());
		wri.unwrite(20);
		assertEquals(0, wri.length());
	}

}
