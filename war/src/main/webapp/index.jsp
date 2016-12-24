<!doctype html>
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
<c:set var="metaDescription" scope="page"><fmt:message key="indexMetaDescription"/></c:set>
<jsp:useBean id="baseSession" class="base.http.BaseSession" type="base.http.BaseSession" scope="session"></jsp:useBean>
<jsp:setProperty name="baseSession" property="httpSession" value="${pageContext.session}"></jsp:setProperty>
<jsp:setProperty name="baseSession" property="language" value="${language}"></jsp:setProperty>
<jsp:useBean id="cd" class="model.Cd" type="model.Cd" scope="request"/>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="indexTitle"/></title>
    <jsp:include page="droplet/head.jsp" flush="true">
        <jsp:param name="metaDescription" value="${metaDescription}"/>
    </jsp:include>
</head>
<body>
<div id="wrapper">
    <h1><fmt:message key="indexH1"/></h1>
    <div class="spacer">
        <ul>
            <li><a href="cdsServlet">cdsServlet</a></li>
            <li><a href="http://localhost:7070/wildfly10core/CdsFinder">CdsFinder</a></li>
            <li><a href="http://localhost:7070/wildfly10core/CdsFinder/findCdById;id=1;">findCdById</a></li>
            <li><a href="http://127.0.0.1:7070/wildfly10core/HelloWebService?wsdl">http://127.0.0.1:7070/wildfly10core/HelloWebService?wsdl</a></li>
        </ul>
        <div id="cds">
            <form method="post" id="formCd" name="formCd" action="cdsServlet">
                <input type="hidden" id="step" name="step" value="${baseSession.step}"/>
                <label id="titleL"><fmt:message key="cdsTitle"/>
                    <input type="text" id="title" name="title" value="<jsp:getProperty name="cd" property="title"/>"/>
                </label>
                <label id="artistL"><fmt:message key="cdsArtist"/>
                    <input type="text" id="artist" name="artist" value="<jsp:getProperty name="cd" property="artist"/>"/>
                </label>
                <label id="yearL"><fmt:message key="cdsYear"/>
                    <input type="text" id="year" name="year" value="<jsp:getProperty name="cd" property="year"/>"/>
                </label>
                <input type="button" id="buttonSubmit" name="buttonSubmit" value="<fmt:message key="submit"/>"/>
            </form>
        </div>
        <ul>
            <li><a href="cdsServlet?step=printAllCdInHtmlTable"><fmt:message key="printAllCdInHtmlTable"/></a></li>
        </ul>
    </div>
    <div class="spacer">
        <ol start="27">
            <li id="27">XXVII</li>
            <li class="28">XXVIII</li>
            <li id="createBook"><fmt:message key="createBook"/></li>
            <li id="displayBook"><fmt:message key="displayBook"/></li>
            <li id="displayAllBooks"><fmt:message key="displayAllBooks"/></li>
            <li id="cleanBooksDiv"><fmt:message key="cleanBooksDiv"/></li>
            <li id="cleanCitiesDiv"><fmt:message key="cleanCitiesDiv"/></li>
        </ol>

        <h2><fmt:message key="createBook"/></h2>
        <form method="get" id="createBookForm" name="createBookForm" action="createBook">
            <input type="hidden" id="language" name="language" value="${language}"/>
            <label for="author"><input id="author" name="author" type="text" value=""/></label>
            <label for="title"><input id="title" name="title" type="text" value=""/></label>
            <input id="submit" name="submit" type="submit" value="<fmt:message key="createBook"/>"/></label>
        </form>
    </div>
    <div id="books" class="spacer"></div>
    <div class="spacer">
        <ul>
            <li id="autocompleteDisplayCities"><fmt:message key="autocompleteDisplayCities"/></li>
            <li id="autocompleteIsJQueryUiAutoCompleteWidgetJsonpSource"><fmt:message key="autocompleteIsJQueryUiAutoCompleteWidgetJsonpSource"/></li>
        </ul>
        <div id="cities" class="spacer"></div>
        <input type="text" id="city" name="city" value=""/>
    </div>
    <div class="spacer">
        <p><a href="apps.jsp"><fmt:message key="apps"/></a></p>
    </div>
</div>
</body>
</html>