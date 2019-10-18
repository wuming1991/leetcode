package com.wuming.vm.unit10.namecheck;


import static javax.lang.model.element.ElementKind.ENUM_CONSTANT;
import static javax.lang.model.element.ElementKind.FIELD;
import static javax.lang.model.element.ElementKind.INTERFACE;
import static javax.lang.model.element.ElementKind.METHOD;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.tools.Diagnostic.Kind.WARNING;

import java.util.EnumSet;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.unit10.namecheck
 * @Class NameChecker
 * @Author: WuMing
 * @CreateDate: 2018/12/6 15:47
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class NameChecker {
	
	private final Messager messager;
	NameCheckScanner nameCheckScanner = new NameCheckScanner();
	
	public NameChecker(ProcessingEnvironment processingEnvironment) {
		this.messager = processingEnvironment.getMessager();
	}
	
	public void checkNames(Element element) {
		nameCheckScanner.scan(element);
	}
	
	private class NameCheckScanner extends ElementScanner6<Void, Void> {
		
		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCase(e, true);
			super.visitType(e, p);
			return null;
		}
		
		@Override
		public Void visitExecutable(ExecutableElement e, Void aVoid) {
			if (e.getKind() == METHOD) {
				Name name = e.getSimpleName();
				if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
					messager.printMessage(WARNING, "普通方法" + name + "不能与类名重复,避免与构造混淆", e);
				}
				checkCamelCase(e, false);
			}
			super.visitExecutable(e, aVoid);
			return null;
		}
		
		@Override
		public Void visitVariable(VariableElement e, Void aVoid) {
			if (e.getKind() == ENUM_CONSTANT || e.getConstantValue() != null
				|| heuristicallyConstant(e)) {
				checkAllCaps(e);
			} else {
				checkCamelCase(e, false);
			}
			return null;
		}
		
		private boolean heuristicallyConstant(VariableElement e) {
			if (e.getEnclosingElement().getKind() == INTERFACE) {
				return true;
			} else if (e.getKind() == FIELD && e.getModifiers()
				.containsAll(EnumSet.of(PUBLIC, STATIC, FINAL))) {
				return true;
			}
			return false;
		}
		
		private void checkCamelCase(Element e, boolean initialCaps) {
			String name = e.getSimpleName().toString();
			boolean preciousUpper = false;
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if (Character.isUpperCase(firstCodePoint)) {
				preciousUpper = true;
				if (!initialCaps) {
					messager.printMessage(WARNING, "名称" + name + "应以小写开头" + e);
					return;
				} else if (Character.isLowerCase(firstCodePoint)) {
					if (initialCaps) {
						messager.printMessage(WARNING, "名称" + name + "应大写开头" + e);
						return;
					}
				} else {
					conventional = false;
				}
				if (conventional) {
					int cp = firstCodePoint;
					for (int i = Character.charCount(cp); i < name.length();
						i += Character.charCount(cp)) {
						cp = name.codePointAt(i);
						if (Character.isUpperCase(cp)) {
							if (preciousUpper) {
								conventional = false;
								break;
							}
							preciousUpper = true;
						} else {
							preciousUpper = false;
						}
					}
				}
				if (!conventional) {
					messager.printMessage(WARNING, "名称" + name + "应符合驼峰命名(Camel Case Name)" + e);
					
				}
			}
		}
		
		private void checkAllCaps(Element e) {
			String name = e.getSimpleName().toString();
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			if (!Character.isUpperCase(firstCodePoint)) {
				conventional = false;
			} else {
				boolean previouUnderscore = false;
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length();
					i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if (cp == (int) '_') {
						if (previouUnderscore) {
							conventional = false;
							break;
						}
						previouUnderscore = true;
					} else {
						previouUnderscore = false;
						if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
							conventional = false;
							break;
						}
					}
				}
			}
			if (!conventional) {
				messager.printMessage(WARNING, "常量" + name + "应使用大写字母和下划线,且以字母开头" + e);
			}
		}
	}
}
