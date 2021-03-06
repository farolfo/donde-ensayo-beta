<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%-- <c:set var="req" value="${pageContext.request}" /> --%>
<%-- <c:set var="uri" value="${req.requestURI}" /> --%>
<%-- <c:set var="url">${req.requestURL}</c:set> --%>
<%-- <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" /> --%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="icon" type="image/ico" href="<c:url value='/img/icon.ico'/>">

<title>elManager</title>

<!-- CSS bootstrap styles -->
<link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet">
<!-- CSS PawBook styles -->
<link href="<c:url value='/css/pawbook.css'/>" rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]
-->
<style type="text/css">
body {
/* 	padding-top: 60px; */
 	padding-bottom: 40px; 
 	background-image:url('../../img/wall3.jpg'); 
}
</style>

<!-- Javascript -->

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE&sensor=false"></script>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>

<script type="text/javascript" src="<c:url value='/js/jquery-1.8.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.numeric.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/elmanager.js'/>"></script>


<%-- <script type="text/javascript" src="<c:url value='/js/commons.js'/>"></script> --%>







