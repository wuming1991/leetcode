package com.wuming.pattern.Mediator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Mediator
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/17 11:40
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		RealMediator mediator = new RealMediator();
		Financial financial = new Financial(mediator);
		Market market = new Market(mediator);
		Development development = new Development(mediator);
		mediator.setFinancial(financial);
		mediator.setDevelopment(development);
		mediator.setMarket(market);
		development.speak();
		financial.speak();
		market.speak();
	}
}
