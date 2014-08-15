<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weather Application</title>
<spring:url value="/resources/" var="staticLocation" />
<link rel="stylesheet" type="text/css"
	href="${staticLocation}css/weatherApp.css" />
<script type="text/javascript"
	src="${staticLocation}js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${staticLocation}js/weatherApp.js"></script>
</head>
<body>
	<h3 style="text-align: center;">Weather Application</h3>
	<div class="zipContainer">
		<div id="zipCodeSection" class="zipCodeClass">
			<label for="zipCode">Enter ZIP:</label> 
			<input type="text" maxlength="5" class="zipCode" id="zipCode" /> <br /> 
			<span class="errorMessage" id="errorMessage"></span> 
			<input type="submit" class="zipCodeButton" id="zipCodeButton" value="Submit" />
		</div>
		<div id="zipCodeDataSection" class="zipCodeDataSection">
			<table>
				<tbody>
					<tr>
						<td>Temperature:</td>
						<td><span id="temperature"></span>&deg;<span id="tempUnit">F</span></td>
					</tr>
					<tr>
						<td>City:</td>
						<td id="city"></td>
					</tr>
					<tr>
						<td>State:</td>
						<td id="state"></td>
					</tr>
					<tr>
						<td>ZIP:</td>
						<td id="zip"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>