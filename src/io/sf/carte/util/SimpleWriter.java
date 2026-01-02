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

/**
 * A simple writer interface.
 * <p>
 * Similar to a subset from {@link java.io.Writer}, but with {@link #newLine()} method
 * added, similar to {@link java.io.BufferedWriter#newLine()} but with the difference that
 * the newline not necessarily uses the <code>line.separator</code> system property and
 * instead may target another system.
 *
 */
public interface SimpleWriter extends Appendable {

	/**
	 * Write a newline according to the characteristics of the target platform.
	 * 
	 * @throws IOException
	 *             if an error happens when writing.
	 */
	void newLine() throws IOException;

	/**
	 * Write a character sequence.
	 * 
	 * @param seq the CharSequence.
	 * @throws IOException if an error happens when writing.
	 */
	void write(CharSequence seq) throws IOException;

	@Override
	default SimpleWriter append(CharSequence csq) throws IOException {
		write(csq);
		return this;
	}

	@Override
	default SimpleWriter append(CharSequence csq, int start, int end) throws IOException {
		if (csq == null) {
			csq = "null";
		}
		write(csq.subSequence(start, end));
		return this;
	}

	/**
	 * Write a sequence of characters from an array.
	 * 
	 * @param cbuf
	 *            the character array.
	 * @param off
	 *            the offset to start writing.
	 * @param len
	 *            the number of characters to write from the array.
	 * @throws IOException
	 *             if an error happens when writing.
	 */
	void write(char[] cbuf, int off, int len) throws IOException;

	/**
	 * Write a character.
	 * 
	 * @param c
	 *            the character.
	 * @throws IOException
	 *             if an error happens when writing.
	 */
	void write(char c) throws IOException;

	@Override
	default SimpleWriter append(char c) throws IOException {
		write(c);
		return this;
	}

	/**
	 * Write an integer.
	 * 
	 * @param num
	 *            the integer number.
	 * @throws IOException
	 *             if an error happens when writing.
	 */
	default void write(int num) throws IOException {
		write(Integer.toString(num));
	}

	/**
	 * Remove the last character found in this buffer, if any.
	 * <p>
	 * Optional operation.
	 * </p>
	 * 
	 * @throws UnsupportedOperationException if the operation is not supported.
	 */
	default void unwrite() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot unwrite.");
	}

	/**
	 * Remove the last <code>numChars</code> characters found in this buffer.
	 * <p>
	 * If <code>numChars</code> is larger than the length of this buffer, removes
	 * all its contents.
	 * 
	 * @param numChars the number of characters to remove.
	 * @throws UnsupportedOperationException if the operation is not supported.
	 */
	default void unwrite(int numChars) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot unwrite.");
	}

	/**
	 * Get the last character processed.
	 * <p>
	 * Optional operation.
	 * </p>
	 * 
	 * @return the last character processed.
	 * @throws UnsupportedOperationException if the operation is not supported.
	 * @throws IllegalStateException         if no character was processed yet.
	 */
	default char getLastChar() throws UnsupportedOperationException, IllegalStateException {
		throw new UnsupportedOperationException("Cannot retrieve the last character.");
	}

}
