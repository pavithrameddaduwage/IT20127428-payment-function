<%@page import="model.pay"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Details</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/pay.js"></script>

</head>
<body>
<div class="container"><div class="row"><div class="col-6"> 
<h1>Payment Details Form</h1>
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

 

<form id="formItem" name="formItem">
 User ID
 <input id="userID" name="userID" type="text" 
 class="form-control form-control-sm">
 <br>  Amount
 <input id="Amount" name="Amount" type="text" 
 class="form-control form-control-sm">
 <br> Payment Type
 <input id="paymenttype" name="paymenttype" type="text" 
 class="form-control form-control-sm">
 <br> Date 
 <input id="Date" name="Date" placeholder="MM/DD/YYY" type="date" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidpaymentIDSave"name="hidpaymentIDSave" value="">
</form>
 
<br>
<div id="divItemsGrid">
 <%
  
 pay itemObj = new pay(); 
 out.print(itemObj.readItems()); 
 %>
</div>
</div> </div> </div> 



</body>
</html>