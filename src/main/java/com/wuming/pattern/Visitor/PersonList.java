package com.wuming.pattern.Visitor;

import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.VariableElement;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Visitor
 * @Class PersonList
 * @Author: WuMing
 * @CreateDate: 2018/7/19 19:01
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class PersonList {
	
	List<Person> list = new ArrayList<>(10);
	
	public void addPerson(Person p) {
		list.add(p);
	}
	
	public void select(PersonVisitor visitor) {
		for (Person person : list) {
			person.accept(visitor);
		}
	}
}
