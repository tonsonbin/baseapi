<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/common/tlds/fns.tld" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/common/tlds/fnc.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<c:set var="ct" value="${pageContext.request.contextPath}"/>
<c:set var="ctapi" value="${pageContext.request.contextPath}${fns:getApiPath() }"/>
<c:set var="ctview" value="${pageContext.request.contextPath}${fns:getViewPath() }"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
