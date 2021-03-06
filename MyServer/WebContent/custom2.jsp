<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="index.jsp"%>
<!--errorPage="index.jsp" 페이지에 에러가 발생하면 index.jsp 로 자동으로 이동한다.  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 시간포맷팅 -->
<c:set var="date" value="<%= new Date() %>"/>
<fmt:formatDate value="${date }" type="both" dateStyle="short" timeStyle="short"/> <br />
<fmt:formatDate value="${date }" type="both" dateStyle="medium" timeStyle="medium"/>	<br />
<fmt:formatDate value="${date }" type="both" dateStyle="long" timeStyle="long"/> <br />
<fmt:formatDate value="${date }" type="both" dateStyle="full" timeStyle="full"/> <br />

<!-- 숫자 -->
<fmt:formatNumber value="100000000" type="currency" currencySymbol="$" /> <br />
<fmt:formatNumber value="0.80" type="percent" /> <br />

<!-- 로케일 -->
<fmt:setLocale value="ko_kr"/>
<fmt:formatDate value="${date }" type="both" dateStyle="full" timeStyle="full"/> <br />
<fmt:formatNumber value="100000000" type="currency" /> <br />
<fmt:setLocale value="en_us"/>
<fmt:formatDate value="${date }" type="both" dateStyle="full" timeStyle="full"/> <br />
<fmt:formatNumber value="100000000" type="currency"  /> <br />
<fmt:setLocale value="ja_jp"/>
<fmt:formatDate value="${date }" type="both" dateStyle="full" timeStyle="full"/> <br />
<fmt:formatNumber value="100000000" type="currency"  /> <br />

<!-- 시간대 -->
<fmt:setTimeZone value="Asia/Hong_Kong"/>
<fmt:formatDate value="${date }" type="both" dateStyle="full" timeStyle="full"/> <br />

