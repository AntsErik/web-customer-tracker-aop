<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>

    <title>List Customers</title>
    
    <!-- reference out style sheet -->
    
    <link   type="text/css"
            rel="stylesheet"
            href="${pageContext.request.contextPath}/resources/css/style.css" />
    
</head>

<body>

    <div id="wrapper">
    
        <div id="header">
        
            <h2>CRM - Customer Relationship Manager</h2>
            
        </div>
        
    </div>
    
    <div id="container">
    
        <div id="content">
            
            <!-- adding the Customer adding button -->
            
            <input  type="button" value="Add Customer" 
                    onclick="window.location.href='showFormForAdd'; return false;" 
                    class="add-button" />
                    
            <!-- adding search module -->
            
            <!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>        
            
            <!-- add html table here -->
            
            <table>
                
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Action</th>                    
                </tr>
                
                <!--  loop over and print our Customers -->
                <c:forEach var="tempCustomer" items="${customers}">
                    
                    <!-- Construct an "update" link with Customer ID -->
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${tempCustomer.id}" />                    
                    </c:url>
 
                    <!-- Construct a "delete" link with Customer ID -->                   
                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${tempCustomer.id}" />                    
                    </c:url>
                    
                    
                    <tr>
                        <td>${tempCustomer.firstName}</td>
                        <td>${tempCustomer.lastName}</td>
                        <td>${tempCustomer.email}</td>
                                                
                        <td>
                        <!-- display the update link -->
                        <a href="${updateLink}">Update</a>
                        |
                        <a  href="${deleteLink}"
                            onclick="if (!(confirm('Are you sure you want to delete this Customer?'))) return false">
                            Delete</a>
                        </td>
                        
                    </tr>
                    
                </c:forEach>
                
            </table>
            
        </div>
        
    </div>

</body>

</html>