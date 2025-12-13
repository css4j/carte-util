/*

 Copyright (c) 2005-2025, Carlos Amengual.

 Licensed under a BSD-style License. You can find the license here:
 https://css4j.github.io/LICENSE.txt

 */
/*
  SPDX-License-Identifier: BSD-3-Clause OR BSD-2-Clause
 */

package io.sf.carte.util.agent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class AgentUtilTest {

	@Test
	public void testFindCharset() {
		String conType = "text/html;";
		assertNull(AgentUtil.findCharset(conType, 10));
		conType = "text/html; charset=";
		assertNull(AgentUtil.findCharset(conType, 10));
		conType = "text/html; charset =";
		assertNull(AgentUtil.findCharset(conType, 10));
		conType = "text/html;charset=utf-8";
		assertEquals("utf-8", AgentUtil.findCharset(conType, 10));
		conType = "text/html; charset=utf-8";
		assertEquals("utf-8", AgentUtil.findCharset(conType, 10));
		conType = "text/html;charset=\"utf-8\"";
		assertEquals("utf-8", AgentUtil.findCharset(conType, 10));
		conType = "text/html;charset='utf-8'";
		assertEquals("utf-8", AgentUtil.findCharset(conType, 10));
	}

}
