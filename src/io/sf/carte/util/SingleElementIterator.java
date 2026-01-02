/*

 Copyright (c) 2005-2026, Carlos Amengual.

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause
 */

package io.sf.carte.util;

import java.util.Iterator;

/**
 * Iterates an immutable collection composed by a single element.
 * 
 * @param <E>
 *            the element type.
 */
public class SingleElementIterator<E> implements Iterator<E> {

	private E element;

	/**
	 * Construct a new iterator with the given element.
	 * 
	 * @param element the element.
	 * @throws NullPointerException if the element is {@code null}.
	 */
	public SingleElementIterator(E element) {
		super();
		if (element == null) {
			throw new NullPointerException();
		}
		this.element = element;
	}

	@Override
	public boolean hasNext() {
		return element != null;
	}

	@Override
	public E next() {
		E ret = element;
		element = null;
		return ret;
	}

}
