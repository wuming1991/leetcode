package com.wuming.pattern.Mediator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Mediator
 * @Class RealMediator
 * @Author: WuMing
 * @CreateDate: 2018/7/17 11:16
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class RealMediator implements Mediator {
	
	Financial financial;
	Development development;
	Market market;
	
	public Financial getFinancial() {
		return financial;
	}
	
	public void setFinancial(Financial financial) {
		this.financial = financial;
	}
	
	public Development getDevelopment() {
		return development;
	}
	
	public void setDevelopment(Development development) {
		this.development = development;
	}
	
	public Market getMarket() {
		return market;
	}
	
	public void setMarket(Market market) {
		this.market = market;
	}
	
	@Override
	public void communicate(Department department) {
		if (department instanceof Financial) {
			development.listen();
			market.listen();
		}
		if (department instanceof Development) {
			financial.listen();
			market.listen();
		}
		if (department instanceof Market) {
			development.listen();
			financial.listen();
		}
	}
}
