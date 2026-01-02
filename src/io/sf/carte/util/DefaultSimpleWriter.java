/*

 Copyright (c) 2005-2026, Carlos Amengual.

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause
 */

package io.sf.carte.util;

import java.io.IOException;
import java.io.Writer;

/**
 * An implementation of {@link SimpleWriter} backed by a {@link Writer}.
 */
public class DefaultSimpleWriter implements SimpleWriter {

	private final Writer writer;

	/**
	 * Initializes a <code>BufferSimpleWriter</code> with a writer.
	 * 
	 * @param writer the writer.
	 */
	public DefaultSimpleWriter(Writer writer) {
		super();
		this.writer = writer;
	}

	/**
	 * Get the {@code Writer}.
	 * 
	 * @return the writer.
	 */
	public Writer getWriter() {
		return writer;
	}

	@Override
	public void newLine() throws IOException {
		writer.append('\n');
	}

	@Override
	public void write(CharSequence s) throws IOException {
		writer.append(s);
	}

	@Override
	public void write(char[] cbuf, int offset, int len) throws IOException {
		writer.write(cbuf, offset, len);
	}

	@Override
	public DefaultSimpleWriter append(CharSequence csq, int start, int end) throws IOException {
		writer.append(csq, start, end);
		return this;
	}

	@Override
	public void write(char c) throws IOException {
		writer.append(c);
	}

	@Override
	public void write(int num) throws IOException {
		writer.write(Integer.toString(num));
	}

}
