/*

 Copyright (c) 2005-2025, Carlos Amengual.

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause
 */

package io.sf.carte.util;

/**
 * A visitor (from the 'visitor' pattern).
 * 
 * @param <T> the type to visit.
 */
public interface Visitor<T> {

	/**
	 * Visit the given object.
	 * 
	 * @param arg the object.
	 */
	void visit(T arg);

}
