package com.myself.gyl.query.business;

import java.util.Date;
import java.util.Map;

import com.myself.gyl.query.QueryCondition;


public class XsysdzhubQuery extends QueryCondition {
	private Long ddh;
	private String gs;
	private Date rq;//日期
	private Date qsrq;//起算日期
	private String ks;//客商
	private String bm;//部门
	private String ywy;//业务员
	private String sxbz;//生效标志
	private String zdr;//制单人
	private Date zdrq;//制单日期
	private String shr;//审核人
	private Date shrq;//审核日期
	private String qzr;//签字人
	private Date qzrq;//签字日期
	private String xgr;//修改人
	private Date xgrq;//修改日期

	
	@Override
	public Map<String, Object> buildQueryConditions() {
		if(this.ddh != null){
			this.nameValues.put("ddh", ddh);
		}
		if(this.gs != null){
			this.nameValues.put("gs", gs);
		}
		if(this.rq!=null){
			this.nameValues.put("rq",rq);
		}
		if(this.qsrq!=null){
			this.nameValues.put("qsrq",qsrq);
		}
		
		if(this.ks!=null){
			this.nameValues.put("ks",ks);
		}
		if(this.bm!=null){
			this.nameValues.put("bm",bm);
		}
		if(this.ywy!=null){
			this.nameValues.put("ywy",ywy);
		}
		if(this.sxbz!=null){
			this.nameValues.put("sxbz",sxbz);
		}
		
		if(this.zdr!=null){
			this.nameValues.put("zdr",zdr);
		}
		if(this.zdrq!=null){
			this.nameValues.put("zdrq",zdrq);
		}
		if(this.shr!=null){
			this.nameValues.put("shr",shr);
		}
		if(this.shrq!=null){
			this.nameValues.put("shrq",shrq);
		}
		if(this.qzr!=null){
			this.nameValues.put("qzr",qzr);
		}
		if(this.qzrq!=null){
			this.nameValues.put("qzrq",qzrq);
		}
		if(this.xgr!=null){
			this.nameValues.put("xgr",xgr);
		}
		if(this.xgrq!=null){
			this.nameValues.put("xgrq",xgrq);
		}
		return this.nameValues;
	}
}
