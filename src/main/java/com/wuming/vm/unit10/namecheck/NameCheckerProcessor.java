package com.wuming.vm.unit10.namecheck;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.unit10.namecheck
 * @Class NameCheckerProcessor
 * @Author: WuMing
 * @CreateDate: 2018/12/6 15:45
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckerProcessor extends AbstractProcessor {
	private NameChecker nameChecker;
	@Override
	public void init(ProcessingEnvironment processingEnvironment){
		super.init(processingEnvironment);
		nameChecker=new NameChecker(processingEnvironment);
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if(!roundEnv.processingOver()){
			for(Element element:roundEnv.getRootElements()){
				nameChecker.checkNames(element);
			}
		}
		return false;
	}
	
	
}
