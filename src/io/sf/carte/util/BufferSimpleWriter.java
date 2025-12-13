/*

 Copyright (c) 2005-2025, Carlos Amengual.

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause
 */

package io.sf.carte.util;

import java.io.IOException;

/**
 * An implementation of {@link SimpleWriter} backed by a {@link StringBuilder}.
 */
public class BufferSimpleWriter implements SimpleWriter {

	private final StringBuilder buffer;

	/**
	 * Initializes a <code>BufferSimpleWriter</code> with an initial buffer capacity
	 * of 128 characters.
	 */
	public BufferSimpleWriter() {
		super();
		buffer = new StringBuilder(128);
	}

	/**
	 * Initializes a <code>BufferSimpleWriter</code> with an initial buffer capacity
	 * of <code>initialCapacity</code> characters.
	 * 
	 * @param initialCapacity the buffer initial capacity.
	 */
	public BufferSimpleWriter(int initialCapacity) {
		super();
		buffer = new StringBuilder(initialCapacity);
	}

	/**
	 * Initializes a <code>BufferSimpleWriter</code> with the given buffer.
	 * 
	 * @param buffer the <code>StringBuilder</code> buffer.
	 */
	public BufferSimpleWriter(StringBuilder buffer) {
		super();
		this.buffer = buffer;
	}

	/**
	 * Get the {@code StringBuilder} buffer.
	 * 
	 * @return the buffer.
	 */
	public StringBuilder getBuffer() {
		return buffer;
	}

	/**
	 * Get the count of the characters in this object.
	 * 
	 * @return the count of the characters.
	 */
	public int length() {
		return buffer.length();
	}

	@Override
	public void newLine() {
		buffer.append('\n');
	}

	/**
	 * Return the contents of this buffer as a <code>String</code>.
	 * 
	 * @return the contents of this buffer.
	 */
	@Override
	public String toString() {
		return buffer.toString();
	}

	/**
	 * Remove the last character found in this buffer, if any.
	 */
	@Override
	public void unwrite() {
		int len = buffer.length();
		if (len != 0) {
			buffer.setLength(len - 1);
		}
	}

	/**
	 * Get the last character processed.
	 * 
	 * @return the last character processed.
	 * @throws IllegalStateException if no character was processed yet.
	 */
	@Override
	public char getLastChar() throws IllegalStateException {
		int len = buffer.length();
		if (len == 0) {
			throw new IllegalStateException();
		}
		return buffer.charAt(len - 1);
	}

	/**
	 * Remove the last <code>numChars</code> characters found in this buffer.
	 * <p>
	 * If <code>numChars</code> is larger than the length of this buffer, removes
	 * all its contents.
	 * 
	 * @param numChars the number of characters to remove.
	 */
	@Override
	public void unwrite(int numChars) {
		int len = buffer.length() - numChars;
		if (len < 0) {
			len = 0;
		}
		buffer.setLength(len);
	}

	@Override
	public void write(CharSequence s) {
		buffer.append(s);
	}

	@Override
	public BufferSimpleWriter append(CharSequence csq, int start, int end) throws IOException {
		buffer.append(csq, start, end);
		return this;
	}

	@Override
	public void write(char[] cbuf, int offset, int len) {
		buffer.append(cbuf, offset, len);
	}

	@Override
	public void write(char c) {
		buffer.append(c);
	}

	@Override
	public void write(int num) {
		buffer.append(num);
	}

}
