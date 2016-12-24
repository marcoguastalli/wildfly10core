<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text"/>
<c:set var="metaDescription" scope="page"><fmt:message key="errorMetaDescription"/></c:set>
<jsp:useBean id="baseSession" class="base.http.BaseSession" type="base.http.BaseSession" scope="session"></jsp:useBean>
<!doctype html>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="errorTitle"/></title>
    <jsp:include page="droplet/head.jsp" flush="true">
        <jsp:param name="metaDescription" value="${metaDescription}"/>
    </jsp:include>
</head>
<body>
<div id="wrapper">
    <h1><fmt:message key="error"/></h1>
</div>
</body>
</html>