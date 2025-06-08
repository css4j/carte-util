/*

 Copyright (c) 2005-2025, Carlos Amengual.

 SPDX-License-Identifier: BSD-3-Clause

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */

package io.sf.carte.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

public class DefaultSimpleWriterTest {

	@Test
	public void testDefaultSimpleWriter() throws IOException {
		StringWriter writer = new StringWriter();
		DefaultSimpleWriter wri = new DefaultSimpleWriter(writer);
		wri.write('a');
		wri.write("bc");
		char[] cbuf = {'d', 'e', 'f'};
		wri.write(cbuf , 0, cbuf.length);
		wri.write(1);
		wri.write(0);
		assertEquals("abcdef10", writer.toString());
		wri.append("ghijk", 1, 4);
		assertEquals("abcdef10hij", writer.toString());
		assertThrows(UnsupportedOperationException.class, () -> wri.unwrite());
		assertEquals("abcdef10hij", writer.toString());
		assertThrows(UnsupportedOperationException.class, () -> wri.unwrite(3));
		wri.append('_');
		assertEquals("abcdef10hij_", writer.toString());
	}

}
