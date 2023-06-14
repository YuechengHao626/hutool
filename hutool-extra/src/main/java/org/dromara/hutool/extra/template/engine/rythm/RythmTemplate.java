/*
 * Copyright (c) 2023 looly(loolly@aliyun.com)
 * Hutool is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *          http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package org.dromara.hutool.extra.template.engine.rythm;

import org.dromara.hutool.core.convert.Convert;
import org.dromara.hutool.core.reflect.TypeReference;
import org.dromara.hutool.extra.template.Template;

import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;

/**
 * Rythm模板包装
 *
 * @author looly
 *
 */
public class RythmTemplate implements Template, Serializable {
	private static final long serialVersionUID = -132774960373894911L;

	private final org.rythmengine.template.ITemplate rawTemplate;

	/**
	 * 包装Rythm模板
	 *
	 * @param template Rythm的模板对象 {@link org.rythmengine.template.ITemplate}
	 * @return {@code RythmTemplate}
	 */
	public static RythmTemplate wrap(final org.rythmengine.template.ITemplate template) {
		return (null == template) ? null : new RythmTemplate(template);
	}

	/**
	 * 构造
	 *
	 * @param rawTemplate Velocity模板对象
	 */
	public RythmTemplate(final org.rythmengine.template.ITemplate rawTemplate) {
		this.rawTemplate = rawTemplate;
	}

	@Override
	public void render(final Map<?, ?> bindingMap, final Writer writer) {
		final Map<String, Object> map = Convert.convert(new TypeReference<Map<String, Object>>() {}, bindingMap);
		rawTemplate.__setRenderArgs(map);
		rawTemplate.render(writer);
	}

	@Override
	public void render(final Map<?, ?> bindingMap, final OutputStream out) {
		rawTemplate.__setRenderArgs(bindingMap);
		rawTemplate.render(out);
	}
}