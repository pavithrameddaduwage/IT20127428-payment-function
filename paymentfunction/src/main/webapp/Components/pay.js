 $(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidpaymentIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "paymentAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 });   
 


});
function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 14
 $("#hidpaymentIDSave").val(""); 
 $("#formItem")[0].reset(); 
}  

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidpaymentIDSave").val($(this).data("paymentid")); 
 $("#userID").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#Amount").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#paymenttype").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#Date").val($(this).closest("tr").find('td:eq(3)').text()); 
});
 // DELETE==========================================
 $(document).on("click", "btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "paymentAPI", 
 type : "DELETE ", 
 data : "paymentID" + $(this).data("paymentid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
 function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
});

 
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#userID").val().trim() == "") 
 { 
 return "Insert User ID."; 
 } 
// NAME
if ($("#Amount").val().trim() == "") 
 { 
 return "Insert the Total Payment ."; 
 } 
 
// PRICE-------------------------------
if ($("#paymenttype").val().trim() == "") 
 { 
 return "Insert Payment Type."; 
 } 
 
// DESCRIPTION------------------------
if ($("#Date").val().trim() == "") 
 { 
 return "Insert the Date of Payment."; 
 } 
return true; 
}
