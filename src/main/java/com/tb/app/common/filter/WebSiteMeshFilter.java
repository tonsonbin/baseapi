package com.tb.app.common.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle;

public class WebSiteMeshFilter  extends ConfigurableSiteMeshFilter {
	
	@Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		
		//默认
        builder.addDecoratorPaths("/view/**", "/WEB-INF/decorator/default.jsp");
        //sys，当多个修饰时，越后面的越先加载
        builder.addDecoratorPaths("/view/sys/**","/WEB-INF/decorator/expend-bottomMenu.jsp", "/WEB-INF/decorator/sys.jsp","/WEB-INF/decorator/default.jsp");
        builder.addDecoratorPaths("/view/sys/unauth/login", "/WEB-INF/decorator/sys.jsp","/WEB-INF/decorator/default.jsp","/WEB-INF/decorator/default.jsp");
        /**
         * 
         * 其他开始>>
         * 
         **/
        //builder.addDecoratorPaths("/view/nc/**", "/WEB-INF/decorator/newcontract.jsp","/WEB-INF/decorator/default.jsp");
        
        /**
         * 
         * 其他结束<<
         * 
         * 
         */
        //.addDecoratorPath("/**", "/WEB-INF/decorator/default.jsp");
        builder.addTagRuleBundles(new DivExtractingTagRuleBundle()); 
//        也可使用Controller请求映射
//        builder.addDecoratorPath("/task/index", "/task/decorator").addExcludedPath("/task/decorator");
		
    }
	
}
